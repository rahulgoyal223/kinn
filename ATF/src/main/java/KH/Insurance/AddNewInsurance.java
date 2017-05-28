package KH.Insurance;

import DataSource.Datatable;
import DataSource.GlobalData;
import components.Report;
import components.Waits;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import com.aventstack.extentreports.Status;

public class AddNewInsurance {
	static String strInputValue;
	private static WebElement element = null;
	private static Select list = null;

	public static void FillAddNewInsurance(WebDriver driver) throws Exception {

        try {
        	strInputValue = Datatable.GetValue("IN_PayerType");
        	if (!strInputValue.trim().isEmpty()) {
        		lst_PI_PayerType(driver).selectByVisibleText(strInputValue);
            }
        	
        	strInputValue = Datatable.GetValue("IN_InvoiceType");
        	if (!strInputValue.trim().isEmpty()) {
        		lst_PI_InvoiceType(driver).selectByVisibleText(strInputValue);
            }
        	
        	strInputValue = Datatable.GetValue("IN_ChargeGrouping");
        	if (!strInputValue.trim().isEmpty()) {
        		lst_PI_ChargeGrouping(driver).selectByVisibleText(strInputValue);
            }

        	strInputValue = Datatable.GetValue("IN_InsuranceName");
            if (!strInputValue.trim().isEmpty()) {
            	txt_PI_InsuranceName(driver).clear();
            	txt_PI_InsuranceName(driver).sendKeys(strInputValue);
                GlobalData.setInsuranceName(strInputValue);
            }
        	
            strInputValue = Datatable.GetValue("IN_InsuranceAbbreviation");
            if (!strInputValue.trim().isEmpty()) {
            	txt_PI_InsuranceAbbreviation(driver).clear();
            	txt_PI_InsuranceAbbreviation(driver).sendKeys(strInputValue);
                GlobalData.setInsuranceAbbreviation(strInputValue);
            }
            
            strInputValue = Datatable.GetValue("IN_AddressOne");
            if (!strInputValue.trim().isEmpty()) {
            	txt_PI_AddressOne(driver).clear();
            	txt_PI_AddressOne(driver).sendKeys(strInputValue);
                GlobalData.setAddressOne(strInputValue);
            }
            
            strInputValue = Datatable.GetValue("IN_AddressTwo");
            if (!strInputValue.trim().isEmpty()) {
            	txt_PI_AddressTwo(driver).clear();
            	txt_PI_AddressTwo(driver).sendKeys(strInputValue);
                GlobalData.setAddressTwo(strInputValue);
            }
            
            strInputValue = Datatable.GetValue("IN_ZipCode");
            if (!strInputValue.trim().isEmpty()) {
            	txt_PI_ZipCode(driver).clear();
            	txt_PI_ZipCode(driver).sendKeys(strInputValue);
                GlobalData.setZipCode(strInputValue);
            }
            
            strInputValue = Datatable.GetValue("IN_PayerId");
            if (!strInputValue.trim().isEmpty()) {
            	txt_EDI_PayerID(driver).clear();
            	txt_EDI_PayerID(driver).sendKeys(strInputValue);
                GlobalData.setPayerId(strInputValue);
            }
            Report.Log(Status.PASS, "Insurance details have been filled successfully");
        } catch (Exception e) {
            e.printStackTrace();
            Report.Log(Status.FAIL, "Insurance details have NOT been filled");
        }
    }
  
	// @New Insurance Objects
	public static WebElement btn_NI_AddInsurance(WebDriver driver) {
		element = driver.findElement(By.id("add-insurance-button"));
		return element;
	}

	// @Payer Information Section Objects
	public static Select lst_PI_PayerType(WebDriver driver) {
		element = driver.findElement(By.id("payer-type"));
		list = new Select(driver.findElement(By.id("payer-type")));
		return list;
	}

