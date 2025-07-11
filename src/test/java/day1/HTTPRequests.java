package day1;

import static io.restassured.RestAssured.given;

import static org.hamcrest.Matchers.equalTo;

import java.util.HashMap;

import org.testng.annotations.Test;

/*
 * given() - content type, set cookies, add auth, set headers info etc...
 * 
 * when() - get, post, put, delete
 * 
 * then() - validate status code, extract response, extract headers cookies & response body..
 * 
 */
public class HTTPRequests {

	int id;

	@Test(priority = 1)
	void getUsers() {
		// need to import static packages -
		// https://github.com/rest-assured/rest-assured/wiki/GettingStarted#static-imports
		given()

				.when().get("https://reqres.in/api/users?page=2")

				.then().statusCode(200).body("page", equalTo(2)).log().all();
	}

	@Test(priority = 2)
	void createUser() {

		HashMap<String, String> data = new HashMap<>();
		data.put("name", "Jagan");
		data.put("job", "Engineer");

		given()
			.header("x-api-key", "reqres-free-v1")
			.contentType("application/json").body(data)

		.when()
			.post("https://reqres.in/api/users").jsonPath().getInt("id");

//		.then()
//			.statusCode(201)
//			.contentType("application/json")
//			.log().all();

	}

	@Test(priority = 3, dependsOnMethods = { "createUser" })
	void updateUser() {
		HashMap<String, String> data = new HashMap<>();
		data.put("name", "Jagan");
		data.put("job", "Software Engineer");

		given().header("x-api-key", "reqres-free-v1").contentType("application/json").body(data)

				.when().put("https://reqres.in/api/users/" + id)

				.then().statusCode(200)
				.log().all();

	}

	@Test(priority = 4)
	void deleteUser() {
		given().header("x-api-key", "reqres-free-v1")

				.when().delete("https://reqres.in/api/users/" + id)

				.then().statusCode(204).log().all();
	}

}
