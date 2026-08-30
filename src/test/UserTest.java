package test;

import java.util.Scanner;
import user.Customer;
import user.UserService;

public class UserTest {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        UserService userService = new UserService();

        Customer customer = new Customer(
                "CUS101",
                "Akash",
                "akash@gmail.com",
                "9876543210",
                "12345",
                "REGULAR"
        );

        userService.register(customer);

        System.out.println("\n===== LOGIN =====");

        System.out.print("Enter Customer ID: ");
        String customerId = scanner.nextLine();

        System.out.print("Enter Password: ");
        String password = scanner.nextLine();

        Customer loggedInCustomer =
                userService.login(customerId, password);

        System.out.println(
                "Welcome, " + loggedInCustomer.getName()
        );

        System.out.println("\n===== PROFILE =====");

        System.out.println("Customer ID: "
                + loggedInCustomer.getCustomerId());

        System.out.println("Name: "
                + loggedInCustomer.getName());

        System.out.println("Email: "
                + loggedInCustomer.getEmail());

        System.out.println("Phone: "
                + loggedInCustomer.getPhone());

        System.out.println("Customer Type: "
                + loggedInCustomer.getCustomerType());

        scanner.close();
    }
}