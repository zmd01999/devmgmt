package pl.piasta.acmanagement.infrastructure.service.lmpl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.piasta.acmanagement.api.misc.MyException;
import pl.piasta.acmanagement.api.service.CustomersService;
import pl.piasta.acmanagement.infrastructure.customers.CustomersRepository;
import pl.piasta.acmanagement.domain.customers.model.Customer;
import pl.piasta.acmanagement.domain.misc.ErrorCode;


import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomersServiceImpl implements CustomersService {

    private final CustomersRepository customersRepository;

    @Override
    @Transactional
    public Long addCustomer(Customer customer) {
        if (customersRepository.existsByDocumentId(customer.getDocumentId())) {
            throw new MyException(ErrorCode.CUSTOMER_ALREADY_EXISTS);
        }
        return customersRepository.add(customer);
    }

    @Override
    @Transactional
    public void updateCustomer(Long id, Customer customer) {
        if (!customersRepository.update(id, customer)) {
            throw new MyException(ErrorCode.CUSTOMER_NOT_EXISTS);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Customer getCustomerById(Long id) {
        return customersRepository.get(id)
                .orElseThrow(() -> new MyException(ErrorCode.CUSTOMER_NOT_EXISTS));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Customer> getAllCustomers() {
        return customersRepository.getAll();
    }
}
