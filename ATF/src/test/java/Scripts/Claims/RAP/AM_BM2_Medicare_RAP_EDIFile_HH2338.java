package Scripts.Claims.RAP;

import DataSource.Datatable;
import DataSource.GlobalData;
import components.Browser;
import components.Report;
import components.Verify;
import components.Waits;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

/**
 * Created by austin.ledbetter on 1/27/2017.
 */
public class AM_BM2_Medicare_RAP_EDIFile_HH2338 {
    /************************************************************************************
     'Class name                     : 	AM_BM2_Medicare_RAP_EDIFile_HH2338
     'JIRA ID						:	HH-2238
     'Description                    : 	Verify that EDI is showing the right Relationship to Insured in the SBR segment
     'Input Parameters           	:
     'Output Parameters        		:
     'Assumptions                    : 	Test Data is present in the Global Sheet.
     'Use                            : 	The following test verifies that EDI is showing the right Relationship to Insured in the SBR segment

     'Tags                           : 	Regression
     ************************************************************************************/





    public static void main(String[] args) throws Exception {
    	AM_BM2_Medicare_RAP_EDIFile_HH2338();
    }

    @Test(groups = { "AM_BM2_Claims", "AM_Regression" })
    public static void AM_BM2_Medicare_RAP_EDIFile_HH2338() throws Exception {

        String dataSheetName = null;
        String PM_PatientName = null;
        /******************************************************************
         * Mandate to call below lines at every test case start up
         ******************************************************************/
        //@Reports Configuration
        Report.generateReportsFile("html","AM_BM2_Medicare_RAP_EDIFile_HH2338");
        Report.SetTestName("AM_BM2_Medicare_RAP_EDIFile_HH2338","Verify the EDI file has the right Relationship to Insured");
        Report.assignCategory("RAP");
        Report.assignCategory("Ready");
        //@Open Application and submit credentials
        AM.Login.openAppAndSubmitCredentials();
        //@ Get Current WebDriver
        WebDriver driver = Browser.getDriver();
        //@Import Test data sheet
        String dataFileName = "Claims\\Medicare\\AM_BM2_Medicare_RAP_HH2238.xlsx";
        dataSheetName = "AM_BM2_Medicare_RAP_HH2238";
        Datatable.loadDataSheet(dataFileName, dataSheetName );
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

        if (!GlobalData.getPatientFullName().isEmpty()){
            PM_PatientName = GlobalData.getPatientFullName();
        }else {
            PM_PatientName = Datatable.GetValue("PM_PatientName");
        }


        //Update the Insured relation option on the Edit>Insurance
        AM.Menu.TopMenu.Select(driver, "Go To/Patient Manager");
        Verify.ExactPageTitle(driver, "Patient Manager | Kinnser Software");  
        AM.Patient.PatientManager.SelectActivePatient(driver);
        AM.Menu.TopMenu.Select(driver, "Edit");
        AM.Insurance.PatientInsurance.lnk_Insurance(driver).click();
        AM.Insurance.PatientInsurance.SelectInsurance(driver, "Palmetto GBA").click();
        AM.Insurance.PatientInsurance.lst_relationtoins(driver).selectByVisibleText("Spouse");
        AM.Insurance.PatientInsurance.btn_update(driver).click();

        //@Step Fill out OASIS
        Datatable.loadDataSheet(dataFileName, "AM_BM2_VerifyOasis");
        if(Datatable.GetValue("OASISCheck").equals("Yes")) {
            AM.Menu.TopMenu.Select(driver, "Go To/Patient Manager");
            Verify.ExactPageTitle(driver, "Patient Manager | Kinnser Software");  
            AM.Patient.PatientManager.SelectActivePatient(driver);
            Verify.ExactPageTitle(driver, "Episode Manager | Kinnser Software");  
            AM.Episode.EpisodeManager.SelectTaskTab(driver, "Nursing");
            AM.Episode.EpisodeManager.SelectScheduleTasks(driver, "Oasis-C2 Start of Care");
            AM.Forms.Nursing.OasisSOC.SelectForm(driver, "Demographics");
            AM.Forms.Nursing.OasisSOC.FillDemographicForm(driver);
            AM.Forms.Nursing.OasisSOC.btn_DG_Save(driver).click();
            AM.Forms.Nursing.OasisSOC.saveAlertWindow(driver);
            AM.Menu.TopMenu.Select(driver, "File/OASIS Menu");
            AM.Forms.Nursing.OasisSOC.SelectForm(driver, "Patient History and Diagnoses");
            AM.Forms.Nursing.OasisSOC.FillPatientDiagnosisForm(driver);
            AM.Forms.Nursing.OasisSOC.btn_DG_Save(driver).click();;
            AM.Forms.Nursing.OasisSOC.saveAlertWindow(driver);
            AM.Menu.TopMenu.Select(driver, "File/OASIS Menu");
            AM.Forms.Nursing.OasisSOC.SelectForm(driver, "Therapy Need and Plan of Care");
            AM.Forms.Nursing.OasisSOC.FillTherapyNeedForm(driver);
            AM.Forms.Nursing.OasisSOC.btn_DG_Save(driver).click();
            AM.Forms.Nursing.OasisSOC.saveAlertWindow(driver);

            //@ Step - Schedule Task in Episode Manager
            AM.Menu.TopMenu.Select(driver, "Go To/Patient Manager");
            Verify.ExactPageTitle(driver, "Patient Manager | Kinnser Software");  
            AM.Patient.PatientManager.SelectActivePatient(driver);

            Verify.ExactPageTitle(driver, "Episode Manager | Kinnser Software");  
            AM.Episode.EpisodeManager.SelectScheduleTasks(driver, "Oasis-C2 Start of Care");
            AM.Forms.Nursing.OasisSOC.SubmitOasisWithSignature(driver);
            


            //@ Step - Approve Scheduled Task
            AM.Menu.TopMenu.Select(driver, "Go To/Patient Manager");
            components.Verify.ExactPageTitle(driver, "Patient Manager | Kinnser Software");
            AM.Patient.PatientManager.SelectActivePatient(driver);
            Verify.ExactPageTitle(driver, "Episode Manager | Kinnser Software");  

            AM.Episode.EpisodeManager.SelectScheduleTasks(driver, "Oasis-C2 Start of Care");
            AM.Forms.Nursing.OasisSOC.btn_Approve(driver).click();
        }

        Datatable.loadDataSheet(dataFileName, "AM_BM2_VerifyCMS485");
        if(Datatable.GetValue("CMS485Check").equals("Yes")) {
            AM.Menu.TopMenu.Select(driver, "Go To/Patient Manager");
            Verify.ExactPageTitle(driver, "Patient Manager | Kinnser Software");  
            AM.Patient.PatientManager.SelectActivePatient(driver);
            Verify.ExactPageTitle(driver, "Episode Manager | Kinnser Software");  
            AM.Episode.EpisodeManager.SelectTaskTab(driver, "Orders");
            AM.Episode.EpisodeManager.ScheduleTask(driver);
            AM.Episode.EpisodeManager.SelectScheduleTasks(driver, "CMS 485");
            AM.Forms.Orders.CMS485.FillCMS485Form(driver);
            AM.Forms.Orders.CMS485.btn_Submit(driver).click();

            //@Step - Approve CMS 485 form
            AM.Menu.TopMenu.Select(driver, "Go To/Patient Manager");
            Verify.ExactPageTitle(driver, "Patient Manager | Kinnser Software");  
            AM.Patient.PatientManager.SelectActivePatient(driver);
            components.Verify.ExactPageTitle(driver, "Episode Manager | Kinnser Software");
            AM.Episode.EpisodeManager.SelectTaskTab(driver, "Orders");
            AM.Episode.EpisodeManager.SelectScheduleTasks(driver, "CMS 485");
            AM.Forms.Orders.CMS485.btn_Approve(driver).click();
            AM.Forms.Orders.CMS485.chk_ReturnToClinician(driver).click();
            AM.Forms.Orders.CMS485.txt_ElectronicSignature(driver).sendKeys(Datatable.GetValue("clinicianSignature"));
            AM.Forms.Orders.CMS485.btn_Approve(driver).click();
        }

        //Go to Billing Manager and Create RAP

        AM.Menu.TopMenu.Select(driver, "Go To/Billing Manager");
        Verify.ExactPageTitle(driver, "Billing Manager | Kinnser Software");   
        AM.Billing.BillingManager.SelectMenu(driver, "RAP/Ready");
        Verify.ExactPageTitle(driver, "Claims Manager | Kinnser Software");   
        AM.Billing.Claims.ClaimsManager.lst_RE_Payer(driver).selectByVisibleText("Palmetto GBA");
        AM.Billing.Claims.ClaimsManager.lst_RE_ClaimAge(driver).selectByVisibleText("All Claims");
        Waits.ForGlobalAjaxLoader(driver);
        AM.Billing.Claims.ClaimsManager.txt_RE_Searchbox(driver).sendKeys(PM_PatientName);
        AM.Billing.Claims.ClaimsManager.PA_SelectPatient(driver, PM_PatientName);
        AM.Billing.Claims.ClaimsManager.btn_RD_Createclaim(driver).click();
        AM.Billing.Claims.ClaimsManager.VerifyAlertmessage(driver, "Claim(s) have been created successfully and can be reviewed in the Pending Approval tab.");

        //@Step - Approving the Claim and verifying the successful message------Pending Approval tab
        AM.Menu.TopMenu.Select(driver, "Go To/Billing Manager");
        Verify.ExactPageTitle(driver, "Billing Manager | Kinnser Software");   
        AM.Billing.BillingManager.SelectMenu(driver, "RAP/Pending Approval");
        Verify.ExactPageTitle(driver, "Claims Manager | Kinnser Software");
        Waits.ForGlobalAjaxLoader(driver);
        AM.Billing.Claims.ClaimsManager.txt_RE_Searchbox(driver).sendKeys(PM_PatientName);
        AM.Billing.Claims.ClaimsManager.PA_SelectPatient(driver, PM_PatientName);
        AM.Billing.Claims.ClaimsManager.btn_PA_Approve(driver).click();
        AM.Billing.Claims.ClaimsManager.VerifyAlertmessage(driver, "Claim(s) have been approved successfully and can be reviewed in the Ready to Send tab.");
        AM.Menu.TopMenu.Select(driver, "Go To/Billing Manager");
        Verify.ExactPageTitle(driver, "Billing Manager | Kinnser Software"); 
        AM.Billing.BillingManager.SelectMenu(driver, "RAP/Ready to Send");
        Verify.ExactPageTitle(driver, "Claims Manager | Kinnser Software");
        AM.Billing.Claims.ClaimsManager.lst_RE_Payer(driver).selectByVisibleText("Palmetto GBA");
        Waits.ForGlobalAjaxLoader(driver);
        AM.Billing.Claims.ClaimsManager.txt_RE_Searchbox(driver).sendKeys(PM_PatientName);
        AM.Billing.Claims.ClaimsManager.PA_SelectPatient(driver, PM_PatientName);
        AM.Billing.Claims.ClaimsManager.btn_RS_Claimactions(driver).click();
        AM.Billing.Claims.ClaimsManager.btn_RS_Sendmanually(driver).click();
        AM.Billing.Claims.ClaimsManager.VerifyAlertmessage(driver, "Palmetto GBA: Claim(s) have been generated successfully and can be reviewed in the Pending Payment tab.");
        String href = AM.Billing.Claims.ClaimsManager.lnk_RS_Savefile(driver).getAttribute("href");
        Verify.TextFileContains(href, "SBR*P*01");
    }

    @AfterClass(alwaysRun = true)
    public static void Teardown() {
        components.Browser.teardownTest();
    }
}
