package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import modelo.Material;
import modelo.MtInstitucional;
import modelo.MtOrigenPorPropuesta;

public class MaterialDAO extends DAOGeneral{
	public ArrayList<Material> pedirMateriales(){
		ArrayList<Material> am = new ArrayList<>();
		MtInstitucionalDAO mTDAO = new MtInstitucionalDAO();
		MtOrigenPorPropuestaDAO mPDAO = new MtOrigenPorPropuestaDAO();
		am.addAll(mTDAO.pedirMaterialesI());
		am.addAll(mPDAO.pedirMaterialesP());
		organizar(am);
		return am;
	}
	
	
	public ArrayList<Material> pedirMaterialesDeJornada(String jornABuscar){
		ArrayList<Material> am = new ArrayList<>();
		MtInstitucionalDAO mTDAO = new MtInstitucionalDAO();
		MtOrigenPorPropuestaDAO mPDAO = new MtOrigenPorPropuestaDAO();
		am.addAll(mTDAO.pedirMaterialesIDeJornada(jornABuscar));
		am.addAll(mPDAO.pedirMaterialesPDeJornada(jornABuscar));
		organizar(am);
		return am;
	}
	
	private ArrayList<Material> organizar(ArrayList<Material> am) {
		am.sort((o1, o2) -> o1.getTitulo().compareTo(o2.getTitulo()));
		return am;
	}
	
	//Trae un unico registro de Material filtrando por el titulo del material
	public Material pedirUnMaterial(String titABuscar){
		Material m = null;
		Connection c = null;
		ResultSet rs = null;
		PropuestaDAO pDAO = new PropuestaDAO();
		try {
			c = conectar();
			String query = "SELECT * FROM materiales WHERE titulo_material = ?";
			PreparedStatement pStmt = c.prepareStatement(query);
			pStmt.setString(1, titABuscar);
			rs = pStmt.executeQuery();
			while(rs.next()) {
				String jornada = rs.getString("titulo_jornada");
				String tipo = rs.getString("tipo_material");
				String titulo = rs.getString("titulo_material");
				String categoria = rs.getString("nombre_categoria");
				String descripcion = rs.getString("descripcion_material");
				String fuente = rs.getString("fuente_material");
				String enlaceDoc = rs.getString("enlaceDoc_material");
				String procedencia = rs.getString("procedencia_material");

				if(tipo == "institucional") {
					m = new MtInstitucional(jornada, titulo, categoria, descripcion, fuente, enlaceDoc, procedencia, false);
				} else if (tipo == "origen por propuesta") {
					m = new MtOrigenPorPropuesta(jornada, titulo, categoria, descripcion, fuente, enlaceDoc, pDAO.pedirPropuestasDeMaterial(titulo));
				}
				
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
		return m;
	}
	
	public boolean eliminarMaterial(String titulo) {
		int filasAfectadas = 0;
		Connection c = null;
		try {
			DAOGeneral dGral = new DAOGeneral();
			c = dGral.conectar();
			PreparedStatement pStmt = c.prepareStatement("DELETE FROM materiales WHERE titulo_material = ?");
			pStmt.setString(1, titulo);
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
	};
	
	
	public String pedirTipo(String titABuscar) {
		String s = "";
		Connection c = null;
		ResultSet rs = null;
		try {
			c = conectar();
			String query = "SELECT tipo_material FROM materiales WHERE titulo_material = ?";
			PreparedStatement pStmt = c.prepareStatement(query);
			pStmt.setString(1, titABuscar);
			rs = pStmt.executeQuery();
			while(rs.next()) {
				rs.getString("tipo_material");
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
		return s;
	}


	public ArrayList<Material> pedirMaterialesDisponibles(String jornABuscar) {
		ArrayList<Material> am = new ArrayList<>();
		MtInstitucionalDAO mTDAO = new MtInstitucionalDAO();
		MtOrigenPorPropuestaDAO mPDAO = new MtOrigenPorPropuestaDAO();
		am.addAll(mTDAO.pedirMaterialesIDisponiblesParaJornada(jornABuscar));
		am.addAll(mPDAO.pedirMaterialesPDisponiblesParaJornada(jornABuscar));
		organizar(am);
		return am;
	}
	
	public  boolean cambiarJornada(String titMaterial, String titJornada) {
		int filasAfectadas = 0;
		Connection c = null;
		try {
			DAOGeneral dGral = new DAOGeneral();
			c = dGral.conectar();
			PreparedStatement pStmt = c.prepareStatement("UPDATE materiales SET titulo_jornada = ? WHERE titulo_material = ?");
			pStmt.setString(1, titJornada);
			pStmt.setString(2, titMaterial);
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
