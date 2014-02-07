package factory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

/**
 * This class is used to produce the output and run the program according to task6 
 * 
 * @author markus
 * @version 1.0
 */
public final class Task6 {
    
    private Factory factory;
    
    private final boolean onlyB;
    
    /**
     * Instantiates a new task6.
     * 
     * @param filename
     *            the filename
     * @param onlyB
     *            the only b
     */
    private Task6(String filename, boolean onlyB) {
        this.factory = null;
        this.onlyB = onlyB;
        
        FactoryReader reader;
        try {
            reader = new FactoryReader(filename);
            this.factory = reader.createFactory();
        } catch (final FileNotFoundException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        } catch (final IOException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        } catch (final InputFormatException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }
    
    /**
     * Debug.
     * 
     * @param msg
     *            the msg
     */
    public static void debug(String msg) {
        System.out.println(msg);
    }
    
    /**
     * The main method.
     * 
     * @param args
     *            the arguments
     */
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Usage: java Task6 filename");
            System.exit(1);
        }
        
        final Task6 solution = new Task6(args[0], args.length > 1);
        solution.printLineProductionTimes();
        System.out.println("----");
        solution.printFastestProductionTimes();
    }
    
    /**
     * Prints the fastest production times.
     */
    private void printFastestProductionTimes() {
        final List<Product> products = this.factory.getProducts();
        for (final Product product : products) {
            final List<AssemblyLine> lines = this.factory.getLines(product);
            if ((lines.size() > 2) && this.onlyB) {
                System.out.println(product + ":nicht implementiert");
            } else {
                System.out.print(product + ":");
                final List<AssemblyStation> stations = this.factory
                        .getFastestWay(product);
                for (final AssemblyStation station : stations) {
                    System.out.print(station.getLine());
                    if (station != stations.get(stations.size() - 1)) {
                        System.out.print(",");
                    }
                }
                System.out.println();
            }
        }
    }
    
    /**
     * Prints the line production times.
     */
    private void printLineProductionTimes() {
        final List<AssemblyLine> lines = this.factory.getLines();
        for (final AssemblyLine line : lines) {
            System.out.print(line.getTotalProductionTime());
            if (line != lines.get(lines.size() - 1)) {
                System.out.print(",");
            }
        }
        System.out.println();
    }
}
