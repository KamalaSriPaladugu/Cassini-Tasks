package com.selenium.Tests;

import java.io.IOException;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;

import com.selenium.utilities.XLUtility;

public class baseClass {
	
	public static String baseURL ="http://yahoo.com/";
	public static WebDriver driver;
	public static String loginDataPath=".\\TestData\\Credentials.xlsx";
	
	public Logger logger;
	@BeforeClass
	public void setup() {
		
		System.setProperty("webdriver.chrome.driver", ".\\Driver\\chromedriver.exe");
		driver= new ChromeDriver();	
		logger= Logger.getLogger("Selenium Tests");
		PropertyConfigurator.configure("Log4j.properties");
		logger.setLevel(Level.DEBUG);		
	}
	
	@BeforeClass
	@DataProvider(name = "LoginData")
	 public Object [][] getdata() throws IOException{
		 String path = loginDataPath;
		 XLUtility xlutil = new XLUtility(path);
		 int totRows = xlutil.getRowCount("Credentials");
		 int totCols = xlutil.getCellCount("Credentials",1);
		 Object loginData[][] =new Object[totRows][totCols];
		 for(int i=1;i<=totRows;i++)
		 {
			 for(int j=0;j<totCols;j++)
			 {
				 loginData[i-1][j]= xlutil.getCellData("Credentials", i, j);
				 
			 }
		 }	 
		 return loginData;
		 }
			
		 @AfterClass 
		 public void tearDown() 
		 { driver.quit(); }
			 
	}


