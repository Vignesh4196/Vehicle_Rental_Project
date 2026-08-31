package billing.exceptions;

/**
 * Exception thrown when invoice generation fails.
 */
public class InvoiceGenerationException extends Exception {
    
    public InvoiceGenerationException(String message) {
        super(message);
    }
    
    public InvoiceGenerationException(String message, Throwable cause) {
        super(message, cause);
    }
}
