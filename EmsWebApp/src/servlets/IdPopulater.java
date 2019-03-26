package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
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
 * Servlet implementation class IdPopulater
 */
@WebServlet("/idpopulater")
public class IdPopulater extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IdPopulater() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection connection = ConnectionManager.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement("select * from employees");
			ResultSet rs = ps.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnsNumber = rsmd.getColumnCount();
			List<String> idList = new LinkedList<String>();
			while (rs.next()) {
				for (int i = 1; i <= columnsNumber; i++) {
					if ("emp_id".equals(rsmd.getColumnName(i)))
						idList.add(rs.getString(i));
				}
			}
			request.getSession().setAttribute("idList", idList);
			request.getRequestDispatcher("updatebyid.jsp").forward(request, response);
		} catch (SQLException e1) {
			e1.printStackTrace();
			request.getRequestDispatcher("error.html").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
