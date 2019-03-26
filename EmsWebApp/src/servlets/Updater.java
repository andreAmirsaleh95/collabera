package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
@WebServlet("/update")
public class Updater extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Updater() {
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
		String field = request.getParameter("field");
		int fieldInt = -1;
		try {
			fieldInt = Integer.parseInt(field);
		} catch (NumberFormatException e1) {
			e1.printStackTrace();
			request.getRequestDispatcher("error.html").forward(request, response);
		}
		String sql = "update employees set ";
		String colName = null;
		String valType = "string";
		String newVal = request.getParameter("newVal");
		int newValInt = -1;
		double newValDouble = -1;
		switch(fieldInt) {
		case 0: // Name
			colName = "emp_name";
			break;
			
		case 1: // Age
			colName = "age";
			valType = "int";
			break;
			
		case 2: // Gender
			colName = "gender";
			valType = "int";
			break;
			
		case 3: // Phone number
			colName = "phone_num";
			break;
			
		case 4: // Address
			colName = "address";
			break;
			
		case 5: // ID
			colName = "emp_id";
			valType = "int";
			break;
			
		case 6: // SSN
			colName = "ssn";
			break;
			
		case 7: // Email
			colName = "email";
			break;
			
		case 8: // Job title
			colName = "job_title";
			break;
			
		case 9: // Department
			colName = "department";
			valType = "int";
			break;
		
		case 10: // salary
			colName = "salary";
			valType = "double";
			break;
			
		case 11: // Manager ID
			colName = "reportsTo";
			break;
			
		case 12: // is manager?
			colName = "isManager";
			valType = "int";
			break;
			
		default:
			request.getRequestDispatcher("error.html").forward(request, response);
		}
		
		int empIdInt = -1;
		try {
			empIdInt = Integer.parseInt(request.getParameter("empId"));
		} catch (NumberFormatException e1) {
			e1.printStackTrace();
			request.getRequestDispatcher("error.html").forward(request, response);
		}
		
		
		Connection connection = ConnectionManager.getConnection();
		if (null == connection || null == colName) {
			request.getRequestDispatcher("error.html").forward(request, response);
		} else {
			
			// VALIDATE UNIQUE VAL FOR DB COLS MARKED UNIQUE:
			if (5 == fieldInt) { // emp_id
				try {
					int testVal = Integer.parseInt(newVal);
					PreparedStatement ps = connection.prepareStatement(
							"select count(*) from employees where emp_id = ?;");
					ps.setInt(1, testVal);
					ResultSet rs = ps.executeQuery();
					rs.next();
					if (1 == rs.getInt(1)) {
						request.getRequestDispatcher("error.html").forward(request, response);
					}
				} catch (SQLException | NumberFormatException e1) {
					e1.printStackTrace();
					request.getRequestDispatcher("error.html").forward(request, response);
				}
			} else if (6 == fieldInt) { // ssn
				try {
					int testVal = Integer.parseInt(newVal);
					PreparedStatement ps = connection.prepareStatement(
							"select count(*) from employees where ssn = ?;");
					ps.setInt(1, testVal);
					ResultSet rs = ps.executeQuery();
					rs.next();
					if (1 == rs.getInt(1)) {
						request.getRequestDispatcher("error.html").forward(request, response);
					}
				} catch (SQLException | NumberFormatException e1) {
					e1.printStackTrace();
					request.getRequestDispatcher("error.html").forward(request, response);
				}
			}
			
			// UPDATE VALUE:
			sql = sql + colName + " = ? where emp_id = ?;";
			if (null != colName && null != connection) {
				try {
					PreparedStatement ps = connection.prepareStatement(sql);
					ps.setInt(2, empIdInt);
					if ("int".equals(valType)) {
						newValInt = Integer.parseInt(newVal);
						ps.setInt(1, newValInt);
					} else if ("double".equals(valType)) {
						newValDouble = Double.parseDouble(newVal);
						ps.setDouble(1, newValDouble);
					} else { // if "string".equals(valType)
						ps.setString(1, newVal);
					}
					ps.executeUpdate();
					doGet(request, response);
				} catch (SQLException | NumberFormatException e1) {
					e1.printStackTrace();
					request.getRequestDispatcher("error.html").forward(request, response);
				}
			}
		}
	}
}
