package KH.Facilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.aventstack.extentreports.Status;

import DataSource.Datatable;
import DataSource.GlobalData;
import components.Report;
import components.Waits;

public class AddNewFacility {
	static String strInputValue;
	private static WebElement element = null;
	private static Select list = null;

	// @ Create Facility Screen Objects
	// ## Facility Type
	
	public static void FillAddNewFacility(WebDriver driver) throws Exception {

        try {
        	Waits.ForBrowserLoad(driver);
        	strInputValue = Datatable.GetValue("FC_FacilityType");
        	if (!strInputValue.trim().isEmpty()) {
        		lst_FI_FacilityType(driver).selectByVisibleText(strInputValue);
            }
        	
        	strInputValue = Datatable.GetValue("FC_FacilityName");
        	if (!strInputValue.trim().isEmpty()) {
        		txt_FI_FacilityName(driver).clear();
        		txt_FI_FacilityName(driver).sendKeys(strInputValue);
                GlobalData.setFacilityName(strInputValue);
            }
        	
        	strInputValue = Datatable.GetValue("FC_Address1");
        	if (!strInputValue.trim().isEmpty()) {
        		txt_FI_Address1(driver).clear();
        		txt_FI_Address1(driver).sendKeys(strInputValue);
                GlobalData.setAddressOne(strInputValue);
            }
        	
        	strInputValue = Datatable.GetValue("FC_Zipcode");
        	if (!strInputValue.trim().isEmpty()) {
        		txt_FI_ZipCode(driver).clear();
        		txt_FI_ZipCode(driver).sendKeys(strInputValue);
                GlobalData.setZipCode(strInputValue);
            }
        	
        	strInputValue = Datatable.GetValue("FC_FacilityNPI");
        	if (!strInputValue.trim().isEmpty()) {
        		txt_FI_FacilityNPI(driver).clear();
        		txt_FI_FacilityNPI(driver).sendKeys(strInputValue);
                GlobalData.setFacilityNpi(strInputValue);
            }
        	
        	strInputValue = Datatable.GetValue("FC_Phone1");
        	if (!strInputValue.trim().isEmpty()) {
        		txt_FI_Phone1(driver).clear();
        		txt_FI_Phone1(driver).sendKeys(strInputValue);
            }
        	
        	strInputValue = Datatable.GetValue("FC_Phone2");
			if (!strInputValue.trim().isEmpty()) {
				txt_FI_Phone2(driver).clear();
				txt_FI_Phone2(driver).sendKeys(strInputValue);
			}

			strInputValue = Datatable.GetValue("FC_Phone3");
			if (!strInputValue.trim().isEmpty()) {
				txt_FI_Phone3(driver).clear();
				txt_FI_Phone3(driver).sendKeys(strInputValue);
			}

			strInputValue = Datatable.GetValue("FC_Email");
			if (!strInputValue.trim().isEmpty()) {
				txt_FI_EmailAddress(driver).clear();
				txt_FI_EmailAddress(driver).sendKeys(strInputValue);
			}
			
			Report.Log(Status.PASS, "Facility details have been filled successfully");
		} catch (Exception e) {
			e.printStackTrace();
			Report.Log(Status.FAIL, "Facility details have NOT been filled");
		}
	}
        	
	public static Select lst_FI_FacilityType(WebDriver driver) {
		element = driver.findElement(By.id("facilityTypeKey"));
		list = new Select(driver.findElement(By.id("facilityTypeKey")));
		Waits.ForElementVisibility(driver, element);
		return list;
	}

