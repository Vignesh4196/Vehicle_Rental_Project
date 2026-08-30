package test;

import java.time.LocalDate;

import rental.Rental;
import rental.RentalService;
import rental.Reservation;

public class RentalTest {

    public static void main(String[] args) {

        RentalService service = new RentalService();

        System.out.println("===== RENTAL TEST =====");

        // Test rental duration
        LocalDate startDate = LocalDate.of(2026, 8, 29);
        LocalDate endDate = LocalDate.of(2026, 9, 1);

        long duration = service.calculateRentalDuration(
                startDate,
                endDate
        );

        System.out.println("Rental duration: " + duration + " days");


        // Test renting a vehicle
        Rental rental = service.rentVehicle(
                "CUS001",
                "CAR101",
                startDate,
                endDate
        );

        System.out.println("Rental created!");
        System.out.println("Rental ID: " + rental.getRentalId());
        System.out.println("Customer ID: " + rental.getCustomerId());
        System.out.println("Vehicle ID: " + rental.getVehicleId());
        System.out.println("Status: " + rental.getStatus());


        // Test current rental
        Rental currentRental =
                service.getCurrentRental("CUS001");

        if (currentRental != null) {
            System.out.println("Current rental found: "
                    + currentRental.getRentalId());
        }


        // Test returning vehicle
        service.returnVehicle(rental.getRentalId());

        System.out.println("Vehicle returned!");
        System.out.println("Status after return: "
                + rental.getStatus());


        // Test rental history
        System.out.println("Rental history size: "
                + service.getRentalHistory().size());


        // Test reservation
        Reservation reservation =
                service.reserveVehicle(
                        "CUS002",
                        "CAR101"
                );

        System.out.println("Reservation created!");
        System.out.println("Reservation ID: "
                + reservation.getReservationId());
        System.out.println("Reservation status: "
                + reservation.getStatus());


        // Test cancellation
        service.cancelReservation(
                reservation.getReservationId()
        );

        System.out.println("Reservation cancelled!");
        System.out.println("Reservation status after cancellation: "
                + reservation.getStatus());


        System.out.println("===== TEST COMPLETE =====");
    }
}