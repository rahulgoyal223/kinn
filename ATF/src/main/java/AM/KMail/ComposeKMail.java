package AM.KMail;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import components.Waits;

public class ComposeKMail {
	//static String strInputValue;
	//static String strGlobalInputValue;
	private static WebElement element = null;
	
	//@KMail - Send Message Reusable Methods
	
	//Method to Validate Send Message Page
	public static void validateComposeKMailPage(WebDriver driver) throws InterruptedException{
		Waits.ForBrowserLoad(driver);
		String myTitle = ComposeMailPageName(driver).getText();
		Assert.assertTrue(myTitle.trim().contains("Send Message"), "The Page Name doesnot match, you are not in KMail compose page");
	}
	
	//Method to compose and send an Email
	public static void ComposeMail(WebDriver driver, String subject){
		enterSubject(driver).sendKeys(subject);
		enterMessageBody(driver).sendKeys("This is a Auto Generated Email");
	}
	
	//@object for SendMessage Header
	public static WebElement ComposeMailPageName(WebDriver driver){
		element = driver.findElement(By.xpath("//*[@id='AMContainer']/div[2]/div/h3"));
		return element;
		
	}
	
	//@Objects for sending an email message
	public static WebElement enterSubject(WebDriver driver)
	{
		element = driver.findElement(By.name("messagesubject"));
		return element;	
	}
	
	public static WebElement enterMessageBody(WebDriver driver){
		element = driver.findElement(By.name("messagebody"));
		return element;
	}
	
	public static void sendMessage(WebDriver driver){
		driver.findElement(By.xpath("//input[@value='Send Message']")).click();;
		//return element;
	}
}
