package Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import components.Config;

public class DBConnection {
    private static String connectionUrl = "jdbc:sqlserver://" + Config.getDBServer() + ";user=" + Config.getDBUser() + ";password=" + Config.getDBPassword() 
    		+ ";databaseName=" + Config.getDBName();
    private static Connection db = null;

    public static Connection db() throws SQLException {
        db = DriverManager.getConnection(connectionUrl);
        return db;
    }
    
    public static String[][] executeQuery(String query) throws SQLException{
    	// open the connection and two statements for the query and the row count
		Connection con = db();
		Statement stmt = con.createStatement();
		Statement stmtCount = con.createStatement();
		
		// execute the queries for the data and the row count
		ResultSet rs = stmt.executeQuery(query);
		ResultSet rsCount = stmtCount.executeQuery("select @@rowcount;");
		
		// get the array sizes
		ResultSetMetaData rsmd = rs.getMetaData();
		rsCount.next();
		int rowCount = Integer.parseInt(rsCount.getString(1));
		int columnCount = rsmd.getColumnCount();
		
		// create the array
		String[][] queryArray = new String[rowCount][columnCount];
		
		// iterator for the result set
		int rownum = 0;
		
		// create the array from the result set
    	while(rs.next()){
    		for(int i=1;i<=rsmd.getColumnCount();i++){
    			//rsrow.add(rs.getString(i))
    			queryArray[rownum][i-1] = rs.getString(i);
    		}
	    	rownum++;
    	}
    	
    	// close all of the connections
    	rs.close();
    	rsCount.close();
    	stmt.close();
    	stmtCount.close();
    	con.close();
    	return queryArray;
    }
    
    public static void executeQueryWithNoResults(String query) throws SQLException{
    	// open the connection and execute query
		Connection con = db();
		Statement stmt = con.createStatement();
		stmt.executeUpdate(query);
		stmt.close();
		con.close();

    }
}
