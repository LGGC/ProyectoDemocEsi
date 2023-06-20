package vista;

import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.JScrollPane;

import dao.MaterialDAO;
import modelo.Jornada;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JList;



public class AñadirMate extends JornPaneles {
	public AñadirMate(Jornada j) {
		MaterialDAO mDAO = new MaterialDAO();
		setLayout(null);
		
		JLabel lblMateExistentes = new JLabel("Materiales disponibles:");
		lblMateExistentes.setBounds(200, 193, 173, 14);
		add(lblMateExistentes);
		
		JScrollPane panelDisp = new JScrollPane();
		panelDisp.setBounds(200, 218, 123, 195);
		add(panelDisp);
		
		JButton btnEnviar = new JButton(">>>");
		btnEnviar.setBounds(358, 295, 89, 21);
		btnEnviar.setEnabled(false);
		add(btnEnviar);
		
		JList<String> listMateDisp = new JList<String>();
		listMateDisp.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JList<String> listMateJorn = new JList<String>();
		listMateJorn.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listMateDisp.setModel(refrescarMaterialesDisponibles(mDAO, j.getTitulo()));
		listMateJorn.setModel(refrescarMaterialesDeJornada(mDAO, j.getTitulo()));
		listMateDisp.addListSelectionListener(new ListSelectionListener() {
			@Override
		    public void valueChanged(ListSelectionEvent arg0) {
		        if(!arg0.getValueIsAdjusting()) {
		        	btnEnviar.setEnabled(!listMateDisp.isSelectionEmpty());
					if (!listMateDisp.isSelectionEmpty()) {
			        	listMateJorn.clearSelection();
			        }
		        }
			}
		});
		
		panelDisp.setViewportView(listMateDisp);
		
		
		JLabel lblMateEnJorn = new JLabel("Materiales de la jornada:");
		lblMateEnJorn.setBounds(463, 189, 217, 23);
		add(lblMateEnJorn);
		
		JScrollPane panelDeJornada = new JScrollPane();
		panelDeJornada.setBounds(463, 218, 123, 195);
		add(panelDeJornada);
		
		panelDeJornada.setViewportView(listMateJorn);
		
		btnEnviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mDAO.cambiarJornada(listMateDisp.getSelectedValue(), j.getTitulo());
				JFrame marco = (JFrame) SwingUtilities.getWindowAncestor((Component) e.getSource());
				marco.setContentPane(new AñadirMate(j));
				marco.validate();
			}
		});
		
		JButton btnAtras = new JButton("Atr\u00E1s");
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame marco = (JFrame) SwingUtilities.getWindowAncestor((Component) e.getSource());
				marco.setContentPane(new JornMPanel(j));
				marco.validate();
			}
		});
		btnAtras.setBounds(358, 484, 89, 23);
		add(btnAtras);
	}
}
