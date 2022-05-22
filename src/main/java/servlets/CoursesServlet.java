package servlets;

import DAO.CourseDAO;
import DAO.LessonDAO;
import DAO.SQLiteDAOFactory;
import log.MyLog;
import models.Course;
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

    private static final String url_student = "/main";
    private static final String url_login = "/login";
    private static final String jsp_courses = "/WEB-INF/view/courses.jsp";

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
            resp.sendRedirect(url_student);
            return;
        }
        int courseId = Integer.parseInt(uri);

        /* get course */
        SQLiteDAOFactory sqLiteDAOFactory = new SQLiteDAOFactory();
        CourseDAO courseDAO = sqLiteDAOFactory.getCourseDAO();
        Course course = courseDAO.findCourse(courseId);
        if (course == null) {
            resp.sendRedirect(url_student);
            return;
        }
        req.setAttribute("course", course);

        /* get lessons */
        LessonDAO lessonDAO = sqLiteDAOFactory.getLessonDAO();
        ArrayList<Lesson> lst = lessonDAO.getAvailableLessons(user.getId(), courseId);
        if (lst == null) {
            resp.sendRedirect(url_student);
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
