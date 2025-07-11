package day8;

import static io.restassured.RestAssured.*;

import org.testng.ITestContext;
import org.testng.annotations.Test;

import io.restassured.response.Response;
import junit.framework.Assert;

public class GetUser {

	@Test
	void getUser(ITestContext context) {
		// int id = (int)context.getAttribute("user_id");
		int id = (int) context.getSuite().getAttribute("user_id");

		String token = "91ca1f0ffc19cdc1816b447a41485dcde0849c03b4dbd70c8a08974a93d2deac";

		Response res = given().headers("Authorization", "Bearer " + token).pathParam("id", id).when()
				.get("https://gorest.co.in/public/v2/users/{id}");

		res.then().log().all();
		long time = res.time();
		System.out.println("Response time: "+time);
		//Assert.assertTrue(time < 300);
	}
}
