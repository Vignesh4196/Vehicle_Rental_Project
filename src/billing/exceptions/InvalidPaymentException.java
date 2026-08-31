package billing.exceptions;

/**
 * Exception thrown when payment is invalid or unsuccessful.
 */
public class InvalidPaymentException extends Exception {
    
    public InvalidPaymentException(String message) {
        super(message);
    }
    
    public InvalidPaymentException(String message, Throwable cause) {
        super(message, cause);
    }
}
