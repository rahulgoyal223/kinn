package Scripts.Claims.ManagedCare;

import DataSource.Datatable;
import DataSource.GlobalData;
import components.Browser;
import components.Report;
import components.Waits;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

public class AM_BM2_ManagedCare_Secondary_HH47 {


    /************************************************************************************
     'Class name                     : 	AM_BM2_ManagedCare_Secondary_HH47
     'JIRA ID						:	HH-47
     'Description                    : 	To verify that Episode Condition carries forward onto Secondary Payer Claim
     'Input Parameters            	: 	Patient Name, MRN
     'Output Parameters         		: 	Patient Name,MRN
     'Assumptions                   	: 	Test Data is present in the Global Sheet.
     'Use                            : 	The following test verifies the functionality of user is able to click on 'Void' buttons in the Ready to Send tab of the Managed Care->Secondary Payer.
     'Tags                           : 	Smoke Test
     * @throws Exception
     ************************************************************************************/

    public static void main(String[] args) throws Exception {
        Test();
    }

    @Test(groups = {"AM_BM2_Claims"})
    public static void Test() throws Exception {
        /******************************************************************
         * Mandate to call below lines at every test case start up
         ******************************************************************/
        String PM_PatientName = null;
        String dataSheetName = null;

        Report.generateReportsFile("html", "AM_BM2_ManagedCare_HH2416");
        Report.SetTestName("AM_BM2_ManagedCare_HH2416", "AM_84411_Claims_BM2_MC Secondary_Verify if user can void a claim from Pending Payment tab");
        Report.assignCategory("ManagedCare");
        AM.Login.openAppAndSubmitCredentials();
        WebDriver driver = Browser.getDriver();
        String dataFileName = "Claims\\ManagedCare\\AM_BM2_ManagedCare_Secondary_HH47.xlsx";
        dataSheetName = "CreatePatientYesNo";

        Datatable.loadDataSheet(dataFileName, dataSheetName);
        /*****************************************************************/

        ///@@Step :To create new patient if needed
        if (Datatable.GetValue("CreatePatient").equals("Yes")) {
            //@Step :To load sheet
            Datatable.loadDataSheet(dataFileName, "CreatePatient");
            AM.Menu.TopMenu.Select(driver, "File/New/Patient");
            AM.Patient.AddNewPatient.FillAddNewPatient(driver);
            AM.Patient.AddNewPatient.btn_AddPatient(driver).click();
            AM.Patient.AddNewPatient.clk_btnYes(driver, "Yes");
        }

        if (!GlobalData.getPatientFullName().isEmpty()) {
            PM_PatientName = GlobalData.getPatientFullName();
        } else {
            Datatable.GetValue("PM_PatientName");
        }
        String insuranceName = Datatable.GetValue("Ins_Primary");
        String secondaryInsuranceName = Datatable.GetValue("Ins_Secondary");

        Datatable.loadDataSheet(dataFileName, "EditCondition");
        AM.Menu.TopMenu.Select(driver, "Go To/Patient Manager");
        AM.Patient.PatientManager.SelectActivePatient(driver);
        AM.Menu.TopMenu.Select(driver, "Edit/Condition");
        AM.Episode.EditCondition.FillEditConditionForm(driver);

        Datatable.loadDataSheet(dataFileName, "VerifyOASIS");
        //@Step :To verify and fill the OASIS
        if (Datatable.GetValue("OASISCheck").equals("Yes")) {
            AM.Forms.Nursing.OasisShortcut.FillOutOasisStartOfCare(driver);
        }
        //@step Billing Manager
        AM.Billing.Claims.ClaimsManagerShortcut.MoveManagedCareFromReadyToPendingApproval(driver, PM_PatientName, insuranceName);

        //@Step - Approving the Claim and verifying the successful message------Pending Approval tab
        AM.Billing.Claims.ClaimsManagerShortcut.MoveManagedCareFromPendingApprovalToReadyToSend(driver, PM_PatientName, insuranceName);

        //@Step - generate Claim by clicking on Send manually button and verifying the successful message----Ready to Send tab
        String managedCareClaimNumber = AM.Billing.Claims.ClaimsManagerShortcut.MoveManagedCareFromReadyToSendToPendingPaymentByMarkPendingPayment_getClaimNumber(driver, PM_PatientName, insuranceName);

        //@Step - Navigating to Remittance Managers page
        AM.Billing.Remittance.RemittanceShortcut.SetupRemittanceWithClaim(driver, dataFileName, "Remittance", insuranceName, managedCareClaimNumber);
        AM.Billing.Remittance.RemittanceManager.FillClaimDetails(driver, 1);
        AM.Billing.Remittance.RemittanceManager.chk_PP_SecondarypayerEnable(driver).click();
        WebDriverWait wait = new WebDriverWait(driver,10);
        WebElement saveAndCompleteButton = AM.Billing.Remittance.RemittanceManager.btn_AR_SaveandComplete(driver);
        saveAndCompleteButton.click();
        wait.until(ExpectedConditions.stalenessOf(saveAndCompleteButton));
        Waits.ForBrowserLoad(driver);

        //@step Secondary Managed care -Ready tab
        AM.Billing.Claims.ClaimsManagerShortcut.MoveManagedCareSecondaryFromReadyToPendingApproval(driver, PM_PatientName, secondaryInsuranceName);

        //@Step - Approving the Claim and verifying the successful message------Pending Approval tab
        AM.Menu.TopMenu.Select(driver, "Go To/Billing Manager");
        AM.Billing.BillingManager.SelectMenu(driver, "Secondary Payer/Pending Approval");
        AM.Billing.Claims.ClaimsManager.lst_PA_Payer(driver).selectByVisibleText(secondaryInsuranceName);
        Waits.ForGlobalAjaxLoader(driver);
        AM.Billing.Claims.ClaimsManager.txt_PA_Searchbox(driver).sendKeys(PM_PatientName);
        AM.Billing.Claims.ClaimsManager.PA_SelectPatient(driver, PM_PatientName);
        AM.Billing.Claims.ClaimsManager.Edit_PA_icon(driver).click();

        Waits.ForGlobalAjaxLoader(driver);

        //Verify Prefill codes
        String dataPageName = "EditCondition";
        AM.Billing.Claims.Claim_Worksheet.CheckConditionCodes(driver, dataFileName, dataPageName);
        AM.Billing.Claims.Claim_Worksheet.CheckOccurrenceCodes(driver, dataFileName, dataPageName);
        AM.Billing.Claims.Claim_Worksheet.CheckRemarks(driver, dataFileName, dataPageName);
    }

    @AfterClass(alwaysRun = true)
    public static void Teardown() {
        Browser.teardownTest();
    }

}
