package AM.Forms.PT;

import components.Waits;
import org.apache.commons.lang3.time.DateUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by austin.ledbetter on 2/16/2017.
 */
public class PTVisitsShortcut {
    public static void CompletePTVisits(WebDriver driver, Date endDate, int numberOfVisitsToComplete) throws Exception {
        for (int i = 1; i <= numberOfVisitsToComplete; i++) {
            Date currentDate = DateUtils.addDays(endDate, -1 * i);
            DateFormat format = new SimpleDateFormat("MM/dd/yyyy");
            AM.Menu.TopMenu.Select(driver, "Go To/Patient Manager");
            AM.Patient.PatientManager.SelectActivePatient(driver);
            AM.Episode.EpisodeManager.SelectTaskTab(driver, "PT");
            AM.Episode.EpisodeManager.AddScheduleTasksWithDate(driver, format.format(currentDate));
            AM.Episode.EpisodeManager.btn_InsertUpdateTasks(driver).click();

            driver.findElement(By.id("Details1")).click();
            AM.Menu.TopMenu.Select(driver, "View/PT Visit");

            Waits.ForBrowserLoad(driver);
            PTVisit.FillPTVisitRecordWithDate(driver, format.format(currentDate));
            PTVisit.chk_SignatureRequired(driver).click();
            PTVisit.FillElectronicSignatureWithDate(driver, format.format(currentDate));
            PTVisit.btn_ES_Approve(driver).click();
            Waits.ForBrowserLoad(driver);
            Waits.ForGlobalAjaxLoader(driver);
        }
    }
}
