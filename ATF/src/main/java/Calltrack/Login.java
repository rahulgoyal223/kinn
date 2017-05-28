package Calltrack;

import java.net.MalformedURLException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import components.Browser;
import components.Config;
import components.Report;
import components.Waits;

public class Login {

	private static WebDriver driver;
	
	public static WebDriver openAppAndSubmitCredentials() throws MalformedURLException, Exception {
		String driverpath = Config.getDriversPath();
		String Btype = Config.getBrowserType();
		String Url = Config.getAppUrl() + "/calltrack";
		String UserName = Config.getAppUserNameCalltrack();
		String Password = Config.getAppPasswordCalltrack();
		
		System.out.println("Patient Name is : "+ UserName);
		System.out.println("Patient Name is : "+ Password);
		
		driver = Browser.getDriver(Btype, Url, driverpath);
		SubmitCredentials(UserName, Password);
		
		return driver;
	}

	public static void SubmitCredentials(String UserName, String Password) throws InterruptedException {
		txt_UserName().sendKeys(UserName);
		txt_Password().sendKeys(Password);
		btn_Submit().click();
		Waits.ForBrowserLoad(driver);
		Report.attachScreenShotToReport(driver);
	}
	
	public static WebElement txt_UserName() {		
		return driver.findElement(By.name("username"));
	}

	public static WebElement txt_Password() {
		return driver.findElement(By.name("password"));
	}

	public static WebElement btn_Submit() {		
		return driver.findElement(By.xpath("//*[@id='lfrm']/p/input[3]"));
	}
}
