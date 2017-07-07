package servlets;

import services.H2DBService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by antonandreev on 04/07/2017.
 */
public class FromServlet extends HttpServlet {

    private H2DBService dbService;

    public FromServlet(H2DBService dbService) {
        this.dbService = dbService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/x-json;charset=UTF-8");
        resp.setHeader("Cache-Control", "no-cache");

        String jsonArray = dbService.getJsonArray("fromOffice").toJSONString();

        resp.getWriter().write(jsonArray);
        resp.getWriter().flush();
    }
}
