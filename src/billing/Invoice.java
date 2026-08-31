package billing;

import user.Customer;
import vehicle.Vehicle;

/**
 * Represents a rental invoice with itemized charges.
 */
public class Invoice {
    
    private String invoiceId;
    private Customer customer;
    private Vehicle vehicle;
    private int rentalDays;
    private double basePrice;
    private double discount;
    private double lateFee;
    private double damageFee;
    private double totalAmount;
    
    public Invoice(String invoiceId, Customer customer, Vehicle vehicle, 
                   int rentalDays, double basePrice, double discount,
                   double lateFee, double damageFee, double totalAmount) {
        this.invoiceId = invoiceId;
        this.customer = customer;
        this.vehicle = vehicle;
        this.rentalDays = rentalDays;
        this.basePrice = basePrice;
        this.discount = discount;
        this.lateFee = lateFee;
        this.damageFee = damageFee;
        this.totalAmount = totalAmount;
    }
    
    /**
     * Generate formatted invoice display.
     */
    public String generateInvoice() {
        StringBuilder invoice = new StringBuilder();
        invoice.append("+==================================+\n");
        invoice.append("|       VEHICLE RENTAL INVOICE    |\n");
        invoice.append("+==================================+\n");
        invoice.append(String.format("| Invoice ID   : %-20s |\n", invoiceId));
        invoice.append(String.format("| Customer     : %-20s |\n", customer.getName()));
        invoice.append(String.format("| Vehicle      : %-20s |\n", 
                       vehicle.getBrand() + " " + vehicle.getModel()));
        invoice.append(String.format("| Rental Days  : %-20d |\n", rentalDays));
        invoice.append("+==================================+\n");
        invoice.append(String.format("| Base Price   : Rs. %-15.2f |\n", basePrice));
        invoice.append(String.format("| Discount     : Rs. %-15.2f |\n", discount));
        invoice.append(String.format("| Late Fee     : Rs. %-15.2f |\n", lateFee));
        invoice.append(String.format("| Damage Fee   : Rs. %-15.2f |\n", damageFee));
        invoice.append("+==================================+\n");
        invoice.append(String.format("| TOTAL        : Rs. %-15.2f |\n", totalAmount));
        invoice.append("+==================================+\n");
        
        return invoice.toString();
    }
    
    // Getters and Setters
    public String getInvoiceId() {
        return invoiceId;
    }
    
    public void setInvoiceId(String invoiceId) {
        this.invoiceId = invoiceId;
    }
    
    public Customer getCustomer() {
        return customer;
    }
    
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    
    public Vehicle getVehicle() {
        return vehicle;
    }
    
    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }
    
    public int getRentalDays() {
        return rentalDays;
    }
    
    public void setRentalDays(int rentalDays) {
        this.rentalDays = rentalDays;
    }
    
    public double getBasePrice() {
        return basePrice;
    }
    
    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
    }
    
    public double getDiscount() {
        return discount;
    }
    
    public void setDiscount(double discount) {
        this.discount = discount;
    }
    
    public double getLateFee() {
        return lateFee;
    }
    
    public void setLateFee(double lateFee) {
        this.lateFee = lateFee;
    }
    
    public double getDamageFee() {
        return damageFee;
    }
    
    public void setDamageFee(double damageFee) {
        this.damageFee = damageFee;
    }
    
    public double getTotalAmount() {
        return totalAmount;
    }
    
    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }
}
