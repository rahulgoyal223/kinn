package AM.Insurance;

import components.Report;
import components.Waits;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.aventstack.extentreports.Status;

public class InsuranceRates {

    private static WebElement element = null;
    private static Select list = null;
    
    
    
    public static Select lst_selectRatePeriod(WebDriver driver) {
		list = new Select(driver.findElement(By.id("ratePeriodKey")));
		return list;
    }
    
    public static WebElement txt_startdate(WebDriver driver) throws Exception {
        element = driver.findElement(By.id("ratePeriodStartDate"));
         Waits.ForElementVisibility(driver, element);
         Waits.ForBrowserLoad(driver);
         return element;
     } 
    
    
    public static  WebElement btn_changestartdate(WebDriver driver) {
	    element = driver.findElement(By.id("newStartDateButton"));
	    Waits.ForElementVisibility(driver, element);
	    return element;
	}
    
    public static  WebElement btn_deleterateperiod(WebDriver driver) {
	    element = driver.findElement(By.id("deleteRatePeriodButton"));
	    Waits.ForElementVisibility(driver, element);
	    return element;
	}
    
    public static  WebElement btn_ShowInactive(WebDriver driver) {
	    element = driver.findElement(By.id(" toggleInactiveLink"));
	    Waits.ForElementVisibility(driver, element);
	    return element;
	}
    
 
    
    public static  WebElement lnk_Insurancerates_snv(WebDriver driver) {
	    element = driver.findElement(By.id("taskGroupHeading_1"));
	    Waits.ForElementVisibility(driver, element);
	    return element;
	}

   public static WebElement txt_allowed(WebDriver driver) throws Exception {
       element = driver.findElement(By.id("Allowed_24711"));
        Waits.ForElementVisibility(driver, element);
        Waits.ForBrowserLoad(driver);
        return element;
    } 
  
    
    
}
