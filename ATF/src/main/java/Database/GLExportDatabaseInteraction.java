package Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by austin.ledbetter on 1/26/2017.
 */
public class GLExportDatabaseInteraction {
    public static void populateGLCodeTasks(int corporationKey) throws SQLException {
        Connection db = DBConnection.db();
        String query = "DELETE \n" +
                "  FROM GLCodeTask \n" +
                " WHERE fCorporationKey = ?\n" +
                "\n" +
                "-- TODO: Set parameter values here.\n" +
                "IF OBJECT_ID('tempdb..#Tasks') IS NOT NULL\n" +
                "    DROP TABLE #Tasks\n" +
                "    \n" +
                "CREATE TABLE #Tasks\n" +
                "(\n" +
                "    Billable BIT NULL,\n" +
                "    ClinicTaskActive BIT NULL,\n" +
                "    fTaskKey INT NULL,\n" +
                "    TaskName VARCHAR(100) NULL,\n" +
                "\tClinicType VARCHAR(2),\n" +
                "    TaskType VARCHAR(100) NULL,\n" +
                "    PayerTypeKey INT NULL,\n" +
                "    Adjustments_ClaimCorrection VARCHAR(10) NULL,\n" +
                "    Adjustments_Contractual VARCHAR(10) NULL,\n" +
                "\tAdjustments_Episodic VARCHAR(10) NULL,\n" +
                "\tAdjustments_PriorPeriod VARCHAR(10) NULL,\n" +
                "\tAdjustments_RemittancePost VARCHAR(10) NULL,\n" +
                "\tAR VARCHAR(10) NULL,\n" +
                "\tPayments VARCHAR(10) NULL,\n" +
                "\tRevenue VARCHAR(10) NULL,\n" +
                "\tForeignVisitType_Adjustments_ClaimCorrection VARCHAR(10) NULL,\n" +
                "    ForeignVisitType_Adjustments_Contractual VARCHAR(10) NULL,\n" +
                "\tForeignVisitType_Adjustments_Episodic VARCHAR(10) NULL,\n" +
                "\tForeignVisitType_Adjustments_PriorPeriod VARCHAR(10) NULL,\n" +
                "\tForeignVisitType_Adjustments_RemittancePost VARCHAR(10) NULL,\n" +
                "\tForeignVisitType_AR VARCHAR(10) NULL,\n" +
                "\tForeignVisitType_Payments VARCHAR(10) NULL,\n" +
                "\tForeignVisitType_Revenue VARCHAR(10) NULL,\n" +
                "\tCorpClinicActive BIT NULL\n" +
                ")\n" +
                "INSERT INTO #Tasks\n" +
                "EXECUTE [SPGetGLCodeTasks] \n" +
                "   @CorporationKey = ?\n" +
                "   \n" +
                "   \n" +
                "INSERT INTO [GLCodeTask]\n" +
                "           ([fCorporationKey]\n" +
                "           ,[fTaskKey]\n" +
                "           ,[TaskName]\n" +
                "           ,[fListGLCodeAccountTypeKey]\n" +
                "           ,[GLCode]\n" +
                "           ,[Active]\n" +
                "           ,[ModDate]\n" +
                "           ,[fUsersKey]\n" +
                "           ,[isDirty]\n" +
                "\t\t   ,[fPayerTypeKey]\n" +
                "\t\t   ,[ForeignVisitType]\n" +
                "\t\t   ,[ClinicType])\n" +
                "SELECT ?,\n" +
                "       t.fTaskKey,\n" +
                "       t.TaskName,\n" +
                "       gat.ListGLCodeAccountTypeKey,\n" +
                "       CONVERT(VARCHAR(10), t.fTaskKey) + CONVERT(VARCHAR(10), gat.ListGLCodeAccountTypeKey) As GLCode,\n" +
                "       1 As Active,\n" +
                "       GETDATE() As ModDate,\n" +
                "       -1 As fUsersKey,\n" +
                "       0 As isDirty,\n" +
                "\t     t.PayerTypeKey,\n" +
                "\t     'FVT' +CONVERT(VARCHAR(10), t.fTaskKey) + CONVERT(VARCHAR(10), gat.ListGLCodeAccountTypeKey) As ForeignVisitType,\n" +
                "\t     ClinicType\n" +
                "  FROM #Tasks t\n" +
                "  CROSS APPLY ListGLCodeAccountType gat";
        PreparedStatement stmt = db.prepareStatement(query);
        stmt.setString(1, String.valueOf(corporationKey));
        stmt.setString(2, String.valueOf(corporationKey));
        stmt.setString(3, String.valueOf(corporationKey));
        stmt.execute();
        stmt.close();
    }

