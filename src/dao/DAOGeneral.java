package dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class DAOGeneral {
	private String url = "jdbc:mysql://localhost:3306/democesi";
	private String usr = "root";
	private String pass = "admin";
	
	public DAOGeneral(){}
	
	protected Connection conectar() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(getUrl(), getUsr(), getPass());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	public String getUrl() {
		return url;
	}

	public String getUsr() {
		return usr;
	}

	public String getPass() {
		return pass;
	}
}
