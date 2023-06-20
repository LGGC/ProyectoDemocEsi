package vista;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import dao.JornadaDAO;
import dao.PropuestaDAO;
import modelo.Jornada;
import modelo.Propuesta;

import java.util.ArrayList;
import java.util.EventObject;

import javax.swing.border.BevelBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Component;
import java.awt.Font;



public class JornCPanel extends JornPaneles {
	private JTable tablaJornadas;
	private Jornada j = null;

	public JornCPanel() {
		setLayout(null);

		JornadaDAO jDAO = new JornadaDAO();
		
		JScrollPane panelTabla = new JScrollPane();
		panelTabla.setBounds(8, 10, 822, 442);
		
		JButton botonB = new JButton("Eliminar jornadas");
		botonB.setToolTipText("Permite eliminar jornadas.");
		botonB.setFont(new Font("Tahoma", Font.PLAIN, 12));
		botonB.setEnabled(false);
		tablaJornadas = new JTable(refrescarTabla(jDAO)) {
			 public boolean editCellAt(int row, int column, EventObject e) {
		            return false;
		         }
		};
		tablaJornadas.setCellSelectionEnabled(false);
		tablaJornadas.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
		    @Override
		    public void valueChanged(ListSelectionEvent event) {
		        if (tablaJornadas.getSelectedRow() > -1) {
		        	botonB.setEnabled(true);
		            j = jDAO.pedirUnaJornada(tablaJornadas.getValueAt(tablaJornadas.getSelectedRow(), 0).toString());
		        }
		    }
		});
		panelTabla.setViewportView(tablaJornadas);
		add(panelTabla);
		if (tablaJornadas.getRowCount() == 0){
			botonB.setEnabled(false);
		}
		botonB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(j != null) {
					jDAO.eliminarJornada(j.getTitulo());
					JFrame marco = (JFrame) SwingUtilities.getWindowAncestor((Component) e.getSource());
					marco.setContentPane(new JornCPanel());
					marco.validate();
				}
			}
		});
		botonB.setBounds(479, 509, 178, 28);
		add(botonB);
		JButton botonM = new JButton("A\u00F1adir/modificar jornada");
		botonM.setToolTipText("Permite a\u00F1adir y modificar jornadas a la tabla.");
		botonM.setFont(new Font("Tahoma", Font.PLAIN, 12));
		botonM.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame marco = (JFrame) SwingUtilities.getWindowAncestor((Component) e.getSource());
				marco.setContentPane(new JornMPanel(j));
				marco.validate();
			}
		});
		botonM.setBounds(182, 509, 232, 28);
		add(botonM);
	}
}
