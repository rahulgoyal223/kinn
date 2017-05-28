package Scripts.Claims.RAP;

import DataSource.Datatable;
import DataSource.GlobalData;
import components.Browser;
import components.Report;
import components.Waits;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

public class AM_BM2_Medicare_RAP_HH48 {

    /************************************************************************************
     'Class name                     : 	AM_BM2_Medicare_RAP_HH48
     'JIRA ID						:	HH-48
     'Description                    : 	Verify the occurrence codes on the RAP worksheet
     'Input Parameters           	:
     'Output Parameters        		:
     'Assumptions                    : 	Test Data is present in the Global Sheet.
     'Use                            : 	Verify the occurrence codes on the RAP worksheet

     'Tags                           : 	RAP OccurrenceCodes
     ************************************************************************************/


    public static void main(String[] args) throws Exception {
        Test();
    }

    @Test
    public static void Test() throws Exception {

        String dataSheetName = null;
        String PM_PatientName = null;
        /******************************************************************
         * Mandate to call below lines at every test case start up
         ******************************************************************/
        //@Reports Configuration
        Report.generateReportsFile("html", "AM_BM2_Medicare_RAP_HH48");
        Report.SetTestName("AM_BM2_Medicare_RAP_HH48", "Verify the Condition and occurrence codes on the RAP worksheet");
        Report.assignCategory("RAP");
        Report.assignCategory("Regression");
        //@Open Application and submit credentials
        AM.Login.openAppAndSubmitCredentials();
        //@ Get Current WebDriver
        WebDriver driver = Browser.getDriver();
        //@Import Test data sheet
        String dataFileName = "Claims\\Medicare\\AM_BM2_Medicare_RAP_HH48.xlsx";
        dataSheetName = "AM_BM2_Medicare_RAP_HH48";
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

        Datatable.loadDataSheet(dataFileName, "AM_BM2_VerifyCMS485");
        if (Datatable.GetValue("CMS485Check").equals("Yes")) {
            AM.Forms.Nursing.CMS485Shortcut.FillOutCMS485(driver);
        }


        //Go to Billing Manager and Create RAP
        AM.Billing.Claims.ClaimsManagerShortcut.MoveRAPFromReadyToPendingApproval(driver, PM_PatientName, insuranceName);

        //@Step - Approving the Claim and verifying the successful message------Pending Approval tab
        AM.Menu.TopMenu.Select(driver, "Go To/Billing Manager");
        AM.Billing.BillingManager.SelectMenu(driver, "RAP/Pending Approval");
        Waits.ForGlobalAjaxLoader(driver);
        AM.Billing.Claims.ClaimsManager.txt_RE_Searchbox(driver).sendKeys(PM_PatientName);
        AM.Billing.Claims.ClaimsManager.Edit_PA_icon(driver).click();

        AM.Billing.Claims.RAP_Worksheet.EnterConditionCode(driver, "conditionCode1", "21");
        String conditionCode = AM.Billing.Claims.RAP_Worksheet.GetConditionCode(driver, "conditionCode1").getText();
        Assert.assertEquals(conditionCode, "21 - Billing for Denial Notice");
        AM.Billing.Claims.RAP_Worksheet.btn_SaveAndClose(driver).click();
        Waits.ForGlobalAjaxLoader(driver);

        AM.Menu.TopMenu.Select(driver, "Go To/Billing Manager");
        AM.Billing.BillingManager.SelectMenu(driver, "RAP/Pending Approval");
        Waits.ForGlobalAjaxLoader(driver);
        AM.Billing.Claims.ClaimsManager.txt_RE_Searchbox(driver).sendKeys(PM_PatientName);
        AM.Billing.Claims.ClaimsManager.Edit_PA_icon(driver).click();
        Waits.ForGlobalAjaxLoader(driver);
        conditionCode = AM.Billing.Claims.RAP_Worksheet.GetConditionCode(driver, "conditionCode1").getText();
        Assert.assertEquals(conditionCode, "21 - Billing for Denial Notice");
    }

    @AfterTest(alwaysRun = true)
    public static void Teardown() {
        Browser.teardownTest();
    }

}


