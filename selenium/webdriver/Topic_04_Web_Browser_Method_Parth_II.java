package webdriver;


//import java.util.List;
//import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
//import org.openqa.jetty.html.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

//import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;


public class Topic_04_Web_Browser_Method_Parth_II {
	WebDriver driver;  // Khai báo
	By emailTextbox = By.id("mail");
	By ageUnderRadio = By.id("under_18");
	By educationTextArea = By.id("edu");
	By nameUser5Text = By.xpath("//h5[text()='Name: User5']");
	By passwordTextbox = By.id("password");
	By bioTextarea = By.id("bio");
	By slider2 = By.id("slider-2");
	By developmentCheckbox = By.id("development");
	By disableCheckbox = By.id("check-disbaled");


	@BeforeClass
	public void beforeClass() {
		//Open browser
		driver =  new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		driver.get("https://automationfc.github.io/basic-form/");
				
		}
	
	//@Test
	public void TC_01_Displayed() {
		/*
		if(driver.findElement(emailTextbox).isDisplayed()) {
			driver.findElement(emailTextbox).sendKeys("Automation Testing");
			System.out.println(emailTextbox + "- is displayed");
		} else {
			System.out.println(emailTextbox + "- is un-displayed");
		}
		
	if(driver.findElement(ageUnderRadio).isDisplayed()) {
		driver.findElement(ageUnderRadio).click();
		System.out.println(ageUnderRadio + "- is displayed");
	} else {
		System.out.println(ageUnderRadio + "- is un-displayed");
	}
	
	if(driver.findElement(educationTextArea).isDisplayed()) {
		driver.findElement(educationTextArea).sendKeys("Automation Testing");
		System.out.println(educationTextArea + "- is displayed");
	} else {
		System.out.println(educationTextArea + "- is un-displayed");
	}
	
	if(driver.findElement(nameUser5Text).isDisplayed()) {
		System.out.println(nameUser5Text + "- is displayed");
	} else {
		System.out.println(nameUser5Text + " - is un-displayed");
	}
	*/
		/*
		if(isElementDisplayed(emailTextbox)) {
			inputToElement(emailTextbox, "Automation Testing");
		}
		
		if(isElementDisplayed(educationTextArea)) {
			inputToElement(educationTextArea, "Automation Testing");
		}
		
		if(isElementDisplayed(ageUnderRadio)) {
			clickToElement(ageUnderRadio);
		}
		*/
		Assert.assertTrue(isElementDisplayed(emailTextbox));
		inputToElement(emailTextbox, "Automation Testing");
		
		Assert.assertTrue(isElementDisplayed(educationTextArea));
		inputToElement(educationTextArea, "Automation Testing");
		
		Assert.assertTrue(isElementDisplayed(ageUnderRadio));
		clickToElement(ageUnderRadio);
		
		Assert.assertFalse(isElementDisplayed(nameUser5Text));
		
			
				sleepInSecond(10);
		
			}
	
	
	//@Test
	public void TC_02_Enable() {
		
		Assert.assertTrue(isElementEnable(nameUser5Text));
		Assert.assertTrue(isElementEnable(emailTextbox));
		Assert.assertTrue(isElementEnable(educationTextArea));
		Assert.assertTrue(isElementEnable(ageUnderRadio));
		
		Assert.assertFalse(isElementEnable(passwordTextbox));
		Assert.assertFalse(isElementEnable(bioTextarea));
		Assert.assertFalse(isElementEnable(slider2));
		
	}
	
	
	public void TC_03_Selected() {
		//
		//verify deselected
		Assert.assertFalse(isElementSlected(ageUnderRadio));
		Assert.assertFalse(isElementSlected(developmentCheckbox));
		Assert.assertFalse(isElementSlected(disableCheckbox));
		
		// Click Radio/ Checkbox (selected)
		clickToElement(ageUnderRadio);
		clickToElement(developmentCheckbox);
		
		// Verify selected
		Assert.assertTrue(isElementSlected(ageUnderRadio));
		Assert.assertTrue(isElementSlected(developmentCheckbox));


		// Click Radio/ Checkbox (de-selected)
		clickToElement(ageUnderRadio);
		clickToElement(developmentCheckbox);
		
		// Verify de-selected
		Assert.assertTrue(isElementSlected(ageUnderRadio));
		// Verify de-selected
		Assert.assertFalse(isElementSlected(developmentCheckbox));
	}
	
