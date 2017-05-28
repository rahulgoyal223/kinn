package components;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Properties;

public class Config {

    private static String filenameLocal = "config.local.properties";
    private static String filenameJenkins = System.getProperty("configJenkins");
    private static final Properties config;

    static {
    	Properties fallback = new Properties();
    
        /* Default Project Setup global configuration values */
    	fallback.put("reportsPath", new File("").getAbsolutePath() + "\\ATFReports\\");
        fallback.put("screenShotsPath", new File("").getAbsolutePath() + "\\ATFReports\\Screenshots\\");
        fallback.put("driversPath", "C:\\workspaces\\kinnser\\selenium\\sdrivers\\");
        fallback.put("sourcePath", new File("src\\.").getAbsolutePath());
        fallback.put("testSuite", "0");
        fallback.put("testReportType", "Console");

        /* Selenium Grid Configurations */
        fallback.put("gridExecution", "0");
        fallback.put("gridHubAddress", "http://10.20.0.26:4444/wd/hub");

        /* Default Browser Credentials */
        fallback.put("appUrl", "https://develop.kinnser.net/");
        fallback.put("browserType", "chrome");

        /* AM Default Browser and Application Credentials */
        fallback.put("appUserName", "uber.user.bill2");
        fallback.put("appPassword", "mma4uber");

        /* Default KH Application Credentials */
        fallback.put("appUserNameKH", "uber.user.hm");
        fallback.put("appPasswordKH", "mma4uber");
        
        /* Default Calltrack Application Credentials */
        fallback.put("appUserNameCalltrack", "KQA.Auto");
        fallback.put("appPasswordCalltrack", "Aut0m@teIt");

        /* Default reports file name configuration */
        fallback.put("testCycle", "REG");
        fallback.put("testEnvironment", "Develop");

        /* Database connection info */
        fallback.put("dbserver", "AUS-DEV-DB001");
        fallback.put("dbuser", "cfUserAuto");
        fallback.put("dbpassword", "1b2h3s4D");
    	fallback.put("dbname", "Develop");

    	config = new Properties(fallback);
    	 	
    	File configFile =  new File( filenameLocal );
		if( configFile.exists() && configFile.isFile() ){
	    	loadConfig( filenameLocal );
		}else { 
			loadConfig( filenameJenkins );
		}

		Enumeration<?> e = config.propertyNames();
		while (e.hasMoreElements()) {
			String key = (String) e.nextElement();
			String value = config.getProperty(key);
			System.out.println( key + " = " + value);
		}
    }
    
    private static void loadConfig ( String filename ){
    	try {
    		// load default configuration
    		InputStream stream = new FileInputStream( filename );
    		try {

    			config.load(stream);
    	    	System.out.println("Configuration loaded from  " + filename);
    		}
        	catch (Exception ex) {
        		/* Handle exception. */
        		ex.printStackTrace();
        	}
    		finally {
    			stream.close();
    		}
    	}
    	catch (IOException ex) {
    		/* Handle exception. */
    		System.out.println("Unable to find default configuration file " + new File("").getAbsolutePath() + "\\" + filename);
    	}
    }
    
    //@ Getters & Setters
    public static String getReportsPath() {
        return config.getProperty("reportsPath");
    }

    public static void setReportsPath(String reportsPath) {
        config.put("reportsPath", reportsPath);
    }

    public static String getScreenShotsPath() {
        return config.getProperty("screenShotsPath");
    }

    public static void setScreenShotsPath(String screenShotsPath) {
        config.put("screenShotsPath", screenShotsPath);
    }

    public static String getSourcePath() {
        return config.getProperty("sourcePath");
    }

    public static void setSourcePath(String sourcePath) {
        config.put("sourcePath", sourcePath);
    }

    public static boolean isTestSuite() {
        return config.getProperty("testSuite").equalsIgnoreCase("1");
    }

    public static void setTestSuite(boolean testSuite) {
        config.put("testSuite", testSuite ? "1" : "0");
    }

    public static String getTestReportType() {
        return config.getProperty("testReportType");
    }

    public static void setTestReportType(String testReportType) {
        config.put("testReportType", testReportType);
    }

    public static String getAppUserName() {
        return config.getProperty("appUserName");
    }

    public static void setAppUserName(String appUserName) {
        config.put("appUserName", appUserName);
    }

    public static String getAppPassword() {
        return config.getProperty("appPassword");
    }

    public static String getAppUserKey() {
        return config.getProperty("appUserKey");
    }
    
    public static void setAppPassword(String appPassword) {
        config.put("appPassword", appPassword);
    }

    public static String getAppUrl() {
        return config.getProperty("appUrl");
    }

    public static void setAppUrl(String appUrl) {
        config.put("appUrl", appUrl);
    }

    public static String getBrowserType() {
        return config.getProperty("browserType");
    }

    public static void setBrowserType(String browserType) {
        config.put("browserType", browserType);
    }

    public static String getDriversPath() {
        return config.getProperty("driversPath");
    }

    public static void setDriversPath(String driversPath) {
        config.put("driversPath", driversPath);
    }

    public static boolean isGridExecution() {
        return config.getProperty("gridExecution").equalsIgnoreCase("1");
    }

    public static void setGridExecution(boolean gridExecution) {
        config.put("gridExecution", gridExecution ? "1" : "0" );
    }

    public static String getGridHubAddress() {
        return config.getProperty("gridHubAddress");
    }

    public static void setGridHubAddress(String gridHubAddress) {
        config.put("gridHubAddress", gridHubAddress);
    }
    
	public static String getTestCycle() {
		return config.getProperty("testCycle");
	}

	public static void setTestCycle(String testCycle) {
		config.put("testCycle", testCycle);
	}

	public static String getTestEnvironment() {
		return config.getProperty("testEnvironment");
	}

	public static void setTestEnvironment(String testEnvironment) {
		config.put("testEnvironment", testEnvironment);
	}
	
	public static String getDBServer() {
		return config.getProperty("dbserver");
	}
	
	public static String getDBUser() {
		return config.getProperty("dbuser");
	}
	
	public static String getDBPassword() {
		return config.getProperty("dbpassword");
	}
	
	public static String getDBName() {
		return config.getProperty("dbname");
	}

	public static String getAppPasswordKH() {
		return config.getProperty("appPasswordKH");
	}

	public static void setAppPasswordKH(String appPasswordKH) {
		config.put("appPasswordKH", appPasswordKH);
	}

	public static String getAppUserNameKH() {
		return config.getProperty("appUserNameKH");
	}

	public static void setAppUserNameKH(String appUserNameKH) {
		config.put("appUserNameKH", appUserNameKH);
	}
	
	public static String getAppUserNameCalltrack() {
		return config.getProperty("appUserNameCalltrack");
	}

	public static void setAppUserNameCalltrack(String appUserNameCalltrack) {
		config.put("appUserNameCalltrack", appUserNameCalltrack);
	}
	
	public static String getAppPasswordCalltrack() {
		return config.getProperty("appPasswordCalltrack");
	}

	public static void setAppPasswordCalltrack(String appPasswordCalltrack) {
		config.put("appPasswordCalltrack", appPasswordCalltrack);
	}
}
