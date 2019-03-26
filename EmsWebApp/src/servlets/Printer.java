package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.ConnectionManager;

/**
 * Servlet implementation class Printer
 */
@WebServlet("/print")
public class Printer extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Printer() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

	}

	private static void goBackToMenu(HttpServletResponse response) {
		response.setStatus(response.SC_TEMPORARY_REDIRECT);
		response.setHeader("Location", "http://localhost:8080/EmsWebApp/");

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Connection connection = ConnectionManager.getConnection();
		if (null == connection) {
			request.getRequestDispatcher("error.html").forward(request, response);
		} else {
			try (Statement statement = connection.createStatement();
					ResultSet rs = statement.executeQuery("select * from employees");) {
				ResultSetMetaData rsmd = rs.getMetaData();
				int columnsNumber = rsmd.getColumnCount();
				while (rs.next()) {
					for (int i = 1; i <= columnsNumber; i++) {
						if (i > 1)
							System.out.print(",  ");
						String columnValue = rs.getString(i);
						response.getWriter()
								.append(rsmd.getColumnName(i) + "=" +columnValue);
					}
					response.getWriter().append("\n\n");
				}
			} catch (SQLException e) {
				e.printStackTrace();
				request.getRequestDispatcher("error.html").forward(request, response);
			}
		}
	}

}