    public static void populateGLCodeLevelOfCare(int corporationKey) throws SQLException {
        Connection db = DBConnection.db();
        String query = "DELETE \n" +
                "  FROM GLCodeLevelOfCare\n" +
                " WHERE fCorporationKey = ?\n" +
                "\n" +
                "-- TODO: Set parameter values here.\n" +
                "IF OBJECT_ID('tempdb..#Tasks') IS NOT NULL\n" +
                "    DROP TABLE #Tasks\n" +
                "    \n" +
                "CREATE TABLE #Tasks\n" +
                "(\n" +
                "\tListLevelOfCareKey INT NULL,\n" +
                "    LevelofCare VARCHAR(100) NULL,\n" +
                "    PayerTypeKey INT NULL,\n" +
                "    Adjustments_ClaimCorrection VARCHAR(10) NULL,\n" +
                "    Adjustments_Contractual VARCHAR(10) NULL,\n" +
                "\tAdjustments_Episodic VARCHAR(10) NULL,\n" +
                "\tAdjustments_PriorPeriod VARCHAR(10) NULL,\n" +
                "\tAdjustments_RemittancePost VARCHAR(10) NULL,\n" +
                "\tAR VARCHAR(10) NULL,\n" +
                "\tPayments VARCHAR(10) NULL,\n" +
                "\tRevenue VARCHAR(10) NULL,\n" +
                "\tForeignVisitType_Adjustments_ClaimCorrection VARCHAR(10) NULL,\n" +
                "    ForeignVisitType_Adjustments_Contractual VARCHAR(10) NULL,\n" +
                "\tForeignVisitType_Adjustments_Episodic VARCHAR(10) NULL,\n" +
                "\tForeignVisitType_Adjustments_PriorPeriod VARCHAR(10) NULL,\n" +
                "\tForeignVisitType_Adjustments_RemittancePost VARCHAR(10) NULL,\n" +
                "\tForeignVisitType_AR VARCHAR(10) NULL,\n" +
                "\tForeignVisitType_Payments VARCHAR(10) NULL,\n" +
                "\tForeignVisitType_Revenue VARCHAR(10) NULL,\n" +
                "\tCostCenter_Adjustments_ClaimCorrection VARCHAR(10) NULL,\n" +
                "    CostCenter_Adjustments_Contractual VARCHAR(10) NULL,\n" +
                "\tCostCenter_Adjustments_Episodic VARCHAR(10) NULL,\n" +
                "\tCostCenter_Adjustments_PriorPeriod VARCHAR(10) NULL,\n" +
                "\tCostCenter_Adjustments_RemittancePost VARCHAR(10) NULL,\n" +
                "\tCostCenter_AR VARCHAR(10) NULL,\n" +
                "\tCostCenter_Payments VARCHAR(10) NULL,\n" +
                "\tCostCenter_Revenue VARCHAR(10) NULL\n" +
                ")\n" +
                "INSERT INTO #Tasks\n" +
                "EXECUTE SPGetGLCodeListLevelOfCare \n" +
                "   @CorporationKey = ?\n" +
                "   \n" +
                "\n" +
                "INSERT INTO GLCodeLevelOfCare\n" +
                "           ([fCorporationKey]\n" +
                "           ,[fListLevelOfCareKey]\n" +
                "           ,[LevelOfCare]\n" +
                "           ,[fListGLCodeAccountTypeKey]\n" +
                "           ,[GLCode]\n" +
                "           ,[Active]\n" +
                "           ,[ModDate]\n" +
                "           ,[fUsersKey]\n" +
                "           ,[isDirty]\n" +
                "\t\t   ,[fPayerTypeKey]\n" +
                "\t\t   ,[ForeignVisitType]\n" +
                "\t\t   ,[CostCenter])\n" +
                "SELECT ?,\n" +
                "       t.ListLevelOfCareKey as fListLevelOfCareKey,\n" +
                "       t.LevelofCare,\n" +
                "       gat.ListGLCodeAccountTypeKey as fListGLCodeAccountTypeKey,\n" +
                "       CONVERT(VARCHAR(10), t.ListLevelOfCareKey) + CONVERT(VARCHAR(10), gat.ListGLCodeAccountTypeKey) As GLCode,\n" +
                "       1 As Active,\n" +
                "       GETDATE() As ModDate,\n" +
                "       -1 As fUsersKey,\n" +
                "       0 As isDirty,\n" +
                "\t     t.PayerTypeKey as fPayerTypeKey,\n" +
                "\t\t 'FVT' +CONVERT(VARCHAR(10), t.ListLevelOfCareKey) + CONVERT(VARCHAR(10), gat.ListGLCodeAccountTypeKey) As ForeignVisitType,\n" +
                "\t\t 'CC' +CONVERT(VARCHAR(10), t.ListLevelOfCareKey) + CONVERT(VARCHAR(10), gat.ListGLCodeAccountTypeKey) As CostCenter\n" +
                "  FROM #Tasks t\n" +
                "  CROSS APPLY ListGLCodeAccountType gat";
        PreparedStatement stmt = db.prepareStatement(query);
        stmt.setString(1, String.valueOf(corporationKey));
        stmt.setString(2, String.valueOf(corporationKey));
        stmt.setString(3, String.valueOf(corporationKey));
        stmt.execute();
        stmt.close();
    }

