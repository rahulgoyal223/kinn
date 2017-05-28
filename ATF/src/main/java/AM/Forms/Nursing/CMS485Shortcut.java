package AM.Forms.Nursing;

import DataSource.Datatable;
import components.Verify;
import org.openqa.selenium.WebDriver;

/**
 * Created by austin.ledbetter on 2/15/2017.
 */
public class CMS485Shortcut {
    public static void FillOutCMS485(WebDriver driver) throws Exception {
        AM.Menu.TopMenu.Select(driver, "Go To/Patient Manager");
        AM.Patient.PatientManager.SelectActivePatient(driver);
        AM.Episode.EpisodeManager.SelectTaskTab(driver, "Orders");
        AM.Episode.EpisodeManager.ScheduleTask(driver);
        AM.Episode.EpisodeManager.SelectScheduleTasks(driver, "CMS 485");
        AM.Forms.Orders.CMS485.FillCMS485Form(driver);
        AM.Forms.Orders.CMS485.btn_Submit(driver).click();

        //@Step - Approve CMS 485 form
        AM.Menu.TopMenu.Select(driver, "Go To/Patient Manager");
        AM.Patient.PatientManager.SelectActivePatient(driver);
        Verify.ExactPageTitle(driver, "Episode Manager | Kinnser Software");
        AM.Episode.EpisodeManager.SelectTaskTab(driver, "Orders");
        AM.Episode.EpisodeManager.SelectScheduleTasks(driver, "CMS 485");
        AM.Forms.Orders.CMS485.btn_Approve(driver).click();
        /*comment out for AM_BM2_Medicare_RAP_HH47, leaving in case this breaks another case and we need to look back at it
        AM.Forms.Orders.CMS485.chk_ReturnToClinician(driver).click();
        AM.Forms.Orders.CMS485.txt_ElectronicSignature(driver).sendKeys(Datatable.GetValue("clinicianSignature"));
        AM.Forms.Orders.CMS485.btn_Approve(driver).click();
        */
    }
}
