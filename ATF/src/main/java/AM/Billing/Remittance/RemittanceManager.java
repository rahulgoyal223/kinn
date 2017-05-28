package AM.Billing.Remittance;

import DataSource.Datatable;
import DataSource.GlobalData;
import components.Config;
import components.Events;
import components.Report;
import components.SwitchWindow;
import components.TimeDate;
import components.Verify;
import components.Waits;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.aventstack.extentreports.Status;

public class RemittanceManager {

	private static WebElement element;
	private static Select list;
	private static String strInputValue;

	// @Methods in Add Remittance page
	//@Method to goTo File>New>Remit consistently
	public static void goToNewRemittance(WebDriver driver) {
		driver.get(Config.getAppUrl() + "/EHR/#/AM/billing/remittance/new");
	}
	
	// @Method to add remittance
	public static void AddRemittance(WebDriver driver) throws Exception {
		try {
			Waits.fluentWaitIsEnabled(driver, dt_AR_DepositDate(driver), 3);
			//dt_AR_DepositDate(driver).clear();
			//Events.Fire(driver).moveToElement
			(dt_AR_DepositDate(driver)).click();
			dt_AR_DepositDate(driver).sendKeys(Datatable.GetValue("AR_RemittanceDate"));
			Thread.sleep(Waits.getSleepLevelFive());
			String remNumner = Datatable.GetValue("AR_RemittanceNumber");
			if (remNumner.trim().toLowerCase().equals("dynamicvalue")
					|| remNumner.trim().isEmpty()) {
				remNumner = "rn" + TimeDate.getUniqueInteger();
			}
			txt_AR_RemittanceNumber(driver).sendKeys(remNumner);
			GlobalData.setRemittanceNumber(remNumner);
			Thread.sleep(Waits.getSleepLevelFive());
			System.out.println(Datatable.GetValue("AR_Insurance"));
			lst_AR_Insurance(driver).selectByVisibleText(
					Datatable.GetValue("AR_Insurance"));
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
		}
	}

