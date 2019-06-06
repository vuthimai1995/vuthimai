package selenium;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_05_XpathCSS_bai02 {
	WebDriver driver;
	
	@BeforeClass
	public void beforeClass() {
		// mo trinh duyet firefox
		// update lan 2
		driver = new FirefoxDriver();
		// Mo trang guru len
		driver.get("http://live.guru99.com/index.php/customer/account/login/");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	
	@Test
	public void TC01_EmptyUserandPass() {
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("vuthanhmai1909@gmail.com");
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("123");
		driver.findElement(By.xpath("//button[@id='send2']")).click();
		// gettext : lay cai text o vi tri nay
		//Assert.assertEquals(driver.findElement(By.xpath("//div[@id='advice-validate-email-email']")).getText(),"Please enter a valid email address. For example johndoe@domain.com.");
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='advice-validate-password-pass']")).getText(),"Please enter 6 or more characters without leading or trailing spaces.");
	}

	@AfterClass
	public void afterClass() {
	}

}
