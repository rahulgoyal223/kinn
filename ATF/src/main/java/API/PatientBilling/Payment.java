package API.PatientBilling;

import API.EnvVar;
import API.WebRequestUtil;

public class Payment {
	
	/* Http Post Methods */	
	public String AddAdjustment(String adjustmentData)
	{
		WebRequestUtil webReq = new WebRequestUtil();
		String endpoint = EnvVar.Paymentbaseuri + "Payment";
        String response = "";
		try {
			response = webReq.requestBuilderTypePostJson(EnvVar.PBTestToken, adjustmentData, endpoint);
		} catch (Exception e) {			
			e.printStackTrace();
		}
		return response;
	}
	/* End of Http Post Methods */
	
    /* Http Get Methods */	
	public String GetByInvoiceId(String InvoiceId)
	{
		WebRequestUtil webReq = new WebRequestUtil();
		String endpoint = EnvVar.Paymentbaseuri + "Payment/InvoiceId/" + InvoiceId;
        String response = "";
		try {
			response = webReq.requestBuilderTypeGetJson(EnvVar.PBTestToken, endpoint);
		} catch (Exception e) {			
			e.printStackTrace();
		}
		return response;
	}
	
	public String GetByAdjustmentId(String AdjustmentId)
	{
		WebRequestUtil webReq = new WebRequestUtil();
		String endpoint = EnvVar.Paymentbaseuri + "Payment/" + AdjustmentId;
        String response = "";
		try {
			response = webReq.requestBuilderTypeGetJson(EnvVar.PBTestToken, endpoint);
		} catch (Exception e) {			
			e.printStackTrace();
		}
		return response;
	}
	/* End of Http Get Methods */
	
	/* Http Put Methods */	
	public String DeleteAdjustment(String adjustmentId)
	{
		WebRequestUtil webReq = new WebRequestUtil();
		String endpoint = EnvVar.Paymentbaseuri + "Payment/Undo/" + adjustmentId;
        String response = "";
		try {
			response = webReq.requestBuilderTypePutJson(EnvVar.PBTestToken, adjustmentId, endpoint);
		} catch (Exception e) {			
			e.printStackTrace();
		}
		return response;
	}
	/* End of Http Put Methods */
	
}
