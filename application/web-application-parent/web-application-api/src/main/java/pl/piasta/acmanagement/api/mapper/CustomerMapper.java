package pl.piasta.acmanagement.api.mapper;

import org.mapstruct.Mapper;
import pl.piasta.acmanagement.api.customers.model.UpdateCustomerRequest;
import pl.piasta.acmanagement.domain.customers.model.Customer;
import pl.piasta.acmanagement.dto.customers.CustomerResponse;

import java.util.List;

@Mapper
public interface CustomerMapper {

    Customer mapToCustomer(UpdateCustomerRequest request);
    CustomerResponse mapToResponse(Customer customer);
    List<CustomerResponse> mapToResponseList(List<Customer> customerList);
}
