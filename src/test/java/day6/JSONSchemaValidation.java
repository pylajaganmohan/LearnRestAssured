package day6;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.testng.annotations.Test;

import io.restassured.module.jsv.JsonSchemaValidator;

//JSON --> JSON schema converter
//https://jsonformatter.org/json-to-jsonschema

public class JSONSchemaValidation {
	
	@Test
	void jsonSchemaValidation() throws FileNotFoundException {
		given()
		
		.when()
			.get("http://localhost:3000/store")
		
		.then()
			.assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("storeJSonSchema.json")) //passing classpath of the file
			.assertThat().body(JsonSchemaValidator.matchesJsonSchema(new File(".\\src\\test\\resources\\storeJsonSchema.json"))) //passing the File
			.assertThat().body(JsonSchemaValidator.matchesJsonSchema(new FileInputStream(new File(".\\src\\test\\resources\\storeJsonSchema.json")))); // passing InputStream
	}
}
