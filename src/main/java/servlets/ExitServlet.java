package servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Enumeration;

public class ExitServlet extends HttpServlet {

    private static final String URL_ROOT = "/";

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        exit(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        exit(req, resp);
    }

    protected void exit(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        HttpSession session = req.getSession();
        Enumeration keys = session.getAttributeNames();
        if (keys.hasMoreElements()) {
            session.invalidate();
        }
        resp.sendRedirect(URL_ROOT);
    }
}
