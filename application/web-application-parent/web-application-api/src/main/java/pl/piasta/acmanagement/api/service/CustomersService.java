package pl.piasta.acmanagement.api.service;

import pl.piasta.acmanagement.domain.customers.model.Customer;

import java.util.List;

public interface CustomersService {

    Long addCustomer(Customer customer);
    void updateCustomer(Long id, Customer customer);
    Customer getCustomerById(Long id);
    List<Customer> getAllCustomers();
}
