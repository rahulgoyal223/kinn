package APIScripts.PatientBilling;


import org.testng.Assert;
import org.testng.annotations.Test;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import API.CommonUtil;
import API.EnvVar;
import API.PatientBilling.Payment;

public class PB_Payment_UndoPayment {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			CallUndoPaymentTest();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public static void CallUndoPaymentTest() throws Exception {
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
		
		// Testing Undo Payment method.
		String responseOfDeleteAdjustment = payment.DeleteAdjustment(AdjustmentId);
		Assert.assertEquals(responseOfDeleteAdjustment, "null");
		
		System.out.println("PB_Payment_UndoPayment Test Passed successfully.");
	}

}
