package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import modelo.Propuesta;

public class CategoriaDAO extends DAOGeneral {
	public List<String> pedirCategorias(){
		List<String> ls = new ArrayList<String>();
		Connection c = null;
		String query = String.format("SELECT nombre_categoria FROM %s", "categorias");
		ResultSet rs = null;
		try {
			c=conectar();
			Statement stmt =c.createStatement();
			rs = stmt.executeQuery(query);
			while(rs.next()) {
				String categoria = rs.getString("nombre_categoria");
				ls.add(categoria);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if (c != null) {
				try {
					c.close();
				} catch (SQLException e){
					e.printStackTrace();
				}
			}
		}
		return ls;
	}
}
