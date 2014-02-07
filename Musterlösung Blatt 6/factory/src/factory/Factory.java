package factory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * This class represents a Factory, having assembly lines and products it can produce
 * 
 * @author markus
 * @version 1.0
 */
public class Factory {
    
    private final ArrayList<AssemblyLine> lines;
    private final Map<AssemblyStation, Integer> minimalTimes;
    
    private final Map<AssemblyStation, AssemblyStation> predecessors;
    private final ArrayList<Product> products;
    
    /**
     * Instantiates a new factory.
     */
    public Factory() {
        this.lines = new ArrayList<AssemblyLine>();
        this.products = new ArrayList<Product>();
        
        this.minimalTimes = new HashMap<AssemblyStation, Integer>();
        this.predecessors = new HashMap<AssemblyStation, AssemblyStation>();
    }
    
    /**
     * Adds the line.
     * 
     * @param line
     *            the line
     */
    public void addLine(AssemblyLine line) {
        this.lines.add(line);
    }
    
    /**
     * Adds the product.
     * 
     * @param product
     *            the product
     */
    public void addProduct(Product product) {
        this.products.add(product);
    }
    
    /**
     * Gets the fastest way.
     * 
     * @param product
     *            the product
     * @return the fastest way
     */
    public List<AssemblyStation> getFastestWay(Product product) {
    	if (lines.size() == 2) {
    		return this.calculateFastestWayTwoStations(this.getLines(product));
    	} else {
    		return this.calculateFastestWay(this.getLines());
    	} 
    }
    
    /**
     * Gets the line.
     * 
     * @param lineId
     *            the line id
     * @return the line
     */
    public AssemblyLine getLine(String lineId) {
        for (final AssemblyLine line : this.lines) {
            if (lineId.equals(line.getLineId())) {
                return line;
            }
        }
        return null;
    }
    
    /**
     * Gets the lines.
     * 
     * @return the lines
     */
    public List<AssemblyLine> getLines() {
        return this.lines;
    }
    
    /**
     * Gets the lines.
     * 
     * @param product
     *            the product
     * @return the lines
     */
    public List<AssemblyLine> getLines(Product product) {
        final List<AssemblyLine> result = new ArrayList<AssemblyLine>();
        for (final AssemblyLine line : this.lines) {
            if (line.assembles(product)) {
                result.add(line);
            }
        }
        return result;
    }
    
    /**
     * Gets the products.
     * 
     * @return the products
     */
    public List<Product> getProducts() {
        return this.products;
    }

	/**
	 * Calculate fastest way through exactly two lines.
	 * 
	 * @param lines
	 *            the lines
	 * @return the list
	 */
	private List<AssemblyStation> calculateFastestWayTwoStations(List<AssemblyLine> lines) {
	    final LinkedList<AssemblyStation> path = new LinkedList<AssemblyStation>();
	    final AssemblyLine lineA = lines.get(0);
	    if (lines.size() == 1) {
	        for (int i = 1; i <= lineA.getNumberOfStations(); i++) {
	            path.addFirst(lineA.getStation(i));
	        }
	    } else {
	        final AssemblyLine lineB = lines.get(1);
	        final int n = Math.min(lineA.getNumberOfStations(),
	                lineB.getNumberOfStations());
	        this.minimalTimes.put(lineA.getStation(1), lineA.getStation(1)
	                .getProductionTime());
	        this.minimalTimes.put(lineB.getStation(1), lineB.getStation(1)
	                .getProductionTime());
	        // dynamically calculate for each station its optimal time and
	        // predecessor
	        for (int i = 2; i <= n; i++) {
	            this.calculateMinimumForTwoStations(lineA, lineB, i);
	            this.calculateMinimumForTwoStations(lineB, lineA, i);
	        }
	        if (this.minimalTimes.get(lineA.getStation(n)) <= this.minimalTimes
	                .get(lineB.getStation(n))) {
	            path.add(lineA.getStation(n));
	        } else {
	            path.add(lineB.getStation(n));
	        }
	        AssemblyStation station = path.get(0);
	        while (this.predecessors.containsKey(station)) {
	            station = this.predecessors.get(station);
	            path.addFirst(station);
	        }
	    }
	    return path;
	}

