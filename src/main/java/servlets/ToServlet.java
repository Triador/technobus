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
public class ToServlet extends HttpServlet {

    private H2DBService dbService;

    public ToServlet(H2DBService dbService) {
        this.dbService = dbService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/x-json;charset=UTF-8");
        resp.setHeader("Cache-Control", "no-cache");

        String jsonArray = dbService.getJsonArray("toOffice").toJSONString();

<<<<<<< HEAD
=======
        //вот тут пыталась перенаправить, но он меня послал :((
        // resp.sendRedirect("/index.html");

>>>>>>> 5c2bbe7e2b8244d26430b8a8dd38531615a34f8d

        resp.getWriter().write(jsonArray);
        resp.getWriter().flush();
    }
}
