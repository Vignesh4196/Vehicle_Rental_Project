package rental;

import java.time.LocalDate;

public class Rental {

    private String rentalId;
    private String customerId;
    private String vehicleId;

    private LocalDate startDate;
    private LocalDate endDate;

    private RentalStatus status;

    public Rental(String rentalId,
                  String customerId,
                  String vehicleId,
                  LocalDate startDate,
                  LocalDate endDate,
                  RentalStatus status) {

        this.rentalId = rentalId;
        this.customerId = customerId;
        this.vehicleId = vehicleId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
    }

    public String getRentalId() {
        return rentalId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public RentalStatus getStatus() {
        return status;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public void setStatus(RentalStatus status) {
        this.status = status;
    }
}