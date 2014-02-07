package factory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * This Class reads the Input from a file, in order to construct a factory with its given lines and so on
 * 
 * @author markus
 * @version 1.0
 */
public class FactoryReader {
    
    private Factory factory = null;
    
    private final File file;
    private ArrayList<String> lines = null;
    private ArrayList<Product> products = null;
    
    /**
     * Instantiates a new factory reader.
     * 
     * @param filename
     *            the filename
     * @throws FileNotFoundException
     *             the file not found exception
     */
    public FactoryReader(String filename) throws FileNotFoundException {
        this.file = new File(filename);
        if (!this.file.exists()) {
            throw new FileNotFoundException(String.format(
                    "File '%s' not found", filename));
        }
    }
    
    /**
     * Creates the factory.
     * 
     * @return the factory
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     * @throws InputFormatException
     *             the input format exception
     */
    public Factory createFactory() throws IOException, InputFormatException {
        final BufferedReader reader = new BufferedReader(new FileReader(
                this.file));
        
        String line = reader.readLine();
        
        while (line != null) {
            line = line.trim();
            
            if (line.isEmpty()) {
                line = reader.readLine();
                continue;
            }
            
            final String[] parts = line.split(":");
            
            if (parts.length != 2) {
                reader.close();
                throw new InputFormatException("Invalid Declaration: " + line);
            }
            
            switch (parts[0]) {
                case "factory":
                    this.parseFactoryDeclaration(parts[1]);
                    break;
                case "products":
                    this.parseProductsDeclaration(parts[1]);
                    break;
                case "lines":
                    this.parseLinesDeclaration(parts[1]);
                    break;
                case "line":
                    this.parseLineDefinition(parts[1]);
                    break;
                case "station":
                    this.parseStationDefinition(parts[1]);
                    break;
                case "transition":
                    this.parseTransitionTimes(parts[1]);
                    break;
                default:
                    reader.close();
                    throw new InputFormatException("Unknown Declaration Type: "
                            + parts[0]);
            }
            
            line = reader.readLine();
        }
        
        reader.close();
        
        return this.factory;
    }
    
    /**
     * Header is read.
     * 
     * @return true, if successful
     */
    private boolean headerIsRead() {
        return (this.factory != null) && (this.products != null)
                && (this.lines != null);
    }
    
    /**
     * Parses the factory declaration.
     * 
     * @param declaration
     *            the declaration
     * @throws InputFormatException
     *             the input format exception
     */
    private void parseFactoryDeclaration(String declaration)
            throws InputFormatException {
        if (declaration.isEmpty()) {
            throw new InputFormatException("Factory Declaration is Empty");
        }
        this.factory = new Factory();
    }
    
    /**
     * Parses the line definition.
     * 
     * @param definition
     *            the definition
     * @throws InputFormatException
     *             the input format exception
     */
    private void parseLineDefinition(String definition)
            throws InputFormatException {
        if (!this.headerIsRead()) {
            throw new InputFormatException("Incomplete Header");
        }
        
        final String[] parts = definition.split(",");
        
        if (parts.length != 2) {
            throw new InputFormatException("Invalid Line Definition: "
                    + definition);
        }
        
        final String line = parts[0];
        
        if (!this.lines.contains(line)) {
            throw new InputFormatException("Undeclared Line in Definition: "
                    + definition);
        }
        
        final String product = parts[1];
        
        if (!this.products.contains(new Product(product))) {
            throw new InputFormatException("Undeclared Product in Definition: "
                    + definition);
        }
        
        final AssemblyLine aline = new AssemblyLine(line, product);
        this.factory.addLine(aline);
    }
    
    /**
     * Parses the lines declaration.
     * 
     * @param declaration
     *            the declaration
     * @throws InputFormatException
     *             the input format exception
     */
    private void parseLinesDeclaration(String declaration)
            throws InputFormatException {
        if (declaration.isEmpty()) {
            throw new InputFormatException("Lines Declaration is Empty");
        }
        this.lines = new ArrayList<String>();
        final String[] parts = declaration.split(",");
        for (final String part : parts) {
            this.lines.add(part);
        }
    }
    
    /**
     * Parses the products declaration.
     * 
     * @param declaration
     *            the declaration
     * @throws InputFormatException
     *             the input format exception
     */
    private void parseProductsDeclaration(String declaration)
            throws InputFormatException {
        if (declaration.isEmpty()) {
            throw new InputFormatException("Products Declaration is Empty");
        }
        this.products = new ArrayList<Product>();
        final String[] parts = declaration.split(",");
        for (final String part : parts) {
            final Product product = new Product(part);
            this.products.add(product);
            this.factory.addProduct(product);
        }
    }
    
    /**
     * Parses the station definition.
     * 
     * @param definition
     *            the definition
     * @throws InputFormatException
     *             the input format exception
     */
    private void parseStationDefinition(String definition)
            throws InputFormatException {
        if (!this.headerIsRead()) {
            throw new InputFormatException("Incomplete Header");
        }
        
        final String[] parts = definition.split(",");
        
        if (parts.length != 3) {
            throw new InputFormatException("Invalid Station Definition: "
                    + definition);
        }
        
        final String line = parts[0];
        
        if (!this.lines.contains(line)) {
            throw new InputFormatException("Undeclared Line in Definition: "
                    + definition);
        }
        
        int stationNumber;
        int productionTime;
        
        try {
            stationNumber = Integer.parseInt(parts[1]);
            productionTime = Integer.parseInt(parts[2]);
        } catch (final NumberFormatException ne) {
            throw new InputFormatException(
                    "Expected Delivery Times in Station Definition: "
                            + definition);
        }
        
        final AssemblyLine aline = this.factory.getLine(line);
        final AssemblyStation astation = new AssemblyStation(aline,
                stationNumber, productionTime);
        aline.addStation(astation);
    }
    
    /**
     * Parses the transition times.
     * 
     * @param definition
     *            the definition
     * @throws InputFormatException
     *             the input format exception
     */
    private void parseTransitionTimes(String definition)
            throws InputFormatException {
        if (!this.headerIsRead()) {
            throw new InputFormatException("Incomplete Header");
        }
        
        final String[] parts = definition.split(",");
        
        if (parts.length != 5) {
            throw new InputFormatException("Invalid Transition Definition: "
                    + definition);
        }
        
        final String line1 = parts[0];
        final String line2 = parts[2];
        
        if (!this.lines.contains(line1) || !this.lines.contains(line2)) {
            throw new InputFormatException("Undeclared Line in Definition: "
                    + definition);
        }
        
        int station1;
        int station2;
        int transitionTime;
        try {
            station1 = Integer.parseInt(parts[1]);
            station2 = Integer.parseInt(parts[3]);
            transitionTime = Integer.parseInt(parts[4]);
        } catch (final NumberFormatException ne) {
            throw new InputFormatException(
                    "Expected Delivery Times in Station Definition: "
                            + definition);
        }
        
        final AssemblyLine aline1 = this.factory.getLine(line1);
        final AssemblyLine aline2 = this.factory.getLine(line2);
        final AssemblyStation astation1 = aline1.getStation(station1);
        final AssemblyStation astation2 = aline2.getStation(station2);
        
        astation1.addTransition(astation2, transitionTime);
    }
}
