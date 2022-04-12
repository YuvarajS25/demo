package com.BookMyShowPage;

import java.io.File;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.Test;

import com.BaseUD.BaseUd;
import com.aventstack.extentreports.Status;
import com.google.common.io.Files;

public class SignIn extends BaseUd{

	By sign=By.xpath("//div[text()='Sign in']");
	By google=By.xpath("//*[@id=\"modal-root\"]/div/div/div/div/div[2]/div/div[1]/div/div[1]/div/div");
	By email=By.xpath("//*[@id=\"identifierId\"]");
	By next=By.xpath("//span[contains(text(),'Next')]");
	By error1=By.xpath("//div[@class='o6cuMc']");
	
	@Test(priority =4)
	public void signin() throws InterruptedException, IOException {
		exttest = report.createTest("Selected Signup. ");
		wait(30, sign);
		System.out.println("Display error during invalid Signup. ");
		driver.findElement(sign).click();
		Thread.sleep(3500);
		// parent child window handling 
		String mainWindow=driver.getWindowHandle();
		driver.findElement(google).click();
        exttest.log(Status.PASS, "SignUp Page is obtained Successfully");
		Set<String> set =driver.getWindowHandles();
		Iterator<String> itr= set.iterator();
		while(itr.hasNext()){
			String childWindow=itr.next();
			if(!mainWindow.equals(childWindow)){
				// for switching to the child window 
				exttest = report.createTest("Child Window Handling. ");
				driver.switchTo().window(childWindow);
				exttest.log(Status.PASS, "Child Window Handling is obtained Successfully");
				driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
				driver.findElement(email).sendKeys(prop.getProperty("email"));
				driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
				driver.findElement(next).click();
				Thread.sleep(3000);
				driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
				//capture the error message 
//2. class				
			    String error=driver.findElement(error1).getText();
				driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
			    System.out.println("Error is: "+error);
			    exttest.log(Status.PASS, "Error is obtained Successfully");
//3. ss class 			    
				TakesScreenshot capture = (TakesScreenshot) driver;
				File srcFile = capture.getScreenshotAs(OutputType.FILE);
				File destFile = new File(System.getProperty("user.dir")
						+ "/Screenshot/Invalid Signup Error.png");
				Files.copy(srcFile, destFile);
				exttest.log(Status.PASS, "Screenshot taken Successfully");
				System.out.println("=========================================================================");
				driver.close();
				driver.quit();
			}

		}
	}
}
