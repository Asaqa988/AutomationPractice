import static org.testng.Assert.assertEquals;

import java.nio.channels.Selector;
import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class Mytestcases {

	WebDriver driver = new ChromeDriver();

	String WebSite = "https://codenboxautomationlab.com/practice/";

	Random rand = new Random();

	JavascriptExecutor js = (JavascriptExecutor) driver;

	@BeforeTest
	public void mySetup() {

		driver.manage().window().maximize();
		driver.get(WebSite);
	}

	@Test(priority = 1, description = "Radio button", invocationCount = 1, enabled = false)

	public void Radio_Button_Example() throws InterruptedException {

		List<WebElement> AllRadioButtons = driver.findElements(By.className("radioButton"));
		// to click on the first item
//AllRadioButtons.get(0).click();	

		int randomIndex = rand.nextInt(AllRadioButtons.size()); // size is 3 so it it will generate 0,1,2
		AllRadioButtons.get(randomIndex).click();

		// this is new code for test 1
		boolean expectedddddddddddResult = true;
		boolean ActualResult = AllRadioButtons.get(randomIndex).isSelected();

		Assert.assertEquals(ActualResult, expectedddddddddddResult);

		// l3yon mahdi

		for (int i = 0; i < AllRadioButtons.size(); i++) {
			Thread.sleep(1000);
			AllRadioButtons.get(i).click();
		}
	}

	@Test(priority = 2, description = "dropDown Dynamic", enabled = false)
	public void Dynamic_Dropdown_Example() throws InterruptedException {

		// generate random String in a static way because i dont need my test to include
		// any other data
		String[] countryCodes = { "US", "CA", "OM", "BR", "AR", "FR", "DE", "IT", "ES", "AM" };

		// random index based on the length of the above array

		int randomIndex = rand.nextInt(countryCodes.length);

// webelement to the input field ( country)  
		WebElement DynamicListInput = driver.findElement(By.id("autocomplete"));

		// send an random item from my array to the webelement (inputfield)
		DynamicListInput.sendKeys(countryCodes[randomIndex]);

		Thread.sleep(1000);

		// it will press an arrow down + enter to select the first item from the list
		DynamicListInput.sendKeys(Keys.chord(Keys.ARROW_DOWN, Keys.ENTER));

// i need to capture or take the country name that selenum already selected 
		String DataInsideMyInput = (String) js.executeScript("return arguments[0].value", DynamicListInput);

		// the country name for example United Arab Emirates contains capital letters
		// and small ones
		// so what i did i make all the letters in small ( for example France , and
		// South Africa look at the 'fr and Fr'
		String updateDataInMyInput = DataInsideMyInput.toUpperCase();

		boolean ActualValue = updateDataInMyInput.contains(countryCodes[randomIndex].toUpperCase());

		boolean ExpectedResult = true;

		Assert.assertEquals(ActualValue, ExpectedResult);

	}

	@Test(priority = 3, description = "static dropdown list", enabled = false)
	public void Static_Dropdown_Example() {
		WebElement SelectElement = driver.findElement(By.id("dropdown-class-example"));
		Select sel = new Select(SelectElement);

		 sel.selectByIndex(3);
		// sel.selectByValue("option2");
		// sel.selectByVisibleText("API");

	}

	@Test(priority = 4, description = "check box example", enabled = true)
	public void Checkbox_Example() throws InterruptedException {
		List<WebElement> CheckBoxes = driver.findElements(By.xpath("//input[@type='checkbox']"));
		int randomIndex = rand.nextInt(CheckBoxes.size());
		System.out.println(CheckBoxes.size());
		Thread.sleep(1000);
		// CheckBoxes.getFirst().click();
		// CheckBoxes.getLast().click();
		// CheckBoxes.get(96).click();

		// CheckBoxes.get(randomIndex).click();

		// to select them all
		for (int i = 0; i < CheckBoxes.size(); i++) {
			CheckBoxes.get(i).click();
			boolean ActaualResult = CheckBoxes.get(i).isSelected();
			boolean expectedResult = true ; 
			
			Assert.assertEquals(ActaualResult, expectedResult); 
			
		}
	

		
		
	}

	@Test(priority = 5, description = "this is to move from window to another one", enabled = false)
	public void Switch_Window_Example() throws InterruptedException {

		WebElement OpenWindowButton = driver.findElement(By.id("openwindow"));
		OpenWindowButton.click();
		Thread.sleep(2000);
		List<String> windowsHandels = new ArrayList<String>(driver.getWindowHandles());
		// number of opened windows
		System.out.println(windowsHandels.size());
// switch to the other window 
		driver.switchTo().window(windowsHandels.get(1));
		// in the second window
		WebElement ContactButton = driver.findElement(By.id("menu-item-9680"));
		ContactButton.click();

		System.out.println(driver.getTitle() + " hello from the second window");

		driver.close();
		// switch to the first window
		driver.switchTo().window(windowsHandels.get(0));

	}

	@Test(priority = 6, description = "check moving to another tab", enabled = false)

	public void Switch_Tab_Example() throws InterruptedException {
		WebElement OpenTabButton = driver.findElement(By.id("opentab"));

		OpenTabButton.click();

		List<String> windowsHandels = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(windowsHandels.get(1));
		Thread.sleep(2000);
		System.out.println(driver.getTitle());
	}

	@Test(priority = 7, description = "Alert and confirm", enabled = false)
	public void Switch_To_Alert_Example() throws InterruptedException {
		WebElement nameBox = driver.findElement(By.id("name"));
		nameBox.sendKeys("abedalraheem");
		// WebElement AlertBox = driver.findElement(By.id("alertbtn"));
		// AlertBox.click();

		Thread.sleep(1000);
		// driver.switchTo().alert().accept();
		// driver.switchTo().alert().dismiss();

		WebElement ConfirmBox = driver.findElement(By.id("confirmbtn"));
		ConfirmBox.click();
		Thread.sleep(1000);
		// driver.switchTo().alert().accept();
		// driver.switchTo().alert().dismiss();

		// System.out.println(driver.switchTo().alert().getText());
	}

	@Test(priority = 8, description = " play with the data of the column ", enabled = false)

	public void Web_Table_Example() {

		WebElement TheTable = driver.findElement(By.id("product"));

		List<WebElement> theDataInsideTheTable = TheTable.findElements(By.tagName("tr"));

		for (int i = 1; i < theDataInsideTheTable.size(); i++) {

			int totalTdInTheRow = theDataInsideTheTable.get(i).findElements(By.tagName("td")).size();

			System.out.println(
					theDataInsideTheTable.get(i).findElements(By.tagName("td")).get(totalTdInTheRow - 1).getText());
		}

	}

	@Test(priority = 9, description = "this is to test hide and show buttons", enabled = false)
	public void Element_Displayed_Example() throws InterruptedException {

		JavascriptExecutor js = (JavascriptExecutor) driver;
		SoftAssert myAssertion = new SoftAssert();

		// softassert if one test failed it will continue to the rest of the code

		// hardassert once failed it will stop all the execution

		js.executeScript("window.scrollTo(0,1500)");

		WebElement HideButton = driver.findElement(By.id("hide-textbox"));
		WebElement ShowButton = driver.findElement(By.id("show-textbox"));

		HideButton.click();

		WebElement theTEXXXXXTINPUT = driver.findElement(By.id("displayed-text"));

		// hard assert

		// Assert.assertEquals(theTEXXXXXTINPUT.isDisplayed(), true);

		myAssertion.assertEquals(theTEXXXXXTINPUT.isDisplayed(), false);

		Thread.sleep(4000);
		ShowButton.click();
		Assert.assertEquals(theTEXXXXXTINPUT.isDisplayed(), true);

		myAssertion.assertAll();
	}

	@Test(priority = 10, enabled = false)

	public void CheckTheTitle() {
		String expected = "Automation Practice - CodenBox AutomationLab";

		String ActualTitle = driver.getTitle();

		Assert.assertEquals(ActualTitle, expected);
	}

}
