package servlets;

import log.MyLog;
import models.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class CoursesServlet extends HttpServlet {

    private static final String jsp_student = "/WEB-INF/view/main.jsp";
    private static final String url_login = "/login";

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        course(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        course(req, resp);
    }

    protected void course(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User)session.getAttribute("user");
        if (user == null) {
            resp.sendRedirect(url_login);
            return;
        }
        MyLog.Msg(req.getRequestURI());
        // Add 2nd group (admin)
        req.getRequestDispatcher(jsp_student).forward(req, resp);
    }
}
