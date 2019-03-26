package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.ConnectionManager;

/**
 * Servlet implementation class Updater
 */
@WebServlet("/populater")
public class Populater extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Populater() {
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
		String empId = request.getParameter("empId");
		int empIdInt = -1;
		try {
			empIdInt = Integer.parseInt(empId);
		} catch (NumberFormatException e1) {
			e1.printStackTrace();
			request.getRequestDispatcher("error.html").forward(request, response);
		}

		if (0 > empIdInt || 99999 < empIdInt) {
			System.err.println(
					"ERROR: Employee ID must be between 0 and 99999 (inclusive).");
			request.getRequestDispatcher("error.html").forward(request, response);
		}
		
		request.getSession().setAttribute("oldId", empId);

		Connection connection = ConnectionManager.getConnection();
		if (null == connection) {
			System.err.println(
					"ERROR: Could not establish connection to database.");
			request.getRequestDispatcher("error.html").forward(request, response);
		}
		ResultSet rs;
		PreparedStatement ps;
		try {
			ps = connection
					.prepareStatement("select * from employees where emp_id = ?");
			ps.setInt(1, empIdInt);
			rs = ps.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnsNumber = rsmd.getColumnCount();
			while (rs.next()) {
				for (int i = 1; i <= columnsNumber; i++) {
					String columnValue = rs.getString(i);
					System.out.println("columnName = " + rsmd.getColumnName(i));
					System.out.println("columnValue = " + columnValue);
					request.getSession().setAttribute("p_" + rsmd.getColumnName(i), columnValue);
				}
			}
			
			request.getRequestDispatcher("update.jsp").forward(request, response);
		} catch (SQLException e1) {
			e1.printStackTrace();
			request.getRequestDispatcher("error.html").forward(request, response);
		}
	}
}
