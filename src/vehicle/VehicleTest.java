package vehicle;

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

		service.addVehicle(car);
		service.addVehicle(bike);

		System.out.println("\n--- All Vehicles ---");

		service.displayAllVehicles();
	}
}
