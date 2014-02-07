package factory;

import java.util.HashMap;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Class AssemblyLine models an assembly line with n stations and the n+1 times
 * that it takes to convey the product from each station to the next station
 * 
 * @author markus
 * @version 1.0
 */
public class AssemblyLine implements Iterable<AssemblyStation> {
    
    private final String lineId;
    
    private final String productId;
    
    /**
     * stationNumber->station
     */
    private final HashMap<Integer, AssemblyStation> stations;
    
    /**
     * Instantiates a new assembly line.
     * 
     * @param lineId
     *            the line id
     * @param productId
     *            the product id
     */
    public AssemblyLine(String lineId, String productId) {
        this.lineId = lineId;
        this.productId = productId;
        this.stations = new HashMap<Integer, AssemblyStation>();
    }
    
    /**
     * Adds the station.
     * 
     * @param station
     *            the station
     */
    public void addStation(AssemblyStation station) {
        this.stations.put(station.getStationNumber(), station);
    }
    
    /**
     * Assembles.
     * 
     * @param product
     *            the product
     * @return true, if successful
     */
    public boolean assembles(Product product) {
        return this.productId.equals(product.getId());
    }
    
    /*
     * (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        final AssemblyLine other = (AssemblyLine) obj;
        if (this.lineId == null) {
            if (other.lineId != null) {
                return false;
            }
        } else if (!this.lineId.equals(other.lineId)) {
            return false;
        }
        return true;
    }
    
    /**
     * Gets the first station.
     * 
     * @return the first station
     */
    public AssemblyStation getFirstStation() {
        return this.stations.get(1);
    }
    
    /**
     * Gets the last station.
     * 
     * @return the last station
     */
    public AssemblyStation getLastStation() {
        return this.stations.get(this.getNumberOfStations());
    }
    
    /**
     * Gets the line id.
     * 
     * @return the line id
     */
    public String getLineId() {
        return this.lineId;
    }
    
    /**
     * Gets the number of stations.
     * 
     * @return the number of stations
     */
    public int getNumberOfStations() {
        return this.stations.size();
    }
    
    /**
     * Gets the product id.
     * 
     * @return the product id
     */
    public String getProductId() {
        return this.productId;
    }
    
    /**
     * Gets the production time till station.
     * 
     * @param number
     *            the number
     * @return the production time till station
     */
    public int getProductionTimeTillStation(int number) {
        int time = 0;
        
        for (int i = 1; i <= number; i++) {
            time += this.getStation(i).getProductionTime();
        }
        
        return time;
    }
    
    /**
     * Gets the station.
     * 
     * @param number
     *            the number
     * @return the station
     */
    public AssemblyStation getStation(int number) {
        return this.stations.get(number);
    }
    
    /**
     * Gets the total production time.
     * 
     * @return the total production time
     */
    public int getTotalProductionTime() {
        return this.getProductionTimeTillStation(this.getNumberOfStations());
    }
    
    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = (prime * result)
                + ((this.lineId == null) ? 0 : this.lineId.hashCode());
        return result;
    }
    
    /*
     * (non-Javadoc)
     * @see java.lang.Iterable#iterator()
     */
    @Override
    public Iterator<AssemblyStation> iterator() {
        return new AssemblyLineIterator(this);
    }
    
    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return this.lineId;
    }
    
    private class AssemblyLineIterator implements Iterator<AssemblyStation> {
        
        private final AssemblyLine line;
        private AssemblyStation station;
        
        /**
         * Instantiates a new assembly line iterator.
         * 
         * @param line
         *            the line
         */
        public AssemblyLineIterator(AssemblyLine line) {
            this.line = line;
            this.station = null;
        }
        
        /*
         * (non-Javadoc)
         * @see java.util.Iterator#hasNext()
         */
        @Override
        public boolean hasNext() {
            if (this.line.getNumberOfStations() == 0) {
                return false;
            }
            
            if (this.station == null) {
                return true;
            } else {
                return this.station.hasNextStation();
            }
        }
        
        /*
         * (non-Javadoc)
         * @see java.util.Iterator#next()
         */
        @Override
        public AssemblyStation next() {
            if (this.line.getNumberOfStations() == 0) {
                throw new NoSuchElementException();
            }
            
            if (this.station == null) {
                this.station = this.line.getStation(1);
                return this.station;
            } else if (this.station.hasNextStation()) {
                this.station = this.station.getNextStation();
                return this.station;
            } else {
                throw new NoSuchElementException();
            }
        }
        
        /*
         * (non-Javadoc)
         * @see java.util.Iterator#remove()
         */
        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
        
    }
}