package vista;

import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.JScrollPane;

import dao.PropuestaDAO;
import modelo.Propuesta;

import java.util.EventObject;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Component;
import java.awt.Font;



public class PropCPanel extends PropPaneles {
	private JTable tablaPropuestas;
	private Propuesta p = null;

	public PropCPanel() {
		setLayout(null);

		PropuestaDAO pDAO = new PropuestaDAO();
		
		JScrollPane panelTabla = new JScrollPane();
		panelTabla.setBounds(8, 10, 822, 442);
		
		JButton botonB = new JButton("Eliminar propuestas");
		botonB.setToolTipText("Permite eliminar propuestas.");
		botonB.setFont(new Font("Tahoma", Font.PLAIN, 12));
		botonB.setEnabled(false);
		
		JButton btnVerDetalles = new JButton("Ver detalles de la propuesta");
		btnVerDetalles.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnVerDetalles.setEnabled(false);
		
		tablaPropuestas = new JTable(refrescarTabla(pDAO)) {
			 public boolean editCellAt(int row, int column, EventObject e) {
		            return false;
		         }
		};
		tablaPropuestas.setCellSelectionEnabled(false);
		tablaPropuestas.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
		    @Override
		    public void valueChanged(ListSelectionEvent event) {
		    	int fila = tablaPropuestas.getSelectedRow();
		        if (fila > -1) {
		        	botonB.setEnabled(true);
		        	btnVerDetalles.setEnabled(true);
		            p = pDAO.pedirUnaPropuesta(tablaPropuestas.getValueAt(fila, 0).toString());
		        }
		        btnVerDetalles.setEnabled(tablaPropuestas.getValueAt(fila, 7).toString().equals("Pendiente de aprobación"));
		    }
		});
		panelTabla.setViewportView(tablaPropuestas);
		add(panelTabla);
		
		if (tablaPropuestas.getRowCount() == 0){
			botonB.setEnabled(false);
			btnVerDetalles.setEnabled(false);
		}
		
		botonB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(p != null) {
					pDAO.eliminarPropuesta(p.getTitulo());
					JFrame marco = (JFrame) SwingUtilities.getWindowAncestor((Component) e.getSource());
					marco.setContentPane(new PropCPanel());
					marco.validate();
				}
			}
		});
		botonB.setBounds(479, 509, 178, 28);
		add(botonB);
		
		JButton botonM = new JButton("A\u00F1adir/modificar propuesta");
		botonM.setToolTipText("Permite a\u00F1adir y modificar propuestas a la tabla.");
		botonM.setFont(new Font("Tahoma", Font.PLAIN, 12));
		botonM.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame marco = (JFrame) SwingUtilities.getWindowAncestor((Component) e.getSource());
				marco.setContentPane(new PropMPanel(p));
				marco.validate();
			}
		});
		botonM.setBounds(182, 509, 232, 28);
		add(botonM);
		
		btnVerDetalles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame marco = (JFrame) SwingUtilities.getWindowAncestor((Component) e.getSource());
				marco.setContentPane(new PropVerDetalles(p));
				marco.validate();
				
			}
		});
		btnVerDetalles.setBounds(308, 548, 248, 23);
		add(btnVerDetalles);
	}
}
