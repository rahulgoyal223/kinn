package AM.Insurance;


import components.Waits;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;



public class PatientInsurance{

    private static WebElement element = null;
    private static Select list = null;
    
    
    public static  WebElement txt_InsuredLastname(WebDriver driver) {
	    element = driver.findElement(By.name("InsuredLastName"));
	    return element;
	}
    
    public static  WebElement txt_InsuredFirstname(WebDriver driver) {
	    element = driver.findElement(By.name("InsuredFirstName"));
	    return element;
	}
    
    public static Select lst_BillingFrequency(WebDriver driver) {
        element = driver.findElement(By.name("insuredGender"));
         list = new Select(driver.findElement(By.name("insuredGender")));
         return list;
     }   
    
    public static  WebElement txt_InsuredSSNa(WebDriver driver) {
	    element = driver.findElement(By.name("SSNa"));
	    return element;
	}
    
    public static  WebElement txt_InsuredSSNb(WebDriver driver) {
	    element = driver.findElement(By.name("SSNb"));
	    return element;
	}
    
    public static  WebElement txt_InsuredSSNc(WebDriver driver) {
	    element = driver.findElement(By.name("SSNc"));
	    return element;
	}
    
    public static  WebElement txt_InsuredDOB(WebDriver driver) {
	    element = driver.findElement(By.name("InsuredDOB"));
	    return element;
	}
    
    public static  WebElement txt_InsuredPhone(WebDriver driver) {
	    element = driver.findElement(By.name("InsuredPhone"));
	    return element;
	}
    
    public static  WebElement txt_InsuredEmployer(WebDriver driver) {
	    element = driver.findElement(By.name("InsuredEmployerName"));
	    return element;
	}
    
    public static  WebElement txt_InsuredPlanname(WebDriver driver) {
	    element = driver.findElement(By.name("InsuredPlanName"));
	    return element;
	}
    
    public static Select lst_position(WebDriver driver) {
        element = driver.findElement(By.name("Position"));
         list = new Select(driver.findElement(By.name("Position")));
         return list;
     }  
    
    public static  WebElement txt_InsuredAddress(WebDriver driver) {
	    element = driver.findElement(By.name("insuredAddressOne"));
	    return element;
	}
    
    public static  WebElement txt_InsuredCity(WebDriver driver) {
	    element = driver.findElement(By.name("insuredCity"));
	    return element;
	}
    
    public static  WebElement txt_InsuredState(WebDriver driver) {
	    element = driver.findElement(By.name("insuredState"));
	    return element;
	}
    
    public static  WebElement txt_InsuredZip(WebDriver driver) {
	    element = driver.findElement(By.name("insuredZipCode"));
	    return element;
	}
    
    public static Select lst_relationtoins(WebDriver driver) {
        element = driver.findElement(By.name("fListInsuredRelationshipKey"));
         list = new Select(driver.findElement(By.name("fListInsuredRelationshipKey")));
         return list;
     } 
    
    public static  WebElement txt_groupname(WebDriver driver) {
	    element = driver.findElement(By.name("GroupNumber"));
	    return element;
	}
    
    public static  WebElement txt_groupnumber(WebDriver driver) {
	    element = driver.findElement(By.name(""));
	    return element;
	}
    
    public static  WebElement txt_policynumber(WebDriver driver) {
	    element = driver.findElement(By.name("PolicyNumber"));
	    return element;
	}
    
    public static  WebElement txt_effectivestart(WebDriver driver) {
	    element = driver.findElement(By.name("EffectiveDate"));
	    return element;
	}
    
    public static  WebElement txt_effectiveend(WebDriver driver) {
	    element = driver.findElement(By.name("EndDate"));
	    return element;
	}
    
    public static Select lst_AOB(WebDriver driver) {
        element = driver.findElement(By.name("AOB"));
         list = new Select(driver.findElement(By.name("AOB")));
         return list;
     }
    
    public static  WebElement txt_deductible(WebDriver driver) {
	    element = driver.findElement(By.name("Deductible"));
	    return element;
	}
    
    public static  WebElement txt_coveragepercent(WebDriver driver) {
	    element = driver.findElement(By.name("CoveragePercent"));
	    return element;
	}
    
    public static  WebElement txt_copay(WebDriver driver) {
	    element = driver.findElement(By.name("CoPayAmount"));
	    return element;
	}
   //edit>insurance 
    public static  WebElement lnk_Insurance(WebDriver driver) {
	    element = driver.findElement(By.linkText("Insurance"));
	    return element;
	}
    
    public static WebElement SelectInsurance(WebDriver driver, String InsuranceName) {
    	element = driver.findElement(By.linkText(InsuranceName));
    	return element;
    }    
    
    public static  WebElement btn_update(WebDriver driver) {
	    element = driver.findElement(By.name("submit"));
	    return element;
	}
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

    
    
}
