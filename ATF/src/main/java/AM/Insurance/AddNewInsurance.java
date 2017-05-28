package AM.Insurance;

import DataSource.Datatable;
import DataSource.GlobalData;
import components.Events;
import components.Report;
import components.TimeDate;
import components.Verify;
import components.Waits;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.aventstack.extentreports.Status;

import java.util.List;

public class AddNewInsurance {
	static String strInputValue;
	private static WebElement element = null;
	private static Select list = null;
	
	
	// @Create Insurance Reusable methods
    public static void FillAddNewInsurance(WebDriver driver) throws Exception {

        try {
        	Verify.ExactPageHeader(driver, "Insurance");
        	strInputValue = Datatable.GetValue("IN_PayerType");
        	if (!strInputValue.trim().isEmpty()) {
        		lst_I_PayerType(driver).selectByVisibleText(strInputValue);
            }
        	
        	strInputValue = Datatable.GetValue("IN_InvoiceType");
        	if (!strInputValue.trim().isEmpty()) {
        		lst_I_InvoiceType(driver).selectByVisibleText(strInputValue);
            }
        	
        	strInputValue = Datatable.GetValue("IN_ChargeGrouping");
        	if (!strInputValue.trim().isEmpty()) {
        		lst_I_ChargeGrouping(driver).selectByVisibleText(strInputValue);
            }
       
        	strInputValue = Datatable.GetValue("IN_ChargeType");
        	if (!strInputValue.trim().isEmpty()) {
        		lst_I_ChargeType(driver).selectByVisibleText(strInputValue);
            }
        	
        	strInputValue = Datatable.GetValue("IN_ParentInsurance");
        	if (!strInputValue.trim().isEmpty()) {
        		lst_I_ParentInsurance(driver).selectByVisibleText(strInputValue);
            }
        	
        	strInputValue = Datatable.GetValue("IN_InsuranceName");
            if (!strInputValue.trim().isEmpty()) {
            	txt_I_InsuranceName(driver).clear();
            	txt_I_InsuranceName(driver).sendKeys(strInputValue);
                GlobalData.setInsuranceName(strInputValue);
            }
        	
            strInputValue = Datatable.GetValue("IN_InsuranceAbbreviation");
            if (!strInputValue.trim().isEmpty()) {
            	txt_I_InsuranceAbbreviation(driver).clear();
            	txt_I_InsuranceAbbreviation(driver).sendKeys(strInputValue);
                GlobalData.setInsuranceAbbreviation(strInputValue);
            }
            Report.Log(Status.PASS, "Insurance details have been filled successfully");
        } catch (Exception e) {
            e.printStackTrace();
            Report.Log(Status.FAIL, "Insurance details have NOT been filled");
        }
    }
    
