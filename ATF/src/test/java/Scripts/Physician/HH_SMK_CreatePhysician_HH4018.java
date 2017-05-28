package Scripts.Physician;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.Assert;
import DataSource.Datatable;
import DataSource.GlobalData;
import components.Browser;
import components.Report;
import components.Waits;

public class HH_SMK_CreatePhysician_HH4018 {
	
	/************************************************************************************
	'Class name                     : 	HH_SMK_CreatePhysician_HH4018
	'JIRA ID						:	HH-4018
	'Description                    : 	This test will create & edit a Physician and atlast delete & add the details of a physician.
	'Input Parameters           	: 	Lastname, Firstname
	'Output Parameters        		: 	
	'Assumptions                    : 	Test Data is present in the Global Sheet.
	'Use                            : 	The following test verifies that the created insurance is able to edit & delete a physician.
	'Tags                           : 	Regression
	 ************************************************************************************/
	public static void main(String[] args) throws Exception {
		HH_SMK_CreatePhysician_HH4018();
	}
		
	@Test(groups = { "AM_Regression", "AM_Clinical", "SmokeTest" })
	public static void HH_SMK_CreatePhysician_HH4018() throws Exception {
		
		String dataSheetName = null;
	    String LG_Login = null;
	    Report.generateReportsFile("html","HH_SMK_CreatePhysician_HH4018");
	    Report.SetTestName("HH_SMK_CreatePhysician_HH4018", "HH_SMK_CreatePhysician_HH4018_verifies that the created insurance is able to edit & delete a physician");
	    Report.assignCategory("SMK");
	    //@Step :To login into the application
	    AM.Login.openAppAndSubmitCredentials();
	    WebDriver driver = Browser.getDriver();
	    //@Step :To load data sheet
	    String dataFileName = "Clinical\\HH_SMK_CreatePhysician_HH4018.xlsx";
	    dataSheetName = "HH_SMK_CreatePhysician_HH4018";
	    
	    Datatable.loadDataSheet(dataFileName, dataSheetName);
	    
	   /*****************************************************************/
	    
	     if (Datatable.GetValue("CreatePhysician").equals("Yes")) {
	    	//@Step :To load sheet and add new Physician
	        Datatable.loadDataSheet(dataFileName, "CreatePhysician"); 
	        //@Step :To select the required menu
	        AM.Menu.TopMenu.Select(driver, "File/New/Physician");
	        Waits.ForBrowserLoad(driver);
	        //@Step :To Add New Physician details
	        AM.Physician.AddNewPhysician.FillAddNewPhysician(driver);
	        AM.Physician.AddNewPhysician.AddPhysician(driver);
	        Waits.ForBrowserLoad(driver);
	        Report.attachScreenShotToReport(driver);
	        //@Step :To Navigate to the Physician Manager page
	        AM.Menu.TopMenu.Select(driver, "Go To/Reports / Admin");
	        AM.ReportsAdmin.Administration.SelectTask(driver, "Administration/Physicians");
	        AM.Patient.PatientManager.ToSelectAlphabetOfPatient(driver, "T").click();
	        AM.Physician.PhysicianManager.SelectPhysicianNameLink(driver, GlobalData.getPatientFirstName(), GlobalData.getPatientLastName());
	        //@Step :To Edit the details of the Physician
	        AM.Menu.TopMenu.Select(driver, "Edit/Profile");
	        AM.Physician.AddNewPhysician.txt_AP_PhysicianUPIN(driver).clear();
	        AM.Physician.AddNewPhysician.txt_AP_PhysicianUPIN(driver).sendKeys("AMG987");
	        AM.Physician.AddNewPhysician.txt_AP_PhysicianNPI(driver).clear();
	        AM.Physician.AddNewPhysician.txt_AP_PhysicianNPI(driver).sendKeys("NPI567");
	        AM.Physician.AddNewPhysician.btn_AddPhysician(driver).click();
	        AM.Physician.AddNewPhysician.clk_btnYes(driver, "Yes");
	        //@Step :To Navigate to the Physician Manager page
	        AM.Menu.TopMenu.Select(driver, "Go To/Reports / Admin");
	        AM.ReportsAdmin.Administration.SelectTask(driver, "Administration/Physicians");
	        AM.Patient.PatientManager.ToSelectAlphabetOfPatient(driver, "T").click();
	        AM.Physician.PhysicianManager.SelectPhysicianNameLink(driver, GlobalData.getPatientFirstName(), GlobalData.getPatientLastName());
	        //@Step :To verify the edited values are saved for existing Physician
	        AM.Menu.TopMenu.Select(driver, "Edit/Profile");
	        AM.Physician.PhysicianManager.VerifyEditedValue(driver, "AMG987", AM.Physician.AddNewPhysician.txt_AP_PhysicianUPIN(driver).getAttribute("value").trim());
	        AM.Physician.PhysicianManager.VerifyEditedValue(driver, "NPI567", AM.Physician.AddNewPhysician.txt_AP_PhysicianNPI(driver).getAttribute("value").trim());
	        //@Step :To delete the created physician
	        AM.Physician.AddNewPhysician.btn_DeletePhysician(driver).click();
	        driver.switchTo().alert().accept();
	        //@Step :To verify that the created physician is deleted successfully
	        AM.Physician.PhysicianManager.VerifyPhysicianDeleted(driver, GlobalData.getPatientFirstName(), GlobalData.getPatientLastName());
			Report.attachScreenShotToReport(driver);
	     }
	     
	     else{
 	    	
	     	System.out.print("\n CreatePhysician Field is not set to Yes.");
	     	Report.Log(com.aventstack.extentreports.Status.FAIL, "CreatePhysician Field is not set to Yes");
	     	Assert.fail("CreatePhysician Yes is not given in the data sheet");
	     }
	     
	     if (!GlobalData.getLoginCredentials().isEmpty()){
	      	
	      	LG_Login = GlobalData.getLoginCredentials();
	      	
	      }
	      
	      else {
	      	Datatable.GetValue("CreatePatient");
	      }  
	      
	      Datatable.loadDataSheet(dataFileName, dataSheetName);
	      
	      
	      //@Step :To create a new physician from the new patient entry screen
	      if(Datatable.GetValue("CreatePatient").equals("Yes")){
	     	Datatable.loadDataSheet(dataFileName, "CreatePatient");
	     	AM.Menu.TopMenu.Select(driver, "File/New/Patient");
	     	Waits.ForBrowserLoad(driver);
	 		AM.Patient.AddNewPatient.FillAddNewPatient(driver);
	 		Report.attachScreenShotToReport(driver);
	 		AM.Patient.AddNewPatient.lst_Ref_Physician(driver).selectByIndex(1);
	      }
	      
	      else{
		    	
	       	System.out.print("\n CreatePatient Field is not set to Yes.");
	       	Report.Log(com.aventstack.extentreports.Status.FAIL, "CreatePatient Field is not set to Yes");
	       	Assert.fail("CreatePatient Yes is not given in the data sheet");
	       	    }
	      
	      if (!GlobalData.getLoginCredentials().isEmpty()){
		      	
		      	LG_Login = GlobalData.getLoginCredentials();
		      	
		  }
		      
		  else {
		      Datatable.GetValue("CreatePhysician");
		  }  
		      
	      	Datatable.loadDataSheet(dataFileName, dataSheetName);
		      
		  if(Datatable.GetValue("CreatePhysician").equals("Yes")){
		    Datatable.loadDataSheet(dataFileName, "CreatePhysician");
		    AM.Physician.AddNewPhysician.FillAddNewPhysician(driver);
		    Report.attachScreenShotToReport(driver);
		    AM.Physician.AddNewPhysician.AddPhysician(driver);
		    Waits.ForBrowserLoad(driver);
		    Report.attachScreenShotToReport(driver);
		    //@Step :To verify that the created new physician is able to select
		    AM.Menu.TopMenu.Select(driver, "Go To/Reports / Admin");
	        AM.ReportsAdmin.Administration.SelectTask(driver, "Administration/Physicians");
	        AM.Patient.PatientManager.ToSelectAlphabetOfPatient(driver, "T").click();
	        Report.attachScreenShotToReport(driver);
	        AM.Physician.PhysicianManager.SelectPhysicianNameLink(driver, GlobalData.getPatientFirstName(), GlobalData.getPatientLastName());
	        Report.attachScreenShotToReport(driver);
		  }
			     
		  else{
		 	    	
			System.out.print("\n CreatePhysician Field is not set to Yes.");
			Report.Log(com.aventstack.extentreports.Status.FAIL, "CreatePhysician Field is not set to Yes");
			Assert.fail("CreatePhysician Yes is not given in the data sheet");
		}
	}
	
	@AfterClass(alwaysRun = true)
	public static void Teardown() {
	        components.Browser.teardownTest();
  }
}
