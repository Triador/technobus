package servlets;

import services.DBService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by antonandreev on 04/07/2017.
 */
public class ToServlet extends HttpServlet {

    private DBService dbService;

    public ToServlet(DBService dbService) {
        this.dbService = dbService;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/x-json;charset=UTF-8");
        resp.setHeader("Cache-Control", "no-cache");
        String jsonArray = dbService.jsonArrayFromH2db("toOffice").toJSONString();
        resp.getWriter().write(jsonArray);
        resp.getWriter().flush();
    }
}
