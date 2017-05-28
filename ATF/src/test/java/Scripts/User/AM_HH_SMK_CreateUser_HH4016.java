package Scripts.User;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.Assert;
import DataSource.Datatable;
import DataSource.GlobalData;
import components.Browser;
import components.Report;
import components.Waits;
public class AM_HH_SMK_CreateUser_HH4016 {

	/************************************************************************************
	'Class name                     : 	AM_HH_SMK_CreateUser_HH4016
	'JIRA ID						:	HH-4016
	'Description                    : 	This test will create a user and verify whether the user is able to login.
	'Input Parameters           	: 	Last Name, First Name
	'Output Parameters        		: 	
	'Assumptions                    : 	Test Data is present in the Global Sheet.
	'Use                            : 	The following test verifies that the created user is able to login into the application successfully.
	'Tags                           : 	Regression
	 ************************************************************************************/
	public static void main(String[] args) throws Exception {
		AM_HH_SMK_CreateUser_HH4016();
	}
		
	@Test(groups = { "AM_Regression", "AM_Clinical", "SmokeTest" })
	public static void AM_HH_SMK_CreateUser_HH4016() throws Exception {
		
		
    String dataSheetName = null;
    String LG_Login = null;
    //@Reports Configuration
    Report.generateReportsFile("html","AM_HH_SMK_CreateUser_HH4016");
    Report.SetTestName("AM_HH_SMK_CreateUser_HH4016", "AM_HH_SMK_CreateUser_HH4016_verifies that the created user is able to login into the application successfully");
    Report.assignCategory("SMK");
    //@Open Application and submit credentials
    AM.Login.openAppAndSubmitCredentials();
    //@ Get Current WebDriver
    WebDriver driver = Browser.getDriver();
    //@Import Test data sheet
    String dataFileName = "UserCreation\\AM_HH\\AM_HH_SMK_HH4016.xlsx";
    dataSheetName = "AM_HH_SMK_HH4016";
    
    Datatable.loadDataSheet(dataFileName, dataSheetName);
    
   /*****************************************************************/
    
    //@@Step  :To create new user
    if (Datatable.GetValue("CreateUser").equals("Yes")) {

    	Report.Log(com.aventstack.extentreports.Status.PASS, "Data Is Read");
    	//@Step :To load sheet and add new User
        Datatable.loadDataSheet(dataFileName, "CreateUser");            
        //@Step :To select the required menu
        AM.Menu.TopMenu.Select(driver, "File/New/User");
        AM.User.AddNewUser.FillAddNewUser(driver);
        AM.User.AddNewUser.txt_U_AgencyEmployeeID(driver).sendKeys("3234");
        AM.User.AddNewUser.verify_Valid_Username(driver);
        AM.User.AddNewUser.btn_U_CreateUser(driver).click();
        Report.attachScreenShotToReport(driver);
        AM.User.AddNewUser.btn_U_CompleteUserSetup(driver).click();
        Report.attachScreenShotToReport(driver);
        AM.User.AddNewUser.chk_Access_Manager(driver).click();
        AM.User.AddNewUser.chk_Approve_Auth(driver).click();
        AM.User.AddNewUser.btn_U_UpdateRoles(driver).click();
        Waits.ForBrowserLoad(driver);
        AM.User.AddNewUser.btn_U_Logout(driver).click();
        Waits.ForPageWait(driver);
        AM.Login.SubmitCredentials(driver, GlobalData.getUserName(), GlobalData.getTemporaryPwd());
        AM.User.AddNewUser.verify_Login_Page(driver);
    	Report.attachScreenShotToReport(driver);
    }
    
    else{
    	
     	System.out.print("\n CreateUser Field is not set to Yes.");
		Report.Log(com.aventstack.extentreports.Status.FAIL, "CreateUser Field is not set to Yes");
		Assert.fail("Createuser Yes is not given in the data sheet");
    }
	}
        
	@AfterClass(alwaysRun = true)
   	public static void Teardown() {
	        components.Browser.teardownTest();
     }
}