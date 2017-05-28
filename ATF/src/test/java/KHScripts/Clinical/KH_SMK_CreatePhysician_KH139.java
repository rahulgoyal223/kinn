package KHScripts.Clinical;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import org.testng.Assert;
import DataSource.Datatable;
import DataSource.GlobalData;
import components.Browser;
import components.Report;
import components.Waits;

public class KH_SMK_CreatePhysician_KH139 {
	
	/************************************************************************************
	'Class name                     : 	KH_SMK_CreatePhysician_KH139
	'JIRA ID						:	KH-139
	'Description                    : 	This test will create & edit a Physician and atlast delete & add the details of a physician.
	'Input Parameters           	: 	Lastname, Firstname
	'Output Parameters        		: 	
	'Assumptions                    : 	Test Data is present in the Global Sheet.
	'Use                            : 	The following test verifies that the created insurance is able to edit & delete a physician.
	'Tags                           : 	Regression
	 ************************************************************************************/
	public static void main(String[] args) throws Exception {
		KH_SMK_CreatePhysician_KH139();
	}
		
	@Test(groups = { "KH_Regression", "KH_Clinical", "SmokeTest" })
	public static void KH_SMK_CreatePhysician_KH139() throws Exception {
		
		String dataSheetName = null;
	    String LG_Login = null;
	    Report.generateReportsFile("html","KH_SMK_CreatePhysician_KH139");
	    Report.SetTestName("KH_SMK_CreatePhysician_KH139", "KH_SMK_CreatePhysician_KH139_verifies that the created insurance is able to edit & delete a physician");
	    Report.assignCategory("SMK");
	    //@Step :To login into the application
	    KH.Login.openAppAndSubmitCredentials();
	    WebDriver driver = Browser.getDriver();
	    //@Step :To load data sheet
	    String dataFileName = "KHClinical\\Physician\\KH_SMK_CreatePhysician_KH139.xlsx";
	    dataSheetName = "KH_SMK_CreatePhysician_KH139";
	    
	    Datatable.loadDataSheet(dataFileName, dataSheetName);
	    
	   /*****************************************************************/
	    
	     if (Datatable.GetValue("CreatePhysician").equals("Yes")) {
	    	//@Step :To load sheet and add new Physician
	        Datatable.loadDataSheet(dataFileName, "CreatePhysician"); 
	        //@Step :To select the required menu
	        KH.Menu.TopMenu.Select(driver, "File/New/Physician");
	        Waits.ForBrowserLoad(driver);
	        //@Step :To Add New Physician details
	        KH.Physician.AddNewPhysician.FillAddNewPhysician(driver);
	        KH.Physician.AddNewPhysician.btn_AP_AddPhysician(driver).click();
	        KH.Physician.AddNewPhysician.clk_btnYes(driver, "Yes");
	        Waits.ForBrowserLoad(driver);
	        Report.attachScreenShotToReport(driver);
	        //@Step :To Navigate to the Physician Manager page
	        KH.Menu.TopMenu.Select(driver, "Go To/Reports / Admin");
	        KH.ReportsAdmin.Administration.SelectTask(driver, "Administration/Physicians");
	        KH.Patient.PatientManager.ToSelectAlphabetOfPatient(driver, "T").click();
	        KH.Physician.PhysicianManager.SelectPhysicianEditLink(driver);
	        //@Step :To Edit the details of the Physician
	        KH.Physician.AddNewPhysician.txt_AP_PhysicianUPIN(driver).clear();
	        KH.Physician.AddNewPhysician.txt_AP_PhysicianUPIN(driver).sendKeys("AMG987");
	        KH.Physician.AddNewPhysician.txt_AP_PhysicianNPI(driver).clear();
	        KH.Physician.AddNewPhysician.txt_AP_PhysicianNPI(driver).sendKeys("NPI567");
	        KH.Physician.AddNewPhysician.btn_AP_AddPhysician(driver).click();
	        KH.Physician.AddNewPhysician.clk_btnYes(driver, "Yes");
	        //@Step :To Navigate to the Physician Manager page
	        KH.Menu.TopMenu.Select(driver, "Go To/Reports / Admin");
	        KH.ReportsAdmin.Administration.SelectTask(driver, "Administration/Physicians");
	        KH.Patient.PatientManager.ToSelectAlphabetOfPatient(driver, "T").click();
	        KH.Physician.PhysicianManager.SelectPhysicianEditLink(driver);
	        //@Step :To verify the edited values are saved for existing Physician
	        KH.Physician.PhysicianManager.VerifyEditedValue(driver, "AMG987", AM.Physician.AddNewPhysician.txt_AP_PhysicianUPIN(driver).getAttribute("value").trim());
	        KH.Physician.PhysicianManager.VerifyEditedValue(driver, "NPI567", AM.Physician.AddNewPhysician.txt_AP_PhysicianNPI(driver).getAttribute("value").trim());
	        
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
	     	KH.Menu.TopMenu.Select(driver, "File/New/Patient");
	     	Waits.ForBrowserLoad(driver);
	 		KH.Patient.AddNewPatient.FillAddNewPatient(driver);
	 		Report.attachScreenShotToReport(driver);
	 		//@Step :To verify created physician is visible in patient creation screen
	 		String FullName = GlobalData.getPhysicianLastName().concat(",").concat(" ").concat(GlobalData.getPhysicianFirstName());
		    KH.Patient.AddNewPatient.lst_RI_AttendingPhysician(driver).selectByVisibleText(FullName);
		    Report.attachScreenShotToReport(driver);
		    Report.Log(Status.PASS, "Created Physician is visible");       		
		    Assert.assertTrue(true);
		    KH.Patient.AddNewPatient.btn_P_SaveAndCreate(driver).click();
		    Waits.ForGlobalAjaxLoader(driver);
		    //@Step :To delete the created physician
		    KH.Menu.TopMenu.Select(driver, "Go To/Reports / Admin");
	        KH.ReportsAdmin.Administration.SelectTask(driver, "Administration/Physicians");
	        KH.Patient.PatientManager.ToSelectAlphabetOfPatient(driver, "T").click();
	        Report.attachScreenShotToReport(driver);
	        KH.Physician.PhysicianManager.SelectPhysicianEditLink(driver);	        
	        KH.Physician.AddNewPhysician.btn_DeletePhysician(driver).click();
	        driver.switchTo().alert().accept();
	        //@Step :To verify that the created physician is deleted successfully
	        KH.Menu.TopMenu.Select(driver, "Go To/Reports / Admin");
	        KH.ReportsAdmin.Administration.SelectTask(driver, "Administration/Physicians");
	        KH.Patient.PatientManager.ToSelectAlphabetOfPatient(driver, "T").click();
	        KH.Physician.PhysicianManager.VerifyPhysicianDeleted(driver, GlobalData.getPhysicianFirstName(), GlobalData.getPhysicianLastName());
			Report.attachScreenShotToReport(driver);
	      }
	      
	      else{
		    	
	       	System.out.print("\n CreatePatient Field is not set to Yes.");
	       	Report.Log(com.aventstack.extentreports.Status.FAIL, "CreatePatient Field is not set to Yes");
	       	Assert.fail("CreatePatient Yes is not given in the data sheet");
	       	    }
	}
	
	@AfterClass(alwaysRun = true)
	public static void Teardown() {
	        components.Browser.teardownTest();
  }

}
