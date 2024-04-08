package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Role;

/**
 * Servlet implementation class TeacherDashboardServlet
 */
@WebServlet("/TeacherDashboardServlet")
public class TeacherDashboardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TeacherDashboardServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Role userRole = (Role) session.getAttribute("userRole");

        // Check if user is allowed to access teacher dashboard
        if (userRole == Role.TEACHER) {
            // Proceed to teacher dashboard
            request.getRequestDispatcher("/teacher-dashboard.jsp").forward(request, response);
        } else {
            // Redirect to unauthorized page or show error message
            response.sendRedirect("/unauthorized.jsp");
        }
    }

}
