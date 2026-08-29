package vehicle;

public class Car extends Vehicle {
	private int numberOfSeats;

	public Car(String vehicleId, String brand, String model, double pricePerDay, int numberOfSeats) {

		super(vehicleId, brand, model, pricePerDay);

		this.numberOfSeats = numberOfSeats;

	}

	public int getNumberOfSeats() {
		return numberOfSeats;
	}

	@Override
	public double calculateRentalPrice(int days) {
		// TODO Auto-generated method stub
		return getPricePerDay() * days;
	}
}
