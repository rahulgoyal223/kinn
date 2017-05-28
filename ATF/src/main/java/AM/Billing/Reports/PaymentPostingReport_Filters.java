package AM.Billing.Reports;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import components.Waits;

public class PaymentPostingReport_Filters {


	 private static WebElement element;
	  private static Select list = null;
		
		
		
		
		//Payment Postign Report-Specific Filters
			
			
			  public static  WebElement btn_TOB_selectall(WebDriver driver) {
				    element = driver.findElement(By.id("filter_Type_of_Bill_btn-group")).findElement(By.id("filter_Type_of_Bill_btn_select_all"));
				    Waits.ForElementVisibility(driver, element);
				    return element;
				}
				
			  public static  WebElement btn_TOB_clearall(WebDriver driver) {
				    element = driver.findElement(By.id("filter_Type_of_Bill_btn-group")).findElement(By.id("filter_Type_of_Bill_btn_clear_all"));
				    Waits.ForElementVisibility(driver, element);
				    return element;
				}
			  
			  public static  WebElement txt_deposit_start_date(WebDriver driver) {
				    element = driver.findElement(By.id("filter_Deposit_Date_Range_startDate"));
				    Waits.ForElementVisibility(driver, element);
				    return element;
				}
			  
			  public static  WebElement txt_deposit_end_date(WebDriver driver) {
				    element = driver.findElement(By.id("filter_Deposit_Date_Range_endDate"));
				    Waits.ForElementVisibility(driver, element);
				    return element;
				}
		
		
	}


