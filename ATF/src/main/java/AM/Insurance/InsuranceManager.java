package AM.Insurance;

import components.Waits;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;


public class InsuranceManager {

    private static WebElement element = null;
    private static Select list = null;


	public static void SelectActiveInsurance(WebDriver driver,
			String InsuranceName) throws Exception {

	}
	
    public static WebElement SelectInsurance(WebDriver driver, String InsuranceName) throws InterruptedException {
    	SelectSortedInsurance(driver, InsuranceName).click();
    	element = driver.findElement(By.linkText(InsuranceName));
    	return element;
    }    
    
    public static WebElement SelectSortedInsurance(WebDriver driver, String InsuranceName) throws InterruptedException {
    	char FL = InsuranceName.charAt(0);
    	String FLnew = String.valueOf(FL).toUpperCase();
        element = driver.findElement(By.id("" + FLnew + ""));
        return element;
    }
    
    //@Objects under Insurance
    
    public static Select lst_BillingFrequency(WebDriver driver) {
        element = driver.findElement(By.id("ListBillingFrequencyKey"));
         list = new Select(driver.findElement(By.id("ListBillingFrequencyKey")));
         return list;
     }        

    
    public static WebElement rdb_CI_VisitAuthorizationReq_YES(WebDriver driver) {
        element = driver.findElement(By.id("VisitAuthorizationRequired1"));
        return element;
    }
    
    public static WebElement rdb_CI_VisitAuthorizationReq_NO(WebDriver driver) {
        element = driver.findElement(By.id("VisitAuthorizationRequired0"));
        return element;
    }
    
    public static WebElement rdb_CI_DisplayInAM_YES(WebDriver driver) throws InterruptedException {
        element = driver.findElement(By.id("includeAuthManager1"));
        return element;
    }
    
    public static WebElement rdb_CI_DisplayInAM_NO(WebDriver driver) throws InterruptedException {
        element = driver.findElement(By.id("includeAuthManager0"));
        return element;
    }        
    
    public static WebElement btn_UpdateInsurance(WebDriver driver) throws InterruptedException {
        element = driver.findElement(By.xpath("//input[@value='Update Insurance']"));
        return element;
    } 
    
    //@ Insurance Manager test objects
    public static WebElement lnk_InsuranceIndex(WebDriver driver) {
        element = driver.findElement(By.id("InsuranceName"));
        return element;
    }

	
	public static WebElement txt_allowed(WebDriver driver) throws Exception {
		element=driver.findElement(By.id("m59292"));
		return element;
	}
	
/*	public static Select lst_InsuranceType(WebDriver driver) {
	       element = driver.findElement(By.id("ListInsuranceTypeKey"));
	        list = new Select(driver.findElement(By.id("ListInsuranceTypeKey")));
	        Waits.ForElementVisibility(driver, element);
	        Waits.ForGlobalAjaxLoader(driver);
	        return list;
	    }
*/	

    
       
       public static  WebElement lnk_Insuranceratesbm2(WebDriver driver) {
    	    element = driver.findElement(By.linkText("Insurance Rates (BM2)"));
    	    return element;
    	}
        
     
    


}
