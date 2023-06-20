package vista;

import javax.swing.SwingUtilities;

import dao.PropuestaDAO;
import modelo.Propuesta;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTextField;



public class PropRechazar extends PropPaneles {
	private JTextField txtMotivo = new JTextField();;
	public PropRechazar(Propuesta p) {
		setLayout(null);
		PropuestaDAO pDAO = new PropuestaDAO();
		
		txtMotivo = new JTextField();
		txtMotivo.setBounds(294, 237, 201, 114);

		add(txtMotivo);
		txtMotivo.setColumns(10);
		
		JLabel lblRechazo = new JLabel("Motivos para rechazar "+p.getTitulo());
		lblRechazo.setBounds(294, 212, 317, 14);
		add(lblRechazo);
		
		
		JButton btnRechazar = new JButton("Rechazar");
		btnRechazar.setEnabled(!txtMotivo.getText().isBlank());
		btnRechazar.setBounds(564, 250, 89, 23);
		btnRechazar.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		p.setEstado("Rechazada");
		p.setMotivoRechazo(txtMotivo.getText().trim());
		pDAO.rechazarPropuesta(p);
		JFrame marco = (JFrame) SwingUtilities.getWindowAncestor((Component) e.getSource());
		marco.setContentPane(new PropCPanel());
		marco.validate();
			}
		});
		
		add(btnRechazar);
		
		JButton btnAtras = new JButton("Atr\u00E1s");
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame marco = (JFrame) SwingUtilities.getWindowAncestor((Component) e.getSource());
				marco.setContentPane(new PropVerDetalles(p));
				marco.validate();
			}
		});
		btnAtras.setBounds(564, 311, 89, 23);
		add(btnAtras);
	
		txtMotivo.getDocument().addDocumentListener(new DocumentListener() {
			  public void changedUpdate(DocumentEvent e) {
			    changed();
			  }
			  public void removeUpdate(DocumentEvent e) {
			    changed();
			  }
			  public void insertUpdate(DocumentEvent e) {
			    changed();
			  }

			  public void changed() {
				 String s = txtMotivo.getText();
			     if (!s.isBlank()){
			    	 btnRechazar.setEnabled(true);
			     } else {
			    	 btnRechazar.setEnabled(false);
			     }
			    }
			});
		add(txtMotivo);
		
		}
}
