package test;

import billing.*;
import billing.exceptions.*;
import user.Customer;
import vehicle.Car;

/**
 * Comprehensive test class for Billing & Pricing Module (Member 4).
 * Tests all features: pricing strategies, discounts, fees, and invoice generation.
 */
public class BillingTest {
    
    public static void main(String[] args) {
        System.out.println("========================================");
        System.out.println("   BILLING & PRICING MODULE TEST");
        System.out.println("========================================\n");
        
        try {
            // Initialize test data
            BillingPricing billing = new BillingPricing();
            
            // Create test customer
            Customer customer = new Customer("C001", "Vicky", "vicky@email.com", 
                                            "9876543210", "pass123", "PREMIUM");
            
            // Create test vehicle (Honda City)
            Car vehicle = new Car("V001", "Honda", "City", 2500, 5);
            
            System.out.println("TEST DATA:");
            System.out.println("----------");
            System.out.println("Customer: " + customer.getName() + " (Type: " + customer.getCustomerType() + ")");
            System.out.println("Vehicle: " + vehicle.getBrand() + " " + vehicle.getModel());
            System.out.println("Price/Day: ₹" + vehicle.getPricePerDay());
            System.out.println("Rental Days: 3\n");
            
            // Test 1: Regular Pricing Strategy
            System.out.println("TEST 1: Regular Pricing Strategy");
            System.out.println("================================");
            PricingStrategy regularPricing = new RegularPricing();
            double regularPrice = billing.calculateRentalPrice(vehicle, 3, regularPricing);
            System.out.println("Regular Price (No Discount): Rs. " + regularPrice);
            System.out.println("Expected: Rs. 7500.0\n");
            
            // Test 2: Premium Pricing Strategy
            System.out.println("TEST 2: Premium Pricing Strategy");
            System.out.println("================================");
            PricingStrategy premiumPricing = new PremiumPricing();
            double premiumPrice = billing.calculateRentalPrice(vehicle, 3, premiumPricing);
            System.out.println("Premium Price (10% Discount): Rs. " + premiumPrice);
            System.out.println("Expected: Rs. 6750.0\n");
            
            // Test 3: Calculate Discount
            System.out.println("TEST 3: Discount Calculation");
            System.out.println("============================");
            double premiumDiscount = billing.calculateDiscount(regularPrice, "PREMIUM");
            double regularDiscount = billing.calculateDiscount(regularPrice, "REGULAR");
            System.out.println("Premium Discount (10%): Rs. " + premiumDiscount);
            System.out.println("Regular Discount (0%): Rs. " + regularDiscount);
            System.out.println("Expected Premium: Rs. 750.0, Regular: Rs. 0.0\n");
            
            // Test 4: Late Fee Calculation
            System.out.println("TEST 4: Late Fee Calculation");
            System.out.println("============================");
            int lateDays = 2;
            double lateFee = billing.calculateLateFee(lateDays);
            System.out.println("Late Days: " + lateDays);
            System.out.println("Late Fee (Rs. 500/day): Rs. " + lateFee);
            System.out.println("Expected: Rs. 1000.0\n");
            
            // Test 5: Damage Fee Calculation
            System.out.println("TEST 5: Damage Fee Calculation");
            System.out.println("==============================");
            double damageAmount = 2000;
            double damageFee = billing.calculateDamageFee(damageAmount);
            System.out.println("Damage Cost: Rs. " + damageAmount);
            System.out.println("Damage Fee: Rs. " + damageFee + "\n");
            
            // Test 6: Final Amount Calculation (Premium with late fee & damage)
            System.out.println("TEST 6: Final Amount Calculation");
            System.out.println("================================");
            double finalAmount = billing.calculateFinalAmount(
                regularPrice,      // Base price: 7500
                premiumDiscount,    // Discount: 750
                lateFee,           // Late fee: 1000
                damageFee          // Damage fee: 2000
            );
            System.out.println("Base Price:   Rs. " + regularPrice);
            System.out.println("- Discount:   -Rs. " + premiumDiscount);
            System.out.println("+ Late Fee:   +Rs. " + lateFee);
            System.out.println("+ Damage Fee: +Rs. " + damageFee);
            System.out.println("---------------------");
            System.out.println("Final Amount: Rs. " + finalAmount);
            System.out.println("Expected: Rs. 9750.0\n");
            
            // Test 7: Invoice Generation & Display
            System.out.println("TEST 7: Invoice Generation");
            System.out.println("==========================\n");
            Invoice invoice = billing.generateInvoice(
                "INV001",
                customer,
                vehicle,
                3,
                regularPrice,
                premiumDiscount,
                lateFee,
                damageFee,
                finalAmount
            );
            billing.printInvoice(invoice);
            
            // Test 8: Error Handling Tests
            System.out.println("\nTEST 8: Error Handling");
            System.out.println("======================");
            testErrorHandling(billing);
            
            System.out.println("\n========================================");
            System.out.println("     ALL TESTS COMPLETED SUCCESSFULLY");
            System.out.println("========================================");
            
        } catch (Exception e) {
            System.err.println("Test failed with exception: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * Test exception handling for various error scenarios.
     */
    private static void testErrorHandling(BillingPricing billing) {
        // Test 1: Invalid rental days
        try {
            Car vehicle = new Car("V002", "Toyota", "Fortuner", 3000, 8);
            PricingStrategy pricing = new RegularPricing();
            billing.calculateRentalPrice(vehicle, -1, pricing);
            System.out.println("[FAIL] Should have thrown InvalidRentalAmountException for negative days");
        } catch (InvalidRentalAmountException e) {
            System.out.println("[PASS] Correctly caught InvalidRentalAmountException: " + e.getMessage());
        }
        
        // Test 2: Invalid discount type
        try {
            billing.calculateDiscount(5000, "INVALID_TYPE");
            System.out.println("[FAIL] Should have thrown InvalidDiscountException for invalid type");
        } catch (InvalidDiscountException e) {
            System.out.println("[PASS] Correctly caught InvalidDiscountException: " + e.getMessage());
        }
        
        // Test 3: Negative late days
        try {
            billing.calculateLateFee(-5);
            System.out.println("[FAIL] Should have thrown InvalidRentalAmountException for negative late days");
        } catch (InvalidRentalAmountException e) {
            System.out.println("[PASS] Correctly caught InvalidRentalAmountException: " + e.getMessage());
        }
        
        // Test 4: Negative damage cost
        try {
            billing.calculateDamageFee(-1000);
            System.out.println("[FAIL] Should have thrown InvalidRentalAmountException for negative damage");
        } catch (InvalidRentalAmountException e) {
            System.out.println("[PASS] Correctly caught InvalidRentalAmountException: " + e.getMessage());
        }
        
        // Test 5: Invalid payment amounts
        try {
            billing.calculateFinalAmount(-100, 50, 100, 0);
            System.out.println("[FAIL] Should have thrown InvalidPaymentException for negative base price");
        } catch (InvalidPaymentException e) {
            System.out.println("[PASS] Correctly caught InvalidPaymentException: " + e.getMessage());
        }
        
        // Test 6: Null invoice data
        try {
            billing.generateInvoice(null, null, null, 0, 0, 0, 0, 0, 0);
            System.out.println("[FAIL] Should have thrown InvoiceGenerationException");
        } catch (InvoiceGenerationException e) {
            System.out.println("[PASS] Correctly caught InvoiceGenerationException: " + e.getMessage());
        }
    }
}
