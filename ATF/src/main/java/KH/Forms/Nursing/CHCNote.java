package KH.Forms.Nursing;

import com.aventstack.extentreports.Status;

import DataSource.Datatable;
import components.Events;
import components.Report;
import components.Waits;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class CHCNote {

	private static WebElement element = null;
	private static String strInputValue;
	private static Select list = null;

	// @Method To select the Form
	public static WebElement SelectForm(WebDriver driver, String formname)
			throws Exception {

		try {
			List<WebElement> allrows = driver.findElements(By.tagName("td"));
			for (WebElement row : allrows) {
				String linkTxt = row.getText();
				if (linkTxt.trim().contains(formname)) {
					row.click();
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return element;
	}

	// @Method To fill the Time In, Time Out and Visit Date
	public static void FillCHCForm(WebDriver driver) throws Exception {

		strInputValue = Datatable.GetValue("TimeIn");
		if (!strInputValue.trim().isEmpty()) {
			txt_TimeIn(driver).clear();
			txt_TimeIn(driver).sendKeys(strInputValue);
		}
		
		strInputValue = Datatable.GetValue("TimeOut");
		if (!strInputValue.trim().isEmpty()) {
			txt_TimeOut(driver).clear();
			txt_TimeOut(driver).sendKeys(strInputValue);
		}

		strInputValue = Datatable.GetValue("VisitDate");
		if (!strInputValue.trim().isEmpty()) {
			dt_VisitDate(driver).clear();
			dt_VisitDate(driver).sendKeys(strInputValue);
		}
		
	}
	
	// @Method to fill in the CHC Sections
	public static void FillCHCSection(WebDriver driver, int index) throws Exception {
		strInputValue = Datatable.GetValue("Discipline");
		if (!strInputValue.trim().isEmpty()) {
			lst_CHC_ClinicianDiscipline(driver, index).selectByVisibleText(strInputValue);
		}
		
		strInputValue = Datatable.GetValue("StartTime");
		if (!strInputValue.trim().isEmpty()) {
			txt_CHC_StartTime(driver, index).clear();
			txt_CHC_StartTime(driver, index).sendKeys(strInputValue);
		}
		
		strInputValue = Datatable.GetValue("EndTime");
		if (!strInputValue.trim().isEmpty()) {
			txt_CHC_EndTime(driver, index).clear();
			txt_CHC_EndTime(driver, index).sendKeys(strInputValue);
		}
		
		strInputValue = Datatable.GetValue("StartDate");
		if (!strInputValue.trim().isEmpty()) {
			dt_CHC_StartDate(driver, index).clear();
			dt_CHC_StartDate(driver, index).sendKeys(strInputValue);
		}
		
		strInputValue = Datatable.GetValue("EndDate");
		if (!strInputValue.trim().isEmpty()) {
			dt_CHC_EndDate(driver, index).clear();
			dt_CHC_EndDate(driver, index).sendKeys(strInputValue);
		}
		
		strInputValue = Datatable.GetValue("BP_Top");
		if (!strInputValue.trim().isEmpty()) {
			txt_CHC_BP_Sys(driver, index).clear();
			txt_CHC_BP_Sys(driver, index).sendKeys(strInputValue);
		}
		
		strInputValue = Datatable.GetValue("BP_Bottom");
		if (!strInputValue.trim().isEmpty()) {
			txt_CHC_BP_Dia(driver, index).clear();
			txt_CHC_BP_Dia(driver, index).sendKeys(strInputValue);
		}
		
		strInputValue = Datatable.GetValue("BP_Position");
		if (!strInputValue.trim().isEmpty()) {
			lst_CHC_BP_Position(driver, index).selectByVisibleText(strInputValue);
		}
		
		strInputValue = Datatable.GetValue("BP_Side");
		if (!strInputValue.trim().isEmpty()) {
			lst_CHC_BP_Side(driver, index).selectByVisibleText(strInputValue);
		}
		
		strInputValue = Datatable.GetValue("HR");
		if (!strInputValue.trim().isEmpty()) {
			txt_CHC_HeartRate(driver, index).clear();
			txt_CHC_HeartRate(driver, index).sendKeys(strInputValue);
		}
		
		strInputValue = Datatable.GetValue("Resp");
		if (!strInputValue.trim().isEmpty()) {
			txt_CHC_Respirations(driver, index).clear();
			txt_CHC_Respirations(driver, index).sendKeys(strInputValue);
		}
		
		strInputValue = Datatable.GetValue("O2");
		if (!strInputValue.trim().isEmpty()) {
			txt_CHC_O2_Sat(driver, index).clear();
			txt_CHC_O2_Sat(driver, index).sendKeys(strInputValue);
		}
		
		strInputValue = Datatable.GetValue("RoomAir");
		if (!strInputValue.trim().isEmpty()) {
			lst_CHC_RoomAirRate(driver, index).selectByVisibleText(strInputValue);
		}
		
		strInputValue = Datatable.GetValue("Route");
		if (!strInputValue.trim().isEmpty()) {
			lst_CHC_Route(driver, index).selectByVisibleText(strInputValue);
		}
		
		strInputValue = Datatable.GetValue("Temperature");
		if (!strInputValue.trim().isEmpty()) {
			txt_CHC_Temperature(driver, index).clear();
			txt_CHC_Temperature(driver, index).sendKeys(strInputValue);
		}
		
		strInputValue = Datatable.GetValue("TempTaken");
		if (!strInputValue.trim().isEmpty()) {
			lst_CHC_Temp_Taken(driver, index).selectByVisibleText(strInputValue);
		}
		
		strInputValue = Datatable.GetValue("VitalSignsComments");
		if (!strInputValue.trim().isEmpty()) {
			txt_CHC_VitalSignComments(driver, index).clear();
			txt_CHC_VitalSignComments(driver, index).sendKeys(strInputValue);
		}
		
		strInputValue = Datatable.GetValue("Comments");
		if (!strInputValue.trim().isEmpty()) {
			txt_CHC_Comments(driver, index).clear();
			txt_CHC_Comments(driver, index).sendKeys(strInputValue);
		}
	}
	
	// @Method to sign/date CHC Note
		public static void FillCHCSignDate(WebDriver driver) throws Exception {

			//chk_CHC_ReturnToClinician(driver).click();

			strInputValue = Datatable.GetValue("ElecSig");
			if (!strInputValue.trim().isEmpty()) {
				txt_CHC_Signature(driver).clear();
				txt_CHC_Signature(driver).sendKeys(strInputValue);
			}

			strInputValue = Datatable.GetValue("SigDate");
			if (!strInputValue.trim().isEmpty()) {
				dt_CHC_SignatureDate(driver).clear();
				dt_CHC_SignatureDate(driver).sendKeys(strInputValue);
			}
			
		}
		
	// @Method to get validation error window text
	public static void ValidateErrorWindowText(WebDriver driver, String text) {
		String error_text = win_CHC_Validation_Error_Window(driver).getText();
		
		if (error_text.contains(text)) {
			Report.Log(Status.PASS, "The error message displayed is - '"
					+ text + "'");
		} else {
			Report.Log(Status.FAIL, "The error message displayed is - '"
					+ text + "'");
			
		}
		
	}

	// @ Objects for CHC Note
	public static WebElement txt_TimeIn(WebDriver driver) {
		element = driver.findElement(By.id("frm_timein"));
		return element;
	}
	
	public static WebElement txt_TimeOut(WebDriver driver) {
		element = driver.findElement(By.id("frm_timeout"));
		return element;
	}
	
	public static WebElement dt_VisitDate(WebDriver driver) {
		element = driver.findElement(By.id("frm_visitdate"));
		return element;
	}
	
	public static WebElement txt_AssocMileage(WebDriver driver) {
		element = driver.findElement(By.id("associatedMileage"));
		return element;
	}
	
	public static Select lst_CHC_ClinicianDiscipline(WebDriver driver, int index) {
		element = driver.findElement(By.id("frm_CCNDiscipline_idx_"+index+""));
		list = new Select(driver.findElement(By.id("frm_CCNDiscipline_idx_"+index+"")));
		return list;
	}
	
	public static WebElement txt_CHC_StartTime(WebDriver driver, int index) {
		element = driver.findElement(By.id("frm_CCNStartTime_idx_"+index+""));
		return element;
	}
	
	public static WebElement txt_CHC_EndTime(WebDriver driver, int index) {
		element = driver.findElement(By.id("frm_CCNEndTime_idx_"+index+""));
		return element;
	}
	
	public static WebElement dt_CHC_StartDate(WebDriver driver, int index) {
		element = driver.findElement(By.id("frm_CCNStartDate_idx_"+index+""));
		return element;
	}
	
	public static WebElement dt_CHC_EndDate(WebDriver driver, int index) {
		element = driver.findElement(By.id("frm_CCNEndDate_idx_"+index+""));
		return element;
	}
	
	public static WebElement txt_CHC_BP_Sys(WebDriver driver, int index) {
		element = driver.findElement(By.id("frm_VSDuringBPsys_idx_"+index+""));
		return element;
	}
	
	public static WebElement txt_CHC_BP_Dia(WebDriver driver, int index) {
		element = driver.findElement(By.id("frm_VSDuringBPdia_idx_"+index+""));
		return element;
	}
	
	public static Select lst_CHC_BP_Position(WebDriver driver, int index) {
		element = driver.findElement(By.id("frm_VSDuringPosition_idx_"+index+""));
		list = new Select(driver.findElement(By.id("frm_VSDuringPosition_idx_"+index+"")));
		return list;
	}
	
	public static Select lst_CHC_BP_Side(WebDriver driver, int index) {
		element = driver.findElement(By.id("frm_VSDuringSide_idx_"+index+""));
		list = new Select(driver.findElement(By.id("frm_VSDuringSide_idx_"+index+"")));
		return list;
	}
	
	public static WebElement txt_CHC_HeartRate(WebDriver driver, int index) {
		element = driver.findElement(By.id("frm_VSDuringHeartRate_idx_"+index+""));
		return element;
	}
	
	public static WebElement txt_CHC_Respirations(WebDriver driver, int index) {
		element = driver.findElement(By.id("frm_VSDuringResp_idx_"+index+""));
		return element;
	}
	
	public static WebElement txt_CHC_O2_Sat(WebDriver driver, int index) {
		element = driver.findElement(By.id("frm_VSDuring02Sat_idx_"+index+""));
		return element;
	}
	
	public static Select lst_CHC_RoomAirRate(WebDriver driver, int index) {
		element = driver.findElement(By.id("frm_VSDuring02SatType_idx_"+index+""));
		list = new Select(driver.findElement(By.id("frm_VSDuring02SatType_idx_"+index+"")));
		return list;
	}
	
	public static Select lst_CHC_Route(WebDriver driver, int index) {
		element = driver.findElement(By.id("frm_VSDuring02SatRoute_idx_"+index+""));
		list = new Select(driver.findElement(By.id("frm_VSDuring02SatRoute_idx_"+index+"")));
		return list;
	}
	
	public static WebElement txt_CHC_Temperature(WebDriver driver, int index) {
		element = driver.findElement(By.id("frm_VSTemperature_idx_"+index+""));
		return element;
	}
	
	public static Select lst_CHC_Temp_Taken(WebDriver driver, int index) {
		element = driver.findElement(By.id("frm_VSTemperatureType_idx_"+index+""));
		list = new Select(driver.findElement(By.id("frm_VSTemperatureType_idx_"+index+"")));
		return list;
	}
	
	public static WebElement txt_CHC_VitalSignComments(WebDriver driver, int index) {
		element = driver.findElement(By.id("frm_VSComments_idx_"+index+""));
		return element;
	}
	
	public static WebElement btn_CHC_AddProblem(WebDriver driver) {
		element = driver.findElement(By.id("addProblem"));
		return element;
	}
	
	public static WebElement txt_CHC_SearchFilter(WebDriver driver) {
		element = driver.findElement(By.id("dataTableFilter"));
		return element;
	}
	
	public static Select lst_CHC_CommentTemplate(WebDriver driver, int index) {
		element = driver.findElement(By.id("frm_CCNCommentsTemplate_idx_"+index+""));
		list = new Select(driver.findElement(By.id("frm_CCNCommentsTemplate_idx_"+index+"")));
		return list;
	}
	
	public static WebElement txt_CHC_Comments(WebDriver driver, int index) {
		element = driver.findElement(By.id("frm_CCNComments_idx_"+index+""));
		return element;
	}
	
	public static WebElement btn_CHC_Approve(WebDriver driver) {
		element = driver.findElement(By.id("btnApprove"));
		return element;
	}
	
	public static WebElement btn_CHC_Return(WebDriver driver) {
		element = driver.findElement(By.id("btnReturn"));
		return element;
	}
	
	public static WebElement btn_CHC_Save(WebDriver driver) {
		element = driver.findElement(By.id("btnSave"));
		return element;
	}
	
	public static WebElement btn_CHC_Submit(WebDriver driver) {
		element = driver.findElement(By.id("btnSumbit"));
		return element;
	}
	
	public static WebElement chk_CHC_ReturnToClinician(WebDriver driver) {
		element = driver.findElement(By.id("signatureRequired"));
		return element;
	}
	
	public static WebElement chk_CHC_BypassCM(WebDriver driver) {
		element = driver.findElement(By.name("bypassCM"));
		return element;
	}
	
	public static WebElement txt_CHC_Signature(WebDriver driver) {
		element = driver.findElement(By.id("clinicianSignature"));
		return element;
	}
	
	public static WebElement dt_CHC_SignatureDate(WebDriver driver) {
		element = driver.findElement(By.name("signDate"));
		return element;
	}
	
	public static WebElement acc_CHC_AddSection(WebDriver driver) {
		element = driver.findElement(By.xpath("//div[@id='ContinuousCareNote']/div[1]/div[2]/div[7]/div/div[2]/div/input[1]"));
		return element;
	}
	
	public static WebElement acc_CHC_RemoveSection(WebDriver driver) {
		element = driver.findElement(By.xpath("//div[@id='ContinuousCareNote']/div[1]/div[2]/div[7]/div/div[3]/div/input[2]"));
		return element;
	}
	
	public static WebElement btn_CHC_Validation_Err_Ok(WebDriver driver) {
		element = driver.findElement(By.xpath("//div[6]/div[11]/div/button"));
		return element;
	}
	
	public static WebElement win_CHC_Validation_Error_Window(WebDriver driver) {
		element = driver.findElement(By.id("errorWindow"));
		return element;
	}
	
}
