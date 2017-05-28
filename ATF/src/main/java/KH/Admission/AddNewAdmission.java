package KH.Admission;

import DataSource.Datatable;
import DataSource.GlobalData;
import components.Waits;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.aventstack.extentreports.Status;
import components.Events;
import components.Report;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class AddNewAdmission {
	static String strInputValue;
	private static WebElement element = null;
	private static Select list = null;


	//@ Create Admission Reusable methods.  Uses bare minimum of fields needed to create an Admission.
	public static void FillAddNewAdmission(WebDriver driver) throws Exception {

	//@ The code below can used when creating an admission from pending admissions.
/*		strInputValue = Datatable.GetValue(Datatable.getCurrentRow(), "RI_ReferralDate");
		if (!strInputValue.trim().isEmpty()) {
			dt_RI_ReferralDate(driver).clear();
			dt_RI_ReferralDate(driver).sendKeys(strInputValue);
		}

		strInputValue = Datatable.GetValue(Datatable.getCurrentRow(), "RI_Source");
		if (!strInputValue.trim().isEmpty()) {
			lst_RI_Source(driver).selectByVisibleText(strInputValue);
		}

		strInputValue = Datatable.GetValue(Datatable.getCurrentRow(), "RI_ExternalReferral");
		if (!strInputValue.trim().isEmpty()) {
			lst_RI_ExternalReferral(driver).selectByVisibleText(strInputValue);
		}

		strInputValue = Datatable.GetValue(Datatable.getCurrentRow(), "RI_ReferringIndividual");
		if (!strInputValue.trim().isEmpty()) {
			txt_RI_ReferringIndividual(driver).clear();
			txt_RI_ReferringIndividual(driver).sendKeys(strInputValue);
		}

		strInputValue = Datatable.GetValue(Datatable.getCurrentRow(), "RI_InternalReferral");
		if (!strInputValue.trim().isEmpty()) {
			lst_RI_InternalReferral(driver).selectByVisibleText(strInputValue);
		}

		strInputValue = Datatable.GetValue(Datatable.getCurrentRow(), "RI_ReferringPhysicianName");
		if (!strInputValue.trim().isEmpty()) {
			lst_RI_ReferringPhysicianName(driver).selectByVisibleText(strInputValue);
		}

		strInputValue = Datatable.GetValue(Datatable.getCurrentRow(), "RI_IsReferringPhysicianAttending");
		if (!strInputValue.trim().isEmpty() && strInputValue.equals("Y")) {
			opt_RI_IsReferringPhysicianAttendingY(driver).click();
		} else
		{
			opt_RI_IsReferringPhysicianAttendingN(driver).click();
		}

		strInputValue = Datatable.GetValue(Datatable.getCurrentRow(), "RI_AttendingPhysicianName");
		if (!strInputValue.trim().isEmpty()) {
			lst_RI_AttendingPhsycianName(driver).selectByVisibleText(strInputValue);
		}*/

		strInputValue = Datatable.GetValue("DI_TerminalDiagnosis");
		if (!strInputValue.trim().isEmpty()) {
			txt_DI_TerminalDiagnosis(driver).clear();
			txt_DI_TerminalDiagnosis(driver).sendKeys(strInputValue);
			get_ICDDiagnosis(driver,strInputValue);
		}

		//@ Changing insurance hides the Notice of Election fields
		/*
		strInputValue = Datatable.GetValue(Datatable.getCurrentRow(), "II_Insurance");
		if (!strInputValue.trim().isEmpty()) {
			lst_II_Insurance(driver).selectByVisibleText(strInputValue);
		}
		*/

		strInputValue = Datatable.GetValue("II_MedicareNumber");
		if (!strInputValue.trim().isEmpty()) {
			txt_II_MedicareNumber(driver).clear();
			txt_II_MedicareNumber(driver).sendKeys(strInputValue);
		}

		strInputValue = Datatable.GetValue("II_MedicaidNumber");
		if (!strInputValue.trim().isEmpty()) {
			txt_II_MedicaidNumber(driver).clear();
			Events.Fire(driver).moveToElement(txt_II_MedicaidNumber(driver)).click().perform();
			txt_II_MedicaidNumber(driver).sendKeys(strInputValue);
		}

		strInputValue = Datatable.GetValue("AB_StartOfCareDate");
		if (!strInputValue.trim().isEmpty()) {
			//dt_AB_StartOfCareDate(driver).clear();
			Events.Fire(driver).moveToElement(dt_AB_StartOfCareDate(driver)).click().perform();
			dt_AB_StartOfCareDate(driver).sendKeys(strInputValue);
		}

		//@ These values auto-fill
		/*
		strInputValue = Datatable.GetValue(Datatable.getCurrentRow(), "AB_AdmissionDate");
		if (!strInputValue.trim().isEmpty()) {
			dt_AB_AdmissionDate(driver).clear();
			dt_AB_AdmissionDate(driver).sendKeys(strInputValue);
		}

		strInputValue = Datatable.GetValue(Datatable.getCurrentRow(), "AB_BenefitPeriodBeginningDate");
		if (!strInputValue.trim().isEmpty()) {
			dt_AB_BenefitPeriodBeginningDate(driver).clear();
			dt_AB_BenefitPeriodBeginningDate(driver).sendKeys(strInputValue);
		}
		*/
		strInputValue = Datatable.GetValue("AB_AdmissionHour");
		if (!strInputValue.trim().isEmpty()) {
			lst_AB_AdmissionHour(driver).selectByVisibleText(strInputValue);
		}

		strInputValue = Datatable.GetValue("AB_NoticeOfElection");
		if (!strInputValue.trim().isEmpty()) {
			dt_AB_NoticeOfElectionDate(driver).clear();
			dt_AB_NoticeOfElectionDate(driver).sendKeys(strInputValue);
		}

		strInputValue = Datatable.GetValue("AB_NoticeOfElectionConfirmationDate");
		if (!strInputValue.trim().isEmpty()) {
			dt_AB_NoticeOfElectionConfirmationDate(driver).clear();
			dt_AB_NoticeOfElectionConfirmationDate(driver).sendKeys(strInputValue);
		}

		strInputValue = Datatable.GetValue("AB_Readmission");
		if (!strInputValue.trim().isEmpty()) {
			lst_AB_Readmission(driver).selectByVisibleText(strInputValue);
		}

		strInputValue = Datatable.GetValue("AB_AdmissionStatus");
		if (!strInputValue.trim().isEmpty()) {
			lst_AB_ReadmissionStatus(driver).selectByVisibleText(strInputValue);
		}

		strInputValue = Datatable.GetValue("AB_SameAsResidence");
		if (!strInputValue.trim().isEmpty() && strInputValue.equals("Y")) {
			chk_AB_SameAsResidence(driver).click();
		}

		strInputValue = Datatable.GetValue("AB_CaseManager");
		if (!strInputValue.trim().isEmpty()) {
			lst_AB_CaseManager(driver).selectByVisibleText(strInputValue);
		}

		strInputValue = Datatable.GetValue("AB_PatientBeingAddmittedOn");
		if (!strInputValue.trim().isEmpty()) {
			txt_AB_PatientAdmittedOn(driver).clear();
			txt_AB_PatientAdmittedOn(driver).sendKeys(strInputValue);
		}

		strInputValue = Datatable.GetValue("IG_Physician");
		if (!strInputValue.trim().isEmpty()) {
			lst_IG_Physician(driver).selectByVisibleText(strInputValue);
		}
		strInputValue = Datatable.GetValue("IG_RegisteredNurse");
		if (!strInputValue.trim().isEmpty()) {
			lst_IG_RegisteredNurse(driver).selectByVisibleText(strInputValue);
		}
		strInputValue = Datatable.GetValue("IG_SocialWorker");
		if (!strInputValue.trim().isEmpty()) {
			lst_IG_SocialWorker(driver).selectByVisibleText(strInputValue);
		}
		strInputValue = Datatable.GetValue("IG_ChaplainCounselor");
		if (!strInputValue.trim().isEmpty()) {
			lst_IG_ChaplainCounselor(driver).selectByVisibleText(strInputValue);
		}
		strInputValue = Datatable.GetValue("IG_VolunteerCoordinator");
		if (!strInputValue.trim().isEmpty()) {
			lst_IG_VolunteerCoordinator(driver).selectByVisibleText(strInputValue);
		}


		try {


		} catch (Exception e) {
			e.printStackTrace();
			Report.Log(Status.FAIL, "Patient details have NOT been filled");
		}
	}



			//@ Create Admission Screen test objects
	//@ Referral Information
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

	public static Select lst_RI_ReferringPhysicianName(WebDriver driver) {
		element = driver.findElement(By.id("physicianKeyReferring"));
		list = new Select(driver.findElement(By.id("physicianKeyReferring")));
		Waits.ForElementVisibility(driver, element);
		return list;
	}

	public static WebElement opt_RI_IsReferringPhysicianAttendingY(WebDriver driver) {
		element = driver.findElement(By.id("isReferringAttendingY"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	public static WebElement opt_RI_IsReferringPhysicianAttendingN(WebDriver driver) {
		element = driver.findElement(By.id("isReferringAttendingN"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	public static Select lst_RI_AttendingPhsycianName(WebDriver driver) {
		element = driver.findElement(By.id("physicianKeyAttending"));
		list = new Select(driver.findElement(By.id("physicianKeyAttending")));
		Waits.ForElementVisibility(driver, element);
		return list;
	}

	//@ Diagnosis Information
	public static WebElement txt_DI_TerminalDiagnosis(WebDriver driver) {
		element = driver.findElement(By.name("td"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	//@ Insurance Information
	public static Select lst_II_Insurance(WebDriver driver) {
		element = driver.findElement(By.id("patientInsurance"));
		list = new Select(driver.findElement(By.id("patientInsurance")));
		Waits.ForElementVisibility(driver, element);
		return list;
	}

	public static WebElement txt_II_MedicareNumber(WebDriver driver) {
		element = driver.findElement(By.id("policyNumber"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	public static WebElement txt_II_MedicaidNumber(WebDriver driver) {
		element = driver.findElement(By.cssSelector("div.ng-scope > div.control-group > div.controls > #medicaidNumber"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	public static WebElement dt_II_StartOfCareMedicaidRoomAndBoard(WebDriver driver) {
		element = driver.findElement(By.id("roomAndBoardStartDate-0"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	//@ Admission & Benefit Period Information
	public static WebElement dt_AB_StartOfCareDate(WebDriver driver) {
		element = driver.findElement(By.id("startOfCare"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	public static WebElement dt_AB_AdmissionDate(WebDriver driver) {
		element = driver.findElement(By.id("admissionDate"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	public static WebElement dt_AB_BenefitPeriodBeginningDate(WebDriver driver) {
		element = driver.findElement(By.id("benefitPeriodStartDate"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	public static WebElement dt_AB_NoticeOfElectionDate(WebDriver driver) {
		element = driver.findElement(By.id("noticeOfElectionDate"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	public static WebElement dt_AB_NoticeOfElectionConfirmationDate(WebDriver driver) {
		element = driver.findElement(By.id("medicareConfirmationDate"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}
	public static Select lst_AB_AdmissionHour(WebDriver driver) {
		element = driver.findElement(By.id("admissionHour"));
		list = new Select(driver.findElement(By.id("admissionHour")));
		Waits.ForElementVisibility(driver, element);
		return list;
	}

	public static Select lst_AB_Readmission(WebDriver driver) {
		element = driver.findElement(By.id("readmission"));
		list = new Select(driver.findElement(By.id("readmission")));
		Waits.ForElementVisibility(driver, element);
		return list;
	}

	public static Select lst_AB_ReadmissionStatus(WebDriver driver) {
		element = driver.findElement(By.id("LoCType"));
		list = new Select(driver.findElement(By.id("LoCType")));
		Waits.ForElementVisibility(driver, element);
		return list;
	}

	public static WebElement chk_AB_SameAsResidence(WebDriver driver) {
		element = driver.findElement(By.id("initialPlaceOfService"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	public static Select lst_AB_InitialPlaceOfService(WebDriver driver) {
		element = driver.findElement(By.id("LoCResidence"));
		list = new Select(driver.findElement(By.id("LoCResidence")));
		Waits.ForElementVisibility(driver, element);
		return list;
	}

	public static Select lst_AB_Facility(WebDriver driver) {
		element = driver.findElement(By.id("LoCFacility"));
		list = new Select(driver.findElement(By.id("LoCFacility")));
		Waits.ForElementVisibility(driver, element);
		return list;
	}

	public static Select lst_AB_CaseManager(WebDriver driver) {
		element = driver.findElement(By.id("CaseManager"));
		list = new Select(driver.findElement(By.id("CaseManager")));
		Waits.ForElementVisibility(driver, element);
		return list;
	}

	public static WebElement txt_AB_PatientAdmittedOn(WebDriver driver) {
		element = driver.findElement(By.id("benefitPeriodCount"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	public static Select lst_AB_AreNewAssessmentsNeeded(WebDriver driver) {
		element = driver.findElement(By.id("newAssessmentNeeded"));
		list = new Select(driver.findElement(By.id("newAssessmentNeeded")));
		Waits.ForElementVisibility(driver, element);
		return list;
	}

	//@ Interdisciplinary Group Assignment
	public static Select lst_IG_Physician(WebDriver driver) {
		element = driver.findElement(By.id("idgPhysician"));
		list = new Select(driver.findElement(By.id("idgPhysician")));
		Waits.ForElementVisibility(driver, element);
		return list;
	}
	public static Select lst_IG_RegisteredNurse(WebDriver driver) {
		element = driver.findElement(By.id("idgRN"));
		list = new Select(driver.findElement(By.id("idgRN")));
		Waits.ForElementVisibility(driver, element);
		return list;
	}
	public static Select lst_IG_SocialWorker(WebDriver driver) {
		element = driver.findElement(By.id("idgMSW"));
		list = new Select(driver.findElement(By.id("idgMSW")));
		Waits.ForElementVisibility(driver, element);
		return list;
	}
	public static Select lst_IG_ChaplainCounselor(WebDriver driver) {
		element = driver.findElement(By.id("idgChaplain"));
		list = new Select(driver.findElement(By.id("idgChaplain")));
		Waits.ForElementVisibility(driver, element);
		return list;
	}	public static Select lst_IG_VolunteerCoordinator(WebDriver driver) {
		element = driver.findElement(By.id("idgVolunteer"));
		list = new Select(driver.findElement(By.id("idgVolunteer")));
		Waits.ForElementVisibility(driver, element);
		return list;
	}

	//@ Save and Submit
	public static WebElement btn_BP_SaveAndSubmit(WebDriver driver) {
		element = driver.findElement(By.id("submitBtn"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	//@ Get the terminal diagnosis selection from ICD Lookup
	public static void  get_ICDDiagnosis(WebDriver driver, String icdCode) {
		element = driver.findElement(By.cssSelector("div.ks-icd-content.lookup-mode-0 > span.ng-binding"));
		Waits.ForElementVisibility(driver, element);
		element.click();
	}
	
	public static void VerifyPrimaryInsurance(WebDriver driver) throws Exception {

        try {
            	String name = GlobalData.getInsuranceName();
            	lst_II_Insurance(driver).selectByVisibleText(name);
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

