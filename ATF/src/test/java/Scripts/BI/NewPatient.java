package Scripts.BI;




import DataSource.Datatable;
import components.Browser;
import components.Report;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;


public class NewPatient {
	
	/************************************************************************************
	'Class name                     : 	AM_BI.AM.Patient
	'JIRA ID						:	
	'Description                    : 	Test adds new patients
	'Input Parameters           	: 	Patient Name, MRN
	'Output Parameters        		: 	MRN
	'Assumptions                    : 	Test Data is present in the Global Sheet.
	'Use                            : 	Insert new patients for ETL. Patients added will be utilized for updates
	'Tags                           : 	BI
	 ************************************************************************************/
	
	public static void main(String[] args) throws Exception {
		NewPatient();
	}

	
	@Test
    public static void NewPatient() throws Exception {
        //public void test() throws Exception {

    	String dataSheetName = null;    	

        /******************************************************************
         * Mandate to call below lines at every test case start up
         ******************************************************************/
    	//@Reports Configuration
        Report.generateReportsFile("html","AM_BI_AddNewPatient");
        Report.SetTestName("AM_BI_AddNewPatient", "Add new patient");
        Report.assignCategory("BI");
        Report.assignCategory("Patient");
        //@Open Application and submit credentials
        AM.Login.openAppAndSubmitCredentials();
        //@ Get Current WebDriver
        WebDriver driver = Browser.getDriver();
        //@Import Test data sheet
        String dataFileName = "BI\\AM_BI_AddNewPatient.xlsx";
        dataSheetName = "AM_BI_AddNewPatient";
        Datatable.loadDataSheet(dataFileName, dataSheetName);
        /*****************************************************************/
      //@Step - Create New Patient, if Required
        for(int i=10; i>0; i--){
            
        	//@ Load Data and Add New Patient
            Datatable.loadDataSheet(dataFileName, "CreatePatient" + i);            
            AM.Menu.TopMenu.Select(driver, "File/New/Patient");
            AM.Patient.AddNewPatient.FillAddNewPatient(driver);
            AM.Patient.AddNewPatient.btn_AddPatient(driver).click();
            AM.Patient.AddNewPatient.clk_btnYes(driver, "Yes");
        }}
        @AfterClass(alwaysRun = true)
  	  public static void Teardown() {
  	  components.Browser.teardownTest();
	}
}