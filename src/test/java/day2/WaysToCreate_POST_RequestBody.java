package day2;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

/*
 * Different Ways to create POST Request body
 * 1. Post request body using HashMap
 * 2. Post request body creation using Org.JSON
 * 3. Post request body creation using POJO class
 * 4. post request using external JSON file data
 */

public class WaysToCreate_POST_RequestBody {

	@Test(priority = 1)
	void testPostUsing_HashMap() {
		// 1. Post request body using HashMap
		HashMap<String, Object> data = new HashMap<String, Object>();
		data.put("id", "7");
		data.put("name", "Jagan");
		data.put("location", "india");
		data.put("phone", "9876543210");
		String[] course = { "C", "C++" };
		data.put("courses", course);

		given()
			.contentType("application/json")
			.body(data)

		.when()
			.post("http://localhost:3000/TechStudents")
			
		.then()
			.statusCode(201)
			.body("name", equalTo("Jagan"))
			.body("location", equalTo("india"))
			.body("phone", equalTo("9876543210"))
			.body("courses[0]", equalTo("C"))
			.body("courses[1]", equalTo("C++"))
			.header("Content-Type", equalTo("application/json"))
			.log().all();
	}

	@Test(priority = 2)
	void testPostUsing_OrgJSON() {
		// 2. Post request body creation using Org.JSON
		JSONObject data = new JSONObject();
		data.put("id", "7");
		data.put("name", "Jagan");
		data.put("location", "india");
		data.put("phone", "9876543210");
		String[] course = { "C", "C++" };
		data.put("courses", course);

		given()
			.contentType("application/json")
			.body(data.toString()) // convert JSON data object to String format

		.when()
			.post("http://localhost:3000/TechStudents")
			
		.then()
			.statusCode(201)
			.body("name", equalTo("Jagan"))
			.body("location", equalTo("india"))
			.body("phone", equalTo("9876543210"))
			.body("courses[0]", equalTo("C"))
			.body("courses[1]", equalTo("C++"))
			.header("Content-Type", equalTo("application/json"))
			.log().all();
	}

	@Test(priority = 3)
	void testPostUsing_POJO() {
		// 3. Post request body creation using POJO class
		DataPOJO data = new DataPOJO();
		data.setId("7");
		data.setName("Jagan");
		data.setLocation("india");
		data.setPhone("9876543210");
		String[] course = { "C", "C++" };
		data.setCourses(course);

		given()
			.contentType("application/json")
			.body(data) // Pass data here

		.when()
			.post("http://localhost:3000/TechStudents")
		
		.then()
			.statusCode(201)
			.body("name", equalTo("Jagan"))
			.body("location", equalTo("india"))
			.body("phone", equalTo("9876543210"))
			.body("courses[0]", equalTo("C"))
			.body("courses[1]", equalTo("C++"))
			.header("Content-Type", equalTo("application/json"))
			.log().all();
	}

	@Test(priority = 4)
	void testPostUsing_ExternalJSONFile() throws FileNotFoundException {
		// 4. post request using external JSON file data
		File f = new File(".\\body.json");
		FileInputStream fr = new FileInputStream(f); // we can use Either FileInputStream or FileReader to read the file
		// FileReader fr = new FileReader(f);
		JSONTokener jt = new JSONTokener(fr);
		JSONObject data = new JSONObject(jt);

		given()
			.contentType("application/json")
			.body(data.toString())

		.when()
			.post("http://localhost:3000/TechStudents")
		
		.then()
			.statusCode(201)
			.body("name", equalTo("Jagan"))
			.body("location", equalTo("india"))
			.body("phone", equalTo("9876543210"))
			.body("courses[0]", equalTo("C"))
			.body("courses[1]", equalTo("C++"))
			.header("Content-Type", equalTo("application/json"))
			.log().all();
	}

	@AfterMethod
	void deleteData() {
		when()
			.delete("http://localhost:3000/TechStudents/7")

		.then()
			.statusCode(200)
			.log().all();
	}
}
