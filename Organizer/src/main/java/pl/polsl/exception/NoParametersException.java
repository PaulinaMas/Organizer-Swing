package pl.polsl.exception;

/**
 * Class for exception when no parameters are in command line
 *
 * @author Paulina Maslowska
 * @version 2.0
 */
public class NoParametersException extends Exception {
/**
 * exception when no parameters are in command line
 */
    public NoParametersException() {
        super("No paramteters!");
    }
}