	// ## Facility Name*
	public static WebElement txt_FI_FacilityName(WebDriver driver) {
		element = driver.findElement(By.id("facilityName"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	// ## Address 1*
	public static WebElement txt_FI_Address1(WebDriver driver) {
		element = driver.findElement(By.id("addressOne"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	// ## Address 2
	public static WebElement txt_FI_Address2(WebDriver driver) {
		element = driver.findElement(By.id("addressTwo"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	// ## Zip Code*
	public static WebElement txt_FI_ZipCode(WebDriver driver) {
		element = driver.findElement(By.id("zipcode"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	// ## Zip Code plus Four
	public static WebElement txt_FI_ZipCodePlusFour(WebDriver driver) {
		element = driver.findElement(By.id("zipCodePlusFour"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	// ## City *
	public static WebElement txt_FI_City(WebDriver driver) {
		element = driver.findElement(By.id("city"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	// ## State*
	public static WebElement txt_FI_State(WebDriver driver) {
		element = driver.findElement(By.id("state"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	// ## Facility NPI*
	public static WebElement txt_FI_FacilityNPI(WebDriver driver) {
		element = driver.findElement(By.id("facilityNPIrequired"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	// ## Phone* (Area Code)
	public static WebElement txt_FI_Phone1(WebDriver driver) {
		element = driver.findElement(By.id("primaryPhonea"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	// ## Phone* (Prefix)
	public static WebElement txt_FI_Phone2(WebDriver driver) {
		element = driver.findElement(By.id("primaryPhoneb"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	// ## Phone* (Last Four)
	public static WebElement txt_FI_Phone3(WebDriver driver) {
		element = driver.findElement(By.id("primaryPhonec"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	// ## Other Phone (Area Code)
	public static WebElement txt_FI_OtherPhone1(WebDriver driver) {
		element = driver.findElement(By.id("otherPhonea"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	// ## Other Phone (Prefix)
	public static WebElement txt_FI_OtherPhone2(WebDriver driver) {
		element = driver.findElement(By.id("otherPhoneb"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	// ## Other Phone (Last Four)
	public static WebElement txt_FI_OtherPhone3(WebDriver driver) {
		element = driver.findElement(By.id("otherPhonec"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	// ## Fax (Area Code)
	public static WebElement txt_FI_Fax1(WebDriver driver) {
		element = driver.findElement(By.id("faxa"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	// ## Fax (Prefix)
	public static WebElement txt_FI_Fax2(WebDriver driver) {
		element = driver.findElement(By.id("faxb"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	// ## Fax (Last Four)
	public static WebElement txt_FI_Faxe3(WebDriver driver) {
		element = driver.findElement(By.id("faxc"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	// ## Primary Contact
	public static WebElement txt_FI_PrimaryContact(WebDriver driver) {
		element = driver.findElement(By.id("primaryContact"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	// ## Email Address
	public static WebElement txt_FI_EmailAddress(WebDriver driver) {
		element = driver.findElement(By.id("emailAddress"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	// ## External Referral
	public static WebElement chk_FI_ExternalReferral(WebDriver driver) {
		element = driver.findElement(By.id("externalReferral"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	// ## Comments
	public static WebElement txt_FI_Comments(WebDriver driver) {
		element = driver.findElement(By.id("comments"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}
	
//	// ## State Contract Number
	public static WebElement res_FI_StateContractNumber(WebDriver driver) {
		element = driver.findElement(By.id("s2id_autogen2_search"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}
	
	// ## Professional Liability Insurance (Yes)
	public static WebElement rbn_FI_ProfLiabilityInsYes(WebDriver driver) {
		element = driver.findElement(By.id("ProfessionalLiabilityInsuranceY"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	// ## Professional Liability Insurance (No)
	public static WebElement rbn_FI_ProfLiabilityInsNo(WebDriver driver) {
		element = driver.findElement(By.id("ProfessionalLiabilityInsuranceN"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}
	
	// ## General Liability Insurance (Yes)
	public static WebElement rbn_FI_GenLiabilityInsYes(WebDriver driver) {
		element = driver.findElement(By.id("GeneralLiabilityInsuranceY"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}
	
	// ## General Liability Insurance (No)
	public static WebElement rbn_FI_GenLiabilityInsNo(WebDriver driver) {
		element = driver.findElement(By.id("GeneralLiabilityInsuranceN"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	// ## Save and Create New Facility
	public static WebElement btn_FI_SaveAndCreate(WebDriver driver) {
		element = driver.findElement(By.id("submitBtn"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}
}
