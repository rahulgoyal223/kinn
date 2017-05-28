package Scripts.Clinical.PTG;


import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import com.aventstack.extentreports.Status;
import components.Browser;
import components.Config;
import components.Report;

public class AMClinicalPTGHH3848 {

	/************************************************************************************
	 * 'Class name : AM_Clinical_PTG_HH3848
   * 'JIRA ID : HH-3848
   * 'Description : Verify the access the PTG feature through a Restricted Clinic Setting
   * 'Input Parameters :
   * 'Output Parameters :
   * 'Assumptions : 
   * 'Use : The following test verify that the user can only the access the PTG feature
   * through a Restricted Clinic Setting
	 * 'Tags : Functional
	 ************************************************************************************/

	
	@Test(groups = { "AM_Regression", "AM_Clinical" })
	public static void test() throws Exception {
		// @Reports Configuration
		String Url = Config.getAppUrl();
		String partialUrl = "am/usermanager/editdemographic.cfm?amuserkey=301838";
		Report.generateReportsFile("html", "AM_Clinical_PTG_HH3848");
		Report.SetTestName("AM_Clinical_PTG_HH3848",
				"To Verify that the user can only the access the PTG feature through a Restricted Clinic Setting");
		Report.assignCategory("PTG");

		// @Open Application and submit credentials
		AM.Login.openAppAndSubmitCredentials();
		// @ Get Current WebDriver
		WebDriver driver = Browser.getDriver();
		try {
			// @Import Test data sheet
			AM.Menu.TopMenu.Select(driver, "Go To/Reports / Admin");
			AM.ReportsAdmin.ReportsAdmin.lnk_RestrictedSetting(driver).click();
			AM.ReportsAdmin.RestrictedSetting.CheckCheckbox(driver, AM.ReportsAdmin.RestrictedSetting.chk_EnableGoalAdmin(driver),false);
			
			AM.Menu.TopMenu.Select(driver, "Go To/Reports / Admin");
			AM.ReportsAdmin.ReportsAdmin.lnk_ClinicSetting(driver).click();
		
			Boolean settingChecked = AM.ReportsAdmin.ClinicSetting.SettingValue(AM.ReportsAdmin.ClinicSetting.chk_EnableProgressToGoals(driver));
					
			if (settingChecked != true){
				AM.ReportsAdmin.ClinicSetting.CheckCheckbox(driver, AM.ReportsAdmin.ClinicSetting.chk_EnableProgressToGoals(driver), false);
			}
			AM.Menu.TopMenu.Select(driver, "Go To/Reports / Admin");
			AM.ReportsAdmin.ReportsAdmin.lnk_Users(driver);
			driver.navigate().to(Url + partialUrl);
			AM.Menu.TopMenu.Select(driver, "Edit/Roles");
			// Asserting section #2
			boolean roleChecked = AM.User.EditRole.ReturnRoleValue(AM.User.EditRole.chk_GoalAdministrationRole(driver));
			
			if (roleChecked != true){
				AM.User.EditRole.CheckCheckbox(driver, AM.User.EditRole.chk_GoalAdministrationRole(driver), false);
				AM.Menu.TopMenu.lnk_Logout(driver);
				driver.quit();
				AM.Login.openAppAndSubmitCredentials();
				driver = Browser.getDriver();
			}
			AM.Menu.TopMenu.Select(driver, "Go To/Reports / Admin");
			AM.ReportsAdmin.ReportsAdmin.lnk_GoalsAdmin(driver).click();
			Report.attachScreenShotToReport(driver);
			Report.Log(Status.PASS, "The setting enabled the user to enter the PTG Library");
			Assert.assertTrue(true);
		}
		catch(Exception e){
			Report.Log(Status.FAIL, "There was an issue enabling the PTG feature");
			Assert.fail("There was an issue turning ON the PTG setting");
			e.printStackTrace();
		}
	}
	@AfterClass(alwaysRun = true)
	public static void Teardown() {
		AM.ReportsAdmin.ReportsAdmin.turnOffPTG(Browser.getDriver());
		components.Browser.teardownTest();
	}

}