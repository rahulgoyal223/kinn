package AM.Billing.Claims;

import DataSource.Datatable;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class Claim_Worksheet {

    public static void CheckConditionCodes(WebDriver driver, String dataFileName, String dataPageName) throws Exception {
        //Check condition codes
        String expected = "";
        String actual = "";
        boolean isGood = false;
        Datatable.loadDataSheet(dataFileName, dataPageName);
        for (int i = 0; i < 11; i++) {
            int spreadSheetConditionCodeIndex = i + 18;
            int workSheetConditionCodeIndex = i + 1;
            expected = Datatable.GetValue("conditionCode" + spreadSheetConditionCodeIndex);
            actual = AM.Billing.Claims.RAP_Worksheet.GetConditionCode(driver, "conditionCode" + workSheetConditionCodeIndex).getText();
            isGood = actual.startsWith(expected);
            Assert.assertTrue(isGood);
        }
    }

    public static void CheckOccurrenceCodes(WebDriver driver, String dataFileName, String dataPageName) throws Exception {
        //OccurrenceCodes
        String expected = "";
        String actual = "";
        boolean isGood = false;
        Datatable.loadDataSheet(dataFileName, dataPageName);
        expected = Datatable.GetValue("oCode31a");
        actual = AM.Billing.Claims.RAP_Worksheet.GetOccurrenceCode(driver, "occurenceCode1").getText();
        isGood = actual.startsWith(expected);
        Assert.assertTrue(isGood);

        expected = Datatable.GetValue("oDate31a");
        actual = AM.Billing.Claims.RAP_Worksheet.OccurrenceDate1(driver).getAttribute("value");
        isGood = actual.startsWith(expected);
        Assert.assertTrue(isGood);

        expected = Datatable.GetValue("oCode31b");
        actual = AM.Billing.Claims.RAP_Worksheet.GetOccurrenceCode(driver, "occurenceCode2").getText();
        isGood = actual.startsWith(expected);
        Assert.assertTrue(isGood);

        expected = Datatable.GetValue("oDate31b");
        actual = AM.Billing.Claims.RAP_Worksheet.OccurrenceDate2(driver).getAttribute("value");
        isGood = actual.startsWith(expected);
        Assert.assertTrue(isGood);

        expected = Datatable.GetValue("oCode32a");
        actual = AM.Billing.Claims.RAP_Worksheet.GetOccurrenceCode(driver, "occurenceCode3").getText();
        isGood = actual.startsWith(expected);
        Assert.assertTrue(isGood);

        expected = Datatable.GetValue("oDate32a");
        actual = AM.Billing.Claims.RAP_Worksheet.OccurrenceDate3(driver).getAttribute("value");
        isGood = actual.startsWith(expected);
        Assert.assertTrue(isGood);

        expected = Datatable.GetValue("oCode32b");
        actual = AM.Billing.Claims.RAP_Worksheet.GetOccurrenceCode(driver, "occurenceCode4").getText();
        isGood = actual.startsWith(expected);
        Assert.assertTrue(isGood);

        expected = Datatable.GetValue("oDate32b");
        actual = AM.Billing.Claims.RAP_Worksheet.OccurrenceDate4(driver).getAttribute("value");
        isGood = actual.startsWith(expected);
        Assert.assertTrue(isGood);

        expected = Datatable.GetValue("oCode33a");
        actual = AM.Billing.Claims.RAP_Worksheet.GetOccurrenceCode(driver, "occurenceCode5").getText();
        isGood = actual.startsWith(expected);
        Assert.assertTrue(isGood);

        expected = Datatable.GetValue("oDate33a");
        actual = AM.Billing.Claims.RAP_Worksheet.OccurrenceDate5(driver).getAttribute("value");
        isGood = actual.startsWith(expected);
        Assert.assertTrue(isGood);

        expected = Datatable.GetValue("oCode33b");
        actual = AM.Billing.Claims.RAP_Worksheet.GetOccurrenceCode(driver, "occurenceCode6").getText();
        isGood = actual.startsWith(expected);
        Assert.assertTrue(isGood);

        expected = Datatable.GetValue("oDate33b");
        actual = AM.Billing.Claims.RAP_Worksheet.OccurrenceDate6(driver).getAttribute("value");
        isGood = actual.startsWith(expected);
        Assert.assertTrue(isGood);

        expected = Datatable.GetValue("oCode34a");
        actual = AM.Billing.Claims.RAP_Worksheet.GetOccurrenceCode(driver, "occurenceCode7").getText();
        isGood = actual.startsWith(expected);
        Assert.assertTrue(isGood);

        expected = Datatable.GetValue("oDate34a");
        actual = AM.Billing.Claims.RAP_Worksheet.OccurrenceDate7(driver).getAttribute("value");
        isGood = actual.startsWith(expected);
        Assert.assertTrue(isGood);

        expected = Datatable.GetValue("oCode34b");
        actual = AM.Billing.Claims.RAP_Worksheet.GetOccurrenceCode(driver, "occurenceCode8").getText();
        isGood = actual.startsWith(expected);
        Assert.assertTrue(isGood);

        expected = Datatable.GetValue("oDate34b");
        actual = AM.Billing.Claims.RAP_Worksheet.OccurrenceDate8(driver).getAttribute("value");
        isGood = actual.startsWith(expected);
        Assert.assertTrue(isGood);
    }

    public static void CheckOccurrenceSpanCodes(WebDriver driver, String dataFileName, String dataPageName) throws Exception {
        //OccurrenceSpanCodes
        String expected = "";
        String actual = "";
        boolean isGood = false;
        Datatable.loadDataSheet(dataFileName, dataPageName);
        expected = Datatable.GetValue("osCode35");
        actual = AM.Billing.Claims.UB04_Worksheet.GetOccurrenceCode(driver, "occurenceSpanCode1").getText();
        isGood = actual.startsWith(expected);
        Assert.assertTrue(isGood);

        expected = Datatable.GetValue("osStartDate35");
        actual = AM.Billing.Claims.UB04_Worksheet.OccurrenceSpanDateFrom1(driver).getAttribute("value");
        isGood = actual.startsWith(expected);
        Assert.assertTrue(isGood);

        expected = Datatable.GetValue("osEndDate35");
        actual = AM.Billing.Claims.UB04_Worksheet.OccurrenceSpanDateThrough1(driver).getAttribute("value");
        isGood = actual.startsWith(expected);
        Assert.assertTrue(isGood);

        expected = Datatable.GetValue("osCode36");
        actual = AM.Billing.Claims.UB04_Worksheet.GetOccurrenceCode(driver, "occurenceSpanCode2").getText();
        isGood = actual.startsWith(expected);
        Assert.assertTrue(isGood);

        expected = Datatable.GetValue("osStartDate36");
        actual = AM.Billing.Claims.UB04_Worksheet.OccurrenceSpanDateFrom2(driver).getAttribute("value");
        isGood = actual.startsWith(expected);
        Assert.assertTrue(isGood);

        expected = Datatable.GetValue("osEndDate36");
        actual = AM.Billing.Claims.UB04_Worksheet.OccurrenceSpanDateThrough2(driver).getAttribute("value");
        isGood = actual.startsWith(expected);
        Assert.assertTrue(isGood);
    }

    public static void CheckRemarks(WebDriver driver, String dataFileName, String dataPageName) throws Exception {
        //Remarks
        String expected = "";
        String actual = "";
        boolean isGood = false;
        Datatable.loadDataSheet(dataFileName, dataPageName);
        expected = Datatable.GetValue("remarks");
        actual = AM.Billing.Claims.RAP_Worksheet.txt_Remarks(driver).getAttribute("value");
        Assert.assertEquals(actual, expected);
    }

    public static void CheckConditionCodesAreBlank(WebDriver driver) {
        //Check condition codes
        String actual = "";
        boolean isGood = false;
        for (int i = 0; i < 11; i++) {
            int spreadSheetConditionCodeIndex = i + 18;
            int workSheetConditionCodeIndex = i + 1;
            actual = AM.Billing.Claims.RAP_Worksheet.GetConditionCode(driver, "conditionCode" + workSheetConditionCodeIndex).getText();
            isGood = actual.isEmpty();
            Assert.assertTrue(isGood);
        }
    }

    public static void CheckOccurrenceCodesAreBlank(WebDriver driver) {
        //OccurrenceCodes
        String actual = "";
        boolean isGood = false;
        actual = AM.Billing.Claims.RAP_Worksheet.GetOccurrenceCode(driver, "occurenceCode1").getText();
        isGood = actual.isEmpty();
        Assert.assertTrue(isGood);

        actual = AM.Billing.Claims.RAP_Worksheet.OccurrenceDate1(driver).getAttribute("value");
        isGood = actual.isEmpty();
        Assert.assertTrue(isGood);

        actual = AM.Billing.Claims.RAP_Worksheet.GetOccurrenceCode(driver, "occurenceCode2").getText();
        isGood = actual.isEmpty();
        Assert.assertTrue(isGood);

        actual = AM.Billing.Claims.RAP_Worksheet.OccurrenceDate2(driver).getAttribute("value");
        isGood = actual.isEmpty();
        Assert.assertTrue(isGood);

        actual = AM.Billing.Claims.RAP_Worksheet.GetOccurrenceCode(driver, "occurenceCode3").getText();
        isGood = actual.isEmpty();
        Assert.assertTrue(isGood);

        actual = AM.Billing.Claims.RAP_Worksheet.OccurrenceDate3(driver).getAttribute("value");
        isGood = actual.isEmpty();
        Assert.assertTrue(isGood);

        actual = AM.Billing.Claims.RAP_Worksheet.GetOccurrenceCode(driver, "occurenceCode4").getText();
        isGood = actual.isEmpty();
        Assert.assertTrue(isGood);

        actual = AM.Billing.Claims.RAP_Worksheet.OccurrenceDate4(driver).getAttribute("value");
        isGood = actual.isEmpty();
        Assert.assertTrue(isGood);

        actual = AM.Billing.Claims.RAP_Worksheet.GetOccurrenceCode(driver, "occurenceCode5").getText();
        isGood = actual.isEmpty();
        Assert.assertTrue(isGood);

        actual = AM.Billing.Claims.RAP_Worksheet.OccurrenceDate5(driver).getAttribute("value");
        isGood = actual.isEmpty();
        Assert.assertTrue(isGood);

        actual = AM.Billing.Claims.RAP_Worksheet.GetOccurrenceCode(driver, "occurenceCode6").getText();
        isGood = actual.isEmpty();
        Assert.assertTrue(isGood);

        actual = AM.Billing.Claims.RAP_Worksheet.OccurrenceDate6(driver).getAttribute("value");
        isGood = actual.isEmpty();
        Assert.assertTrue(isGood);

        actual = AM.Billing.Claims.RAP_Worksheet.GetOccurrenceCode(driver, "occurenceCode7").getText();
        isGood = actual.isEmpty();
        Assert.assertTrue(isGood);

        actual = AM.Billing.Claims.RAP_Worksheet.OccurrenceDate7(driver).getAttribute("value");
        isGood = actual.isEmpty();
        Assert.assertTrue(isGood);

        actual = AM.Billing.Claims.RAP_Worksheet.GetOccurrenceCode(driver, "occurenceCode8").getText();
        isGood = actual.isEmpty();
        Assert.assertTrue(isGood);

        actual = AM.Billing.Claims.RAP_Worksheet.OccurrenceDate8(driver).getAttribute("value");
        isGood = actual.isEmpty();
        Assert.assertTrue(isGood);
    }
}
