package selenium;
import org.testng.annotations.Test;

import com.thoughtworks.selenium.webdriven.commands.GetText;

import org.testng.annotations.BeforeClass;

import static org.junit.Assert.assertEquals;

import java.beans.Customizer;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.server.handler.SendKeys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_09_Radio_CheckBox_Alert {
	WebDriver driver;
	
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		//sau khi mo trang guru thi phai nhap ham doi
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	
	@Test
	public void TC03_CustomerDropdown() throws Exception {
			// Step 1: Truy cap trang http://live.guru99.com/
			driver.get("http://live.guru99.com/");
			// Step 2: Click My account
			driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
			// Step 3: Check url page
			Assert.assertEquals(driver.getCurrentUrl(), "http://live.guru99.com/index.php/customer/account/login/");
			//Step 4: Click Create an Account button
			driver.findElement(By.xpath("//span[text()='Create an Account']")).click();
			//Step 5: Check url page
			Assert.assertEquals(driver.getCurrentUrl(), "http://live.guru99.com/index.php/customer/account/create/");
	}
	
	
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
