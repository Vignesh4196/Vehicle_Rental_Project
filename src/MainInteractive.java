import billing.*;
import billing.exceptions.*;
import user.Customer;
import vehicle.Car;
import vehicle.Bike;
import java.util.Scanner;

/**
 * Interactive Vehicle Rental System - Takes user input for rentals and billing
 */
public class MainInteractive {
    
    private static Scanner scanner = new Scanner(System.in);
    private static BillingPricing billingService = new BillingPricing();
    
    public static void main(String[] args) {
        System.out.println("\n========== VEHICLE RENTAL SYSTEM - INTERACTIVE MODE ==========\n");
        
        try {
            boolean continueApp = true;
            
            while (continueApp) {
                displayMenu();
                int choice = getIntInput("Enter your choice (1-3): ");
                
                switch (choice) {
                    case 1:
                        processRental();
                        break;
                    case 2:
                        System.out.println("\nThank you for using Vehicle Rental System!");
                        continueApp = false;
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
            
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getMessage());
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }
    
    /**
     * Display main menu
     */
    private static void displayMenu() {
        System.out.println("\n========== MAIN MENU ==========");
        System.out.println("1. Process New Rental");
        System.out.println("2. Exit");
        System.out.println("==============================");
    }
    
    /**
     * Main rental processing workflow
     */
    private static void processRental() 
            throws InvalidRentalAmountException, InvalidDiscountException, 
                   InvalidPaymentException, InvoiceGenerationException {
        
        System.out.println("\n========== NEW RENTAL PROCESS ==========\n");
        
        // Get Customer Information
        System.out.println("--- CUSTOMER INFORMATION ---");
        String customerId = getStringInput("Enter Customer ID (e.g., C001): ");
        String customerName = getStringInput("Enter Customer Name: ");
        String email = getStringInput("Enter Email: ");
        String phone = getStringInput("Enter Phone Number: ");
        String password = getStringInput("Enter Password: ");
        String customerType = getCustomerType();
        
        Customer customer = new Customer(customerId, customerName, email, phone, password, customerType);
        
        // Get Vehicle Information
        System.out.println("\n--- VEHICLE SELECTION ---");
        System.out.println("1. Car");
        System.out.println("2. Bike");
        int vehicleType = getIntInput("Select Vehicle Type (1 or 2): ");
        
        vehicle.Vehicle vehicle = null;
        if (vehicleType == 1) {
            vehicle = createCar();
        } else if (vehicleType == 2) {
            vehicle = createBike();
        } else {
            System.out.println("Invalid vehicle type!");
            return;
        }
        
        // Get Rental Details
        System.out.println("\n--- RENTAL DETAILS ---");
        int rentalDays = getIntInput("Enter number of rental days: ");
        int lateDays = getIntInput("Enter number of late return days (0 if none): ");
        double damageCost = getDoubleInput("Enter damage cost in Rs. (0 if none): ");
        
        // Generate Invoice ID
        String invoiceId = "INV" + System.currentTimeMillis() % 100000;
        
        // Calculate Pricing
        System.out.println("\n--- CALCULATING CHARGES ---");
        
        PricingStrategy pricingStrategy = customerType.equalsIgnoreCase("PREMIUM") 
            ? new PremiumPricing() 
            : new RegularPricing();
        
        double basePrice = billingService.calculateRentalPrice(vehicle, rentalDays, pricingStrategy);
        double discount = billingService.calculateDiscount(basePrice, customerType);
        double lateFee = billingService.calculateLateFee(lateDays);
        double damageFee = billingService.calculateDamageFee(damageCost);
        double totalAmount = billingService.calculateFinalAmount(basePrice, discount, lateFee, damageFee);
        
        // Display Billing Breakdown
        System.out.println("\n--- BILLING BREAKDOWN ---");
        System.out.println("Base Price (" + rentalDays + " days x Rs. " + vehicle.getPricePerDay() + "): Rs. " + basePrice);
        System.out.println("Discount (" + customerType + "): -Rs. " + discount);
        System.out.println("Late Fee (" + lateDays + " days x Rs. 500): +Rs. " + lateFee);
        System.out.println("Damage Charges: +Rs. " + damageCost);
        System.out.println("---------------------------------------------");
        System.out.println("TOTAL AMOUNT: Rs. " + totalAmount);
        
        // Generate and Display Invoice
        System.out.println("\n--- GENERATING INVOICE ---\n");
        Invoice invoice = billingService.generateInvoice(
            invoiceId, 
            customer, 
            vehicle, 
            rentalDays, 
            basePrice, 
            discount, 
            lateFee, 
            damageFee, 
            totalAmount
        );
        billingService.printInvoice(invoice);
        
        // Ask for confirmation
        System.out.println("\nRental processed successfully!");
        System.out.println("Invoice ID: " + invoiceId);
    }
    
    /**
     * Create Car with user input
     */
    private static vehicle.Vehicle createCar() {
        System.out.println("\n--- CAR DETAILS ---");
        String vehicleId = getStringInput("Enter Vehicle ID: ");
        String brand = getStringInput("Enter Brand (e.g., Honda): ");
        String model = getStringInput("Enter Model (e.g., City): ");
        double pricePerDay = getDoubleInput("Enter Price per Day (Rs.): ");
        int seats = getIntInput("Enter Number of Seats: ");
        
        return new Car(vehicleId, brand, model, pricePerDay, seats);
    }
    
    /**
     * Create Bike with user input
     */
    private static vehicle.Vehicle createBike() {
        System.out.println("\n--- BIKE DETAILS ---");
        String vehicleId = getStringInput("Enter Vehicle ID: ");
        String brand = getStringInput("Enter Brand (e.g., Royal Enfield): ");
        String model = getStringInput("Enter Model (e.g., Classic 350): ");
        double pricePerDay = getDoubleInput("Enter Price per Day (Rs.): ");
        
        return new Bike(vehicleId, brand, model, pricePerDay);
    }
    
    /**
     * Get customer type from user
     */
    private static String getCustomerType() {
        System.out.println("1. PREMIUM (10% discount)");
        System.out.println("2. REGULAR (No discount)");
        int choice = getIntInput("Select Customer Type (1 or 2): ");
        
        return choice == 1 ? "PREMIUM" : "REGULAR";
    }
    
    /**
     * Get string input from user
     */
    private static String getStringInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }
    
    /**
     * Get integer input from user
     */
    private static int getIntInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                int value = Integer.parseInt(scanner.nextLine().trim());
                if (value >= 0) {
                    return value;
                } else {
                    System.out.println("Please enter a non-negative number.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
    }
    
    /**
     * Get double input from user
     */
    private static double getDoubleInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                double value = Double.parseDouble(scanner.nextLine().trim());
                if (value >= 0) {
                    return value;
                } else {
                    System.out.println("Please enter a non-negative number.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
    }
}
