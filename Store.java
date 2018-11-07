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

import OnlineStore.Items;

@WebServlet("/Store")
public class Store extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Items> items = new ArrayList<Items>();
		Connection connection = null;
		int counter = 0;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://cs3.calstatela.edu/cs3220stu51";
            String username = "cs3220stu51";
            String password = "ewgXn5dA";
            String sql = "SELECT * FROM Items";
            
            connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while(resultSet.next()) {
                Items item = new Items(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getString("description"), resultSet.getInt("quantity"), resultSet.getDouble("price"), resultSet.getDouble("total"), resultSet.getBoolean("cart"), resultSet.getInt("orderNum"));
                
                if (item.isCart())
                	counter++;
                
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
		request.setAttribute("counter", counter);
        request.getRequestDispatcher("/WEB-INF/Store.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Items> items = new ArrayList<Items>();
		String name = request.getParameter("name");
		Connection connection = null;
		
		if (name == null || name == "")
			doGet(request, response);
		else {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				String url = "jdbc:mysql://cs3.calstatela.edu/cs3220stu51";
	            String username = "cs3220stu51";
	            String password = "ewgXn5dA";
	            String sql = "SELECT * FROM Items WHERE name LIKE \"%\"? OR name LIKE \"%\"?\"%\" OR name LIKE ?\"%\"";
	            
	            connection = DriverManager.getConnection(url, username, password);
	            PreparedStatement preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setString(1, name);
				preparedStatement.setString(2, name);
				preparedStatement.setString(3, name);
				ResultSet resultSet = preparedStatement.executeQuery();
				
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
	        request.getRequestDispatcher("/WEB-INF/Store.jsp").forward(request, response);
		}
	}
}