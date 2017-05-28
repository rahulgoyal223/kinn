package AM.Patient;

import com.aventstack.extentreports.Status;

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

import java.util.List;

public class AddNewPatient {
    static String strInputValue;
    private static WebElement element = null;
    private static Select list = null;

    // @Create Patient Reusable methods
    public static void FillAddNewPatient(WebDriver driver) throws Exception {
    	Waits.ForElementToBeClickable(driver, btn_AddPatient(driver));
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
            	
            }            		
            txt_PD_FirstName(driver).clear();
            txt_PD_FirstName(driver).sendKeys(strInputValue);
            GlobalData.setPatientFirstName(strInputValue);
            strInputValue = Datatable.GetValue("PD_MiddleInitial");
            if (!strInputValue.trim().isEmpty()) {
                txt_PD_MiddleInitial(driver).clear();
                txt_PD_MiddleInitial(driver).sendKeys(strInputValue);
                GlobalData.setPatientMiddleInitial(strInputValue);
                GlobalData.setPatientFullName(GlobalData.getPatientLastName() + ", " + GlobalData.getPatientFirstName() + " " + GlobalData.getPatientMiddleInitial() + "." );
            }else {
            	GlobalData.setPatientFullName(GlobalData.getPatientLastName() + ", " + GlobalData.getPatientFirstName());
            }

            strInputValue = Datatable.GetValue("PD_BirthDate");
            if (!strInputValue.trim().isEmpty()) {
                Events.Fire(driver).moveToElement(dt_PD_BirthDate(driver)).click().perform();
                dt_PD_BirthDate(driver).clear();
                dt_PD_BirthDate(driver).sendKeys(strInputValue);
            }

            strInputValue = Datatable.GetValue("PD_Gender");
            if (!strInputValue.trim().isEmpty()) {
                lst_PD_Gender(driver).selectByVisibleText(strInputValue);
            }

            strInputValue = Datatable.GetValue("PD_SocialSecurityNumber");
            if (!strInputValue.trim().isEmpty()) {
                txt_PD_SSnumber1(driver).clear();
                Events.Fire(driver).moveToElement(txt_PD_SSnumber1(driver)).click().perform();
                txt_PD_SSnumber1(driver).sendKeys(strInputValue);
            }

            strInputValue = Datatable.GetValue("PD_MedicareNumber");
            if (!strInputValue.trim().isEmpty()) {
                txt_PD_Medicarenumber(driver).clear();
                txt_PD_Medicarenumber(driver).sendKeys(strInputValue);
            }

            strInputValue = Datatable.GetValue("PD_MedicaidNumber");
            if (!strInputValue.trim().isEmpty()) {
                txt_PD_Medicaidnumber(driver).clear();
                txt_PD_Medicaidnumber(driver).sendKeys(strInputValue);
            }
            
            strInputValue = Datatable.GetValue("PD_MrecordNumber");
            if (strInputValue.trim().isEmpty() || strInputValue.trim().toLowerCase().equals("dynamicvalue")){
            	strInputValue = TimeDate.getUniqueInteger();
            	txt_PD_MRecorddnumber(driver).clear();
            	txt_PD_MRecorddnumber(driver).sendKeys(strInputValue);
                GlobalData.setPatientMRNumber(strInputValue);
            }

            strInputValue = Datatable.GetValue("PD_LocationHS");
            if (!strInputValue.trim().isEmpty()) {
                lst_PD_LocationHS(driver).selectByVisibleText(strInputValue);
            }

            // Emergency Contact
            strInputValue = Datatable.GetValue("EC_ContactName");
            if (!strInputValue.trim().isEmpty()) {
                txt_EC_Name(driver).clear();
                txt_EC_Name(driver).sendKeys(strInputValue);
            }

            strInputValue = Datatable.GetValue("EC_ContactRelationship");
            if (!strInputValue.trim().isEmpty()) {
                txt_EC_Relationship(driver).clear();
                txt_EC_Relationship(driver).sendKeys(strInputValue);
            }

