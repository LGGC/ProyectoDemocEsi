package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


import modelo.Material;
import modelo.MtInstitucional;


public class MtInstitucionalDAO extends MaterialDAO{
	
	//Trae todos los registros de los materiales institucionales
	public ArrayList<Material> pedirMaterialesI(){
		ArrayList<Material> am = new ArrayList<>();
		Connection c = null;
		String query = "SELECT * FROM materiales WHERE tipo_material = 'institucional'";
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
				String procedencia = rs.getString("procedencia_material");
				boolean prioritario = rs.getBoolean("prioritario_material");
				am.add(new MtInstitucional(jornada, titulo, categoria, descripcion, fuente, enlaceDoc, procedencia, prioritario));
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
	
	public ArrayList<Material> pedirMaterialesIDeJornada(String jornABuscar){
		ArrayList<Material> am = new ArrayList<>();
		Connection c = null;
		String query = "SELECT * FROM materiales WHERE tipo_material = ? AND titulo_jornada = ?";
		ResultSet rs = null;
		try {
			c=conectar();
			PreparedStatement pStmt =c.prepareStatement(query);
			pStmt.setString(1, "institucional");
			pStmt.setString(2, jornABuscar);
			rs = pStmt.executeQuery();
			while(rs.next()) {
				String jornada = rs.getString("titulo_jornada");
				String titulo = rs.getString("titulo_material");
				String categoria = rs.getString("nombre_categoria");
				String descripcion = rs.getString("descripcion_material");
				String fuente = rs.getString("fuente_material");
				String enlaceDoc = rs.getString("enlaceDoc_material");
				String procedencia = rs.getString("procedencia_material");
				boolean prioritario = rs.getBoolean("prioritario_material");
				am.add(new MtInstitucional(jornada, titulo, categoria, descripcion, fuente, enlaceDoc, procedencia, prioritario));
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
	
	public ArrayList<Material> pedirMaterialesIDisponiblesParaJornada(String jornABuscar){
		ArrayList<Material> am = new ArrayList<>();
		Connection c = null;
		String query = "SELECT * FROM materiales WHERE tipo_material = ? AND NOT titulo_jornada = ?";
		ResultSet rs = null;
		try {
			c=conectar();
			PreparedStatement pStmt =c.prepareStatement(query);
			pStmt.setString(1, "institucional");
			pStmt.setString(2, jornABuscar);
			rs = pStmt.executeQuery();
			while(rs.next()) {
				String jornada = rs.getString("titulo_jornada");
				String titulo = rs.getString("titulo_material");
				String categoria = rs.getString("nombre_categoria");
				String descripcion = rs.getString("descripcion_material");
				String fuente = rs.getString("fuente_material");
				String enlaceDoc = rs.getString("enlaceDoc_material");
				String procedencia = rs.getString("procedencia_material");
				boolean prioritario = rs.getBoolean("prioritario_material");
				am.add(new MtInstitucional(jornada, titulo, categoria, descripcion, fuente, enlaceDoc, procedencia, prioritario));
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
	
	//Metodo Guardar Material, devuelve True si es diferente de 0
	public boolean guardarMaterial(MtInstitucional mI) {
		int filasAfectadas = 0;
		Connection c = null;
			try {
				c = conectar();
				String query = "INSERT INTO materiales (titulo_jornada,tipo_material,titulo_material,nombre_categoria,descripcion_material,fuente_material,enlaceDoc_material,procedencia_material,prioritario_material) VALUES (?,?,?,?,?,?,?,?,?)";
				PreparedStatement pStmt = c.prepareStatement(query);
				pStmt.setString(1, mI.getJornada());
				pStmt.setString(2, "institucional");
				pStmt.setString(3, mI.getTitulo());
				pStmt.setString(4, mI.getCategoria());
				pStmt.setString(5, mI.getDescripcion());
				pStmt.setString(6, mI.getFuente());
				pStmt.setString(7, mI.getEnlaceDoc());
				pStmt.setString(8, mI.getProcedencia());
				pStmt.setBoolean(9, mI.esPrioritario());
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
	
	//Modificar un Material filtrando por su titulo
	public  boolean modificarMaterial(MtInstitucional mI) {
		int filasAfectadas = 0;
		Connection c = null;
		try {
			DAOGeneral dGral = new DAOGeneral();
			c = dGral.conectar();
			PreparedStatement pStmt = c.prepareStatement("UPDATE materiales SET titulo_jornada = ?, tipo_material = ?, nombre_categoria = ?, descripcion_material = ?, fuente_material = ?, enlaceDoc_material = ?, procedencia_material = ?, prioritario_material = ? WHERE titulo_material = ?");
			pStmt.setString(1, mI.getJornada());
			pStmt.setString(2, "institucional");
			pStmt.setString(3, mI.getCategoria());
			pStmt.setString(4, mI.getDescripcion());
			pStmt.setString(5, mI.getFuente());
			pStmt.setString(6, mI.getEnlaceDoc());
			pStmt.setString(7, mI.getProcedencia());
			pStmt.setBoolean(8, mI.esPrioritario());
			pStmt.setString(9, mI.getTitulo());
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
}