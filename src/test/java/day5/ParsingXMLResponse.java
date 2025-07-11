package day5;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.List;

import org.testng.annotations.Test;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import junit.framework.Assert;


public class ParsingXMLResponse {
	
	@Test
	void testXMLResponse_1() {
		//Approach 1
		
		given()
		
		.when()
			.get("http://localhost:3001/api/books?page=1")
		
		.then()
			.header("Content-Type","application/xml; charset=utf-8")
			.body("library.page", equalTo("1"))
			.body("library.books.book[0].author", equalTo("Nihel Rees"))
			.log().all();
	}
	
	@Test
	void testXMLResponse_2() {
		//Approach 2
		
		Response res = given()
		
		.when()
			.get("http://localhost:3001/api/books?page=1");
		
		Assert.assertEquals(res.getContentType(), "application/xml; charset=utf-8");
		Assert.assertEquals(res.header("Content-Type"), "application/xml; charset=utf-8");
		Assert.assertEquals(res.getStatusCode(), 200);
		
		String bookname= res.xmlPath().get("library.books.book[0].title").toString();
		Assert.assertEquals(bookname, "Sayings of the Century");
		
		String pageNo = res.xmlPath().getString("library.page");
		Assert.assertEquals(pageNo,"1");
		
		Headers head = res.getHeaders();
		for(Header h:head) {
			System.out.println(h.getName()+": "+h.getValue());
		}
		
	}
	
	@Test
	void testXMLResponseBodyData() {

		Response res = given()

				.when().get("http://localhost:3001/api/books?page=1");

		XmlPath xp = new XmlPath(res.asString());
		
		List<String> books = xp.getList("library.books.book");
		
		//verify numbers books per page are <= 10
		int totalBooks = books.size();
		int per_page = xp.getInt("library.per_page");
		Assert.assertTrue(totalBooks <= per_page);

		String title = "";
		for (int i = 0; i < totalBooks; i++) {
			String author = xp.getString("library.books.book[" + i + "].author").toString();
			if (author.equalsIgnoreCase("Nihel Rees")) {
				title = xp.getString("library.books.book[" + i + "].title");
				break;
			}
		}
		if (title != "")
			Assert.assertTrue(title.equalsIgnoreCase("Sayings of the Century"));
		
		//Verify Author is present in the Page
		List<String> authors = xp.getList("library.books.book.author");
		boolean found = false;
		for(int i=0;i<authors.size();i++) {
			if(authors.get(i).equalsIgnoreCase("Nihel Rees")) {
				found = true;
			}
		}
		Assert.assertTrue(found);
	}
	
}
