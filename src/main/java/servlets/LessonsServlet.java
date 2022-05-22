package servlets;

import DAO.LessonDAO;
import DAO.SQLiteDAOFactory;
import models.Lesson;
import models.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LessonsServlet extends HttpServlet {

    private static final String url_student = "/main";
    private static final String url_login = "/login";
    private static final String jsp_courses = "/WEB-INF/view/lesson.jsp";

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        lesson(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        lesson(req, resp);
    }

    protected void lesson(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            resp.sendRedirect(url_login);
            return;
        }
        String uri = req.getRequestURI().substring(9);
        if (!uri.matches("[-+]?\\d+") || uri.length() >= 10 || Integer.parseInt(uri) <= 0) {
            resp.sendRedirect(url_student);
            return;
        }
        int lessonId = Integer.parseInt(uri);

        /* get course */
        SQLiteDAOFactory sqLiteDAOFactory = new SQLiteDAOFactory();
        LessonDAO lessonDAO = sqLiteDAOFactory.getLessonDAO();
        Lesson lesson = lessonDAO.getAvailableLesson(user.getId(), lessonId);
        /* if lesson is not found or unavailable */
        if (lesson == null) {
            resp.sendRedirect(url_student);
            return;
        }
        req.setAttribute("lesson", lesson);
        // Add 2nd group (admin)
        req.getRequestDispatcher(jsp_courses).forward(req, resp);
    }
}
