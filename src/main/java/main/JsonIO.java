package main;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by antonandreev on 06/07/2017.
 */
public class JsonIO {

    public static Map<String, Byte> readFromJson(String input) {
        Map<String, Byte> map = new TreeMap<>();
        JSONParser parser = new JSONParser();

        try {
            Object obj = parser.parse(new FileReader(input));
            JSONArray jsonArray = (JSONArray) obj;

            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject jsonObject = (JSONObject) jsonArray.get(i);

                String time = jsonObject.get("time").toString();
                Byte mask = Byte.parseByte(jsonObject.get("mask").toString(), 2);

                map.put(time, mask);
            }

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        return map;
    }

    public static void writeToJsonFromTxt(String input, String output) {

        try (FileWriter file = new FileWriter(output); BufferedReader reader = new BufferedReader(new FileReader(input))) {

            JSONArray jsonArray = new JSONArray();

            while (reader.ready()) {
                String[] list = reader.readLine().split(" ");
                JSONObject obj = new JSONObject();
                obj.put("time", list[0]);
                obj.put("mask", list[1]);
                jsonArray.add(obj);
            }
            file.write(jsonArray.toJSONString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void createH2dbFromJson(String input, Connection con, String bdName) {
        JSONParser parser = new JSONParser();

        try (Statement stmt = con.createStatement()) {
            stmt.executeUpdate("CREATE TABLE " + bdName + "(time TIME NOT NULL, mask TINYINT NOT NULL, PRIMARY KEY(time));");
            Object obj = parser.parse(new FileReader(input));
            JSONArray jsonArray = (JSONArray) obj;

            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject jsonObject = (JSONObject) jsonArray.get(i);

                String time = jsonObject.get("time").toString();
                Byte mask = Byte.parseByte(jsonObject.get("mask").toString(), 2);

                stmt.executeUpdate("INSERT INTO " + bdName + "(time, mask) VALUES ('" + time + ":00'," + mask + ")");

            }

        } catch (IOException | ParseException | SQLException e) {
            e.printStackTrace();
        }
    }
}
