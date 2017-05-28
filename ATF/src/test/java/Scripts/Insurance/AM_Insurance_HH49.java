package Scripts.Insurance;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import org.openqa.selenium.support.ui.Select;







import com.aventstack.extentreports.Status;


//import DataSource.Datatable;
import components.Browser;
import components.Report;
import components.Waits;

import java.util.List;

public class AM_Insurance_HH49 {

	/************************************************************************************
	'Class name                     : 	AM_Insurance_HH49
	'JIRA ID						:	
	'Description                    : 	Verify validate that the user is unable to change FI insurance unless in calltrack. 
	'Input Parameters           	: 	Patient Name, Claim Number, MRN
	'Output Parameters        		: 	
	'Assumptions                    : 	Test Data is present in the Global Sheet.
	'Use                            : 	The following test verifies and  validate that the user is unable to inactivate FI insurance.
	'Tags                           : 	Regression
	 ************************************************************************************/
	
	public static void main(String[] args) throws Exception {
		AM_Insurance_HH49();
	}

	@Test(groups = { "AM_Regression", "AM_Clinical" })
	public static void AM_Insurance_HH49() throws Exception {
    
    Report.generateReportsFile("html","AM_Insurance_H49");
    Report.SetTestName("AM_Insurance_HH-49", "AM_HH-49_Reports/Admin_ Prevent Inactivating FI Insurance Or Creating Duplicate FI.");
    Report.assignCategory("Insurance");

	AM.Login.openAppAndSubmitCredentials();
    WebDriver driver = Browser.getDriver();
    
    /*****************************************************************/
    //@Step - : verify the Insurance Manager Inactive disabled for FI
    Waits.ForBrowserLoad(driver);
    AM.Menu.TopMenu.Select(driver, "Go To/Reports / Admin");
    AM.ReportsAdmin.Administration.SelectTask(driver, "Administration/Insurance");
    AM.Insurance.InsuranceManager.SelectSortedInsurance(driver, "P").click();
    
    if(AM.Insurance.InsuranceManager.txt_allowed(driver).isEnabled())
    {
     	System.out.print("\n FI Inactivate is not disabled on Insurance Manager.");
		Report.Log(Status.PASS, "FI Inactivate is disabled on Insurance Manager page");
    }
    else
    {
     	System.out.print("\n FI Inactivate is disabled on Insurance Manager.");
		Report.Log(Status.FAIL, "FI Inactivate is not disabled on Insurance Manager page");
		Assert.fail("FI Inactivate is not disabled on Insurance Manager page");
    }

    Report.attachScreenShotToReport(driver);
	
    //@Step - : verify the New Insurance does not have have access to Medicare Traditional if not in Calltrack.
    // TODO Finish locating the disable attribute for the payer type and validating
	AM.Menu.TopMenu.Select(driver, "File/New/Insurance");

	Select selectInsurance = AM.Insurance.AddNewInsurance.lst_I_PayerType(driver);
 	List<WebElement> options = selectInsurance.getOptions();
 	WebElement medTradOption = null;
	for(WebElement option: options)
	{
		if(option.getText().equals("Medicare Traditional"))
		{
			medTradOption = option;
		}
	}
	if(medTradOption == null)
	{
		System.out.print("\n Medicare Traditional is not in the Payer Type list at all");
		Report.Log(Status.FAIL, "Medicare Traditional is not in the Payer Type list at all");
		Assert.fail("Medicare Traditional is not in the Payer Type list at all");
	}
	else if(medTradOption.isEnabled())
    {
     	System.out.print("\n Medicare Traditional is not disabled in Payer Type list.");
		Report.Log(Status.PASS, "Medicare Traditional is disabled in Payer Type list");
		Assert.assertTrue(true);
    }
    else
    {
     	System.out.print("\n Medicare Traditional is disabled in Payer Type list.");
		Report.Log(Status.FAIL, "Medicare Traditional is not disabled in Payer Type list");
		Assert.fail("Medicare Traditional is not disabled in Payer Type list");
    }

    Report.attachScreenShotToReport(driver);

	}

	 @AfterClass(alwaysRun = true)
	 public static void Teardown() {
		 components.Browser.teardownTest();
	 }

}
