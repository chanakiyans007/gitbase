package practice.testng;

import org.testng.annotations.Test;

public class OrderTest {
	
	@Test //(invocationCount = 10)
	public void createOrderTest() {
		System.out.println("Execurte createOrderTest==>123");
	}
	
	@Test (enabled = false) //(dependsOnMethods = "createOrderTest") 
	public void billingAnOrderTest() {
		System.out.println("Execurte billingAnOrderTest==>123");
	}

}
