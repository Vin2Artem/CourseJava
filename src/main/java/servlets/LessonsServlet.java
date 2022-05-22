package servlets;

import DAO.LessonDAO;
import DAO.SQLiteDAOFactory;
import models.Lesson;
import models.User;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

public class LessonsServlet extends HttpServlet {

    private static final String url_student = "/main";
    private static final String url_login = "/login";
    private static final String jsp_lessons = "/WEB-INF/view/lesson.jsp";

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
        Lesson lesson = lessonDAO.getLessonById(user.getId(), lessonId);
        /* if lesson is not found */
        if (lesson == null) {
            resp.sendRedirect(url_student);
            return;
        }
        int courseId = lesson.getCourse();
        /* if lesson is unavailable */
        if (lesson.getDaysToUnlock() > 0) {
            resp.sendRedirect("/course/" + courseId);
            return;
        }
        /* set last lesson cookie */
        Cookie last_lesson = new Cookie("last_lesson", String.valueOf(lesson.getId()));
        /* 30 days */
        last_lesson.setMaxAge(60 * 60 * 24 * 30);
        last_lesson.setPath("/");
        resp.addCookie(last_lesson);

        req.setAttribute("courseId", courseId);
        req.setAttribute("lesson", lesson);
        // Add 2nd group (admin)
        req.getRequestDispatcher(jsp_lessons).forward(req, resp);
    }
}
