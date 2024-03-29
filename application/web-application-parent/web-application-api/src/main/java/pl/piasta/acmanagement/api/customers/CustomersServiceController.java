package pl.piasta.acmanagement.api.customers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import pl.piasta.acmanagement.api.common.VResponse;
import pl.piasta.acmanagement.api.mapper.CustomerMapper;

import pl.piasta.acmanagement.domain.customers.model.Customer;
import pl.piasta.acmanagement.dto.customers.CustomerResponse;
import pl.piasta.acmanagement.dto.customers.UpdateCustomerRequest;
import pl.piasta.acmanagement.api.service.CustomersService;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
@Validated
@RequiredArgsConstructor
public class CustomersServiceController {

    private final CustomersService service;
    private final CustomerMapper mapper;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public VResponse<Long> addCustomer(@RequestBody @Valid UpdateCustomerRequest request) {
        Customer customer = mapper.mapToCustomer(request);
        Long id = service.addCustomer(customer);
        return VResponse.success(id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void updateCustomer(@PathVariable @Min(1) Long id, @RequestBody @Valid UpdateCustomerRequest request) {
        Customer customer = mapper.mapToCustomer(request);
        service.updateCustomer(id, customer);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public VResponse<CustomerResponse> getCustomer(@PathVariable @Min(1) Long id) {
        Customer customer = service.getCustomerById(id);
        return VResponse.success(mapper.mapToResponse(customer));
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public VResponse<List<CustomerResponse>> getAllCustomers() {
        List<Customer> customerList = service.getAllCustomers();
        return VResponse.success(mapper.mapToResponseList(customerList));
    }
}
