package selenium;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;

public class Topic_09_Radio_CheckBox_Alert {
	WebDriver driver;
	JavascriptExecutor javaExcutor;
	Alert alert;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		javaExcutor = (JavascriptExecutor) driver;
		// sau khi mo trang guru thi phai nhap ham doi
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	// @Test
	public void TC01_CustomerDropdown() throws Exception {
		// Step 1: Truy cap trang http://live.guru99.com/
		driver.get("http://live.guru99.com/");
		// Step 2: cach 1: Click My account
		// driver.findElement(By.xpath("//div[@class='footer']//a[@title='My
		// Account']")).click();
		// cach 2: click theo javascript
		javaExcutor.executeScript("arguments[0].click()",
				driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")));
		// Step 3: Check url page
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.guru99.com/index.php/customer/account/login/");
		// Step 4: Click Create an Account button
		driver.findElement(By.xpath("//span[text()='Create an Account']")).click();
		// Step 5: Check url page
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.guru99.com/index.php/customer/account/create/");
	}

	// @Test
	public void TC02_CheckBox() throws Exception {
		// Step 1: Truy cap trang http://demos.telerik.com/kendo-ui/styling/checkboxes
		driver.get("http://demos.telerik.com/kendo-ui/styling/checkboxes");
		// khai báo ham dualCheckbox
		WebElement dualCheckbox = driver
				.findElement(By.xpath("//label[text()='Dual-zone air conditioning']//preceding-sibling::input"));
		// Click bằng javascipt
		javaExcutor.executeScript("arguments[0].click()", dualCheckbox);
		Assert.assertTrue(dualCheckbox.isSelected());
		Thread.sleep(3000);
		javaExcutor.executeScript("arguments[0].click()", dualCheckbox);
		Assert.assertFalse(dualCheckbox.isSelected());
	}

	// @Test
	public void TC03_RadioButton() throws Exception {
		// Step 1: Truy cap trang http://demos.telerik.com/kendo-ui/styling/checkboxes
		driver.get("http://demos.telerik.com/kendo-ui/styling/radios");
		WebElement PetrolCheckbox = driver
				.findElement(By.xpath("//label[text()='2.0 Petrol, 147kW']/preceding-sibling::input"));
		// Click bằng javascipt
		javaExcutor.executeScript("arguments[0].click()", PetrolCheckbox);
		if (PetrolCheckbox.isSelected()) {
			System.out.print("Value is selected");
		} else {
			javaExcutor.executeScript("arguments[0].click()", PetrolCheckbox);
		}
	}

	// @Test
	public void TC04_AcceptAlert() throws Exception {
		// Step 1: Truy cap trang https://daominhdam.github.io/basic-form/index.html
		driver.get("https://daominhdam.github.io/basic-form/index.html");
		driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
		// chuyển sang màn hình của Alert
		alert = driver.switchTo().alert();
		// khai bao bien alerttext
		String alerttext = alert.getText();
		// kiem tra phan tu do la dung
		Assert.assertEquals(alerttext, "I am a JS Alert");
		// click vao alert do
		alert.accept();
		Assert.assertEquals(driver.findElement(By.xpath("//p[@id='result']")).getText(),
				"You clicked an alert successfully");

	}

	// @Test
	public void TC05_ConfirmAlert() throws Exception {
		// Step 1: Truy cap trang https://daominhdam.github.io/basic-form/index.html
		driver.get("https://daominhdam.github.io/basic-form/index.html");
		driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
		// chuyển sang màn hình của Alert
		alert = driver.switchTo().alert();
		// khai bao bien alerttext
		String confirmttext = alert.getText();
		// kiem tra phan tu do la dung
		Assert.assertEquals(confirmttext, "I am a JS Confirm");
		// click vao alert do
		alert.dismiss();
		Assert.assertEquals(driver.findElement(By.xpath("//p[@id='result']")).getText(), "You clicked: Cancel");
	}

	@Test
	public void TC06_PromtAlert() throws Exception {
		// Step 1: Truy cap trang https://daominhdam.github.io/basic-form/index.html
		driver.get("https://daominhdam.github.io/basic-form/index.html");
		driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
		// chuyển sang màn hình của Alert
		alert = driver.switchTo().alert();
		// khai bao bien alerttext
		String promptttext = alert.getText();
		Assert.assertEquals(promptttext, "I am a JS prompt");
		alert.sendKeys("daominhdam");
		alert.accept();
		Assert.assertEquals(driver.findElement(By.xpath("//p[@id='result']")).getText(), "You entered: daominhdam");
	}

	// @AfterClass
	public void afterClass() {
		driver.quit();
	}

}
