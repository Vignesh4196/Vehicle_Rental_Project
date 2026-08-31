package billing.exceptions;

/**
 * Exception thrown when discount is invalid.
 */
public class InvalidDiscountException extends Exception {
    
    public InvalidDiscountException(String message) {
        super(message);
    }
    
    public InvalidDiscountException(String message, Throwable cause) {
        super(message, cause);
    }
}
