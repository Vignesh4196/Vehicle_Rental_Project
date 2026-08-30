package user;

import java.util.HashMap;

public class UserRepository {

    private HashMap<String, Customer> customers;

    public UserRepository() {
        customers = new HashMap<>();
    }

    public void addCustomer(Customer customer) {
        customers.put(customer.getCustomerId(), customer);
    }

    public Customer findCustomerById(String customerId) {
        return customers.get(customerId);
    }

    public boolean customerExists(String customerId) {
        return customers.containsKey(customerId);
    }

    public void removeCustomer(String customerId) {
        customers.remove(customerId);
    }
}