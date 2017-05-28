package DataSource;

import com.aventstack.extentreports.Status;

import components.Report;

public class GlobalData {

	/* Default Project Setup global data values */
	private static String patientFirstName = "";
	private static String patientLastName = "";
	private static String physicianFirstName = "";
	private static String physicianLastName = "";
	private static String patientFullName = "";
	private static String patientMRNumber = "";
	private static String claimNumber = "";
	private static String patientMiddleInitial = "";
	private static String remittanceNumber = "";
	private static int currentDataRow = 1;
	private static String authorizationNumber = "";
	private static String insuranceName = "";
	private static String insuranceAbbreviation = "";
	private static String lastName = "";
	private static String firstName = "";
	private static String userName = "";
	private static String temporaryPwd = "";
	private static String emailAddress = "";
	private static String loginCredentials = "";
	private static String addressOne = "";
	private static String addressTwo = "";
	private static String zipCode = "";
	private static String payerId = "";
	private static String physicianUpin = "";
	private static String physicianNpi = "";
	private static String physicianLicense = "";
	private static String physicianExpiration = "";
	private static String communityCare = "";
	private static String facilityName = "";
	private static String facilityNpi = "";
	private static String trainerName = "";
	private static String trainerPwd = "";
	private static String date = "";

	public static String getPatientFirstName() {
		Report.Log(Status.INFO, "Patient First Name '" + patientFirstName
				+ "' is read from global data configuration");
		return patientFirstName;
	}

	public static void setPatientFirstName(String patientFirstName) {
		GlobalData.patientFirstName = patientFirstName;
	}

	public static String getPatientLastName() {
		Report.Log(Status.INFO, "Patient Last Name '" + patientLastName
				+ "' is read from global data configuration");
		return patientLastName;
	}

	public static void setPatientLastName(String patientLastName) {
		GlobalData.patientLastName = patientLastName;
	}

	public static String getPhysicianFirstName() {
		Report.Log(Status.INFO, "Physician First Name '" + physicianFirstName
				+ "' is read from global data configuration");
		return physicianFirstName;
	}

	public static void setPhysicianFirstName(String physicianFirstName) {
		GlobalData.physicianFirstName = physicianFirstName;
	}

	public static String getPhysicianLastName() {
		Report.Log(Status.INFO, "Physician Last Name '" + physicianLastName
				+ "' is read from global data configuration");
		return physicianLastName;
	}

	public static void setPhysicianLastName(String physicianLastName) {
		GlobalData.physicianLastName = physicianLastName;
	}
	public static String getPatientFullName() {
		Report.Log(Status.INFO, "Patient Full name '" + patientFullName
				+ "' is read from global data configuration");
		return patientFullName;
	}

	public static void setPatientFullName(String patientFullName) {
		GlobalData.patientFullName = patientFullName;
	}

	public static String getPatientMRNumber() {
		Report.Log(Status.INFO, "Patient MRNumber '" + patientMRNumber
				+ "' is read from global data configuration");
		return patientMRNumber;
	}

	public static void setPatientMRNumber(String patientMRNumber) {
		GlobalData.patientMRNumber = patientMRNumber;
	}

	public static String getClaimNumer() {
		Report.Log(Status.INFO, "Patient Claim number '" + claimNumber
				+ "' is read from global data configuration");
		return claimNumber;
	}
	
	public static void setClaimNumer(String claimNumer) {
		GlobalData.claimNumber = claimNumer;
	}
	
	public static String getInsuranceName() {
		Report.Log(Status.INFO, "Insurance Name '" + insuranceName
				+ "' is read from global data configuration");
		return insuranceName;
	}

	public static void setInsuranceName(String insuranceName) {
		GlobalData.insuranceName = insuranceName;
	}

	public static String getInsuranceAbbreviation() {
		Report.Log(Status.INFO, "Insurance Abbreviation '" + insuranceAbbreviation
				+ "' is read from global data configuration");
		return insuranceAbbreviation;
	}

	public static void setInsuranceAbbreviation(String insuranceAbbreviation) {
		GlobalData.insuranceAbbreviation = insuranceAbbreviation;
	}

	public static String getPatientMiddleInitial() {
		return patientMiddleInitial;
	}

	public static void setPatientMiddleInitial(String patientMiddleInitial) {
		GlobalData.patientMiddleInitial = patientMiddleInitial;
	}

	public static String getRemittanceNumber() {
		return remittanceNumber;
	}

