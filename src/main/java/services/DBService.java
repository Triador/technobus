package services;

/**
 * Created by antonandreev on 06/07/2017.
 */

import main.JsonIO;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.sql.*;

/**
 * Created by antonandreev on 04/07/2017.
 */
public class DBService {
    private static volatile DBService dbService;
    static {
        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("Error load H2 JDBC driver: " + e.getMessage());
        }
    }

    public static DBService getInstance() {
        if (dbService == null) {
            synchronized (DBService.class) {
                if (dbService == null) {
                    dbService = new DBService();
                }
            }
        }
        return dbService;
    }

    public JSONArray jsonArrayFromH2db(String bdName) {
        Connection conn = getH2Configuration();
        JSONArray jsonArray = new JSONArray();

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT time, mask FROM " + bdName)) {

            while (rs.next()) {
                JSONObject obj = new JSONObject();
                obj.put("time", rs.getString("time"));
                obj.put("mask", rs.getByte("mask"));
                jsonArray.add(obj);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return jsonArray;
    }


    static void createSimpleDBSchema(Connection con, String input, String bdName) {
        JsonIO.createH2dbFromJson(input, con, bdName);
    }


    static Connection getH2Configuration() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:h2:./h2db", "root", "root");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

    static void selectAll(Connection con, String bdName) {

        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT time, mask FROM " + bdName)) {

            while (rs.next()) {
                System.out.println(rs.getString("time") + " " + rs.getByte("mask"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
