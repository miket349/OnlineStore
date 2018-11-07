package OnlineStore;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Checkout")
public class Checkout extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Items> items = new ArrayList<Items>();
		Connection connection = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://cs3.calstatela.edu/cs3220stu51";
            String username = "cs3220stu51";
            String password = "ewgXn5dA";
            String sql = "SELECT * FROM ShoppingCart";
            
            connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while(resultSet.next()) {
                Items item = new Items(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getString("description"), resultSet.getInt("quantity"), resultSet.getDouble("price"), resultSet.getDouble("total"), resultSet.getBoolean("cart"), resultSet.getInt("orderNum"));
                items.add(item);
            }
		}
		catch (SQLException | ClassNotFoundException e) {
			throw new ServletException(e);
		}
		finally {
			try {
				if (connection != null)
					connection.close();
			}
			catch (SQLException e){
				throw new ServletException(e);
			}
		}
		
		request.setAttribute("items", items);
        request.getRequestDispatcher("/WEB-INF/Checkout.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		Connection connection = null;
		
		if (firstName == null || firstName == "" || lastName == null || lastName == "" || email == null || email == "")
			doGet(request, response);
		else {
			int quantity = Integer.parseInt(request.getParameter("quantity"));
			int id = Integer.parseInt(request.getParameter("id"));
			String name = request.getParameter("name");
			double total = Double.parseDouble(request.getParameter("total"));
			
			Random random = new Random();
			int orderNum = random.nextInt(99999) + 1;
			
			try {
				Class.forName("com.mysql.jdbc.Driver");
				String url = "jdbc:mysql://cs3.calstatela.edu/cs3220stu51";
	            String username = "cs3220stu51";
	            String password = "ewgXn5dA";
	            String sql = "UPDATE Items SET quantity = quantity - ? WHERE id = ?";
	            
	            connection = DriverManager.getConnection(url, username, password);
	            PreparedStatement preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setInt(1, quantity);
				preparedStatement.setInt(2, id);
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
				catch (SQLException e){
					throw new ServletException(e);
				}
			}
			
			try {
				Class.forName("com.mysql.jdbc.Driver");
				String url = "jdbc:mysql://cs3.calstatela.edu/cs3220stu51";
	            String username = "cs3220stu51";
	            String password = "ewgXn5dA";
	            String sql = "INSERT INTO History (name, quantity, total, orderNum) VALUES (?, ?, ?, ?)";
	            
	            connection = DriverManager.getConnection(url, username, password);
	            PreparedStatement preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setString(1, name);
				preparedStatement.setInt(2, quantity);
				preparedStatement.setDouble(3, total);
				preparedStatement.setInt(4, orderNum);
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
				catch (SQLException e){
					throw new ServletException(e);
				}
			}
			
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
		}
		
		request.setAttribute("message", "Thank you for your purchase!");
		response.sendRedirect("History");
	}
}