package servlets;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import services.DBService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by antonandreev on 04/07/2017.
 */
public class FromServlet extends HttpServlet {

    private DBService dbService;

    public FromServlet(DBService dbService) {
        this.dbService = dbService;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/x-json;charset=UTF-8");
        resp.setHeader("Cache-Control", "no-cache");

        JSONParser parser = new JSONParser();

        try {
            Object obj = parser.parse(new FileReader("from.json"));
            JSONArray jsonArray = (JSONArray) obj;
            resp.getWriter().write(jsonArray.toJSONString());
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        resp.getWriter().flush();
    }
}
