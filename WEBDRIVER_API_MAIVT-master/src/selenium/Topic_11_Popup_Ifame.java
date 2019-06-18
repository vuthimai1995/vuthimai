package selenium;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.sun.corba.se.spi.orbutil.fsm.Action;
import com.sun.glass.ui.Size;

import sun.misc.PerformanceLogger;

import java.util.List;
import java.util.Set;
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

public class Topic_11_Popup_Ifame {
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
	public void TC01_HDFCBank() throws Exception {
		// Step 1: Truy cap trang https://www.hdfcbank.com
		driver.get("https://www.hdfcbank.com");
		//step 2: close popup
		driver.findElement(By.xpath("//div[@id='parentdiv']/img[@class='popupCloseButton']")).click();
		//starts - with la lay doan text tuong doi voi dang abcxxxxx (xxx la ngau nhien)
		WebElement lookingforIframe = driver.findElement(By.xpath("//iframe[starts-with(@id, 'viz_iframe')]"));
		//swich to den iframe
		driver.switchTo().frame(lookingforIframe);
		//step 3: veryfy looking for 
		Assert.assertEquals(driver.findElement(By.xpath("//span[@id='messageText']")).getText(), "What are you looking for?");
		// switch ve default mac dinh
		driver.switchTo().defaultContent();
		//step 4: veryfy banner co 6 images
		List<WebElement> rightbannerImg = driver.findElements(By.xpath("//div[@id='rightbanner']/div[1]//div[@class='owl-item']"));
		Assert.assertEquals(rightbannerImg.size(), 6);
		//step 5: veryfy flipper banner
		List<WebElement> flipper = driver.findElements(By.xpath("//div[@class='flipBanner']//img[@class='front icon']"));
		Assert.assertEquals(flipper.size(), 8);
	}
	
	public void switchToChilWindowByID(String parent) {
    	Set <String> allwindowns = driver.getWindowHandles();
		for(String runWindown : allwindowns) {
			if(!runWindown.equals(parent)) {
				driver.switchTo().window(runWindown);
				break;
			}
		}
	}
	public void switchToWindownByTitle (String title) {
		Set <String> allWindown = driver.getWindowHandles();
		for (String runWindown : allWindown) {
			driver.switchTo().window(runWindown);
			String curentWin = driver.getTitle();
			if(curentWin.equals(title)) {
				break;
			}
		}
	}
	public boolean closeAllWindownWithoutParent(String ParentID) {
		Set <String> allWindown = driver.getWindowHandles();
		for (String runWindown : allWindown) {
			if(!runWindown.equals(ParentID)) {
				driver.switchTo().window(runWindown);
				driver.close();
			}
		}
		driver.switchTo().window(ParentID);
		if(driver.getWindowHandles().size()==1) {
			return true;
		}
		else {
			return false;
		}
	}
	
	//@Test
		public void TC02_switchToWindownByTitle() throws Exception {
			// Step 1: Truy cap trang https://daominhdam.github.io/basic-form/index.html
			driver.get("https://daominhdam.github.io/basic-form/index.html");
			String parentID = driver.getWindowHandle();
			System.out.println("Parent ID = "+parentID);
			//step 2: click vao google
			driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
			Thread.sleep(3000);
			//switch qua tab moi 
			switchToChilWindowByID(parentID);
			//step 3: kiem tra title cua window moi
			Assert.assertEquals(driver.getTitle(), "Google");
			//switch ve mac dinh
			driver.switchTo().window(parentID);
			
			driver.findElement(By.xpath("//a[text()='FACEBOOK']")).click();
			Thread.sleep(3000);
			//switch qua tab moi 
			switchToWindownByTitle(parentID);
			//step 3: kiem tra title cua window moi
			Assert.assertEquals(driver.getTitle(), "Facebook - Đăng nhập hoặc đăng ký");
			//switch ve mac dinh
			driver.switchTo().window(parentID);
			
			driver.findElement(By.xpath("//a[text()='TIKI']")).click();
			Thread.sleep(3000);
			//switch qua tab moi 
			switchToWindownByTitle(parentID);
			//step 3: kiem tra title cua window moi
			Assert.assertEquals(driver.getTitle(), "Mua Hàng Trực Tuyến Uy Tín với Giá Rẻ Hơn tại Tiki.vn");
			
			//step 10: close tat ca cac cua so window
			closeAllWindownWithoutParent(parentID);
			
			//step 11: kiem tra da quay ve parentID thanh cong
			Assert.assertEquals(driver.getTitle(), "SELENIUM WEBDRIVER FORM DEMO");
		}
			
		@Test
		public void TC03_switchToWindownByTitle() throws Exception {
			// Step 1: Truy cap trang https://www.hdfcbank.com
			driver.get("https://www.hdfcbank.com");
			String parentID = driver.getWindowHandle();
			System.out.println("Parent ID = "+parentID);
			//step 2: close popup
			driver.findElement(By.xpath("//div[@id='parentdiv']/img[@class='popupCloseButton']")).click();
			
			//step 3: click Agri link
			driver.findElement(By.xpath("//a[text()='Agri']")).click();
			switchToWindownByTitle(parentID);
			
			//step 4: click Account detail link
			driver.findElement(By.xpath("//p[text()='Account Details']")).click();
			switchToWindownByTitle(parentID);
			
			WebElement PrivacyPolicyFrame = driver.findElement(By.xpath("//frame[@name='footer']"));
			//swich to den iframe
			driver.switchTo().frame(PrivacyPolicyFrame);
			//step 5: click privacy policy link
			driver.findElement(By.xpath("//a[text()='Privacy Policy']")).click();
			switchToWindownByTitle(parentID);
			
			//step 5: click CSR link
			driver.findElement(By.xpath("//a[text()='CSR']")).click();
			
			//step 10: close tat ca cac cua so window
			closeAllWindownWithoutParent(parentID);
			driver.switchTo().window(parentID);
		}
		
		
	// @AfterClass
	public void afterClass() {
		driver.quit();
	}

}
