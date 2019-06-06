http://demo.guru99.com/package selenium;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;import org.openqa.selenium.remote.server.handler.FindActiveElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_06_Xpath {
	WebDriver driver;
	By emailtxt = By.xpath("//input[@id='mail']");
	By ageunder18radio = By.xpath("//input[@id='under_18']");
	By educationtxt = By.xpath("//textarea[@id='edu']");
	By jobrole01 = By.xpath("//select[@id='job1']");

	public boolean Is_Enable(By x) {
		boolean element_check = driver.findElement(x).isEnabled();
		if (element_check) {
			System.out.println("Element is enabled");
		} else {
			System.out.println("Element is Disabled");
		}
		return element_check;
	}

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.get("https://daominhdam.github.io/basic-form/index.html");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC01() throws InterruptedException {
		if (driver.findElement(emailtxt).isDisplayed()) {
			driver.findElement(emailtxt).sendKeys("Automation testing");
		}
		if (driver.findElement(ageunder18radio).isDisplayed()) {
			driver.findElement(ageunder18radio).click();
		}
		if (driver.findElement(educationtxt).isDisplayed()) {
			driver.findElement(educationtxt).sendKeys("Automation testing");
		}
	}

	@Test
	public void TC02_CheckElementValue() throws InterruptedException {
		Assert.assertTrue(Is_Enable(emailtxt));
		Assert.assertTrue(Is_Enable(ageunder18radio));
		Assert.assertTrue(Is_Enable(educationtxt));
		Assert.assertTrue(Is_Enable(By.xpath("//select[@id='job1']")));
		Assert.assertTrue(Is_Enable(By.xpath("//input[@id='development']")));
		Assert.assertTrue(Is_Enable(By.xpath("//input[@id='slider-1']")));
		Assert.assertTrue(Is_Enable(By.xpath("//button[@id='button-enabled']")));
		Assert.assertFalse(Is_Enable(By.xpath("//input[@id='password']")));
		Assert.assertFalse(Is_Enable(By.xpath("//input[@id='radio-disabled']")));
		Assert.assertFalse(Is_Enable(By.xpath("//textarea[@id='bio']")));
		Assert.assertFalse(Is_Enable(By.xpath("//select[@id='job2']")));
		Assert.assertFalse(Is_Enable(By.xpath("//input[@id='check-disbaled']")));
		Assert.assertFalse(Is_Enable(By.xpath("//input[@id='slider-2']")));
		Assert.assertFalse(Is_Enable(By.xpath("//button[@id='button-disabled']")));
	}
	@Test
	public void TC03_CheckSelectedElement() throws InterruptedException{
		driver.findElement(By.xpath("//input[@id='under_18']")).click();
		driver.findElement(By.xpath("//input[@id='development']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//input[@id='under_18']")).isSelected());
		Assert.assertTrue(driver.findElement(By.xpath("//input[@id='development']")).isSelected());
		Thread.sleep(3000);
		driver.findElement(By.xpath("//input[@id='development']")).click();
		//vi da click vao develoment la dung nen click lai lan 2 la nguoc lai >> chung minh dieu do la sai
		Assert.assertFalse(driver.findElement(By.xpath("//input[@id='development']")).isSelected());
		
	}

	@AfterClass
	public void afterClass() {
	}

}
