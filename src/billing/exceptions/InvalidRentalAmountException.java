package billing.exceptions;

/**
 * Exception thrown when rental amount is invalid.
 */
public class InvalidRentalAmountException extends Exception {
    
    public InvalidRentalAmountException(String message) {
        super(message);
    }
    
    public InvalidRentalAmountException(String message, Throwable cause) {
        super(message, cause);
    }
}
