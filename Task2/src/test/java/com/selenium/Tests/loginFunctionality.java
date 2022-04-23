package com.selenium.Tests;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;
import com.selenium.pageObjects.LoginPage;


public class loginFunctionality extends baseClass {
	@Test(dataProvider = "LoginData")
	public void loginTest(String username, String password) throws InterruptedException {

		driver.get(baseURL);
		driver.manage().deleteAllCookies();
	

		logger.info("************NEW TEST: Login to Yahoo.com************");
		LoginPage lp = new LoginPage(driver);
		if (driver.findElements(By.xpath("//button[contains(text(),'Accept all')]")).size() != 0) {
			driver.findElement(By.xpath("//button[contains(text(),'Accept all')]")).click();
		}
		lp.clickSignIn();
		Thread.sleep(4000);
		lp.setUserName(username);
		Thread.sleep(3000);
		lp.clickNext();
		Thread.sleep(3000);
		if (driver.findElements(By.id("username-error")).size() != 0) {
			System.out.println(username + " is not valid credential");
			logger.info("************Login Not Successful************");
		}

		else {
			lp.setPassword(password);
			lp.clickNext();
			String actualTitle = "Yahoo UK";
			String expectedTitle = driver.getTitle();
			Assert.assertTrue(expectedTitle.contains(actualTitle));
			logger.info("************Login Successful************");
			System.out.println(username + " Login Successful");
			// Navigate to Finance Tab
			lp.selectFinTab();
			logger.info("************Navigation to Finance tab successful************");
			Thread.sleep(3000);
			// Navigate to marketData Tab
			Actions action = new Actions(driver);
			WebElement marketData = driver.findElement(By.xpath("//div[@class='Pos(r) Z(1)']//descendant::li[2]"));
			action.moveToElement(marketData).click().perform();
			Thread.sleep(3000);
			logger.info("************Navigation to MarketData tab Successful************");

			// Navigate to Calendar Tab
			WebElement calendar = driver.findElement(By.xpath("//a[@title=\"Calendar\"]"));
			action.moveToElement(calendar).click().perform();
			Thread.sleep(3000);
			logger.info("************Navigation to Calendar tab Successful************");

			// Select next in calendar
			driver.findElement(By.xpath("//a[@title='Next']")).click();
			Thread.sleep(4000);
			logger.info("************Navigate to next in calendar************");

			logger.info("************Get list of elements Displyed for 27th April Date************");
			String str = "";
			int i = 1;
			try {
				while (driver.findElement(By.xpath(
						"//span[contains(text(),27)]//parent::div[1]//following-sibling::a[" + i + "]")) != null) {

					str = driver
							.findElement(By.xpath(
									"//span[contains(text(),27)]//parent::div[1]//following-sibling::a[" + i + "]"))
							.getText();
					System.out.println(str);
					i++;
				}
			} catch (WebDriverException e) {

				logger.info("************End of List************");

				// Signout of the account

				WebElement profile = driver.findElement(By.xpath("//button[@id=\"uh-profile\"]"));
				action.moveToElement(profile).click().perform();
				Thread.sleep(4000);

				WebElement signoutBtn = driver.findElement(By.xpath("//*[@id=\"uh-signedout\"]"));
				action.moveToElement(signoutBtn).click().perform();

				lp.clickSignIn();
				Thread.sleep(2000);
				driver.findElement(By.xpath("//div[@class='card-right']")).click();
				Thread.sleep(2000);
				driver.findElement(By.xpath("//div[@class='card-menu hide']")).click();

			}

		}
		logger.info("************End of Test************");
	}

}
