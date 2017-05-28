package AM.Billing.Claims;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.aventstack.extentreports.Status;

import DataSource.Datatable;
import components.Events;
import components.Report;
import components.Waits;

public class HCFA_1500_08_05ClaimWorksheet {
	static WebElement element;
	static String strInputValue = null;
	static Select list;

	public static WebElement CWS_PA_Refresh(WebDriver driver) {
		element = driver.findElement(By.id("refreshBtn"));
		return element;
	}
    
	public static WebElement CWS_PA_Cancel(WebDriver driver) {
		element = driver.findElement(By.id("returnBtn"));
		return element;
	}
	
    public static WebElement CWS_PA_SaveClose(WebDriver driver) {
        element = driver.findElement(By.id("submitBtn"));
        return element;
    }
    public static WebElement CWS_PA_ClaimFrequencyCode(WebDriver driver) {
        element = driver.findElement(By.id("claimFrequencyCode"));
        return element;
    }
    public static WebElement CWS_PA_SaveApprove(WebDriver driver) {
        element = driver.findElement(By.id("approveBtn"));
        return element;
    }  
    
    public static WebElement txt_PD_Remarks(WebDriver driver) {
        element = driver.findElement(By.id("remarks"));
        return element;
    	}
    public static WebElement clickButton(WebDriver driver, String optiontoClick) {
	    element = driver.findElement(By.xpath("//input[@value='"+optiontoClick+"']"));
		return element;					
	}
    
    //Occurence codes objecys

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

    //@Object under RAP worksheet -condition code
    
	public static void SelectFirstConditionCode(WebDriver driver,
			String ConditionCode) throws InterruptedException {
		lst_WS_firstConditionCode(driver).click();
		Events.Fire(driver)
				.moveToElement(lst_WS_firstConditionCodeSearch(driver))
				.click().perform();
		lst_WS_firstConditionCodeSearch(driver).sendKeys(ConditionCode);
		Thread.sleep(Waits.getSleepLevelTwo());
		lst_WS_firstConditionCodeSearch(driver).sendKeys(Keys.ENTER);
	}
   
    public static WebElement lst_WS_firstConditionCode(WebDriver driver) {
		element = driver.findElement(By.xpath("//div[@id='s2id_conditionCode1']//span[@class='select2-arrow']"));
		return element;
	}
    
	public static WebElement lst_WS_firstConditionCodeSearch(WebDriver driver) {
		element = driver
				.findElement(By
						.xpath("/html/body/div[4]/div/input"));
		return element;
	}
	
	public static void SelectSecondConditionCode(WebDriver driver,
			String ConditionCode) throws InterruptedException {
		lst_WS_secondConditionCode(driver).click();
		Events.Fire(driver)
				.moveToElement(lst_WS_secondConditionCodeSearch(driver))
				.click().perform();
		lst_WS_secondConditionCodeSearch(driver).sendKeys(ConditionCode);
		Thread.sleep(Waits.getSleepLevelTwo());
		lst_WS_secondConditionCodeSearch(driver).sendKeys(Keys.ENTER);
	}
   
    public static WebElement lst_WS_secondConditionCode(WebDriver driver) {
		element = driver.findElement(By.xpath("//div[@id='s2id_conditionCode2']//span[@class='select2-arrow']"));
		return element;
	}
    
	public static WebElement lst_WS_secondConditionCodeSearch(WebDriver driver) {
		element = driver
				.findElement(By
						.xpath("/html/body/div[5]/div/input"));
		return element;
	}

    

    public static WebElement verifyEOEClaim(WebDriver driver, int tableno, int rowIndex, int colIndex) {    	
    	element = driver.findElement(By.xpath("  //*[@id='worksheetForm']/div/div[2]/table["+tableno+"]/tbody/tr["+rowIndex+"]/td["+colIndex+"]"));
    	//element = driver.findElement(By.xpath(".//table/tbody/tr["+rowIndex+"]/td["+colIndex+"]"));
    	String txt = element.getText();
       	Report.Log(Status.INFO, ""+txt+" is displayed");
    	return element;
    }
    
    public static WebElement verifyEOEClaimPatientStatus(WebDriver driver,int tableno ,int rowIndex, int colIndex) {    	
     	element = driver.findElement(By.xpath(".//table/tbody/tr["+rowIndex+"]/td["+colIndex+"]"));
      String txt = element.getText(); 
      System.out.println(txt);
      Report.attachScreenShotToReport(driver);
      Report.Log(Status.INFO, ""+txt+" is displayed");
      return element;
      }
    
    public static WebElement HCFA_Labcharge(WebDriver driver) {
        element = driver.findElement(By.id("totalCharge"));
        return element;
    }
    public static void FillOutsideLabCharge(WebDriver driver) throws Exception {

        strInputValue = Datatable.GetValue("HCFA_Outside_LabCharge");
        if (!strInputValue.trim().isEmpty()) {
        	HCFA_Labcharge(driver).clear();
            HCFA_Labcharge(driver).sendKeys(strInputValue);
            }
    }   
    
    

    //@EOE worksheet
   	public static void ExpandManu(WebDriver driver, String menuname) {		
   		boolean isTaskSelected = false;
   		List<WebElement> census = driver.findElements(By.tagName("a"));
   		for(WebElement cen: census) {
   			String centxt = cen.getText();
   			if(centxt.trim().equals(menuname)) {
   				cen.click();
   				break;
   			}
   		}	
   	if (isTaskSelected) {
           Report.Log(Status.PASS, "" + menuname + " is selected successfully");
       } else {
           Report.Log(Status.FAIL, "" + menuname + " is NOT selected successfully");
       }
     }
   	
   	public static WebElement txt_HCFA_ExpandSupply(WebDriver driver) {
           element = driver.findElement(By.id("bundledSuppliesCharge"));
           return element;
       }
   	
   	public static Select lst_AI_PatientStatus(WebDriver driver) throws Exception {
   	   	element = driver.findElement(By.id("admissionDischargeStatus"));
   	   	list = new Select(driver.findElement(By.id("admissionDischargeStatus")));
   	   	Report.Log(Status.INFO, ""+Datatable.GetValue("admissionDischargeStatus")+" is selected");
   	   	return list;
   	}
}  

