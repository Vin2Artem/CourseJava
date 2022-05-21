package servlets;

import DAO.LessonDAO;
import DAO.SQLiteDAOFactory;
import log.MyLog;
import models.Lesson;
import models.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

public class CoursesServlet extends HttpServlet {

    private static final String jsp_student = "/WEB-INF/view/main.jsp";
    private static final String jsp_courses = "/WEB-INF/view/courses.jsp";
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
        User user = (User) session.getAttribute("user");
        if (user == null) {
            resp.sendRedirect(url_login);
            return;
        }
        String uri = req.getRequestURI().substring(9);
        if (!uri.matches("[-+]?\\d+") || uri.length() >= 10 || Integer.parseInt(uri) <= 0) {
            resp.sendRedirect(url_login);
            return;
        }
        SQLiteDAOFactory sqLiteDAOFactory = new SQLiteDAOFactory();
        LessonDAO lessonDAO = sqLiteDAOFactory.getLessonDAO();
        ArrayList<Lesson> lst = lessonDAO.getAvailableLessons(user.getId(), Integer.parseInt(uri));
        if (lst == null) {
            resp.sendRedirect(url_login);
            return;
        }
        for (Lesson lesson : lst) {
            MyLog.Msg(lesson.toString());
        }
        req.setAttribute("lessons", lst);
        // Add 2nd group (admin)
        req.getRequestDispatcher(jsp_courses).forward(req, resp);
    }
}
