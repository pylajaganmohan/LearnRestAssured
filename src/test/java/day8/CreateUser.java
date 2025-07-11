package day8;

import static io.restassured.RestAssured.*;


import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

public class CreateUser {
	
	@Test
	void createUser(ITestContext context) {
		Faker faker = new Faker();
		JSONObject data = new JSONObject();
		data.put("name", faker.name().fullName());
		data.put("email", faker.internet().safeEmailAddress());
		data.put("gender", "male");
		data.put("status", "inactive");
		
		String token = "91ca1f0ffc19cdc1816b447a41485dcde0849c03b4dbd70c8a08974a93d2deac";
		
		int id=given()
					.headers("Authorization","Bearer "+token)
					.contentType("application/json")
					.body(data.toString())
		
				.when()
					.post("https://gorest.co.in/public/v2/users").jsonPath().getInt("id");
		
		System.out.println("employee Id: "+id);
		//context.setAttribute("user_id", id);
		context.getSuite().setAttribute("user_id", id);
	}
}
