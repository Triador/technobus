package services;

/**
 * Created by antonandreev on 06/07/2017.
 */

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.sql.*;

/**
 * Created by antonandreev on 04/07/2017.
 */
public class H2DBService {
    private static volatile H2DBService dbService;
    private static final String URL = "jdbc:h2:./h2db";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    static {
        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("Error load H2 JDBC driver: " + e.getMessage());
        }
    }

    public static H2DBService getInstance() {
        if (dbService == null) {
            synchronized (H2DBService.class) {
                if (dbService == null) {
                    dbService = new H2DBService();
                }
            }
        }
        return dbService;
    }

    public JSONArray getJsonArray(String bdName) {
        Connection conn = getConnect();
        JSONArray jsonArray = new JSONArray();

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT time, mask FROM " + bdName)) {

            while (rs.next()) {
                JSONObject obj = new JSONObject();
                String time = rs.getString("time");
                obj.put("time", time.substring(0, time.length() - 3));
                obj.put("mask", rs.getByte("mask"));
                jsonArray.add(obj);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return jsonArray;
    }

    public static void main(String[] args) {
        update("toOffice", 127, 31);
    }

    public static void update(String bdName, int from, int on) {
        Connection conn = getConnect();

        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate("UPDATE " + bdName + " SET mask = " + on + " WHERE mask = " + from);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static Connection getConnect() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

}
