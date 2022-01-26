package pl.piasta.acmanagement.application.customers;

import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import io.restassured.http.ContentType;
import org.assertj.db.type.Table;
import org.junit.jupiter.api.Test;
import org.springframework.transaction.annotation.Transactional;
import pl.piasta.acmanagement.api.customers.model.UpdateCustomerRequest;
import pl.piasta.acmanagement.application.BaseIT;
import pl.piasta.acmanagement.domain.customers.model.DocumentType;

import static io.restassured.RestAssured.given;
import static org.assertj.db.api.Assertions.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class CustomersControllerIT extends BaseIT {

    @Test
    @Transactional
    public void addCustomer_Should_Return_201_Created_And_Expect_New_Database_Record() {
        given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(new UpdateCustomerRequest(
                        "John", "Smith",
                        "Stratford Park", "3513", "47805", "Terre Haute",
                        "+1-202-555-0183", "example@example.com",
                        DocumentType.IDCARD, "GDA7394612"))
                .post(createURLWithPort(CustomersEndpoints.BASE))
                .then()
                .log().all()
                .assertThat()
                .statusCode(201);
        Table table = new Table(dataSource, "CUSTOMERS", null, new String[] { "id" });
        assertThat(table)
                .hasNumberOfRows(1)
                .column("first_name").value().isEqualTo("John")
                .column("last_name").value().isEqualTo("Smith")
                .column("street_name").value().isEqualTo("Stratford Park")
                .column("house_number").value().isEqualTo("3513")
                .column("zip_code").value().isEqualTo("47805")
                .column("city").value().isEqualTo("Terre Haute")
                .column("phone_number").value().isEqualTo("+1-202-555-0183")
                .column("email").value().isEqualTo("example@example.com")
                .column("document_type").value().isEqualTo("IDCARD")
                .column("document_id").value().isEqualTo("GDA7394612");
    }

    @Test
    @DatabaseSetup(value = "classpath:customers.xml")
    @DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = "classpath:customers.xml")
    public void addCustomer_Should_Return_400_Bad_Request() {
        given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(new UpdateCustomerRequest(
                        "John", "Smith",
                        "Stratford Park", "3513", "47805", "Terre Haute",
                        "+1-202-555-0183", "example@example.com",
                        DocumentType.IDCARD, "GDA7394612"))
                .post(createURLWithPort(CustomersEndpoints.BASE))
                .then()
                .log().all()
                .assertThat()
                .statusCode(400);
    }

    @Test
    @DatabaseSetup(value = "classpath:customers.xml")
    @DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = "classpath:customers.xml")
    public void updateCustomer_Should_Return_204_No_Content_And_Expect_Updated_Database_Record() {
        given()
                .log().all()
                .contentType(ContentType.JSON)
                .pathParam("id", 2)
                .body(new UpdateCustomerRequest(
                        "Britney", "Sparkles",
                        "Armbrester Drive", "1462", "92614", "Irvine",
                        "+49-89-636-4801", "myemail@myprovider.com",
                        DocumentType.IDCARD, "AHY5671267"))
                .put(createURLWithPort(CustomersEndpoints.RESOURCE))
                .then()
                .log().all()
                .assertThat()
                .statusCode(204);
        Table table = new Table(dataSource, "CUSTOMERS", new String[] { "email", "document_id" }, null);
        assertThat(table)
                .hasNumberOfRows(2)
                .row(1)
                .column("email").value(1).isEqualTo("myemail@myprovider.com")
                .column("document_id").value(1).isEqualTo("AHY5671267");
    }

    @Test
    @DatabaseSetup(value = "classpath:customers.xml")
    @DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = "classpath:customers.xml")
    public void getCustomer_Should_Return_200_Ok_And_Expect_1_Customer_Response_Object() {
        given()
                .log().all()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .pathParam("id", 2)
                .get(createURLWithPort(CustomersEndpoints.RESOURCE))
                .then()
                .log().all()
                .assertThat()
                .contentType(ContentType.JSON)
                .statusCode(200)
                .body("lastName", equalTo("Sparkles"))
                .body("documentId", equalTo("ACU2345682"));
    }

    @Test
    public void getCustomer_Should_Return_400_Bad_Request() {
        given()
                .log().all()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .pathParam("id", 1)
                .get(createURLWithPort(CustomersEndpoints.RESOURCE))
                .then()
                .log().all()
                .assertThat()
                .contentType(ContentType.JSON)
                .statusCode(400);
    }

    @Test
    @DatabaseSetup(value = "classpath:customers.xml")
    @DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = "classpath:customers.xml")
    public void getAllCustomers_Should_Return_200_Ok_And_Expect_2_Customer_Response_Objects() {
        given()
                .log().all()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .get(createURLWithPort(CustomersEndpoints.BASE))
                .then()
                .log().all()
                .assertThat()
                .contentType(ContentType.JSON)
                .statusCode(200)
                .body("size()", is(2));
    }

    @Test
    public void getAllCustomers_Should_Return_200_Ok_And_Expect_No_Customer_Response_Objects() {
        given()
                .log().all()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .get(createURLWithPort(CustomersEndpoints.BASE))
                .then()
                .log().all()
                .assertThat()
                .contentType(ContentType.JSON)
                .statusCode(200)
                .body("$", empty());
    }
}
