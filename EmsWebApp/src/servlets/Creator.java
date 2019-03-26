package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.ConnectionManager;

/**
 * Servlet implementation class Creator
 */
@WebServlet("/create")
public class Creator extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Creator() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Don't write HTML from here. Pass to session or something
		response.getWriter().append(
				"<h1>Success!</h1>\n\n<a href=\"http://localhost:8080/EmsWebApp/\">Home</a>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// COLLECT INPUTS:
		String name = request.getParameter("name");
		String age = request.getParameter("age");
		String gender = request.getParameter("gender");
		String contactNo = request.getParameter("contactNo");
		String address = request.getParameter("street")
				+ request.getParameter("town") + request.getParameter("state")
				+ request.getParameter("zip");
		String empId = request.getParameter("empId");
		String ssn = request.getParameter("ssn");
		String email = request.getParameter("email");
		String jobTitle = request.getParameter("jobTitle");
		String department = request.getParameter("department");
		String salary = request.getParameter("salary");
		String reportTo = request.getParameter("reportTo");
		String isManager = request.getParameter("isManager");

		// CONVERT TYPES AND VALIDATE WHERE NEEDED:
		boolean errorFlag = false;
		List<String> errorMsgs = new LinkedList<String>();
		int ageInt = -1;
		int genderInt = -1;
		int empIdInt = -1;
		double salaryDouble = -1;
		int reportToInt = -1;
		int isManagerInt = 1;
		int departmentInt = -1;
		try {
			ageInt = Integer.parseInt(age);
			departmentInt = Integer.parseInt(department);
			genderInt = Integer.parseInt(gender);
			empIdInt = Integer.parseInt(empId);
			salaryDouble = Double.parseDouble(salary);
			reportToInt = Integer.parseInt(reportTo);
			isManagerInt = Integer.parseInt(isManager);

		} catch (NumberFormatException e1) {
			e1.printStackTrace();
			request.getRequestDispatcher("error.html").forward(request, response);
		}

		if (10 > ageInt || 99 < ageInt) {
			System.err
					.println("ERROR: age must be between 10 and 99 (inclusive).");
		}

		if (0 > genderInt || 2 < genderInt) {
			System.err.println("ERROR: gender must be 0, 1, or 2.");
		}

		if (0 > empIdInt || 99999 < empIdInt) {
			System.err.println(
					"ERROR: empId must be non-negative and less than 100000");
		}

		if (0 > salaryDouble || 99999999.99 < salaryDouble) {
			System.err.println(
					"ERROR: salary must be non-negative and less than 99999999.99");
		}

		if (0 > reportToInt || 99999 < reportToInt) {
			System.err.println(
					"ERROR: salary must be non-negative and less than 99999");
		}

		if (0 != isManagerInt) {
			isManagerInt = 1;
		}

		// VALIDATE UNIQUE ID:
		Connection connection = ConnectionManager.getConnection();
		if (null == connection) {
			errorFlag = true;
			errorMsgs.add("ERROR: Could not connect to database.");
		}

		try {
			PreparedStatement ps = connection.prepareStatement(
					"select count(*) from employees where emp_id = ?;");
			ps.setInt(1, empIdInt);
			ResultSet rs = ps.executeQuery();
			rs.next();
			if (0 < rs.getInt(1)) {
//				rs.close();
				errorFlag = true;
				errorMsgs.add(
						"ERROR: Duplicate employee ID already in database. Must be unique.");
			}
		} catch (SQLException e1) {
			errorFlag = true;
			errorMsgs.add(
					"ERROR: Could not query database while validating unique ID.");
			e1.printStackTrace();
		}

		// VALIDATE UNIQUE SSN:
		try {
			PreparedStatement ps = connection.prepareStatement(
					"select count(*) from employees where ssn = ?;");
			ps.setString(1, ssn);
			ResultSet rs = ps.executeQuery();
			rs.next();
			if (1 == rs.getInt(1)) {
//				rs.close();
				errorFlag = true;
				errorMsgs.add(
						"ERROR: Duplicate SSN already in database. Must be unique.");
			}
		} catch (SQLException e1) {
			errorFlag = true;
			errorMsgs.add(
					"ERROR: Could not query database while validating unique SSN.");
			e1.printStackTrace();
		}

		// INSERT EMPLOYEE INTO DB:
		if (errorFlag) {
			request.getSession().setAttribute("errorMsgs", errorMsgs);
			request.getRequestDispatcher("error.jsp").forward(request, response);
		} else {
			try {
				PreparedStatement ps = connection.prepareStatement(
						"insert into employees(db_id, emp_id, ssn, email, emp_name, age,"
								+ "phone_num, address, gender, reportsTo, isManager, job_title,"
								+ "department, salary) values(0, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, "
								+ "?, ?, ?);");
				ps.setInt(1, empIdInt);
				ps.setString(2, ssn);
				ps.setString(3, email);
				ps.setString(4, name);
				ps.setInt(5, ageInt);
				ps.setString(6, contactNo);
				ps.setString(7, address);
				ps.setInt(8, genderInt);
				ps.setInt(9, reportToInt);
				ps.setInt(10, isManagerInt);
				ps.setString(11, jobTitle);
				ps.setInt(12, departmentInt);
				ps.setDouble(13, salaryDouble);
				ps.executeUpdate();
				request.getRequestDispatcher("success.html").forward(request,
						response);
			} catch (SQLException e1) {
				e1.printStackTrace();
				errorMsgs.add("ERROR: Could not insert new employee.");
				request.getRequestDispatcher("error.jsp").forward(request,
						response);
			}
		}
	}
}
