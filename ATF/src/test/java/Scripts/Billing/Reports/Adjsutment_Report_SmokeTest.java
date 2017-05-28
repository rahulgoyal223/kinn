package Scripts.Billing.Reports;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.internal.DynamicGraph.Status;
import org.openqa.selenium.support.ui.Select;












import DataSource.Datatable;
import DataSource.GlobalData;
//import DataSource.Datatable;
import components.Browser;
import components.Report;
import components.Waits;

import java.util.List;


public class Adjsutment_Report_SmokeTest {

	/************************************************************************************
	'Class name                     : 	AM_Billing_Reports_AdjustmentReport_Smoke
	'JIRA ID						:	
	'Description                    : 	Verify validate that the user is able to run the adjustment report and fetch data wothout any errors. 
	'Input Parameters           	: 	
	'Output Parameters        		: 	
	'Assumptions                    : 	Test Data is present in the Global Sheet.
	'Use                            : 	The following test verifies and  validate Verify validate that the user is able to run the adjustment report and fetch data wothout any errors
	'Tags                           : 	Smoke
	 ************************************************************************************/
	 public static void main(String[] args) throws Exception {
		 Adjsutment_Report_SmokeTest();
		}
	    
	    @Test(groups = { "AM_Regression" })
		public static void Adjsutment_Report_SmokeTest() throws Exception {	
	    	
		
	
    Report.generateReportsFile("html","AM_BM2_Reports_Adjustment_report_SmokeTest");
    Report.SetTestName("AM_BM2_Reports_Adjustment_report_SmokeTest", "AM_BM2_Reports_Adjustment_report_SmokeTest");
    Report.assignCategory("Reports");
    AM.Login.openAppAndSubmitCredentials();
    WebDriver driver = Browser.getDriver();
    
    AM.Menu.TopMenu.Select(driver, "Go To/Billing Manager");
    AM.Billing.BillingManager.ClickLink(driver, "Reports/Adjustments Report");
    AM.Billing.Reports.Common_ReportsFilters.btn_branch_selectall(driver).click();
    AM.Billing.Reports.Common_ReportsFilters.btn_payertype_selectall(driver).click();
    AM.Billing.Reports.Common_ReportsFilters.btn_insurance_selectall(driver).click();
    AM.Billing.Reports.AdjustmentReport_Filters.btn_Adjgroupcode_selectall(driver).click();
    AM.Billing.Reports.AdjustmentReport_Filters.btn_Adjreasoncode_selectall(driver).click();
    AM.Billing.Reports.AdjustmentReport_Filters.btn_remarkcodecode_selectall(driver).click();
    AM.Billing.Reports.Common_ReportsFilters.btn_ApplyFilters(driver).click();
		
    WebElement table = driver
				.findElement(By
						.cssSelector("table.table-condensed.table-striped.ks-grid-standard"));
    	Assert.assertFalse(table.getText().trim()
				.contains("There are currently no records to display."));
			
	    }
    	  @AfterClass(alwaysRun = true)
    	   public static void Teardown() {
    	  		 components.Browser.teardownTest();
    	  	 }

   
	    }

