package servlets;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.ConnectionManager;

/**
 * Servlet implementation class Deleter
 */
@WebServlet("/delete")
public class Deleter extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Deleter() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	// TODO Don't write HTML from here. Pass to session or something
			response.getWriter().append("<h1>Success!</h1>\n\n<a href=\"http://localhost:8080/EmsWebApp/\">Home</a>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sql = "delete from employees where emp_id = ?";
		try {
			PreparedStatement ps = ConnectionManager.getConnection()
					.prepareStatement(sql);
			String empId = request.getParameter("empId");
			int empIdInt = Integer.parseInt(empId);
			ps.setInt(1, empIdInt);
			ps.executeUpdate();
			request.getRequestDispatcher("success.html").forward(request,
					response);
		} catch (SQLException | NumberFormatException e) {
			e.printStackTrace();
			request.getRequestDispatcher("error.html").forward(request, response);
		}
	}
}
