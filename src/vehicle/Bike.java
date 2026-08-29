package vehicle;

public class Bike extends Vehicle {
	public Bike(String vehicleId, String brand, String model, double pricePerDay) {
		super(vehicleId, brand, model, pricePerDay);
	}

	@Override
	public double calculateRentalPrice(int days) {
		return getPricePerDay() * days;
	}
}
