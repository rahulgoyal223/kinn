package AM.ReportsAdmin;

import java.net.MalformedURLException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.aventstack.extentreports.Status;

import components.Browser;
import components.Config;
import components.Report;
import components.Waits;


public class ReportsAdmin {
	
	private static WebElement element = null;
	private static Boolean restrictedSettingChecked = null;
	private static Boolean settingChecked = null;
	private static Boolean roleChecked = null;
	
	public static WebDriver turnOnPTG(WebDriver driver){
		WebDriver newDriver = null;
		try{
			String Url = Config.getAppUrl();
			String partialUrl = "/am/usermanager/editdemographic.cfm?amuserkey="+Config.getAppUserKey();
			
			AM.Menu.TopMenu.Select(driver, "Go To/Reports / Admin");
			AM.ReportsAdmin.ReportsAdmin.lnk_RestrictedSetting(driver).click();
			restrictedSettingChecked = AM.ReportsAdmin.RestrictedSetting.ReturnSettingValue(AM.ReportsAdmin.RestrictedSetting.chk_EnableGoalAdmin(driver));
			if(restrictedSettingChecked != true){
				AM.ReportsAdmin.RestrictedSetting.CheckCheckbox(driver, AM.ReportsAdmin.RestrictedSetting.chk_EnableGoalAdmin(driver),false);
			}
			
			// Asserting section #1
			settingChecked = AM.ReportsAdmin.RestrictedSetting.ReturnSettingValue(AM.ReportsAdmin.RestrictedSetting.chk_EnableProgressToGoals(driver));
			if (settingChecked != true){
				AM.ReportsAdmin.RestrictedSetting.CheckCheckbox(driver, AM.ReportsAdmin.RestrictedSetting.chk_EnableProgressToGoals(driver), false);
			}
			
			AM.Menu.TopMenu.Select(driver, "Go To/Reports / Admin");
			AM.ReportsAdmin.ReportsAdmin.lnk_Users(driver);
			driver.navigate().to(Url + partialUrl);
			AM.Menu.TopMenu.Select(driver, "Edit/Roles");
			// Asserting section #2
			roleChecked = AM.User.EditRole.ReturnRoleValue(AM.User.EditRole.chk_GoalAdministrationRole(driver));
			
			if (roleChecked != true){
				AM.User.EditRole.CheckCheckbox(driver, AM.User.EditRole.chk_GoalAdministrationRole(driver), false);
				AM.Menu.TopMenu.lnk_Logout(driver);
				driver.quit();
				try {
					AM.Login.openAppAndSubmitCredentials();
					newDriver = Browser.getDriver();
			    } catch (MalformedURLException e) {
			        System.out.println("Error: " + e.getMessage());
			        e.printStackTrace();
			    }	
			}
			else{
				newDriver = driver;
			}
		}
		catch(Exception e){
			Report.Log(Status.FAIL, e.getMessage());
			Assert.fail(e.getMessage());
			e.printStackTrace();
		}
		return newDriver;
		
	}
	
	public static void turnOffPTG(WebDriver driver){
		try{
			String Url = Config.getAppUrl();
			String partialUrl = "am/usermanager/editdemographic.cfm?amuserkey="+Config.getAppUserKey();
			
			if (roleChecked != true){
				AM.Menu.TopMenu.Select(driver, "Go To/Reports/Admin");
				AM.ReportsAdmin.ReportsAdmin.lnk_Users(driver);
				driver.navigate().to(Url + partialUrl);
				AM.Menu.TopMenu.Select(driver, "Edit/Roles");
				AM.User.EditRole.CheckCheckbox(driver, AM.User.EditRole.chk_GoalAdministrationRole(driver), true);
			}
	
			if (settingChecked != true){
				AM.Menu.TopMenu.Select(driver, "Go To/Reports / Admin");
				AM.ReportsAdmin.ReportsAdmin.lnk_RestrictedSetting(driver).click();
				AM.ReportsAdmin.RestrictedSetting.CheckCheckbox(driver, AM.ReportsAdmin.RestrictedSetting.chk_EnableProgressToGoals(driver), true);
			}
			
			if(restrictedSettingChecked != true){
				AM.Menu.TopMenu.Select(driver, "Go To/Reports / Admin");
				AM.ReportsAdmin.ReportsAdmin.lnk_RestrictedSetting(driver).click();
				AM.ReportsAdmin.RestrictedSetting.CheckCheckbox(driver, AM.ReportsAdmin.RestrictedSetting.chk_EnableGoalAdmin(driver),true);
			}
		}
		catch(Exception e){
			Report.Log(Status.FAIL, e.getMessage());
			Assert.fail(e.getMessage());
			e.printStackTrace();
		}
	}
	
	
	//ADMINISTRATION
	public static WebElement lnk_RestrictedSetting(WebDriver driver) {
        element = driver.findElement(By.linkText("Restricted Clinic Settings"));
        Waits.ForElementVisibility(driver, element);
        return element;
    }
	
	public static WebElement lnk_ClinicSetting(WebDriver driver) {
        element = driver.findElement(By.linkText("Clinic Settings"));
        Waits.ForElementVisibility(driver, element);
        return element;
    }
	
	public static WebElement lnk_Users(WebDriver driver){
		element = driver.findElement(By.linkText("Users"));
        Waits.ForElementVisibility(driver, element);
        return element;
	}
	public static WebElement lnk_GoalsAdmin(WebDriver driver){
		element = driver.findElement(By.linkText("Goal / Intervention Administration"));
        Waits.ForElementVisibility(driver, element);
        return element;
	}
}