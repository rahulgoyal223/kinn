package KH.Patient;

import com.aventstack.extentreports.Status;

import DataSource.Datatable;
import DataSource.GlobalData;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import components.Events;
import components.Report;
import components.TimeDate;
import components.Waits;



public class AddNewPatient {
	static String strInputValue;
	private static WebElement element = null;
	private static Select list = null;
	
	public static void FillAddNewPatient(WebDriver driver) throws Exception {

		try {

			strInputValue = Datatable.GetValue("PD_LastName");
			if (!strInputValue.trim().isEmpty()) {
				txt_PD_LastName(driver).clear();
				txt_PD_LastName(driver).sendKeys(strInputValue);
				GlobalData.setPatientLastName(strInputValue);
			}

			strInputValue = Datatable.GetValue("PD_FirstName");
            		if (strInputValue.trim().isEmpty() || strInputValue.trim().toLowerCase().equals("dynamicvalue")){
            			strInputValue = "A" + TimeDate.getUniqueInteger();
            			txt_PD_FirstName(driver).clear();
                		txt_PD_FirstName(driver).sendKeys(strInputValue);
                		GlobalData.setPatientFirstName(strInputValue);
            		}else {txt_PD_FirstName(driver).clear();
                    		txt_PD_FirstName(driver).sendKeys(strInputValue);
                    		GlobalData.setPatientFirstName(strInputValue);
            		}      		

			GlobalData.setPatientFullName(GlobalData.getPatientLastName() + ", " + GlobalData.getPatientFirstName());

			strInputValue = Datatable.GetValue("PD_BirthDate");
			if (!strInputValue.trim().isEmpty()) {
				//Events.Fire(driver).moveToElement(dt_PD_BirthDate(driver)).click().perform();
				dt_PD_BirthDate(driver).clear();
				dt_PD_BirthDate(driver).sendKeys(strInputValue);
			}

			strInputValue = Datatable.GetValue("PD_Gender");
			if (!strInputValue.trim().isEmpty()) {
				lst_PD_Gender(driver).selectByVisibleText(strInputValue);
			}

			strInputValue = Datatable.GetValue("PD_SocialSecurityNumber1");
			if (!strInputValue.trim().isEmpty()) {
				txt_PD_SSNumber1(driver).clear();
				Events.Fire(driver).moveToElement(txt_PD_SSNumber1(driver)).click().perform();
				txt_PD_SSNumber1(driver).sendKeys(strInputValue);
			}

			strInputValue = Datatable.GetValue("PD_SocialSecurityNumber2");
			if (!strInputValue.trim().isEmpty()) {
				txt_PD_SSNumber2(driver).clear();
				Events.Fire(driver).moveToElement(txt_PD_SSNumber2(driver)).click().perform();
				txt_PD_SSNumber2(driver).sendKeys(strInputValue);
			}

			strInputValue = Datatable.GetValue("PD_SocialSecurityNumber3");
			if (!strInputValue.trim().isEmpty()) {
				txt_PD_SSNumber3(driver).clear();
				Events.Fire(driver).moveToElement(txt_PD_SSNumber3(driver)).click().perform();
				txt_PD_SSNumber3(driver).sendKeys(strInputValue);
			}

			strInputValue = Datatable.GetValue("PD_ResidenceType");
			if (!strInputValue.trim().isEmpty()) {
				lst_PD_ResidenceType(driver).selectByVisibleText(strInputValue);
			}
			Waits.ForGlobalAjaxLoader(driver);

			if (strInputValue.equals("Home")) {

				// Populate Home Address
				strInputValue = Datatable.GetValue("PD_Address");
				if (!strInputValue.trim().isEmpty()) {
					txt_PD_Address(driver).clear();
					txt_PD_Address(driver).sendKeys(strInputValue);
				}

				strInputValue = Datatable.GetValue("PD_ZipCode");
				if (!strInputValue.trim().isEmpty()) {
					txt_PD_ZipCode(driver).clear();
					txt_PD_ZipCode(driver).sendKeys(strInputValue);
				}

				strInputValue = Datatable.GetValue("PD_ZipCodePlusFour");
				if (!strInputValue.trim().isEmpty()) {
					txt_PD_ZipCodePlusFour(driver).clear();
					txt_PD_ZipCodePlusFour(driver).sendKeys(strInputValue);
				}

				strInputValue = Datatable.GetValue("PD_Phone1");
				if (!strInputValue.trim().isEmpty()) {
					txt_PD_Phone1(driver).clear();
					txt_PD_Phone1(driver).sendKeys(strInputValue);
				}

				strInputValue = Datatable.GetValue("PD_Phone2");
				if (!strInputValue.trim().isEmpty()) {
					txt_PD_Phone2(driver).clear();
					txt_PD_Phone2(driver).sendKeys(strInputValue);
				}

				strInputValue = Datatable.GetValue("PD_Phone3");
				if (!strInputValue.trim().isEmpty()) {
					txt_PD_Phone3(driver).clear();
					txt_PD_Phone3(driver).sendKeys(strInputValue);
				}
			}
			else
			{
				// Populate Facility Name
				strInputValue = Datatable.GetValue("PD_ResidenceName");
				if (!strInputValue.trim().isEmpty()) {
					lst_PD_ResidenceName(driver).selectByVisibleText(strInputValue);
				}
			}

			strInputValue = Datatable.GetValue("RI_ReferralDate");
			if (!strInputValue.trim().isEmpty()) {
				dt_RI_ReferralDate(driver).clear();
				Events.Fire(driver).moveToElement(dt_RI_ReferralDate(driver)).click().perform();
				dt_RI_ReferralDate(driver).sendKeys(strInputValue);
			}

			strInputValue = Datatable.GetValue("RI_Source");
			if (!strInputValue.trim().isEmpty()) {
				lst_RI_Source(driver).selectByVisibleText(strInputValue);
			}

			strInputValue = Datatable.GetValue("RI_ReferringPhysicianAttending");
			if (!strInputValue.trim().isEmpty()) {
				if (strInputValue.equals("N")) {
					opt_RI_ReferringPhysicianAttendingN(driver).click();
				}
				else {
					opt_RI_ReferringPhysicianAttendingY(driver).click();
				}
			}

			strInputValue = Datatable.GetValue("ECI_NoEmergencyContact");
			if (!strInputValue.trim().isEmpty() && strInputValue.equals("Y")) {
				chk_ECI_NoEmergencyContact(driver).click();
			}

			strInputValue = Datatable.GetValue("ADI_ADNo");
			if (!strInputValue.trim().isEmpty() && strInputValue.equals("Y")) {
				opt_ADI_ADNo(driver).click();
			}

			strInputValue = Datatable.GetValue("ADI_POLSTOnFile");
			if (!strInputValue.trim().isEmpty()){
				if (strInputValue.endsWith("Y")){
					opt_ADI_POLSTOnFileYes(driver).click();
				}
				else {
					opt_ADI_POLSTOnFileNo(driver).click();
				}
			}

			Report.Log(Status.PASS, "Patient details have been filled successfully");
		} catch (Exception e) {
			e.printStackTrace();
			Report.Log(Status.FAIL, "Patient details have NOT been filled");
		}
	}

