package rental;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.UUID;

import rental.exceptions.InvalidRentalPeriodException;
import rental.exceptions.RentalNotFoundException;
import rental.exceptions.ReservationNotFoundException;

public class RentalService {

    private Map<String, Rental> rentals;
    private List<Rental> rentalHistory;
    private Map<String, Reservation> reservations;
    private Map<String, Queue<String>> reservationQueues;

    public RentalService() {
        rentals = new HashMap<>();
        rentalHistory = new ArrayList<>();
        reservations = new HashMap<>();
        reservationQueues = new HashMap<>();
    }

    // Calculate the number of days between rental dates
    public long calculateRentalDuration(
            LocalDate startDate,
            LocalDate endDate) {

        if (startDate == null || endDate == null) {
            throw new InvalidRentalPeriodException(
                    "Start date and end date cannot be null");
        }

        if (endDate.isBefore(startDate)) {
            throw new InvalidRentalPeriodException(
                    "End date cannot be before start date");
        }

        return ChronoUnit.DAYS.between(startDate, endDate);
    }

    // Rent a vehicle
    public Rental rentVehicle(
            String customerId,
            String vehicleId,
            LocalDate startDate,
            LocalDate endDate) {

        if (customerId == null || customerId.isBlank()) {
            throw new IllegalArgumentException(
                    "Customer ID cannot be empty");
        }

        if (vehicleId == null || vehicleId.isBlank()) {
            throw new IllegalArgumentException(
                    "Vehicle ID cannot be empty");
        }

        // Validate rental dates
        calculateRentalDuration(startDate, endDate);

        // Generate rental ID
        String rentalId = "R" + UUID.randomUUID()
                .toString()
                .substring(0, 8);

        // Create rental
        Rental rental = new Rental(
                rentalId,
                customerId,
                vehicleId,
                startDate,
                endDate,
                RentalStatus.ACTIVE
        );

        // Store rental
        rentals.put(rentalId, rental);

        return rental;
    }

    // Return a vehicle
    public void returnVehicle(String rentalId) {

        Rental rental = rentals.get(rentalId);

        if (rental == null) {
            throw new RentalNotFoundException(
                    "Rental not found: " + rentalId);
        }

        if (rental.getStatus() != RentalStatus.ACTIVE) {
            throw new RentalNotFoundException(
                    "Active rental not found: " + rentalId);
        }

        // Change rental status to completed
        rental.setStatus(RentalStatus.COMPLETED);

        // Add to rental history
        rentalHistory.add(rental);
    }

    // Get the customer's current active rental
    public Rental getCurrentRental(String customerId) {

        for (Rental rental : rentals.values()) {

            if (rental.getCustomerId().equals(customerId)
                    && rental.getStatus() == RentalStatus.ACTIVE) {

                return rental;
            }
        }

        return null;
    }

    // Get rental history
    public List<Rental> getRentalHistory() {
        return new ArrayList<>(rentalHistory);
    }

    // Reserve a vehicle
    public Reservation reserveVehicle(
            String customerId,
            String vehicleId) {

        if (customerId == null || customerId.isBlank()) {
            throw new IllegalArgumentException(
                    "Customer ID cannot be empty");
        }

        if (vehicleId == null || vehicleId.isBlank()) {
            throw new IllegalArgumentException(
                    "Vehicle ID cannot be empty");
        }

        // Generate reservation ID
        String reservationId = "RES" + UUID.randomUUID()
                .toString()
                .substring(0, 8);

        // Create reservation
        Reservation reservation = new Reservation(
                reservationId,
                customerId,
                vehicleId,
                LocalDate.now()
        );

        // Store reservation
        reservations.put(reservationId, reservation);

        // Get existing queue or create a new queue
        Queue<String> queue = reservationQueues
                .computeIfAbsent(
                        vehicleId,
                        key -> new LinkedList<>()
                );

        // Add customer to the reservation queue
        queue.add(customerId);

        return reservation;
    }

    // Cancel a reservation
    public void cancelReservation(String reservationId) {

        Reservation reservation =
                reservations.get(reservationId);

        if (reservation == null) {
            throw new ReservationNotFoundException(
                    "Reservation not found: " + reservationId);
        }

        // Change status to cancelled
        reservation.setStatus(RentalStatus.CANCELLED);

        // Remove customer from the vehicle queue
        Queue<String> queue =
                reservationQueues.get(
                        reservation.getVehicleId());

        if (queue != null) {
            queue.remove(reservation.getCustomerId());
        }
    }
}