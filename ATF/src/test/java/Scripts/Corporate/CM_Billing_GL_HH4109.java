package Scripts.Corporate;

import CM.Billing.GLExport_Created;
import Database.GLExportDatabaseInteraction;
import components.*;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

/**
 * Created by austin.ledbetter on 1/26/2017.
 */
public class CM_Billing_GL_HH4109 {
    /************************************************************************************
     'Class name                     : 	CM_Billing_GL_HH4109
     'JIRA ID						:	HH-4109
     'Description                    : 	To Verify GL Export and that logging is generated
     'Input Parameters           	:
     'Output Parameters        		:
     'Assumptions                    : 	uber.user.bill2 corporation has a hard closed period for all clinics
     'Use                            : 	The following test verifies that uber user can run a GL Export and that logging is generated
     'Tags                           : 	CM Billing GL
     ************************************************************************************/

    public static void main(String[] args) throws Exception {
    	CM_Billing_GL_HH4109 ();
    }

    @Test(groups = { "AM_Regression" })
    public static void CM_Billing_GL_HH4109() throws Exception {



        /******************************************************************
         * Mandate to call below lines at every test case start up
         ******************************************************************/
        //@Reports Configuration
        Report.generateReportsFile("html","CM_Billing_GL_HH4109");
        Report.SetTestName("CM_Billing_GL_HH4109","To Verify corporate Hardclose dashboard loads and closed period loads");
        Report.assignCategory("CM");
        Report.assignCategory("Billing");
        Report.assignCategory("GL");

        int corporationKey = 249; //KOS Corporate Billing Manager 2

        //@Insert GL Codes for kos clinic
        GLExportDatabaseInteraction.populateGLCodeTasks(corporationKey);
        GLExportDatabaseInteraction.populateGLCodeGeneralLineItems(corporationKey);
        GLExportDatabaseInteraction.populateGLCodeLevelOfCare(corporationKey);

        //@Open Application and submit credentials
        String driverpath = Config.getDriversPath();
        String Btype = Config.getBrowserType();
        String Url = Config.getAppUrl();
        WebDriver driver = Browser.getDriver(Btype, Url, driverpath);
        //This is the login for Intrepid for testing a large corp HC load
        AM.Login.SubmitCredentials(driver, "uber.user.bill2", Config.getAppPassword());

        driver.get(Config.getAppUrl() + "am/hotbox.cfm");
        Waits.ForBrowserLoad(driver);
        Waits.ForGlobalAjaxLoader(driver);
        /****************************************************************/
        CM.Swapuser.lst_CM_Swapuser(driver).selectByVisibleText("KOS Corporate Billing Manager 2");
        Thread.sleep(Waits.getSleepLevelTwo());
        driver.get(Config.getAppUrl() + "EHR/#/CM");
        Waits.ForBrowserLoad(driver);
        Waits.ForGlobalAjaxLoader(driver);
        AM.Menu.TopMenu.Select(driver, "Go To/Billing");
        CM.Billing.Index.lnk_GL_Export(driver).click();
        Waits.ForBrowserLoad(driver);
        Waits.ForGlobalAjaxLoader(driver);

        int monthPosition = CM.Billing.GLExport_Creatable.getSelectedMonthPosition(driver);
        int yearPosition = CM.Billing.GLExport_Creatable.getSelectedYearPosition(driver);
        boolean redLightExists = CM.Billing.GLExport_Creatable.doesRedLightExist(driver);
        while(redLightExists){
            if(monthPosition == 1){
                monthPosition = 12;
                yearPosition--;
            }
            else{
                monthPosition--;
            }
            CM.Billing.GLExport_Creatable.lst_year(driver).selectByIndex(yearPosition - 1);
            CM.Billing.GLExport_Creatable.lst_month(driver).selectByIndex(monthPosition - 1);
            Waits.ForBrowserLoad(driver);
            Waits.ForGlobalAjaxLoader(driver);
            redLightExists = CM.Billing.GLExport_Creatable.doesRedLightExist(driver);
        }

        if(!redLightExists){
            CM.Billing.GLExport_Creatable.btn_RunExport(driver).click();
            CM.Billing.GLExport_Creatable.VerifyAlertmessage(driver, "Successfully Exported the GL File. It is available under the Exported tab.");
        }

        AM.Menu.TopMenu.Select(driver, "Go To/Billing");
        CM.Billing.Index.lnk_GL_Export(driver).click();
        Waits.ForBrowserLoad(driver);
        Waits.ForGlobalAjaxLoader(driver);
        CM.Billing.GLExport_Creatable.rbn_Exported(driver).click();
        Waits.ForBrowserLoad(driver);
        Waits.ForGlobalAjaxLoader(driver);

        String latestBatchNumber = GLExport_Created.getLatestBatchNumber(driver);
        boolean loggingExists = GLExportDatabaseInteraction.doesLoggingForGLExportExist(latestBatchNumber);

        Assert.assertTrue(loggingExists);

    }
    //@Teardown
    @AfterClass(alwaysRun = true)
    public static void Teardown() {
        components.Browser.teardownTest();
    }
}

