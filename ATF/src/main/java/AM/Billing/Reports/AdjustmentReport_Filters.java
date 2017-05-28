package AM.Billing.Reports;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import components.Waits;

public class AdjustmentReport_Filters {


	 private static WebElement element;
	  private static Select list = null;
		
		public static  WebElement btn_Adjgroupcode_selectall(WebDriver driver) {
		    element = driver.findElement(By.id("filter_Adjustment_Group_Code_btn-group")).findElement(By.id("filter_Adjustment_Group_Code_btn_select_all"));
		    Waits.ForElementVisibility(driver, element);
		    return element;
		}
		

		public static  WebElement btn_Adjreasoncode_selectall(WebDriver driver) {
		    element = driver.findElement(By.id("filter_Adjustment_Reason_Code_btn-group")).findElement(By.id("filter_Adjustment_Reason_Code_btn_select_all"));
		    Waits.ForElementVisibility(driver, element);
		    return element;
		}
		
		public static  WebElement btn_remarkcodecode_selectall(WebDriver driver) {
		    element = driver.findElement(By.id("filter_Remark_Code_btn-group")).findElement(By.id("filter_Remark_Code_btn_select_all"));
		    Waits.ForElementVisibility(driver, element);
		    return element;
		}
		
		
		
		
	}


