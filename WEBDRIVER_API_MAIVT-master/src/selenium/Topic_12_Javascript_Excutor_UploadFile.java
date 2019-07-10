package selenium;





import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.util.List;
import java.util.Random;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.thoughtworks.selenium.webdriven.commands.KeyEvent;

public class Topic_12_Javascript_Excutor_UploadFile {
	WebDriver driver;
	String randomEmail;
	String root_Folder_Path, image_01_Path, image_02_Path, image_03_Path;
	String image_01_Name = "v1.jpg";
	String image_02_Name = "v2.jpg";
	String image_03_Name = "v3.jpg";
	String chromePath;
	String firefoxPath;
	
	@BeforeClass
	public void beforeClass() {
		//driver = new FirefoxDriver();
		System.setProperty("webdriver.chrome.driver", ".\\lib\\chromedriver.exe");
		driver = new ChromeDriver();
		//driver = new FirefoxDriver();
		// sau khi mo trang guru thi phai nhap ham doi
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		randomEmail = "vuthanhmai" + randomData() + "@gmail.com";
		root_Folder_Path = System.getProperty("user.dir");
		System.out.println(root_Folder_Path);
		chromePath = root_Folder_Path + "\\uploadFile\\chrome.exe" ;
		firefoxPath = root_Folder_Path + "\\uploadFile\\firefox.exe" ;
		image_01_Path = root_Folder_Path + "\\UploadFile\\" + image_01_Name;
		image_02_Path = root_Folder_Path + "\\UploadFile\\" + image_02_Name;
		image_03_Path = root_Folder_Path + "\\UploadFile\\" + image_03_Name;
	}

	public int randomData() {
    	Random random = new Random();
		return random.nextInt(999999);
	}
	
