package KHScripts.User;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import org.testng.annotations.AfterClass;
import org.testng.Assert;
import DataSource.Datatable;
import DataSource.GlobalData;
import components.Browser;
import components.Report;
import components.Waits;

public class KH_SMK_CreateUser_KH136 {
	
	/************************************************************************************
	'Class name                     : 	KH_SMK_CreateUser_KH136
	'JIRA ID						:	KH-136
	'Description                    : 	This test will create a user and verify whether the user is able to login.
	'Input Parameters           	: 	Last Name, First Name
	'Output Parameters        		: 	
	'Assumptions                    : 	Test Data is present in the Global Sheet.
	'Use                            : 	The following test verifies that the created user is able to login into the application successfully.
	'Tags                           : 	Regression
	 ************************************************************************************/
	public static void main(String[] args) throws Exception {
		KH_SMK_CreateUser_KH136();
	}
		
	@Test(groups = { "KH_Regression", "KH_Clinical", "SmokeTest" })
	public static void KH_SMK_CreateUser_KH136() throws Exception {
		
		
    String dataSheetName = null;
    String LG_Login = null;
    //@Reports Configuration
    Report.generateReportsFile("html","KH_SMK_CreateUser_KH136");
    Report.SetTestName("KH_SMK_CreateUser_KH136", "KH_SMK_CreateUser_KH136_verifies that the created user is able to login into the application successfully");
    Report.assignCategory("SMK");
    //@Open Application and submit credentials
    KH.Login.openAppAndSubmitCredentials();
    //@ Get Current WebDriver
    WebDriver driver = Browser.getDriver();
    //@Import Test data sheet
    String dataFileName = "UserCreation\\KH_SMK\\KH_SMK_CreateUser_KH136.xlsx";
    dataSheetName = "KH_SMK_CreateUser_KH136";
    
    Datatable.loadDataSheet(dataFileName, dataSheetName);
    
   /*****************************************************************/
    
    //@@Step  :To create new user
    if (Datatable.GetValue("CreateUser").equals("Yes")) {
    	
    	Report.Log(com.aventstack.extentreports.Status.PASS, "Data Is Read");
    	//@Step :To load sheet and add new User
        Datatable.loadDataSheet(dataFileName, "CreateUser");            
        //@Step :To select the required menu
        KH.Menu.TopMenu.Select(driver, "File/New/User");
        KH.User.AddNewUser.FillAddNewUser(driver);
        KH.User.AddNewUser.txt_NU_AgencyEmployeeID(driver).sendKeys("3234");
        KH.User.AddNewUser.verify_Valid_Username(driver);
        KH.User.AddNewUser.btn_NU_CreateUser(driver).click();
        Report.attachScreenShotToReport(driver);
        KH.User.AddNewUser.btn_NU_CompleteUserSetup(driver).click();
        Report.attachScreenShotToReport(driver);
        KH.User.AddNewUser.chk_NUAccess_Manager(driver).click();
        KH.User.AddNewUser.chk_NUBereavement_Cotr(driver).click();
        KH.User.AddNewUser.btn_NU_UpdateRoles(driver).click();
        Waits.ForBrowserLoad(driver);
        KH.User.AddNewUser.btn_NU_Logout(driver).click();
        Waits.ForPageWait(driver);
        KH.Login.SubmitCredentials(driver, GlobalData.getUserName(), GlobalData.getTemporaryPwd());
        KH.User.AddNewUser.verify_Login_Page(driver);
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
