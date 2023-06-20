package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MatPropDAO extends DAOGeneral {
	public boolean asociarMatProp(String propAAsociar, String matAAsociar) {
		int filasAfectadas = 0;
		Connection c = null;
			try {
				c = conectar();
				String query = "INSERT INTO materiales_propuestas (titulo_propuesta,titulo_material) VALUES (?,?)";
				PreparedStatement pStmt = c.prepareStatement(query);
				pStmt.setString(1, propAAsociar);
				pStmt.setString(2, matAAsociar);
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
	
	public boolean desasociarMatProp(String propAAsociar, String matAAsociar) {
		int filasAfectadas = 0;
		Connection c = null;
		try {
			DAOGeneral dGral = new DAOGeneral();
			c = dGral.conectar();
			PreparedStatement pStmt = c.prepareStatement("DELETE FROM materiales_propuestas WHERE titulo_propuesta = ? AND titulo_material = ?");
			pStmt.setString(1, propAAsociar);
			pStmt.setString(2, matAAsociar);
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
}
