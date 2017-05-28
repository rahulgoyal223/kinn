package KH.Forms.Nursing;

import DataSource.Datatable;
import components.Events;
import components.Report;
import components.Waits;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.Status;

import java.sql.Driver;
import java.util.List;


public class RNIA {

	private static WebElement element = null;
	private static String strInputValue;
	private static Select list = null;
	public static String txt_HIS_F3000A_Date;
//	public static String rad_HIS_F3000A_Yes1;
	public static String txt_HIS_F3000A_Yes1;
	private static WebDriver driver;
	

	// @Method To select the Form
	public static WebElement SelectForm(WebDriver driver, String formname)
			throws Exception {

		try {
			List<WebElement> allrows = driver.findElements(By.tagName("td"));
			for (WebElement row : allrows) {
				String linkTxt = row.getText();
				if (linkTxt.trim().contains(formname)) {
					row.click();
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return element;
	}

	// @ Demographics Section
	public static WebElement txt_RNIA_TimeIn(WebDriver driver) {
		element = driver.findElement(By.id("frm_timein"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	public static WebElement txt_RNIA_TimeOut(WebDriver driver) {
		element = driver.findElement(By.id("frm_timeout"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	public static WebElement txt_RNIA_VisitDate(WebDriver driver) {
		element = driver.findElement(By.id("frm_visitdate"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	public static WebElement txt_RNIA_AssociatedMileage(WebDriver driver) {
		element = driver.findElement(By.id("associatedMileage"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}
	
	public static WebElement lnk_RNIA_Details(WebDriver driver) {
		element = driver.findElement(By.id("Details1"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}
	
	public static WebElement lnk_RNIA_PrintView(WebDriver driver) {
		element = driver.findElement(By.id("PrintView1"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}
	
	public static WebElement lnk_RNIA_VitalSignsDiagnosis(WebDriver driver) {
		element = driver.findElement(By.xpath("//div[@id='InitialAssessment']//td[.='Vital Signs/Diagnosis']"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}
	
	public static WebElement txt_RNIA_VitalSignsDiagnosis_ESAS(WebDriver driver) {
		element = driver.findElement(By.xpath("//*[@id='ESASdiv']/span"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}
	
	public static WebElement txt_RNIA_VitalSignsDiagnosis_ESAS_PrintView(WebDriver driver) {
		element = driver.findElement(By.xpath("//*[@id='VitalSigns']/div[5]/div[2]/div[4]/div/span"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}
	
	public static WebElement chk_VSD_Decline(WebDriver driver) {
		element = driver.findElement(By.id("lcd_decline_nonDiseaseSpecificDeclineInCinincalStatusGuidelines"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}
	
	public static WebElement rad_HIS_F2000A_0(WebDriver driver) {
		element = driver.findElement(By.id("HIS_AskedAboutCPR0"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}
	
	public static WebElement rad_HIS_F2100A_0(WebDriver driver) {
		element = driver.findElement(By.id("HIS_AskedAboutTreatmentsOther0"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}
	
	public static WebElement rad_HIS_F2200A_0(WebDriver driver) {
		element = driver.findElement(By.id("HIS_AskedAboutHospitalization0"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}
	
	public static WebElement rad_HIS_J0900A_0(WebDriver driver) {
		element = driver.findElement(By.id("HIS_ScreenedForPain0"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}
	
	public static WebElement rad_HIS_J2030A_0(WebDriver driver) {
		element = driver.findElement(By.id("HIS_ScreenedForShortnessOfBreath0"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}
	
	public static WebElement rad_HIS_J2040A_0(WebDriver driver) {
		element = driver.findElement(By.id("HIS_TreatmentShortnessOfBreath0"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}
	
	public static WebElement rad_Primary_Caregiver_No(WebDriver driver) {
		element = driver.findElement(By.id("saR2"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}
	
	public static WebElement rad_Hospice_Aide_Needed_Yes(WebDriver driver) {
		element = driver.findElement(By.id("saR31"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}
	
	public static WebElement rad_HIS_F3000A_Yes1(WebDriver driver) {
		element = driver.findElement(By.id("HIS_AskedAboutSpiritual1"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}


    //Select selectedAskedAboutSpiritual = new Select(driver.findElement(By.id("AskedAboutSpiritual")));
	
	//public static final String get_txt_HIS_F3000A_from_RNIA = "test";
	//public static final String get_txt_HIS_F3000A_from_RNIA = driver.findElement(By.id("HIS_AskedAboutSpiritual1")).getText();
	/*public static WebElement get_txt_HIS_F3000A_from_RNIA(WebDriver driver) {
		element = driver.findElement(By.id("HIS_AskedAboutSpiritual1")).getAttribute(strInputValue);
		Waits.ForElementVisibility(driver, element);
		return element;
	}*/
	

	
	public static WebElement rad_HIS_J0905_No0(WebDriver driver) {
		element = driver.findElement(By.id("HIS_PainActiveProblem0"));
		return element;
	}
	
	public static WebElement rad_HIS_J0905_Yes1(WebDriver driver) {
		element = driver.findElement(By.id("HIS_PainActiveProblem1"));
		return element;
	}
	
	public static WebElement chk_HIS_J0910C_Severity(WebDriver driver) {
		element = driver.findElement(By.id("PainAssessmentIncludedSeverity"));
		return element;
	}
	
	public static WebElement chk_HIS_J0910C_Frequency(WebDriver driver) {
		element = driver.findElement(By.id("PainAssessmentIncludedFrequency"));
		return element;
	}
	
	public static WebElement rad_HIS_J2030C_Yes(WebDriver driver) {
		element = driver.findElement(By.id("HIS_ScreeningIndicateShortnessOfBreath1"));
		return element;
	}
	
	public static WebElement rad_HIS_J2030C_No(WebDriver driver) {
		element = driver.findElement(By.id("HIS_ScreeningIndicateShortnessOfBreath0"));
		return element;
	}
	
	public static WebElement rad_HIS_J2040A_Yes(WebDriver driver) {
		element = driver.findElement(By.id("HIS_TreatmentShortnessOfBreath2"));
		return element;
	}
	
	public static WebElement rad_HIS_J2040A_No(WebDriver driver) {
		element = driver.findElement(By.id("HIS_TreatmentShortnessOfBreath1"));
		return element;
	}
	
	public static WebElement chk_HIS_J2040C_Opioiods(WebDriver driver) {
		element = driver.findElement(By.id("TreatmentShortnessOfBreathOpioids"));
		return element;
	}
	
	public static WebElement chk_HIS_J2040C_OtherMedication(WebDriver driver) {
		element = driver.findElement(By.id("TreatmentShortnessOfBreathOther"));
		return element;
	}
	
	public static WebElement chk_HIS_J2040C_Oxygen(WebDriver driver) {
		element = driver.findElement(By.id("TreatmentShortnessOfBreathOxygen"));
		return element;
	}
	
	public static WebElement chk_HIS_J2040C_NonMed(WebDriver driver) {
		element = driver.findElement(By.id("TreatmentShortnessOfBreathNonMed"));
		return element;
	}

	

	public static WebElement txt_HIS_F2200B_Date(WebDriver driver) {
		element = driver.findElement(By.id("HIS_AskedAboutHospitalizationDate"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}
	
	public static WebElement txt_HIS_F3000A_Date(WebDriver driver) {
		element = driver.findElement(By.id("HIS_AskedAboutSpiritualDate"));
		Waits.ForElementVisibility(driver, element);
		return element;

	}
	
	public static WebElement txt_HIS_F3000A_Comment(WebDriver driver) {
		element = driver.findElement(By.id("HIS_AskedAboutSpiritual_Comment"));
		 return element;
	}
	
	public static WebElement rad_ESAS_Pain(WebDriver driver, String locator) throws Exception {
		element = driver.findElement(By.id("ESAS_Pain".concat(locator)));
		return element;
	}
	
	public static WebElement rad_ESAS_Tiredness(WebDriver driver, String locator) throws Exception {
		element = driver.findElement(By.id("ESAS_Tiredness".concat(locator)));
		return element;
	}
	
	public static WebElement rad_ESAS_Drowsiness(WebDriver driver, String locator) throws Exception {
		element = driver.findElement(By.id("ESAS_Drowsiness".concat(locator)));
		return element;
	}
	
	public static WebElement rad_ESAS_Nausea(WebDriver driver, String locator) throws Exception {
		element = driver.findElement(By.id("ESAS_Nausea".concat(locator)));
		return element;
	}
	
	public static WebElement rad_ESAS_LackOfAppetite(WebDriver driver, String locator) throws Exception {
		element = driver.findElement(By.id("ESAS_LackofAppetite".concat(locator)));
		return element;
	}
	
	public static WebElement rad_ESAS_ShortnessofBreath(WebDriver driver, String locator) throws Exception {
		element = driver.findElement(By.id("ESAS_ShortnessofBreath".concat(locator)));
		return element;
	}
	
	public static WebElement rad_ESAS_Depression(WebDriver driver, String locator) throws Exception {
		element = driver.findElement(By.id("ESAS_Depression".concat(locator)));
		return element;
	}
	
	public static WebElement rad_ESAS_Anxiety(WebDriver driver, String locator) throws Exception {
		element = driver.findElement(By.id("ESAS_Anxiety".concat(locator)));
		return element;
	}
	
	public static WebElement rad_ESAS_Wellbeing(WebDriver driver, String locator) throws Exception {
		element = driver.findElement(By.id("ESAS_Wellbeing".concat(locator)));
		return element;
	}
	
	public static WebElement btn_SavePageForm(WebDriver driver) {
		element = driver.findElement(By.id("savePageForm"));
		return element;
	}
	
	public static WebElement rad_N0500A_0(WebDriver driver) {
		element = driver.findElement(By.id("HIS_OpioidInitiated0"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}
	
	public static WebElement rad_N0510A_0(WebDriver driver) {
		element = driver.findElement(By.id("HIS_PRNOpioidInitiated0"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}
	
	public static WebElement txt_Int_Addl_Comments(WebDriver driver) {
		element = driver.findElement(By.id("IntegumentaryWoundCareComments"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}
	
	public static WebElement rad_Patient_SOB_Never(WebDriver driver) {
		element = driver.findElement(By.id("respiratory_SOB5"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}
	
	public static WebElement txt_Fall_Risk_Assess_Comments(WebDriver driver) {
		element = driver.findElement(By.id("FallRiskAssessmentComments"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}
	
	public static WebElement rad_DME_ChairBedAlarm(WebDriver driver) {
		element = driver.findElement(By.id("has_chairBedAlarm"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}
	
	public static WebElement rad_DME_Wheelchair(WebDriver driver) {
		element = driver.findElement(By.id("has_wheelchair"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}
	
	public static WebElement txt_Supply_Search(WebDriver driver) {
		element = driver.findElement(By.id("search"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}
	
	public static WebElement btn_Supply_Add(WebDriver driver) {
		element = driver.findElement(By.xpath("//div[1]/form/div/div/div[2]/div[2]/div[5]/div/div/div[3]/div/ul[2]/li[1]/input[2]"));
		Waits.ForElementToBeClickable(driver, element);
		return element;
	}
	
	public static WebElement btn_Supply_Delete(WebDriver driver) {
		element = driver.findElement(By.id("deleteButton"));
		Waits.ForElementToBeClickable(driver, element);
		return element;
	}
	
	public static WebElement txt_Supply_Quantity(WebDriver driver) {
		element = driver.findElement(By.xpath("//div[1]/form/div/div/div[2]/div[2]/div[5]/table[2]/tbody/tr[2]/td/input"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}
	
	public static WebElement lnk_NoSuppliesUsed(WebDriver driver) {
		element = driver.findElement(By.linkText("click here"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}
	
	public static WebElement rad_Allergies_NKA(WebDriver driver) {
		element = driver.findElement(By.id("nkaRadio"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}
	
	public static WebElement txt_SummaryComments_Comments(WebDriver driver) {
		element = driver.findElement(By.id("SummaryCommentsComments"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}
	
	public static WebElement chk_Return_to_Clinician(WebDriver driver) {
		element = driver.findElement(By.id("signatureRequired"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}
	
	public static WebElement txt_Clinician_Signature(WebDriver driver) {
		element = driver.findElement(By.id("clinicianSignature"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}
	
	public static WebElement txt_Signature_Date(WebDriver driver) {
		element = driver.findElement(By.name("signDate"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}
	
	public static WebElement btn_Submit(WebDriver driver) {
		element = driver.findElement(By.id("btnSumbit"));
		Waits.ForElementToBeClickable(driver, element);
		return element;
	}
	
	public static WebElement btn_save_continue(WebDriver driver) {
		element = driver.findElement(By.name("sc"));
		Waits.ForElementToBeClickable(driver, element);
		return element;
	}
	
	public static WebElement btn_save_submit(WebDriver driver) {
		element = driver.findElement(By.id("btnSumbit"));
		Waits.ForElementToBeClickable(driver, element);
		return element;
	}
	
	public static WebElement btn_approve(WebDriver driver) {
		element = driver.findElement(By.xpath("//input[@value='Approve']"));
		return element;
	}

	// @Method To fill the RNIA - Vital Signs/Diagnosis form without ESAS table.
	public static void FillVitalSignFormNull(WebDriver driver) throws Exception {
		
		chk_VSD_Decline(driver).click();
		rad_HIS_F2000A_0(driver).click();
		rad_HIS_F2100A_0(driver).click();
		rad_HIS_F2200A_0(driver).click();	

	}
	// @Method To fill the RNIA - Vital Signs/Diagnosis form
	public static void FillVitalSignForm(WebDriver driver) throws Exception {
		
		chk_VSD_Decline(driver).click();
		rad_HIS_F2000A_0(driver).click();
		rad_HIS_F2100A_0(driver).click();
		rad_HIS_F2200A_0(driver).click();	
		FillESAStable(driver);

	}
	

	/**
	 * Data sheet - KHClinical\\HISMeasures\\KH_HISMeasures_KH793.xlsx
	 * Fill in the ESAS values in the data sheet under FillRNIA-VSD tab.
	 * The entered values should by any one of these values "Unable to assess,0,1,2,3,4,5,6,7,8,9,10"  
	 */
	public static void FillESAStable(WebDriver driver) throws Exception {
		
		rad_ESAS_Pain(driver, locatorreader("ESAS_Pain")).click();
		rad_ESAS_Tiredness(driver, locatorreader("ESAS_Tiredness")).click();
		rad_ESAS_Drowsiness(driver, locatorreader("ESAS_Drowsiness")).click();
		rad_ESAS_Nausea(driver, locatorreader("ESAS_Nausea")).click();
		rad_ESAS_LackOfAppetite(driver, locatorreader("ESAS_LackofAppetite")).click();
		rad_ESAS_ShortnessofBreath(driver, locatorreader("ESAS_ShortnessofBreath")).click();
		rad_ESAS_Depression(driver, locatorreader("ESAS_Depression")).click();
		rad_ESAS_Anxiety(driver, locatorreader("ESAS_Anxiety")).click();
		rad_ESAS_Wellbeing(driver, locatorreader("ESAS_Wellbeing")).click();
		
	}
	
	//@Method to get the value from data sheet for ESAS table in RNIA-Vital Signs/Diagnosis form
		public static String locatorreader(String value) throws Exception{
			
			String locator = Datatable.GetValue(value);
			if (locator.contentEquals("Unable to assess")){
				locator = "NA";
			}
			return locator;
			
		}
	

	
	public static void FillPain(WebDriver driver) throws Exception {

		//rad_HIS_F3000A_Yes1(driver).click();
		//get_txt_HIS_F3000A_from_RNIA(driver);
		
		strInputValue = Datatable.GetValue("ScreenedForPain");
		if (!strInputValue.trim().isEmpty() && strInputValue.equals("Y")) {
			//rad_HIS_J0900A_1(driver).click();
		}

		strInputValue = Datatable.GetValue("ScreenedForPainComment");
		if (!strInputValue.trim().isEmpty()) {
			txt_ScreenedForPain_Comment(driver).clear();
			txt_ScreenedForPain_Comment(driver).sendKeys(strInputValue);
		}
		
		strInputValue = Datatable.GetValue("PainDate");
		if (!strInputValue.trim().isEmpty()) {
			dt_HIS_ScreenedForPainDate(driver).clear();
			dt_HIS_ScreenedForPainDate(driver).sendKeys(strInputValue);
		}

		strInputValue = Datatable.GetValue("PainSeverity");
		if (!strInputValue.trim().isEmpty()) {
			lst_HIS_PainSeverity(driver).selectByVisibleText(strInputValue);
		}

		strInputValue = Datatable.GetValue("PainScreeningTool");
		if (!strInputValue.trim().isEmpty()) {
			lst_HIS_PainScreeningTool(driver).selectByVisibleText(strInputValue);
		}
		
		strInputValue = Datatable.GetValue("ShortnessBreath");
		if (!strInputValue.trim().isEmpty() && strInputValue.equals("N")) {
			rad_HIS_J0905_No0(driver).click();
		}
		else
		{
			rad_HIS_J0905_Yes1(driver).click();	
		}
		
		strInputValue = Datatable.GetValue("ScreenShortnessBreath");
		if (!strInputValue.trim().isEmpty() && strInputValue.equals("N")) {
			//rad_HIS_J0910A_0(driver).click();
		}
		else
		{
			//rad_HIS_J0910A_1(driver).click();
		}
		
		
		strInputValue = Datatable.GetValue("ComPainAssessmentComment");
		if (!strInputValue.trim().isEmpty()) {
			txt_ComPainAssessment_Comment(driver).clear();
			txt_ComPainAssessment_Comment(driver).sendKeys(strInputValue);
		}
		
		strInputValue = Datatable.GetValue("ComPainAssDate");
		if (!strInputValue.trim().isEmpty()) {
			dt_CompPainAssessment(driver).clear();
			dt_CompPainAssessment(driver).sendKeys(strInputValue);
		}

		strInputValue = Datatable.GetValue("CompPainAssIncSeverity");
		if (!strInputValue.trim().isEmpty() && strInputValue.equals("Y")) {
			chk_HIS_J0910C_Severity(driver).click();
		}
		strInputValue = Datatable.GetValue("CompPainAssIncFrequency");
		if (!strInputValue.trim().isEmpty() && strInputValue.equals("Y")) {
			chk_HIS_J0910C_Frequency(driver).click();
		}
		
		strInputValue = Datatable.GetValue("PatScreenShortBreath");
		if (!strInputValue.trim().isEmpty() && strInputValue.equals("Y")) {
			//rad_HIS_J2030A_1(driver).click();
		}
		else 
		{
			rad_HIS_J2030A_0(driver).click();
		}
		
		strInputValue = Datatable.GetValue("ShortnessOfBreathDate");
		if (!strInputValue.trim().isEmpty()) {
			dt_HIS_ScreenedForShortnessOfBreathDate(driver).clear();
			dt_HIS_ScreenedForShortnessOfBreathDate(driver).sendKeys(strInputValue);
		}
		
		strInputValue = Datatable.GetValue("PatScreenShortBreathYes");
		if (!strInputValue.trim().isEmpty() && strInputValue.equals("Y")) {
			rad_HIS_J2030C_Yes(driver).click();
		}
		else 
		{
			rad_HIS_J2030C_No(driver).click();
		}

		strInputValue = Datatable.GetValue("PatDeclined");
		if (!strInputValue.trim().isEmpty() && strInputValue.equals("Y")) {
			rad_HIS_J2040A_Yes(driver).click();
		}
		else
		{
			rad_HIS_J2040A_No(driver).click();
		}
		
		strInputValue = Datatable.GetValue("TreatShortnessOfBreathDate");
		if (!strInputValue.trim().isEmpty()) {
			dt_HIS_TreatmentShortnessOfBreathDate(driver).clear();
			dt_HIS_TreatmentShortnessOfBreathDate(driver).sendKeys(strInputValue);
		}
		
		strInputValue = Datatable.GetValue("TypesOfTreatment");
		if (!strInputValue.trim().isEmpty() && strInputValue.equals("Y")) {
			chk_HIS_J2040C_Opioiods(driver).click();
		}
		
		strInputValue = Datatable.GetValue("TypesOfTreatment");
		if (!strInputValue.trim().isEmpty() && strInputValue.equals("Y")) {
			chk_HIS_J2040C_OtherMedication(driver).click();
		}
		
		strInputValue = Datatable.GetValue("TypesOfTreatment");
		if (!strInputValue.trim().isEmpty() && strInputValue.equals("Y")) {
			chk_HIS_J2040C_Oxygen(driver).click();
		}
		
		strInputValue = Datatable.GetValue("TypesOfTreatment");
		if (!strInputValue.trim().isEmpty() && strInputValue.equals("Y")) {
			chk_HIS_J2040C_NonMed(driver).click();
		}
		
	}
	
		public static WebElement dt_HIS_ScreenedForPainDate(WebDriver driver) {
			element = driver.findElement(By.id("HIS_ScreenedForPainDate"));
			return element;
		}
	
		public static WebElement dt_HIS_ScreenedForShortnessOfBreathDate(WebDriver driver) {
			element = driver.findElement(By.id("HIS_ScreenedForShortnessOfBreathDate"));
			return element;
		}

		public static Select lst_HIS_PainSeverity(WebDriver driver) {
			element = driver.findElement(By.id("HIS_PainSeverity"));
			list = new Select(driver.findElement(By.id("HIS_PainSeverity")));
			return list;
		}
		
		public static Select lst_HIS_PainScreeningTool(WebDriver driver) {
			element = driver.findElement(By.id("HIS_PainScreeningTool"));
			list = new Select(driver.findElement(By.id("HIS_PainScreeningTool")));
			return list;
		}
		
		// HIS_ScreenedForPain_Comment
		public static WebElement txt_ScreenedForPain_Comment(WebDriver driver) {
			element = driver.findElement(By.id("HIS_ScreenedForPain_Comment"));
			return element;
		}
		
		public static WebElement txt_ComPainAssessment_Comment(WebDriver driver) {
			element = driver.findElement(By.id("HIS_ComprehensivePainAssessment_Comment"));
			return element;
		}
		
		public static WebElement dt_CompPainAssessment(WebDriver driver) {
			element = driver.findElement(By.id("HIS_ComprehensivePainAssessmentDate"));
			return element;
		}
		
		public static WebElement dt_HIS_TreatmentShortnessOfBreathDate(WebDriver driver) {
			element = driver.findElement(By.id("HIS_TreatmentShortnessOfBreathDate"));
			return element;
		}
		
		// patientName in Print View
		public static WebElement txt_patientName(WebDriver driver) {
			element = driver.findElement(By.id("patientName"));
			return element;
		}
		
		public static WebElement btn_RefreshHISMeasure(WebDriver driver) {
			element = driver.findElement(By.id("RefreshHISMeasureButton"));
			return element;
		} 
		
		//Warning OK Button
		public static WebElement btn_HISRefreshConfirm(WebDriver driver) {
			element = driver.findElement(By.id("HISRefreshConfirm"));
			return element;
		} 
				
		//Web Elements in HIS Measures Screen
		
		public static WebElement txt_PatientFirstName(WebDriver driver) {
			element = driver.findElement(By.id("PatientFirstName"));
			return element;
		}
		
		public static WebElement txt_PatientLastName(WebDriver driver) {
			element = driver.findElement(By.id("PatientLastName"));
			return element;
		}
		
		public static WebElement rad_ScreenedForPainYes(WebDriver driver) {
			element = driver.findElement(By.id("ScreenedForPainYes"));
			return element;
		}
		
		public static WebElement rad_ScreenedForPainNo(WebDriver driver) {
			element = driver.findElement(By.id("ScreenedForPainNo"));
			return element;
		}
		
		public static WebElement dt_ScreenedForPainDate(WebDriver driver) {
			element = driver.findElement(By.id("ScreenedForPainDate"));
			return element;
		}

		public static Select lst_PainSeverity(WebDriver driver) {
			element = driver.findElement(By.id("PainSeverity"));
			list = new Select(driver.findElement(By.id("PainSeverity")));
			return list;
		}

		public static Select lst_PainScreeningTool(WebDriver driver) {
			element = driver.findElement(By.id("PainScreeningTool"));
			list = new Select(driver.findElement(By.id("PainScreeningTool")));
			return list;
		}
		
		public static WebElement dt_ComprehensivePainAssessmentDate(WebDriver driver) {
			element = driver.findElement(By.id("ComprehensivePainAssessmentDate"));
			return element;
		}
		
		public static WebElement chk_PainAssessmentIncludedSeverity(WebDriver driver) {
			element = driver.findElement(By.id("PainAssessmentIncludedSeverity"));
			return element;
		}
		
		public static WebElement chk_PainAssessmentIncludedFrequency(WebDriver driver) {
			element = driver.findElement(By.id("PainAssessmentIncludedFrequency"));
			return element;
		}

		public static WebElement rad_PainActiveProblemYes(WebDriver driver) {
			element = driver.findElement(By.id("PainActiveProblemYes"));
			return element;
		}

		public static WebElement rad_ComprehensivePainAssessmentYes(WebDriver driver) {
			element = driver.findElement(By.id("ComprehensivePainAssessmentYes"));
			return element;
		}

		public static WebElement rad_ScreenedForShortnessOfBreathYes(WebDriver driver) {
			element = driver.findElement(By.id("ScreenedForShortnessOfBreathYes"));
			return element;
		}
		
		public static WebElement dt_ScreenedForShortnessOfBreathDate(WebDriver driver) {
			element = driver.findElement(By.id("ScreenedForShortnessOfBreathDate"));
			return element;
		}
		
		public static WebElement rad_ScreeningIndicateShortnessOfBreathYes(WebDriver driver) {
			element = driver.findElement(By.id("ScreeningIndicateShortnessOfBreathYes"));
			return element;
		}

		public static WebElement dt_TreatmentShortnessOfBreathDate(WebDriver driver) {
			element = driver.findElement(By.id("TreatmentShortnessOfBreathDate"));
			return element;
		}
		
		public static WebElement chk_TreatmentShortnessOfBreathOpioids(WebDriver driver) {
			element = driver.findElement(By.id("TreatmentShortnessOfBreathOpioids"));
			return element;
		}
		
		public static WebElement chk_TreatmentShortnessOfBreathOther(WebDriver driver) {
			element = driver.findElement(By.id("TreatmentShortnessOfBreathOther"));
			return element;
		}
		
		public static WebElement chk_TreatmentShortnessOfBreathOxygen(WebDriver driver) {
			element = driver.findElement(By.id("TreatmentShortnessOfBreathOxygen"));
			return element;
		}
		
		public static WebElement chk_TreatmentShortnessOfBreathNonMed(WebDriver driver) {
			element = driver.findElement(By.id("TreatmentShortnessOfBreathNonMed"));
			return element;
		}

		public static WebElement chk_SpiritualConcerns(WebDriver driver) {
			element = driver.findElement(By.id("HIS_AskedAboutSpiritual0"));
			return element;
		}
	// @Method To fill the RNIA - Pain form
	public static void FillPainForm(WebDriver driver) throws Exception {

		rad_HIS_J0900A_0(driver).click();
		rad_HIS_J2030A_0(driver).click();
		rad_HIS_J2040A_0(driver).click();

	}
	
	// @Method To fill the RNIA - ADLs form
		public static void FillADLsForm(WebDriver driver) throws Exception {

			rad_Primary_Caregiver_No(driver).click();
			rad_Hospice_Aide_Needed_Yes(driver).click();

	}
		
	// @Method To fill the RNIA - Psychological form
		public static void FillPsychologicalForm(WebDriver driver) throws Exception {

			rad_HIS_F3000A_Yes1(driver).click();
			//get_txt_HIS_F3000A_from_RNIA(driver);
			
			strInputValue = Datatable.GetValue("F3000ADate");
			if (!strInputValue.trim().isEmpty()) {
				txt_HIS_F3000A_Date(driver).clear();
				txt_HIS_F3000A_Date(driver).sendKeys(strInputValue);
			}
			
			strInputValue = Datatable.GetValue("F3000AComment");
			if (!strInputValue.trim().isEmpty()) {
				txt_HIS_F3000A_Comment(driver).clear();
				txt_HIS_F3000A_Comment(driver).sendKeys(strInputValue);
			}

				
			Report.attachScreenShotToReport(driver);
			
		}	

				
		


	// @Method To fill the RNIA - Integumentary form
		public static void FillIntegumentaryForm(WebDriver driver) throws Exception {

			strInputValue = Datatable.GetValue("IntAddlComments");
			if (!strInputValue.trim().isEmpty()) {
				txt_Int_Addl_Comments(driver).clear();
				txt_Int_Addl_Comments(driver).sendKeys(strInputValue);
			}
				
		}
		
	// @Method To fill the RNIA - Respiratory / Pulmonary form
		public static void FillRespiratoryPulmonary(WebDriver driver) throws Exception {

			rad_Patient_SOB_Never(driver).click();
							
		}
			
	// @Method To fill the RNIA - Digestive form
		public static void FillDigestiveForm(WebDriver driver) throws Exception {

			rad_N0500A_0(driver).click();
			rad_N0510A_0(driver).click();

		}
		
	// @Method To fill the RNIA - Fall Risk Assessment form
		public static void FillFallRiskAssessForm(WebDriver driver) throws Exception {

			strInputValue = Datatable.GetValue("FallRiskAssessmentComments");
			if (!strInputValue.trim().isEmpty()) {
				txt_Fall_Risk_Assess_Comments(driver).clear();
				txt_Fall_Risk_Assess_Comments(driver).sendKeys(strInputValue);
			}
				
		}
		
	// @Method To fill the RNIA - DME form
		public static void FillDME(WebDriver driver) throws Exception {

			rad_DME_ChairBedAlarm(driver).click();
			rad_DME_Wheelchair(driver).click();
				
		}
		
	// @Method To fill the RNIA - Supplies Used form
		public static void FillSuppliesUsed(WebDriver driver) throws Exception {

			strInputValue = Datatable.GetValue("Supplies");
			if (!strInputValue.trim().isEmpty()) {
				txt_Supply_Search(driver).clear();
				txt_Supply_Search(driver).sendKeys(strInputValue);
			}
			
			btn_Supply_Add(driver);
				
		}
		
	// @Method To fill the RNIA - Supplies Used Quantity form
		public static void FillSupplyQuantity(WebDriver driver) throws Exception {

			lnk_NoSuppliesUsed(driver);
			
		}
		
	// @Method To fill the RNIA - Medication Record form
		public static void FillMedicationRecord(WebDriver driver) throws Exception {

			rad_Allergies_NKA(driver).click();
			
		}
		
	// @Method To fill the RNIA - Summary/Comments form
		public static void FillSummaryComments(WebDriver driver) throws Exception {

			strInputValue = Datatable.GetValue("SummaryCommentsComments");
			if (!strInputValue.trim().isEmpty()) {
				txt_SummaryComments_Comments(driver).clear();
				txt_SummaryComments_Comments(driver).sendKeys(strInputValue);
			}
			
		}
		
	// @Method To sign and submit the RNIA
		public static void FillSignSubmitRNIA(WebDriver driver) throws Exception {

			strInputValue = Datatable.GetValue("TimeIn");
			if (!strInputValue.trim().isEmpty()) {
				txt_RNIA_TimeIn(driver).clear();
				txt_RNIA_TimeIn(driver).sendKeys(strInputValue);
			}

			strInputValue = Datatable.GetValue("TimeOut");
			if (!strInputValue.trim().isEmpty()) {
				txt_RNIA_TimeOut(driver).clear();
				txt_RNIA_TimeOut(driver).sendKeys(strInputValue);
			}
			
			strInputValue = Datatable.GetValue("VisitDate");
			if (!strInputValue.trim().isEmpty()) {
				txt_RNIA_VisitDate(driver).clear();
				txt_RNIA_VisitDate(driver).sendKeys(strInputValue);
			}
			
			chk_Return_to_Clinician(driver).click();
			
			strInputValue = Datatable.GetValue("Signature");
			if (!strInputValue.trim().isEmpty()) {
				txt_Clinician_Signature(driver).clear();
				txt_Clinician_Signature(driver).sendKeys(strInputValue);
			}
			
			strInputValue = Datatable.GetValue("SignatureDate");
			if (!strInputValue.trim().isEmpty()) {
				txt_Signature_Date(driver).clear();
				txt_Signature_Date(driver).sendKeys(strInputValue);
			}
			
		}
		
	// @Method To fill the entire RNIA from start to finish
		public static void FillRNIAComplete(WebDriver driver) throws Exception {

			lnk_RNIA_VitalSignsDiagnosis(driver).click();
			FillVitalSignForm(driver);
			btn_save_continue(driver).click();
			FillPainForm(driver);
			btn_save_continue(driver).click();
			FillADLsForm(driver);
			btn_save_continue(driver).click();
			FillPsychologicalForm(driver);
			btn_save_continue(driver).click();
			FillIntegumentaryForm(driver);
			btn_save_continue(driver).click();
			FillRespiratoryPulmonary(driver);
			btn_save_continue(driver).click();
			FillDigestiveForm(driver);
			btn_save_continue(driver).click();
			FillFallRiskAssessForm(driver);
			btn_save_continue(driver).click();
			FillDME(driver);
			btn_save_continue(driver).click();
			FillSuppliesUsed(driver);
			btn_save_continue(driver).click();
			FillSupplyQuantity(driver);
			btn_save_continue(driver).click();
			FillMedicationRecord(driver);
			btn_save_continue(driver).click();
			FillSummaryComments(driver);
			btn_Submit(driver).click();
			FillSignSubmitRNIA(driver);
			btn_save_submit(driver).click();

		}

}
