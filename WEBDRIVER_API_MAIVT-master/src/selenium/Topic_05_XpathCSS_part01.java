package selenium;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import static org.junit.Assert.assertEquals;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_05_XpathCSS_part01 {
	WebDriver driver;
	String randomEmail;
	
	public int randomData() {
		Random random = new Random();
		return random.nextInt(999999);
	}

	@BeforeClass
	public void beforeClass() {
		// mo trinh duyet firefox
		// update lan 2
		driver = new FirefoxDriver();
		// Mo trang guru len
		driver.get("http://live.guru99.com/index.php/customer/account/login/");
		//sau khi mo trang guru thi phai nhap ham doi
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		//day la locator mo muc My Account khi da loc tu 2 matching node
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		randomEmail = "MaiVT" + randomData() + "@gmail.com";
	}
	

	@Test
	public void TC01_EmptyUserandPass() throws Exception {
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("");
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("");
		driver.findElement(By.xpath("//button[@id='send2']")).click();
		// gettext : lay cai text o vi tri nay
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='advice-required-entry-email']")).getText(),"This is a required field.");
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='advice-required-entry-pass']")).getText(),"This is a required field.");
		Thread.sleep(3000);
	}
	
	@Test
	public void TC02_EmptyUserandPass() throws InterruptedException {
		driver.findElement(By.xpath("//input[@id='email']")).clear();
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("vuthanhmai1909@123.123");
		driver.findElement(By.xpath("//input[@id='pass']")).clear();
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("");
		driver.findElement(By.xpath("//button[@id='send2']")).click();
		// gettext : lay cai text o vi tri nay
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='advice-validate-email-email']")).getText(),"Please enter a valid email address. For example johndoe@domain.com.");
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='advice-required-entry-pass']")).getText(),"This is a required field.");
		Thread.sleep(3000);
	}
	@Test
	public void TC03_EmptyUserandPass() throws InterruptedException {
		driver.findElement(By.xpath("//input[@id='email']")).clear();
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("automation@gmail.com");
		driver.findElement(By.xpath("//input[@id='pass']")).clear();
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("123");
		driver.findElement(By.xpath("//button[@id='send2']")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='advice-validate-password-pass']")).getText(), "Please enter 6 or more characters without leading or trailing spaces.");
		Thread.sleep(3000);
	}
	
	
	@Test
	public void TC04_CreateAccount() throws InterruptedException {
		driver.findElement(By.xpath("//span[text() = 'Create an Account']")).click();
		driver.findElement(By.xpath("//input[@id='firstname']")).sendKeys("Vu");
		driver.findElement(By.xpath("//input[@id='middlename']")).sendKeys("Thi");
		driver.findElement(By.xpath("//input[@id='lastname']")).sendKeys("Mai");
		driver.findElement(By.xpath("//input[@id='email_address']")).sendKeys(randomEmail);
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys("123456");
		driver.findElement(By.xpath("//input[@id='confirmation']")).sendKeys("123456");
		driver.findElement(By.xpath("//form[@id='form-validate']/div[2]/button")).click();
		Thread.sleep(3000);   
		Assert.assertEquals(driver.findElement(By.xpath("//span[text() = 'Thank you for registering with Main Website Store.']")).getText(), "Thank you for registering with Main Website Store.");
		Thread.sleep(3000);
		driver.findElement(By.xpath("//div[@class='page-header-container']//div[@class='account-cart-wrapper']//span[text() = 'Account']")).click();
		driver.findElement(By.xpath("//a[@title='Log Out']")).click();
		Thread.sleep(000);
		Assert.assertEquals(driver.getTitle(), "Magento Commerce");
	}

	@AfterClass
	public void afterClass() {
		//driver.quit();
	}

}
