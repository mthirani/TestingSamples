package SnowflakeJDBCCommands;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by mayankthirani on 3/8/19.
 */
public class JDBCCommands {
    public static void main(String []args) {
        Pattern p = Pattern.compile("[0-9]{4}-[0-9]{2}-[0-9]{2}[\\s][0-9]{2}:[0-9]{2}:[0-9]{2}" +
                ".[0-9]{3}[\\s][+-]*[0-9]{4}");
        Properties connectionProps = new Properties();
        connectionProps.put("user", "Danila Mikhaylutsa");
        connectionProps.put("password", "snaplogic123");
        connectionProps.put("db", "SNAPQADB");
        connectionProps.put("warehouse", "DEMO_WH");
        connectionProps.put("schema", "PRASANNA");
        connectionProps.put("ssl", "on");
        String test = String.format("%s%s", "Mayank", "Thirani");
        System.out.println(test);
//        String jdbcURL = "jdbc:snowflake://snaplogic.snowflakecomputing" +
//                ".com:443/?ssl=on&user=Danila " +
//                "Mikhaylutsa&password=snaplogic123&db=SNAPQADB&warehouse=DEMO_WH&schema=PRASANNA";
        try {
//            Connection conn = DriverManager.getConnection(jdbcURL);
            Connection conn = DriverManager.getConnection("jdbc:snowflake://snaplogic" +
                    ".snowflakecomputing.com:443", connectionProps);
            System.out.println("Is connection closed :: " + conn.isClosed());
            if (conn != null) {
                System.out.println("Connection is successfull");
                Statement statement = conn.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM \"PRASANNA\"" +
                        ".\"SWAT2179_6\"");
                ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
                int i = 1;
                while (i <= resultSetMetaData.getColumnCount()) {
                    System.out.println(resultSetMetaData.getColumnName(i));
                    System.out.println(resultSetMetaData.getColumnClassName(i).contains("Timestamp"));
                    i++;
                }
                System.out.println("ID, value");
                while (resultSet.next()) {
                    String value = resultSet.getString(1);
                    System.out.println(value);
                    Matcher m = p.matcher(value);
                    if (m.matches()) {
                        System.out.println("Pattern matched");
                    }
                }

                //Execute PUT command
//                statement.executeQuery("PUT " +
//                        "'file:////Users/mayankthirani/Downloads/" +
//                        "snowflakebuklload_data_f1c6dbb9-d326-4a3d-b591-cec3c51d3cee.tmp' '@~/INPUTVIEW/042356a6-5c89-4e6a-87fc-80e1b880bdd8/'");
//                statement.executeQuery("PUT " +
//                        "'file:////Users/mayankthirani/Desktop/SWAT0x002F1861/" +
//                        "snowflakebuklload_data.tmp' '@~/INPUTVIEW/042356a6-5c89-4e6a-87fc-80e1b880bdd8/'");
                conn.close();

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
