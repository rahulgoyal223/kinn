package APIScripts.PatientBilling;

import java.util.Objects;



import org.testng.Assert;
import org.testng.annotations.Test;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import API.CommonUtil;
import API.EnvVar;
import API.PatientBilling.Payment;

public class PB_Payment_FullPayment {

	public static void main(String[] args) {
		try {
			CallFullPaymentTest();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public static void CallFullPaymentTest() throws Exception {
		Payment payment = new Payment();
		String jData = "";	
		
		// Step1: Let's 1st read file from fileSystem
		// PB_Adjustment.txt path here			
		jData = CommonUtil.ReadJson(EnvVar.PBAdjustmentFilePath);
		Assert.assertNotNull(jData);
		
		// Testing Adding Adjustment method.		
		String addedAdjustmentResponse = payment.AddAdjustment(jData);
		JsonObject jObject = new JsonParser().parse(addedAdjustmentResponse).getAsJsonObject();
		String AdjustmentId = jObject.get("AdjustmentId").getAsString();
		String InvoiceId = jObject.get("InvoiceId").getAsString();
		Assert.assertNotNull(InvoiceId); 
		Assert.assertNotNull(AdjustmentId); 		
		
		// Testing Get Methods.
		String testResponse = "failed";
		String responseByInvoiceId = payment.GetByInvoiceId(InvoiceId);
		String responseByAdjustmentId = payment.GetByAdjustmentId(AdjustmentId);
		JsonArray adjustmentArray = (JsonArray)new JsonParser().parse(responseByInvoiceId);
		for(int i = 0; i < adjustmentArray.size(); i++) {
			Object object = adjustmentArray.get(i);
			JsonObject aJson = (JsonObject) object;		
			String retAdjustmentId = aJson.get("AdjustmentId").getAsString();
			if(Objects.equals(retAdjustmentId,AdjustmentId)) {
				testResponse = "success";
				boolean AreEqualJsonStrings = CommonUtil.AreJsonStringsEqual(addedAdjustmentResponse, aJson.toString(), null);
				Assert.assertEquals(AreEqualJsonStrings, true);
			}
		}
		Assert.assertEquals(testResponse, "success");
		
		String retInvoiceId = new JsonParser().parse(responseByAdjustmentId).getAsJsonObject().get("InvoiceId").getAsString();
		Assert.assertEquals(retInvoiceId, InvoiceId);
		
		//check for equality
		boolean AreEqualJsonStrings = CommonUtil.AreJsonStringsEqual(addedAdjustmentResponse, responseByInvoiceId, null);
		Assert.assertEquals(AreEqualJsonStrings, true);
		
		System.out.println("PB_Payment_FullPayment Test Passed successfully.");
	}
}
