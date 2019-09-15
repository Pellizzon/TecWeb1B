package mvc.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {
	private Connection connection = null;
//	String url = System.getenv("mysql_url");
//	String user = System.getenv("mysql_user");
//	String password = System.getenv("mysql_password");

	String url = "jdbc:mysql://localhost/meus_dados";
	String user = "root";
	String password = "38117111";

	public UserDAO() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean verificaJaCadastrado(User pessoa) {
		boolean st = false;
		String sql = "SELECT * FROM cadastro WHERE user=?";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, pessoa.getUser());
			ResultSet rs = stmt.executeQuery();
			st = rs.next();
			stmt.execute();
			stmt.close();
			return st;
		} catch (SQLException e) {
			e.printStackTrace();
			return st;
		}
	}

	public boolean verificaLogin(User pessoa) {
		boolean st = false;
		String sql = "SELECT * FROM cadastro WHERE user=? and password=?";
		PreparedStatement stmt;
		try {
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, pessoa.getUser());
			stmt.setString(2, pessoa.getPassword());
			ResultSet rs = stmt.executeQuery();
			st = rs.next();
			stmt.execute();
			stmt.close();
			return st;
		} catch (SQLException e) {
			e.printStackTrace();
			return st;
		}
	}

	public void adicionaLogin(User pessoa) {
		String sql = "INSERT INTO cadastro (name, user, password) values (?,?,?)";
		PreparedStatement stmt;
		try {
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, pessoa.getName());
			stmt.setString(2, pessoa.getUser());
			stmt.setString(3, pessoa.getPassword());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String getCurrentUser(User pessoa) {
		String sql = "SELECT name FROM cadastro WHERE user=? and password=?";
		PreparedStatement stmt;
		try {
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, pessoa.getUser());
			stmt.setString(2, pessoa.getPassword());

			ResultSet rs = stmt.executeQuery();
			String name = null;

			if (rs.next()) {
				name = rs.getString("name");
			}

			stmt.execute();
			stmt.close();
			return name;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
	}
}