            strInputValue = Datatable.GetValue("EC_ContactAddress");
            if (!strInputValue.trim().isEmpty()) {
                txt_EC_Address(driver).clear();
                txt_EC_Address(driver).sendKeys(strInputValue);
            }

            strInputValue = Datatable.GetValue("EC_ContactPhone");
            if (!strInputValue.trim().isEmpty()) {
                Events.Fire(driver).moveToElement(txt_EC_Phone1(driver)).click().perform();
                txt_EC_Phone1(driver).sendKeys(strInputValue);
            }
            // Referral

            strInputValue = Datatable.GetValue("Ref_Physician");
            if (!strInputValue.trim().isEmpty()) {
                lst_Ref_Physician(driver).selectByValue(strInputValue);
            }


            strInputValue = Datatable.GetValue("Ref_AdmissionSource");
            if (!strInputValue.trim().isEmpty()) {
                lst_Ref_AdmissionSource(driver).selectByVisibleText(strInputValue);
            }

            strInputValue = Datatable.GetValue("Ref_ExternalReferral");
            if (!strInputValue.trim().isEmpty()) {
                lst_Ref_Ereferral(driver).selectByVisibleText(strInputValue);
            }

            strInputValue = Datatable.GetValue("Ref_ReferralDate");
            if (!strInputValue.trim().isEmpty()) {
                Events.Fire(driver).moveToElement(dt_Ref_ReferralDate(driver)).click().perform();
                dt_Ref_ReferralDate(driver).clear();
                dt_Ref_ReferralDate(driver).sendKeys(strInputValue);
            }

            strInputValue = Datatable.GetValue("Ref_InternalReferral");
            if (!strInputValue.trim().isEmpty()) {
                lst_Ref_Ireferral(driver).selectByValue(strInputValue);
            }

            strInputValue = Datatable.GetValue("Ref_AdmissionType");
            if (!strInputValue.trim().isEmpty()) {
                lst_Ref_Admissiontype(driver).selectByVisibleText(strInputValue);
            }

            // Insurance

            strInputValue = Datatable.GetValue("Ins_Primary");
            if (!strInputValue.trim().isEmpty()) {
                lst_Ins_Primary(driver).selectByVisibleText(strInputValue);
            }

            strInputValue = Datatable.GetValue("Ins_Secondary");
            if (!strInputValue.trim().isEmpty()) {
                lst_Ins_Secondary(driver).selectByVisibleText(strInputValue);
            }

            strInputValue = Datatable.GetValue("CreateEpisode_ScheduleSOCV");
            if (!strInputValue.trim().isEmpty() && strInputValue.trim().toLowerCase().equals("yes")) {
                chk_CE_SOCV(driver).click();
            }
            
            strInputValue = Datatable.GetValue("Ins_Tertiary");
            if (!strInputValue.trim().isEmpty()) {
                lst_Ins_tertiory(driver).selectByVisibleText(strInputValue);
            }            
            
            // Create Episode & Schedule Start of Care Visit
            strInputValue = Datatable.GetValue("CE_Clinician");
            if (!strInputValue.trim().isEmpty()) {
                lst_CE_CreateEpisodeAndScheduleStart(driver).selectByValue(strInputValue);
            }

            strInputValue = Datatable.GetValue("CE_SOCDate");
            if (!strInputValue.trim().isEmpty()) {
                Events.Fire(driver).moveToElement(dt_CE_SOCDate(driver)).click().perform();
                dt_CE_SOCDate(driver).clear();
                dt_CE_SOCDate(driver).sendKeys(strInputValue);
            }

            strInputValue = Datatable.GetValue("CE_ScheduledStartOfCare");
            if (!strInputValue.trim().isEmpty()) {
                opt_CE_RadioButton(driver, strInputValue).click();
            }

