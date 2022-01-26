package pl.piasta.acmanagement.domain.customers;

import pl.piasta.acmanagement.domain.customers.model.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomersRepository {

    Long add(Customer customer);
    boolean update(Long id, Customer customer);
    boolean existsByDocumentId(String documentId);
    Optional<Customer> get(Long id);
    List<Customer> getAll();
}
