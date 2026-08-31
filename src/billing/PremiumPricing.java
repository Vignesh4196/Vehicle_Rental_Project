package billing;

import vehicle.Vehicle;

/**
 * Premium pricing strategy - 10% discount applied.
 * Price = (Vehicle price per day × Number of days) × 0.90
 */
public class PremiumPricing implements PricingStrategy {
    
    private static final double DISCOUNT_RATE = 0.90; // 10% discount
    
    @Override
    public double calculatePrice(Vehicle vehicle, int days) {
        double basePrice = vehicle.getPricePerDay() * days;
        return basePrice * DISCOUNT_RATE;
    }
}
