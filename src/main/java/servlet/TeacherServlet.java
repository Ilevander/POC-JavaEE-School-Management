package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import repository.TeacherRepository;
import repository.TeacherRepositoryImpl;
import model.Role;
import model.Teacher;

@WebServlet("/teachers")
public class TeacherServlet extends HttpServlet {
    
	private static final long serialVersionUID = 1L;
	private TeacherRepository teacherRepository = new TeacherRepositoryImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            List<Teacher> teachers = teacherRepository.findAll();
            request.setAttribute("teachers", teachers);
            request.getRequestDispatcher("/WEB-INF/jsp/teachers.jsp").forward(request, response);
        } else if (action.equals("edit")) {
            int id = Integer.parseInt(request.getParameter("id"));
            Teacher teacher = teacherRepository.findById(id);
            request.setAttribute("teacher", teacher);
            request.getRequestDispatcher("/WEB-INF/jsp/edit-teacher.jsp").forward(request, response);
        }
        // Add other cases for create, update, delete actions
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action.equals("create")) {
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String subject = request.getParameter("subject");
            String email = request.getParameter("email");
            Role role = Role.valueOf(request.getParameter("role")); // Assuming Role enum exists
            Teacher teacher = new Teacher(0, firstName, lastName, subject, email, role); // ID is auto-generated
            teacherRepository.save(teacher);
            response.sendRedirect(request.getContextPath() + "/teachers");
        } else if (action.equals("update")) {
            int id = Integer.parseInt(request.getParameter("id"));
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String subject = request.getParameter("subject");
            String email = request.getParameter("email");
            Role role = Role.valueOf(request.getParameter("role")); // Assuming Role enum exists
            Teacher teacher = new Teacher(id, firstName, lastName, subject, email, role);
            teacherRepository.update(teacher);
            response.sendRedirect(request.getContextPath() + "/teachers");
        } else if (action.equals("delete")) {
            int id = Integer.parseInt(request.getParameter("id"));
            teacherRepository.deleteById(id);
            response.sendRedirect(request.getContextPath() + "/teachers");
        }
    }
}