            strInputValue = Datatable.GetValue("CE_EpisodeStartDate");
            if (!strInputValue.trim().isEmpty()) {
                Events.Fire(driver).moveToElement(dt_CE_EpisodeStartDate(driver)).click().perform();
                dt_CE_EpisodeStartDate(driver).clear();
                dt_CE_EpisodeStartDate(driver).sendKeys(strInputValue);
            }

            strInputValue = Datatable.GetValue("CE_CaseManager");
            if (!strInputValue.trim().isEmpty()) {
                lst_CE_CaseManager(driver).selectByValue(strInputValue);
            }

            strInputValue = Datatable.GetValue("CE_OasisApprover");
            if (!strInputValue.trim().isEmpty()) {
                lst_CE_OasisApprover(driver).selectByValue(strInputValue);
            }

            strInputValue = Datatable.GetValue("CE_PTApprover");
            if (!strInputValue.trim().isEmpty()) {
                lst_CE_PTApprover(driver).selectByValue(strInputValue);
            }

            strInputValue = Datatable.GetValue("CE_OTApprover");
            if (!strInputValue.trim().isEmpty()) {
                lst_CE_OTApprover(driver).selectByValue(strInputValue);
            }

            strInputValue = Datatable.GetValue("CE_STApprover");
            if (!strInputValue.trim().isEmpty()) {
                lst_CE_STApprover(driver).selectByValue(strInputValue);;
            }

            // Patient Address
            strInputValue = Datatable.GetValue("PA_Address");
            if (!strInputValue.trim().isEmpty()) {
                txt_PA_Address(driver).clear();
                txt_PA_Address(driver).sendKeys(strInputValue);
            }

            strInputValue = Datatable.GetValue("PA_ZIPCode");
            if (!strInputValue.trim().isEmpty()) {
                txt_PA_ZIPCode1(driver).clear();
                Events.Fire(driver).moveToElement(txt_PA_ZIPCode1(driver)).click().perform();
                txt_PA_ZIPCode1(driver).clear();
                txt_PA_ZIPCode1(driver).sendKeys(strInputValue);
            }

            strInputValue = Datatable.GetValue("PA_City");
            if (!strInputValue.trim().isEmpty()) {
                if (!txt_PA_City(driver).getText().equals(strInputValue))
                    txt_PA_City(driver).clear();
                txt_PA_City(driver).sendKeys(strInputValue);
            }

            strInputValue = Datatable.GetValue("PA_State");
            if (!strInputValue.trim().isEmpty()) {

                if (!txt_PA_State(driver).getText().equals(strInputValue)) {
                    txt_PA_State(driver).clear();
                    txt_PA_State(driver).sendKeys(strInputValue);
                }
            }

            strInputValue = Datatable.GetValue("PA_Phone");
            if (!strInputValue.trim().isEmpty()) {
                txt_PA_PhoneA(driver).clear();
                Events.Fire(driver).moveToElement(txt_PA_PhoneA(driver)).click().perform();
                txt_PA_PhoneA(driver).sendKeys(strInputValue);
            }

            strInputValue = (Datatable.GetValue("PA_OtherPhone"));
            if (!strInputValue.trim().isEmpty()) {
                txt_PA_OtherPhoneA(driver).clear();
                Events.Fire(driver).moveToElement(txt_PA_OtherPhoneA(driver)).click().perform();
                txt_PA_OtherPhoneA(driver).sendKeys(strInputValue);
            }

            strInputValue = Datatable.GetValue("PA_PatientEmail");
            if (!strInputValue.trim().isEmpty()) {
                txt_PA_PatientEmail(driver).clear();
                txt_PA_PatientEmail(driver).sendKeys(strInputValue);
            }

            strInputValue = Datatable.GetValue("PA_HHCAHPSOptOut");
            if (!strInputValue.trim().isEmpty() && strInputValue.contentEquals("Yes")) {
                chk_PA_PatientOptsOut(driver).click();

            }

            // Pharmacy

