package vista;

import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

import dao.JornadaDAO;
import dao.MaterialDAO;
import modelo.Jornada;
import modelo.Material;

public class JornPaneles extends JPanel {
	
	protected DefaultListModel<String> refrescarLista(JornadaDAO jDAO) {
		DefaultListModel<String> dLM = new DefaultListModel<String>();	
		ArrayList<Jornada> jor = jDAO.pedirJornadas();
		for(Jornada j : jor) {
			dLM.addElement(j.getTitulo());
		}
		return dLM;
	}
	
	protected DefaultTableModel refrescarTabla(JornadaDAO jDAO) {
		DefaultTableModel dTM = new DefaultTableModel();
		dTM.addColumn("Titulo");
		dTM.addColumn("Objetivo");
		dTM.addColumn("Referente");
		dTM.addColumn("Es prioritaria");
		ArrayList<Jornada> aj = jDAO.pedirJornadas();
       
		for(Jornada j : aj) {
			String s;
			if (j.esPrioritaria()) {
				s = "Si";
			} else {
				s = "No";
			}
			dTM.insertRow(0, new Object[] {j.getTitulo(), j.getObjetivo(), j.getReferenteInstitucional(), s});
		}
		return dTM;
	}
	
	protected DefaultListModel<String> refrescarMaterialesDisponibles(MaterialDAO mDAO, String tituloJorn) {
		DefaultListModel<String> dLM = new DefaultListModel<String>();	
		ArrayList<Material> am = mDAO.pedirMaterialesDisponibles(tituloJorn);
		for(Material m : am) {
			dLM.addElement(m.getTitulo());
		}
		return dLM;
	}

	protected DefaultListModel<String> refrescarMaterialesDeJornada(MaterialDAO mDAO, String tituloJorn) {
		DefaultListModel<String> dLM = new DefaultListModel<String>();	
		ArrayList<Material> am = mDAO.pedirMaterialesDeJornada(tituloJorn);
		for(Material m : am) {
			dLM.addElement(m.getTitulo());
		}
		return dLM;
	}
}
