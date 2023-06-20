package vista;

import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

import dao.CategoriaDAO;
import dao.JornadaDAO;
import dao.MaterialDAO;
import dao.PropuestaDAO;
import modelo.Jornada;
import modelo.Material;
import modelo.Propuesta;

public class MatePaneles extends JPanel {
	public MatePaneles() {
	}
	protected DefaultListModel<String> refrescarLista(MaterialDAO mDAO) {
		DefaultListModel<String> dLM = new DefaultListModel<String>();	
		ArrayList<Material> am = mDAO.pedirMateriales();
		for(Material m : am) {
			dLM.addElement(m.getTitulo());
		}
		return dLM;
	}
	
	protected DefaultListModel<String> refrescarListaMateriales(PropuestaDAO pDAO, String titABuscar) {
		DefaultListModel<String> dLM = new DefaultListModel<String>();	
		ArrayList<Propuesta> ap = pDAO.pedirPropuestasDeMaterial(titABuscar);
		for(Propuesta p : ap) {
			dLM.addElement(p.getTitulo());
		}
		return dLM;
	}
	
	protected DefaultTableModel refrescarTabla(MaterialDAO mDAO) {
		DefaultTableModel dTM = new DefaultTableModel();
		dTM.addColumn("Titulo");
		dTM.addColumn("Categoria");
		dTM.addColumn("Descripcion");
		dTM.addColumn("Fuente");
		dTM.addColumn("E.d.D");
		dTM.addColumn("Tipo");
		dTM.addColumn("Proritario");
		dTM.addColumn("Jornada");
		ArrayList<Material> am = mDAO.pedirMateriales();
		for(Material m : am) {
			String tipo = mDAO.pedirTipo(m.getTitulo());
			dTM.insertRow(0, new Object[] {m.getTitulo(), m.getCategoria(), m.getDescripcion(), m.getFuente(), m.getEnlaceDoc(), tipo, m.esPrioritario(), m.getJornada()});
		}
		return dTM;
	}
	
	protected DefaultComboBoxModel<String> conseguirCategorias(){
		CategoriaDAO cDAO = new CategoriaDAO();
		List<String> ls = cDAO.pedirCategorias();
		return new DefaultComboBoxModel<String>(ls.toArray(new String[0]));
	}
	
	protected DefaultComboBoxModel<String> conseguirJornadas() {
		JornadaDAO jDAO = new JornadaDAO();
		ArrayList<String> ls = new ArrayList<>();	
		ArrayList<Jornada> aj = jDAO.pedirJornadas();
		for(Jornada j : aj) {
			ls.add(j.getTitulo());
		}
		return new DefaultComboBoxModel<String>(ls.toArray(new String[0]));
	}
	
	protected DefaultComboBoxModel<String> conseguirTipos(){
		List<String> ls = new ArrayList<>(); 
		ls.add("institucional");
		ls.add("origen por propuesta");
		return new DefaultComboBoxModel<String>(ls.toArray(new String[0]));
	}
}