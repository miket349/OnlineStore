package OnlineStore;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/RemoveItem")
public class RemoveItem extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Connection connection = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://cs3.calstatela.edu/cs3220stu51";
			String username = "cs3220stu51";
			String password = "ewgXn5dA";
			String sql = "DELETE FROM ShoppingCart where id = ?";
			connection = DriverManager.getConnection(url, username, password);
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();
		}
		catch (SQLException | ClassNotFoundException e) {
			throw new ServletException(e);
		}
		finally {
			try {
				if (connection != null)
					connection.close();
			}
			catch (SQLException e) {
				throw new ServletException(e);
			}
		}
			
		response.sendRedirect("ShoppingCart");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}