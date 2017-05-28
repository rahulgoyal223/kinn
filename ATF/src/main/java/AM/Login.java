package AM;

import components.Browser;
import components.Config;
import components.Report;
import components.Waits;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.aventstack.extentreports.Status;

import DataSource.Datatable;
import DataSource.GlobalData;

import java.net.MalformedURLException;

public class Login {
	static String strInputValue;
	private static WebElement element = null;

	public static void openAppAndSubmitCredentials()
			throws MalformedURLException, InterruptedException {
		String driverpath = Config.getDriversPath();
		String Btype = Config.getBrowserType();
		String Url = Config.getAppUrl();
		String UserName = Config.getAppUserName();
		String Password = Config.getAppPassword();
		WebDriver driver = Browser.getDriver(Btype, Url, driverpath);
		AM.Login.SubmitCredentials(driver, UserName, Password);

		if(driver.getPageSource().contains("Your current (or temporary) password has expired"))
	        {
	        bypassThisScreen(driver).click();
	        Waits.ForBrowserLoad(driver);
	        }
	}

	public static void SubmitCredentials(WebDriver driver, String UserName,
			String Password) throws InterruptedException {
		txt_UserName(driver).sendKeys(UserName);
		txt_Password(driver).sendKeys(Password);
		btn_Submit(driver).click();
		driver.switchTo().alert().accept();
		Waits.ForBrowserLoad(driver);
		Report.attachScreenShotToReport(driver);
	}
	
	public static void FillLoginDetails(WebDriver driver) throws Exception {

        try {
        	//Waits.ForBrowserLoad(driver);
        	strInputValue = Datatable.GetValue("UI_Name");
        	if (!strInputValue.trim().isEmpty()) {
        		txt_UserName(driver).clear();
        		txt_UserName(driver).sendKeys(strInputValue);
                GlobalData.setUserName(strInputValue);
            }
        	
        	strInputValue = Datatable.GetValue("UI_Password");
        	if (!strInputValue.trim().isEmpty()) {
        		txt_Password(driver).clear();
        		txt_Password(driver).sendKeys(strInputValue);
                GlobalData.setTemporaryPwd(strInputValue);
            }
        	Report.Log(Status.PASS, "User details have been filled successfully");
    	} catch (Exception e) {
        e.printStackTrace();
        Report.Log(Status.FAIL, "User details have NOT been filled");
    }
}
	public static WebElement txt_UserName(WebDriver driver) {
		element = driver.findElement(By.name("username"));
		Waits.ForElementVisibility(driver, element); 
		return element;
	}

	public static WebElement txt_Password(WebDriver driver) {
		element = driver.findElement(By.name("password"));
		Waits.ForElementVisibility(driver, element); 
		return element;
	}

	public static WebElement btn_Submit(WebDriver driver) {
		element = driver.findElement(By
				.xpath("//button[@type='submit']"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}
	
	public static void skipExpiredPassword(WebDriver driver) throws Exception {
		if(driver.getPageSource().contains("Your current (or temporary) password has expired")) {
			getBypassThisScreen(driver).click();
			Waits.ForBrowserLoad(driver);
        }
	}
	
	
	public static WebElement getBypassThisScreen(WebDriver driver) {
		return driver.findElement(By.linkText("Bypass this screen"));
	}
	
	public static WebElement bypassThisScreen(WebDriver driver) {
		element = driver.findElement(By.linkText("Bypass this screen"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

}