	public static void setRemittanceNumber(String remittanceNumber) {
		GlobalData.remittanceNumber = remittanceNumber;
	}

	public static int getCurrentDataRow() {
		return currentDataRow;
	}

	public static void setCurrentDataRow(int currentDataRow) {
		GlobalData.currentDataRow = currentDataRow;
	}

	public static String getAuthorizationNumber() {
		return authorizationNumber;
	}

	public static void setAuthorizationNumber(String authorizationNumber) {
		GlobalData.authorizationNumber = authorizationNumber;
	}
	
	public static String getLastName() {
		return lastName;
	}

	public static void setLastName(String lastName) {
		GlobalData.lastName = lastName;
	}
	
	public static String getFirstName() {
		return firstName;
	}

	public static void setFirstName(String firstName) {
		GlobalData.firstName = firstName;
	}
	
	public static String getUserName() {
		return userName;
	}

	public static void setUserName(String userName) {
		GlobalData.userName = userName;
	}
	
	public static String getTemporaryPwd() {
		return temporaryPwd;
	}

	public static void setTemporaryPwd(String temporaryPwd) {
		GlobalData.temporaryPwd = temporaryPwd;
	}
	
	public static String getEmailAddress() {
		return emailAddress;
	}

	public static void setEmailAddress(String emailAddress) {
		GlobalData.emailAddress = emailAddress;
	}
	
	public static String getAddressOne() {
		return addressOne;
	}

	public static void setAddressOne(String addressOne) {
		GlobalData.addressOne = addressOne;
	}
	
	public static String getAddressTwo() {
		return addressTwo;
	}

	public static void setAddressTwo(String addressTwo) {
		GlobalData.addressTwo = addressTwo;
	}
	
	public static String getZipCode() {
		return zipCode;
	}

	public static void setZipCode(String zipCode) {
		GlobalData.zipCode = zipCode;
	}
	
	public static String getPayerId() {
		return payerId;
	}

	public static void setPayerId(String payerId) {
		GlobalData.payerId = payerId;
	}
	
	public static String getPhysicianUpin() {
		return physicianUpin;
	}

	public static void setPhysicianUpin(String physicianUpin) {
		GlobalData.physicianUpin = physicianUpin;
	}
	
	public static String getPhysicianNpi() {
		return physicianNpi;
	}

	public static void setPhysicianNpi(String physicianNpi) {
		GlobalData.physicianNpi = physicianNpi;
	}
	
	public static String getPhysicianLicense() {
		return physicianLicense;
	}

	public static void setPhysicianLicense(String physicianLicense) {
		GlobalData.physicianLicense = physicianLicense;
	}	
	
	public static String getPhysicianExpiration() {
		return physicianExpiration;
	}

	public static void setPhysicianExpiration(String physicianExpiration) {
		GlobalData.physicianExpiration = physicianExpiration;
	}
	
	public static void setTrainerName(String trainerName) {
		GlobalData.trainerName = trainerName;
	}	
	
	public static String getTrainerName() {
		return trainerName;
	}
	
	public static void setTrainerPwd(String trainerPwd) {
		GlobalData.trainerPwd = trainerPwd;
	}	
	
	public static String getTrainerPwd() {
		return trainerPwd;
	}
	
	public static String getCommunityCare() {
		return communityCare;
	}

	public static void setCommunityCare(String communityCare) {
		GlobalData.communityCare = communityCare;
	}
	
	public static String getDate() {
		return date;
	}

	public static void setDate(String date) {
		GlobalData.date = date;
	}
	
	public static String getLoginCredentials() {
		Report.Log(Status.INFO, "Login Details '" + loginCredentials
				+ "' is read from global data configuration");
		return loginCredentials;
	}

	public static void setLoginCredentials(String loginCredentials) {
		GlobalData.loginCredentials = loginCredentials;
	}
	
	public static String getFacilityName() {
		Report.Log(Status.INFO, "Facility Name '" + facilityName
				+ "' is read from global data configuration");
		return facilityName;
	}

	public static void setFacilityName(String facilityName) {
		GlobalData.facilityName = facilityName;
	}
	
	public static String getFacilityNpi() {
		Report.Log(Status.INFO, "Facility Npi '" + facilityNpi
				+ "' is read from global data configuration");
		return facilityNpi;
	}

	public static void setFacilityNpi(String facilityNpi) {
		GlobalData.facilityNpi = facilityNpi;
	}
}

