package API;

public class EnvVar {
	
	//if shouldShuffle is true, then the elements in the retrieved items from get calls are shuffled
    public static String hostname = "localhost:2392";
    public static String ClinicKey = "323";
    public static String ClinicBranchKey = "2332";
    public static String PBUnbilledItemsFilePath = "src/test/resources/testdata/PB_UnbilledItems.txt";
    public static String PBAdjustmentFilePath = "src/test/resources/testdata/PB_Adjustment.txt";
    public static String PBTestToken = "bcd21a6f-ca41-4c65-ae8c-fc47a9fe7f24";
    
    // base uris
    public static String Baseuri = "http://" + hostname;
    public static String Invoicebaseuri = Baseuri + "/Clinic/" + ClinicKey + "/ClinicBranch/" + ClinicBranchKey + "/";
    public static String Clinicbaseuri = Baseuri + "/Clinic/" + ClinicKey + "/ClinicBranch/" + ClinicBranchKey + "/";
    public static String Paymentbaseuri = Baseuri + "/Clinic/" + ClinicKey + "/ClinicBranch/" + ClinicBranchKey + "/";
    public static String Patientbaseuri = Baseuri + "/Clinic/" + ClinicKey + "/ClinicBranch/" + ClinicBranchKey + "/";
	
}
