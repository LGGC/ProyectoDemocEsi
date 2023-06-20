package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import modelo.Material;
import modelo.MtOrigenPorPropuesta;

public class MtOrigenPorPropuestaDAO extends MaterialDAO{
	
	public ArrayList<Material> pedirMaterialesP(){
		ArrayList<Material> am = new ArrayList<>();
		Connection c = null;
		PropuestaDAO pDAO = new PropuestaDAO();
		String query = "SELECT * FROM materiales WHERE tipo_material = 'origen por propuesta'";
		ResultSet rs = null;
		try {
			c=conectar();
			Statement stmt =c.createStatement();
			rs = stmt.executeQuery(query);
			while(rs.next()) {
				String jornada = rs.getString("titulo_jornada");
				String titulo = rs.getString("titulo_material");
				String categoria = rs.getString("nombre_categoria");
				String descripcion = rs.getString("descripcion_material");
				String fuente = rs.getString("fuente_material");
				String enlaceDoc = rs.getString("enlaceDoc_material");
				am.add(new MtOrigenPorPropuesta(jornada, titulo, categoria, descripcion, fuente, enlaceDoc, pDAO.pedirPropuestasDeMaterial(titulo)));
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
		return am;
	}
	
	public ArrayList<Material> pedirMaterialesPDeJornada(String jornABuscar){
		ArrayList<Material> am = new ArrayList<>();
		Connection c = null;
		PropuestaDAO pDAO = new PropuestaDAO();
		String query = "SELECT * FROM materiales WHERE tipo_material = ? AND titulo_jornada = ?";
		ResultSet rs = null;
		try {
			c=conectar();
			PreparedStatement pStmt =c.prepareStatement(query);
			pStmt.setString(1, "origen por propuesta");
			pStmt.setString(2, jornABuscar);
			rs = pStmt.executeQuery();
			while(rs.next()) {
				String jornada = rs.getString("titulo_jornada");
				String titulo = rs.getString("titulo_material");
				String categoria = rs.getString("nombre_categoria");
				String descripcion = rs.getString("descripcion_material");
				String fuente = rs.getString("fuente_material");
				String enlaceDoc = rs.getString("enlaceDoc_material");
				am.add(new MtOrigenPorPropuesta(jornada, titulo, categoria, descripcion, fuente, enlaceDoc, pDAO.pedirPropuestasDeMaterial(titulo)));
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
		return am;
	}
	
	public boolean guardarMaterial(MtOrigenPorPropuesta mP) {
		int filasAfectadas = 0;
		Connection c = null;
			try {
				c = conectar();
				String query = "INSERT INTO materiales (titulo_jornada,tipo_material,titulo_material,nombre_categoria,descripcion_material,fuente_material,enlaceDoc_material,prioritario_material) VALUES (?,?,?,?,?,?,?,?)";
				PreparedStatement pStmt = c.prepareStatement(query);
				pStmt.setString(1, mP.getJornada());
				pStmt.setString(2, "origen por propuesta");
				pStmt.setString(3, mP.getTitulo());
				pStmt.setString(4, mP.getCategoria());
				pStmt.setString(5, mP.getDescripcion());
				pStmt.setString(6, mP.getFuente());
				pStmt.setString(7, mP.getEnlaceDoc());
				pStmt.setBoolean(8, mP.esPrioritario());
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
	
	public  boolean modificarMaterial(MtOrigenPorPropuesta mP) {
		int filasAfectadas = 0;
		Connection c = null;
		try {
			DAOGeneral dGral = new DAOGeneral();
			c = dGral.conectar();
			PreparedStatement pStmt = c.prepareStatement("UPDATE materiales SET titulo_jornada = ?, tipo_material = ?, nombre_categoria = ?, descripcion_material = ?, fuente_material = ?, enlaceDoc_material = ?, prioritario_material = ? WHERE titulo_material = ?");
			pStmt.setString(1, mP.getJornada());
			pStmt.setString(2, "institucional");
			pStmt.setString(3, mP.getCategoria());
			pStmt.setString(4, mP.getDescripcion());
			pStmt.setString(5, mP.getFuente());
			pStmt.setString(6, mP.getEnlaceDoc());
			pStmt.setBoolean(7, mP.esPrioritario());
			pStmt.setString(8, mP.getTitulo());
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
		return filasAfectadas == 1;
	}

	public ArrayList<Material> pedirMaterialesPDisponiblesParaJornada(String jornABuscar) {
		ArrayList<Material> am = new ArrayList<>();
		Connection c = null;
		PropuestaDAO pDAO = new PropuestaDAO();
		String query = "SELECT * FROM materiales WHERE tipo_material = ? AND NOT titulo_jornada = ?";
		ResultSet rs = null;
		try {
			c=conectar();
			PreparedStatement pStmt =c.prepareStatement(query);
			pStmt.setString(1, "origen por propuesta");
			pStmt.setString(2, jornABuscar);
			rs = pStmt.executeQuery();
			while(rs.next()) {
				String jornada = rs.getString("titulo_jornada");
				String titulo = rs.getString("titulo_material");
				String categoria = rs.getString("nombre_categoria");
				String descripcion = rs.getString("descripcion_material");
				String fuente = rs.getString("fuente_material");
				String enlaceDoc = rs.getString("enlaceDoc_material");
				am.add(new MtOrigenPorPropuesta(jornada, titulo, categoria, descripcion, fuente, enlaceDoc, pDAO.pedirPropuestasDeMaterial(titulo)));
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
		return am;
	}
	
	public ArrayList<MtOrigenPorPropuesta> pedirMaterialesDePropuesta(String propABuscar){
		ArrayList<MtOrigenPorPropuesta> am = new ArrayList<>();
		Connection c = null;
		PropuestaDAO pDAO = new PropuestaDAO();
		String query = "SELECT * FROM materiales a INNER JOIN materiales_propuestas b ON a.titulo_material = b.titulo_material WHERE b.titulo_propuesta = ?";
		ResultSet rs = null;
		try {
			c=conectar();
			PreparedStatement pStmt =c.prepareStatement(query);
			pStmt.setString(1, propABuscar);
			rs = pStmt.executeQuery();
			while(rs.next()) {
				String jornada = rs.getString("titulo_jornada");
				String titulo = rs.getString("titulo_material");
				String categoria = rs.getString("nombre_categoria");
				String descripcion = rs.getString("descripcion_material");
				String fuente = rs.getString("fuente_material");
				String enlaceDoc = rs.getString("enlaceDoc_material");
				am.add(new MtOrigenPorPropuesta(jornada, titulo, categoria, descripcion, fuente, enlaceDoc, pDAO.pedirPropuestasDeMaterial(titulo)));
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
		return am;
	}
	
	public ArrayList<MtOrigenPorPropuesta> pedirMaterialesDisponiblesParaPropuesta(String propABuscar){
		ArrayList<MtOrigenPorPropuesta> am = new ArrayList<>();
		Connection c = null;
		PropuestaDAO pDAO = new PropuestaDAO();
		String query = "SELECT * FROM materiales a WHERE NOT EXISTS (SELECT b.titulo_material from materiales_propuestas b INNER JOIN materiales a ON a.titulo_material = b.titulo_material WHERE b.titulo_propuesta = ?)";
		ResultSet rs = null;
		try {
			c=conectar();
			PreparedStatement pStmt =c.prepareStatement(query);
			pStmt.setString(1, propABuscar);
			rs = pStmt.executeQuery();
			while(rs.next()) {
				String jornada = rs.getString("titulo_jornada");
				String titulo = rs.getString("titulo_material");
				String categoria = rs.getString("nombre_categoria");
				String descripcion = rs.getString("descripcion_material");
				String fuente = rs.getString("fuente_material");
				String enlaceDoc = rs.getString("enlaceDoc_material");
				am.add(new MtOrigenPorPropuesta(jornada, titulo, categoria, descripcion, fuente, enlaceDoc, pDAO.pedirPropuestasDeMaterial(titulo)));
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
		return am;
	}
}