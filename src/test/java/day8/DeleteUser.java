package day8;

import static io.restassured.RestAssured.given;

import org.testng.ITestContext;
import org.testng.annotations.Test;

public class DeleteUser {
	
	@Test
	void deleteUser(ITestContext context) {
		String token = "91ca1f0ffc19cdc1816b447a41485dcde0849c03b4dbd70c8a08974a93d2deac";
		//int id = (int)context.getAttribute("user_id");
		int id = (int)context.getSuite().getAttribute("user_id");
		
		given()
			.headers("Authorization","Bearer "+token)
			.contentType("application/json")
			.pathParam("id", id)
		
		.when()
			.delete("https://gorest.co.in/public/v2/users/{id}")
			
		.then().log().all();
	
	}
}
