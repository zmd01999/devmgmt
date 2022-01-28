package pl.piasta.acmanagement.application.acsystems;

import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import io.restassured.http.ContentType;
import org.assertj.db.type.DateTimeValue;
import org.assertj.db.type.Table;
import org.assertj.db.type.TimeValue;
import org.junit.jupiter.api.Test;

import pl.piasta.acmanagement.application.BaseIT;
import pl.piasta.acmanagement.dto.acsystems.AddSystemRequest;

import java.time.LocalDateTime;

import static io.restassured.RestAssured.given;
import static org.assertj.db.api.Assertions.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class AcSystemsControllerIT extends BaseIT {

    @Test
    @DatabaseSetup(value = { "classpath:units.xml", "classpath:customers.xml" })
    @DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = { "classpath:customers.xml", "classpath:units.xml", "classpath:systems.xml" })
    public void addSystem_Should_Return_201_Created_And_Expect_New_Database_Record() {
        LocalDateTime nextMaintainance = LocalDateTime.now().plusDays(8);
        given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(new AddSystemRequest(1L, nextMaintainance, true, 2L))
                .post(createURLWithPort(AcSystemsEndpoints.BASE))
                .then()
                .log().all()
                .assertThat()
                .statusCode(201);
        Table table = new Table(dataSource, "AC_SYSTEMS", null, new String[] { "id" });
        assertThat(table)
                .hasNumberOfRows(1)
                .column("unit_id").value().isEqualTo(1)
                .column("next_maintainance").value().isCloseTo(DateTimeValue.from(nextMaintainance), TimeValue.of(0, 0, 1))
                .column("notifications").value().isEqualTo(true)
                .column("customer_id").value().isEqualTo(2);
    }

    @Test
    public void addSystem_Should_Return_400_Bad_Request() {
        LocalDateTime nextMaintainance = LocalDateTime.now().plusDays(8);
        given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(new AddSystemRequest(1L, nextMaintainance, true, 2L))
                .post(createURLWithPort(AcSystemsEndpoints.BASE))
                .then()
                .log().all()
                .assertThat()
                .statusCode(400);
    }

    @Test
    @DatabaseSetup(value = { "classpath:customers.xml", "classpath:units.xml", "classpath:systems.xml" })
    @DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = { "classpath:customers.xml", "classpath:units.xml", "classpath:systems.xml" })
    public void getSystem_Should_Return_200_Ok_And_Expect_1_System_Response_Object() {
        given()
                .log().all()
                .contentType(ContentType.JSON)
                .pathParam("id", 2)
                .get(createURLWithPort(AcSystemsEndpoints.RESOURCE))
                .then()
                .log().all()
                .assertThat()
                .contentType(ContentType.JSON)
                .statusCode(200)
                .body("unit.id", equalTo(2))
                .body("notified", equalTo(true))
                .body("customer.id", equalTo(1));
    }

    @Test
    public void getSystem_Should_Return_400_Bad_Request() {
        given()
                .log().all()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .pathParam("id", 1)
                .get(createURLWithPort(AcSystemsEndpoints.RESOURCE))
                .then()
                .log().all()
                .assertThat()
                .contentType(ContentType.JSON)
                .statusCode(400);
    }

    @Test
    @DatabaseSetup(value = { "classpath:customers.xml", "classpath:units.xml", "classpath:systems.xml" })
    @DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = { "classpath:customers.xml", "classpath:units.xml", "classpath:systems.xml" })
    public void getAllSystems_Should_Return_200_Ok_And_Expect_3_System_Response_Objects() {
        given()
                .log().all()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .get(createURLWithPort(AcSystemsEndpoints.BASE))
                .then()
                .log().all()
                .assertThat()
                .contentType(ContentType.JSON)
                .statusCode(200)
                .body("size()", is(2));
    }

    @Test
    public void getAllSystems_Should_Return_200_Ok_And_Expect_No_System_Response_Objects() {
        given()
                .log().all()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .get(createURLWithPort(AcSystemsEndpoints.BASE))
                .then()
                .log().all()
                .assertThat()
                .contentType(ContentType.JSON)
                .statusCode(200)
                .body("$", empty());
    }
}
