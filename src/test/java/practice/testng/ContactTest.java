package practice.testng;

import org.testng.annotations.Test;

public class ContactTest {

	@Test //(priority = 1)
	public void createContactTest() {
		System.out.println("execute createContactTest --> HDFC");
	}
	
	@Test  (dependsOnMethods = "createContactTest")//(priority = 2) 
	public void modifyContactTest() {
		
		System.out.println("execute modifyContactTest --> HDFC ==> ICICI");
	}
	
	@Test (dependsOnMethods = "modifyContactTest") //(priority = 3)
	public void deleteContactTest() {
		
		System.out.println("execute deleteContactTest ICICI");
	}
	
}
