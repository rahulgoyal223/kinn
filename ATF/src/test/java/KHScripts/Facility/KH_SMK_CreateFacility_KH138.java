package KHScripts.Facility;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;
import com.aventstack.extentreports.Status;
import org.testng.annotations.AfterClass;
import org.testng.Assert;
import DataSource.Datatable;
import DataSource.GlobalData;
import components.Browser;
import components.Report;
import components.Waits;

public class KH_SMK_CreateFacility_KH138 {
	
	/************************************************************************************
	'Class name                     : 	KH_SMK_CreateFacility_KH138
	'JIRA ID						:	KH-138
	'Description                    : 	This test will create a Facility and verify that the created facility is present.
	'Input Parameters           	: 	Facility Name, Facility NPI
	'Output Parameters        		: 	
	'Assumptions                    : 	Test Data is present in the Global Sheet.
	'Use                            : 	The following test verifies that the created Facility is present.
	'Tags                           : 	Regression
	 ************************************************************************************/
	public static void main(String[] args) throws Exception {
		KH_SMK_CreateFacility_KH138();
	}
		
	@Test(groups = { "KH_Regression", "KH_Clinical", "SmokeTest" })
	public static void KH_SMK_CreateFacility_KH138() throws Exception {
		
		
    String dataSheetName = null;
    //@Reports Configuration
    Report.generateReportsFile("html","KH_SMK_CreateFacility_KH138");
    Report.SetTestName("KH_SMK_CreateFacility_KH138", "KH_SMK_CreateFacility_KH138_verifies that the created Facility is present");
    Report.assignCategory("SMK");
    //@Open Application and submit credentials
    KH.Login.openAppAndSubmitCredentials();
    //@ Get Current WebDriver
    WebDriver driver = Browser.getDriver();
    //@Import Test data sheet
    String dataFileName = "KHFacility\\KH_SMK_CreateFacility_KH138.xlsx";
    dataSheetName = "KH_SMK_CreateFacility_KH138";
    
    Datatable.loadDataSheet(dataFileName, dataSheetName);
    
   /*****************************************************************/
    
    //@@Step  :To create new user
    if (Datatable.GetValue("CreateFacility").equals("Yes")) {
    	Report.Log(com.aventstack.extentreports.Status.PASS, "Data Is Read");
    	//@Step :To load sheet and add new User
        Datatable.loadDataSheet(dataFileName, "CreateFacility");            
        //@Step :To select the required menu
        KH.Menu.TopMenu.Select(driver, "File/New/Facility");
        KH.Facilities.AddNewFacility.FillAddNewFacility(driver);
        KH.Facilities.AddNewFacility.chk_FI_ExternalReferral(driver).click();
        KH.Facilities.AddNewFacility.txt_FI_Comments(driver).sendKeys("New Facility Creation");
        Report.attachScreenShotToReport(driver);
        KH.Facilities.AddNewFacility.btn_FI_SaveAndCreate(driver).click();
        //@Step :To select the created Facility
        KH.Facilities.FacilityManager.SelectFacility(driver, GlobalData.getFacilityName());
        Report.attachScreenShotToReport(driver);
        //@Step :To edit the created facility
        KH.Facilities.EditFacility.lst_EF_FacilityType(driver).selectByVisibleText("Home Health Agency");
        Report.attachScreenShotToReport(driver);
        KH.Facilities.EditFacility.btn_EF_UpdateFacility(driver).click();
        //@Step :To verify that the edited field is present in the facility
        KH.Menu.TopMenu.Select(driver, "Go To/Reports / Admin");
        KH.ReportsAdmin.Administration.SelectTask(driver, "Administration/Facilities");
        Report.attachScreenShotToReport(driver);
        KH.Facilities.FacilityManager.SelectFacility(driver, GlobalData.getFacilityName());
        Report.attachScreenShotToReport(driver);
        Waits.ForBrowserLoad(driver);
        Select sel = new Select(driver.findElement(By.id("facilityTypeKey")));
        List<WebElement> list = sel.getOptions();
        boolean isMenuSelected = false;
        for(int i=0;i<list.size();i++){
            if(list.get(i).getText().equals("Home Health Agency")){
            	isMenuSelected = true;
                break;
                }
        }
        if (isMenuSelected = true) {
			Report.Log(Status.PASS, "Edited Facility Type is present successfully");
			Assert.assertTrue(isMenuSelected, "Edited Facility Type is present successfully");
		} else {
			Report.Log(Status.FAIL, "Edited Facility Type is not present successfully");
			Assert.assertFalse(false);
        }
        Report.attachScreenShotToReport(driver);
    }
    else{
	    	
     	System.out.print("\n CreateFacility Field is not set to Yes.");
     	Report.Log(com.aventstack.extentreports.Status.FAIL, "CreateFacility Field is not set to Yes");
     	Assert.fail("CreateFacility Yes is not given in the data sheet");
     }
	}
	
	@AfterClass(alwaysRun = true)
	public static void Teardown() {
	        components.Browser.teardownTest();
  }
}