	// @ Test Objects for add new patient screen
	public static WebElement btn_P_SaveAndCreate(WebDriver driver) {
		element = driver.findElement(By.id("submitBtn"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	// @ Objects under section Patient Demographics
	public static WebElement txt_PD_LastName(WebDriver driver) {
		element = driver.findElement(By.id("lastName"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	public static WebElement txt_PD_FirstName(WebDriver driver) {
		element = driver.findElement(By.id("firstName"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	public static WebElement txt_PD_MiddleInitial(WebDriver driver) {
		element = driver.findElement(By.id("middleInitial"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	public static WebElement txt_PD_Suffix(WebDriver driver) {
		element = driver.findElement(By.id("suffix"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	public static WebElement dt_PD_BirthDate(WebDriver driver) {
		element = driver.findElement(By.id("dateOfBirth"));
		return element;
	}

	public static Select lst_PD_Gender(WebDriver driver) {
		element = driver.findElement(By.id("gender"));
		list = new Select(driver.findElement(By.id("gender")));
		Waits.ForElementVisibility(driver, element);
		return list;
	}

	public static WebElement txt_PD_SSNumber1(WebDriver driver) {
		element = driver.findElement(By.id("ssnA"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	public static WebElement txt_PD_SSNumber2(WebDriver driver) {
		element = driver.findElement(By.id("ssnB"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	public static WebElement txt_PD_SSNumber3(WebDriver driver) {
		element = driver.findElement(By.id("ssnC"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	public static WebElement txt_PD_MRN(WebDriver driver) {
		element = driver.findElement(By.id("medicalRecordNumber"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	public static Select lst_PD_PrimaryLanguage(WebDriver driver) {
		element = driver.findElement(By.id("primaryLanguage"));
		list = new Select(driver.findElement(By.id("primaryLanguage")));
		Waits.ForElementVisibility(driver, element);
		return list;
	}

	public static Select lst_PD_PrimaryReligion(WebDriver driver) {
		element = driver.findElement(By.id("primaryReligion"));
		list = new Select(driver.findElement(By.id("primaryReligion")));
		Waits.ForElementVisibility(driver, element);
		return list;
	}

	public static Select lst_PD_Branch(WebDriver driver) {
		element = driver.findElement(By.id("clinicBranch"));
		list = new Select(driver.findElement(By.id("clinicBranch")));
		Waits.ForElementVisibility(driver, element);
		return list;
	}

	public static WebElement chk_PD_RaceAmericanIndian(WebDriver driver) {
		element = driver.findElement(By.id("race-0"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	public static WebElement chk_PD_RaceAsian(WebDriver driver) {
		element = driver.findElement(By.id("race-1"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	public static WebElement chk_PD_RaceAfricanAmerican(WebDriver driver) {
		element = driver.findElement(By.id("race-2"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	public static WebElement chk_PD_RaceHispanic(WebDriver driver) {
		element = driver.findElement(By.id("race-3"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	public static WebElement chk_PD_RaceNativeHawaiian(WebDriver driver) {
		element = driver.findElement(By.id("race-4"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	public static WebElement chk_PD_RaceWhite(WebDriver driver) {
		element = driver.findElement(By.id("race-5"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	public static Select lst_PD_ResidenceType(WebDriver driver) {
		element = driver.findElement(By.id("facilityType"));
		list = new Select(driver.findElement(By.id("facilityType")));
		Waits.ForElementVisibility(driver, element);
		return list;
	}

	public static Select lst_PD_ResidenceName(WebDriver driver) {
		element = driver.findElement(By.id("facility"));
		list = new Select(driver.findElement(By.id("facility")));
		Waits.ForElementVisibility(driver, element);
		return list;
	}

	public static WebElement txt_PD_Address(WebDriver driver) {
		element = driver.findElement(By.id("addressOne"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	public static WebElement txt_PD_ZipCode(WebDriver driver) {
		element = driver.findElement(By.id("zipcode"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	public static WebElement txt_PD_ZipCodePlusFour(WebDriver driver) {
		element = driver.findElement(By.id("zipCodePlusFour"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	public static WebElement txt_PD_City(WebDriver driver) {
		element = driver.findElement(By.id("city"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	public static WebElement txt_PD_State(WebDriver driver) {
		element = driver.findElement(By.id("state"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	public static WebElement txt_PD_Phone1(WebDriver driver) {
		element = driver.findElement(By.id("phoneA"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	public static WebElement txt_PD_Phone2(WebDriver driver) {
		element = driver.findElement(By.id("phoneB"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	public static WebElement txt_PD_Phone3(WebDriver driver) {
		element = driver.findElement(By.id("phoneC"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	public static WebElement txt_PD_EmailAddress(WebDriver driver) {
		element = driver.findElement(By.id("emailAddress"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	public static WebElement chk_PD_CAHPSSurveyOptOut(WebDriver driver) {
		element = driver.findElement(By.id("HHCAHPSOptOut"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	// @ Objects under section Referral Information
	public static WebElement dt_RI_ReferralDate(WebDriver driver) {
		element = driver.findElement(By.id("referralDate"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	public static Select lst_RI_Source(WebDriver driver) {
		element = driver.findElement(By.id("listReferralTypeKey"));
		list = new Select(driver.findElement(By.id("listReferralTypeKey")));
		Waits.ForElementVisibility(driver, element);
		return list;
	}

	public static Select lst_RI_ExternalReferral(WebDriver driver) {
		element = driver.findElement(By.id("facilityKey"));
		list = new Select(driver.findElement(By.id("facilityKey")));
		Waits.ForElementVisibility(driver, element);
		return list;
	}

	public static WebElement txt_RI_ReferringIndividual(WebDriver driver) {
		element = driver.findElement(By.id("referringIndividual"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	public static Select lst_RI_InternalReferral(WebDriver driver) {
		element = driver.findElement(By.id("internalReferral"));
		list = new Select(driver.findElement(By.id("internalReferral")));
		Waits.ForElementVisibility(driver, element);
		return list;
	}

	public static Select lst_RI_ReferringPhysician(WebDriver driver) {
		element = driver.findElement(By.id("physicianKeyReferring"));
		list = new Select(driver.findElement(By.id("physicianKeyReferring")));
		Waits.ForElementVisibility(driver, element);
		return list;
	}

	public static WebElement opt_RI_ReferringPhysicianAttendingY(
			WebDriver driver) {
		element = driver.findElement(By.id("isReferringAttendingY"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	public static WebElement opt_RI_ReferringPhysicianAttendingN(
			WebDriver driver) {
		element = driver.findElement(By.id("isReferringAttendingN"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	public static Select lst_RI_AttendingPhysician(WebDriver driver) {
		element = driver.findElement(By.id("physicianKeyAttending"));
		list = new Select(driver.findElement(By.id("physicianKeyAttending")));
		Waits.ForElementVisibility(driver, element);
		return list;
	}
	
	// @ Objects under section Emergency Contact Information
	public static WebElement chk_ECI_NoEmergencyContact(WebDriver driver) {
		element = driver.findElement(By.id("noEmergencyContact"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	public static WebElement txt_ECI_LastName(WebDriver driver) {
		element = driver.findElement(By.id("contactLastName0"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	public static WebElement txt_ECI_FirstName(WebDriver driver) {
		element = driver.findElement(By.id("contactFirstName0"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	public static Select lst_ECI_Relationship(WebDriver driver) {
		element = driver.findElement(By.id("contactRelationshipType0"));
		list = new Select(driver.findElement(By.id("contactRelationshipType0")));
		Waits.ForElementVisibility(driver, element);
		return list;
	}

	public static WebElement txt_ECI_AddressOne(WebDriver driver) {
		element = driver.findElement(By.id("contactAddressOne0"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	public static WebElement txt_ECI_AddressTwo(WebDriver driver) {
		element = driver.findElement(By.id("contactAddressTwo0"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	public static WebElement txt_ECI_ZipCode(WebDriver driver) {
		element = driver.findElement(By.id("emergencyContact0zipcode"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	public static WebElement txt_ECI_ZipCodePlusFour(WebDriver driver) {
		element = driver.findElement(By.id("emergencyContact0zipCodePlusFour"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	public static WebElement txt_ECI_City(WebDriver driver) {
		element = driver.findElement(By.id("emergencyContact0city"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	public static WebElement txt_ECI_State(WebDriver driver) {
		element = driver.findElement(By.id("emergencyContact0state"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	public static WebElement txt_ECI_Phone1(WebDriver driver) {
		element = driver.findElement(By.id("contactPhone0a"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	public static WebElement txt_ECI_Phone2(WebDriver driver) {
		element = driver.findElement(By.id("contactPhone0b"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	public static WebElement txt_ECI_Phone3(WebDriver driver) {
		element = driver.findElement(By.id("contactPhone0c"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	public static WebElement txt_ECI_EmailAddress(WebDriver driver) {
		element = driver.findElement(By.id("contactEmail0"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	public static WebElement opt_ECI_PowerOfAttorneyFinancial(WebDriver driver) {
		element = driver.findElement(By.id("powerOfAttorneyFinancial0"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	public static WebElement opt_ECI_PowerOfAttorneyMedical(WebDriver driver) {
		element = driver.findElement(By.id("powerOfAttorneyMedical0"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	// @ Objects under section Advance Directive Information
	public static WebElement opt_ADI_ADYesFullCode(WebDriver driver) {
		element = driver.findElement(By.id("signedDirectiveOnFile0"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	public static WebElement opt_ADI_ADYesDNR(WebDriver driver) {
		element = driver.findElement(By.id("signedDirectiveOnFile1"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	public static WebElement opt_ADI_ADLivingWill(WebDriver driver) {
		element = driver.findElement(By.id("signedDirectiveOnFile2"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	public static WebElement opt_ADI_ADNo(WebDriver driver) {
		element = driver.findElement(By.id("signedDirectiveOnFile3"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	public static WebElement dt_ADI_ADDateIssued(WebDriver driver) {
		element = driver.findElement(By.id("advanceDirectiveDate"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	public static WebElement opt_ADI_POLSTOnFileYes(WebDriver driver) {
		element = driver.findElement(By.id("polst1"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	public static WebElement opt_ADI_POLSTOnFileNo(WebDriver driver) {
		element = driver.findElement(By.id("polst2"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	public static WebElement dt_ADI_POLSTDate(WebDriver driver) {
		element = driver.findElement(By.id("polstDate"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	// @ Objects under section Funeral Home Information
	public static Select lst_FHI_Name(WebDriver driver) {
		element = driver.findElement(By.id("funeralHome"));
		list = new Select(driver.findElement(By.id("funeralHome")));
		Waits.ForElementVisibility(driver, element);
		return list;
	}

	// @ Objects under section Pharmacy Information
	public static Select lst_PI_Pharmacy1(WebDriver driver) {
		element = driver.findElement(By.id("pharmacy"));
		list = new Select(driver.findElement(By.id("pharmacy")));
		Waits.ForElementVisibility(driver, element);
		return list;
	}

	public static Select lst_PI_Pharmacy2(WebDriver driver) {
		element = driver.findElement(By.id("pharmacy2"));
		list = new Select(driver.findElement(By.id("pharmacy2")));
		Waits.ForElementVisibility(driver, element);
		return list;
	}
	
	public static void clk_btnYes(WebDriver driver, String option) {
        try {
            List<WebElement> btn = driver.findElements(By.xpath("//*[@type='button']"));
            for (WebElement btns : btn) {
                String btnsTxt = btns.getText();
                if (btnsTxt.equals(option)) {
                    btns.click();
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
