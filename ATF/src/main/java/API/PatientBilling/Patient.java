package API.PatientBilling;

import API.EnvVar;
import API.WebRequestUtil;

public class Patient {
	
	/* Http Get Methods */
	
	public String GetPatientByRefKey()
	{
		WebRequestUtil webReq = new WebRequestUtil();
		String endpoint = EnvVar.Patientbaseuri;
        String response = "";
		try {
			response = webReq.requestBuilderTypeGetJson(EnvVar.PBTestToken, endpoint);
		} catch (Exception e) {			
			e.printStackTrace();
		}
		return response;
	}
	
	public String GetPatient(String PatientId)
	{
		WebRequestUtil webReq = new WebRequestUtil();
		String endpoint = EnvVar.Baseuri + "/Patient/" + PatientId;
        String response = "";
		try {
			response = webReq.requestBuilderTypeGetJson(EnvVar.PBTestToken, endpoint);
		} catch (Exception e) {			
			e.printStackTrace();
		}
		return response;
	}
	
	/* End of Http Get Methods */
	
	/* Http Post Methods */
	
	public String CreatePatient(String jsonPatientData)
	{
		WebRequestUtil webReq = new WebRequestUtil();
        String endpoint = EnvVar.Patientbaseuri + "Patient";
        String response = "";
		try {
			response = webReq.requestBuilderTypePostJson(EnvVar.PBTestToken,jsonPatientData,endpoint);
		} catch (Exception e) {			
			e.printStackTrace();
		}
		return response;
	}
	
	/* End of Http Post Methods */
	
	/* Http Put Methods */
	
	public String UpdatePatient(String jsonPatientData)
	{
		WebRequestUtil webReq = new WebRequestUtil();
        String endpoint = EnvVar.Patientbaseuri + "Patient";
        String response = "";
		try {
			response = webReq.requestBuilderTypePutJson(EnvVar.PBTestToken,jsonPatientData,endpoint);
		} catch (Exception e) {			
			e.printStackTrace();
		}
		return response;
	}
	
	/* End of Http Put Methods */
}
