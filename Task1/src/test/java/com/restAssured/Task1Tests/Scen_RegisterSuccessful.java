package com.restAssured.Task1Tests;


import java.io.IOException;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.restAssured.utilities.XLUtility;

import io.restassured.RestAssured;
import io.restassured.http.Method;

/*Tests to validate Register Successful POST API
   * Test with valid email and password 
   * Test with invalid email and some password
   * Test with no email and some password
   * Test with valid email and no password
   */

public class Scen_RegisterSuccessful extends baseClass{

 
 @BeforeClass
 @DataProvider(name = "RegisteredData")
 public Object [][] getdata() throws IOException{
	 String path = registerDataPath;
	 XLUtility xlutil = new XLUtility(path);
	 int totRows = xlutil.getRowCount("Sheet1");
	 int totCols = xlutil.getCellCount("Sheet1",1);
	 Object loginData[][] =new Object[totRows][totCols];
	 for(int i=1;i<=totRows;i++)
	 {
		 for(int j=0;j<totCols;j++)
		 {
			 loginData[i-1][j]= xlutil.getCellData("Sheet1", i, j);
			 
		 }
	 }	 
	 return loginData;
	 }
 
 @SuppressWarnings("unchecked")
 @Test(dataProvider = "RegisteredData")
 void checkRegisterSuccessful(String email, String pwd, String message, String statusCode){
	 
	 RestAssured.baseURI="https://reqres.in/api/";
	 httpRequest = RestAssured.given();
	 JSONObject requestParams = new JSONObject();
	 requestParams.put("email", email);
	 requestParams.put("password", pwd);
	 httpRequest.header("Content-Type","application/json");
	 httpRequest.body(requestParams.toJSONString()); 
	 
	 response = httpRequest.request(Method.POST,"/register"); 
	 String responseBody = response.getBody().asString();
 
	 //validation
	 int actualStatuscode = response.getStatusCode();
	 Assert.assertEquals(String.valueOf(actualStatuscode), statusCode);
	 Assert.assertTrue(responseBody.contains(message));
 }
 
 @AfterClass
 public void tearDown()
 {
 	logger.info("************Verify Register Successful tests are complete************");
 }

}
