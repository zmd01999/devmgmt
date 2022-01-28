package pl.piasta.acmanagement.application.acunits;

import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import io.restassured.http.ContentType;
import org.assertj.db.type.Table;
import org.junit.jupiter.api.Test;
import org.springframework.transaction.annotation.Transactional;

import pl.piasta.acmanagement.application.BaseIT;
import pl.piasta.acmanagement.dto.acunits.UpdateUnitRequest;

import javax.json.Json;
import javax.json.JsonPatch;

import static io.restassured.RestAssured.given;
import static org.assertj.db.api.Assertions.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class AcUnitsControllerIT extends BaseIT {

    @Test
    @Transactional
    public void addUnit_Should_Return_201_Created_And_Expect_New_Database_Record() {
        given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(new UpdateUnitRequest("Test Systems Inc.", "Product 001", 230, 30))
                .post(createURLWithPort(AcUnitsEndpoints.BASE))
                .then()
                .log().all()
                .assertThat()
                .statusCode(201);
        Table table = new Table(dataSource, "AC_UNITS", null, new String[] { "id" });
        assertThat(table)
                .hasNumberOfRows(1)
                .column("manufacturer").value().isEqualTo("Test Systems Inc.")
                .column("product_name").value().isEqualTo("Product 001")
                .column("voltage").value().isEqualTo(230)
                .column("current").value().isEqualTo(30);
    }

    @Test
    @DatabaseSetup(value = "classpath:units.xml")
    @DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = "classpath:units.xml")
    public void updateUnit_Should_Return_204_No_Content_And_Expect_Updated_Database_Record() {
        JsonPatch jsonPatch = Json.createPatchBuilder()
                .replace("/productName", "Updated Product 002")
                .replace("/voltage", 210)
                .build();
        given()
                .log().all()
                .contentType("application/json-patch+json")
                .pathParam("id", 2)
                .body(jsonPatch.toString())
                .patch(createURLWithPort(AcUnitsEndpoints.RESOURCE))
                .then()
                .log().all()
                .assertThat()
                .statusCode(204);
        Table table = new Table(dataSource, "AC_UNITS", new String[] { "product_name", "voltage" }, null);
        assertThat(table)
                .hasNumberOfRows(2)
                .row(1)
                .column("product_name").value(1).isEqualTo("Updated Product 002")
                .column("voltage").value(1).isEqualTo(210);
    }

    @Test
    @DatabaseSetup(value = "classpath:units.xml")
    @DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = "classpath:units.xml")
    public void getUnit_Should_Return_200_Ok_And_Expect_1_Unit_Response_Object() {
        given()
                .log().all()
                .contentType(ContentType.JSON)
                .pathParam("id", 2)
                .get(createURLWithPort(AcUnitsEndpoints.RESOURCE))
                .then()
                .log().all()
                .assertThat()
                .contentType(ContentType.JSON)
                .statusCode(200)
                .body("manufacturer", equalTo("Additional Systems Inc."))
                .body("productName", equalTo("Product 002"))
                .body("voltage", equalTo(240))
                .body("current", equalTo(20));
    }

    @Test
    public void getUnit_Should_Return_400_Bad_Request() {
        given()
                .log().all()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .pathParam("id", 1)
                .get(createURLWithPort(AcUnitsEndpoints.RESOURCE))
                .then()
                .log().all()
                .assertThat()
                .contentType(ContentType.JSON)
                .statusCode(400);
    }

    @Test
    @DatabaseSetup(value = "classpath:units.xml")
    @DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = "classpath:units.xml")
    public void getAllUnits_Should_Return_200_Ok_And_Expect_3_Unit_Response_Objects() {
        given()
                .log().all()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .get(createURLWithPort(AcUnitsEndpoints.BASE))
                .then()
                .log().all()
                .assertThat()
                .contentType(ContentType.JSON)
                .statusCode(200)
                .body("size()", is(2));
    }

    @Test
    public void getAllUnits_Should_Return_200_Ok_And_Expect_No_Unit_Response_Objects() {
        given()
                .log().all()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .get(createURLWithPort(AcUnitsEndpoints.BASE))
                .then()
                .log().all()
                .assertThat()
                .contentType(ContentType.JSON)
                .statusCode(200)
                .body("$", empty());
    }
}
