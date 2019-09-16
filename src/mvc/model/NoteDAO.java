package mvc.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class NoteDAO {
	private Connection connection = null;
	String url = System.getenv("mysql_url");
	String user = System.getenv("mysql_user");
	String password = System.getenv("mysql_password");

//	String url = "jdbc:mysql://localhost/meus_dados";
//	String user = "root";
//	String password = "38117111";

	public NoteDAO() {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

	}

	public void adicionaNota(Notas note) {
		String sql = "INSERT INTO notes (user, date, priority, msg) values (?,?,?,?)";
		PreparedStatement stmt;
		try {
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, note.getUser());
			stmt.setDate(2, new java.sql.Date(note.getDate().getTimeInMillis()));
			stmt.setString(3, note.getPriority());
			stmt.setString(4, note.getMsg());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void removeNote(Integer id) {
		PreparedStatement stmt;
		try {
			stmt = connection.prepareStatement("DELETE FROM notes WHERE id=?");
			stmt.setLong(1, id);
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void updateNote(Notas note) {
		String sql = "UPDATE notes SET user=?, date=?, priority=?, msg=? WHERE id=?";
		PreparedStatement stmt;
		try {
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, note.getUser());
			stmt.setDate(2, new java.sql.Date(note.getDate().getTimeInMillis()));
			stmt.setString(3, note.getPriority());
			stmt.setString(4, note.getMsg());
			stmt.setInt(5, note.getId());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public String getCurrentDate(Notas note) {
		String sql = "SELECT date FROM notes WHERE id=?";
		PreparedStatement stmt;
		try {
			stmt = connection.prepareStatement(sql);
			stmt.setInt(1, note.getId());

			ResultSet rs = stmt.executeQuery();
			String date = null;

			if (rs.next()) {
				date = rs.getString("date");
			}

			stmt.execute();
			stmt.close();
			return date;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}

	public List<Notas> getFiltro(String obj) {
		List<Notas> notes = new ArrayList<Notas>();
		PreparedStatement stmt;
		try {
			if (obj.contentEquals("priority")) {
				stmt = connection.prepareStatement("SELECT * FROM notes ORDER BY priority DESC");
			} else if (obj.contentEquals("user")) {
				stmt = connection.prepareStatement("SELECT * FROM notes ORDER BY user");
			} else if (obj.contentEquals("date")) {
				stmt = connection.prepareStatement("SELECT * FROM notes ORDER BY date");
			} else {
				stmt = connection.prepareStatement("SELECT * FROM notes");
			}
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Notas note = new Notas();
				note.setId(rs.getInt("id"));
				note.setUser(rs.getString("user"));
				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("date"));
				note.setDate(data);
				note.setPriority(rs.getString("priority"));
				note.setMsg(rs.getString("msg"));
				notes.add(note);
			}
			rs.close();
			stmt.close();
			return notes;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public void close() throws SQLException {
		connection.close();
	}
}
