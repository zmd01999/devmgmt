package pl.piasta.acmanagement.infrastructure.customers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.piasta.acmanagement.domain.customers.CustomersRepository;
import pl.piasta.acmanagement.domain.customers.model.Customer;
import pl.piasta.acmanagement.infrastructure.dao.CustomersDao;
import pl.piasta.acmanagement.infrastructure.mapper.CustomersEntityMapper;
import pl.piasta.acmanagement.infrastructure.model.CustomersEntity;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CustomersRepositoryImpl implements CustomersRepository {

    private final CustomersDao dao;
    private final CustomersEntityMapper mapper;

    @Override
    @Transactional
    public Long add(Customer customer) {
        CustomersEntity entity = new CustomersEntity();
        updateEntity(entity, customer);
        return dao.save(entity).getId();
    }

    @Override
    @Transactional
    public boolean update(Long id, Customer customer) {
        return dao.findById(id).map(entity -> {
            updateEntity(entity, customer);
            return true;
        }).orElse(false);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsByDocumentId(String documentId) {
        return dao.existsByDocumentId(documentId);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Customer> get(Long id) {
        Optional<CustomersEntity> entity = dao.findById(id);
        return mapper.mapToCustomerOptional(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Customer> getAll() {
        List<CustomersEntity> entityList = dao.findAll();
        return mapper.mapToCustomerList(entityList);
    }

    void updateEntity(CustomersEntity entity, Customer customer) {
        if (customer.getId() != null) {
            entity.setId(customer.getId());
        }
        entity.setFirstName(customer.getFirstName());
        entity.setLastName(customer.getLastName());
        entity.setStreetName(customer.getStreetName());
        entity.setHouseNumber(customer.getHouseNumber());
        entity.setCity(customer.getCity());
        entity.setZipCode(customer.getZipCode());
        entity.setPhoneNumber(customer.getPhoneNumber());
        entity.setEmail(customer.getEmail());
        entity.setDocumentType(customer.getDocumentType());
        entity.setDocumentId(customer.getDocumentId());
    }
}
