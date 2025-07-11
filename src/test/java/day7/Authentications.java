package day7;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;


public class Authentications {
	
	@Test
	void testBasicAuthentication() {
		given()
			.auth().basic("postman", "password")
		
		.when()
			.get("https://postman-echo.com/basic-auth")
		
		.then()
			.statusCode(200)
			.body("authenticated", equalTo(true))
			.log().all();
	}
	
	@Test
	void testDigestAuthentication() {
		given()
			.auth().digest("postman", "password")
		
		.when()
			.get("https://postman-echo.com/basic-auth")
		
		.then()
			.statusCode(200)
			.body("authenticated", equalTo(true))
			.log().all();
	}
	
	@Test
	void testPremptiveAuthentication() {
		given()
			.auth().preemptive().basic("postman", "password")
		
		.when()
			.get("https://postman-echo.com/basic-auth")
		
		.then()
			.statusCode(200)
			.body("authenticated", equalTo(true))
			.log().all();
	}
	
	@Test
	void testBearerTokenAuthentication() {
		
		String bearerToken = "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";
		
		given()
			.headers("Authorization","Bearer "+bearerToken)
		
		.when()
			.get("https://api.github.com/user/repos")
		
		.then()
			.statusCode(200)
			.log().all();
	}
	
	
	
	void testOAuth1Authentication() {
		given()
			.auth().oauth("consumerKey", "consumerSecret", "accessToken", "tokenSecret")  //Syntax
		.when()
			.get("url")
		.then()
			.statusCode(200);
	}
	
	
	@Test
	void testOAuth2Authentication() {
		given()
			.auth().oauth2("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") //Syntax
		.when()
			.get("https://api.github.com/user/repos")
		.then()
			.statusCode(200)
			.log().all();
	}
	
	@Test
	void testAPIkeyAuthentication() {
		
		//Method 1
		/*
		given()
			.queryParam("appid", "f0cdce582c06cf93f7a4c4542def97ae")
		.when()
			.get("https://api.openweathermap.org/data/2.5/forecast/daily?q=Delhi&units=metric&cnt=7")
		.then()
			.statusCode(200);
		*/
		
		//Method 2
		given()
			.pathParam("path", "data/2.5/forecast/daily")
			.queryParam("q", "Delhi")
			.queryParam("units", "metric")
			.queryParam("cnt", 7)
			.queryParam("appid", "f0cdce582c06cf93f7a4c4542def97ae")
		.when()
			.get("https://api.openweathermap.org/{path}")
		.then()
			.statusCode(200);
	}
}
