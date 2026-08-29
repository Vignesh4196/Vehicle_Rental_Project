package rental.exceptions;

public class VehicleAlreadyRentedException extends RuntimeException {
	private static final long serialVersionUID = 1L;
    public VehicleAlreadyRentedException(String message) {
        super(message);
    }
}