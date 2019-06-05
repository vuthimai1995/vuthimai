package selenium;

import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_01_CheckEnvironment {
    WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		//sau khi mo trang guru thi phai nhap ham doi
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}
	
	//@Test
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
	

	public void selectItemDropdow(String parentdropdownlocator, String allItemDropdown, String ExpectedText) throws Exception {
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
			String ItemText = allItem.get(i).getText();
			if (ItemText.equals(ExpectedText)) {
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", allItem.get(i));
				Thread.sleep(1000);
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
	
	//@Test
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
	public void TC04_Angular() throws Exception {
		driver.get("https://material.angular.io/components/select/examples");
		selectItemDropdow("//mat-label[text()='State']//ancestor::div[@class='mat-form-field-infix']//div[@class='mat-select-arrow-wrapper']", "//span[@class='mat-option-text']//parent::mat-option[@class='mat-option ng-star-inserted']", "Alaska");
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='mat-select-value']//span[text()='Alaska']")).isDisplayed());
		Thread.sleep(3000);
	}
	
	//@Test
	public void TC05_KendoUI() throws Exception {
		driver.get("https://demos.telerik.com/kendo-ui/dropdownlist/index");
		selectItemDropdow("//span[@aria-owns='color_listbox']//span[@class='k-select']", "//ul[@id='color_listbox']/li", "Orange");
		Assert.assertTrue(driver.findElement(By.xpath("//span[@aria-owns='color_listbox']//span[text()='Orange']")).isDisplayed());
		Thread.sleep(3000);
	}
	
	//@Test
	public void TC06_vueJS() throws Exception {
		driver.get("https://mikerodham.github.io/vue-dropdowns/");
		selectItemDropdow("//div[@class='btn-group']//span[@class='caret']", "//ul[@class='dropdown-menu']//li", "Second Option");
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='dropdown-toggle' and contains(.,'Second Option')]")).isDisplayed());
		Thread.sleep(3000);
		
		selectItemDropdow("//div[@class='btn-group']//span[@class='caret']", "//ul[@class='dropdown-menu']//li", "Third Option");
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='dropdown-toggle' and contains(.,'Third Option')]")).isDisplayed());
		Thread.sleep(3000);
	}
	
	
	@Test
	public void TC07_EditTable() throws Exception {
		driver.get("http://indrimuska.github.io/jquery-editable-select/");
		
		selectItemDropdow("//div[@id='default-place']/input", "//div[@id='basic-place']//ul[@class='es-list']/li", "Audi");
		driver.findElement(By.xpath("//div[@id='default-place']/input")).sendKeys("Audi");
		//kiem hien thi 1 phan tu thi >> phai chon san phan tu muon chon xong moi tim xpath phan tu do
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='default-place']/ul/li[text()='Audi']")).isDisplayed());
		Thread.sleep(3000);
		
		driver.findElement(By.xpath("//div[@id='default-place']/input")).clear();
		selectItemDropdow("//div[@id='default-place']/input", "//div[@id='basic-place']//ul[@class='es-list']/li", "BMW");
		driver.findElement(By.xpath("//div[@id='default-place']/input")).sendKeys("BMW");
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='default-place']/ul/li[text()='BMW']")).isDisplayed());
		WebDriverWait driverwait = new WebDriverWait(driver, 3000);
		driverwait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//div[@id='default-place']/input")));
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
