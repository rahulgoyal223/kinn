package KH.Patient;

import DataSource.Datatable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class ViewHISMeasures {

	private static WebElement element = null;
	private static String strInputValue;
	
	//@Method to pull input value from Excel
	public static String get_txt_HIS_F3000A_from_Excel(WebDriver driver) throws Exception {

		strInputValue = Datatable.GetValue("F3000ATxtFromHIS");
		return strInputValue;

		}
	
	//@Method to pull input value from Excel
	public static String get_txt_HIS_F3000A_Date_from_Excel(WebDriver driver) throws Exception {

		strInputValue = Datatable.GetValue("F3000ADate");
		return strInputValue;
		}
	
	//@Method to pull currently displayed AskedAboutSpiritual drop down from HIS page 
	public static String get_txt_HIS_F3000A_from_HIS(WebDriver driver) throws Exception {

		String selectedOption = lst_AskedAboutSpiritual(driver).getText();
		return selectedOption;
		
		}
	
	//@Method to pull currently displayed AskedAboutSpiritual date from HIS page 
	public static String get_txt_HIS_F3000A_Date_from_HIS(WebDriver driver) throws Exception {

		strInputValue = txt_AskedAboutSpiritualDate(driver).getAttribute("value");
		return strInputValue;
		}
	
	
	//Define elements for header 
	public static WebElement tab_HISAdmissionView(WebDriver driver) {
		element = driver.findElement(By.id("HISAdmissionView"));		
		return element;
	}
	
	public static WebElement tab_HISDischargeView(WebDriver driver) {
		element = driver.findElement(By.id("HISDischargeView"));		
		return element;
	}
	
	public static WebElement btn_RefreshHISMeasureButton(WebDriver driver) {
		element = driver.findElement(By.id("RefreshHISMeasureButton"));		
		return element;
	}
	
	//Define elements for Section A
	public static WebElement lst_SiteOfServiceAtAdmission(WebDriver driver) {
		element = driver.findElement(By.id("InitialPlaceOfService"));		
		return element;
	}
	
	public static WebElement txt_getAdmissionDate(WebDriver driver) {
		element = driver.findElement(By.id("AdmissionDate"));
		return element;
	}
	
	public static WebElement txt_DateInitialNursingAssessmentInitiated(WebDriver driver) {
		element = driver.findElement(By.id("InitialAssessmentDate"));
		return element;
	}
	
	public static WebElement txt_PatientFirstName(WebDriver driver) {
		element = driver.findElement(By.id("PatientFirstName"));
		return element;
	}
	
	public static WebElement txt_PatientMiddleInitial(WebDriver driver) {
		element = driver.findElement(By.id("PatientMiddleInitial"));
		return element;
	}
	
	public static WebElement txt_PatientLastName(WebDriver driver) {
		element = driver.findElement(By.id("PatientLastName"));
		return element;
	}
	
	public static WebElement txt_SSN_A(WebDriver driver) {
		element = driver.findElement(By.id("SSNA"));
		return element;
	}
	
	public static WebElement txt_SSN_B(WebDriver driver) {
		element = driver.findElement(By.id("SSNB"));
		return element;
	}
	
	public static WebElement txt_SSN_C(WebDriver driver) {
		element = driver.findElement(By.id("SSNC"));
		return element;
	}
	
	public static WebElement txt_PatientNameSuffix(WebDriver driver) {
		element = driver.findElement(By.id("PatientNameSuffix"));
		return element;
	}
	
	public static WebElement txt_PatientMedicareNumber(WebDriver driver) {
		element = driver.findElement(By.id("PatientMedicareNumber"));
		return element;
	}
	
	public static WebElement txt_MedicaidNumber(WebDriver driver) {
		element = driver.findElement(By.id("MedicaidNumber"));
		return element;
	}
	
	public static WebElement rad_Male(WebDriver driver) {
		element = driver.findElement(By.id("GenderMale"));
		return element;
	}
	
	public static WebElement rad_Female(WebDriver driver) {
		element = driver.findElement(By.id("GenderFemale"));
		return element;
	}
	
	public static WebElement txt_DateOfBirth(WebDriver driver) {
		element = driver.findElement(By.id("DOB"));
		return element;
	}
	
	public static WebElement chk_EthnicityAmericanIndianAlaskaNative(WebDriver driver) {
		element = driver.findElement(By.id("EthnicityAmericanIndianAlaskaNative"));
		return element;
	}
	
	public static WebElement chk_EthnicityAsian(WebDriver driver) {
		element = driver.findElement(By.id("EthnicityAsian"));
		return element;
	}
	
	public static WebElement chk_EthnicityBlackAfricanAmerican(WebDriver driver) {
		element = driver.findElement(By.id("EthnicityBlackAfricanAmerican"));
		return element;
	}
	
	public static WebElement chk_EthnicityHispanicLatino(WebDriver driver) {
		element = driver.findElement(By.id("EthnicityHispanicLatino"));
		return element;
	}
	
	public static WebElement chk_EthnicityNativeHawaiianPacificIslander(WebDriver driver) {
		element = driver.findElement(By.id("EthnicityNativeHawaiianPacificIslander"));
		return element;
	}
	
	public static WebElement chk_EthnicityWhite(WebDriver driver) {
		element = driver.findElement(By.id("EthnicityWhite"));
		return element;
	}
	
	public static WebElement lst_AdmittedFrom(WebDriver driver) {
		element = driver.findElement(By.id("AdmittedFrom"));
		return element;
	}
	
	//Define elements for Section F
	public static WebElement lst_AskedAboutCPR(WebDriver driver) {
		element = driver.findElement(By.id("AskedAboutCPR"));
		return element;
	}
	
	public static WebElement txt_AskedAboutCPRDate(WebDriver driver) {
		element = driver.findElement(By.id("AskedAboutCPRDate"));
		return element;
	}
	
	public static WebElement lst_AskedAboutTreatmentsOtherThanCPR(WebDriver driver) {
		element = driver.findElement(By.id("AskedAboutTreatmentsOther"));
		return element;
	}
	
	public static WebElement txt_AskedAboutTreatmentsOtherThanCPRDate(WebDriver driver) {
		element = driver.findElement(By.id("AskedAboutTreatmentsOtherDate"));
		return element;
	}
	
	public static WebElement lst_AskedAboutHospitalization(WebDriver driver) {
		element = driver.findElement(By.id("AskedAboutHospitalization"));
		return element;
	}
	
	public static WebElement txt_AskedAboutHospitalizationDate(WebDriver driver) {
		element = driver.findElement(By.id("AskedAboutHospitalizationDate"));
		return element;
	}
			
	public static WebElement lst_AskedAboutSpiritual(WebDriver driver) {
		element = driver.findElement(By.id("AskedAboutSpiritual"));
		return element;
	}
	
	public static WebElement txt_AskedAboutSpiritualDate(WebDriver driver) {
		element = driver.findElement(By.id("AskedAboutSpiritualDate"));
		return element;
	}
	
	//Define elements for Section I
	public static WebElement lst_PrincipalDiagnosis(WebDriver driver) {
		element = driver.findElement(By.id("PrincipalDiagnosis"));
		return element;
	}
	
	//Define elements for Section J
	public static WebElement rad_ScreenedForPainNo(WebDriver driver) {
		element = driver.findElement(By.id("ScreenedForPainNo"));
		return element;
	}
	
	public static WebElement rad_ScreenedForPainYes(WebDriver driver) {
		element = driver.findElement(By.id("ScreenedForPainYes"));
		return element;
	}
	
	public static WebElement txt_ScreenedForPainDate(WebDriver driver) {
		element = driver.findElement(By.id("ScreenedForPainDate"));
		return element;
	}
	
	public static WebElement lst_PainScreeningTool(WebDriver driver) {
		element = driver.findElement(By.id("PainScreeningTool"));
		return element;
	}
	
	public static WebElement rad_ComprehensivePainAssessmentNo(WebDriver driver) {
		element = driver.findElement(By.id("ComprehensivePainAssessmentNo"));
		return element;
	}
	
	public static WebElement rad_ComprehensivePainAssessmentYes(WebDriver driver) {
		element = driver.findElement(By.id("ComprehensivePainAssessmentYes"));
		return element;
	}
	
	public static WebElement txt_ComprehensivePainAssessmentDate(WebDriver driver) {
		element = driver.findElement(By.id("ComprehensivePainAssessmentDate"));
		return element;
	}
	
	public static WebElement chk_PainAssessmentIncludedLocation(WebDriver driver) {
		element = driver.findElement(By.id("PainAssessmentIncludedLocation"));
		return element;
	}
	
	public static WebElement chk_PainAssessmentIncludedSeverity(WebDriver driver) {
		element = driver.findElement(By.id("PainAssessmentIncludedSeverity"));
		return element;
	}
	
	public static WebElement chk_PainAssessmentIncludedCharacter(WebDriver driver) {
		element = driver.findElement(By.id("PainAssessmentIncludedCharacter"));
		return element;
	}
	
	public static WebElement chk_PainAssessmentIncludedDuration(WebDriver driver) {
		element = driver.findElement(By.id("PainAssessmentIncludedDuration"));
		return element;
	}
	
	public static WebElement chk_PainAssessmentIncludedFrequency(WebDriver driver) {
		element = driver.findElement(By.id("PainAssessmentIncludedFrequency"));
		return element;
	}
	
	public static WebElement chk_PainAssessmentIncludedRelievesWorsens(WebDriver driver) {
		element = driver.findElement(By.id("PainAssessmentIncludedRelievesWorsens"));
		return element;
	}
	
	public static WebElement chk_PainAssessmentIncludedFunctionQuality(WebDriver driver) {
		element = driver.findElement(By.id("PainAssessmentIncludedFunctionQuality"));
		return element;
	}
	
	public static WebElement rad_ScreenedForShortnessOfBreathNo(WebDriver driver) {
		element = driver.findElement(By.id("ScreenedForShortnessOfBreathNo"));
		return element;
	}

	public static WebElement rad_ScreenedForShortnessOfBreathYes(WebDriver driver) {
		element = driver.findElement(By.id("ScreenedForShortnessOfBreathYes"));
		return element;
	}
	
	public static WebElement txt_ScreenedForShortnessOfBreathDate(WebDriver driver) {
		element = driver.findElement(By.id("ScreenedForShortnessOfBreathDate"));
		return element;
	}
	
	public static WebElement rad_ScreeningIndicateShortnessOfBreathNo(WebDriver driver) {
		element = driver.findElement(By.id("ScreeningIndicateShortnessOfBreathNo"));
		return element;
	}
	
	public static WebElement rad_ScreeningIndicateShortnessOfBreathYes(WebDriver driver) {
		element = driver.findElement(By.id("ScreeningIndicateShortnessOfBreathYes"));
		return element;
	}
	
	public static WebElement lst_TreatmentShortnessOfBreath(WebDriver driver) {
		element = driver.findElement(By.id("TreatmentShortnessOfBreath"));
		return element;
	}
	
	public static WebElement txt_TreatmentShortnessOfBreathDate(WebDriver driver) {
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
	

	//Define elements for Section N
	public static WebElement rad_OpioidInitiatedNo(WebDriver driver) {
		element = driver.findElement(By.id("OpioidInitiatedNo"));
		return element;
	}
	
	public static WebElement rad_OpioidInitiatedYes(WebDriver driver) {
		element = driver.findElement(By.id("OpioidInitiatedYes"));
		return element;
	}
	
	public static WebElement txt_OpioidInitiatedDate(WebDriver driver) {
		element = driver.findElement(By.id("OpioidInitiatedDate"));
		return element;
	}
	
	public static WebElement rad_PRNOpioidInitiatedNo(WebDriver driver) {
		element = driver.findElement(By.id("PRNOpioidInitiatedNo"));
		return element;
	}
	
	public static WebElement rad_PRNOpioidInitiatedYes(WebDriver driver) {
		element = driver.findElement(By.id("PRNOpioidInitiatedYes"));
		return element;
	}
	
	public static WebElement txt_PRNOpioidInitiatedDate(WebDriver driver) {
		element = driver.findElement(By.id("PRNOpioidInitiatedDate"));
		return element;
	}
	
	public static WebElement lst_BowelRegimenInitiated(WebDriver driver) {
		element = driver.findElement(By.id("BowelRegimenInitiated"));
		return element;
	}
	
	public static WebElement txt_BowelRegimenInitiatedDate(WebDriver driver) {
		element = driver.findElement(By.id("BowelRegimenInitiatedDate"));
		return element;
	}
	

	//Define elements for Section Z
	public static WebElement txt_CompletionSigSignature(WebDriver driver) {
		element = driver.findElement(By.id("CompletionSigSignature"));
		return element;
	}
	
	public static WebElement txt_CompletionSigTitle(WebDriver driver) {
		element = driver.findElement(By.id("CompletionSigTitle"));
		return element;
	}
	
	public static WebElement chk_HISMeasureHASectionA(WebDriver driver) {
		element = driver.findElement(By.id("HISMeasureHASectionA"));
		return element;
	}
	
	public static WebElement chk_HISMeasureHASectionF(WebDriver driver) {
		element = driver.findElement(By.id("HISMeasureHASectionF"));
		return element;
	}
	
	public static WebElement chk_HISMeasureHASectionI(WebDriver driver) {
		element = driver.findElement(By.id("HISMeasureHASectionI"));
		return element;
	}
	
	public static WebElement chk_HISMeasureHASectionJ(WebDriver driver) {
		element = driver.findElement(By.id("HISMeasureHASectionJ"));
		return element;
	}
	
	public static WebElement chk_HISMeasureHASectionN(WebDriver driver) {
		element = driver.findElement(By.id("HISMeasureHASectionN"));
		return element;
	}
	
	public static WebElement txt_CompletionSigDate(WebDriver driver) {
		element = driver.findElement(By.id("CompletionSigDate"));
		return element;
	}
	
	public static WebElement btn_AddSignatureButton(WebDriver driver) {
		element = driver.findElement(By.id("AddSignatureButton"));
		return element;
	}
	
	public static WebElement txt_VerifySigSignature(WebDriver driver) {
		element = driver.findElement(By.id("VerifySigSignature"));
		return element;
	}
	
	public static WebElement txt_VerifySigDate(WebDriver driver) {
		element = driver.findElement(By.id("VerifySigDate"));
		return element;
	}
	
	//Define elements for footer buttons
	public static WebElement btn_SaveHISAdmissionButton(WebDriver driver) {
		element = driver.findElement(By.id("SaveHISAdmissionButton"));
		return element;
	}
	
	public static WebElement btn_CompleteHISAdmissionButton(WebDriver driver) {
		element = driver.findElement(By.id("CompleteHISAdmissionButton"));
		return element;
	}
	
		
}
