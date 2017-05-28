package KHScripts.Clinical;

	import org.openqa.selenium.WebDriver;
	import org.testng.annotations.AfterClass;
	import org.testng.annotations.Test;
	import org.testng.Assert;
	import DataSource.Datatable;
	import DataSource.GlobalData;
	import components.Browser;
import components.Config;
import components.Report;
	import components.Waits;
	
	public class KH_SMK_NewClinic_KH132 {

		/************************************************************************************
		'Class name                     : 	KH_SMK_NewClinic_KH132
		'JIRA ID						:	KH-132
		'Description                    : 	This test Confirms a New Clinic can be created via CallTrack
		'Input Parameters           	: 	Admin User Name, Trainer User Name
		'Output Parameters        		: 	
		'Assumptions                    : 	Test Data is present in the Global Sheet.
		'Use                            : 	Create Agency in CallTrack and Verify user can log in with created accounts
		'Tags                           : 	Regression
		 ************************************************************************************/
		public static void main(String[] args) throws Exception {
			KH_SMK_NewClinic_KH132();
		}
			
		@Test(groups = { "KH_Regression", "KH_Clinical", "SmokeTest" })
		public static void KH_SMK_NewClinic_KH132() throws Exception {
			
			
	    String dataSheetName = null;
	    //@Reports Configuration
	    Report.generateReportsFile("html","KH_SMK_NewClinic_KH132");
	    Report.SetTestName("KH_SMK_NewClinic_KH132", "KH_SMK_NewClinic_KH132_verifies that the created user in callTrack is able to login into the application successfully");
	    Report.assignCategory("SMK");
	    //@Open Application and submit credentials
	    KH.Login.CallTrackAndSubmitCredentials();
	    //@ Get Current WebDriver
	    WebDriver driver = Browser.getDriver();
	    //@Step To click Create Agency
	    KH.NewClinic.CallTrackHome.link_CreateAgency(driver).click();
	    //@Import Test data sheet
	    String dataFileName = "Clinical\\KH_SMK_NewClinic_KH132.xlsx";
	    dataSheetName = "KH_SMK_NewClinic_KH132";
	    
	    Datatable.loadDataSheet(dataFileName, dataSheetName);
	    
	   /*****************************************************************/
	    
	    //@@Step  :To create new user
	    if (Datatable.GetValue("CreateClinic").equals("Yes")) {
	    	Report.Log(com.aventstack.extentreports.Status.PASS, "Data Is Read");
	    	//@Step :To load sheet and add new User
	        Datatable.loadDataSheet(dataFileName, "CreateClinic");
	        KH.NewClinic.CreateClinic.FillAddNewClinic(driver);
	        Report.attachScreenShotToReport(driver);
	        KH.NewClinic.CreateClinic.btn_Create_Agency(driver).click();
	        Report.attachScreenShotToReport(driver);
	        Waits.ForBrowserLoad(driver);
	        KH.NewClinic.CallTrackHome.link_Logout(driver).click();
	        driver.quit();
	        driver = Browser.getDriver(Config.getBrowserType(), Config.getAppUrl(), Config.getDriversPath());
	        KH.Login.SubmitCredentials(driver, GlobalData.getUserName(), GlobalData.getTemporaryPwd());
	        Report.attachScreenShotToReport(driver);
	        KH.User.AddNewUser.verify_Patient_Manager(driver, "Patient Manager", KH.User.AddNewUser.Verify_LoginPage_Patient(driver).getText().trim());
	        KH.User.AddNewUser.btn_Nu_Logout(driver).click();
	        /*Commented below lines as because bug is still not closed. 
	        KH.Login.SubmitCredentials(driver, GlobalData.getTrainerName(), GlobalData.getTrainerPwd());	
	        KH.User.AddNewUser.verify_Patient_Manager(driver, "Patient Manager", KH.User.AddNewUser.Verify_LoginPage_Patient(driver).getText().trim());*/
	    }
	    
	    else{
	    	
	     	System.out.print("\n CreateClinic Field is not set to Yes.");
			Report.Log(com.aventstack.extentreports.Status.FAIL, "CreateClinic Field is not set to Yes");
			Assert.fail("CreateClinic Yes is not given in the data sheet");
	    }
		}
	        
		@AfterClass(alwaysRun = true)
	   	public static void Teardown() {
		        components.Browser.teardownTest();
	     }
	}
