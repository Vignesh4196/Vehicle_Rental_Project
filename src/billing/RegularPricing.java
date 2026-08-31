package billing;

import vehicle.Vehicle;

/**
 * Regular pricing strategy - No discount applied.
 * Base price = Vehicle price per day × Number of days
 */
public class RegularPricing implements PricingStrategy {
    
    @Override
    public double calculatePrice(Vehicle vehicle, int days) {
        return vehicle.getPricePerDay() * days;
    }
}
