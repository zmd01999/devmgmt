package pl.piasta.acmanagement.infrastructure.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import pl.piasta.acmanagement.domain.customers.model.DocumentType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CUSTOMERS")
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class CustomersEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "FIRST_NAME", nullable = false, length = 50)
    private String firstName;

    @Column(name = "LAST_NAME", nullable = false, length = 50)
    private String lastName;

    @Column(name = "STREET_NAME", nullable = false, length = 100)
    private String streetName;

    @Column(name = "HOUSE_NUMBER", nullable = false)
    private String houseNumber;

    @Column(name = "ZIP_CODE", nullable = false, length = 10)
    private String zipCode;

    @Column(name = "CITY", nullable = false, length = 50)
    private String city;

    @Column(name = "PHONE_NUMBER", nullable = false, length = 15)
    private String phoneNumber;

    @Column(name = "EMAIL", nullable = false)
    private String email;

    @Column(name = "DOCUMENT_TYPE", nullable = false, length = 10)
    @Enumerated(EnumType.STRING)
    private DocumentType documentType;

    @Column(name = "DOCUMENT_ID", nullable = false, length = 10)
    private String documentId;
}