	/**
	 * Calculate minimum for exaclty two stations.
	 * 
	 * @param A
	 *            the a
	 * @param B
	 *            the b
	 * @param stationNumber
	 *            the station number
	 */
	private void calculateMinimumForTwoStations(AssemblyLine lineA,
	        AssemblyLine lineB, int stationNumber) {
	    final AssemblyStation predecessorA = lineA
	            .getStation(stationNumber - 1);
	    final AssemblyStation predecessorB = lineB
	            .getStation(stationNumber - 1);
	    final AssemblyStation stationA = lineA.getStation(stationNumber);
	    final int preTimeA = this.minimalTimes.get(predecessorA);
	    final int preTimeB = this.minimalTimes.get(predecessorB);
	    if (preTimeA <= (preTimeB + predecessorB.getTransitionTime(stationA))) {
	        this.minimalTimes.put(stationA, stationA.getProductionTime()
	                + preTimeA);
	        this.predecessors.put(stationA, predecessorA);
	    } else {
	        // Band switch
	        this.minimalTimes.put(stationA, stationA.getProductionTime()
	                + preTimeB + predecessorB.getTransitionTime(stationA));
	        this.predecessors.put(stationA, predecessorB);
	    }
	}
	
	
	//Task 6.C
	/**
	 * Calculates the minimum out of multiple stations.
	 * @param lines the lines that can be used
	 * @param stationNumber the best available station 
	 */
	 private void calculateMinimumForStation(List<AssemblyLine> lines, int stationNumber) {
	        for (AssemblyLine sourceLine : lines) {
	            AssemblyStation predecessor = sourceLine.getStation(stationNumber-1);
	            int preTime = minimalTimes.get(predecessor);
	            for (AssemblyLine targetLine : lines) {
	                AssemblyStation station = targetLine.getStation(stationNumber);
	                int time = preTime + predecessor.getTransitionTime(station) + station.getProductionTime();
	                if (time <= minimalTimes.get(station)) {
	                    minimalTimes.put(station, time);
	                    predecessors.put(station, predecessor);
	                }
	            }
	        }
	    }

	 
	 	/**
	 	 * Calculates the fastest way through a flexible amount of lines
	 	 * @param lines the lines that can be used
	 	 * @return the best way through the given lines
	 	 */
    private List<AssemblyStation> calculateFastestWay(List<AssemblyLine> lines) {
        // initialize all times by regular assembly-line time
        for (AssemblyLine line : lines) {
            int time = 0;
            AssemblyStation predecessor = null;
            for (AssemblyStation station : line) {
                time += station.getProductionTime();
                minimalTimes.put(station, time);
                if (predecessor != null) {
                    predecessors.put(station, predecessor);
                } 
                predecessor = station;
            }
        }
	        // dynamically calculate for each station its optimal time and predecessor
        for (int i = 2; i <= lines.get(0).getNumberOfStations(); i++) {
            calculateMinimumForStation(lines, i);
        }
	        // search last station of fastest path
        AssemblyStation lastStation = lines.get(0).getLastStation();
        for (AssemblyLine line : lines) {
            if (minimalTimes.get(line.getLastStation()) < minimalTimes.get(lastStation)) {
                lastStation = line.getLastStation();
            }
        }
	        // create path from predecessors
        LinkedList<AssemblyStation> path = new LinkedList<AssemblyStation>();
        AssemblyStation station = lastStation;
        path.add(station);
        while (predecessors.containsKey(station)) {
            station = predecessors.get(station);
            path.addFirst(station);
        }
	        return path;
    }
}