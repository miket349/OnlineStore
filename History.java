package OnlineStore;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
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

@WebServlet("/History")
public class History extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Items> items = new ArrayList<Items>();
		String message = request.getParameter("message");
		Connection connection = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://cs3.calstatela.edu/cs3220stu51";
            String username = "cs3220stu51";
            String password = "ewgXn5dA";
            String sql = "SELECT * FROM History";
            
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
		request.setAttribute("message", message);
        request.getRequestDispatcher("/WEB-INF/History.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}