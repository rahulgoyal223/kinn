package KH.Facilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import components.Waits;

public class EditFacility {
	private static WebElement element = null;
	private static Select list = null;

	// @Edit Facility Objects
	// ## Facility Type
	public static Select lst_EF_FacilityType(WebDriver driver) {
		element = driver.findElement(By.id("facilityTypeKey"));
		list = new Select(driver.findElement(By.id("facilityTypeKey")));
		Waits.ForElementVisibility(driver, element);
		return list;
	}

	// ## Facility Name*
	public static WebElement txt_EF_FacilityName(WebDriver driver) {
		element = driver.findElement(By.id("facilityName"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	// ## Address 1*
	public static WebElement txt_EF_Address1(WebDriver driver) {
		element = driver.findElement(By.id("addressOne"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	// ## Address 2
	public static WebElement txt_EF_Address2(WebDriver driver) {
		element = driver.findElement(By.id("addressTwo"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	// ## Zip Code*
	public static WebElement txt_EF_ZipCode(WebDriver driver) {
		element = driver.findElement(By.id("zipcode"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	// ## Zip Code plus Four
	public static WebElement txt_EF_ZipCodePlusFour(WebDriver driver) {
		element = driver.findElement(By.id("zipCodePlusFour"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	// ## City *
	public static WebElement txt_EF_City(WebDriver driver) {
		element = driver.findElement(By.id("city"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	// ## State*
	public static WebElement txt_EF_State(WebDriver driver) {
		element = driver.findElement(By.id("state"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	// ## Facility NPI*
	public static WebElement txt_EF_FacilityNPI(WebDriver driver) {
		element = driver.findElement(By.id("facilityNPIrequired"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	// ## Phone* (Area Code)
	public static WebElement txt_EF_Phone1(WebDriver driver) {
		element = driver.findElement(By.id("primaryPhonea"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	// ## Phone* (Prefix)
	public static WebElement txt_EF_Phone2(WebDriver driver) {
		element = driver.findElement(By.id("primaryPhoneb"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	// ## Phone* (Last Four)
	public static WebElement txt_EF_Phone3(WebDriver driver) {
		element = driver.findElement(By.id("primaryPhonec"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	// ## Other Phone (Area Code)
	public static WebElement txt_EF_OtherPhone1(WebDriver driver) {
		element = driver.findElement(By.id("otherPhonea"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	// ## Other Phone (Prefix)
	public static WebElement txt_EF_OtherPhone2(WebDriver driver) {
		element = driver.findElement(By.id("otherPhoneb"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	// ## Other Phone (Last Four)
	public static WebElement txt_EF_OtherPhone3(WebDriver driver) {
		element = driver.findElement(By.id("otherPhonec"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	// ## Fax (Area Code)
	public static WebElement txt_EF_Fax1(WebDriver driver) {
		element = driver.findElement(By.id("faxa"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	// ## Fax (Prefix)
	public static WebElement txt_EF_Fax2(WebDriver driver) {
		element = driver.findElement(By.id("faxb"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	// ## Fax (Last Four)
	public static WebElement txt_EF_Faxe3(WebDriver driver) {
		element = driver.findElement(By.id("faxc"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	// ## Primary Contact
	public static WebElement txt_EF_PrimaryContact(WebDriver driver) {
		element = driver.findElement(By.id("primaryContact"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	// ## Email Address
	public static WebElement txt_EF_EmailAddress(WebDriver driver) {
		element = driver.findElement(By.id("emailAddress"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	// ## External Referral
	public static WebElement chk_EF_ExternalReferral(WebDriver driver) {
		element = driver.findElement(By.id("externalReferral"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	// ## Comments
	public static WebElement txt_EF_Comments(WebDriver driver) {
		element = driver.findElement(By.id("comments"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}
	
//	// ## State Contract Number
	public static WebElement res_EF_StateContractNumber(WebDriver driver) {
		element = driver.findElement(By.id("s2id_autogen2_search"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}
	
	// ## Professional Liability Insurance (Yes)
	public static WebElement rbn_EF_ProfLiabilityInsYes(WebDriver driver) {
		element = driver.findElement(By.id("ProfessionalLiabilityInsuranceY"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	// ## Professional Liability Insurance (No)
	public static WebElement rbn_EF_ProfLiabilityInsNo(WebDriver driver) {
		element = driver.findElement(By.id("ProfessionalLiabilityInsuranceN"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}
	
	// ## General Liability Insurance (Yes)
	public static WebElement rbn_EF_GenLiabilityInsYes(WebDriver driver) {
		element = driver.findElement(By.id("GeneralLiabilityInsuranceY"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}
	
	// ## General Liability Insurance (No)
	public static WebElement rbn_EF_GenLiabilityInsNo(WebDriver driver) {
		element = driver.findElement(By.id("GeneralLiabilityInsuranceN"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	// ## Attachments ("Browse..." Button)
	public static WebElement chk_EF_Attachments(WebDriver driver) {
		element = driver.findElement(By.id("files"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}
	
	// ## Update New Facility
	public static WebElement btn_EF_UpdateFacility(WebDriver driver) {
		element = driver.findElement(By.id("submitBtn"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}
}
