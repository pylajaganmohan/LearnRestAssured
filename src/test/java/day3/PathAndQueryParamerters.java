package day3;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

public class PathAndQueryParamerters {
	
	//http://reqres.in/api/users?page=2&id=5

	@Test
	void testQueryAndPathParameters() {
		given()
			.header("x-api-key", "reqres-free-v1")
			.pathParam("mypath", "users") //path paramerters
			.queryParam("page", 2)
			.queryParam("id", 5)
		.when()
			.get("http://reqres.in/api/{mypath}")
		
		.then()
			.statusCode(200)
			.header("Content-Type", equalTo("application/json; charset=utf-8"))
			.log().all();
	}
}
