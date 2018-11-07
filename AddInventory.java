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

@WebServlet("/AddInventory")
public class AddInventory extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/AddInventory.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("name") == null || request.getParameter("name") == "" || request.getParameter("quantity") == null || request.getParameter("quantity") == "" || request.getParameter("price") == null || request.getParameter("price") == "") {
			request.setAttribute("error", "All fields cannot be empty");
			doGet(request, response);
		}
		else {
			String name = request.getParameter("name");
			String description = request.getParameter("description");
			int quantity = Integer.parseInt(request.getParameter("quantity"));
			double price = Double.parseDouble(request.getParameter("price"));
			Connection connection = null;
			
			try {
				Class.forName("com.mysql.jdbc.Driver");
				String url = "jdbc:mysql://cs3.calstatela.edu/cs3220stu51";
				String username = "cs3220stu51";
				String password = "ewgXn5dA";
				String sql = "INSERT INTO Items (name, description, quantity, price) VALUES (?, ?, ?, ?)";
				
				connection = DriverManager.getConnection(url, username, password);
				PreparedStatement preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setString(1, name);
				preparedStatement.setString(2, description);
				preparedStatement.setInt(3, quantity);
				preparedStatement.setDouble(4, price);
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
			
			response.sendRedirect("Inventoryy");
		}
	}
}