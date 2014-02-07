package factory;

/**
 * This Exception is needed, if the input file has a wrong format, ergo is not parseable
 * 
 * @author markus
 * @version 1.0
 */
public class InputFormatException extends IllegalArgumentException {
    private static final long serialVersionUID = 672344176131482062L;
    
    /**
     * Default constructor to instantiates a new input format exception.
     */
    public InputFormatException() {
        super();
    }
    
    /**
     * Instantiates a new input format exception.
     * 
     * @param message
     *            the message
     */
    public InputFormatException(String message) {
        super(message);
    }
    
}
