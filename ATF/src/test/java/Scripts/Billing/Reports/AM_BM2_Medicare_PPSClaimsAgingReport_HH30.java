package Scripts.Billing.Reports;

import AM.Billing.Claims.ClaimsManagerShortcut;
import AM.Billing.Remittance.RemittanceShortcut;
import AM.Billing.Reports.BillingReportShortcuts;
import DataSource.Datatable;
import DataSource.GlobalData;
import components.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import java.text.DecimalFormat;

public class AM_BM2_Medicare_PPSClaimsAgingReport_HH30 {

    /************************************************************************************
     'Class name                     : 	AM_BM2_Medicare_PPSClaimsAgingReport_HH30
     'JIRA ID						:	HH-30
     'Description                    : 	Verify that EOE line item has same balance after the EOE has been rejected
     'Input Parameters           	:
     'Output Parameters        		:
     'Assumptions                    : 	Test Data is present in the Global Sheet.
     'Use                            : 	Verify that EOE line item has same balance after the EOE has been rejected

     'Tags                           : 	Top5
     ************************************************************************************/


    public static void main(String[] args) throws Exception {
        Test();
    }

    @Test(groups = {"AM_Remittance"})
    public static void Test() throws Exception {

        String dataSheetName = null;
        String PM_PatientName = null;
        /******************************************************************
         * Mandate to call below lines at every test case start up
         ******************************************************************/
        //@Reports Configuration
        Report.generateReportsFile("html", "AM_BM2_Medicare_PPSClaimsAgingReport_HH30");
        Report.SetTestName("AM_BM2_Medicare_PPSClaimsAgingReport_HH30", "Verify that EOE line item has same balance after the EOE has been rejected");
        Report.assignCategory("RAP");
        Report.assignCategory("Ready");
        //@Open Application and submit credentials
        AM.Login.openAppAndSubmitCredentials();
        //@ Get Current WebDriver
        WebDriver driver = Browser.getDriver();
        //@Import Test data sheet
        String dataFileName = "Billing.Reports\\AM_BM2_Medicare_PPSClaimsAgingReport_HH30.xlsx";
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

        Datatable.loadDataSheet(dataFileName, "AM_BM2_VerifyCMS485");
        if (Datatable.GetValue("CMS485Check").equals("Yes")) {
            AM.Forms.Nursing.CMS485Shortcut.FillOutCMS485(driver);
        }

        //Go to Billing Manager and Create RAP
        String rapClaimNumber = ClaimsManagerShortcut.MoveRAPFromReadyAllTheWayToPendingPayment_getClaimNumber(driver, PM_PatientName, insuranceName);

        //@Step - Adding claims in Remittance
        RemittanceShortcut.FillOutAndCompleteRemittance(driver, dataFileName, "RAPRemittance", insuranceName, rapClaimNumber);

        //@Step - EOE Creating claim
        String eoeClaimNumber = ClaimsManagerShortcut.MoveEOEFromReadyAllTheWayToPendingPayment_getClaimNumber(driver, PM_PatientName, insuranceName);

        //@Step - Adding claims in Remittance
        RemittanceShortcut.FillOutAndCompleteTakeBackRemittance(driver, dataFileName, "RAPRemittance", insuranceName, rapClaimNumber);
        RemittanceShortcut.FillOutAndCompleteRemittance(driver, dataFileName, "EOERemittance", insuranceName, eoeClaimNumber);

        //@Step - Moving EOE from Paid to Adjusted
        ClaimsManagerShortcut.MoveEOEFromPaidToAdjustedInPendingApproval(driver, PM_PatientName, insuranceName);

        //@Step - Approve claim
        ClaimsManagerShortcut.MoveEOEFromPendingApprovalToReadyToSend(driver, PM_PatientName);

        //@Step - Resend the claim
        eoeClaimNumber = ClaimsManagerShortcut.MoveEOEFromReadyToSendToPendingPayment_getClaimNumber(driver, PM_PatientName);

        //@Step - Adding claims in Remittance to get balance
        AM.Menu.TopMenu.Select(driver, "Go To/Billing Manager");
        AM.Billing.BillingManager.ClickLink(driver, "Medicare/Remittance Manager");

        AM.Billing.Remittance.RemittanceManager.goToNewRemittance(driver);
        Thread.sleep(Waits.getSleepLevelFive());
        AM.Billing.Remittance.RemittanceManager.lst_AR_Insurance(driver).selectByVisibleText(insuranceName);
        AM.Billing.Remittance.RemittanceManager.AddClaims(driver, eoeClaimNumber);
        AM.Billing.Remittance.EditRemittance.lnk_showClaim(driver, eoeClaimNumber).click();
        String balance = AM.Billing.Remittance.EditRemittance.getLineItemBalance(driver, eoeClaimNumber);
        Thread.sleep(Waits.getSleepLevelThree());

        String ppsClaimsAgeBalance = BillingReportShortcuts.GetBalanceForPatientFromPPSClaimsAgingReport(driver, patientFirstName);

        String pbdrBalance = BillingReportShortcuts.GetBalanceForPatientFromPatientBalanceDetailReport(driver, patientFirstName);

        DecimalFormat df = new DecimalFormat("#.##");
        Assert.assertEquals(df.format(StringToFloatConverter.ConvertToFloat(balance)), df.format(StringToFloatConverter.ConvertToFloat(ppsClaimsAgeBalance)));
        Assert.assertEquals(df.format(StringToFloatConverter.ConvertToFloat(balance)), df.format(StringToFloatConverter.ConvertToFloat(pbdrBalance)));
    }

    @AfterTest(alwaysRun = true)
    public static void Teardown() {
        Browser.teardownTest();
    }

}


