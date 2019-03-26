package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
 * Servlet implementation class Updater2
 */
@WebServlet("/update2")
public class Updater2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Updater2() {
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
		response.getWriter().append("Served at: ")
				.append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// COLLECT INPUTS:
		String name = request.getParameter("full_name");
		String age = request.getParameter("age");
		String gender = request.getParameter("gender");
		String contactNo = request.getParameter("contactNo");
		String address = request.getParameter("address");
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
			errorFlag = true;
			errorMsgs.add("ERROR: Could not find a number in one of the fields.");
		}

		if (10 > ageInt || 99 < ageInt) {
			errorFlag = true;
			errorMsgs.add("ERROR: age must be between 10 and 99 (inclusive).");
		}

		if (0 > genderInt || 2 < genderInt) {
			errorFlag = true;
			errorMsgs.add(
					"ERROR: gender must be Male, Female, or Other (0, 1, or 2).");
		}

		if (0 > empIdInt || 99999 < empIdInt) {
			errorFlag = true;
			errorMsgs.add(
					"ERROR: Employee ID must be non-negative int less than 100000");
		}

		if (0 > salaryDouble || 99999999.99 < salaryDouble) {
			errorFlag = true;
			errorMsgs.add(
					"ERROR: salary must be non-negative and less than 100000000 with two decimal digits of precision.");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}

		if (0 > reportToInt || 99999 < reportToInt) {
			errorFlag = true;
			errorMsgs
					.add("ERROR: salary must be non-negative int less than 100000");
		}

		if (0 != isManagerInt) {
			isManagerInt = 1;
		}

		
			Connection connection = ConnectionManager.getConnection();
			if (null == connection) {
				errorMsgs.add("ERROR: Could not connect to database.");
			}
		if (!errorFlag) {
			try {
//			PreparedStatement ps1 = connection.prepareStatement(
//					"select count(*) from employees where emp_id = ?;");
//			ps1.setInt(1, empIdInt);
//			ResultSet rs = ps1.executeQuery();
//			rs.next();
//			if (1 == rs.getInt(1)) {
//				System.err.println("ERROR:");
//				rs.close();
//				request.getRequestDispatcher("error.html").forward(request,
//						response);
//			}

				// INSERT EMPLOYEE INTO DB:
				PreparedStatement ps = connection.prepareStatement(
						"update employees set emp_id=?, ssn=?, email=?, emp_name=?, age=?, phone_num=?, address=?, gender=?, reportsTo=?, isManager=?, job_title=?, department=?, salary=? where emp_id=?;");
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
				ps.setInt(14, Integer.parseInt(request.getSession().getAttribute("oldId").toString()));
				ps.executeUpdate();
				request.getRequestDispatcher("success.html").forward(request,
						response);
			} catch (SQLException e1) {
				errorMsgs.add("Error: " + e1.getMessage());
				request.getSession().setAttribute("errorMsgs", errorMsgs);
				request.getRequestDispatcher("error.jsp").forward(request, response);
				e1.printStackTrace();
			}
		} else {
			request.getSession().setAttribute("errorMsgs", errorMsgs);
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
	}
}
