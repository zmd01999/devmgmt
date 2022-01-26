package pl.piasta.acmanagement.infrastructure.mapper;

import org.springframework.stereotype.Component;
import pl.piasta.acmanagement.domain.customers.model.Customer;
import pl.piasta.acmanagement.infrastructure.model.CustomersEntity;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class CustomersEntityMapper {

    public Optional<Customer> mapToCustomerOptional(Optional<CustomersEntity> customersEntity) {
        return customersEntity.map(this::mapToCustomer);
    }

    public List<Customer> mapToCustomerList(List<CustomersEntity> customersEntityList) {
        return customersEntityList
                .stream()
                .map(this::mapToCustomer)
                .collect(Collectors.toList());
    }

    public Customer mapToCustomer(CustomersEntity customersEntity) {
        return new Customer(
                customersEntity.getId(),
                customersEntity.getFirstName(),
                customersEntity.getLastName(),
                customersEntity.getStreetName(),
                customersEntity.getHouseNumber(),
                customersEntity.getZipCode(),
                customersEntity.getCity(),
                customersEntity.getPhoneNumber(),
                customersEntity.getEmail(),
                customersEntity.getDocumentType(),
                customersEntity.getDocumentId());
    }
}
