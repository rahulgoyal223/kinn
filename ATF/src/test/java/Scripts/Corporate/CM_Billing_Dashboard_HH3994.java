package Scripts.Corporate;


	import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import DataSource.Datatable;
import DataSource.GlobalData;
import components.Browser;
import components.Config;
import components.Report;
import components.Waits;

	public class CM_Billing_Dashboard_HH3994 {
		
		/************************************************************************************
		'Class name                     : 	CM_Billing_Dashboard_HH3994
		'JIRA ID						:	HH-3394
		'Description                    : 	To Verify corporate Hardclose dashboard loads and closed period loads
		'Input Parameters           	: 	
		'Output Parameters        		: 	
		'Assumptions                    : 	Intrepid is a corp clinic that may meet url max char
		'Use                            : 	The following test verifies that Intrepid corp dhasboard loads without error
		'Tags                           : 	Regression
		 ************************************************************************************/
		
	    public static void main(String[] args) throws Exception {
	    	CM_Billing_Dashboard_HH3994();
		}
	    
	    @Test(groups = { "AM_Regression" })
		public static void CM_Billing_Dashboard_HH3994() throws Exception {	
			

			/******************************************************************
			 * Mandate to call below lines at every test case start up
			 ******************************************************************/	
			//@Reports Configuration
				Report.generateReportsFile("html","CM_Billing_Dashboard_HH3994");
				Report.SetTestName("CM_Billing_Dashboard_HH3994","To Verify corporate Hardclose dashboard loads and closed period loads");
				Report.assignCategory("CM");
				Report.assignCategory("Hardclose");
				
				//@Open Application and submit credentials
				String driverpath = Config.getDriversPath();
				String Btype = Config.getBrowserType();
				String Url = Config.getAppUrl();
				WebDriver driver = Browser.getDriver(Btype, Url, driverpath);
				//This is the login for Intrepid for testing a large corp HC load
				AM.Login.SubmitCredentials(driver, "271no0096", Config.getAppPassword());

				driver.get(Config.getAppUrl() + "am/hotbox.cfm");
				
			/****************************************************************/	
				CM.Swapuser.lst_CM_Swapuser(driver).selectByVisibleText("_IntrepidUSA Admin");
				Thread.sleep(Waits.getSleepLevelFive());
				driver.get(Config.getAppUrl() + "EHR/#/CM");
				Thread.sleep(Waits.getSleepLevelThree());
				AM.Menu.TopMenu.Select(driver, "Go To/Billing");

				CM.Billing.Index.lnk_HardClose_Dashboard(driver).click();

				//@Step - Verify HardClose period options load and Radio button click
				Select HCPeriod = CM.Billing.Dashboard.lst_hardClosePeriodMonth(driver);
				if(HCPeriod.getOptions().isEmpty()){
					Report.attachScreenShotToReport(driver);
					Report.Log(Status.FAIL, "Error messgae : Expected Prior Period to have options");
				} else {
					Report.attachScreenShotToReport(driver);
					Report.Log(Status.PASS, "Prior Period Hardclose options available");
				}
				
				CM.Billing.Dashboard.rbn_hardClosePeriodType(driver).click();
				
				
		}
	    //@Teardown
	    @AfterClass(alwaysRun = true)
	    public static void Teardown() {
	        components.Browser.teardownTest();
	    }
	}

