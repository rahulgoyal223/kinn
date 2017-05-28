package AM.ReportsAdmin;


import components.Waits;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class ClinicSetting {
	
	private static WebElement element = null;
	private static boolean ischecked = false;
	
	public static void CheckCheckbox(WebDriver driver, WebElement element,boolean unchecked){
		if(!element.isSelected()||(element.isSelected() && unchecked )){
			element.click();
			btn_SaveSettings(driver).click();
		}
	}

	public static boolean SettingValue (WebElement element)	{
		if (element.isSelected() == true){
			ischecked = true;
		}
		return ischecked;
	}
	
	public static WebElement chk_EnableProgressToGoals(WebDriver driver) {
		element = driver.findElement(By.xpath("//*[@id='formSettings']/div/table[2]/tbody/tr[2]/td[2]/input"));
	    Waits.ForElementVisibility(driver, element);
	    return element;
	}
  
  public static WebElement chk_NewWoundCareWorksheet(WebDriver driver) {
		element = driver.findElement(By.xpath("//input[@name='ClinicSettingList'and @value='109']"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}
	
	public static WebElement btn_SaveSettings(WebDriver driver){
		element = driver.findElement(By.id("saveSettings"));
	    Waits.ForElementVisibility(driver, element);
	    return element;
	}
}
