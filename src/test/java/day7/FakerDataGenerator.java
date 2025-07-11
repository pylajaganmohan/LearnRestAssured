package day7;

import org.testng.annotations.Test;

import com.github.javafaker.Faker;

public class FakerDataGenerator {
	
	@Test
	void testGenerateDummyData() {
		Faker faker = new Faker();
		String fullName = faker.name().fullName();
		String firstName = faker.name().firstName();
		String lastName = faker.name().lastName();
		
		String userName = faker.name().username();
		String pwd = faker.internet().password();
		
		String ph = faker.phoneNumber().cellPhone();
		
		String email = faker.internet().safeEmailAddress();
		
		//System.out.println(fullName+" "+firstName+" "+lastName+" "+userName+" "+pwd+" "+ph+" "+email);
		
		System.out.println(fullName);
		System.out.println(firstName);
		System.out.println(lastName);
		System.out.println(userName);
		System.out.println(pwd);
		System.out.println(ph);
		System.out.println(email);
	}
}
