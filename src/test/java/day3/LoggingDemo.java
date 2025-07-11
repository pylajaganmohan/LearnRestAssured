package day3;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import io.restassured.filter.log.LogDetail;

public class LoggingDemo {
	
	@Test
	void tesLogs() {
		given()
		.header("x-api-key", "reqres-free-v1")
	.when()
		.get("http://reqres.in/api/users?page=2")
	
	.then()
		//.log().headers()
		//.log().body()
		//.log().cookies()
		.log().all();
	}
}
