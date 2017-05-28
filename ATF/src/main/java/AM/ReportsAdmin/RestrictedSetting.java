package AM.ReportsAdmin;

import components.Waits;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class RestrictedSetting {
	
	private static WebElement element = null;
	private static boolean ischecked = false;
	
	
	public static void CheckCheckbox(WebDriver driver, WebElement element,boolean unchecked){
		if(!element.isSelected()||(element.isSelected() && unchecked )){
			element.click();
			btn_SaveChanges(driver).click();
		}
	}

	public static boolean ReturnSettingValue (WebElement element)	{
		if (element.isSelected()){
			ischecked = true;
		}
		return ischecked;
	}
	
	public static WebElement chk_EnableGoalAdmin(WebDriver driver) {
		
		element = driver.findElement(By.id("ClinicSettingList110"));
		Waits.ForElementVisibility(driver, element);
        return element;
	}

	public static WebElement chk_EnableProgressToGoals(WebDriver driver) {
		
		element = driver.findElement(By.id("ClinicSettingList111"));
		return element;
	}

	public static WebElement btn_SaveChanges(WebDriver driver) {
        element = driver.findElement(By.id("button"));
        Waits.ForElementVisibility(driver, element);
        return element;
    }

}