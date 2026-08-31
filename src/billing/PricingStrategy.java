package billing;

import vehicle.Vehicle;

/**
 * Strategy interface for calculating rental prices.
 * Demonstrates polymorphism and strategy pattern.
 */
public interface PricingStrategy {
    
    /**
     * Calculate the price for renting a vehicle.
     * 
     * @param vehicle The vehicle to rent
     * @param days The number of days to rent
     * @return The calculated price
     */
    double calculatePrice(Vehicle vehicle, int days);
}
