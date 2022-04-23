package com.restAssured.Task1Tests;


import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.Method;

/*Tests to validate List Resource GET API
 * Test if data is returned
 * Test for specific id and resource
 */
public class Scen_ListResource extends baseClass {

	@BeforeTest
	void getListOfResource() {

		RestAssured.baseURI = "https://reqres.in/api/";
		// Request Object
		httpRequest = RestAssured.given();
		

	}

	@Test
	void validateResponse() throws IOException, ParseException {
		// Response Object
		response = httpRequest.request(Method.GET, "/unknown");
		// print response in console
		//String responseBody = response.getBody().asString();
		FileWriter file = new FileWriter("./outputpayload.json");
		logger.info("************Displaying Resource list************");
		file.write(response.getBody().prettyPrint());
		file.flush();
		file.close();

		logger.info("************Checking for specific resource(id+name combination) in response************");
		String checkName = "aqua sky";
		int checkID = 4;
		JSONParser jsonparser = new JSONParser();
		FileReader reader = new FileReader(".\\outputpayload.json");
		Object obj= jsonparser.parse(reader);
		JSONObject resourceObj=(JSONObject)obj;
		JSONArray dataArray =(JSONArray)resourceObj.get("data");
		Boolean result = null;
		for(int i=0;i<dataArray.size();i++)
		{
			JSONObject data = (JSONObject)dataArray.get(i);
			if (String.valueOf(data.get("id")).equals(String.valueOf(checkID))) {
				
					if((String.valueOf(data.get("name")).equals(checkName)))
			{
				result = true;
				System.out.println("Expected resource with id = "+checkID+ " and name = "+checkName +" is present in Resource List");
			    break;
			    
			 }
			}
			else{result=false;}	
		}
		if(!result) {
			System.out.println("Expected resource with id = "+checkID+ " and name = "+checkName +" is NOT present in Resource List");
		}
		Assert.assertTrue(result);	
	}
	@Test
	void validateStatusCode() {
		logger.info("************Checking status code returned************");
		int statuscode = response.getStatusCode();
		Assert.assertEquals(statuscode, 200);
		System.out.println("Actual Status code is as expected");
		logger.info("************List Resource test is complete************");
	}
}
