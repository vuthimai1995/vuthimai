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

public class Topic_08_DropDown {
	WebDriver driver;
	Select select;
	
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		//sau khi mo trang guru thi phai nhap ham doi
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	@Test
	public void TC01_Dropdown() throws Exception {
		driver.get("https://daominhdam.github.io/basic-form/index.html");
		WebElement jobRole01dropdownlist = driver.findElement(By.xpath("//select[@id='job1']"));
		Select select = new Select(jobRole01dropdownlist);
		//step2: kiem tra DropDown khong ho tro thuoc tinh Multi
		Assert.assertFalse(select.isMultiple());
		//Step3,4: Chon gia tri Automation Testing va kiem tra bang Visibletext
		select.selectByVisibleText("Automation Tester");
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Automation Tester");
		Thread.sleep(1500);
		//step5,6: kiem tra gia tri manual tester bang SelectValue
		select.selectByValue("manual");
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Manual Tester");
		Thread.sleep(1500);
		//Step 7,8: kiem tra gia tri Mobile test bang phuong thuc selectIndex
		select.selectByIndex(3);
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Mobile Tester");
		Thread.sleep(1500);
		Assert.assertEquals(select.getOptions().size(), 5);
	}
	
	@Test
	public void selectItemDropdow(String parentdropdownlocator, String allItemDropdown, String ExpectedText) throws Exception {
		//click con dropdown
		WebElement parendropdownelement = driver.findElement(By.xpath(parentdropdownlocator));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", parendropdownelement);
		Thread.sleep(1000);
		
		//wait all element is display
		WebDriverWait driverwait = new WebDriverWait(driver, 3000);
		driverwait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(allItemDropdown)));
		
		//get all element in dropdown
		List< WebElement > allItem = driver.findElements(By.xpath(allItemDropdown));
		
		//Use For to gettext elements
		for (int i = 0; i < allItem.size(); i++) {
			String ItemText = allItem.get(i).getText();
			if(ItemText.equals(ExpectedText)) {
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", allItem.get(i));
				Thread.sleep(1000);
				allItem.get(i).click();
				if(allItem.get(i).isDisplayed()){
					//selenium click
					allItem.get(i).click();
				}
				else {
					//javascrip click
					((JavascriptExecutor) driver).executeScript("arguments[0].click();", allItem.get(i));
				}
				break;
			}
		}	
	}
	
	@Test
	public void TC03_CustomerDropdown() throws Exception {
		driver.get("http://jqueryui.com/resources/demos/selectmenu/default.html ");
		selectItemDropdow("//span[@id='number-button']", "//ul[@id='number-menu']/li", "19");
		//xac minh gia tr dc hien thi
		Assert.assertTrue(driver.findElement(By.xpath("//span[@id='number-button']/span[@class='ui-selectmenu-text' and text()='19']")).isDisplayed());
		Thread.sleep(3000);
		
		selectItemDropdow("//span[@id='number-button']", "//ul[@id='number-menu']/li", "9");
		//xac minh gia tr dc hien thi
		Assert.assertTrue(driver.findElement(By.xpath("//span[@id='number-button']/span[@class='ui-selectmenu-text' and text()='9']")).isDisplayed());
		Thread.sleep(3000);
		
		selectItemDropdow("//span[@id='number-button']", "//ul[@id='number-menu']/li", "11");
		//xac minh gia tr dc hien thi
		Assert.assertTrue(driver.findElement(By.xpath("//span[@id='number-button']/span[@class='ui-selectmenu-text' and text()='11']")).isDisplayed());
		Thread.sleep(3000);
	}
	
	//@Test
		public void TC04() throws Exception {
			driver.get("https://material.angular.io/components/select/examples ");
			selectItemDropdow("//mat-label[text()='State']//ancestor::div[@class='mat-form-field-infix']//div[@class='mat-select-arrow-wrapper']", "//span[@class='mat-option-text']", "Alaska");
			Assert.assertTrue(driver.findElement(
					By.xpath("//div[@class='mat-select-value']//span[text()='Alaska']"))
							.isDisplayed());
			Thread.sleep(1000);
		}

		//@Test
		public void TC05() throws Exception {
			driver.get("https://demos.telerik.com/kendo-ui/dropdownlist/index");

			selectItemDropdow("//span[@aria-owns='color_listbox']//span[@class='k-select']", "//ul[@id='color_listbox']/li", "Orange");
			Assert.assertTrue(driver.findElement(
					By.xpath("//span[@class='k-input' and text()='Orange']"))
							.isDisplayed());
			Thread.sleep(2000);
			selectItemDropdow("//span[@aria-owns='color_listbox']//span[@class='k-select']", "//ul[@id='color_listbox']/li", "Grey");
			Assert.assertTrue(driver.findElement(
					By.xpath("//span[@class='k-input' and text()='Grey']"))
							.isDisplayed());
			Thread.sleep(2000);

		}

		//@Test
		public void TC06() throws Exception {
			
			 driver.get("https://mikerodham.github.io/vue-dropdowns/");
			 selectItemDropdow("//div[@class='btn-group']//li[@class='dropdown-toggle']", "//ul[@class='dropdown-menu']/li","Second Option");
			 Assert.assertTrue(driver.findElement(By.xpath("//li[@class='dropdown-toggle' and contains(text(),'Second Option')]")).isDisplayed());
			Thread.sleep(2000);
			 
		}

		//@Test
		public void TC07() throws Exception {
			driver.get("http://indrimuska.github.io/jquery-editable-select/");
			selectItemDropdow("//*[@id='default-place']/input", "//div[@id='basic-place']//ul[@class='es-list']/li", "Audi");
			driver.findElement(By.xpath("//*[@id='default-place']/input")).sendKeys("Audi");
			Assert.assertTrue(driver.findElement(
					By.xpath("//div[@id='default-place']/ul/li[text()='Audi']"))
							.isDisplayed());
			
			driver.findElement(By.xpath("//*[@id='default-place']/input")).clear();
			selectItemDropdow("//*[@id='default-place']/input", "//div[@id='basic-place']//ul[@class='es-list']/li", "BMW");
			driver.findElement(By.xpath("//*[@id='default-place']/input")).sendKeys("BMW");
			Assert.assertTrue(driver.findElement(
					By.xpath("//div[@id='default-place']/ul/li[text()='BMW']"))
							.isDisplayed());
			Thread.sleep(1000);
			

		}
		public void selectMultiItemDropdow(String parentdropdownlocator, String allItemDropdown, String[] ExpectedText) throws Exception {
	    	// Click con dropdown
			WebElement parentdropdownelement = driver.findElement(By.xpath(parentdropdownlocator));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", parentdropdownelement);
			Thread.sleep(1000);

			// Wait all element is display
			WebDriverWait driverwait = new WebDriverWait(driver, 3000);
			driverwait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(allItemDropdown)));

			// Get all iteam in dropdown
			List<WebElement> allItem = driver.findElements(By.xpath(allItemDropdown));

			// Use FOR to gettext elements
			for (int i = 0; i < allItem.size(); i++) {
				//String ItemText = allItem.get(i).getText();
				
				for (String item:ExpectedText) {
					if(allItem.get(i).getText().equals(item)) {
						((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", allItem.get(i));
						Thread.sleep(1000);
						((JavascriptExecutor) driver).executeScript("arguments[0].click();", allItem.get(i));
						Thread.sleep(1000);
						List <WebElement> itemSelected = driver.findElements(By.xpath("//li[@class='selected']//input"));
						System.out.println("Item selected" +itemSelected.size());
						if(ExpectedText.length==itemSelected.size()) {
							break;
						}
					
					}
				}
			}
		}
		public boolean checkItemSelected1(String[] itemSelectedText) {
	    //List <WebElement> itemSelected1 = driver.findElements(By.xpath("//li[@class='selected']//input"));
		String allItemSelectedText = driver.findElement(By.xpath("//a[@class='ui label transition visible']")).getText();
			for(String item:itemSelectedText)
			{
				if(allItemSelectedText.contains(item)) {
					break;
				}
			}
			return true;
	}
		
		
		@Test
		public void TC08() throws Exception {
			String[] a={"CSS", "HTML", "Python"};
			 driver.get("https://semantic-ui.com/modules/dropdown.html");
			 selectMultiItemDropdow("//div[@class='ui fluid dropdown selection multiple']//i[@class='dropdown icon']", "//div[@class='menu transition visible']/div",a);
			 checkItemSelected1(a);

			 
		}
		
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
