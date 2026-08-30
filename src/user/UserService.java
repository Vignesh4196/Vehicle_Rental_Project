package user;

import user.exceptions.UserAlreadyExistsException;
import user.exceptions.UserNotFoundException;
import user.exceptions.InvalidCredentialsException;

public class UserService {

    private UserRepository userRepository;

    public UserService() {
        userRepository = new UserRepository();
    }

    public void register(Customer customer) {

        if (userRepository.customerExists(customer.getCustomerId())) {
            throw new UserAlreadyExistsException(
                "Customer already exists: " + customer.getCustomerId()
            );
        }

        userRepository.addCustomer(customer);

        System.out.println("Customer registered successfully.");
    }

    public Customer login(String customerId, String password) {

        Customer customer = userRepository.findCustomerById(customerId);

        if (customer == null) {
            throw new UserNotFoundException(
                "Customer not found: " + customerId
            );
        }

        if (!customer.getPassword().equals(password)) {
            throw new InvalidCredentialsException(
                "Invalid password."
            );
        }

        System.out.println("Login successful.");

        return customer;
    }

    public Customer findCustomer(String customerId) {

        Customer customer =
            userRepository.findCustomerById(customerId);

        if (customer == null) {
            throw new UserNotFoundException(
                "Customer not found: " + customerId
            );
        }

        return customer;
    }

    public void updateCustomer(Customer customer) {

        if (!userRepository.customerExists(customer.getCustomerId())) {
            throw new UserNotFoundException(
                "Customer not found: " + customer.getCustomerId()
            );
        }

        userRepository.addCustomer(customer);

        System.out.println("Customer updated successfully.");
    }

    public void changePassword(
            String customerId, String newPassword) {

        Customer customer =
            userRepository.findCustomerById(customerId);

        if (customer == null) {
            throw new UserNotFoundException(
                "Customer not found: " + customerId
            );
        }

        customer.setPassword(newPassword);

        System.out.println("Password changed successfully.");
    }

    public void logout() {

        System.out.println("Logout successful.");
    }
}