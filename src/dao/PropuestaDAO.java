
package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import modelo.Propuesta;

public class PropuestaDAO extends DAOGeneral{
	
	public ArrayList<Propuesta> pedirPropuestas(){
		ArrayList<Propuesta> ap = new ArrayList<>();
		Connection c = null;
		String query = "SELECT * FROM propuestas";
		ResultSet rs = null;
		try {
			c=conectar();
			Statement stmt =c.createStatement();
			rs = stmt.executeQuery(query);
			while(rs.next()) {
				String origen = rs.getString("origen_propuesta");
				String categoria = rs.getString("nombre_categoria");
				String autor = rs.getString("autor_propuesta");
				Date fecha = rs.getDate("fecha_propuesta");
				String titulo = rs.getString("titulo_propuesta");
				String descripcion = rs.getString("descripcion_propuesta");
				String motivacion = rs.getString("motivacion_propuesta");
				String estado = rs.getString("estado_propuesta");
				String motivoRechazo = rs.getString("motivoRechazo_propuesta");
				ap.add(new Propuesta(origen, categoria, autor, fecha, titulo, descripcion, motivacion, estado, motivoRechazo));
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
	
	public ArrayList<Propuesta> pedirPropuestasDeMaterial(String titMaterial){
		ArrayList<Propuesta> ap = new ArrayList<>();
		Connection c = null;
		ResultSet rs = null;
		try {
			c=conectar();
			String query = "SELECT * FROM propuestas a INNER JOIN materiales_propuestas b ON a.titulo_propuesta = b.titulo_propuesta WHERE b.titulo_material = ?";
			PreparedStatement pStmt = c.prepareStatement(query);
			pStmt.setString(1, titMaterial);
			rs = pStmt.executeQuery();
			while(rs.next()) {
				String origen = rs.getString("origen_propuesta");
				String categoria = rs.getString("nombre_categoria");
				String autor = rs.getString("autor_propuesta");
				Date fecha = rs.getDate("fecha_propuesta");
				String titulo = rs.getString("titulo_propuesta");
				String descripcion = rs.getString("descripcion_propuesta");
				String motivacion = rs.getString("motivacion_propuesta");
				String estado = rs.getString("estado_propuesta");
				String motivoRechazo = rs.getString("motivoRechazo_propuesta");
				ap.add(new Propuesta(origen, categoria, autor, fecha, titulo, descripcion, motivacion, estado, motivoRechazo));
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
	
	public Propuesta pedirUnaPropuesta(String titABuscar){
		Propuesta p = new Propuesta();
		Connection c = null;
		ResultSet rs = null;
		try {
			c = conectar();
			String query = "SELECT * FROM propuestas WHERE titulo_propuesta = ?";
			PreparedStatement pStmt = c.prepareStatement(query);
			pStmt.setString(1, titABuscar);
			rs = pStmt.executeQuery();
			while(rs.next()) {
				String origen = rs.getString("origen_propuesta");
				String categoria = rs.getString("nombre_categoria");
				String autor = rs.getString("autor_propuesta");
				Date fecha = rs.getDate("fecha_propuesta");
				String titulo = rs.getString("titulo_propuesta");
				String descripcion = rs.getString("descripcion_propuesta");
				String motivacion = rs.getString("motivacion_propuesta");
				String estado = rs.getString("estado_propuesta");
				String motivoRechazo = rs.getString("motivoRechazo_propuesta");
				p = new Propuesta(origen, categoria, autor, fecha, titulo, descripcion, motivacion, estado, motivoRechazo);
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
		return p;
	}
	
	//Metodo Guardar Propuestas, devuelve True si es difer	String estadoActual = "pendiente de aprobación";ente de 0
	public boolean guardarPropuesta(Propuesta p) {
			int filasAfectadas = 0;
			Connection c = null;
			try {
				c = conectar();
				String query = "INSERT INTO propuestas (nombre_categoria,autor_propuesta,fecha_propuesta,titulo_propuesta,descripcion_propuesta,estado_propuesta,motivoRechazo_propuesta,motivacion_propuesta,origen_propuesta)VALUES(?,?,?,?,?,?,?,?,?)";
				PreparedStatement pStmt = c.prepareStatement(query);
				pStmt.setString(1, p.getCategoria());
				pStmt.setString(2, p.getAutor());
				pStmt.setDate(3, p.getFecha());
				pStmt.setString(4, p.getTitulo());
				pStmt.setString(5, p.getDescripcion());
				String estadoActual = "Pendiente de aprobación";
				p.setEstado(estadoActual);
				pStmt.setString(6, p.getEstado());
				pStmt.setString(7, p.getMotivoRechazo());
				pStmt.setString(8, p.getMotivacion());
				pStmt.setString(9, p.getOrigen());
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
	
	public boolean eliminarPropuesta(String titulo) {
		int filasAfectadas = 0;
		Connection c = null;
		try {
			DAOGeneral dGral = new DAOGeneral();
			c = dGral.conectar();
			PreparedStatement pStmt = c.prepareStatement("DELETE FROM propuestas WHERE titulo_propuesta = ?");
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
		return filasAfectadas == 1;
	}
	
	public boolean modificarPropuesta(Propuesta p) {
		int filasAfectadas = 0;
		Connection c = null;
		try {
			DAOGeneral dGral = new DAOGeneral();
			c = dGral.conectar();
			PreparedStatement pStmt = c.prepareStatement("UPDATE propuestas SET autor_propuesta = ?,descripcion_propuesta = ?,estado_propuesta = ?,fecha_propuesta = ?,motivacion_propuesta = ?,motivoRechazo_propuesta = ?,nombre_categoria = ?,origen_propuesta = ? WHERE titulo_propuesta = ?");
			pStmt.setString(1, p.getAutor());
			pStmt.setString(2, p.getDescripcion());
			pStmt.setString(3, p.getEstado());
			pStmt.setDate(4, p.getFecha());
			pStmt.setString(5, p.getMotivacion());
			pStmt.setString(6, p.getMotivoRechazo());
			pStmt.setString(7, p.getCategoria());
			pStmt.setString(8, p.getOrigen());
			pStmt.setString(9, p.getTitulo());
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
	
	public boolean rechazarPropuesta(Propuesta p) {
		int filasAfectadas = 0;
		Connection c = null;
		try {
			DAOGeneral dGral = new DAOGeneral();
			c = dGral.conectar();
			PreparedStatement pStmt = c.prepareStatement("UPDATE propuestas SET estado_propuesta  = ?, motivoRechazo_propuesta = ? WHERE titulo_propuesta = ?");
			pStmt.setString(1, p.getEstado());
			pStmt.setString(2, p.getMotivoRechazo());
			pStmt.setString(3, p.getTitulo());
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