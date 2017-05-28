package Scripts.BI;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import DataSource.Datatable;
import DataSource.GlobalData;
import components.Browser;
import components.Report;
import components.Waits;

public class AddPatientTask {


	/************************************************************************************
	'Class name                     : 	AddPatientTask
	'JIRA ID						:	
	'Description                    : 	Verify that user is able to complete OASIS 
	'Input Parameters           	: 	Patient Name, Claim Number, MRN
	'Output Parameters        		: 	
	'Assumptions                    : 	Test Data is present in the Global Sheet.
	'Use                            : 	The following task completes an OASIS task to appear in CDC ETL
	'Tags                           : 	
	 ************************************************************************************/
	
	public static void main(String[] args) throws Exception {
		AddPatientTask();
	}
	
	@Test
	public static void AddPatientTask() throws Exception {

		
		Report.generateReportsFile("html","BI_AddPatientTask");
	    Report.SetTestName("BI_AddPatientTask", "Verify that user is able to complete OASIS");
	    Report.assignCategory("BI");
	    AM.Login.openAppAndSubmitCredentials();
	    WebDriver driver = Browser.getDriver();
	    String dataFileName = "BI\\BI_AddPatientTask.xlsx";
	    
	    
	 
	
		
		Datatable.loadDataSheet(dataFileName, "VerifyOASIS"); 
		GlobalData.setPatientFullName(Datatable.GetValue("PM_PatientName"));
	    //@Step :To verify and fill the OASIS
	    if(Datatable.GetValue("OASISCheck").equals("Yes")){
	    	
		    AM.Menu.TopMenu.Select(driver, "Go To/Patient Manager");
		    //AM.Patient.PatientManager.SelectActivePatient(driver);
		    AM.Patient.PatientManager.SelectActivePatient(driver);
		    AM.Episode.EpisodeManager.SelectTaskTab(driver, "Nursing");
		    AM.Episode.EpisodeManager.SelectScheduleTasks(driver, "OASIS-C2 Start of Care");
		    AM.Forms.Nursing.OasisSOC.SelectForm(driver, "Demographics");
		    AM.Forms.Nursing.OasisSOC.FillDemographicForm(driver);
		    AM.Forms.Nursing.OasisSOC.SaveOasisPage(driver);
		    AM.Menu.TopMenu.Select(driver, "File/OASIS Menu");
		    AM.Forms.Nursing.OasisSOC.SelectForm(driver, "Patient History and Diagnoses");
		    AM.Forms.Nursing.OasisSOC.FillPatientDiagnosisForm(driver);
		    AM.Forms.Nursing.OasisSOC.SaveOasisPage(driver);
		    AM.Menu.TopMenu.Select(driver, "File/OASIS Menu");
		    AM.Forms.Nursing.OasisSOC.SelectForm(driver, "Therapy Need and Plan of Care");
		    AM.Forms.Nursing.OasisSOC.FillTherapyNeedForm(driver);
		    AM.Forms.Nursing.OasisSOC.SaveOasisPage(driver);
		    
		    //@ Step - Schedule Task in Episode Manager
		    AM.Menu.TopMenu.Select(driver, "Go To/Patient Manager");        
		    AM.Patient.PatientManager.SelectActivePatient(driver);        
		    AM.Episode.EpisodeManager.SelectScheduleTasks(driver, "OASIS-C2 Start of Care");        
		    AM.Forms.Nursing.OasisSOC.SubmitOasisWithSignature(driver);
		    
		     //@ Step - Schedule Task in Episode Manager
		    AM.Menu.TopMenu.Select(driver, "Go To/Patient Manager");
		    components.Verify.ExactPageTitle(driver, "Patient Manager | Kinnser Software");
		    AM.Patient.PatientManager.SelectActivePatient(driver);
		    AM.Episode.EpisodeManager.SelectScheduleTasks(driver, "OASIS-C2 Start of Care");
		    AM.Forms.Nursing.OasisSOC.ApproveOasis(driver);    
	    }
	
	}
	@AfterClass(alwaysRun = true)
	  public static void Teardown() {
	  components.Browser.teardownTest();
	  }
}
