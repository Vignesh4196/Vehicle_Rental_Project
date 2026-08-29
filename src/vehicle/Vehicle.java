package vehicle;

public abstract class Vehicle {
	private String vehicleId;
	private String brand;
	private String model;
	private double pricePerDay;
	private VehicleStatus status;

	public Vehicle(String vehicleId, String brand, String model, double pricePerDay) {

		this.vehicleId = vehicleId;
		this.brand = brand;
		this.model = model;
		this.pricePerDay = pricePerDay;
		this.status = VehicleStatus.AVAILABLE;
	}

	public abstract double calculateRentalPrice(int days);

	public String getVehicleId() {
		return vehicleId;
	}

	public String getBrand() {
		return brand;
	}

	public String getModel() {
		return model;
	}

	public double getPricePerDay() {
		return pricePerDay;
	}

	public void setPricePerDay(double pricePerDay) {
		this.pricePerDay = pricePerDay;
	}

	public VehicleStatus getStatus() {
		return status;
	}

	public void setStatus(VehicleStatus status) {
		this.status = status;
	}
}
