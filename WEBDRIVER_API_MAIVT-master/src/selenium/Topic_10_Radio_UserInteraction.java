package selenium;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.sun.corba.se.spi.orbutil.fsm.Action;
import com.sun.glass.ui.Size;

import sun.misc.PerformanceLogger;

import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

public class Topic_10_Radio_UserInteraction {
	WebDriver driver;
	Actions action;
	Alert alert;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		action = new Actions(driver);
		// sau khi mo trang guru thi phai nhap ham doi
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	// @Test
	public void TC01_MoveMousetoElement() throws Exception {
		// Step 1: Truy cap trang http://live.guru99.com/
		driver.get("http://www.myntra.com/");
		// Step 2: Hover chuột vào menu đểlogin
		WebElement profiletext = driver.findElement(By.xpath("//span[text()='Profile']"));
		// note: sau action luon phai co perform()
		action.moveToElement(profiletext).perform();
		// step 3: click vao Login
		driver.findElement(By.xpath("//a[text()='log in']")).click();
		// step 4: veryfy form dc hien thi
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='login-box']")).isDisplayed());
	}

	// @Test
	public void TC02_ClickandHoldElement() throws Exception {
		// Step 1: Truy cap trang http://live.guru99.com/
		driver.get("http://jqueryui.com/resources/demos/selectable/display-grid.html ");
		// Khai bao bien theo list
		// Note: khai bao List thi phai la driver.findElements
		List<WebElement> numberItem = driver.findElements(By.xpath("//ol[@id='selectable']/li"));
		// step: click and hold tu 1 den 4
		action.clickAndHold(numberItem.get(0)).moveToElement(numberItem.get(3)).release().perform();
		// verfy khi da chon 4 phan tu do
		Assert.assertEquals(
				driver.findElements(By.xpath("//ol[@id='selectable']/li[contains(@class, 'ui-selected')]")).size(), 4);
	}

	//@Test
	public void TC03_ClickandSelectElement() throws Exception {
		// Step 1: Truy cap trang http://live.guru99.com/
		driver.get("http://jqueryui.com/resources/demos/selectable/display-grid.html ");
		List<WebElement> numberItem = driver.findElements(By.xpath("//ol[@id='selectable']/li"));
		// nhan giu ban phim control
		action.keyDown(Keys.CONTROL).perform();
		action.click(numberItem.get(1)).perform();
		action.click(numberItem.get(3)).perform();
		action.click(numberItem.get(6)).perform();
		action.click(numberItem.get(11)).perform();
		action.click(numberItem.get(9)).perform();
		// bo ban phim
		action.keyUp(Keys.CONTROL).perform();
		Assert.assertEquals(
				driver.findElements(By.xpath("//ol[@id='selectable']/li[contains(@class, 'ui-selected')]")).size(), 5);
	}
	
	//@Test
		public void TC04_DoubleClick() throws Exception {
			// Step 1: Truy cap trang http://live.guru99.com/
			driver.get("http://www.seleniumlearn.com/double-click");
			WebElement doubleclickme = driver.findElement(By.xpath("//button[text()='Double-Click Me!']"));
			action.doubleClick(doubleclickme).perform();
			//chuyen man hinh alert
			alert = driver.switchTo().alert();
			// khai bao bien alerttext
			String confirmttext = alert.getText();
			// kiem tra phan tu do la dung
			Assert.assertEquals(confirmttext, "The Button was double-clicked.");
			alert.accept();
		}
	
		//@Test
				public void TC05_RightClicktoElement() throws Exception {
					// Step 1: Truy cap trang http://live.guru99.com/
					driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");
					WebElement rightclickme = driver.findElement(By.xpath("//span[text()='right click me']"));
					//hover chuot vao right click me
					action.moveToElement(rightclickme).perform();
					//click chuot phai vao quit
					WebElement quittext = driver.findElement(By.xpath("//span[text()='Quit']"));
					action.moveToElement(quittext).perform();
					Assert.assertTrue(driver.findElement(By.xpath("//li[contains(@class, 'context-menu-hover') and contains(@class, 'context-menu-visible')]/span[text()='Quit']")).isDisplayed()); 
					driver.findElement(By.xpath("//span[text()='Quit']")).click();
					alert = driver.switchTo().alert();
					alert.accept();
				}
			@Test
				public void TC06_RightClicktoElement() throws Exception {
					// Step 1: Truy cap trang http://live.guru99.com/
					driver.get("http://demos.telerik.com/kendo-ui/dragdrop/angular");
					WebElement sourceElement = driver.findElement(By.xpath("//div[@id='draggable']"));
					WebElement TargetElement = driver.findElement(By.xpath("//div[@id='droptarget']"));
					((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView()", TargetElement);
					action.dragAndDrop(sourceElement, TargetElement).perform();
					Thread.sleep(3000);
					Assert.assertEquals(TargetElement.getText(), "You did great!");
				}
			
				
	// @AfterClass
	public void afterClass() {
		driver.quit();
	}

}
