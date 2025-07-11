package day8;

import static io.restassured.RestAssured.given;

import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

public class UpdateUser {
	
	@Test
	void updateUser(ITestContext context) {
		
		//int id = (int)context.getAttribute("user_id");
		int id = (int)context.getSuite().getAttribute("user_id");
		
		Faker faker = new Faker();
		JSONObject data = new JSONObject();
		data.put("name", faker.name().fullName());
		data.put("email", faker.internet().safeEmailAddress());
		data.put("gender", "male");
		data.put("status", "active");
		
		String token = "91ca1f0ffc19cdc1816b447a41485dcde0849c03b4dbd70c8a08974a93d2deac";
		
		given()
			.headers("Authorization","Bearer "+token)
			.contentType("application/json")
			.pathParam("id", id)
			.body(data.toString())
		
		.when()
			.put("https://gorest.co.in/public/v2/users/{id}")
			
		.then().log().all();
		
		
	}
}
