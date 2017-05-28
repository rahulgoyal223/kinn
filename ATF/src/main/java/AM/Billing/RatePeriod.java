package AM.Billing;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import DataSource.Datatable;
import DataSource.GlobalData;

import com.aventstack.extentreports.Status;

import components.Report;
import components.Waits;


public class RatePeriod {
    static String strInputValue;
    private static WebElement element = null;
    private static Select list = null;
    
 
  
    
         public static  WebElement lnk_rateperiod(WebDriver driver) {
	      element = driver.findElement(By.linkText("Rate Periods"));
	      Waits.ForElementVisibility(driver, element);
	      return element;
	        }

		public static  WebElement btn_addrateperiod(WebDriver driver) {
		    element = driver.findElement(By.id("addRatePeriodButton"));
		    Waits.ForElementVisibility(driver, element);
		    return element;
		}
        
		public static  WebElement btn_delrateperiod(WebDriver driver, String rateperiodkey) {
		    element = driver.findElement(By.id("deleteRatePeriodButton_{rateperiodkey}"));
		    Waits.ForElementVisibility(driver, element);
		    return element;
		}
		
		public static  WebElement txt_addrateperiod(WebDriver driver) {
		    element = driver.findElement(By.id("newStartDate"));
		    Waits.ForElementVisibility(driver, element);
		    return element;
		}
        
		
		public static  WebElement btn_add(WebDriver driver) {
		    element = driver.findElement(By.id("addRatePeriodModalFooter")).findElement(By.id("confirmAddNew"));
		    Waits.ForElementVisibility(driver, element);
		    return element;
		}
		

		public static  WebElement btn_cancel(WebDriver driver) {
		    element = driver.findElement(By.id("addRatePeriodModalFooter")).findElement(By.id("cancelAddNew"));
		    Waits.ForElementVisibility(driver, element);
		    Waits.ForGlobalAjaxLoader(driver);
		    return element;
		}
        

}