    public static void AddInsurance(WebDriver driver) {
    	try{
    		WebElement header = driver.findElement(
    				By.xpath("//h1[not(parent::div/parent::div[@id='releaseNoteContainer'])]"));
    		Waits.ForElementToBeClickable(driver, btn_AddInsurance(driver));
            btn_AddInsurance(driver).click();
            Waits.ForElementStaleness(driver, header);
            Verify.ExactPageHeader(driver, "Insurance");
    	} catch (Exception e){
    		Report.Log(Status.FAIL, "Insurance was NOT added successfully");
    		Assert.fail("Insurance was NOT added successfully");
    		e.printStackTrace();
    	}
    }
	// @ Test Objects for add new insurance screen
	// @ Objects under Insurance Section
	public static WebElement btn_AddInsurance(WebDriver driver) {
		element = driver.findElement(By
				.xpath("//*[@id='insForm']/div[5]/div[2]/input"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	public static Select lst_I_PayerType(WebDriver driver) {
		element = driver.findElement(By.id("ListInsuranceTypeKey"));
		list = new Select(driver.findElement(By.id("ListInsuranceTypeKey")));
		Waits.ForElementVisibility(driver, element);
		return list;
	}

	public static WebElement chk_I_MedicareAdvantagePlan(WebDriver driver) {
		element = driver.findElement(By.id("advantagePlan"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	public static Select lst_I_InvoiceType(WebDriver driver) {
		element = driver.findElement(By.id("listClaimFormKey"));
		list = new Select(driver.findElement(By.id("listClaimFormKey")));
		Waits.ForElementVisibility(driver, element);
		return list;
	}

	public static Select lst_I_ChargeGrouping(WebDriver driver) {
		element = driver.findElement(By.id("ListClaimGroupByKey"));
		list = new Select(driver.findElement(By.id("ListClaimGroupByKey")));
		Waits.ForElementVisibility(driver, element);
		return list;
	}

	public static Select lst_I_ChargeType(WebDriver driver) {
		element = driver.findElement(By.id("ListClaimChargeByKey"));
		list = new Select(driver.findElement(By.id("ListClaimChargeByKey")));
		Waits.ForElementVisibility(driver, element);
		return list;
	}

	public static WebElement chk_I_MultiplyChargeByUnits(WebDriver driver) {
		element = driver.findElement(By.id("NotMultiplyUnits"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	public static Select lst_I_BillingFrequency(WebDriver driver) {
		element = driver.findElement(By.id("ListBillingFrequencyKey"));
		list = new Select(driver.findElement(By.id("ListBillingFrequencyKey")));
		Waits.ForElementVisibility(driver, element);
		return list;
	}

	public static Select lst_I_TypeOfBill(WebDriver driver) {
		element = driver.findElement(By.id("TypeOfBillPrefix"));
		list = new Select(driver.findElement(By.id("TypeOfBillPrefix")));
		Waits.ForElementVisibility(driver, element);
		return list;
	}

	public static Select lst_I_ParentInsurance(WebDriver driver) {
		element = driver.findElement(By.id("parentInsuranceKey"));
		list = new Select(driver.findElement(By.id("parentInsuranceKey")));
		Waits.ForElementVisibility(driver, element);
		return list;
	}

	public static WebElement txt_I_InsuranceName(WebDriver driver) {
		element = driver.findElement(By.id("InsuranceName"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	public static WebElement txt_I_InsuranceAbbreviation(WebDriver driver) {
		element = driver.findElement(By.id("InsuranceAbbreviation"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	public static WebElement txt_I_AddressOne(WebDriver driver) {
		element = driver.findElement(By.id("addressOne"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	public static WebElement txt_I_AddressTwo(WebDriver driver) {
		element = driver.findElement(By.id("AddressTwo"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	public static WebElement txt_I_ZIPCode(WebDriver driver) {
		element = driver.findElement(By.id("ZIPCode"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	public static WebElement txt_I_City(WebDriver driver) {
		element = driver.findElement(By.id("city"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	public static WebElement txt_I_State(WebDriver driver) {
		element = driver.findElement(By.id("state"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	public static WebElement txt_I_ProviderCode(WebDriver driver) {
		element = driver.findElement(By.id("insProviderCode"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	public static WebElement txt_I_ProviderNumber(WebDriver driver) {
		element = driver.findElement(By.id("insProviderNumber"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	public static WebElement txt_I_UB04Locator51(WebDriver driver) {
		element = driver.findElement(By.id("ub04Locator51a"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	public static WebElement txt_I_UB04Locator57(WebDriver driver) {
		element = driver.findElement(By.id("ub04Locator57"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	public static WebElement txt_I_UB04Locator81CCa1(WebDriver driver) {
		element = driver.findElement(By.id("ub04Locator81CCa1"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	public static WebElement txt_I_UB04Locator81CCa2(WebDriver driver) {
		element = driver.findElement(By.id("ub04Locator81CCa2"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	public static WebElement txt_I_UB04Locator81CCa3(WebDriver driver) {
		element = driver.findElement(By.id("ub04Locator81CCa3"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	public static WebElement txt_I_HCFA17a1(WebDriver driver) {
		element = driver.findElement(By.id("hcfa17a1"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	public static WebElement txt_I_HCFA17a2(WebDriver driver) {
		element = driver.findElement(By.id("hcfa17a2"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	public static WebElement txt_I_HCFA24i(WebDriver driver) {
		element = driver.findElement(By.id("hcfa24i"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	public static WebElement txt_I_HCFA24j(WebDriver driver) {
		element = driver.findElement(By.id("hcfa24j"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	public static WebElement txt_I_HCFA32b(WebDriver driver) {
		element = driver.findElement(By.id("hcfa32b"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	public static WebElement txt_I_HCFA33b(WebDriver driver) {
		element = driver.findElement(By.id("hcfa33b"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	public static Select lst_I_ICDVersion(WebDriver driver) {
		element = driver.findElement(By.id("ICDVersion"));
		list = new Select(driver.findElement(By.id("ICDVersion")));
		Waits.ForElementVisibility(driver, element);
		return list;
	}

	public static WebElement opt_I_RadioButtonAllowedAmount(WebDriver driver,
			String Menus) throws Exception {
		List<WebElement> radioButtons = driver.findElements(By
				.xpath("//input[@type='radio']"));
		for (int i = 1; i <= radioButtons.size(); i++) {
			WebElement element = driver.findElement(By
					.id("replaceChargeWithAllowed"));
			Waits.ForElementVisibility(driver, element);
			String radioButton = radioButtons.get(i).getText();

			String[] rb = radioButton.split(" ");
			int len = rb.length;
			return radioButtons.get(len);

		}
		return element;
	}

	public static WebElement opt_I_RadioButtonNonBillableSupplies(
			WebDriver driver, String Menus) throws Exception {
		List<WebElement> radioButtons = driver.findElements(By
				.xpath("//input[@type='radio']"));
		for (int i = 1; i <= radioButtons.size(); i++) {
			WebElement element = driver.findElement(By
					.id("suppliesAreNonBillable"));
			Waits.ForElementVisibility(driver, element);
			String radioButton = radioButtons.get(i).getText();

			String[] rb = radioButton.split(" ");
			int len = rb.length;
			return radioButtons.get(len);

		}
		return element;
	}

	public static WebElement chk_I_UnBilledSupplies(WebDriver driver) {
		element = driver.findElement(By.id("markSuppliesNonBillable"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	// EDI
	public static Select lst_EDI_Clearinghouse(WebDriver driver) {
		element = driver.findElement(By.id("listClearingHouseKey"));
		list = new Select(driver.findElement(By.id("listClearingHouseKey")));
		Waits.ForElementVisibility(driver, element);
		return list;
	}

	public static Select lst_EDI_InterchangeReceiverID(WebDriver driver) {
		element = driver.findElement(By.id("ListInterchangeQualifierKey"));
		list = new Select(driver.findElement(By
				.id("ListInterchangeQualifierKey")));
		Waits.ForElementVisibility(driver, element);
		return list;
	}

	public static WebElement txt_EDI_PayerID(WebDriver driver) {
		element = driver.findElement(By.id("payerID"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	// Contact Information
	public static WebElement txt_CI_ContactPerson(WebDriver driver) {
		element = driver.findElement(By.id("Contact"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	public static WebElement txt_CI_ContactEmail(WebDriver driver) {
		element = driver.findElement(By.id("ContactEmail"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	public static WebElement txt_CI_PhoneA(WebDriver driver) {
		element = driver.findElement(By.id("PhoneA"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	public static WebElement txt_CI_PhoneB(WebDriver driver) {
		element = driver.findElement(By.id("PhoneB"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	public static WebElement txt_CI_PhoneC(WebDriver driver) {
		element = driver.findElement(By.id("PhoneC"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	public static WebElement txt_CI_FaxA(WebDriver driver) {
		element = driver.findElement(By.id("FaxA"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	public static WebElement txt_CI_FaxB(WebDriver driver) {
		element = driver.findElement(By.id("FaxB"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	public static WebElement txt_CI_FaxC(WebDriver driver) {
		element = driver.findElement(By.id("FaxC"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

}
