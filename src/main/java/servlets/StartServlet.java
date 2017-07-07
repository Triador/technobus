package servlets;

import services.H2DBService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

<<<<<<< HEAD
/**
 * Created by antonandreev on 04/07/2017.
 */
=======
>>>>>>> 5c2bbe7e2b8244d26430b8a8dd38531615a34f8d
public class StartServlet extends HttpServlet {

    private H2DBService dbService;

    public StartServlet(H2DBService dbService) {
        this.dbService = dbService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/x-json;charset=UTF-8");
        resp.setHeader("Cache-Control", "no-cache");

<<<<<<< HEAD
        resp.getWriter().println(req.getRequestURL());
        resp.getWriter().flush();
=======
        //тут выбираем user-agent, но пока перенаправлю в to
        resp.sendRedirect("/to");
>>>>>>> 5c2bbe7e2b8244d26430b8a8dd38531615a34f8d
    }
}
