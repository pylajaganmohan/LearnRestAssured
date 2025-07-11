package day6;

import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


//POJO ---Serialize--->  JSON Object ---DeSerialize---> POJO
//
public class SerializationDeserialization {
	
	
	//POJO ---> JSON (Serialization)
	@Test
	void convertPojo2Json() throws JsonProcessingException {
		
		//Created Java object using POJO class
		StudentPOJO data = new StudentPOJO();
		data.setId("7");
		data.setName("Jagan");
		data.setLocation("india");
		data.setPhone("9876543210");
		String[] course = { "C", "C++" };
		data.setCourses(course);
		
		//convert java object  --> json object (Serialization)
		ObjectMapper objMapper = new ObjectMapper();
		String jsondata=objMapper.writerWithDefaultPrettyPrinter().writeValueAsString(data);
		System.out.println(jsondata);
		
	}
	
	
	@Test
	void convertJson2Pojo() throws JsonProcessingException {
		
		String jsondata = "{\r\n"
				+ "  \"id\" : \"7\",\r\n"
				+ "  \"name\" : \"Jagan\",\r\n"
				+ "  \"location\" : \"india\",\r\n"
				+ "  \"phone\" : \"9876543210\",\r\n"
				+ "  \"courses\" : [ \"C\", \"C++\" ]\r\n"
				+ "}";
		
		//converting JSOM data -- POJO Object
		ObjectMapper objMapper = new ObjectMapper();
		StudentPOJO studPojo = objMapper.readValue(jsondata, StudentPOJO.class);
		System.out.println(studPojo.toString());
		
	}
}
