package day4;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.json.JSONObject;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import junit.framework.Assert;

public class ParsingJSONReaponseData {
	
	@Test
	void testJSONResponse1(){
		
		//Approach 1
		given()
			.contentType(ContentType.JSON)
		
		.when()
			.get("http://localhost:3000/store")
		
		.then()
		.statusCode(200)
		.header("Content-Type","application/json")
		.body("book[3].title", equalTo("The Lord of the Rings"))
		.log().all();
		
	}
	
	@Test
	void testJSONResponse2(){
		
		//Approach 2
		Response res = given()
			.contentType(ContentType.JSON)
		
		.when()
			.get("http://localhost:3000/store");
		
		Assert.assertEquals(res.getStatusCode(), 200);
		Assert.assertEquals(res.getContentType(),"application/json");
		
		String bookname = res.jsonPath().getString("book[3].title");
		Assert.assertEquals(bookname, "The Lord of the Rings");
		
	}
	
	@Test
	void testJSONResponseBodyData(){
		
		Response res = given()
			.contentType(ContentType.JSON)
		
		.when()
			.get("http://localhost:3000/store");
		
		Assert.assertEquals(res.getStatusCode(), 200);
		Assert.assertEquals(res.getContentType(),"application/json");
		
		String bookname = res.jsonPath().getString("book[3].title");
		Assert.assertEquals(bookname, "The Lord of the Rings");

		JSONObject jo = new JSONObject(res.asString());
		
		boolean status = false;
		
		//Search for title of the book
		for (int i = 0; i < jo.getJSONArray("book").length(); i++) {
			String bookTitle = jo.getJSONArray("book").getJSONObject(i).get("title").toString();
						
			if(bookTitle.equalsIgnoreCase("The Lord of the Rings")) {
				status = true;
				break;
			}
		}
		Assert.assertTrue(status);
		
		//validate total price of books
		double price = 0;
		for(int i=0;i<jo.getJSONArray("book").length();i++) {
			price += Double.parseDouble(jo.getJSONArray("book").getJSONObject(i).get("price").toString());
		}	
		System.out.println("Total Price of Books: "+price);
		Assert.assertEquals(price, 526.0);
		
	}
	
	
	
	
	
	
	
}
