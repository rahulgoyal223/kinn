package Scripts.Clinical;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import DataSource.Datatable;
import components.Browser;
import components.Report;
import components.Waits;

public class AM_EditEpisode_HH4353 {

	/************************************************************************************
	'Class name              : 	AM_EditEpisode_HH4353
	'JIRA ID				 :	HH-4353
	'Description             :  To verify that secondary physician name is displayed correctly after saved
	'Input Parameters        : 	Patient Name
	'Output Parameters       : 	Task status
	'Assumptions             : 	Test Data is present in the Global Sheet.
	'Use                     : 	The following test verifies that secondary physician name is displayed correctly after saved
	'Tags                    : 	Regression, Clinical
	 ************************************************************************************/

   	public static void main(String[] args) throws Exception {
   		AM_EditEpisode_HH4353();
	 }
	
   	 @Test(groups = { "AM_Regression", "AM_Clinical" })
	 public static void AM_EditEpisode_HH4353() throws Exception {

    	String dataSheetName    = null;  
    	String PM_PatientName   = null;  
       /******************************************************************
       * Mandate to call below lines at every test case start up
       * 
       * 
       ******************************************************************/
       //@Reports Configuration       
       Report.generateReportsFile("html","AM_EditEpisode_HH4353");
       Report.SetTestName("AM_EditEpisode_HH4353", "To verify secondary physician name is displayed correctly ");       
       Report.assignCategory("Clinical");
       
       //@Open Application and submit credentials
       AM.Login.openAppAndSubmitCredentials();
      
       //@ Get Current WebDriver
        WebDriver driver = Browser.getDriver();
       
       //@Import Test data sheet
       String dataFileName = "Clinical\\AM_EditEpisode_HH4353.xlsx";
       dataSheetName = "CreatePatient";
       Datatable.loadDataSheet(dataFileName, dataSheetName);
       //********************************************************************** 
             
      //@@Step :To create new patient
		if (Datatable.GetValue("CreatePatient").equals("Yes")) {
			
			//@Step :To load sheet
			Datatable.loadDataSheet(dataFileName, "CreatePatient");
			AM.Menu.TopMenu.Select(driver, "File/New/Patient");
			AM.Patient.AddNewPatient.FillAddNewPatient(driver);
			AM.Patient.AddNewPatient.AddPatient(driver);
			PM_PatientName = Datatable.GetValue("PM_PatientName");
		} else {
				//@ Step -  :To navigate to patient manager
				AM.Menu.TopMenu.Select(driver, "Go To/Patient Manager");      
				AM.Patient.PatientManager.SelectActivePatient(driver);
			}		
	   System.out.println("Patient Name is : "+ PM_PatientName);
		
	   // To enter in edit episode
	   AM.Menu.TopMenu.Select(driver, "Edit/Episode");
	   
	   //To select Secondary Physician
	   driver.findElement(By.id("PTextS")).click();
	   
	   WebElement elemntSphysician = driver.findElement(By.xpath("//*[@id='PFormS']/select/option[3]"));
	   String sphysician = elemntSphysician.getText().trim();
	   elemntSphysician.click();
	   
	   AM.Episode.EditEpisode.btn_EE_Update(driver).click();
	   AM.Episode.EditEpisode.clk_btnYes(driver, "Yes");	   
	   Waits.ForElementVisibility(driver, driver.findElement(By.id("PTextS")));
	   
	   //To check in the Secondary physician was saved correctly
	   String savedPhysician = driver.findElement(By.id("PTextS")).getText().trim();
	   if(savedPhysician.equals(sphysician))
 		{
		   Report.Log( 
 	  			Status.PASS, "Secondary physician was saved correctly"
 					   );
 			}else {
 					Report.Log( 	      			
 	     			Status.FAIL,"Secondary physician saved was " + savedPhysician +" when it should have been " + sphysician
 	  				);
 					Assert.fail("Secondary physician saved was " + savedPhysician +" when it should have been " + sphysician);
 	        	  }	 	    
 		Report.attachScreenShotToReport(driver);  
 		
	}
   	 //@Teardown
   	@AfterClass(alwaysRun = true)
	 public static void Teardown() {
		components.Browser.teardownTest();
  	}
}
