package Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import java.sql.*;
import java.util.List;


public class BillingDatabaseInteraction {
	
	public static boolean checkEpisodeadjustments(String claimtransactionkey) throws SQLException {
        int numRecords = 0;
        Connection db = DBConnection.db();
        String query = "select COUNT(AdjustmentAmount) As NumRecords from ClaimTransactionLineItemAdjustment where fClaimTransactionLineItemKey in (select ClaimTransactionLineItemKey from ClaimTransactionLineItem where fClaimTransactionKey = ?)";
        PreparedStatement stmt = db.prepareStatement(query);
        stmt.setString(1, String.valueOf(claimtransactionkey));
        ResultSet  resultSet = stmt.executeQuery();
        if(resultSet.next())
        {
            numRecords = resultSet.getInt("numRecords");
        }
        stmt.close();
        return numRecords > 0;
    }
	
	
	

}