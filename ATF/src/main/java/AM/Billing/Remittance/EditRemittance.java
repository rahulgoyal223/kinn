package AM.Billing.Remittance;

import components.Waits;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Wait;

import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by austin.ledbetter on 2/1/2017.
 */
public class EditRemittance {

    private static WebElement getGenericElementByIdWhenClickable(WebDriver driver, String id){
        WebElement element = driver.findElement(By.id(id));
        Waits.ForElementToBeClickable(driver, element);
        return element;
    }

    private static WebElement getGenericElementByIdWhenVisible(WebDriver driver, String id){
        WebElement element = driver.findElement(By.id(id));
        Waits.ForElementVisibility(driver, element);
        return element;
    }

    public static WebElement cbo_PatientSearch_Select2Dropmask(WebDriver driver) {
        return getGenericElementByIdWhenClickable(driver, "select2-drop-mask");
    }

    public static WebElement lnk_showClaim(WebDriver driver, String claimNumber){
        return getGenericElementByIdWhenClickable(driver, claimNumber + "_showClaim");
    }

    public static WebElement txt_PaymentsAppliedToClaims(WebDriver driver) {
        return getGenericElementByIdWhenVisible(driver, "reimbursementTotal");
    }

    public static WebElement txt_UnappliedPayment(WebDriver driver) {
        return getGenericElementByIdWhenVisible(driver, "balance");
    }

    public static WebElement txt_claimNetReimbursement(WebDriver driver, String claimNumber) {
        return getGenericElementByIdWhenVisible(driver, claimNumber + "_netReimbursementAmount");
    }

    public static WebElement txt_firstLineItemPayment(WebDriver driver, String claimNumber) {
        WebElement claimLineItemTable = driver.findElement(By.id(claimNumber + "_lineItemsTable"));
        List<WebElement> elements = claimLineItemTable.findElements(By.tagName("table"));
        for (WebElement element : elements) {
            if(element.getAttribute("id").contains("lineItemDetails")){
                String claimLineItemId = element.getAttribute("id").replace("_lineItemDetails", "");
                WebElement payment = element.findElement(By.id(claimLineItemId + "_payment"));
                return payment;
            }
        }
        return null;
    }

    public static WebElement txt_firstLineItemBalance(WebDriver driver, String claimNumber) {
        WebElement claimLineItemTable = driver.findElement(By.id(claimNumber + "_lineItemsTable"));
        List<WebElement> elements = claimLineItemTable.findElements(By.tagName("table"));
        for (WebElement element : elements) {
            Waits.ForElementVisibility(driver, element);
            if(element.getAttribute("id").contains("lineItemDetails")){
                String claimLineItemId = element.getAttribute("id").replace("_lineItemDetails", "");
                WebElement balance = driver.findElement(By.id(claimLineItemId + "_balance"));
                Waits.ForElementVisibility(driver, balance);
                return balance;
            }
        }
        return null;
    }

    public static WebElement cbo_PatientSearch(WebDriver driver){
        WebElement select2Div = driver.findElement(By.id("s2id_remittancePatientFilter"));
        Waits.ForElementVisibility(driver, select2Div);
        WebElement dropdown = select2Div.findElement(By.className("select2-choice"));
        Waits.ForElementVisibility(driver, dropdown);
        return dropdown;
    }

    public static void EnterPatientSearchTerm(WebDriver driver, String patientSearchString){
        WebElement dropdown = cbo_PatientSearch(driver);
        dropdown.click();
        WebElement input = driver.findElement(By.className("select2-input"));
        Waits.ForElementVisibility(driver, input);
        input.sendKeys(patientSearchString);
    }

    public static boolean VerifyNoPatientSearchResults(WebDriver driver) {
        WebElement input = driver.findElement(By.className("select2-no-results"));
        Waits.ForElementVisibility(driver, input);
        return true;
    }

    public static boolean VerifyPatientInSearchResults(WebDriver driver, String patientSearchString) {
        WebElement resultsList = driver.findElement(By.className("select2-results"));
        WebElement result = resultsList.findElement(By.className("select2-result"));
        String resultText = result.getText();
        return resultText.equalsIgnoreCase(patientSearchString);
    }

    private static Float getFloatValueFromRemittanceLineItem(String remittanceLineItemValue){
        String value = remittanceLineItemValue.replace(",", "");
        if(remittanceLineItemValue.contains("(") && remittanceLineItemValue.contains(")")){
            return Float.parseFloat(value.replace("(", "").replace(")", "")) * -1;
        }
        return Float.parseFloat(value);
    }

    public static String getLineItemBalance(WebDriver driver, String claimNumber) {
        WebElement claimLineItemTable = driver.findElement(By.id(claimNumber + "_lineItemsTable"));
        List<WebElement> trElements = claimLineItemTable.findElements(By.tagName("tr"));
        Float currentBalance = Float.valueOf(0);
        DecimalFormat df = new DecimalFormat("#.##");
        for (WebElement trElement : trElements) {
            List<WebElement> divElements = trElement.findElements(By.tagName("div"));
            for (WebElement divElement : divElements) {
                String divId = divElement.getAttribute("id");
                if(divId.contains("balance") && divId.contains(claimNumber)){
                    currentBalance += getFloatValueFromRemittanceLineItem(divElement.getText());
                }
            }
        }
        return df.format(currentBalance);
    }
}