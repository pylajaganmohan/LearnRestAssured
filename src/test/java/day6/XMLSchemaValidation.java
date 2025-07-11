package day6;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import io.restassured.matcher.RestAssuredMatchers;


//https://www.convertsimple.com/convert-xml-to-xsd-xml-schema/

public class XMLSchemaValidation {
	
	@Test
	void xmlSchemaValidation() {
		given()
			
		.when()
			.get("http://localhost:3001/api/books?page=1")
		.then()
			.assertThat().body(RestAssuredMatchers.matchesXsdInClasspath("booksXMLSchema.xsd"));
	}
}
