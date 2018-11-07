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

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ShoppingCart")
public class ShoppingCart extends HttpServlet {
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
        request.getRequestDispatcher("/WEB-INF/ShoppingCart.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("quantity") == null || request.getParameter("quantity") == "") 
			response.sendRedirect("Store");
		else {
			int id = Integer.parseInt(request.getParameter("id"));
			String name = request.getParameter("name");
			int quantity = Integer.parseInt(request.getParameter("quantity"));
			double price = Double.parseDouble(request.getParameter("price"));
			double total = price * quantity;
			boolean cart = request.getParameter("cart") != null;
			Connection connection = null;
			
			try {
				Class.forName("com.mysql.jdbc.Driver");
				String url = "jdbc:mysql://cs3.calstatela.edu/cs3220stu51";
		        String username = "cs3220stu51";
		        String password = "ewgXn5dA";
	            String sql = "INSERT INTO ShoppingCart (id, name, quantity, price, total, cart) VALUES (?, ?, ?, ?, ?, ?)";
		            
		        connection = DriverManager.getConnection(url, username, password);
		        PreparedStatement preparedStatement = connection.prepareStatement(sql);
		        preparedStatement.setInt(1, id);
				preparedStatement.setString(2, name);
				preparedStatement.setInt(3, quantity);
				preparedStatement.setDouble(4, price);
				preparedStatement.setDouble(5, total);
				preparedStatement.setBoolean(6, cart);
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
				
			doGet(request, response);
		}
	}
}
