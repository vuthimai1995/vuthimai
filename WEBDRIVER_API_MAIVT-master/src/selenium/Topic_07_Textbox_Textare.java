package selenium;
import org.testng.annotations.Test;

import com.thoughtworks.selenium.webdriven.commands.GetText;

import org.testng.annotations.BeforeClass;

import static org.junit.Assert.assertEquals;

import java.beans.Customizer;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.server.handler.SendKeys;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_07_Textbox_Textare {
	WebDriver driver;
	
	By customernametxt = By.xpath("//input[@name='name']");
	By genderadiobutton = By.xpath("//input[@value='m']");
	By dateofbirthtxt = By.xpath("//input[@name='dob']");
	By addresstxtarea = By.xpath("//textarea[@name='addr']");
	By citytxt = By.xpath("//input[@name='city']");
	By statetxt = By.xpath("//input[@name='state']");
	By pintxt = By.xpath("//input[@name='pinno']");
	By mobilenumbertxt = By.xpath("//input[@name='telephoneno']");
	By emailtxt = By.xpath("//input[@name='emailid']");
	By passtxt = By.xpath("//input[@name='password']");
	
	public int random() {
		Random random = new Random();
		return random.nextInt(999999);
	}
	
	String customername = "Vu Thi Mai";
	String dateofbirth = "1995-10-08";
	String address = "124 Hoang Quoc Viet";
	String city = "Ha Noi";
	String state = "Cau Giay";
	String pin = "123123";
	String mobilenumber = "0334388386";
	String pass = "123456";
	String randomEmail = "vuthanhmai"+random()+"@gmail.com";
	
	@BeforeClass
	public void beforeClass() {
		// mo trinh duyet firefox
		// update lan 2
		driver = new FirefoxDriver();
		// Mo trang guru len
		driver.get("http://demo.guru99.com/v4/");
		//sau khi mo trang guru thi phai nhap ham doi
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
	}
	
	@Test
	public void TC01_NewCustomer() throws Exception {
		//dien thong tin dang nhap
		driver.findElement(By.xpath("//input[@name='uid']")).sendKeys("mngr139454");
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("qwerty1!");
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//marquee[@class='heading3']")).getText(),"Welcome To Manager's Page of Guru99 Bank");
		driver.findElement(By.xpath("//a[text()='New Customer']")).click();
		//dien thong tin tai khoan
		driver.findElement(customernametxt).sendKeys("Vu Thi Mai");
		driver.findElement(genderadiobutton).click();
		driver.findElement(dateofbirthtxt).sendKeys("1995-10-08");
		driver.findElement(addresstxtarea).sendKeys("124 Hoang Quoc Viet");
		driver.findElement(citytxt).sendKeys("Ha Noi");
		driver.findElement(statetxt).sendKeys("Cau Giay");
		driver.findElement(pintxt).sendKeys("123123");
		driver.findElement(mobilenumbertxt).sendKeys("0334388386");
		driver.findElement(passtxt).sendKeys("123456");
		//khai bao email random
		driver.findElement(emailtxt).sendKeys(randomEmail);
		//Sau khi nhap thong tin thi nhan nut Submit
		driver.findElement(By.xpath("//input[@value='Submit']")).click();
	}
	
	@Test
	public void TC02_CustomerID() throws Exception {
		Thread.sleep(3000);
		//Get text cua Customer ID
		driver.findElement(By.xpath("//td[text()='Customer ID']//following-sibling::td")).getText();
		//In ra Customer ID
		System.out.println("Customer ID = "+driver.findElement(By.xpath("//td[text()='Customer ID']//following-sibling::td")).getText());
	}
	
	@Test
	public void TC03_VeryfyCustomer() throws Exception {
		//so sanh voi gia tri da khai bao o ham String
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Customer Name']//following-sibling::td")).getText(), customername );
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Birthdate']//following-sibling::td")).getText(), dateofbirth);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']//following-sibling::td")).getText(), address);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']//following-sibling::td")).getText(), city);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']//following-sibling::td")).getText(), state);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']//following-sibling::td")).getText(), pin);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']//following-sibling::td")).getText(), mobilenumber);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']//following-sibling::td")).getText(), randomEmail);
	}
	
	@Test
	public void TC04_EditCustomer() throws Exception {
		//khai bien customerID va lay Customer ID tu TC_02 (sau khi da nhap tu TC_01)
		String customerId = driver.findElement(By.xpath("//td[text() = 'Customer ID']/following-sibling::td")).getText();
		driver.findElement(By.xpath("//a[text()='Edit Customer']")).click();
		driver.findElement(By.xpath("//input[@name='cusid']")).sendKeys(customerId);
		driver.findElement(By.xpath("//input[@name='AccSubmit']")).click();
		//driver.findElement(By.xpath("//td[text() = 'Customer Name']//following-sibling::td//input")).getAttribute("value");
		Assert.assertEquals(driver.findElement(customernametxt).getAttribute("value"), customername);
		Assert.assertEquals(driver.findElement(addresstxtarea).getText(), address);
	}
	
	
	@Test
	public void TC05_InputEditCustomer() throws Exception {
		//Lay path file theo ten truong o muc edit customer
		driver.findElement(By.xpath("//td[text()='Address']//following-sibling::td//textarea")).clear();
		driver.findElement(By.xpath("//td[text()='Address']//following-sibling::td//textarea")).sendKeys("195 Dong Ngac");
		driver.findElement(By.xpath("//td[text()='City']//following-sibling::td//input")).clear();
		driver.findElement(By.xpath("//td[text()='City']//following-sibling::td//input")).sendKeys("HA NOI 2");
		driver.findElement(By.xpath("//td[text()='State']//following-sibling::td//input")).clear();
		driver.findElement(By.xpath("//td[text()='State']//following-sibling::td//input")).sendKeys("Tu Liem");
		driver.findElement(By.xpath("//td[text()='PIN']//following-sibling::td//input")).clear();
		driver.findElement(By.xpath("//td[text()='PIN']//following-sibling::td//input")).sendKeys("123123");
		driver.findElement(By.xpath("//td[text()='Mobile Number']//following-sibling::td//input")).clear();
		driver.findElement(By.xpath("//td[text()='Mobile Number']//following-sibling::td//input")).sendKeys("0334388388");
		driver.findElement(By.xpath("//td[text()='E-mail']//following-sibling::td//input")).clear();
		driver.findElement(By.xpath("//td[text()='E-mail']//following-sibling::td//input")).sendKeys("maivt@vivas.vn");
		driver.findElement(By.xpath("//input[@name='sub']")).click();
	}

	//@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
