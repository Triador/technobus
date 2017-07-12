package DAO;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by antonandreev on 10/07/2017.
 */
public class ScheduleDAO {

    private Connection connection;

    public ScheduleDAO(Connection connection) {
        this.connection = connection;
    }

    public JSONArray getJsonArray(String bdName) {

        JSONArray jsonArray = new JSONArray();

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM " + bdName + " ORDER BY time")) {

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

    public void update(String bdName, int from, int to) {

        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate("UPDATE " + bdName + " SET mask = " + to + " WHERE mask = " + from);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