            strInputValue = Datatable.GetValue("PH_Name");
            if (!strInputValue.trim().isEmpty()) {
                txt_PH_Name(driver).clear();
                txt_PH_Name(driver).sendKeys(strInputValue);
            }
            strInputValue = Datatable.GetValue("PH_AddressOne");
            if (!strInputValue.trim().isEmpty()) {
                txt_PH_AddressOne(driver).clear();
                txt_PH_AddressOne(driver).sendKeys(strInputValue);
            }
            strInputValue = Datatable.GetValue("PH_AddressTwo");
            if (!strInputValue.trim().isEmpty()) {
                txt_PH_AddressTwo(driver).clear();
                txt_PH_AddressTwo(driver).sendKeys(strInputValue);
            }

            strInputValue = Datatable.GetValue("PH_ZIPCode");
            if (!strInputValue.trim().isEmpty()) {
                txt_PH_ZIPCode1(driver).clear();
                Events.Fire(driver).moveToElement(txt_PH_ZIPCode1(driver)).click().perform();
                txt_PH_ZIPCode1(driver).sendKeys(strInputValue);
            }

            strInputValue = Datatable.GetValue("PH_City");
            if (!strInputValue.trim().isEmpty()) {
                if (!txt_PH_City(driver).getText().equals(strInputValue))
                    txt_PH_City(driver).clear();
                txt_PH_City(driver).sendKeys(strInputValue);
            }

            strInputValue = Datatable.GetValue("PH_State");
            if (!strInputValue.trim().isEmpty()) {
                if (!txt_PH_State(driver).getText().equals(strInputValue))
                    txt_PH_State(driver).clear();
                txt_PH_State(driver).sendKeys(strInputValue);
            }

            strInputValue = (Datatable.GetValue("PH_Phone"));
            if (!strInputValue.trim().isEmpty()) {
                txt_PH_Phone1(driver).clear();
                Events.Fire(driver).moveToElement(txt_PH_Phone1(driver)).click().perform();
                txt_PH_Phone1(driver).sendKeys(strInputValue);
            }
            // Associated Physicians
            strInputValue = Datatable.GetValue("AP_AssociatedPhysician");
            if (!strInputValue.trim().isEmpty()) {
                lst_AP_AssociatedPhysicians(driver).selectByValue(strInputValue);
            }

            // Guarantor
            strInputValue = Datatable.GetValue("GU_ListPatientGuarantorRelationship");
            if (!strInputValue.trim().isEmpty()) {
                lst_GU_Guarantor(driver).selectByVisibleText(strInputValue);
            }

            // Specify Care Type for HH-CAHPS Export Exclusion
            strInputValue = Datatable.GetValue("SC_RoutineMaternity");
            if (!strInputValue.trim().isEmpty() && strInputValue.contentEquals("Yes")) {
                chk_SC_RoutineMaternityCareOnly(driver).click();
            }

            strInputValue = Datatable.GetValue("SC_HospiceCare");
            if (!strInputValue.trim().isEmpty() && strInputValue.contentEquals("Yes")) {
                chk_SC_Hospice(driver).click();
            }

            // Add Flags
            strInputValue = Datatable.GetValue("43654");
            if (!strInputValue.trim().isEmpty() && strInputValue.contentEquals("Yes")) {
            	chk_AF_AngryDogAtHouse(driver).click();
            }

            strInputValue = Datatable.GetValue("43655");
            if (!strInputValue.trim().isEmpty() && strInputValue.contentEquals("Yes")) {
            	chk_AF_FamilyAlert(driver).click();
            }

            strInputValue = Datatable.GetValue("43656");
            if (!strInputValue.trim().isEmpty() && strInputValue.contentEquals("Yes")) {
            	chk_AF_GateCode(driver).click();
            }

