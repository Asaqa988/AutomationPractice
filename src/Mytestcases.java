import static org.testng.Assert.assertEquals;

import java.nio.channels.Selector;
import java.time.Duration;
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
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Mytestcases {

	WebDriver driver = new ChromeDriver();

	String website = "https://codenboxautomationlab.com/practice/";

	Random rand = new Random();

	@BeforeTest
	public void MySetup() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		driver.manage().window().maximize();
		driver.get(website);
	}

	@Test(priority = 1, enabled = false)
	public void RadioButtons() {

		// select one random radio button

		WebElement divForRadio = driver.findElement(By.id("radio-btn-example"));
		divForRadio.findElements(By.tagName("input"));

		int RandomIndex = rand.nextInt(divForRadio.findElements(By.tagName("input")).size());

		WebElement SelectedInput = divForRadio.findElements(By.tagName("input")).get(RandomIndex);

		SelectedInput.click();

		boolean ActualResult = SelectedInput.isSelected();
		boolean ExpectedResult = true;

		Assert.assertEquals(ActualResult, ExpectedResult);

	}

	@Test(priority = 2, enabled = false)
	public void DynamicDropDown() throws InterruptedException {

		String[] myRandomTwoCharacter = { "AB", "EA", "GH", "IJ", "KL", "MO", "OP" };

		int randomIndex = rand.nextInt(myRandomTwoCharacter.length);
		String myInputdata = myRandomTwoCharacter[randomIndex];

		WebElement autocompleteInput = driver.findElement(By.id("autocomplete"));

		autocompleteInput.sendKeys(myInputdata);
		Thread.sleep(1000);
		autocompleteInput.sendKeys(Keys.chord(Keys.ARROW_DOWN, Keys.ENTER));

		JavascriptExecutor js = (JavascriptExecutor) driver;

		String DataInsideMyInput = (String) js.executeScript("return arguments[0].value", autocompleteInput);

		String updatedData = DataInsideMyInput.toLowerCase();
		boolean ActualResult = updatedData.contains(myInputdata.toLowerCase());

		System.out.println(updatedData);
		System.out.println(myInputdata.toLowerCase());
		Assert.assertEquals(ActualResult, true);

	}

	@Test(priority = 3, enabled = false)
	public void SelectTag() {

		WebElement mySelectelement = driver.findElement(By.id("dropdown-class-example"));

		Select selctor = new Select(mySelectelement);

		int randomIndex = rand.nextInt(1, 4);

//		selctor.selectByVisibleText("API");
//		selctor.selectByValue("option2");
		selctor.selectByIndex(randomIndex);
	}

	@Test(priority = 4, enabled = false)
	public void Checkbox() {
		WebElement divForThecheckBox = driver.findElement(By.id("checkbox-example"));

		List<WebElement> myCheckBox = divForThecheckBox.findElements(By.xpath("//input[@type='checkbox']"));

		for (int i = 0; i < myCheckBox.size(); i++) {

			myCheckBox.get(i).click();
		}

	}

	@Test(priority = 5, enabled = false)
	public void switchWindows() throws InterruptedException {

		driver.findElement(By.id("openwindow")).click();
		Thread.sleep(1000);
		List<String> windowHandles = new ArrayList<>(driver.getWindowHandles());

		driver.switchTo().window(windowHandles.get(1));
		Thread.sleep(3000);

		driver.findElement(By.xpath("//*[@id=\"menu-item-9675\"]/a/span[1]")).click();

		// get back to the main webdriver the first chrome browswe
		driver.switchTo().window(windowHandles.get(0));

		Thread.sleep(3000);

		WebElement divForThecheckBox = driver.findElement(By.id("checkbox-example"));

		List<WebElement> myCheckBox = divForThecheckBox.findElements(By.xpath("//input[@type='checkbox']"));

		for (int i = 0; i < myCheckBox.size(); i++) {

			myCheckBox.get(i).click();
		}

	}

	@Test(priority = 6, enabled = false)
	public void switchTABS() throws InterruptedException {

		driver.findElement(By.id("opentab")).click();

		List<String> windowHandles = new ArrayList<>(driver.getWindowHandles());

		driver.switchTo().window(windowHandles.get(1));
		Thread.sleep(3000);

		String ActualValue = driver.getTitle();

		String expectedValue = "Codenbox AutomationLab - YouTube";

		Assert.assertEquals(ActualValue, expectedValue);

	}

	@Test(priority = 7, enabled = false)
	public void switcALERT() throws InterruptedException {

		// ****************** alert normal ***********************

		// driver.findElement(By.id("alertbtn")).click();

		// Thread.sleep(2000);
		// driver.switchTo().alert().accept();

		// ******************confirm alert*********************

		// to dismiss the confirm alert ( to cancel it )
		// driver.switchTo().alert().dismiss();

		// to accept the alert ( and send the information for example my name that
		// inside the box)
		driver.findElement(By.id("name")).sendKeys("ahmad");

		driver.findElement(By.id("confirmbtn")).click();
		Thread.sleep(2000);

		boolean ActualResult = driver.switchTo().alert().getText().contains("ahmad");
		boolean expectedResult = true;

		Assert.assertEquals(ActualResult, expectedResult);
		driver.switchTo().alert().accept();

	}

	@Test(priority = 8, enabled = false)
	public void TableExample() {

		WebElement theTable = driver.findElement(By.id("product"));

		;
		List<WebElement> allCoursesList = theTable.findElements(By.tagName("td"));

		String myString = theTable.findElements(By.tagName("td")).get(0).getText();
		System.out.println();

		driver.findElement(By.id("name")).sendKeys(myString);

		for (int i = 1; i < allCoursesList.size(); i = i + 3) {
			System.out.println(allCoursesList.get(i).getText());
		}

	}

	@Test(priority = 9, enabled = false)
	public void ElementDisplayed() throws InterruptedException {

		// hide element

		WebElement HideButton = driver.findElement(By.id("hide-textbox"));
		HideButton.click();

		WebElement TheElementThatWeWantToHide = driver.findElement(By.id("displayed-text"));

		boolean ActualResult = TheElementThatWeWantToHide.isDisplayed();
		boolean ExpectedResult = false;

		Assert.assertEquals(ActualResult, ExpectedResult);

		Thread.sleep(2000);

		WebElement ShowButton = driver.findElement(By.id("show-textbox"));
		ShowButton.click();
		boolean ActualResult2 = TheElementThatWeWantToHide.isDisplayed();
		boolean ExpectedResult2 = true;
		Assert.assertEquals(ActualResult2, ExpectedResult2);

	}

	@Test(priority = 10, enabled = false)
	public void Enabled_Disabled() {

		WebElement DisableButton = driver.findElement(By.id("disabled-button"));
		DisableButton.click();

		WebElement TheelementThatWeNeedToDisable = driver.findElement(By.id("enabled-example-input"));

		boolean ActualResult = TheelementThatWeNeedToDisable.isEnabled();
		boolean ExpectedResult = false;

		Assert.assertEquals(ActualResult, ExpectedResult);

		WebElement EnableButton = driver.findElement(By.id("enabled-button"));

		EnableButton.click();
		boolean ActualResult2 = TheelementThatWeNeedToDisable.isEnabled();

		boolean ExpectedResult2 = true;
		Assert.assertEquals(ActualResult2, ExpectedResult2);

	}

	@Test(priority = 11, enabled = false)
	public void MouseHover() {

		JavascriptExecutor js = (JavascriptExecutor) driver;

		js.executeScript("window.scrollTo(0,1750)");

		Actions action = new Actions(driver);

		WebElement Thetarget = driver.findElement(By.id("mousehover"));
		action.moveToElement(Thetarget).perform();
		;

		driver.findElement(By.linkText("Reload")).click();

	}

	@Test(priority = 12, enabled = false)
	public void Calendar() throws InterruptedException {

		driver.findElement(By.linkText("Booking Calendar")).click();

		List<String> windowHandles = new ArrayList<>(driver.getWindowHandles());

		driver.switchTo().window(windowHandles.get(1));

		Thread.sleep(3000);
		;

		// this is the correct solution - the problem is when you try this you will get
		// error says stale element not found ( the develope is not using react or any
		// modern framework )
		// the modern Framework applies the SPA single page application

		// List<WebElement> AvailableDate=
		// driver.findElements(By.xpath("//a[@href='javascript:void(0)']"));

//		System.out.println(AvailableDate.size());

		// this is not a good solution because i have to repeat the code as much as
		// avaliable times are there

//		AvailableDate.get(1).click();
//		AvailableDate.get(2).click();
//		AvailableDate.get(3).click();

		// this will solve the problem of stale element not found but also is not a
		// correct one why because the list
		// will get declared تعرف
		// 25 times each time
		for (int i = 1; i < 25; i++) {
			List<WebElement> AvailableDate = driver.findElements(By.xpath("//a[@href='javascript:void(0)']"));

			AvailableDate.get(i).click();
			Thread.sleep(1000);
			;

		}

	}

	@Test(priority = 13, enabled = true)
	public void iFrame() throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor)driver ; 
		
		js.executeScript("window.scrollTo(0,2400)");
		
	WebElement myFrame = 	driver.findElement(By.id("courses-iframe"));
	
	
	Thread.sleep(3000);
	driver.switchTo().frame(myFrame);
	js.executeScript("window.scrollTo(0,1100)");

	
	String myText =driver.findElement(By.xpath("//*[@id=\"ct_heading-1b594e8\"]/div/h3/span")).getText();
	
	System.out.println(myText);
	
	Thread.sleep(3000);
	driver.findElement(By.xpath("//*[@id=\"ct_button-20c391b5\"]/a/span[2]")).click();
	Thread.sleep(2000);
	
	driver.findElement(By.xpath("//*[@id=\"ct-pagetitle\"]/div/ul/li[1]/a")).click();

	}

}
