package Scripts.Clinical.PTG;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import DataSource.Datatable;
import DataSource.GlobalData;
import components.Browser;
import components.Config;
import components.Report;
import components.Verify;
import components.Waits;

public class AM_Clinical_PTG_HH4336 {

	/************************************************************************************
	'Class name                     : 	AM_Restricted_Clinic_Settings_HH4336
	'JIRA ID						:	HH-4336
	'Description                    : 	To Verify "Enable Progress to Goals" is in restricted clinic settings 
	'Input Parameters           	: 	
	'Output Parameters        		: 	
	'Assumptions                    : 	Uber.UserBill2 has access to restricted clinic settings
	'Use                            : 	The following test verifies "Enable Progress to Goals" is in restricted clinic settings and works as expected
	'Tags                           : 	Regression, Smoke Test, E2E
	************************************************************************************/
	
	public static void main(String[] args) throws Exception {
		AM_Clinical_PTG_HH4336();
	}

	@Test(groups = { "AM_Regression", "AM_Clinical" })
	public static void AM_Clinical_PTG_HH4336() throws Exception {
		//@Reports Configuration			
		Report.generateReportsFile("html","AM_Restricted_Clinic_Settings_HH4336");
		Report.SetTestName("AM_Clinical_PTG_HH4336", "To Verify \"Enable Progress to Goals\" is in restricted clinic settings");
		Report.assignCategory("PTG");
		
		//@Open Application and submit credentials
		AM.Login.openAppAndSubmitCredentials();
		
		//@ Get Current WebDriver
		WebDriver driver = Browser.getDriver();
		
		Boolean initialGoalsAdminChecked = null;
		Boolean initialPtgChecked = null;
		try {
			String expectedWarning;
			WebElement chkProgressToGoals;
			WebElement chkGoalsAdmin;
			
			// @Step Go To Reports Admin / Restricted Clinic Settings
			AM.Menu.TopMenu.Select(driver, "Go To/Reports / Admin");
			AM.ReportsAdmin.ReportsAdmin.lnk_RestrictedSetting(driver).click();
			
			// @Step Read current Goals Administration and PTG settings to return them later 
			initialGoalsAdminChecked = AM.ReportsAdmin.RestrictedSetting.chk_EnableGoalAdmin(driver).isSelected();
			initialPtgChecked = AM.ReportsAdmin.RestrictedSetting.chk_EnableProgressToGoals(driver).isSelected();
			
			// @Step Deactivate both settings, first the PTG since it's dependent from the goals admin and save
			if( initialPtgChecked ){
				AM.ReportsAdmin.RestrictedSetting.chk_EnableProgressToGoals(driver).click();
			}
			if( initialGoalsAdminChecked ){
				AM.ReportsAdmin.RestrictedSetting.chk_EnableGoalAdmin(driver).click();
			}
			if( initialPtgChecked || initialGoalsAdminChecked ){
				AM.ReportsAdmin.RestrictedSetting.btn_SaveChanges(driver).click();
			}
			

			// @Step When Goals Admin is unchecked the Enable Progress to Goals should not change its
			// status and display a warning message to the user
			Report.Log(Status.INFO, "The user clicks Enable Progress to Goals when Goals Admin is unchecked");
			chkProgressToGoals = AM.ReportsAdmin.RestrictedSetting.chk_EnableProgressToGoals(driver);
			chkProgressToGoals.click();

			Verify.VerifyBoolStatus(
					chkProgressToGoals.isSelected(), 
					false, 
					"Then Enable Progress to Goals checkbox should not change its status and stay ", 
					"checked", 
					"unchecked");
			expectedWarning = "You can not enable Progress to Goals for the clinic if Goal / Intervention Administration is disabled";
			Verify.VerifyBoolStatus(
					driver.findElements(
						By.xpath("//div[contains(text(),'" + expectedWarning + "')]")
						).size() == 1,
					true,
					"A warning message is displayed to the user: <pre>" + expectedWarning + "</pre>",  
					"", 
					"");
			
			Report.attachScreenShotToReport(driver, chkProgressToGoals);

			// @Step If the Goals Admin is checked and the Enable Progress to Goals is checked too 
			// both checks should change its status and display a warning message to the user alerting that 
			// new admissions are going to use PTG from that moment and on
			chkGoalsAdmin = AM.ReportsAdmin.RestrictedSetting.chk_EnableGoalAdmin(driver);
			Report.Log(Status.INFO, "If the user checks Goals Admin  and Enable Progress to Goals checkboxes ");
			chkGoalsAdmin.click();
			chkProgressToGoals.click();

			Verify.VerifyBoolStatus(
					chkGoalsAdmin.isSelected(), 
					true, 
					"Enable Goal / Intervention Administration checkbox should be ", 
					"checked", 
					"unchecked");
			Verify.VerifyBoolStatus(
					chkProgressToGoals.isSelected(), 
					true, 
					"Enable Progress to Goals checkbox should be ", 
					"checked", 
					"unchecked");

			expectedWarning = "Progress to Goals will be enabled for all new admissions taking place after you save these changes.";
			Verify.VerifyBoolStatus(
					driver.findElements(
						By.xpath("//div[contains(text(),'" + expectedWarning + "')]")
						).size() == 1,
					true,
					"A warning message is displayed to the user: <pre>" + expectedWarning + "</pre>",  
					"", 
					"");
			
			Report.attachScreenShotToReport(driver, chkProgressToGoals);
			
			
			//@Step Save both settings as enabled
			Report.Log(Status.INFO, "Save both settings as enabled");
			AM.ReportsAdmin.RestrictedSetting.btn_SaveChanges(driver).click();
			
			//@Step when Progress to Goals is enabled
			//it should not be possible to deactivate Goals Administration
			//and a warning message will be presented to the user
			Report.Log(Status.INFO, "Progress to Goals is enabled and the user clicks Goal / Intervention Administration checkbox to disable it");
			chkGoalsAdmin = AM.ReportsAdmin.RestrictedSetting.chk_EnableGoalAdmin(driver);
			chkProgressToGoals = AM.ReportsAdmin.RestrictedSetting.chk_EnableProgressToGoals(driver);
			chkGoalsAdmin.click();

			Verify.VerifyBoolStatus(
					chkGoalsAdmin.isSelected(), 
					true, 
					"Enable Goal / Intervention Administration checkbox should remain ", 
					"checked", 
					"unchecked");
			expectedWarning = "You can not disable Goal / Intervention Administration if Progress to Goals is enabled for the clinic";
			Verify.VerifyBoolStatus(
					driver.findElements(
						By.xpath("//div[contains(text(),'" + expectedWarning + "')]")
						).size() == 1,
					true,
					"A warning message is displayed to the user: <pre>" + expectedWarning + "</pre>", 
					"", 
					"");
			Report.attachScreenShotToReport(driver, chkGoalsAdmin);

			//@Step User clicks on Enable Progress to Goals to deactivate it
			//a message will be presented to the user alerting new admissions will not use the new ptg anymore
			chkProgressToGoals.click();
			Verify.VerifyBoolStatus(
					chkProgressToGoals.isSelected(), 
					false, 
					"Enable Progress to Goals checkbox should be ", 
					"checked", 
					"unchecked");
			expectedWarning = "Progress to Goals will be disabled for all new admissions taking place after you save these changes.";
			Verify.VerifyBoolStatus(
					driver.findElements(
						By.xpath("//div[contains(text(),'" + expectedWarning + "')]")
						).size() == 1,
					true,
					"A warning message is displayed to the user: <pre>" + expectedWarning + "</pre>", 
					"", 
					"");
			Report.attachScreenShotToReport(driver, chkProgressToGoals);
			Assert.assertTrue(true);
		}
		catch(Exception e){
			Report.Log(Status.FAIL, e.getMessage());
			Assert.fail( e.getMessage());
			e.printStackTrace();
		}
		

		// @Return PTG Setting to it's original status
		if( initialPtgChecked != null && initialGoalsAdminChecked != null ){
			AM.Menu.TopMenu.Select(driver, "Go To/Reports / Admin");
			AM.ReportsAdmin.ReportsAdmin.lnk_RestrictedSetting(driver).click();
			Boolean currentGoalsAdminChecked = AM.ReportsAdmin.RestrictedSetting.chk_EnableGoalAdmin(driver).isSelected();
			Boolean currentPtgChecked = AM.ReportsAdmin.RestrictedSetting.chk_EnableProgressToGoals(driver).isSelected();
		
			if( initialGoalsAdminChecked ){
				if( ! currentGoalsAdminChecked ) {
					AM.ReportsAdmin.RestrictedSetting.chk_EnableGoalAdmin(driver).click();
				}
				if( initialPtgChecked != currentPtgChecked ){
					AM.ReportsAdmin.RestrictedSetting.chk_EnableProgressToGoals(driver).click();
				}
				
			} else {
				if( currentPtgChecked ) {
					AM.ReportsAdmin.RestrictedSetting.chk_EnableProgressToGoals(driver).click();
				}
				if( currentGoalsAdminChecked ) {
					AM.ReportsAdmin.RestrictedSetting.chk_EnableGoalAdmin(driver).click();
				}
			}
		
			if( initialGoalsAdminChecked != currentGoalsAdminChecked 
				|| initialPtgChecked != currentPtgChecked){
				AM.ReportsAdmin.RestrictedSetting.btn_SaveChanges(driver).click();
			}
			
		}
		
	}
	
	 @AfterClass(alwaysRun = true)
	 public static void Teardown() {
		 components.Browser.teardownTest();
	 }
}