    public static void populateGLCodeGeneralLineItems(int corporationKey) throws SQLException {
        Connection db = DBConnection.db();
        String query = "DELETE \n" +
                "  FROM GLCodeGeneralLineItemType\n" +
                " WHERE fCorporationKey = ?\n" +
                "\n" +
                "-- TODO: Set parameter values here.\n" +
                "IF OBJECT_ID('tempdb..#Tasks') IS NOT NULL\n" +
                "    DROP TABLE #Tasks\n" +
                "    \n" +
                "CREATE TABLE #Tasks\n" +
                "(\n" +
                "    LineItemType VARCHAR(100) NULL,\n" +
                "\tLineItemTypeKey INT NULL,\n" +
                "    PayerTypeKey INT NULL,\n" +
                "    Adjustments_ClaimCorrection VARCHAR(10) NULL,\n" +
                "    Adjustments_Contractual VARCHAR(10) NULL,\n" +
                "\tAdjustments_Episodic VARCHAR(10) NULL,\n" +
                "\tAdjustments_PriorPeriod VARCHAR(10) NULL,\n" +
                "\tAdjustments_RemittancePost VARCHAR(10) NULL,\n" +
                "\tAdjustments_DeferredRevenue VARCHAR(10) NULL,\n" +
                "\tAR VARCHAR(10) NULL,\n" +
                "\tPayments VARCHAR(10) NULL,\n" +
                "\tRevenue VARCHAR(10) NULL,\n" +
                "    CostCenter_Adjustments_ClaimCorrection VARCHAR(10) NULL,\n" +
                "    CostCenter_Adjustments_Contractual VARCHAR(10) NULL,\n" +
                "\tCostCenter_Adjustments_Episodic VARCHAR(10) NULL,\n" +
                "\tCostCenter_Adjustments_PriorPeriod VARCHAR(10) NULL,\n" +
                "\tCostCenter_Adjustments_RemittancePost VARCHAR(10) NULL,\n" +
                "\tCostCenter_Adjustments_DeferredRevenue VARCHAR(10) NULL,\n" +
                "\tCostCenter_AR VARCHAR(10) NULL,\n" +
                "\tCostCenter_Payments VARCHAR(10) NULL,\n" +
                "\tCostCenter_Revenue VARCHAR(10) NULL,\n" +
                "\tForeignVisitType_Adjustments_ClaimCorrection VARCHAR(10) NULL,\n" +
                "    ForeignVisitType_Adjustments_Contractual VARCHAR(10) NULL,\n" +
                "\tForeignVisitType_Adjustments_Episodic VARCHAR(10) NULL,\n" +
                "\tForeignVisitType_Adjustments_PriorPeriod VARCHAR(10) NULL,\n" +
                "\tForeignVisitType_Adjustments_RemittancePost VARCHAR(10) NULL,\n" +
                "\tForeignVisitType_Adjustments_DeferredRevenue VARCHAR(10) NULL,\n" +
                "\tForeignVisitType_AR VARCHAR(10) NULL,\n" +
                "\tForeignVisitType_Payments VARCHAR(10) NULL,\n" +
                "\tForeignVisitType_Revenue VARCHAR(10) NULL,\n" +
                "\tClinicType VARCHAR(2)\n" +
                ")\n" +
                "INSERT INTO #Tasks\n" +
                "EXECUTE spgetglcodegenerallineitemtypes \n" +
                "   @CorporationKey = ?\n" +
                "   \n" +
                "\n" +
                "INSERT INTO GLCodeGeneralLineItemType\n" +
                "           ([fCorporationKey]\n" +
                "           ,[fListLineItemTypeKey]\n" +
                "           ,[LineItemType]\n" +
                "           ,[fListGLCodeAccountTypeKey]\n" +
                "           ,[GLCode]\n" +
                "           ,[Active]\n" +
                "           ,[ModDate]\n" +
                "           ,[fUsersKey]\n" +
                "           ,[isDirty]\n" +
                "\t\t   ,[fPayerTypeKey]\n" +
                "\t\t   ,[CostCenter]\n" +
                "\t\t   ,[ForeignVisitType]\n" +
                "\t\t   ,[ClinicType])\n" +
                "SELECT ?,\n" +
                "       t.LineItemTypeKey,\n" +
                "       t.LineItemType,\n" +
                "       gat.ListGLCodeAccountTypeKey,\n" +
                "       CONVERT(VARCHAR(10), t.LineItemTypeKey) + CONVERT(VARCHAR(10), gat.ListGLCodeAccountTypeKey) As GLCode,\n" +
                "       1 As Active,\n" +
                "       GETDATE() As ModDate,\n" +
                "       -1 As fUsersKey,\n" +
                "       0 As isDirty,\n" +
                "\t     t.PayerTypeKey as fPayerTypeKey,\n" +
                "\t\t 'CC' +CONVERT(VARCHAR(10), t.LineItemTypeKey) + CONVERT(VARCHAR(10), gat.ListGLCodeAccountTypeKey) + t.ClinicType As CostCenter,\n" +
                "\t\t 'FVT' +CONVERT(VARCHAR(10), t.LineItemTypeKey) + CONVERT(VARCHAR(10), gat.ListGLCodeAccountTypeKey) + t.ClinicType As ForeignVisitType,\n" +
                "\t\t ClinicType\n" +
                "  FROM #Tasks t\n" +
                "  CROSS APPLY ListGLCodeAccountType gat";
        PreparedStatement stmt = db.prepareStatement(query);
        stmt.setString(1, String.valueOf(corporationKey));
        stmt.setString(2, String.valueOf(corporationKey));
        stmt.setString(3, String.valueOf(corporationKey));
        stmt.execute();
        stmt.close();
    }

    public static boolean doesLoggingForGLExportExist(String latestBatchNumber) throws SQLException {
        int numRecords = 0;
        Connection db = DBConnection.db();
        String query = "select COUNT(1) As NumRecords FROM PerformanceLog where ExtraLogData like '%GLExportKey:' + ? + '%'";
        PreparedStatement stmt = db.prepareStatement(query);
        stmt.setString(1, String.valueOf(latestBatchNumber));
        ResultSet resultSet = stmt.executeQuery();
        if(resultSet.next())
        {
            numRecords = resultSet.getInt("numRecords");
        }
        stmt.close();
        return numRecords > 0;
    }
}