            strInputValue = Datatable.GetValue("43653");
            if (strInputValue.contentEquals("Yes")) {
            	chk_AF_SpanishSpeaking(driver).click();
            }
            Report.Log(Status.PASS, "Patient details have been filled successfully");
        } catch (Exception e) {
            e.printStackTrace();
            Report.Log(Status.FAIL, "Patient details have NOT been filled");
            Assert.fail("Patient details have NOT been filled");
        }
    }
    
    public static void AddPatient(WebDriver driver) {
    	try{
    		Waits.ForElementToBeClickable(driver, btn_AddPatient(driver));
    		btn_AddPatient(driver).click();
    		clk_btnYes(driver, "Yes");
    		Verify.ExactPageTitle(driver, "Episode Manager | Kinnser Software");
    	} catch (Exception e) {
    		e.printStackTrace();
    		Report.Log(Status.FAIL, "Patient was NOT added successfully");
    		Assert.fail("Patient was NOT added successfully");
    	}
    }

    public static void UpdatePatient(WebDriver driver) {
    	try{
    		WebElement header = driver.findElement(
    				By.xpath("//h1[not(parent::div/parent::div[@id='releaseNoteContainer'])]"));
        	Waits.ForElementToBeClickable(driver, btn_UpdatePatient(driver));
        	btn_UpdatePatient(driver).click();
        	clk_btnYes(driver, "Yes");
        	Waits.ForElementStaleness(driver, header);
        	Verify.ExactPageHeader(driver, "Patient");
    	} catch (Exception e) {
    		Report.Log(Status.FAIL, "Patient was NOT updated successfully");
    		Assert.fail("Patient was NOT updated succcessfully");
    		e.printStackTrace();
    	}
    	
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

    public static void clk_btnNo(WebDriver driver, String option) {
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

	/*private static String[] splitData(String data, String delimiter) throws Exception {
		parts = Datatable.GetValue(data).split(delimiter);
		return parts;
	} */

    // @ Test Objects for add new patient screen
    // @ Objects under section Patient Demographics

    public static WebElement txt_PD_LastName(WebDriver driver) {
        element = driver.findElement(By.id("Lastname"));
        return element;
    }

    public static WebElement txt_PD_FirstName(WebDriver driver) {
        element = driver.findElement(By.id("firstname"));
        return element;
    }

    public static WebElement txt_PD_MiddleInitial(WebDriver driver) {
        element = driver.findElement(By.name("MiddleInitial"));
        return element;
    }

    public static WebElement dt_PD_BirthDate(WebDriver driver) {
        element = driver.findElement(By.id("DateofBirth"));
        return element;
    }

    public static WebElement dt_Ref_ReferralDate(WebDriver driver) {
        element = driver.findElement(By.id("Referraldate"));
        return element;
    }

    public static WebElement btn_AddPatient(WebDriver driver) {
        element = driver.findElement(By.id("AddPatientButton"));
        return element;
    }

    public static WebElement btn_Yes(WebDriver driver) {
        element = driver.findElement(By.xpath("//*[@type='button']"));
        return element;
    }

    public static WebElement btn_No(WebDriver driver) {
        element = driver.findElement(By.xpath("//*[@type='button']"));
        return element;
    }

    public static Select lst_PD_Gender(WebDriver driver) {
        element = driver.findElement(By.id("Gender"));
        list = new Select(driver.findElement(By.id("Gender")));
        return list;
    }

    public static WebElement txt_PD_SSnumber1(WebDriver driver) {
        element = driver.findElement(By.id("M0064a"));
        return element;
    }

    public static WebElement txt_PD_SSnumber2(WebDriver driver) {
        element = driver.findElement(By.id("M0064b"));
        return element;
    }

    public static WebElement txt_PD_SSnumber3(WebDriver driver) {
        element = driver.findElement(By.id("M0064c"));
        return element;
    }

    public static WebElement txt_PD_Medicarenumber(WebDriver driver) {
        element = driver.findElement(By.id("MedicareNumber"));
        return element;
    }

    public static WebElement txt_PD_Medicaidnumber(WebDriver driver) {
        element = driver.findElement(By.id("MedicaidNumber"));
        return element;
    }

    public static WebElement txt_PD_MRecorddnumber(WebDriver driver) {
        element = driver.findElement(By.id("MedicalRecordNumber"));
        return element;
    }

    public static Select lst_PD_LocationHS(WebDriver driver) {
        list = new Select(driver.findElement(By.id("fListHCPCSCodeKey")));
        return list;
    }

    // @ Objects under section Emergency Contact

    public static WebElement txt_EC_Name(WebDriver driver) {
        element = driver.findElement(By.id("ContactName"));
        return element;
    }

    public static WebElement txt_EC_Relationship(WebDriver driver) {
        element = driver.findElement(By.id("ContactRelationship"));
        return element;
    }

    public static WebElement txt_EC_Address(WebDriver driver) {
        element = driver.findElement(By.id("ContactAddress"));
        return element;
    }

    public static WebElement txt_EC_Phone1(WebDriver driver) {
        element = driver.findElement(By.id("contactphonea"));
        return element;
    }

    public static WebElement txt_EC_Phone2(WebDriver driver) {
        element = driver.findElement(By.id("contactphoneb"));
        return element;
    }

    public static WebElement txt_EC_Phone3(WebDriver driver) {
        element = driver.findElement(By.id("contactphonec"));
        return element;
    }

    // @ Objects under section Referral

    public static Select lst_Ref_Physician(WebDriver driver) {
        list = new Select(driver.findElement(By.id("physicianKey")));
        return list;
    }

    public static Select lst_Ref_AdmissionSource(WebDriver driver) {
        list = new Select(driver.findElement(By.id("Source")));
        return list;
    }

    public static Select lst_Ref_Ereferral(WebDriver driver) {
        list = new Select(driver.findElement(By.id("ExternalReferral")));
        return list;
    }

    public static Select lst_Ref_Ireferral(WebDriver driver) {
        list = new Select(driver.findElement(By.id("AMUserkeyrs")));
        return list;
    }

    public static Select lst_Ref_Admissiontype(WebDriver driver) {
        list = new Select(driver.findElement(By.id("AdmissionType")));
        return list;
    }

    // @ Objects under section Insurance

    public static Select lst_Ins_Primary(WebDriver driver) {
        list = new Select(driver.findElement(By.id("InsuranceKey1")));
        return list;
    }

    public static Select lst_Ins_Secondary(WebDriver driver) {
        list = new Select(driver.findElement(By.id("InsuranceKey2")));
        return list;
    }

    public static Select lst_Ins_tertiory(WebDriver driver) {
        list = new Select(driver.findElement(By.id("InsuranceKey3")));
        return list;
    }

    // Create Episode & Schedule Start of Care Visit
    public static Select lst_CE_CreateEpisodeAndScheduleStart(WebDriver driver) {
        list = new Select(driver.findElement(By.id("AMUserkey")));
        return list;
    }

    public static WebElement dt_CE_SOCDate(WebDriver driver) {
        element = driver.findElement(By.id("SOCDate"));
        return element;
    }

    public static WebElement opt_CE_RadioButton(WebDriver driver, String Menus) throws Exception {
        List<WebElement> radioButtons = driver.findElements(By.xpath("//input[@type='radio']"));
        for (int i = 1; i <= radioButtons.size(); i++) {
            WebElement element = driver.findElement(By.id("typeOfSOC"));
            Waits.ForElementVisibility(driver, element);
            String radioButton = radioButtons.get(i).getText();

            String[] rb = radioButton.split(" ");
            int len = rb.length;
            return radioButtons.get(len);

        }
        return element;
    }

    public static WebElement dt_CE_EpisodeStartDate(WebDriver driver) {
        element = driver.findElement(By.id("EpisodeStartDate"));
        return element;
    }

    public static Select lst_CE_CaseManager(WebDriver driver) {
        list = new Select(driver.findElement(By.id("AMUserkeycm")));
        return list;
    }

    public static Select lst_CE_OasisApprover(WebDriver driver) {
        list = new Select(driver.findElement(By.id("AMUserkeyOASIS")));
        return list;
    }

    public static Select lst_CE_PTApprover(WebDriver driver) {
        list = new Select(driver.findElement(By.id("AMUserkeyPTA")));
        return list;
    }

    public static Select lst_CE_OTApprover(WebDriver driver) {
        list = new Select(driver.findElement(By.id("AMUserkeyOTA")));
        return list;
    }

    public static Select lst_CE_STApprover(WebDriver driver) {
        list = new Select(driver.findElement(By.id("AMUserkeySTA")));
        return list;
    }

    // Patient Address
    public static WebElement txt_PA_Address(WebDriver driver) {
        element = driver.findElement(By.id("AddressOne"));
        return element;
    }

    public static WebElement txt_PA_ZIPCode1(WebDriver driver) {
        element = driver.findElement(By.id("ZIPCode"));
        return element;
    }

    public static WebElement txt_PA_ZIPCode2(WebDriver driver) {
        element = driver.findElement(By.id("ZIPCodePlus4"));
        return element;
    }

    public static WebElement txt_PA_City(WebDriver driver) {
        element = driver.findElement(By.id("city"));
        return element;
    }

    public static WebElement txt_PA_State(WebDriver driver) {
        element = driver.findElement(By.id("state"));
        return element;
    }

    public static WebElement txt_PA_PhoneA(WebDriver driver) {
        element = driver.findElement(By.id("patientphonea"));
        return element;
    }

    public static WebElement txt_PA_PhoneB(WebDriver driver) {
        element = driver.findElement(By.id("patientphoneb"));
        return element;
    }

    public static WebElement txt_PA_PhoneC(WebDriver driver) {
        element = driver.findElement(By.id("patientphonec"));
        return element;
    }

    public static WebElement txt_PA_OtherPhoneA(WebDriver driver) {
        element = driver.findElement(By.id("otherphonea"));
        return element;
    }

    public static WebElement txt_PA_OtherPhoneB(WebDriver driver) {
        element = driver.findElement(By.id("otherphoneb"));
        return element;
    }

    public static WebElement txt_PA_OtherPhoneC(WebDriver driver) {
        element = driver.findElement(By.id("otherphonec"));
        return element;
    }

    public static WebElement txt_PA_PatientEmail(WebDriver driver) {
        element = driver.findElement(By.id("patientEmail"));
        return element;
    }

    public static WebElement chk_PA_PatientOptsOut(WebDriver driver) {
        element = driver.findElement(By.id("HHCAHPSOptOut"));
        return element;
    }

    // Pharmacy
    public static WebElement txt_PH_Name(WebDriver driver) {
        element = driver.findElement(By.id("PharmacyName"));
        return element;
    }

    public static WebElement txt_PH_AddressOne(WebDriver driver) {
        element = driver.findElement(By.id("PharmacyAddressOne"));
        return element;
    }

    public static WebElement txt_PH_AddressTwo(WebDriver driver) {
        element = driver.findElement(By.id("PharmacyAddressTwo"));
        return element;
    }

    public static WebElement txt_PH_ZIPCode1(WebDriver driver) {
        element = driver.findElement(By.id("PharmacyZIPCode"));
        return element;
    }

    public static WebElement txt_PH_ZIPCode2(WebDriver driver) {
        element = driver.findElement(By.id("PharmacyZIPCodePlus4"));
        return element;
    }

    public static WebElement txt_PH_City(WebDriver driver) {
        element = driver.findElement(By.id("PharmacyCity"));
        return element;
    }

    public static WebElement txt_PH_State(WebDriver driver) {
        element = driver.findElement(By.id("PharmacyState"));
        return element;
    }

    public static WebElement txt_PH_Phone1(WebDriver driver) {
        element = driver.findElement(By.id("PharmacyPhonea"));
        return element;
    }

    public static WebElement txt_PH_Phone2(WebDriver driver) {
        element = driver.findElement(By.id("PharmacyPhoneb"));
        return element;
    }

    public static WebElement txt_PH_Phone3(WebDriver driver) {
        element = driver.findElement(By.id("PharmacyPhonec"));
        return element;
    }

    // Associated Physicians
    public static Select lst_AP_AssociatedPhysicians(WebDriver driver) {
        list = new Select(driver.findElement(By.id("associatedPhysician1")));
        return list;
    }

    // Guarantor
    public static Select lst_GU_Guarantor(WebDriver driver) {
        list = new Select(driver.findElement(By.id("ListPatientGuarantorRelationshipKey")));
        return list;
    }

    // Specify Care Type for HH-CAHPS Export Exclusion
    public static WebElement chk_SC_RoutineMaternityCareOnly(WebDriver driver) {
        element = driver.findElement(By.id("RoutineMaternity"));
        return element;
    }

    public static WebElement chk_SC_Hospice(WebDriver driver) {
        element = driver.findElement(By.id("HospiceCare"));
        return element;
    }

    // Add Flags
    public static WebElement chk_AF_AngryDogAtHouse(WebDriver driver) {
        element = driver.findElement(By.id("43654"));
        return element;
    }

    public static WebElement chk_AF_FamilyAlert(WebDriver driver) {
        element = driver.findElement(By.id("43655"));
        return element;
    }

    public static WebElement chk_AF_GateCode(WebDriver driver) {
        element = driver.findElement(By.id("43656"));
        return element;
    }

    public static WebElement chk_AF_SpanishSpeaking(WebDriver driver) {
        element = driver.findElement(By.id("43653"));
        return element;
    }

    public static WebElement win_PossibleMatches(WebDriver driver) {
        element = driver.findElement(By.id("ui-dialog-title-PatientSearch"));
        return element;
    }

    public static WebElement btn_PM_SelectPatient(WebDriver driver) {
        element = driver.findElement(By.linkText("Select Patient"));
        return element;
    }

    public static WebElement btn_PM_Cancel(WebDriver driver) {
        element = driver.findElement(By.id("OverlappingVisitsCancelButton"));
        return element;
    }

    public static Select lst_EL_Add(WebDriver driver) {
        list = new Select(driver.findElement(By.id("createNewOptions")));
        return list;
    }

    public static Select lst_AD_startofcaredate(WebDriver driver) {
        list = new Select(driver.findElement(By.id("admissionKeySelect")));
        return list;
    }

    public static WebElement dt_Episodedatedate(WebDriver driver) {
        element = driver.findElement(By.id("Startdate"));
        return element;
    }

    public static Select lst_primaryinsurance(WebDriver driver) {
        list = new Select(driver.findElement(By.id("primaryInsurance")));
        return list;
    }

    public static Select lst_primaryagent(WebDriver driver) {
        list = new Select(driver.findElement(By.id("Clinician")));
        return list;

    }

    public static WebElement btn_AddEpisode(WebDriver driver) {
        element = driver.findElement(By.id("addEpisodeButton"));
        return element;

    }
    
    public static WebElement chk_CE_SOCV(WebDriver driver) {
    	element = driver.findElement(By.id("SOC"));
        return element;
        
    }
    
    public static WebElement btn_UpdatePatient(WebDriver driver) {
        element = driver.findElement(By.id("updatePatientButton"));
        return element;
    }

    //@step PCR#
    public static WebElement txt_ED_PCRNum(WebDriver driver) {
        element = driver.findElement(By.id("pcrTrackingNumber"));
        return element;
    }
    
    //@step To verify created insurance is visible in primary insurance field 
    public static void VerifyPrimaryInsurance(WebDriver driver) throws Exception {

        try {
            	String name = GlobalData.getInsuranceName();
            	lst_Ins_Primary(driver).selectByVisibleText(name);
            	Report.attachScreenShotToReport(driver);
            	Report.Log(Status.PASS, "Created Insurance is visible");       		
            	Assert.assertTrue(true);
        }
        	catch (Exception e) {
        	e.printStackTrace();
        	Report.Log(Status.FAIL, "Ceated Insurance is not visible");
        	Assert.fail("Ceated Insurance is not visible");
        	}
        }	
    
}
        
    

