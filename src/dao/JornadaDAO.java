package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import modelo.Jornada;
import modelo.Propuesta;

public class JornadaDAO extends DAOGeneral{
	
	public ArrayList<Jornada> pedirJornadas(){
		ArrayList<Jornada> ap = new ArrayList<>();
		MaterialDAO mDAO = new MaterialDAO();
		Connection c = null;
		String query = String.format("SELECT * FROM %s", "jornadas");
		ResultSet rs = null;
		try {
			c=conectar();
			Statement stmt =c.createStatement();
			rs = stmt.executeQuery(query);
			while(rs.next()) {
				String referente = rs.getString("referente_jornada");
				String objetivo = rs.getString("objetivo_jornada");
				String titulo = rs.getString("titulo_jornada");
				ap.add(new Jornada(referente, objetivo, titulo,mDAO.pedirMaterialesDeJornada(titulo)));
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
		return ap;
	}
	
	public Jornada pedirUnaJornada(String JornadaQueBuscar){
		Jornada j = new Jornada();
		Connection c = null;
		ResultSet rs = null;
		try {
			c = conectar();
			String query = "SELECT * FROM jornadas WHERE titulo_jornada = ?";
			PreparedStatement pStmt = c.prepareStatement(query);
			pStmt.setString(1, JornadaQueBuscar);
			rs = pStmt.executeQuery();
			while(rs.next()) {
				String referente = rs.getString("referente_jornada");
				String objetivo = rs.getString("objetivo_jornada");
				String titulo = rs.getString("titulo_jornada");
				j = new Jornada(referente, objetivo, titulo);
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
		return j;
	}
	
	//Metodo Guardar jornadas, devuelve True si es diferente de 0
	public boolean guardarJornada(Jornada j) {
			int filasAfectadas = 0;
			Connection c = null;
			try {
				c = conectar();
				String query = "INSERT INTO jornadas(referente_jornada,objetivo_jornada,titulo_jornada) VALUES(?,?,?)";
				PreparedStatement pStmt = c.prepareStatement(query);
				pStmt.setString(1, j.getReferenteInstitucional());
				pStmt.setString(2, j.getObjetivo());
				pStmt.setString(3, j.getTitulo());
				filasAfectadas = pStmt.executeUpdate();
			} catch (SQLException ex) {
				ex.printStackTrace();
			} finally {
				try {
					if (c != null) {
						c.close();
					}
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
			return filasAfectadas != 0;
	}
	
	public boolean eliminarJornada(String titulo) {
		int filasAfectadas = 0;
		Connection c = null;
		try {
			DAOGeneral dGral = new DAOGeneral();
			c = dGral.conectar();
			PreparedStatement pStmt = c.prepareStatement("DELETE FROM jornadas WHERE titulo_jornada = ?");
			pStmt.setString(1,titulo);
			filasAfectadas = pStmt.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (c != null) {
					c.close();
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		return filasAfectadas >= 1;
	}
	
	public boolean modificarJornada(Jornada j) {
		int filasAfectadas = 0;
		Connection c = null;
		try {
			DAOGeneral dGral = new DAOGeneral();
			c = dGral.conectar();
			PreparedStatement pStmt = c.prepareStatement("UPDATE jornadas SET referente_jornada = ?,objetivo_jornada = ? WHERE titulo_jornada = ?");
			pStmt.setString(1, j.getReferenteInstitucional());
			pStmt.setString(2, j.getObjetivo());
			pStmt.setString(3, j.getTitulo());
			filasAfectadas = pStmt.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (c != null) {
					c.close();
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		return filasAfectadas >= 1;
	}

	
}