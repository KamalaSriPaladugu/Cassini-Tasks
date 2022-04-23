package com.restAssured.Task1Tests;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.BeforeClass;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class baseClass {

	public static RequestSpecification httpRequest;
	public static Response response;
	public static String loginDataPath = ".\\TestData\\LoginCredentials.xlsx";
	public static String registerDataPath = ".\\TestData\\RegisterCredentials.xlsx";
	
	
	public Logger logger;
	@BeforeClass
	public void setup() {
		logger= Logger.getLogger("Reqres API Tests");
		PropertyConfigurator.configure("Log4j.properties");
		logger.setLevel(Level.DEBUG);
		
	}
	
		
}