	@Test
	public void TC_04_MailChimp() {
		By passwordTextbox = By.id("new_password");
		By signupButton = By.id("create-account");
		
		driver.get("https://login.mailchimp.com/signup/");
		inputToElement(By.id("email"), "nhunguyen.eng@gmail.com");
		inputToElement(By.id("new_username"), "nhunguyen.eng");
		//Lower case
		inputToElement(passwordTextbox, "auto");		
		Assert.assertTrue(isElementDisplayed(By.xpath("//li[@class='lowercase-char completed' and text()='One lowercase character']")));
		Assert.assertFalse(isElementEnable(signupButton));
		
		// Upper case + Lower case
		inputToElement(passwordTextbox, "Auto");
		Assert.assertTrue(isElementDisplayed(By.xpath("//li[@class='lowercase-char completed' and text()='One lowercase character']")));
		Assert.assertTrue(isElementDisplayed(By.xpath("//li[@class='uppercase-char completed' and text()='One uppercase character']")));
		Assert.assertFalse(isElementEnable(signupButton));
		
		// Upper case +  Lower case + Number
		inputToElement(passwordTextbox, "Auto1");
		Assert.assertTrue(isElementDisplayed(By.xpath("//li[@class='lowercase-char completed' and text()='One lowercase character']")));
		Assert.assertTrue(isElementDisplayed(By.xpath("//li[@class='uppercase-char completed' and text()='One uppercase character']")));
		Assert.assertTrue(isElementDisplayed(By.xpath("//li[@class='number-char completed' and text()='One number']")));
		Assert.assertFalse(isElementEnable(signupButton));
		
		// Upper case + Lower case + Number + Special character
		inputToElement(passwordTextbox, "Auto1@");
		Assert.assertTrue(isElementDisplayed(By.xpath("//li[@class='lowercase-char completed' and text()='One lowercase character']")));
		Assert.assertTrue(isElementDisplayed(By.xpath("//li[@class='uppercase-char completed' and text()='One uppercase character']")));
		Assert.assertTrue(isElementDisplayed(By.xpath("//li[@class='number-char completed' and text()='One number']")));
		Assert.assertTrue(isElementDisplayed(By.xpath("//li[@class='special-char completed' and text()='One special character']")));
		Assert.assertFalse(isElementEnable(signupButton));
		
		// >=8 Char + Number + Special Char + Lower case
		inputToElement(passwordTextbox, "auto@123");
		Assert.assertTrue(isElementDisplayed(By.xpath("//li[@class='lowercase-char completed' and text()='One lowercase character']")));
		Assert.assertTrue(isElementDisplayed(By.xpath("//li[@class='number-char completed' and text()='One number']")));
		Assert.assertTrue(isElementDisplayed(By.xpath("//li[@class='special-char completed' and text()='One special character']")));
		Assert.assertTrue(isElementDisplayed(By.xpath("//li[@class='8-char completed' and text()='8 characters minimum']")));
		Assert.assertFalse(isElementEnable(signupButton));
		
		// Upper case + Lower case + Number + Special character + >=8 character 
		inputToElement(passwordTextbox, "Auto1@23");
		Assert.assertFalse(isElementDisplayed(By.xpath("//li[@class='lowercase-char completed' and text()='One lowercase character']")));
		Assert.assertFalse(isElementDisplayed(By.xpath("//li[@class='uppercase-char completed' and text()='One uppercase character']")));
		Assert.assertFalse(isElementDisplayed(By.xpath("//li[@class='number-char completed' and text()='One number']")));
		Assert.assertFalse(isElementDisplayed(By.xpath("//li[@class='special-char completed' and text()='One special character']")));
		Assert.assertFalse(isElementDisplayed(By.xpath("//li[@class='8-char completed' and text()='8 characters minimum']")));
		
		Assert.assertTrue(isElementEnable(signupButton));
		
	}
	
	@AfterClass
	public void affterClass() {
		driver.quit();
	}
	
	public void sleepInSecond(long time) {
		try {
			Thread.sleep(time*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean isElementDisplayed(By by) {
		if(driver.findElement(by).isDisplayed()) {
			System.out.println(by + "- is displayed");
			return true;					
		} else {
			System.out.println(by + "- is not displayed");
			return false;
		}	
		
	}
	
	public boolean isElementEnable(By by) {
		if(driver.findElement(by).isEnabled()) {
			System.out.println(by + "- is enable");
			return true;
		} else {
			System.out.println(by + "- is disable");
			return false;
		}
	}
	
	
	public boolean isElementSlected (By by) {
		if(driver.findElement(by).isSelected()) {
			System.out.println(by + "- is selected");
			return true;
		} else {
			System.out.println(by + "- is not selected");
			return false;
			
		}
		
	}
	
	public void clickToElement(By by) {
		driver.findElement(by).click();
	}
	
	public void inputToElement(By by, String value) {
		driver.findElement(by).clear();
		driver.findElement(by).sendKeys(value);
	}	
		
	}
