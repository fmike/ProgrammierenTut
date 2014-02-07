package factory;

import java.util.HashMap;

/**
 *  This class represents a Station, having a given time it needs to produce and a station number
 * 
 * @author markus
 * @version 1.0
 */
public class AssemblyStation {
    
    private final AssemblyLine line;
    private final int productionTime;
    private final int stationNumber;
    
    private final HashMap<AssemblyStation, Integer> transitionTimes;
    
    /**
     * Instantiates a new assembly station.
     * 
     * @param line
     *            the line
     * @param stationNumber
     *            the station number
     * @param productionTime
     *            the production time
     */
    public AssemblyStation(AssemblyLine line, int stationNumber,
            int productionTime) {
        this.line = line;
        this.stationNumber = stationNumber;
        this.productionTime = productionTime;
        this.transitionTimes = new HashMap<AssemblyStation, Integer>();
    }
    
    /**
     * Adds the transition.
     * 
     * @param station
     *            the station
     * @param transitionTime
     *            the transition time
     */
    public void addTransition(AssemblyStation station, int transitionTime) {
        this.transitionTimes.put(station, transitionTime);
    }
    
    /**
     * Gets the line.
     * 
     * @return the line
     */
    public AssemblyLine getLine() {
        return this.line;
    }
    
    /**
     * Gets the next station.
     * 
     * @return the next station
     */
    public AssemblyStation getNextStation() {
        return this.line.getStation(this.stationNumber + 1);
    }
    
    /**
     * Gets the production time.
     * 
     * @return the production time
     */
    public int getProductionTime() {
        return this.productionTime;
    }
    
    /**
     * Gets the station number.
     * 
     * @return the station number
     */
    public int getStationNumber() {
        return this.stationNumber;
    }
    
    /**
     * Gets the transition time.
     * 
     * @param station
     *            the station
     * @return the transition time
     */
    public int getTransitionTime(AssemblyStation station) {
        if (station.getStationNumber() != (this.stationNumber + 1)) {
            return Integer.MAX_VALUE;
        }
        if (station.getLine().equals(this.line)) {
            return 0;
        } else {
            return this.transitionTimes.get(station);
        }
    }
    
    /**
     * Checks for next station.
     * 
     * @return true, if successful
     */
    public boolean hasNextStation() {
        return this.getNextStation() != null;
    }
    
    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "[line=" + this.line + ", stationNumber=" + this.stationNumber
                + "]";
    }
}
