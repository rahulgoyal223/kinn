package AM.Billing.Reports;

import components.Waits;
import components.ksGrid;
import org.openqa.selenium.WebDriver;

/**
 * Created by austin.ledbetter on 2/16/2017.
 */
public class BillingReportShortcuts {
    public static String GetBalanceForPatientFromPatientBalanceDetailReport(WebDriver driver, String patientFirstName) throws Exception {
        AM.Menu.TopMenu.Select(driver, "Go To/Billing Manager");
        Thread.sleep(Waits.getSleepLevelFive());
        AM.Billing.BillingManager.ClickLink(driver, "Reports/Patient Balance Detail Report");
        Waits.ForGlobalAjaxLoader(driver);
        PatientBalanceDetailReport_Filters.btn_insurance_selectall(driver).click();
        PatientBalanceDetailReport_Filters.btn_payertype_selectall(driver).click();
        PatientBalanceDetailReport_Filters.btn_branch_selectall(driver).click();
        PatientBalanceDetailReport_Filters.EnterPatientSearchTerm(driver, patientFirstName);
        PatientBalanceDetailReport_Filters.chk_Unbilled(driver).click();
        PatientBalanceDetailReport_Filters.btn_ApplyFilters(driver).click();
        Waits.ForGlobalAjaxLoader(driver);
        int colNumber = ksGrid.getColumnIndex(driver, "Balance");
        return ksGrid.getCellDataFromFooter(driver, colNumber);
    }

    public static String GetBalanceForPatientFromPPSClaimsAgingReport(WebDriver driver, String patientFirstName) throws Exception {
        AM.Menu.TopMenu.Select(driver, "Go To/Billing Manager");
        Thread.sleep(Waits.getSleepLevelFive());
        AM.Billing.BillingManager.ClickLink(driver, "Reports/PPS Claims Aging Report");
        Waits.ForGlobalAjaxLoader(driver);
        PPSClaimAgingReport_Filters.btn_insurance_selectall(driver).click();
        PPSClaimAgingReport_Filters.btn_payertype_selectall(driver).click();
        PPSClaimAgingReport_Filters.btn_branch_selectall(driver).click();
        PPSClaimAgingReport_Filters.EnterPatientSearchTerm(driver, patientFirstName);
        PPSClaimAgingReport_Filters.btn_ApplyFilters(driver).click();
        Waits.ForGlobalAjaxLoader(driver);
        int colNumber = ksGrid.getColumnIndex(driver, "Age 0-30");
        return ksGrid.getCellData(driver, 0, colNumber);
    }
}
