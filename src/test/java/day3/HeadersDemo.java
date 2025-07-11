package day3;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

import org.testng.annotations.Test;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

public class HeadersDemo {
	
	@Test
	void testHeaders() {
		given()
		
		.when()
			.get("https://www.google.com/")
		
		.then()
			.header("Content-Type","text/html; charset=ISO-8859-1")
			.header("Content-Encoding", "gzip")
			.header("Transfer-Encoding", "chunked")
			.log().all();
	}
	
	@Test
	void getAllHeaders() {
		Response res = when().get("https://www.google.com/");

		// get single header info
		String contentType = res.getHeader("Content-Type");
		System.out.println(contentType);

		// get alll headers info
		Headers headers = res.getHeaders();
		for (Header h : headers) {
			System.out.println(h.getName() + ": " + h.getValue());
		}
		
	}
	
}
