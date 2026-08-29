package rental.exceptions;

public class InvalidRentalPeriodException extends RuntimeException {
	private static final long serialVersionUID = 1L;
    public InvalidRentalPeriodException(String message) {
        super(message);
    }
}