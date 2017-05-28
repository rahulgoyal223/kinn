package Database;

import java.sql.*;
import java.util.List;

public class Queries{
	
	public static String[][] getDBInsuranceList() throws SQLException {
		String query = "exec SPGetInsuranceList 175,0,125";
		String[][] queryResult = DBConnection.executeQuery(query);
		//while
		return queryResult;
	}
	
	public static void enableQAPIRestrictedClinicSetting() throws SQLException {
		String query = "if not exists (select 1 from ClinicSetting where fCLinicKey = 3860 and fListSettingKey = 110) begin Insert into ClinicSetting (fListSettingKey, fCLinicKey, Value, SettingValue) Values (110, 3860, null, null) end";
		DBConnection.executeQueryWithNoResults(query);

	}
}