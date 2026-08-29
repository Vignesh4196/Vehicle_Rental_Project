package vehicle;

import vehicle.exceptions.VehicleAlreadyExistsException;
import vehicle.exceptions.VehicleNotAvailableException;
import vehicle.exceptions.VehicleNotFoundException;

public class VehicleTest {
	public static void main(String[] args) {

		Car car = new Car("CAR101", "Toyota", "Camry", 2500, 5);

		Bike bike = new Bike("BIKE101", "Honda", "Activa", 1000);

		System.out.println("Car ID: " + car.getVehicleId());
		System.out.println("Car: " + car.getBrand() + " " + car.getModel());
		System.out.println("Seats: " + car.getNumberOfSeats());
		System.out.println("Car rental for 3 days: ₹" + car.calculateRentalPrice(3));

		System.out.println();

		System.out.println("Bike ID: " + bike.getVehicleId());
		System.out.println("Bike: " + bike.getBrand() + " " + bike.getModel());
		System.out.println("Bike rental for 3 days: ₹" + bike.calculateRentalPrice(3));

		VehicleService service = new VehicleService();

		try {

			service.addVehicle(car);
			service.addVehicle(bike);

		} catch (VehicleAlreadyExistsException e) {

			System.out.println("Add Vehicle Error: " + e.getMessage());
		}
		try {

			service.addVehicle(car);

		} catch (VehicleAlreadyExistsException e) {

			System.out.println("Add Vehicle Error: " + e.getMessage());
		}

		System.out.println("\n--- All Vehicles ---");

		service.displayAllVehicles();
		try {

			Vehicle foundVehicle = service.findVehicle("CAR101");

			System.out.println("\nVehicle Found:");
			System.out.println(foundVehicle.getBrand() + " " + foundVehicle.getModel());

		} catch (VehicleNotFoundException e) {

			System.out.println("Error: " + e.getMessage());
		}
		try {

			service.rentVehicle("CAR101");

		} catch (VehicleNotFoundException | VehicleNotAvailableException e) {

			System.out.println("Rental Error: " + e.getMessage());
		}
		try {

			service.rentVehicle("CAR101");

		} catch (VehicleNotFoundException | VehicleNotAvailableException e) {

			System.out.println("Rental Error: " + e.getMessage());
		}
		try {

			service.returnVehicle("CAR101");

		} catch (VehicleNotFoundException e) {

			System.out.println("Return Error: " + e.getMessage());
		}
		System.out.println("\n--- Vehicles After Return ---");
		service.displayAllVehicles();
		try {

			service.removeVehicle("BIKE101");

		} catch (VehicleNotFoundException e) {

			System.out.println("Remove Error: " + e.getMessage());
		}
		System.out.println("\n--- Vehicles After Removal ---");
		service.displayAllVehicles();
		try {

			service.removeVehicle("CAR999");

		} catch (VehicleNotFoundException e) {

			System.out.println("Remove Error: " + e.getMessage());
		}
		try {

			service.findVehicle("CAR999");

		} catch (VehicleNotFoundException e) {

			System.out.println("Find Error: " + e.getMessage());
		}
	}
}
