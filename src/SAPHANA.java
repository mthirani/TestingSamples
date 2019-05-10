import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by mayankthirani on 3/29/19.
 */
public class SAPHANA {
    public static void main(String []args) {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(
                    "jdbc:sap://35.173.88.84:30015/?autocommit=false","CODEJAMMER",
                    "CodeJam2016");
        } catch (SQLException e) {
            System.err.println("Connection Failed. User/Passwd Error?");
            return;
        }
        if (connection != null) {
            try {
                System.out.println("Connection to HANA successful!");
                Statement stmt = connection.createStatement();
                ResultSet resultSet = stmt.executeQuery("select * from SWAT1909");
                resultSet.next();
                int hello = resultSet.getInt(1);
                java.sql.DatabaseMetaData dbMetaData = connection.getMetaData();
                resultSet = dbMetaData.getColumns(null, null, "\"SWAT1916_3\"",
                        null);
                while (resultSet.next()) {
                    String columnName = resultSet.getString("COLUMN_NAME");
                    String data_type = resultSet.getString("DATA_TYPE");
                    String table_schem = resultSet.getString("TABLE_SCHEM");
                    String table_name = resultSet.getString("TABLE_NAME");
                    System.out.println("column_name:: " + columnName + ", data_type:: " +
                            data_type + ", table_schema:: " + table_schem + ", table_name:: " + table_name);
                }
                System.out.println(hello);
            } catch (SQLException e) {
                System.err.println("Query failed!");
            }
        }
    }
}