	// @Method for deleting remittance
	public static void WaringWindow(WebDriver driver, String optiontoclick) {

		boolean isWindowOptionSelected = false;
		try {
			List<WebElement> options = driver.findElements(By
					.xpath("//button[@ng-click='close(btn.result)']"));
			for (WebElement opt : options) {
				if (opt.getText().equals(optiontoclick)) {
					opt.click();
					isWindowOptionSelected = true;
					break;
				}
			}
			if (isWindowOptionSelected) {
				Report.Log(Status.PASS, "" + optiontoclick
						+ " is selected successfully");
			} else {
				Report.Log(Status.FAIL, "" + optiontoclick
						+ " is NOT selected successfully");
				Assert.fail("" + optiontoclick
						+ " is NOT selected successfully");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static String FetchClaimsNum(WebDriver driver)
			throws InterruptedException {

		/*
		 * List<WebElement> tblval = driver.findElements(By.tagName("td"));
		 * for(int i=1;i<tblval.size();i++){ String tblval1 =
		 * tblval.get(i).getText(); if(tblval1.trim().equals("Claim#")){
		 */
		// TODO to write a common function for getting table values
		// String claimval =
		// row.findElement(By.xpath(".//input[@type = 'checkbox']")).getText();
		/*
		 * Thread.sleep(Waits.getSleepLevelFive());
		 * Thread.sleep(Waits.getSleepLevelFive()); WebElement table =
		 * driver.findElement(By.tagName("table")); List<WebElement> allrows =
		 * table.findElements(By.tagName("tr")); for (WebElement row : allrows)
		 * { System.out.println(row.getText()); if
		 * (row.getText().contains(patient)) { By attrval =
		 * By.tagName("input")("ng-scope"); String attrreq =
		 * driver.findElement(attrval).getAttribute("id"); return attrreq;
		 */
		/*
		 * List<WebElement> ele = driver.findElements(By.xpath(
		 * "//input[contains(@type,'checkbox') and contains(@class,'ng-scope')]"
		 * )); for(int i=1; i<ele.size();i++){ String claimno =
		 * ele.get(i).getAttribute("id"); return claimno;
		 * 
		 * }
		 */
		By attrval = By
				.xpath("//*[@id='angularGrid|cf00d']/div[2]/table/tbody/tr/td[8]/div/text()");
		String claimno = driver.findElement(attrval).getText();
		return claimno;
		// *[@id="angularGrid|cf00d"]/div[2]/table/tbody/tr/td[8]

	}

	// @Method to Click Remittance Number
	public static void ClickTask(WebDriver driver, String RemittanceNo) {
		try {
			boolean isTaskClicked = false;
			List<WebElement> tsk = driver.findElements(By.tagName("a"));
			for (WebElement task : tsk) {
				if (task.getText().equals(RemittanceNo)) {
					task.click();
					isTaskClicked = true;
					break;
				}
			}
			if (isTaskClicked) {
				Report.Log(Status.PASS, "" + RemittanceNo
						+ " is clicked successfully");
			} else {
				Report.Log(Status.FAIL, "" + RemittanceNo
						+ " is NOT clicked successfully");
				Assert.fail("" + RemittanceNo
						+ " is NOT clicked successfully");
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}

	}

	// @Method to add claim in remittance
	public static void AddClaims(WebDriver driver, String Claimno)
			throws Exception {
		try {
			Waits.fluentWaitIsEnabled(driver, btn_AR_AddClaims(driver), 2);
			btn_AR_AddClaims(driver).click();
			SwitchWindow.to(driver, "Add Claim");
			txt_Cl_ClaimNumber(driver).clear();
			txt_Cl_ClaimNumber(driver).sendKeys(Claimno);
			btn_Cl_Search(driver).click();
			Waits.fluentWaitIsEnabled(driver, chk_Cl_ClaimNo(driver), 2);
			chk_Cl_ClaimNo(driver).click();
			btn_Cl_Done(driver).click();
			SwitchWindow.to(driver, "title");
			Waits.fluentWaitIsNotEnabled(driver, 
					driver.findElement(By.id("insurance")), 2);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/************************************
	 * Method fills in remittance claim details 
	 * and adds Payment
	 * @param driver
	 * @param lineno - index of claim on remittance
	 * @throws Exception
	 ***********************************/
	// @Method to add claim in remittance
	public static void FillClaimDetails(WebDriver driver, Integer lineno)
			throws Exception {

		try {
			
			Integer claim = lineno - 1;
			WebElement table = driver.findElement(By.id("claim-" + claim + ""));
			Waits.fluentWaitIsDisplayed(driver, table, 2);
			element = table.findElement(By
					.xpath(".//i[@class = 'icon-plus-sign']"));
			if (element.isEnabled()) {
				element.click();
			}
			
			Waits.fluentWaitIsEnabled(driver, cmb_AR_RemarkCode(driver), 2);
			strInputValue = Datatable.GetValue("AR_TOB");
			if (!strInputValue.trim().isEmpty()) {
				SelectTOB(driver, strInputValue);
			}

			SelectClaimStatus(driver, Datatable.GetValue("AR_Claimstatus"));

			strInputValue = Datatable.GetValue("AR_Payment");
			if (!strInputValue.trim().isEmpty()) {
				txt_AR_Payment(driver).clear();
				txt_AR_Payment(driver).sendKeys(strInputValue);
			}

			strInputValue = Datatable.GetValue("AR_PostAdjustment");
			if (!strInputValue.trim().isEmpty()) {
				txt_AR_PostAdjustment(driver).clear();
				txt_AR_PostAdjustment(driver).sendKeys(strInputValue);
			}

			strInputValue = Datatable.GetValue("AR_ICNnumber");
			if (!strInputValue.trim().isEmpty()) {
				txt_AR_ICNnumber(driver).sendKeys(strInputValue);
			}

			strInputValue = Datatable.GetValue("AR_Adjustmentgourpcode");
			if (!strInputValue.trim().isEmpty()) {
				SelectAdjustmentGroupCode(driver, strInputValue);
			}

			strInputValue = Datatable.GetValue("AR_Adjustmentreason");
			if (!strInputValue.trim().isEmpty()) {
				SelectAdjustmentReason(driver, strInputValue);
			}

			strInputValue = Datatable.GetValue("AR_Remarkcode");
			if (!strInputValue.trim().isEmpty()) {
				SelectRemarkCode(driver, strInputValue);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/************************************
	 * Method fills in remittance claim details 
	 * and adds PostAdjustment
	 * @param driver
	 * @param lineno - index of claim on remittance
	 * @throws Exception
	 ***********************************/

	//@step for primary payer
	
	public static void FillClaimDetailsPrimarypayer(WebDriver driver, Integer lineno) throws Exception {
			
			try {
				Integer claim = lineno-1;
				WebElement table = driver.findElement(By.id("claim-"+claim+""));
				
				element = table.findElement(By.xpath(".//i[@class = 'icon-plus-sign']"));
				Waits.fluentWaitIsDisplayed(driver, table, 3);
				if(element.isEnabled()) {
				element.click();
				}
				Waits.fluentWaitIsEnabled(driver, cmb_AR_RemarkCode(driver), 5);
				strInputValue = Datatable.GetValue("AR_TOB");
				if (!strInputValue.trim().isEmpty()) {
				SelectTOB(driver, strInputValue);
				}
				
				SelectClaimStatus(driver, Datatable.GetValue("AR_Claimstatus"));
				
				strInputValue = Datatable.GetValue("AR_Payment");
				if (!strInputValue.trim().isEmpty()) {
				txt_AR_Payment(driver).sendKeys(strInputValue); 
				}
				
				strInputValue = Datatable.GetValue("AR_PostAdjustment");
				if (!strInputValue.trim().isEmpty()) {
				txt_AR_PostAdjustment(driver).sendKeys(strInputValue); 
				}	
				
				strInputValue = Datatable.GetValue("AR_ICNnumber");
				if (!strInputValue.trim().isEmpty()) {
				txt_AR_ICNnumber(driver).sendKeys(strInputValue); 
				}
				
				strInputValue = Datatable.GetValue("AR_Adjustmentgourpcode");
				if (!strInputValue.trim().isEmpty()) {
				SelectAdjustmentGroupCode(driver, strInputValue);
				}
				
				strInputValue = Datatable.GetValue("AR_Adjustmentreason");
				if (!strInputValue.trim().isEmpty()) {
				SelectAdjustmentReason(driver, strInputValue);
				}
				
				strInputValue = Datatable.GetValue("AR_Remarkcode");
				if (!strInputValue.trim().isEmpty()) {
				SelectRemarkCode(driver, strInputValue);
				}
				
				}  catch (Exception e) {
				e.printStackTrace();
			}
		}

	//@Reopen the remittance
	public static void FillClaimDetails2(WebDriver driver, Integer lineno) throws Exception {
		try {
			Integer claim = lineno - 1;
			WebElement table = driver.findElement(By.id("claim-" + claim + ""));
			Waits.fluentWaitIsEnabled(driver, table, 3);
			element = table.findElement(By
					.xpath(".//i[@class = 'icon-plus-sign']"));
			if (element.isEnabled()) {
				element.click();
			}
			Waits.fluentWaitIsEnabled(driver, cmb_AR_RemarkCode(driver), 5);
			strInputValue = Datatable.GetValue("AR_TOB");
			if (!strInputValue.trim().isEmpty()) {
				SelectTOB(driver, strInputValue);
			}

			SelectClaimStatus(driver,
					Datatable.GetValue("AR_Claimstatus"));

			strInputValue = Datatable.GetValue("AR_PostAdjustment");
			if (!strInputValue.trim().isEmpty()) {
				txt_AR_PostAdjustment(driver).sendKeys(strInputValue);
			}
			strInputValue = Datatable.GetValue("AR_ICNnumber");
			if (!strInputValue.trim().isEmpty()) {
				txt_AR_ICNnumber(driver).sendKeys(strInputValue);
			}

			strInputValue = Datatable.GetValue("AR_Adjustmentgourpcode");
			if (!strInputValue.trim().isEmpty()) {
				SelectAdjustmentGroupCode(driver, strInputValue);
			}

			strInputValue = Datatable.GetValue("AR_Adjustmentreason");
			if (!strInputValue.trim().isEmpty()) {
				SelectAdjustmentReason(driver, strInputValue);
			}

			strInputValue = Datatable.GetValue("AR_Remarkcode");
			if (!strInputValue.trim().isEmpty()) {
				SelectRemarkCode(driver, strInputValue);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	

	public static void SelectAdjustmentGroupCodeClaim(WebDriver driver,
			String Groupcode) throws InterruptedException {
		Waits.ForElementToBeClickable(driver, cmb_AR_AdjustmentGroupCode(driver));
		cmb_AR_AdjustmentGroupCode(driver).click();
		Events.Fire(driver)
				.moveToElement(cmb_AR_AdjustmentGroupCodeSearchClaim(driver))
				.click().perform();
		cmb_AR_AdjustmentGroupCodeSearchClaim(driver).sendKeys(Groupcode);
		Thread.sleep(Waits.getSleepLevelTwo());
		cmb_AR_AdjustmentGroupCodeSearchClaim(driver).sendKeys(Keys.ENTER);
	}

	private static void SelectAdjustmentReasonClaim(WebDriver driver,
			String Adjreason) throws InterruptedException {
		Waits.ForElementToBeClickable(driver, cmb_AR_AdjustmentReasonClaim(driver));
		cmb_AR_AdjustmentReasonClaim((driver)).click();
		Events.Fire(driver)
				.moveToElement(cmb_AR_AdjustmentReasonSearchClaim(driver))
				.click().perform();
		cmb_AR_AdjustmentReasonSearchClaim(driver).sendKeys(Adjreason);
		Thread.sleep(Waits.getSleepLevelTwo());
		cmb_AR_AdjustmentReasonSearchClaim(driver).sendKeys(Keys.ENTER);
	}

	private static void SelectAdjustmentReasonProvider(WebDriver driver,
			String Adjreason) throws InterruptedException {
		Waits.ForElementToBeClickable(driver, cmb_AR_AdjustmentReasonProvider(driver));
		cmb_AR_AdjustmentReasonProvider((driver)).click();
		Events.Fire(driver)
				.moveToElement(cmb_AR_AdjustmentReasonSearchProvider(driver))
				.click().perform();
		cmb_AR_AdjustmentReasonSearchProvider(driver).sendKeys(Adjreason);
		Thread.sleep(Waits.getSleepLevelTwo());
		cmb_AR_AdjustmentReasonSearchProvider(driver).sendKeys(Keys.ENTER);
	}

	private static void SelectAdjustmentReasonExternal(WebDriver driver,
			String Adjreason) throws InterruptedException {
		Waits.ForElementToBeClickable(driver, cmb_AR_AdjustmentReasonExternal(driver));
		cmb_AR_AdjustmentReasonExternal(driver).click();
		Events.Fire(driver)
				.moveToElement(cmb_AR_AdjustmentReasonSearchExternal(driver))
				.click().perform();
		cmb_AR_AdjustmentReasonSearchExternal(driver).sendKeys(Adjreason);
		Thread.sleep(Waits.getSleepLevelTwo());
		cmb_AR_AdjustmentReasonSearchExternal(driver).sendKeys(Keys.ENTER);
	}

	public static void AddClaimsAdjustment(WebDriver driver, String Claimno)
			throws Exception {
		try {
			Waits.ForElementToBeClickable(driver, btn_AR_AddClaimAdjustment(driver));
			btn_AR_AddClaimAdjustment(driver).click();
			Waits.ForGlobalAjaxLoader(driver);
			txt_AR_ClaimAdjustment(driver).sendKeys(
					Datatable.GetValue("AR_Claimadjustment"));
			SelectAdjustmentGroupCodeClaim(driver,
					Datatable.GetValue("AR_Adjustmentgourpcode"));
			SelectAdjustmentReasonClaim(driver,
					Datatable.GetValue("AR_Adjustmentreason"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void AddProviderAdjustment(WebDriver driver) throws Exception {
		try {
			Waits.fluentWaitIsEnabled(driver, btn_AR_AddproviderAdjustment(driver), 5);
			btn_AR_AddproviderAdjustment(driver).click();
			Waits.ForGlobalAjaxLoader(driver);
			txt_AR_ProviderAdjustment(driver).sendKeys(
					Datatable.GetValue("AR_Provideradjustment"));
			SelectAdjustmentReasonProvider(driver,
					Datatable.GetValue("AR_ProviderAdjustmentreason"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void AddExternalAdjustment(WebDriver driver) throws Exception {
		try {
			Waits.fluentWaitIsEnabled(driver, btn_AR_ApplyBalanceExternally(driver), 3);
			btn_AR_ApplyBalanceExternally(driver).click();
			Waits.ForGlobalAjaxLoader(driver);
			txt_AR_ExternalAdjustment(driver).clear();
			txt_AR_ExternalAdjustment(driver).sendKeys(
					Datatable.GetValue("AR_Externaladjustment"));
			SelectAdjustmentReasonExternal(driver,
					Datatable.GetValue("AR_ExternalAdjustmentreason"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void SelectClaimStatus(WebDriver driver, String Statuscode)
			throws InterruptedException {
		Waits.fluentWaitIsEnabled(driver, cmb_AR_ClaimStatus(driver), 1);
		cmb_AR_ClaimStatus(driver).click();
		Events.Fire(driver).moveToElement(cmb_AR_ClaimStatusSearch(driver))
				.click().perform();
		cmb_AR_ClaimStatusSearch(driver).sendKeys(Statuscode);
		Thread.sleep(Waits.getSleepLevelTwo());
		cmb_AR_ClaimStatusSearch(driver).sendKeys(Keys.ENTER);

	}

	private static void SelectTOB(WebDriver driver, String TOB)
			throws InterruptedException {
		Waits.fluentWaitIsEnabled(driver, cmb_AR_TOB(driver), 1);
		Events.Fire(driver).moveToElement(cmb_AR_TOB(driver)).click().perform();
		Events.Fire(driver).moveToElement(cmb_AR_TOBSearch(driver)).click()
				.perform();
		Thread.sleep(Waits.getSleepLevelFive());
		cmb_AR_TOBSearch(driver).sendKeys(TOB);
		Thread.sleep(Waits.getSleepLevelFive());
		cmb_AR_TOBSearch(driver).sendKeys(Keys.ENTER);
	}

	private static void SelectAdjustmentGroupCode(WebDriver driver,String Groupcode) 
			throws InterruptedException {
		Waits.fluentWaitIsEnabled(driver, cmb_AR_AdjustmentGroupCode(driver), 1);
		Events.Fire(driver).moveToElement(cmb_AR_AdjustmentGroupCode(driver)).click().perform();
		Events.Fire(driver)
				.moveToElement(cmb_AR_AdjustmentGroupCodeSearch(driver))
				.click().perform();
		Thread.sleep(Waits.getSleepLevelFive());
		cmb_AR_AdjustmentGroupCodeSearch(driver).sendKeys(Groupcode);
		Thread.sleep(Waits.getSleepLevelFive());
		cmb_AR_AdjustmentGroupCodeSearch(driver).sendKeys(Keys.ENTER);
	}

	private static void SelectAdjustmentReason(WebDriver driver,
			String Adjreason) throws InterruptedException {
		Waits.fluentWaitIsEnabled(driver, cmb_AR_AdjustmentReason(driver), 1);
		cmb_AR_AdjustmentReason(driver).click();
		Events.Fire(driver)
				.moveToElement(cmb_AR_AdjustmentReasonSearch(driver)).click()
				.perform();
		cmb_AR_AdjustmentReasonSearch(driver).sendKeys(Adjreason);
		Thread.sleep(Waits.getSleepLevelTwo());
		cmb_AR_AdjustmentReasonSearch(driver).sendKeys(Keys.ENTER);
	}

	private static void SelectRemarkCode(WebDriver driver, String Remarkcode)
			throws InterruptedException {
		Waits.fluentWaitIsEnabled(driver, cmb_AR_RemarkCode(driver), 1);
		cmb_AR_RemarkCode(driver).click();
		Events.Fire(driver).moveToElement(cmb_AR_RemarkCodeSearch(driver))
				.click().perform();
		cmb_AR_RemarkCodeSearch(driver).sendKeys(Remarkcode);
		Thread.sleep(Waits.getSleepLevelFive());
		cmb_AR_RemarkCodeSearch(driver).sendKeys(Keys.ENTER);
	}

	public static void VerifyAlertMessage(WebDriver driver, String Msg)
			throws Exception {
		String getMsg;
		try {
			Waits.fluentWaitIsEnabled(driver, Alt_AR_Succmsg(driver), 5);
			getMsg = Alt_AR_Succmsg(driver).getText();
			if (getMsg.trim().equals(Msg.trim())) {
				Report.Log(Status.PASS, "Alert " + Msg + " is displayed");
			} else {
				Report.Log(Status.FAIL, "Alert " + Msg + " is NOT displayed");
				Report.attachScreenShotToReport(driver);
				Assert.fail();
			}
		} catch (Exception e) {
			e.printStackTrace();
			Report.Log(Status.FAIL, "Alert " + Msg + " is NOT displayed");
			Report.attachScreenShotToReport(driver);
			Assert.fail("Alert " +Msg+ " is NOT displayed");
		}

	}

	public static void OpenRemittance(WebDriver driver, int rowIndex,
			int colIndex) {
		try {
			
			WebElement table = driver
					.findElement(By
							.cssSelector("table.table-condensed.table-striped.ks-grid-standard"));
			Waits.fluentWaitIsDisplayed(driver, table, 2);
			if (table.getText().trim()
					.contains("There are currently no records to display.")) {
				Report.Log(Status.INFO,
						"There are currently no records to display.");
			} else {
				WebElement allRows = table
						.findElements(By.xpath(".//tbody/tr")).get(rowIndex);
				Waits.fluentWaitIsEnabled(driver, allRows, 2);
				allRows.findElements(By.xpath(".//td")).get(colIndex).click();
				Report.Log(Status.PASS, "Remittence is opened successfully");
			}
		} catch (Exception e) {
			e.printStackTrace();
			Report.Log(Status.FAIL, "Remittence is not opened");
			Report.attachScreenShotToReport(driver);
			Assert.fail("Remittance was not opened");
		}
	}

	public static void SaveAndCompleteRemit(WebDriver driver) throws Exception {
		Waits.ForElementToBeClickable(driver, btn_AR_SaveandComplete(driver));
		btn_AR_SaveandComplete(driver).click();
		Waits.fluentWaitIsDisplayed(driver, Alt_AR_Succmsg(driver), 5);
		Verify.ExactPageTitle(driver, "Remittance Manager | Kinnser Software"); 
		VerifyAlertMessage(driver, "The Payment Remittance has been saved and completed.");
	}
	
	public static void SaveRemit(WebDriver driver) throws Exception {
		Waits.ForElementToBeClickable(driver, btn_AR_Save(driver));
		btn_AR_Save(driver).click();
		Thread.sleep(Waits.getSleepLevelTwo());
		Waits.fluentWaitIsDisplayed(driver, Alt_AR_Succmsg(driver), 5);
		VerifyAlertMessage(driver, "The Payment Remittance has been saved.");  	
	}
	
	public static void DeleteRemit(WebDriver driver) throws Exception {
		Waits.ForElementToBeClickable(driver, btn_AR_Delete(driver));
		btn_AR_Delete(driver).click();
		SwitchWindow.toTopWindow(driver);
		btn_AR_WarningOk(driver).click();
		VerifyAlertMessage(driver, "Deleted");
	}
	
	public static void CancelRemit(WebDriver driver) throws Exception {
		Verify.ExactPageHeader(driver, "Remittance");
		Waits.fluentWaitIsEnabled(driver, btn_AR_Cancel(driver), 3);
		btn_AR_Cancel(driver).click();
	}
	
	public static void FilterAndSearchForRemit(WebDriver driver, String rmNumber) throws Exception {
		Verify.ExactPageTitle(driver, "Remittance Manager | Kinnser Software"); 
		btn_RM_Filters(driver).click();
      	btn_RM_SelectAll(driver).click();
      	btn_RM_ApplyFilters(driver).click();
      	Waits.ForElementToBeClickable(driver, txt_RM_Search(driver));
		txt_RM_Search(driver).sendKeys(rmNumber);
		
	}
	
	public static void ReopenRemit(WebDriver driver) throws Exception {
		Verify.ExactPageHeader(driver, "Remittance (Read Only)");
		Waits.fluentWaitIsEnabled(driver, btn_RM_Reopen(driver), 3);
		btn_RM_Reopen(driver).click();
	}
	
	// Objects in Add Remittance screen
	public static WebElement dt_AR_DepositDate(WebDriver driver) {
		element = driver.findElement(By.id("depositDate"));
		return element;
	}

	public static WebElement txt_AR_RemittanceNumber(WebDriver driver) {
		element = driver.findElement(By.id("referenceNumber"));
		return element;
	}

	public static Select lst_AR_Insurance(WebDriver driver) {
		list = new Select(driver.findElement(By.id("insurance")));
		return list;
	}

	public static WebElement txt_AR_CheckTotal(WebDriver driver) {
		element = driver.findElement(By.id("remittanceAmount"));
		return element;
	}

	public static WebElement btn_AR_AddproviderAdjustment(WebDriver driver) {
		element = driver.findElement(By.id("addProviderAdjustmentBtn"));
		return element;
	}

	public static WebElement btn_AR_ApplyBalanceExternally(WebDriver driver) {
		element = driver.findElement(By.id("addExternalAdjustmentBtn"));
		return element;
	}

	public static WebElement btn_AR_Cancel(WebDriver driver) {
		element = driver.findElement(By.id("cancelButton"));
		return element;
	}

	public static WebElement btn_AR_Save(WebDriver driver) {
		element = driver.findElement(By.id("saveButton"));
		return element;
	}

	public static WebElement btn_AR_SaveandComplete(WebDriver driver) {
		element = driver.findElement(By.id("completeButton"));
		Waits.ForGlobalAjaxLoader(driver);
		return element;
	}

	public static WebElement btn_AR_Delete(WebDriver driver) {
		element = driver.findElement(By.id("deleteButton"));
		Waits.ForGlobalAjaxLoader(driver);
		return element;
	}

	public static WebElement btn_AR_AddClaims(WebDriver driver) {
		element = driver.findElement(By.id("addClaimsButton"));
		return element;
	}

	public static WebElement btn_AR_ExpandClaimDetails(WebDriver driver) {
		element = driver.findElement(By
				.xpath(".//i[@class = 'icon-plus-sign']"));
		return element;
	}

	public static WebElement cmb_AR_TOB(WebDriver driver) {
		element = driver
				.findElement(By
						.xpath("//div[@id='s2id_typeOfBill']//span[@class='select2-arrow']"));
		return element;
	}

	public static WebElement cmb_AR_TOBSearch(WebDriver driver) {
		element = driver.findElement(By.xpath("/html/body/div[4]/div/input"));
		return element;
	}

	public static WebElement cmb_AR_ClaimStatus(WebDriver driver) {
		element = driver.findElement(By.id("s2id_claimStatus"));
		return element;
	}

	public static WebElement cmb_AR_ClaimStatusSearch(WebDriver driver) {
		element = driver.findElement(By.xpath("/html/body/div[5]/div/input"));
		return element;
	}

	public static WebElement txt_AR_ICNnumber(WebDriver driver) {
		element = driver.findElement(By
				.xpath("//input[@ng-model = 'claim.ICNNumber']"));
		return element;
	}

	public static WebElement txt_AR_RemarkCodes(WebDriver driver) {
		element = driver.findElement(By
				.xpath("/html/body/div[1]/div[5]/div[2]/div[1]/form/div/div[4]/table[2]/tbody/tr[4]/td/table/tbody/tr/td[2]/div/table/tbody/tr/td[5]/div"));
		return element;
	}

	public static WebElement txt_AR_Payment(WebDriver driver) {
		element = driver.findElement(By
				.xpath("//input[@ng-model = 'lineItem.payment']"));
		return element;
	}

	public static WebElement txt_AR_PostAdjustment(WebDriver driver) {
		element = driver.findElement(By
				.xpath("//input[@ng-model = 'adjustment.postAdjustment']"));
		return element;
	}

	public static WebElement cmb_AR_AdjustmentGroupCode(WebDriver driver) {
		element = driver.findElement(By.xpath("//div[@id='s2id_adjustmentGroupCode']//span[@class='select2-arrow']"));
		return element;
	}

	public static WebElement cmb_AR_AdjustmentGroupCodeSearch(WebDriver driver) {
		element = driver
				.findElement(By
						.xpath("/html/body/div[6]/div/input"));
		return element;
	}

	public static WebElement cmb_AR_AdjustmentReason(WebDriver driver) {
		element = driver
				.findElement(By
						.xpath("/html/body/div[1]/div[5]/div[2]/div[1]/form/div/div[4]/table[2]/tbody/tr[4]/td/table/tbody/tr/td[2]/div/table/tbody/tr/td[4]/div/a"));
		return element;
	}

	public static WebElement cmb_AR_AdjustmentReasonSearch(WebDriver driver) {
		element = driver
				.findElement(By
						.xpath("/html/body/div[7]/div/input"));
		return element;
	}

	public static WebElement cmb_AR_RemarkCode(WebDriver driver) {
		element = driver
				.findElement(By
						.xpath("/html/body/div[1]/div[5]/div[2]/div[1]/form/div/div[4]/table[2]/tbody/tr[4]/td/table/tbody/tr/td[2]/div/table/tbody/tr/td[5]/div/a"));
		return element;
	}

	public static WebElement cmb_AR_RemarkCodeSearch(WebDriver driver) {
		element = driver
				.findElement(By
						.xpath("/html/body/div[8]/div/input"));
		return element;
	}

	public static WebElement Alt_AR_Succmsg(WebDriver driver) {
		element = driver.findElement(By
				.xpath("//span[@ng-bind-html-unsafe = 'alert.message']"));
		return element;
	}

	public static WebElement btn_AR_WarningOk(WebDriver driver) {
		element = driver
				.findElement(By
						.xpath("//button[@class = 'btn ng-scope ng-binding btn-primary']"));
		return element;
	}

	public static WebElement btn_AR_Warningcancel(WebDriver driver) {
		element = driver.findElement(By
				.xpath(".//i[@class = 'btn ng-scope ng-binding']"));
		return element;
	}

	public static WebElement btn_RM_Reopen(WebDriver driver) {
		element = driver.findElement(By.id("reopenButton"));
		return element;
	}

	public static WebElement cmb_AR_AdjustmentGroupCodeSearchClaim(WebDriver driver) {
		element = driver
				.findElement(By
						.xpath("/html/body/div[9]/div/input"));
		return element;
	}

	public static WebElement cmb_AR_AdjustmentReasonClaim(WebDriver driver) {
		element = driver.findElement(By.id("s2id_adjustmentReason"));
		return element;
	}

	public static WebElement cmb_AR_AdjustmentReasonSearchClaim(WebDriver driver) {
		element = driver
				.findElement(By
						.xpath("/html/body/div[10]/div/input"));
		return element;
	}

	public static WebElement btn_AR_AddClaimAdjustment(WebDriver driver) {
		element = driver.findElement(By
				.xpath("//input[@value = 'Add Claim Adjustment']"));
		return element;
	}

	public static WebElement txt_AR_ClaimAdjustment(WebDriver driver) {
		element = driver.findElement(By.id("claimAdjustmentAmount_1"));
		return element;
	}

	public static WebElement txt_AR_NetReimbursementAmt(WebDriver driver) {
		element = driver.findElement(By
				.xpath("//input[@ng-model = 'claim.claimAmount']"));
		return element;
	}

	public static WebElement txt_AR_ProviderAdjustment(WebDriver driver) {
		element = driver.findElement(By.id("providerAdjustment_0"));
		return element;
	}

	public static WebElement cmb_AR_AdjustmentReasonProvider(WebDriver driver) {
		element = driver.findElement(By.id("s2id_providerAdjustmentCode_0"));
		return element;
	}

	public static WebElement cmb_AR_AdjustmentReasonSearchProvider(WebDriver driver) {
		element = driver
				.findElement(By
						.xpath("/html/body/div[4]/div/input"));
		return element;
	}

	public static WebElement txt_AR_ExternalAdjustment(WebDriver driver) {
		element = driver.findElement(By.id("externalAdjustment_0"));
		return element;
	}

	public static WebElement cmb_AR_AdjustmentReasonExternal(WebDriver driver) {
		element = driver.findElement(By.id("s2id_externalAdjustmentCode_0"));
		return element;
	}

	public static WebElement cmb_AR_AdjustmentReasonSearchExternal(WebDriver driver) {
		element = driver
				.findElement(By
						.xpath("/html/body/div[5]/div/input"));
		return element;
	}

	// Objects in Add Claims screen

	public static WebElement txt_Cl_LastName(WebDriver driver) {
		element = driver.findElement(By.id("inputPatientLast"));
		return element;
	}

	public static WebElement txt_Cl_FirstName(WebDriver driver) {
		element = driver.findElement(By.id("inputPatientFirst"));
		return element;
	}

	public static WebElement txt_Cl_ClaimNumber(WebDriver driver) {
		element = driver.findElement(By.id("inputClaimNumber"));
		return element;
	}

	public static Select lst_Cl_ClaimStatus(WebDriver driver) {
		list = new Select(driver.findElement(By.id("claimTransactionStatus")));
		return list;
	}

	public static WebElement dt_Cl_ClaimDate(WebDriver driver) {
		element = driver.findElement(By.id("inputClaimDate"));
		return element;
	}

	public static WebElement dt_Cl_ServiceDateRangeStart(WebDriver driver) {
		element = driver.findElement(By.id("inputServiceDateRangeStart"));
		return element;
	}

	public static WebElement dt_Cl_ServiceDateRangeEnd(WebDriver driver) {
		element = driver.findElement(By.id("inputServiceDateRangeEnd"));
		return element;
	}

	public static WebElement btn_Cl_Search(WebDriver driver) {
		element = driver.findElement(By.id("search"));
		return element;
	}

	public static WebElement btn_Cl_Reset(WebDriver driver) {
		element = driver.findElement(By.xpath("//button[@value = 'Reset']"));
		return element;
	}

	public static WebElement btn_Cl_Done(WebDriver driver) {
		element = driver.findElement(By.id("doneBtn"));
		return element;
	}

	public static WebElement chk_Cl_ClaimNo(WebDriver driver) {
		element = driver.findElement(By.id("addClaimCheckBox_0"));
		return element;
	}

	// @Objects in Remittance Manager's screen

	public static WebElement btn_RM_Filters(WebDriver driver) {
		element = driver.findElement(By
				.xpath("//button[@ng-click = 'toggleFilterPanel()']"));
		return element;
	}

	public static WebElement btn_RM_SelectAll(WebDriver driver) {
		element = driver.findElement(By
				.id("filter_Remittance_Status_btn_select_all"));
		return element;
	}

	public static WebElement btn_RM_ApplyFilters(WebDriver driver) {

		element = driver.findElement(By
				.xpath("//button[@ng-click='fetchData()']"));
		return element;
	}

	public static WebElement txt_RM_Search(WebDriver driver) {  
        element = driver.findElement(By.name("gridQuery"));
        return element;
	}
	
	//@step delete remittance
	public static WebElement icon_AR_Delete(WebDriver driver) throws InterruptedException {
	element = driver.findElement(By.xpath("//i[@ng-click='removeClaim( claim )']"));
	Waits.ForBrowserLoad(driver);
	return element;
	}
	
	//@Step Secondary payer check enable
		public static WebElement chk_PP_SecondarypayerEnable(WebDriver driver) throws InterruptedException {
			element = driver.findElement(By.xpath("//input[@ng-model='claim.billSecondaryEnabled']"));
			//element = driver.findElement(By.id("billSecondaryCheckbox_2432110"));
	        Waits.ForBrowserLoad(driver);
	        return element;
	 }


    public static void FillClaimDetailsForTakeBack(WebDriver driver, int lineno) {
		try {
			Waits.ForBrowserLoad(driver);
			Thread.sleep(Waits.getSleepLevelFive());
			Integer claim = lineno - 1;
			WebElement table = driver.findElement(By.id("claim-" + claim + ""));

			element = table.findElement(By
					.xpath(".//i[@class = 'icon-plus-sign']"));
			if (element.isEnabled()) {
				element.click();
			}

			strInputValue = Datatable.GetValue("AR_TOB");
			if (!strInputValue.trim().isEmpty()) {
				SelectTOB(driver, strInputValue);
			}

			SelectClaimStatus(driver, Datatable.GetValue("AR_Claimstatus"));

			strInputValue = Datatable.GetValue("AR_Payment");
			if (!strInputValue.trim().isEmpty()) {
				strInputValue = GetNegativeValue(strInputValue);
				txt_AR_Payment(driver).clear();
				txt_AR_Payment(driver).sendKeys(strInputValue);
			}

			strInputValue = Datatable.GetValue("AR_PostAdjustment");
			if (!strInputValue.trim().isEmpty()) {
				txt_AR_PostAdjustment(driver).sendKeys(strInputValue);
			}

			strInputValue = Datatable.GetValue("AR_ICNnumber");
			if (!strInputValue.trim().isEmpty()) {
				txt_AR_ICNnumber(driver).sendKeys(strInputValue);
			}

			strInputValue = Datatable.GetValue("AR_Adjustmentgourpcode");
			if (!strInputValue.trim().isEmpty()) {
				SelectAdjustmentGroupCode(driver, strInputValue);
			}

			strInputValue = Datatable.GetValue("AR_Adjustmentreason");
			if (!strInputValue.trim().isEmpty()) {
				SelectAdjustmentReason(driver, strInputValue);
			}

			strInputValue = Datatable.GetValue("AR_Remarkcode");
			if (!strInputValue.trim().isEmpty()) {
				SelectRemarkCode(driver, strInputValue);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
    }

	private static String GetNegativeValue(String strInputValue) {
		if(strInputValue.contains("-")){
			return strInputValue.replace("-", "");
		}
		else{
			return "-" + strInputValue;
		}
	}
}

