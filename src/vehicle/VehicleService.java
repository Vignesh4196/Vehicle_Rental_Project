package vehicle;

import java.util.ArrayList;
import java.util.List;

public class VehicleService {
	private List<Vehicle> vehicles = new ArrayList<>();

	public void addVehicle(Vehicle vehicle) {
		vehicles.add(vehicle);
	}

	public void displayAllVehicles() {

		for (Vehicle vehicle : vehicles) {

			System.out.println(vehicle.getVehicleId() + " - " + vehicle.getBrand() + " " + vehicle.getModel() + " - ₹"
					+ vehicle.getPricePerDay() + "/day - " + vehicle.getStatus());
		}
	}
}
