package AM.Billing.Reports;

import java.util.List;

import components.Report;
import components.Waits;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.aventstack.extentreports.Status;


public class Common_ReportsFilters {
	
	
	private static WebElement element;
	 private static Select list = null;
	 
//Commom filters across the reports
	 //Select ALL 
	  public static  WebElement btn_branch_selectall(WebDriver driver) {
		 		    element = driver.findElement(By.id("filter_Branch_btn-group")).findElement(By.id("filter_Branch_btn_select_all"));
		 		    Waits.ForElementVisibility(driver, element);
		 		    return element;
		 		}
	
	  public static  WebElement btn_payertype_selectall(WebDriver driver) {
		    element = driver.findElement(By.id("filter_Payer_Type_btn_select_all")).findElement(By.id("filter_Payer_Type_btn_select_all"));
		    Waits.ForElementVisibility(driver, element);
		    return element;
		}
	
	  public static  WebElement btn_insurance_selectall(WebDriver driver) {
	    element = driver.findElement(By.id("filter_Insurance_btn-group")).findElement(By.id("filter_Insurance_btn_select_all"));
	    Waits.ForElementVisibility(driver, element);
	    return element;
	}
	
   //Clear ALL
	  public static  WebElement btn_branch_clearall(WebDriver driver) {
		    element = driver.findElement(By.id("filter_Branch_btn-group")).findElement(By.id("filter_Branch_btn_clear_all"));
		    Waits.ForElementVisibility(driver, element);
		    return element;
		}

     public static  WebElement btn_payertype_clearall(WebDriver driver) {
      element = driver.findElement(By.id("filter_Payer_Type_btn_select_all")).findElement(By.id("filter_Payer_Type_btn_clear_all"));
      Waits.ForElementVisibility(driver, element);
      return element;
       }

     public static  WebElement btn_insurance_clearall(WebDriver driver) {
        element = driver.findElement(By.id("filter_Insurance_btn-group")).findElement(By.id("filter_Insurance_btn_clear_all"));
         Waits.ForElementVisibility(driver, element);
        return element;
        }
     
 	public static  WebElement btn_ApplyFilters(WebDriver driver) {
	    element = driver.findElement(By.xpath("//button[contains(text(),'Apply Filters')]"));
	    Waits.ForElementVisibility(driver, element);
	    return element;
	}
	
	
		

	


		
	
	
	


}
	
//	
	
	
	
	
	
		

