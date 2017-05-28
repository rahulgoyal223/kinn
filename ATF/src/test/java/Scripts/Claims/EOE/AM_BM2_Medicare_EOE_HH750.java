package Scripts.Claims.EOE;

import AM.Billing.Claims.ClaimsManager;
import AM.Billing.Claims.ClaimsManagerShortcut;
import AM.Billing.Remittance.RemittanceShortcut;
import AM.Billing.Reports.BillingReportShortcuts;
import DataSource.Datatable;
import DataSource.GlobalData;
import Database.GLExportDatabaseInteraction;
import components.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import java.text.DecimalFormat;


public class AM_BM2_Medicare_EOE_HH750 {

    /************************************************************************************
     'Class name                    : 	AM_BM2_Medicare_EOE_HH750
     'JIRA ID						:	HH-750
     'Description                   : 	Verify Lupa Episode is not accounting missed visits into calculation
     'Input Parameters           	:
     'Output Parameters        		:
     'Assumptions                   : 	Test Data is present in the Global Sheet.
     'Use                           : 	Verify Lupa Episode is not accounting missed visits into calculation

     'Tags                          : 	Regression
     ************************************************************************************/


    public static void main(String[] args) throws Exception {
    	AM_BM2_Medicare_EOE_HH750();
    }

    @Test//(groups = { "AM_BM2_Claims", "AM_Regression" })*/
    public static void AM_BM2_Medicare_EOE_HH750() throws Exception {

        String dataSheetName = null;
        String PM_PatientName = null;
        /******************************************************************
         * Mandate to call below lines at every test case start up
         ******************************************************************/
        //@Reports Configuration
        Report.generateReportsFile("html", "AM_BM2_Medicare_EOE_HH750");
        Report.SetTestName("AM_BM2_Medicare_EOE_HH750", "Verify Lupa Episode is not accounting missed visits into calculation");
        Report.assignCategory("EOE");
        Report.assignCategory("Pending Approval");
        //@Open Application and submit credentials
        AM.Login.openAppAndSubmitCredentials();
        //@ Get Current WebDriver
        WebDriver driver = Browser.getDriver();
        //@Import Test data sheet
        String dataFileName = "Claims\\Medicare\\AM_BM2_Medicare_EOE_HH750.xlsx";
        dataSheetName = "CreatePatientYesNo";
        Datatable.loadDataSheet(dataFileName, dataSheetName);
        /****************************************************************/
        //@Step - Create New Patient, if Required
        if (Datatable.GetValue("CreatePatient").equals("Yes")) {
            //@ Load Data and Add New Patient
            Datatable.loadDataSheet(dataFileName, "CreatePatient");
            AM.Menu.TopMenu.Select(driver, "File/New/Patient");
            AM.Patient.AddNewPatient.FillAddNewPatient(driver);
            AM.Patient.AddNewPatient.btn_AddPatient(driver).click();
            AM.Patient.AddNewPatient.clk_btnYes(driver, "Yes");
        }
        String patientFirstName = GlobalData.getPatientFirstName();
        if (patientFirstName.isEmpty()) {
            patientFirstName = Datatable.GetValue("PD_FisrtName");
        }
        if (!GlobalData.getPatientFullName().isEmpty()) {
            PM_PatientName = GlobalData.getPatientFullName();
        } else {
            PM_PatientName = Datatable.GetValue("PM_PatientName");
        }
        String insuranceName = Datatable.GetValue("Ins_Primary");

        //@Step Fill out OASIS
        Datatable.loadDataSheet(dataFileName, "AM_BM2_VerifyOasis");
        if (Datatable.GetValue("OASISCheck").equals("Yes")) {
            AM.Forms.Nursing.OasisShortcut.FillOutOasisStartOfCare(driver);
        }

        //@ Step add 2 visits and complete them
        for (int i = 1; i <= 2; i++) {
            Datatable.loadDataSheet(dataFileName, "AM_VerifyPTVisit" + i);
            AM.Menu.TopMenu.Select(driver, "Go To/Patient Manager");
            AM.Patient.PatientManager.SelectActivePatient(driver);
            AM.Episode.EpisodeManager.SelectTaskTab(driver, "PT");
            AM.Episode.EpisodeManager.AddScheduleTasks(driver);
            AM.Episode.EpisodeManager.btn_InsertUpdateTasks(driver).click();

            driver.findElement(By.id("Details" + i)).click();
            AM.Menu.TopMenu.Select(driver, "View/PT Visit");

            Waits.ForBrowserLoad(driver);
            AM.Forms.PT.PTVisit.FillPTVisitRecord(driver);
            AM.Forms.PT.PTVisit.chk_SignatureRequired(driver).click();
            AM.Forms.PT.PTVisit.FillElectronicSignature(driver);
            AM.Forms.PT.PTVisit.btn_ES_Approve(driver).click();
            Waits.ForGlobalAjaxLoader(driver);
        
        }
        //Complete some missed visits
        for (int i = 1; i <= 2; i++) {
           Datatable.loadDataSheet(dataFileName, "AM_VerifyOTMV" + i);
            AM.Menu.TopMenu.Select(driver, "Go To/Patient Manager");
            AM.Patient.PatientManager.SelectActivePatient(driver);
            AM.Episode.EpisodeManager.SelectTaskTab(driver, "OT");
           Waits.ForGlobalAjaxLoader(driver);
            AM.Episode.EpisodeManager.AddScheduleTasks(driver);
            AM.Episode.EpisodeManager.btn_InsertUpdateTasks(driver).click();

            driver.findElement(By.id("Details" + i)).click();
            Waits.ForGlobalAjaxLoader(driver);
           AM.Episode.TaskDetails.FillMissedVisit(driver); 
            Waits.ForGlobalAjaxLoader(driver);
        }
            //485

        Datatable.loadDataSheet(dataFileName, "AM_BM2_VerifyCMS485");
        if (Datatable.GetValue("CMS485Check").equals("Yes")) {
            AM.Forms.Nursing.CMS485Shortcut.FillOutCMS485(driver);
        }
        
        


        //Go to Billing Manager and Create RAP
        String rapClaimNumber = ClaimsManagerShortcut.MoveRAPFromReadyAllTheWayToPendingPayment_getClaimNumber(driver, PM_PatientName, insuranceName);

        //@Step - Adding claims in Remittance
        RemittanceShortcut.FillOutAndCompleteRemittance(driver, dataFileName, "RAPRemittance", insuranceName, rapClaimNumber);

        //@Step - Moving EOE to Pending Approval
        String EOEClaimNumber =ClaimsManagerShortcut.MoveEOEFromReadyToPendingApproval_getClaimNumber(driver, PM_PatientName, insuranceName);
	       
	       boolean adjustmentsExisits= Database.BillingDatabaseInteraction.checkEpisodeadjustments(EOEClaimNumber);
	       Assert.assertTrue(adjustmentsExisits);
	       
    }

    @AfterTest(alwaysRun = true)
    public static void Teardown() {
        Browser.teardownTest();
    }

}


