package Scripts.Clinical.Visits;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import DataSource.Datatable;
import DataSource.GlobalData;
import components.Browser;
import components.Report;
import components.Verify;
import components.Waits;

public class AM_ClinicalVisit_HH3334 {

	/************************************************************************************
	'Class name              : 	AM_ClinicalVisit_HH3334
	'JIRA ID				 :	HH-3334
	'Description             :  To verify the Schedule and completion of Medication Profile Task in AM
	'Input Parameters        : 	Patient Name
	'Output Parameters       : 	Task status
	'Assumptions             : 	Test Data is present in the Global Sheet.
	'Use                     : 	The following test verifies if the patient created
								and Verify Medication Profile Task process in AM
	'Tags                    : 	Regression	
	 ************************************************************************************/

   	public static void main(String[] args) throws Exception {
   		AM_ClinicalVisit_HH3334();
	 }
	
   	@Test(groups = { "AM_Regression", "AM_Clinical" })
	 public static void AM_ClinicalVisit_HH3334() throws Exception {

    	String dataSheetName    = null;  
    	String PM_PatientName   = null;  
       /******************************************************************
       * Mandate to call below lines at every test case start up
       * 
       * 
       ******************************************************************/
       //@Reports Configuration       
       Report.generateReportsFile("html","AM_ClinicalVisit_HH3334");
       Report.SetTestName("AM_ClinicalVisit_HH3334", "Verify Medication Profile Task process in AM");
       Report.assignCategory("ClinicalVisit");
      //@Open Application and submit credentials
       AM.Login.openAppAndSubmitCredentials();
       //@ Get Current WebDriver
        WebDriver driver = Browser.getDriver();
       //@Import Test data sheet
       String dataFileName = "Clinical\\ClinicalVisit\\AM_ClinicalVisit_HH3334.xlsx";
       dataSheetName = "AM_BM2_MedicationProfile";
       Datatable.loadDataSheet(dataFileName, dataSheetName);
       //********************************************************************** 
       
      //@@Step :To create new patient if needed
       if (Datatable.GetValue("CreatePatient").equals("Yes")) {

       	  //@Step :To load sheet
           Datatable.loadDataSheet(dataFileName, "CreatePatient");
           WebElement stale = Waits.StalenessPreset(driver);
           AM.Menu.TopMenu.Select(driver, "File/New/Patient");
           Waits.ForElementStaleness(driver,stale);
           AM.Patient.AddNewPatient.FillAddNewPatient(driver);
           AM.Patient.AddNewPatient.AddPatient(driver);          
       }     
       
       if (!GlobalData.getPatientFullName().isEmpty()){
       	PM_PatientName = GlobalData.getPatientFullName();
       }else {
       	Datatable.GetValue("PM_PatientName");
       }  
       
       Datatable.loadDataSheet(dataFileName, "AM_BM2_MedicationProfile");  
       System.out.println("Patient Name is : "+ PM_PatientName);
       
       //@ Step - Select the patient
       AM.Menu.TopMenu.Select(driver, "Go To/Patient Manager");        
       AM.Patient.PatientManager.SelectActivePatient(driver);  

       //@ Step Fill the Medication Profile with medication type as "Medication"
       AM.Menu.TopMenu.Select(driver, "View/Medication Profile");
       Datatable.loadDataSheet(dataFileName, dataSheetName);  
       Waits.ForBrowserLoad(driver);
       AM.Forms.Nursing.MedicationProfile.FillMedication(driver);
       AM.Forms.Nursing.MedicationProfile.SaveMedication(driver);
       
      //@ Step Fill the Medication Profile with medication type as "Nonstandard Dosage"
       Waits.ForBrowserLoad(driver);
       AM.Forms.Nursing.MedicationProfile.FillNonStandardDosage(driver);
       AM.Forms.Nursing.MedicationProfile.SaveNonStandardMedication(driver);
       
      //@ Step Fill the Medication Profile with medication type as "OffMarketUnlisted"
       Waits.ForBrowserLoad(driver);
       AM.Forms.Nursing.MedicationProfile.FillOffMarketUnlisted(driver);
       AM.Forms.Nursing.MedicationProfile.SaveOffMarketMedication(driver);                 
              
       //@ Step click on 'Medication Reconcilliation/Snapshot' button
       AM.Forms.Nursing.MedicationProfile.btn_Medication_Reconciliation(driver).click();
       
       //@ Step click on ' Continue to Reconciliation' button
       Waits.ForElementToBeClickable(driver, AM.Forms.Nursing.MedicationProfile.btn_Continue_Reconciliation(driver));
       AM.Forms.Nursing.MedicationProfile.btn_Continue_Reconciliation(driver).click();
       
       //@ Step Complete the 'Medication Reconciliation' section
       Waits.ForElementToBeClickable(driver, AM.Forms.Nursing.MedicationProfile.rb_Yes(driver));
       AM.Forms.Nursing.MedicationProfile.rb_Yes(driver).click();
       AM.Forms.Nursing.MedicationProfile.txt_Explain(driver).sendKeys(Datatable.GetValue("MP_Explain"));
       
       //@ Step Complete the 'Medication Review Acknowledgement' section
       Waits.ForElementToBeClickable(driver, AM.Forms.Nursing.MedicationProfile.rb_Acknowledgement_Yes(driver));
       AM.Forms.Nursing.MedicationProfile.rb_Acknowledgement_Yes(driver).click();
       AM.Forms.Nursing.MedicationProfile.txt_Acknowledgement_Explain(driver).sendKeys(Datatable.GetValue("MP_Acknowledgement_Explain"));
       
       //@ Step Enter the Electronic Signature and click on 'Sign Document' button       
       AM.Forms.Nursing.MedicationProfile.txt_ES_ElectronicSignature(driver).sendKeys(Datatable.GetValue("ES_ElectronicSignature"));
       AM.Forms.Nursing.MedicationProfile.dt_ES_SignatureDate(driver).sendKeys(Datatable.GetValue("ES_SignatureDate"));
       Waits.ForElementToBeClickable(driver, AM.Forms.Nursing.MedicationProfile.btn_SignDocument(driver));
       AM.Forms.Nursing.MedicationProfile.btn_SignDocument(driver).click();
          
       //@ Step Verify if the Medication Profile task is added to Misc tab and also verify the status
       Waits.ForBrowserLoad(driver);     
       Report.attachScreenShotToReport(driver);
       AM.Episode.EpisodeManager.VerifyStatus(driver, 1); 
       

	}
   	
	 @AfterClass(alwaysRun = true)
	 public void Teardown() {
		 components.Browser.teardownTest();
	 }

}
