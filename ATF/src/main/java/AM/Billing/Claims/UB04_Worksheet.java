package AM.Billing.Claims;

import components.Select2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import DataSource.Datatable;
import DataSource.GlobalData;
import components.Events;
import components.Waits;

public class UB04_Worksheet {

		static String strInputValue;
	    static WebElement element = null;
	    static Select list = null;
	    
	    public static void FillOccurenceCode(WebDriver driver) throws Exception {
	    
	    strInputValue = Datatable.GetValue("OccurenceCode");
	    if (!strInputValue.trim().isEmpty()) {    
	    	lst_WS_OccuranceCode(driver).selectByVisibleText(strInputValue);
	    	
	    }
	    strInputValue = Datatable.GetValue("occurenceDate1");
	    if (!strInputValue.trim().isEmpty()) {
	        Events.Fire(driver).moveToElement(dt_Occurancedate(driver)).click().perform();
	        dt_Occurancedate(driver).clear();
	        dt_Occurancedate(driver).sendKeys(strInputValue);
	    }
	    strInputValue = Datatable.GetValue("DCN");
	    if (!strInputValue.trim().isEmpty()) {
	    	txt_WS_DCN(driver).clear();
	    	txt_WS_DCN(driver).sendKeys(strInputValue);
	        GlobalData.setPatientLastName(strInputValue);
	    }

	    
	    }
	    //@objects under RAP worksheet
	    
	    //Occurence codes objecys
	       public static Select lst_WS_OccuranceCode(WebDriver driver) {
	    	element = driver.findElement(By.id("occurenceCode1"));
	    	list = new Select(driver.findElement(By.id("occurenceCode1")));
			Waits.ForElementVisibility(driver, element);
			return list;
		}

	      public static WebElement dt_Occurancedate(WebDriver driver) {
	           element = driver.findElement(By.id("occurenceDate1"));
	           return element;
	      }

		   public static WebElement dt_Occurancedate2(WebDriver driver) {
		           element = driver.findElement(By.id("occurenceDate2"));
		           return element;
		       }
		       
		   public static WebElement dt_Occurancedate3(WebDriver driver) {
			           element = driver.findElement(By.id("occurenceDate3"));
			           return element;
			       }   
			  
			public static WebElement dt_Occurancedate4(WebDriver driver) {
				           element = driver.findElement(By.id("occurenceDate4"));
				           return element;
				       }  
				 
		   public static WebElement dt_Occurancedate5(WebDriver driver) {
				           element = driver.findElement(By.id("occurenceDate5"));
				           return element;
				       } 

		    public static WebElement dt_Occurancedate6(WebDriver driver) {
				           element = driver.findElement(By.id("occurenceDate6"));
				           return element;
				       }  

				public static WebElement dt_Occurancedate7(WebDriver driver) {
				           element = driver.findElement(By.id("occurenceDate7"));
				           return element;
				       }

				   public static WebElement dt_Occurancedate8(WebDriver driver) {
				           element = driver.findElement(By.id("occurenceDate8"));
				           return element;
				       }  
				   
				    public static WebElement OccurrenceSpanDateFrom1(WebDriver driver){
				        WebElement element = driver.findElement(By.id("occurenceSpanDateFrom1"));
				        return element;
				    }
				    
				    public static WebElement OccurrenceSpanDateThrough1(WebDriver driver){
				        WebElement element = driver.findElement(By.id("occurenceSpanDateThrough1"));
				        return element;
				    }
				    public static WebElement OccurrenceSpanDateFrom2(WebDriver driver){
				        WebElement element = driver.findElement(By.id("occurenceSpanDateFrom2"));
				        return element;
				    }
				    
				    public static WebElement OccurrenceSpanDateThrough2(WebDriver driver){
				        WebElement element = driver.findElement(By.id("occurenceSpanDateThrough2"));
				        return element;
				    }
	       
	      //@ Object for DCN
           
           public static WebElement txt_WS_DCN(WebDriver driver) {
        	   element = driver.findElement((By.xpath("//input[@ng-model ='claim.payers[0].documentControlNumber']")));
               Waits.ForElementVisibility(driver, element);
               return element;
           }
           
           public static WebElement btn_Refresh(WebDriver driver) {
        	   element = driver.findElement((By.id("refreshBtn")));
               Waits.ForElementVisibility(driver, element);
               return element;
           }
         
               public static WebElement btn_SaveandClose(WebDriver driver) {
            	   element = driver.findElement((By.id("submitBtn")));
                   Waits.ForElementVisibility(driver, element);
                   return element;
            } 
               public static WebElement btn_SaveandApprove(WebDriver driver) {
            	   element = driver.findElement((By.id("approveBtn")));
                   Waits.ForElementVisibility(driver, element);
                   return element;
            }   
               
               public static WebElement btn_Cancel(WebDriver driver) {
            	   element = driver.findElement((By.id("returnBtn")));
                   Waits.ForElementVisibility(driver, element);
                   return element;
            }


	public static void EnterOccurrenceCode(WebDriver driver, String occurenceCodeId, String entry) {
		Select2.EnterSelect2Code(driver, occurenceCodeId, entry);
	}

	public static void EnterConditionCode(WebDriver driver, String conditionCodeId, String entry) {
		Select2.EnterSelect2Code(driver, conditionCodeId, entry);
	}

	public static WebElement GetConditionCode(WebDriver driver, String conditionCodeId) {
		return Select2.GetSelect2DropDown(driver, conditionCodeId);
	}

	public static WebElement GetOccurrenceCode(WebDriver driver, String occurrenceCodeId) {
		return Select2.GetSelect2DropDown(driver, occurrenceCodeId);
	}
	
	
	
}

    

             
       

				     
	



