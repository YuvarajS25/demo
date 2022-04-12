package com.BookMyShowPage;

import java.io.File;


import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import com.BaseUD.BaseUd;
import com.ExcelReadWrite.ExcelReadWrite;
import com.aventstack.extentreports.Status;
import com.google.common.io.Files;


public class Movies extends BaseUd{

	By pageList=By.xpath("//a[@class='sc-iGPElx dYqNfN']");
	By name=By.xpath("//*[@id='super-container']/div[2]/div[4]/div[2]/div[2]/div");
	By movies=By.xpath("//a[@class='sc-iGPElx dYqNfN']");

	@Test(priority =8)
	public void selectMoviePage() throws Exception {
		exttest = report.createTest("Compare and Select the Movies page using Excel read");
		System.out.println("Extract languages for movies. ");
		wait(30, pageList);
		// //selecting the movies button
		// driver.findElement(movies).click();
		Actions action = new Actions(driver);
		List<WebElement> pages = driver.findElements(pageList);
		String m = ExcelReadWrite.readExcel(2, 0);
		c3 :
			for(int i=1;i<=pages.size();i++) {
				WebElement K = driver.findElement(movies);
				String s = K.getText();
				if(s.equalsIgnoreCase(m)) {
					action.moveToElement(K).click().perform();
					break c3;
				}
			};
			exttest.log(Status.PASS, "Selected Movies page Successfully");
	}
	
	@Test(priority =9)
	public void extractMovieLang() throws Exception {
		    exttest = report.createTest("Extract languages for movies.");
			WebElement names=driver.findElement(name);
			System.out.println(names.getText());
			exttest.log(Status.PASS, "Languages are extracted Successfully for different movies");
			TakesScreenshot capture = (TakesScreenshot) driver;
			File srcFile = capture.getScreenshotAs(OutputType.FILE);
			File destFile = new File(System.getProperty("user.dir")
					+ "/Screenshot/movies.png");
			Files.copy(srcFile, destFile);
			exttest.log(Status.PASS, "Captured Screenshot Successfully");
			System.out.println("Languages are extracted Successfully for different movies");
			
			System.out.println("=========================================================================");
			Thread.sleep(3000);
	}
}