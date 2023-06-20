package vista;

import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

import dao.MtOrigenPorPropuestaDAO;
import dao.PropuestaDAO;
import modelo.MtOrigenPorPropuesta;
import modelo.Propuesta;

public class PropPaneles extends JPanel {
	
	protected DefaultListModel<String> refrescarLista(PropuestaDAO pDAO) {
		DefaultListModel<String> dLM = new DefaultListModel<String>();	
		ArrayList<Propuesta> ap = pDAO.pedirPropuestas();
		for(Propuesta p : ap) {
			dLM.addElement(p.getTitulo());
		}
		ap = organizar(ap);
		return dLM;
	}
	
	private ArrayList<Propuesta> organizar(ArrayList<Propuesta> ap) {
		ap.sort((o1, o2) -> o1.getTitulo().compareTo(o2.getTitulo()));
		return ap;
	}
	
	protected DefaultTableModel refrescarTabla(PropuestaDAO pDAO) {
		DefaultTableModel dTM = new DefaultTableModel();
		dTM.addColumn("Titulo");
		dTM.addColumn("Origen");
		dTM.addColumn("Categoria");
		dTM.addColumn("Autor");
		dTM.addColumn("Fecha");
		dTM.addColumn("Descripcion");
		dTM.addColumn("Motivacion");
		dTM.addColumn("Estado");
		dTM.addColumn("M.d.R.");		
		ArrayList<Propuesta> ap = pDAO.pedirPropuestas();
		for(Propuesta p : ap) {
			dTM.insertRow(0, new Object[] {p.getTitulo(), p.getOrigen(), p.getCategoria(), p.getAutor(), p.getFecha().toString(), p.getDescripcion(), p.getMotivacion(), p.getEstado(),p.getMotivoRechazo()});
		}
		return dTM;
	}
	
	protected DefaultTableModel refrescarTablaParaUnaPropuesta(PropuestaDAO pDAO,String titABuscar ) {
		DefaultTableModel dTM = new DefaultTableModel();
		dTM.addColumn("Titulo");
		dTM.addColumn("Origen");
		dTM.addColumn("Categoria");
		dTM.addColumn("Autor");
		dTM.addColumn("Fecha");
		dTM.addColumn("Descripcion");
		dTM.addColumn("Motivacion");
		dTM.addColumn("Estado");
		dTM.addColumn("M.d.R.");		
		Propuesta p = pDAO.pedirUnaPropuesta( titABuscar);
			dTM.insertRow(0, new Object[] {p.getTitulo(), p.getOrigen(), p.getCategoria(), p.getAutor(), p.getFecha().toString(), p.getDescripcion(), p.getMotivacion(), p.getEstado(),p.getMotivoRechazo()});
		return dTM;
	}
	
	protected DefaultListModel<String> refrescarMaterialesDePropuesta(MtOrigenPorPropuestaDAO mPDAO, String titulo) {
		ArrayList<MtOrigenPorPropuesta> am = mPDAO.pedirMaterialesDePropuesta(titulo);
		DefaultListModel<String> dLM = new DefaultListModel<String>();
		for (MtOrigenPorPropuesta mP : am) {
			dLM.addElement(mP.getTitulo());
		}
		return dLM;
	}

	protected DefaultListModel<String> refrescarMaterialesDisponibles(MtOrigenPorPropuestaDAO mPDAO, String titulo) {
		ArrayList<MtOrigenPorPropuesta> am = mPDAO.pedirMaterialesDisponiblesParaPropuesta(titulo);
		DefaultListModel<String> dLM = new DefaultListModel<String>();
		for (MtOrigenPorPropuesta mP : am) {
			dLM.addElement(mP.getTitulo());
		}
		return dLM;
	}
}
