package vista;

import javax.swing.SwingUtilities;

import dao.PropuestaDAO;
import modelo.Propuesta;

import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Component;
import javax.swing.JLabel;


public class PropVerDetalles extends PropPaneles {
	
	public PropVerDetalles(Propuesta p) {
		setLayout(null);
		
		PropuestaDAO pDAO = new PropuestaDAO();
		
		JButton btnAceptar = new JButton("Aceptar propuesta");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame marco = (JFrame) SwingUtilities.getWindowAncestor((Component) e.getSource());
				marco.setContentPane(new PropAceptar(p));
				marco.validate();
			}
		});
		btnAceptar.setBounds(176, 363, 191, 23);
		add(btnAceptar);
		
		JButton btnRechazar = new JButton("Rechazar propuesta");
		btnRechazar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame marco = (JFrame) SwingUtilities.getWindowAncestor((Component) e.getSource());
				marco.setContentPane(new PropRechazar(p));
				marco.validate();
			}
		});
		btnRechazar.setBounds(482, 363, 207, 23);
		add(btnRechazar);
		
		JButton btnAtras = new JButton("Atr\u00E1s");
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame marco = (JFrame) SwingUtilities.getWindowAncestor((Component) e.getSource());
				marco.setContentPane(new PropCPanel());
				marco.validate();
			}
			
		});
		btnAtras.setBounds(379, 414, 89, 23);
		add(btnAtras);
		
		JLabel lblTitulo = new JLabel(p.getTitulo());
		lblTitulo.setBounds(148, 74, 244, 14);
		add(lblTitulo);
		
		JLabel lblOrigen = new JLabel("Origen: " + p.getOrigen());
		lblOrigen.setBounds(148, 99, 503, 14);
		add(lblOrigen);
		
		JLabel lblCategora = new JLabel("Categor\u00EDa: " + p.getCategoria());
		lblCategora.setBounds(148, 124, 503, 14);
		add(lblCategora);
		
		JLabel lblDescripcin = new JLabel("Descripci\u00F3n: " + p.getDescripcion());
		lblDescripcin.setBounds(148, 149, 503, 14);
		add(lblDescripcin);
		
		JLabel lblMotivacin = new JLabel("Motivaci\u00F3n: " + p.getMotivacion());
		lblMotivacin.setBounds(148, 174, 503, 14);
		add(lblMotivacin);
		
		JLabel lblEstado = new JLabel("Estado: " + p.getEstado());
		lblEstado.setBounds(148, 209, 503, 14);
		add(lblEstado);
		
		
		}
}
