package Scripts.Clinical.Visits;


import java.util.ArrayList;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.Assert;

import com.aventstack.extentreports.Status;

import DataSource.Datatable;
import DataSource.GlobalData;
import components.Browser;
import components.Report;
import components.Verify;

public class AM_ClinicalVisit_HH4133 {

	/************************************************************************************
		'Class name                     : 	AM_ClinicalVisit_HH4133
		'JIRA ID						:	HH-3943
		'Description                    :   To verify the box 26 of 485 print view displays correctly for 485 v5 	
		'Input Parameters           	: 	
		'Output Parameters        		: 	
		'Assumptions                    : 	Test Data is present in the Global Sheet.
		'Use                            : 	The following test verifies the box 26 of 485 print view displays correctly for 485 v5
		'Tags                           : 	Regression, Smoke Test, E2E
	 ************************************************************************************/
	public static void main(String[] args) throws Exception {
		AM_ClinicalVisit_HH4133();
	}

	@Test(groups = { "AM_Regression", "AM_Clinical" })
	public static void AM_ClinicalVisit_HH4133() throws Exception {
		String dataSheetName = null;
		String PM_PatientName = null;
		//@Reports Configuration			
		Report.generateReportsFile("html","AM_ClinicalVisit_HH4133");
		Report.SetTestName("AM_ClinicalVisit_HH4133", "To verify the box 26 of 485 print view displays correctly for 485 v5");
		Report.assignCategory("ClinicalVisit");
		//@Open Application and submit credentials
		AM.Login.openAppAndSubmitCredentials();
		//@ Get Current WebDriver
		WebDriver driver = Browser.getDriver();
		//@Import Test data sheet
		String dataFileName = "Clinical\\ClinicalVisit\\AM_ClinicalVisit_HH4133.xlsx";
		dataSheetName = "OASIS_C1_SoC";
		Datatable.loadDataSheet(dataFileName, dataSheetName);
		//**********************************************************************

		if (!GlobalData.getPatientFullName().isEmpty()) {
			PM_PatientName = GlobalData.getPatientFullName();
		}

		//@@Step  :To create new patient
		if (Datatable.GetValue("CreatePatient").equals("Yes")) {
			//@Step :To load sheet
			Datatable.loadDataSheet(dataFileName, "CreatePatient");            
			AM.Menu.TopMenu.Select(driver, "File/New/Patient");
			AM.Patient.AddNewPatient.FillAddNewPatient(driver);
			AM.Patient.AddNewPatient.AddPatient(driver);     
			PM_PatientName = Datatable.GetValue("PM_PatientName");      
		} else {
			//@ Step -  :To navigate to patient manager
			AM.Menu.TopMenu.Select(driver, "Go To/Patient Manager");      
			AM.Patient.PatientManager.SelectActivePatient(driver);
		}

		System.out.println("Patient Name is : "
				+ PM_PatientName);

        Datatable.loadDataSheet(dataFileName, "OASIS_C1_SoC");    
        //@Step :Fill demographics in the OASIS SOC
        if(Datatable.GetValue("OASISCheck").equals("Yes")){
        //@ Step - Fill patient OASIS demographics form and Save
          AM.Menu.TopMenu.Select(driver, "Go To/Patient Manager");
          Verify.ExactPageTitle(driver, "Patient Manager | Kinnser Software");
          AM.Patient.PatientManager.SelectActivePatient(driver); 
          AM.Episode.EpisodeManager.SelectTaskTab(driver, "Nursing");
          AM.Episode.EpisodeManager.SelectScheduleTasks(driver, "OASIS-C2 Start of Care");
          AM.Forms.Nursing.OasisSOC.SelectForm(driver, "Demographics");
          AM.Forms.Nursing.OasisSOC.FillDemographicForm(driver);
          AM.Forms.Nursing.OasisSOC.SaveOasisPage(driver);   
        }

		//@ Step - Schedule CMS 485 form 
		Datatable.loadDataSheet(dataFileName, "CMS485");	
		if (Datatable.GetValue("CMS485Check").equals("Yes")){
			AM.Menu.TopMenu.Select(driver, "Go To/Patient Manager");
			AM.Patient.PatientManager.SelectActivePatient(driver);
			AM.Episode.EpisodeManager.SelectTaskTab(driver, "Orders");        
			AM.Episode.EpisodeManager.ScheduleTask(driver);
		}


		//@ Step - Open CMS 485 Print Preview
		AM.Episode.EpisodeManager.SelectScheduleTasks(driver, "CMS 485");
        AM.Menu.TopMenu.Select(driver, "File/Print Preview");
        
        //@ Step - Verify the text displayed in box 26 is the expected
        String expectedText = "26. I certify/ recertify that this patient is confined " +
        		"to his/her home and needs intermittent skilled nursing care, physical " +
        		"therapy and/or speech therapy or continues to need occupational therapy. " +
        		"This patient is under my care, and I have authorized the services on this " +
        		"plan of care and I or another physician will periodically review this plan. " +
        		"I attest that a valid face-to-face encounter occurred (or will occur) within " +
        		"timeframe requirements and it is related to the primary reason the patient " +
        		"requires home health services.";
        
        try {

            Assert.assertEquals(
            		AM.Forms.Orders.CMS485.printBoxNumber(driver, 26).getText(), 
            		expectedText
            		);
        	Report.Log(Status.PASS, "Box 26. verbiage is correct");
         
        } catch(AssertionError ae){
        	Report.Log(Status.FAIL, "Box 26. verbiage is wrong. Error: " + ae.getMessage());
        	throw ae;
        }
       
        //@ Step - Navigate back to the 485, complete and submit the form
        driver.navigate().back();
		AM.Forms.Orders.CMS485.FillCMS485Form(driver);
        AM.Forms.Orders.CMS485.SubmitCMS485(driver);

		//@ Step - Open CMS 485 Print Preview again
		AM.Menu.TopMenu.Select(driver, "Go To/Patient Manager");
		AM.Patient.PatientManager.SelectActivePatient(driver);
		AM.Episode.EpisodeManager.SelectTaskTab(driver, "Orders");
		AM.Episode.EpisodeManager.PrintViewScheduleTasks(driver, "CMS 485");
		AM.Episode.EpisodeManager.OpenPrintViewTab(driver);

        //@ Step - Verify the text displayed in box 26 is the expected after submitting the form
        try {

            Assert.assertEquals(
            		AM.Forms.Orders.CMS485.printBoxNumber(driver, 26).getText(), 
            		expectedText
            		);
        	Report.Log(Status.PASS, "Box 26. verbiage is correct");
         
        } catch(AssertionError ae){
        	Report.Log(Status.FAIL, "Box 26. verbiage is wrong. Error: " + ae.getMessage());
        	throw ae;
        }

	}

	@AfterClass(alwaysRun = true)
	public static void Teardown() {
		components.Browser.teardownTest();
	}

}


