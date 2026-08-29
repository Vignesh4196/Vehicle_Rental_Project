package vehicle;

import java.util.ArrayList;
import java.util.List;

import vehicle.exceptions.VehicleAlreadyExistsException;
import vehicle.exceptions.VehicleNotAvailableException;
import vehicle.exceptions.VehicleNotFoundException;

public class VehicleService {
	private List<Vehicle> vehicles = new ArrayList<>();

//	public void addVehicle(Vehicle vehicle) {
//		vehicles.add(vehicle);
//	}
	public void addVehicle(Vehicle vehicle) throws VehicleAlreadyExistsException {

		for (Vehicle existingVehicle : vehicles) {

			if (existingVehicle.getVehicleId().equals(vehicle.getVehicleId())) {

				throw new VehicleAlreadyExistsException(
						"Vehicle with ID " + vehicle.getVehicleId() + " already exists.");
			}
		}

		vehicles.add(vehicle);

		System.out.println("Vehicle " + vehicle.getVehicleId() + " added successfully.");
	}

	public void displayAllVehicles() {

		for (Vehicle vehicle : vehicles) {

			System.out.println(vehicle.getVehicleId() + " - " + vehicle.getBrand() + " " + vehicle.getModel() + " - ₹"
					+ vehicle.getPricePerDay() + "/day - " + vehicle.getStatus());
		}
	}

	public Vehicle findVehicle(String vehicleId) throws VehicleNotFoundException {

		for (Vehicle vehicle : vehicles) {

			if (vehicle.getVehicleId().equals(vehicleId)) {
				return vehicle;
			}
		}

		throw new VehicleNotFoundException("Vehicle with ID " + vehicleId + " not found.");
	}

	public void rentVehicle(String vehicleId) throws VehicleNotFoundException, VehicleNotAvailableException {

		Vehicle vehicle = findVehicle(vehicleId);

		if (vehicle.getStatus() == VehicleStatus.RENTED) {
			throw new VehicleNotAvailableException("Vehicle with ID " + vehicleId + " is already rented.");
		}

		vehicle.setStatus(VehicleStatus.RENTED);

		System.out.println("Vehicle " + vehicleId + " rented successfully.");
	}

	public void returnVehicle(String vehicleId) throws VehicleNotFoundException {

		Vehicle vehicle = findVehicle(vehicleId);

		vehicle.setStatus(VehicleStatus.AVAILABLE);

		System.out.println("Vehicle " + vehicleId + " returned successfully.");
	}

	public void removeVehicle(String vehicleId) throws VehicleNotFoundException {

		Vehicle vehicle = findVehicle(vehicleId);

		vehicles.remove(vehicle);

		System.out.println("Vehicle " + vehicleId + " removed successfully.");
	}

}
