package billing;

import user.Customer;
import vehicle.Vehicle;
import billing.exceptions.*;

/**
 * Main billing and pricing service for vehicle rentals.
 * Handles:
 * - Rental price calculation
 * - Discount application
 * - Late fee calculation
 * - Damage fee calculation
 * - Invoice generation
 * - Final amount calculation
 */
public class BillingPricing {
    
    private static final double LATE_FEE_PER_DAY = 500.0;
    private static final double REGULAR_DISCOUNT = 0.0;
    private static final double PREMIUM_DISCOUNT = 0.10;
    
    /**
     * Calculate rental price based on pricing strategy.
     * 
     * @param vehicle The vehicle being rented
     * @param days Number of rental days
     * @param pricingStrategy The pricing strategy to apply
     * @return The calculated rental price
     * @throws InvalidRentalAmountException If rental days or vehicle price is invalid
     */
    public double calculateRentalPrice(Vehicle vehicle, int days, 
                                       PricingStrategy pricingStrategy) 
            throws InvalidRentalAmountException {
        
        if (days <= 0) {
            throw new InvalidRentalAmountException("Rental days must be greater than 0");
        }
        
        if (vehicle.getPricePerDay() < 0) {
            throw new InvalidRentalAmountException("Vehicle price per day cannot be negative");
        }
        
        return pricingStrategy.calculatePrice(vehicle, days);
    }
    
    /**
     * Calculate discount amount based on customer type.
     * 
     * @param basePrice The base rental price
     * @param customerType "PREMIUM" or "REGULAR"
     * @return The discount amount
     * @throws InvalidDiscountException If customer type is invalid
     */
    public double calculateDiscount(double basePrice, String customerType) 
            throws InvalidDiscountException {
        
        if (basePrice < 0) {
            throw new InvalidDiscountException("Base price cannot be negative");
        }
        
        double discountRate = 0.0;
        
        if ("PREMIUM".equalsIgnoreCase(customerType)) {
            discountRate = PREMIUM_DISCOUNT;
        } else if ("REGULAR".equalsIgnoreCase(customerType)) {
            discountRate = REGULAR_DISCOUNT;
        } else {
            throw new InvalidDiscountException("Invalid customer type: " + customerType);
        }
        
        return basePrice * discountRate;
    }
    
    /**
     * Calculate late fee based on late days.
     * 
     * @param lateDays Number of days the vehicle was returned late
     * @return The late fee amount
     * @throws InvalidRentalAmountException If late days is negative
     */
    public double calculateLateFee(int lateDays) 
            throws InvalidRentalAmountException {
        
        if (lateDays < 0) {
            throw new InvalidRentalAmountException("Late days cannot be negative");
        }
        
        return lateDays * LATE_FEE_PER_DAY;
    }
    
    /**
     * Calculate damage fee.
     * 
     * @param damageCost The cost of damage
     * @return The damage fee
     * @throws InvalidRentalAmountException If damage cost is negative
     */
    public double calculateDamageFee(double damageCost) 
            throws InvalidRentalAmountException {
        
        if (damageCost < 0) {
            throw new InvalidRentalAmountException("Damage cost cannot be negative");
        }
        
        return damageCost;
    }
    
    /**
     * Calculate the final rental amount.
     * 
     * @param basePrice The base rental price
     * @param discount The discount amount
     * @param lateFee The late fee
     * @param damageFee The damage fee
     * @return The final amount to be paid
     * @throws InvalidPaymentException If final amount calculation fails
     */
    public double calculateFinalAmount(double basePrice, double discount, 
                                       double lateFee, double damageFee) 
            throws InvalidPaymentException {
        
        if (basePrice < 0 || discount < 0 || lateFee < 0 || damageFee < 0) {
            throw new InvalidPaymentException("All amounts must be non-negative");
        }
        
        double finalAmount = basePrice - discount + lateFee + damageFee;
        
        if (finalAmount < 0) {
            throw new InvalidPaymentException("Final amount cannot be negative");
        }
        
        return finalAmount;
    }
    
    /**
     * Generate a complete invoice for a rental.
     * 
     * @param invoiceId Unique invoice identifier
     * @param customer The customer
     * @param vehicle The rented vehicle
     * @param rentalDays Number of days rented
     * @param basePrice The base rental price
     * @param discount The discount applied
     * @param lateFee The late fee
     * @param damageFee The damage fee
     * @param totalAmount The total amount to be paid
     * @return An Invoice object
     * @throws InvoiceGenerationException If invoice generation fails
     */
    public Invoice generateInvoice(String invoiceId, Customer customer, 
                                   Vehicle vehicle, int rentalDays,
                                   double basePrice, double discount,
                                   double lateFee, double damageFee,
                                   double totalAmount) 
            throws InvoiceGenerationException {
        
        try {
            if (invoiceId == null || invoiceId.isEmpty()) {
                throw new InvoiceGenerationException("Invoice ID cannot be null or empty");
            }
            
            if (customer == null) {
                throw new InvoiceGenerationException("Customer cannot be null");
            }
            
            if (vehicle == null) {
                throw new InvoiceGenerationException("Vehicle cannot be null");
            }
            
            if (rentalDays <= 0) {
                throw new InvoiceGenerationException("Rental days must be greater than 0");
            }
            
            return new Invoice(invoiceId, customer, vehicle, rentalDays,
                             basePrice, discount, lateFee, damageFee, totalAmount);
        } catch (Exception e) {
            throw new InvoiceGenerationException("Failed to generate invoice: " + e.getMessage(), e);
        }
    }
    
    /**
     * Print invoice in formatted display.
     * 
     * @param invoice The invoice to print
     */
    public void printInvoice(Invoice invoice) {
        if (invoice != null) {
            System.out.println(invoice.generateInvoice());
        }
    }
}
