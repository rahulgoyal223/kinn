package Scripts.Corporate;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import DataSource.Datatable;
import DataSource.GlobalData;
import components.Browser;
import components.Config;
import components.Report;
import components.Waits;

public class CM_OASIS_Export_HH4063 {

	/************************************************************************************
		'Class name                     : 	CM_OASIS_Export_HH4063
		'JIRA ID						:	HH-4063
		'Description                    : 	To Verify Corporate OASIS Export include OASIS-C2
		'Input Parameters           	: 	Patient Name
		'Output Parameters        		: 	
		'Assumptions                    : 	Test Data is present in the Global Sheet.
		'Use                            : 	The following test verifies OASIS-C2 is included in Corporate OASIS Export
		'Tags                           : 	Regression, Smoke Test, E2E
	 ************************************************************************************/
	public static void main(String[] args) throws Exception {
		CM_OASIS_Export_HH4063();
	}

	@Test(groups = { "AM_Regression", "AM_Clinical" })
	public static void CM_OASIS_Export_HH4063() throws Exception {
		String dataSheetName = null;
		String PM_PatientName = null;
		//@Reports Configuration			
		Report.generateReportsFile("html","CM_OASIS_Export_HH4063");
		Report.SetTestName("CM_OASIS_Export_HH4063", "To Verify M2001 in OASIS-C2 Start Of Care (Nursing)");
		Report.assignCategory("RAP");
		//@Open Application and submit credentials
		AM.Login.openAppAndSubmitCredentials();
		//@ Get Current WebDriver
		WebDriver driver = Browser.getDriver();
		//@Import Test data sheet
		String dataFileName = "Clinical\\ClinicalVisit\\CM_OASIS_Export_HH4063.xlsx";
		dataSheetName = "AM_CV_OASISC2StartOfCare_HH4063";
		Datatable.loadDataSheet(dataFileName, dataSheetName);
		//**********************************************************************

		//@@Step  :To create new patient
		if (Datatable.GetValue("CreatePatient").equals("Yes")) {
			//@Step :To load sheet
			Datatable.loadDataSheet(dataFileName, "CreatePatient");            
			AM.Menu.TopMenu.Select(driver, "File/New/Patient");
			AM.Patient.AddNewPatient.FillAddNewPatient(driver);
			AM.Patient.AddNewPatient.AddPatient(driver);         
		}                  

		if (!GlobalData.getPatientFullName().isEmpty()) {
			PM_PatientName = GlobalData.getPatientFullName();
		}else {
			PM_PatientName = Datatable.GetValue("PM_PatientName");
		}
		System.out.println("Patient Name is : "
				+ PM_PatientName);
		
		Waits.ForBrowserLoad(driver);
		
		//@ Step -  :To select and schedule a Task 		
		AM.Episode.EpisodeManager.SelectScheduleTasks(driver, "OASIS-C2 Start of Care");

		Datatable.loadDataSheet(dataFileName, dataSheetName);
		Waits.ForBrowserLoad(driver);
		
		//@Step Fill for OASIS-C2 Discharge Demographics
	 	AM.Forms.Nursing.OasisSOC.SelectForm(driver, "Demographics");
		AM.Forms.Nursing.OasisSOC.FillDemographicForm(driver);
		AM.Forms.Nursing.OasisSOC.SaveOasisPage(driver);
		
		
		AM.Menu.TopMenu.Select(driver, "File/OASIS Menu");
		Waits.ForBrowserLoad(driver);
		AM.Forms.Nursing.OasisSOC.SubmitOasisWithSignature(driver);
		
		Waits.ForBrowserLoad(driver);
		
		//@Step Go to Corporate OASIS Export page
		CM.Swapuser.lst_CM_Swapuser(driver).selectByVisibleText("KOS Corporate Billing Manager 2");
		Waits.ForBrowserLoad(driver);
		
		CM.Login.skipExpiredPassword(driver);
		
        AM.Menu.TopMenu.Select(driver, "Go To");
        driver.findElement(By.id("goto-corporate-item-link")).click();
        Waits.ForBrowserLoad(driver);
        
        CM.Administration.Menu.lnkCorporateOASISExport(driver).click();
        
        Datatable.loadDataSheet(dataFileName, "CorporateOASISExport");
        CM.Administration.CorporateOASISExport.FillFilters(driver);
        
        CM.Administration.CorporateOASISExport.getBtnApplyFilter(driver).click();
        CM.Administration.CorporateOASISExport.VerifyReportRow(driver, PM_PatientName, "OASIS-C2 Start of Care");

	}

	 @AfterClass(alwaysRun = true)
	 public static void Teardown() {
		 components.Browser.teardownTest();
	 }
    
}
