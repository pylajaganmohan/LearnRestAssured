package day2;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.UUID;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

/*
 * Different Ways to create POST Request body:
 * 1. HashMap
 * 2. Org.JSON
 * 3. POJO
 * 4. External JSON File
 */

public class LinkedinPost {

	String UNIQUE_ID = "ID-" + UUID.randomUUID().toString();  // Unique String ID

	@Test
	void createResource_usingHashMap() {
		HashMap<String, Object> data = new HashMap<>();
		data.put("id", UNIQUE_ID);
		data.put("name", "William");
		data.put("location", "France");
		data.put("phone", "9876543210");
		data.put("courses", new String[] { "C", "C++" });

		given()
			.contentType("application/json")
			.body(data)
		.when()
			.post("http://localhost:3000/TechStudents")
		.then()
			.statusCode(201)
			.body("name", equalTo("William"))
			.body("location", equalTo("France"))
			.body("phone", equalTo("9876543210"))
			.body("courses[0]", equalTo("C"))
			.body("courses[1]", equalTo("C++"))
			.header("Content-Type", containsString("application/json"))
			.log().all();
	}

	
	
	@Test
	void createResource_usingOrgJson() {
		JSONObject data = new JSONObject();
		data.put("id", UNIQUE_ID);
		data.put("name", "William");
		data.put("location", "France");
		data.put("phone", "9876543210");
		data.put("courses", new String[] { "C", "C++" });

		given()
			.contentType("application/json")
			.body(data.toString())
		.when()
			.post("http://localhost:3000/TechStudents")
		.then()
			.statusCode(201)
			.body("name", equalTo("William"))
			.body("location", equalTo("France"))
			.body("phone", equalTo("9876543210"))
			.body("courses[0]", equalTo("C"))
			.body("courses[1]", equalTo("C++"))
			.header("Content-Type", containsString("application/json"))
			.log().all();
	}

	
	
	@Test
	void createResource_usingPOJO() {
		DataPOJO data = new DataPOJO();
		data.setId(UNIQUE_ID); // assuming it's long or int in POJO
		data.setName("William");
		data.setLocation("France");
		data.setPhone("9876543210");
		data.setCourses(new String[] { "C", "C++" });

		given()
			.contentType("application/json")
			.body(data)
		.when()
			.post("http://localhost:3000/TechStudents")
		.then()
			.statusCode(201)
			.body("name", equalTo("William"))
			.body("location", equalTo("France"))
			.body("phone", equalTo("9876543210"))
			.body("courses[0]", equalTo("C"))
			.body("courses[1]", equalTo("C++"))
			.header("Content-Type", containsString("application/json"))
			.log().all();
	}

	
	
	
	@Test
	void createResource_usingExternalJsonFile() throws FileNotFoundException {
		File file = new File(".\\body.json");
		FileInputStream fis = new FileInputStream(file);
		JSONTokener tokener = new JSONTokener(fis);
		JSONObject data = new JSONObject(tokener);

		// Overriding ID dynamically
		data.put("id", UNIQUE_ID);

		given()
			.contentType("application/json")
			.body(data.toString())
		.when()
			.post("http://localhost:3000/TechStudents")
		.then()
			.statusCode(201)
			.body("name", equalTo("William"))
			.body("location", equalTo("France"))
			.body("phone", equalTo("9876543210"))
			.body("courses[0]", equalTo("C"))
			.body("courses[1]", equalTo("C++"))
			.header("Content-Type", containsString("application/json"))
			.log().all();
	}

	@AfterMethod
	void deleteResource_byId() {
		when()
			.delete("http://localhost:3000/TechStudents/" + UNIQUE_ID)
		.then()
			.statusCode(200)
			.log().all();
	}
}
