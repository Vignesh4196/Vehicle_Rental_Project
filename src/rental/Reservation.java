package rental;

import java.time.LocalDate;

public class Reservation {

    private String reservationId;
    private String customerId;
    private String vehicleId;

    private LocalDate reservationDate;

    private RentalStatus status;

    public Reservation(String reservationId,
                       String customerId,
                       String vehicleId,
                       LocalDate reservationDate) {

        this.reservationId = reservationId;
        this.customerId = customerId;
        this.vehicleId = vehicleId;
        this.reservationDate = reservationDate;
        this.status = RentalStatus.RESERVED;
    }

    public String getReservationId() {
        return reservationId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public LocalDate getReservationDate() {
        return reservationDate;
    }

    public RentalStatus getStatus() {
        return status;
    }

    public void setStatus(RentalStatus status) {
        this.status = status;
    }
}