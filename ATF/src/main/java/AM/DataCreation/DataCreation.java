package AM.DataCreation;

import org.openqa.selenium.WebDriver;

import DataSource.Datatable;
import DataSource.GlobalData;
import components.Verify;
import components.Waits;

public class DataCreation {
	WebDriver driver;
	
	public static String CreateMedicarePatient(WebDriver driver) throws Exception {
		
		String dataSheetName = null;
		String PM_PatientName =null;
		String dataFileName = "DataCreation\\DataCreation.xlsx";
		dataSheetName = "CreateMedicarePatient";
		Datatable.loadDataSheet(dataFileName, dataSheetName);
    
		if (Datatable.GetValue("CreatePatient").equals("Yes")) {           
			//@Step :To select the required menu
			Waits.ForBrowserLoad(driver);
			AM.Menu.TopMenu.Select(driver, "Go To/Patient Manager");
			AM.Menu.TopMenu.Select(driver, "File/New/Patient");
			AM.Patient.AddNewPatient.FillAddNewPatient(driver);
			AM.Patient.AddNewPatient.AddPatient(driver);
                   
    	}
		
		if (!GlobalData.getPatientFullName().isEmpty()){
	    	PM_PatientName = GlobalData.getPatientFullName();
	    }else {
	    	Datatable.GetValue("PM_PatientName");
	    } 
		
		return PM_PatientName;
	}
	
	public static String CreateMCPrimaryPatient(WebDriver driver) throws Exception {
		
		String dataSheetName = null;
		String PM_PatientName =null;
		String dataFileName = "DataCreation\\DataCreation.xlsx";
		dataSheetName = "CreateMCPrimaryPatient";
		Datatable.loadDataSheet(dataFileName, dataSheetName);
    
		if (Datatable.GetValue("CreatePatient").equals("Yes")) {      
			//@Step :To select the required menu
			Waits.ForBrowserLoad(driver);
			AM.Menu.TopMenu.Select(driver, "Go To/Patient Manager");
			AM.Menu.TopMenu.Select(driver, "File/New/Patient");
			AM.Patient.AddNewPatient.FillAddNewPatient(driver);
			AM.Patient.AddNewPatient.AddPatient(driver);
                   
    	}
		
		if (!GlobalData.getPatientFullName().isEmpty()){
	    	PM_PatientName = GlobalData.getPatientFullName();
	    }else {
	    	Datatable.GetValue("PM_PatientName");
	    } 
		
		return PM_PatientName;
	}
    
		
	public static String CompleteMedicareOASISand485(WebDriver driver) throws Exception {
		String PM_PatientName = CreateMedicarePatient(driver);
		String dataSheetName = null;
		String dataFileName = "DataCreation\\DataCreation.xlsx";
		dataSheetName = "OASIS";
		Datatable.loadDataSheet(dataFileName, dataSheetName);
		   
	    //@Step :To verify and fill the OASIS
	    if(Datatable.GetValue("OASISCheck").equals("Yes")){
	    	AM.Menu.TopMenu.Select(driver, "Go To/Patient Manager");
	    	AM.Patient.PatientManager.SelectPatient(driver, PM_PatientName);
	    	AM.Episode.EpisodeManager.SelectTaskTab(driver, "Nursing");
	    	AM.Episode.EpisodeManager.SelectScheduleTasks(driver, "OASIS-C2 Start of Care");
	    	AM.Forms.Nursing.OasisSOC.SelectForm(driver, "Demographics");
	    	AM.Forms.Nursing.OasisSOC.FillDemographicForm(driver);
	    	AM.Forms.Nursing.OasisSOC.SaveOasisPage(driver);
	    	AM.Menu.TopMenu.Select(driver, "File/OASIS Menu");
	    	AM.Forms.Nursing.OasisSOC.SelectForm(driver, "Patient History and Diagnoses");
	    	AM.Forms.Nursing.OasisSOC.FillPatientDiagnosisForm(driver);
	    	AM.Forms.Nursing.OasisSOC.SaveOasisPage(driver);
	    	AM.Menu.TopMenu.Select(driver, "File/OASIS Menu");
	    	AM.Forms.Nursing.OasisSOC.SelectForm(driver, "Therapy Need and Plan of Care");
	    	AM.Forms.Nursing.OasisSOC.FillTherapyNeedForm(driver);
	    	AM.Forms.Nursing.OasisSOC.SaveOasisPage(driver);
	    
	    	//@ Step - Schedule Task in Episode Manager
	    	AM.Menu.TopMenu.Select(driver, "Go To/Patient Manager"); 
	    	Verify.ExactPageTitle(driver, "Patient Manager | Kinnser Software");
	    	AM.Patient.PatientManager.SelectPatient(driver, PM_PatientName);
	    	AM.Episode.EpisodeManager.SelectScheduleTasks(driver, "OASIS-C2 Start of Care");        
	    	AM.Forms.Nursing.OasisSOC.SubmitOasisWithSignature(driver);        
	    
	    
	    	//@ Step - Schedule Task in Episode Manager
	    	AM.Menu.TopMenu.Select(driver, "Go To/Patient Manager");
	    	Verify.ExactPageTitle(driver, "Patient Manager | Kinnser Software");
	    	AM.Patient.PatientManager.SelectPatient(driver, PM_PatientName);
	    	AM.Episode.EpisodeManager.SelectScheduleTasks(driver, "OASIS-C2 Start of Care");
	    	AM.Forms.Nursing.OasisSOC.ApproveOasis(driver);   
	    }
	    
	    Datatable.loadDataSheet(dataFileName, "CMS485");
	    if(Datatable.GetValue("CMS485Check").equals("Yes")){
	    	//@ Step - Fill CMS 485 form from orders tab in episode manager
	    	AM.Menu.TopMenu.Select(driver, "Go To/Patient Manager");
	    	AM.Patient.PatientManager.SelectPatient(driver, PM_PatientName);
	    	AM.Episode.EpisodeManager.SelectTaskTab(driver, "Orders");        
	    	AM.Episode.EpisodeManager.ScheduleTask(driver);
	    	AM.Episode.EpisodeManager.SelectScheduleTasks(driver, "CMS 485");
	    	AM.Forms.Orders.CMS485.FillCMS485Form(driver);
	    	AM.Forms.Orders.CMS485.SubmitCMS485(driver);
		
	    	AM.Menu.TopMenu.Select(driver, "Go To/Patient Manager");
	    	AM.Patient.PatientManager.SelectPatient(driver, PM_PatientName);
	    	AM.Episode.EpisodeManager.SelectTaskTab(driver, "Orders");
	    	AM.Episode.EpisodeManager.SelectScheduleTasks(driver, "CMS 485");
	    	AM.Forms.Orders.CMS485.ApproveCMS485(driver);
	    }
	    return PM_PatientName;
	}
	 
