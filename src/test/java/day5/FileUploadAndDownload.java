package day5;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.File;

import org.testng.annotations.Test;


public class FileUploadAndDownload {
	
	@Test
	void singleFileUpload() {
		
		File f = new File("C:\\APITesting\\text1.txt");
		
		
		given()
			.multiPart("file",f) //form-data in Postman
			.contentType("multipart/form-data")
			
		.when().post("http://localhost:8080/uploadFile")
		
		.then()
			.statusCode(200)
			.body("fileName", equalTo("text1.txt"))
			.log().all();
	}
	
	@Test
	void multipleFileUpload() {
		
		File f1 = new File("C:\\APITesting\\text1.txt");
		File f2 = new File("C:\\APITesting\\text2.txt");
		
		given()
			.multiPart("files",f1) //form-data in Postman
			.multiPart("files",f2)
			.contentType("multipart/form-data")
			
		.when().post("http://localhost:8080/uploadMultipleFiles")
		
		.then()
			.statusCode(200)
			.body("[0].fileName", equalTo("text1.txt"))
			.body("[1].fileName", equalTo("text2.txt"))
			.log().all();
	}
	
	@Test
	void fileDownload() {
		given()
		
		.when()
			.get("http://localhost:8080/downloadFile/text1.txt")
		
		.then()
			.statusCode(200)
			.log().body();
	}
}