	public static Select lst_PI_InvoiceType(WebDriver driver) {
		element = driver.findElement(By.id("invoice-type"));
		list = new Select(driver.findElement(By.id("invoice-type")));
		return list;
	}

	public static Select lst_PI_ChargeGrouping(WebDriver driver) {
		element = driver.findElement(By.id("claim-group-by"));
		list = new Select(driver.findElement(By.id("claim-group-by")));
		return list;
	}

	public static WebElement txt_PI_InsuranceName(WebDriver driver) {
		element = driver.findElement(By.id("insurance-name"));
		return element;
	}

	public static WebElement txt_PI_InsuranceAbbreviation(WebDriver driver) {
		element = driver.findElement(By.id("insurance-abbreviation"));
		return element;
	}

	public static WebElement txt_PI_AddressOne(WebDriver driver) {
		element = driver.findElement(By.id("address-one"));
		return element;
	}

	public static WebElement txt_PI_AddressTwo(WebDriver driver) {
		element = driver.findElement(By.id("address-two"));
		return element;
	}

	public static WebElement txt_PI_ZipCode(WebDriver driver) {
		element = driver.findElement(By.id("zipcode-nr"));
		return element;
	}

	public static WebElement txt_PI_City(WebDriver driver) {
		element = driver.findElement(By.id("city-nr"));
		return element;
	}

	public static WebElement txt_PI_State(WebDriver driver) {
		element = driver.findElement(By.id("state-nr"));
		return element;
	}

	public static WebElement opt_PI_DisplayAllowedAmountYes(WebDriver driver) {
		element = driver.findElement(By.id("replaceChargeWithAllowed1"));
		return element;
	}

	public static WebElement opt_PI_DisplayAllowedAmountNo(WebDriver driver) {
		element = driver.findElement(By.id("replaceChargeWithAllowed0"));
		return element;
	}

	public static Select lst_PI_ICDVersion(WebDriver driver) {
		element = driver.findElement(By.id("icd-version"));
		list = new Select(driver.findElement(By.id("icd-version")));
		return list;
	}

	// @EDI Section Objects
	public static Select lst_EDI_Clearinghouse(WebDriver driver) {
		element = driver.findElement(By.id("clearinghouse"));
		list = new Select(driver.findElement(By.id("clearinghouse")));
		return list;
	}

	public static Select lst_EDI_InterchangeReceiverID(WebDriver driver) {
		element = driver.findElement(By.id("interchange-receiver-id"));
		list = new Select(driver.findElement(By.id("interchange-receiver-id")));
		return list;
	}

	public static WebElement txt_EDI_PayerID(WebDriver driver) {
		element = driver.findElement(By.id("payer-id"));
		return element;
	}

	// @Contact Information Section Objects
	public static WebElement txt_CI_Phone1(WebDriver driver) {
		element = driver.findElement(By.id("phonea"));
		return element;
	}

	public static WebElement txt_CI_Phone2(WebDriver driver) {
		element = driver.findElement(By.id("phoneb"));
		return element;
	}

	public static WebElement txt_CI_Phone3(WebDriver driver) {
		element = driver.findElement(By.id("phonec"));
		return element;
	}

	public static WebElement txt_CI_Fax1(WebDriver driver) {
		element = driver.findElement(By.id("facsimilea"));
		return element;
	}

	public static WebElement txt_CI_Fax2(WebDriver driver) {
		element = driver.findElement(By.id("facsimileb"));
		return element;
	}

	public static WebElement txt_CI_Fax3(WebDriver driver) {
		element = driver.findElement(By.id("facsimilec"));
		return element;
	}

	public static WebElement txt_CI_CurrentBalance(WebDriver driver) {
		element = driver.findElement(By.id("current-balance"));
		return element;
	}

	public static Select lst_CI_WorkWeekBegins(WebDriver driver) {
		element = driver.findElement(By.id("work-week-begins"));
		list = new Select(driver.findElement(By.id("work-week-begins")));
		return list;
	}

}
