package selenium;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import static org.junit.Assert.assertArrayEquals;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;

public class Topic_01_CheckEnvironment {

	WebDriver driver;

	// precondition
	@BeforeClass
	public void beforeClass() {
		// mo browser len
		//update
		driver = new FirefoxDriver();
		// mo app len
		driver.get("https://www.guru99.com/");
		//wait element visible in 30s
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void TC_01_Checkurl() {
		//kiem tra url cua trang co dung la https://guru99.com/ hay khong
		String homePageUrl = driver.getCurrentUrl();
		System.out.print("Home page title = " + driver.getTitle());
		Assert.assertEquals(homePageUrl, "https://www.guru99.com/");
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
		
	}
	

}
