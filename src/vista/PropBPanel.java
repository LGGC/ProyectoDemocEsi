package vista;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import dao.PropuestaDAO;
import modelo.Propuesta;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;

public class PropBPanel extends PropPaneles  {

	/**
	 * Create the panel.
	 */
	public PropBPanel() {
		setLayout(null);
		
		PropuestaDAO pDAO = new PropuestaDAO();
		
		JScrollPane panelTabla = new JScrollPane();
		panelTabla.setBounds(231, 171, 167, 236);
		add(panelTabla);
		
		JList<String> listaProp = new JList<String>();
		listaProp.setModel(refrescarLista(pDAO));
		
		JButton btnEliminar = new JButton("Borrar");
		btnEliminar.setEnabled(false);
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String titulo = listaProp.getSelectedValue();
				if (JOptionPane.showConfirmDialog(null, "¿Está seguro de que quiere eliminar \""+ titulo + "\"?") == 0) {
					pDAO.eliminarPropuesta(titulo);	
				}
				JFrame marco = (JFrame) SwingUtilities.getWindowAncestor((Component) e.getSource());
				marco.setContentPane(new PropBPanel());
				marco.validate();
			}
		});
		btnEliminar.setBounds(458, 275, 89, 23);
		add(btnEliminar);
		
		listaProp.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		panelTabla.setViewportView(listaProp);
		listaProp.addListSelectionListener(new ListSelectionListener() {
			@Override
		    public void valueChanged(ListSelectionEvent arg0) {
		        if (!arg0.getValueIsAdjusting()) {
		        	btnEliminar.setEnabled(true);
		        }
		    }
		});
		
		JButton btnAtras = new JButton("Atr\u00E1s");
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame marco = (JFrame) SwingUtilities.getWindowAncestor((Component) e.getSource());
				marco.setContentPane(new PropCPanel());
				marco.validate();
			}
		});
		btnAtras.setBounds(458, 313, 89, 23);
		add(btnAtras);

	}
}
