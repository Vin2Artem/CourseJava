package servlets;

import DAO.CourseDAO;
import DAO.LessonDAO;
import DAO.SQLiteDAOFactory;
import DAO.SQLiteUserCourseDAO;
import models.Course;
import models.Lesson;
import models.User;
import models.UserCourse;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

public class CoursesServlet extends HttpServlet {

    private static final String URL_STUDENT = "/main";
    private static final String URL_LOGIN = "/login";
    private static final String JSP_COURSES = "/WEB-INF/view/courses.jsp";

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            resp.sendRedirect(URL_LOGIN);
            return;
        }
        String uri = req.getRequestURI().substring(9);
        if (!uri.matches("[-+]?\\d+") || uri.length() >= 10 || Integer.parseInt(uri) <= 0) {
            resp.sendRedirect(URL_STUDENT);
            return;
        }
        int courseId = Integer.parseInt(uri);

        /* get course */
        SQLiteDAOFactory sqLiteDAOFactory = new SQLiteDAOFactory();
        CourseDAO courseDAO = sqLiteDAOFactory.getCourseDAO();
        Course course = courseDAO.findCourse(courseId);
        if (course == null) {
            resp.sendRedirect(URL_STUDENT);
            return;
        }
        req.setAttribute("course", course);

        /* get lessons */
        LessonDAO lessonDAO = sqLiteDAOFactory.getLessonDAO();
        ArrayList<Lesson> lst = lessonDAO.getLessonsOfCourse(user, courseId);
        if (lst == null) {
            resp.sendRedirect(URL_STUDENT);
            return;
        }

        UserCourse foundUserCourse = null;
        SQLiteUserCourseDAO sqLiteUserCourseDAO = new SQLiteUserCourseDAO();
        ArrayList<UserCourse> availableUserCourses = sqLiteUserCourseDAO.getAvailableUserCourses(user.getId());
        for (UserCourse availableUserCourse : availableUserCourses) {
            if (availableUserCourse.getCourse() == courseId) {
                foundUserCourse = availableUserCourse;
                break;
            }
        }
        if (foundUserCourse == null) {
            req.setAttribute("courseLocked", true);
        } else {
            req.setAttribute("courseLocked", false);
        }

        req.setAttribute("lessons", lst);
        // Add 2nd group (admin)
        req.getRequestDispatcher(JSP_COURSES).forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            resp.sendRedirect(URL_LOGIN);
            return;
        }
        /* check usual user */
        if (!user.getEditor()) {
            resp.sendRedirect(URL_STUDENT);
            return;
        }
        /* id filter */
        String uri = req.getRequestURI().substring(9);
        if (!uri.matches("[-+]?\\d+") || uri.length() >= 10 || Integer.parseInt(uri) <= 0) {
            resp.sendRedirect(URL_STUDENT);
            return;
        }
        int courseId = Integer.parseInt(uri);
        SQLiteDAOFactory sqLiteDAOFactory = new SQLiteDAOFactory();
        CourseDAO courseDAO = sqLiteDAOFactory.getCourseDAO();
        req.setCharacterEncoding("UTF-8");
        if (courseDAO.editCourse(courseId, req.getParameter("desc"))) {
            req.setAttribute("success", true);
        } else {
            req.setAttribute("success", false);
        }
        doGet(req, resp);
    }
}
