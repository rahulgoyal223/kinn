/**
 * 
 */
package Scripts.Insurance;




import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import DataSource.Datatable;
import DataSource.GlobalData;
import components.Browser;
import components.Report;
import components.Waits;





public class AM_Insurance_Allowed_HH3952 {

	 @Test
	public static void main(String[] args) throws Exception {
			Test();
		}
	 		

@Test
public static void Test() throws Exception {
	
	  Report.generateReportsFile("html","AM_Insurance_Allowed_HH3952");
	    Report.SetTestName("AM_Insurance_Allowed_HH3952", " AM_Insurance_Allowed_HH3952 verify that allowed amount is disabled for medicare traditional/adv insurances");
	
	//@Open Application and submit credentials
    AM.Login.openAppAndSubmitCredentials();
	//@ Get Current WebDriver
    WebDriver driver = Browser.getDriver();
    
    //This test is to make sure Allowed amount is disabled on medicare Traditional Insurance Rates page.
	 
    AM.Menu.TopMenu.Select(driver, "Go To/Reports / Admin");
 	components.Verify.ExactPageTitle(driver, "Reports & Administration | Kinnser Software");
 	AM.ReportsAdmin.ReportsAdminLinks.lnk_insurance(driver).click();
 	components.Verify.ExactPageTitle(driver, "Insurance Manager| Kinnser Software");
    AM.Insurance.InsuranceManager.SelectInsurance(driver, "Palmetto GBA").click();
    AM.Menu.TopMenu.Select(driver, "Edit");
    AM.Insurance.InsuranceManager.lnk_Insuranceratesbm2(driver).click();
	components.Verify.ExactPageTitle(driver, "Insurance Rates - Palmetto GBA| Kinnser Software");
	AM.Insurance.InsuranceRates.lnk_Insurancerates_snv(driver).click();
   
    if(AM.Insurance.InsuranceRates.txt_allowed(driver).isEnabled())
    {
     System.out.print("\n Allowed is not disabled on Insurance rates.");
    }
    else
    {
     System.out.print("\n Allowed is disabled on Insurance rates.");
    }
    
    if(AM.Insurance.InsuranceRates.txt_allowed(driver).isEnabled()) {
		Report.Log(Status.PASS, "Allowed is disabled on Insurance rates page");
	}else{
		Report.Log(Status.FAIL, "Allowed is not disabled on Insurance rates page");
	} 	
    
    Assert.assertFalse(AM.Insurance.InsuranceRates.txt_allowed(driver).isEnabled());
  //@Teardown
	components.Browser.teardownTest();
	 
}
}