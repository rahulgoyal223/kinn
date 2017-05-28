package AM.Forms.Nursing;

import components.Verify;
import org.openqa.selenium.WebDriver;

/**
 * Created by austin.ledbetter on 2/15/2017.
 */
public class OasisShortcut {
    public static void FillOutOasisStartOfCare(WebDriver driver) throws Exception {
        AM.Menu.TopMenu.Select(driver, "Go To/Patient Manager");
        AM.Patient.PatientManager.SelectActivePatient(driver);
        AM.Episode.EpisodeManager.SelectTaskTab(driver, "Nursing");
        AM.Episode.EpisodeManager.SelectScheduleTasks(driver, "OASIS-C2 Start of Care");
        AM.Forms.Nursing.OasisSOC.SelectForm(driver, "Demographics");
        AM.Forms.Nursing.OasisSOC.FillDemographicForm(driver);
        AM.Forms.Nursing.OasisSOC.btn_DG_Save(driver).click();
        AM.Forms.Nursing.OasisSOC.saveAlertWindow(driver);
        AM.Menu.TopMenu.Select(driver, "File/OASIS Menu");
        AM.Forms.Nursing.OasisSOC.SelectForm(driver, "Patient History and Diagnoses");
        AM.Forms.Nursing.OasisSOC.FillPatientDiagnosisForm(driver);
        AM.Forms.Nursing.OasisSOC.btn_DG_Save(driver).click();
        ;
        AM.Forms.Nursing.OasisSOC.saveAlertWindow(driver);
        AM.Menu.TopMenu.Select(driver, "File/OASIS Menu");
        AM.Forms.Nursing.OasisSOC.SelectForm(driver, "Therapy Need and Plan of Care");
        AM.Forms.Nursing.OasisSOC.FillTherapyNeedForm(driver);
        AM.Forms.Nursing.OasisSOC.btn_DG_Save(driver).click();
        AM.Forms.Nursing.OasisSOC.saveAlertWindow(driver);

        //@ Step - Schedule Task in Episode Manager
        AM.Menu.TopMenu.Select(driver, "Go To/Patient Manager");
        AM.Patient.PatientManager.SelectActivePatient(driver);
        AM.Episode.EpisodeManager.SelectScheduleTasks(driver, "OASIS-C2 Start of Care");
        AM.Forms.Nursing.OasisSOC.SubmitOasisWithSignature(driver);


        //@ Step - Approve Scheduled Task
        AM.Menu.TopMenu.Select(driver, "Go To/Patient Manager");
        Verify.ExactPageTitle(driver, "Patient Manager | Kinnser Software");
        AM.Patient.PatientManager.SelectActivePatient(driver);
        AM.Episode.EpisodeManager.SelectScheduleTasks(driver, "OASIS-C2 Start of Care");
        AM.Forms.Nursing.OasisSOC.btn_Approve(driver).click();
    }
}