	public Object navigateToUrlByJs (String url) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		return js.executeScript("window.location = '"+ url +"'");
	}
	public Object executorForBrowser(String javascript) {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		return js.executeScript(javascript);
	}
	public Object clickToElementByJs(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		return js.executeScript("arguments[0].click()",element);
	}
	public Object scrollToBottomPage() {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		return js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}
	public Object scrollToElement(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		return js.executeScript("arguments[0].scrollIntoView(true)", element);
	}
	public Object sendkeyToElementByJs( WebElement element, String value) {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		return js.executeScript("arguments[0].setAttribute('value','"+ value + "')", element);
	}
	public void highlightElement(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		String originalStyle = element.getAttribute("style");
		js.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])",element,"style","border: 5px solid yellow; border-style: dashed;");
		try {
			Thread.sleep(500);
		}
		catch(InterruptedException e){
			e.printStackTrace();
		}
		js.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])",element,"style",originalStyle);
	}
	public Object removeAttributeInDOM(WebElement element, String attribute) {
		JavascriptExecutor js =(JavascriptExecutor)driver;
		return js.executeScript("arguments[0].removeAttribute('"+ attribute + "')", element);
	}
	
	//@Test
	public void TC01_HDFCBank() throws Exception {
		// Step 1: Truy cap trang http://live.guru99.com/ su dung JE
		navigateToUrlByJs("http://live.guru99.com/");
		
		//step 2: Su dung JE de get domain cua page
		String homepageDomain = (String) executorForBrowser("return domain.url");
		//veryfy domain URL
		Assert.assertEquals(homepageDomain, "live.guru99.com");
		
		//step 3: su dung JE de get URL cua page
		String homepageURL = (String) executorForBrowser("return document.URL");
		//Veryfy geT URL
		Assert.assertEquals(homepageURL, "http://live.guru99.com/");
		
		//step 4: open mobile page su dung JE
		WebElement mobileElement = driver.findElement(By.xpath("//a[text()='Mobile']"));
		//highlight khi click vao Mobile
		highlightElement(mobileElement);
		//click vao phan tu Mobile
		clickToElementByJs(mobileElement);
		
		//step 5: add san pham Samsung SONY EXPERA vao Cart(click to ADD TO CART button bang JE)
		WebElement addtocart = driver.findElement(By.xpath("//a[text()='Sony Xperia']//parent::h2//following-sibling::div[@class='actions']//button"));
		//highlight khi click vao addtocart
		highlightElement(addtocart);
		//click vao phan tu addtocart
		clickToElementByJs(addtocart);
		
		//Step 6: veryfy message dc hien thi
		String messageSony = (String) executorForBrowser("return document.documentElement.innerText");
		Assert.assertTrue(messageSony.contains("Sony Xperia was added to your shopping cart."));
		
		//step 7: open Privacy policy
		clickToElementByJs(driver.findElement(By.xpath("//a[text()='Privacy Policy']")));
		String titlePrivacy = (String) executorForBrowser("return document.title");
		Assert.assertEquals(titlePrivacy, "Privacy Policy");
		
		//step 8: scroll xuong page
		scrollToBottomPage();
		
		//step 9: veryfy du lieu co hien thi voi 1 xpath
		WebElement wishlisttext = driver.findElement(By.xpath("//th[text()='WISHLIST_CNT']//following-sibling::td[text()='The number of items in your Wishlist.']"));
		Assert.assertTrue(wishlisttext.isDisplayed());
		  
		//step 10: navigative toi http://demo.guru99.com/v4/
		
	}
	
	
		//@Test
		public void TC02_UploadFile_Senkeys() throws Exception {
			driver.get("http://blueimp.github.io/jQuery-File-Upload/");
			//Step 2: senkeys
			WebElement uploadFile = driver.findElement(By.xpath("//input[@type='file']"));
			
			//su ly duong dan tuong doi de chay qua may khac
			//uploadfile nhieu file 1 luc
			uploadFile.sendKeys(image_01_Path+"\n" + image_02_Path+"\n" + image_03_Path);
			//Doi 3 file load success
			List <WebElement> fileload = driver.findElements(By.xpath("//tbody[@class='files']//p[@class='name']"));
			for (WebElement file: fileload) {
				System.out.println(file.getText());
				Assert.assertTrue(file.isDisplayed());
			}
			
			//Step 4: click start button de upload cho ca 3 file
			List <WebElement> startbutton = driver.findElements(By.xpath("//tbody[@class='files']//button[@class='btn btn-primary start']"));
			for(WebElement start: startbutton) {
				//click bang javascript do bi che quang cao
				clickToElementByJs(start);
				Thread.sleep(2000);
			}
			
			//step 5: veryfy 3 file da upload
			List <WebElement> fileuploaded = driver.findElements(By.xpath("//tbody[@class='files']//p[@class='name']/a"));
			for(WebElement fileupload: fileuploaded) {
				Assert.assertTrue(fileupload.isDisplayed());
			}
			Assert.assertTrue(driver.findElement(By.xpath("//tbody[@class='files']//a[text()='v1.jpg']")).isDisplayed());
			Assert.assertTrue(driver.findElement(By.xpath("//tbody[@class='files']//a[text()='v2.jpg']")).isDisplayed());
			Assert.assertTrue(driver.findElement(By.xpath("//tbody[@class='files']//a[text()='v3.jpg']")).isDisplayed());
		}
		
		//@Test
		public void TC03_UploadFile_AutoIT() throws Exception {
			driver.get("http://blueimp.github.com/jQuery-File-Upload/");
			//Step 2: su dung AutoIT de upload file chay cho 2 trinh duỵet
			WebElement addFileButton = driver.findElement(By.cssSelector(".fileinput-button"));
			//click bang javascript do bi che quang cao
			addFileButton.click();
			//clickToElementByJs(addFileButton);
			Thread.sleep(2000);
			if(driver.toString().contains("chrome")) {
				Runtime.getRuntime().exec(new String[] {chromePath, image_01_Path});
			}
				Runtime.getRuntime().exec(new String[] {firefoxPath, image_01_Path});
			Assert.assertTrue(driver.findElement(By.xpath("//tbody[@class='files']//p[@class='name' and text()='"+ image_01_Name+ "']")).isDisplayed());
			Thread.sleep(2000);
		}
		//copy 2 file chrome.exe và firefox.exe vào mục uploadfile

		@Test
		public void TC04_UploadFile_AutoIT() throws Exception {
			driver.get("http://blueimp.github.com/jQuery-File-Upload/");
			//Step 2: su dung AutoIT de upload file chay cho 2 trinh duỵet
			WebElement addFileButton = driver.findElement(By.cssSelector(".fileinput-button"));
			addFileButton.click();
			//clickToElementByJs(addFileButton);
			Thread.sleep(2000);
			
			StringSelection select = new StringSelection(image_01_Path);
			//copy clipboard
			Toolkit.getDefaultToolkit().getSystemClipboard().setContents(select, null);
			
			//thao tac voi desktop app qua lib awt
			Robot robot = new Robot();
			Thread.sleep(2000);
			
			//nhan phim enter (focus chuot va input file)
			robot.keyPress(java.awt.event.KeyEvent.VK_ENTER);
			robot.keyRelease(java.awt.event.KeyEvent.VK_ENTER);
			
			//Nhan ctrl V
			robot.keyPress(java.awt.event.KeyEvent.VK_CONTROL);
			robot.keyPress(java.awt.event.KeyEvent.VK_V);
			
			//Nha ctrl V
			robot.keyRelease(java.awt.event.KeyEvent.VK_CONTROL);
			robot.keyRelease(java.awt.event.KeyEvent.VK_V);
			
			//Nhan enter (open file)
			robot.keyPress(java.awt.event.KeyEvent.VK_ENTER);
			robot.keyRelease(java.awt.event.KeyEvent.VK_ENTER);
			
			Assert.assertTrue(driver.findElement(By.xpath("//tbody[@class='files']//p[@class='name' and text()='"+ image_01_Name+ "']")).isDisplayed());
			Thread.sleep(2000);
		}
		
	// @AfterClass
	public void afterClass() {
		driver.quit();
	}

}
