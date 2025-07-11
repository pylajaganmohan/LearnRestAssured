package day3;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.http.Cookie;
import io.restassured.response.Response;

public class CookiesDemo {
	
	@Test
	void testCookies() {
		
		given()
		
		.when()
			.get("https://www.google.com/")
		
		.then()
			.cookie("AEC")
			.cookie("NID")
			.log().all();
	}
	
	@Test
	void GetCookiesInfo() {
		Response res = when().get("https://www.google.com/");

		// get single cookie
		String AEC_value = res.getCookie("AEC");
		System.out.println("Value of Cookie is ===> "+AEC_value);
		
		//get all cookies Info
		Map<String, String> cookies = res.getCookies();
		System.out.println(cookies.keySet());
		
		for (Map.Entry<String, String> entry : cookies.entrySet()) {
			System.out.println(entry.getKey() + " ==> " + entry.getValue());
		}

	}
}
