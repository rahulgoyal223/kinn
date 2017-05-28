package KH;

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
			throws MalformedURLException, Exception {
		String driverpath = Config.getDriversPath();
		String Btype = Config.getBrowserType();
		String Url = Config.getAppUrl();
		String UserName = Config.getAppUserNameKH();
		String Password = Config.getAppPasswordKH();
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
	
	// Call track submit credentials
	public static void CallTrackAndSubmitCredentials()
			throws MalformedURLException, Exception {
		String driverpath = Config.getDriversPath();
		String Btype = Config.getBrowserType();
		String Url = Config.getAppUrl()+"/calltrack/index.cfm";
		String UserName = Config.getAppUserNameCalltrack();
		String Password = Config.getAppPasswordCalltrack();
		WebDriver driver = Browser.getDriver(Btype, Url, driverpath);
		KH.Login.CallSubmitCredentials(driver, UserName, Password);

		if(driver.getPageSource().contains("Your current (or temporary) password has expired"))
	        {
	        bypassThisScreen(driver).click();
	        Waits.ForBrowserLoad(driver);
	        }
	}

	public static void CallSubmitCredentials(WebDriver driver, String UserName,
			String Password) throws InterruptedException {
		txt_UserNameCallTrack(driver).sendKeys(UserName);
		txt_PasswordCallTrack(driver).sendKeys(Password);
		btn_SubmitCallTrack(driver).click();
		Waits.ForBrowserLoad(driver);
		Report.attachScreenShotToReport(driver);
	}
	
	public static void FillLoginDetails(WebDriver driver) throws Exception {

        try {
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
		return element;
	}

	public static WebElement txt_Password(WebDriver driver) {
		element = driver.findElement(By.name("password"));
		return element;
	}

	public static WebElement btn_Submit(WebDriver driver) {
		element = driver.findElement(By
				.xpath("//*[@id='login-box']/form/div[4]/button"));
		return element;
	}
	
	public static WebElement bypassThisScreen(WebDriver driver) {
		element = driver.findElement(By.linkText("Bypass this screen"));
		return element;
	}
	
	//Objects for call track
	public static WebElement txt_UserNameCallTrack(WebDriver driver) {
		element = driver.findElement(By.name("username"));
		return element;
	}

	public static WebElement txt_PasswordCallTrack(WebDriver driver) {
		element = driver.findElement(By.name("password"));
		return element;
	}

	public static WebElement btn_SubmitCallTrack(WebDriver driver) {
		element = driver.findElement(By
				.xpath("//*[@id='lfrm']/p/input[3]"));
		return element;
	}

}