	public static String CompleteMCOASISand485(WebDriver driver) throws Exception {
		String PM_PatientName = CreateMCPrimaryPatient(driver);
		String dataSheetName = null;
		String dataFileName = "DataCreation\\DataCreation.xlsx";
		dataSheetName = "OASIS";
		Datatable.loadDataSheet(dataFileName, dataSheetName);
		   
	    //@Step :To verify and fill the OASIS
	    if(Datatable.GetValue("OASISCheck").equals("Yes")){
	    	AM.Menu.TopMenu.Select(driver, "Go To/Patient Manager");
	    	AM.Patient.PatientManager.SelectPatient(driver, PM_PatientName);
	    	AM.Episode.EpisodeManager.SelectTaskTab(driver, "Nursing");
	    	AM.Episode.EpisodeManager.SelectScheduleTasks(driver, "OASIS-C2 Start of Care");
	    	AM.Forms.Nursing.OasisSOC.SelectForm(driver, "Demographics");
	    	AM.Forms.Nursing.OasisSOC.FillDemographicForm(driver);
	    	AM.Forms.Nursing.OasisSOC.SaveOasisPage(driver);
	    	AM.Menu.TopMenu.Select(driver, "File/OASIS Menu");
	    	AM.Forms.Nursing.OasisSOC.SelectForm(driver, "Patient History and Diagnoses");
	    	AM.Forms.Nursing.OasisSOC.FillPatientDiagnosisForm(driver);
	    	AM.Forms.Nursing.OasisSOC.SaveOasisPage(driver);
	    	AM.Menu.TopMenu.Select(driver, "File/OASIS Menu");
	    	AM.Forms.Nursing.OasisSOC.SelectForm(driver, "Therapy Need and Plan of Care");
	    	AM.Forms.Nursing.OasisSOC.FillTherapyNeedForm(driver);
	    	AM.Forms.Nursing.OasisSOC.SaveOasisPage(driver);
	    
	    	//@ Step - Schedule Task in Episode Manager
	    	AM.Menu.TopMenu.Select(driver, "Go To/Patient Manager"); 
	    	Verify.ExactPageTitle(driver, "Patient Manager | Kinnser Software");
	    	AM.Patient.PatientManager.SelectPatient(driver, PM_PatientName);
	    	AM.Episode.EpisodeManager.SelectScheduleTasks(driver, "OASIS-C2 Start of Care");        
	    	AM.Forms.Nursing.OasisSOC.SubmitOasisWithSignature(driver);        
	    
	    
	    	//@ Step - Schedule Task in Episode Manager
	    	AM.Menu.TopMenu.Select(driver, "Go To/Patient Manager");
	    	Verify.ExactPageTitle(driver, "Patient Manager | Kinnser Software");
	    	AM.Patient.PatientManager.SelectPatient(driver, PM_PatientName);
	    	AM.Episode.EpisodeManager.SelectScheduleTasks(driver, "OASIS-C2 Start of Care");
	    	AM.Forms.Nursing.OasisSOC.ApproveOasis(driver);   
	    }
	    
	    Datatable.loadDataSheet(dataFileName, "CMS485");
	    if(Datatable.GetValue("CMS485Check").equals("Yes")){
	    	//@ Step - Fill CMS 485 form from orders tab in episode manager
	    	AM.Menu.TopMenu.Select(driver, "Go To/Patient Manager");
	    	AM.Patient.PatientManager.SelectPatient(driver, PM_PatientName);
	    	AM.Episode.EpisodeManager.SelectTaskTab(driver, "Orders");        
	    	AM.Episode.EpisodeManager.ScheduleTask(driver);
	    	AM.Episode.EpisodeManager.SelectScheduleTasks(driver, "CMS 485");
	    	AM.Forms.Orders.CMS485.FillCMS485Form(driver);
	    	AM.Forms.Orders.CMS485.SubmitCMS485(driver);
		
	    	AM.Menu.TopMenu.Select(driver, "Go To/Patient Manager");
	    	AM.Patient.PatientManager.SelectPatient(driver, PM_PatientName);
	    	AM.Episode.EpisodeManager.SelectTaskTab(driver, "Orders");
	    	AM.Episode.EpisodeManager.SelectScheduleTasks(driver, "CMS 485");
	    	AM.Forms.Orders.CMS485.ApproveCMS485(driver);
	    }
	    return PM_PatientName;
	}
	
	
    public static void CreateAndMoveRAPToPendingPayment(WebDriver driver) throws Exception {
    	String PM_PatientName = CompleteMedicareOASISand485(driver);
		
		
    	AM.Menu.TopMenu.Select(driver, "Go To/Billing Manager");
        Verify.ExactPageTitle(driver, "Billing Manager | Kinnser Software");
        AM.Billing.Claims.ClaimsManager.FilterAndSearchInRAPReady(driver, PM_PatientName, "Palmetto GBA");
        
        AM.Billing.Claims.ClaimsManager.SelectClaim(driver, PM_PatientName);
        AM.Billing.Claims.ClaimsManager.CreateClaim(driver);
        //@Step - Approving the Claim and verifying the successful message------Pending Approval tab
        AM.Billing.Claims.ClaimsManager.SelectStatusTab(driver, "Pending Approval");
        AM.Billing.Claims.ClaimsManager.FilterAndSearchByPayer(driver, PM_PatientName, "Palmetto GBA");
        AM.Billing.Claims.ClaimsManager.SelectClaim(driver, PM_PatientName);
        AM.Billing.Claims.ClaimsManager.ApproveClaim(driver);	
        //@Step - generate Claim by clicking on Send manually button and verifying the successful message----Ready to Send tab
        AM.Billing.Claims.ClaimsManager.SelectStatusTab(driver, "Ready To Send");
        AM.Billing.Claims.ClaimsManager.FilterAndSearchByPayer(driver, PM_PatientName, "Palmetto GBA");
        AM.Billing.Claims.ClaimsManager.SelectClaim(driver, PM_PatientName);
        AM.Billing.Claims.ClaimsManager.SendMedicareClaim(driver);

    	AM.Billing.Claims.ClaimsManager.SelectStatusTab(driver, "Pending Payment");
    	AM.Billing.Claims.ClaimsManager.FilterAndSearchByPayer(driver, PM_PatientName, "Palmetto GBA");
    	AM.Billing.Claims.ClaimsManager.VerifyPatient(driver, PM_PatientName);
    }
	   
    
    public static void CreateAndMoveMCPrimaryClaimToPendingPayment(WebDriver driver) throws Exception {
    	String PM_PatientName = CompleteMCOASISand485(driver);
		
		
    	AM.Menu.TopMenu.Select(driver, "Go To/Billing Manager");
        Verify.ExactPageTitle(driver, "Billing Manager | Kinnser Software");
        AM.Billing.Claims.ClaimsManager.FilterAndSearchInMCReady(driver, PM_PatientName, "Managed Care Insurance");
        
        AM.Billing.Claims.ClaimsManager.SelectClaim(driver, PM_PatientName);
        AM.Billing.Claims.ClaimsManager.CreateClaim(driver);
        //@Step - Approving the Claim and verifying the successful message------Pending Approval tab
        AM.Billing.Claims.ClaimsManager.SelectStatusTab(driver, "Pending Approval");
        AM.Billing.Claims.ClaimsManager.FilterAndSearchByPayer(driver, PM_PatientName, "Managed Care Insurance");
        AM.Billing.Claims.ClaimsManager.SelectClaim(driver, PM_PatientName);
        AM.Billing.Claims.ClaimsManager.ApproveClaim(driver);	
        //@Step - generate Claim by clicking on Send manually button and verifying the successful message----Ready to Send tab
        AM.Billing.Claims.ClaimsManager.SelectStatusTab(driver, "Ready To Send");
        AM.Billing.Claims.ClaimsManager.FilterAndSearchByPayer(driver, PM_PatientName, "Managed Care Insurance");
        AM.Billing.Claims.ClaimsManager.SelectClaim(driver, PM_PatientName);
        AM.Billing.Claims.ClaimsManager.SendMCPrimaryClaim(driver);

    	AM.Billing.Claims.ClaimsManager.SelectStatusTab(driver, "Pending Payment");
    	AM.Billing.Claims.ClaimsManager.FilterAndSearchByPayer(driver, PM_PatientName, "Managed Care Insurance");
    	AM.Billing.Claims.ClaimsManager.VerifyPatient(driver, PM_PatientName);
    }

   
}
