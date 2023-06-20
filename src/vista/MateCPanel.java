package vista;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.util.EventObject;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import dao.MaterialDAO;
import modelo.Material;

import java.awt.event.ActionListener;
import java.awt.Component;
import java.awt.Font;
import java.awt.Panel;
import java.awt.event.ActionEvent;

public class MateCPanel extends MatePaneles{
	private JTable tablaMateriales;
	private Material m = null;

	/**
	 * Create the panel.
	 */
	public MateCPanel() {
		setLayout(null);
		
		MaterialDAO mDAO = new MaterialDAO();
		
		JScrollPane panelTabla = new JScrollPane();
		panelTabla.setBounds(20, 11, 708, 357);
		
		//Boton Baja Material
		JButton botonB = new JButton("Eliminar material");
		botonB.setToolTipText("Permite eliminar propuestas.");
		botonB.setFont(new Font("Tahoma", Font.PLAIN, 12));
		botonB.setEnabled(false);
		
		//Tabla
		tablaMateriales = new JTable(refrescarTabla(mDAO)) {
			public boolean editCellAt(int row, int column, EventObject e) {
				return false;
			}
		};
		tablaMateriales.setCellSelectionEnabled(false);
		//Obtener Material Seleccionado
		tablaMateriales.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent event) {
				if (tablaMateriales.getSelectedRow() > -1) {
					botonB.setEnabled(true);
					m = mDAO.pedirUnMaterial(tablaMateriales.getValueAt(tablaMateriales.getSelectedRow(), 0).toString());
				}
			}
		});
		
		panelTabla.setViewportView(tablaMateriales);
		add(panelTabla);
		
		//Desabilitar Boton Bajas
		if (tablaMateriales.getRowCount() == 0) {
			botonB.setEnabled(false);
		}
		
		botonB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(m != null) {
					mDAO.eliminarMaterial(m.getTitulo());
					JFrame marco = (JFrame) SwingUtilities.getWindowAncestor((Component) e.getSource());
					marco.setContentPane(new MateBPanel());
					marco.validate();
				}
			}
		});
		botonB.setBounds(414, 403, 181, 23);
		add(botonB);
		
		
		//Boton Añadir/Modifica Material
		JButton botonM = new JButton("A\u00F1adir/modificar material");
		botonM.setToolTipText("Permite a\u00F1adir y modificar materiales a la tabla.");
		botonM.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		botonM.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame marco = (JFrame) SwingUtilities.getWindowAncestor((Component) e.getSource());
				marco.setContentPane(new MateMPanel(m));
				marco.validate();
			}
		});
		botonM.setBounds(140, 403, 181, 23);
		add(botonM);
		
		
		

	}
}
