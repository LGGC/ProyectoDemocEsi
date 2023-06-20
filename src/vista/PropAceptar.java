package vista;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import dao.MatPropDAO;
import dao.MtOrigenPorPropuestaDAO;
import dao.PropuestaDAO;
import modelo.Propuesta;

public class PropAceptar extends PropPaneles {

	public PropAceptar(Propuesta p) {
		MtOrigenPorPropuestaDAO mPDAO = new MtOrigenPorPropuestaDAO();
		MatPropDAO matPropDAO = new MatPropDAO();
		PropuestaDAO pDAO = new PropuestaDAO();
		setLayout(null);
		
		p.setEstado("Aceptada");
		p.setMotivoRechazo("");
		pDAO.modificarPropuesta(p);
		
		JLabel lblMateExistentes = new JLabel("Materiales disponibles:");
		lblMateExistentes.setBounds(200, 193, 173, 14);
		add(lblMateExistentes);
		
		JScrollPane panelDisp = new JScrollPane();
		panelDisp.setBounds(200, 218, 123, 195);
		add(panelDisp);
		
		JButton btnEnviar = new JButton(">>>");
		btnEnviar.setBounds(364, 287, 89, 21);
		btnEnviar.setEnabled(false);
		add(btnEnviar);
		
		JButton btnDesasociar = new JButton("<<<");
		btnDesasociar.setEnabled(false);
		btnDesasociar.setBounds(364, 319, 89, 21);
		add(btnDesasociar);
		
		JList<String> listMateDisp = new JList<String>();
		JList<String> listMateProp = new JList<String>();
		listMateDisp.setModel(refrescarMaterialesDisponibles(mPDAO, p.getTitulo()));
		listMateProp.setModel(refrescarMaterialesDePropuesta(mPDAO, p.getTitulo()));
		listMateDisp.addListSelectionListener(new ListSelectionListener() {
			@Override
		    public void valueChanged(ListSelectionEvent arg0) {
		        if(!arg0.getValueIsAdjusting()) {
		        	btnEnviar.setEnabled(!listMateDisp.isSelectionEmpty());
		        	btnDesasociar.setEnabled(listMateDisp.isSelectionEmpty());
					if (!listMateDisp.isSelectionEmpty()) {
			        	listMateProp.clearSelection();
			        }
		        }
			}
		});
		listMateProp.addListSelectionListener(new ListSelectionListener() {
			@Override
		    public void valueChanged(ListSelectionEvent arg0) {
		        if(!arg0.getValueIsAdjusting()) {
		        	btnEnviar.setEnabled(listMateProp.isSelectionEmpty());
		        	btnDesasociar.setEnabled(!listMateProp.isSelectionEmpty());
					if (!listMateProp.isSelectionEmpty()) {
			        	listMateDisp.clearSelection();
			        }
		        }
			}
		});
		panelDisp.setViewportView(listMateDisp);
		
		
		JLabel lblMateEnJorn = new JLabel("Materiales de la propuesta:");
		lblMateEnJorn.setBounds(463, 189, 217, 23);
		add(lblMateEnJorn);
		
		JScrollPane panelDePropuesta = new JScrollPane();
		panelDePropuesta.setBounds(463, 218, 123, 195);
		add(panelDePropuesta);
		
		panelDePropuesta.setViewportView(listMateProp);
		
		btnEnviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] filasSelec = new String[listMateDisp.getSelectedValuesList().size()];
				filasSelec = (listMateDisp.getSelectedValuesList()).toArray(filasSelec); 
			    for(String s : filasSelec) {
			    	matPropDAO.asociarMatProp(p.getTitulo(), s);
			    }
				JFrame marco = (JFrame) SwingUtilities.getWindowAncestor((Component) e.getSource());
				marco.setContentPane(new PropAceptar(p));
				marco.validate();
			}
		});
		
		btnDesasociar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] filasSelec = new String[listMateProp.getSelectedValuesList().size()];
				filasSelec = (listMateProp.getSelectedValuesList()).toArray(filasSelec); 
			    for(String s : filasSelec) {
			    	matPropDAO.desasociarMatProp(p.getTitulo(), s);
			    }
				JFrame marco = (JFrame) SwingUtilities.getWindowAncestor((Component) e.getSource());
				marco.setContentPane(new PropAceptar(p));
				marco.validate();
			}
		});
		
		JButton btnAtras = new JButton("Atr\u00E1s");
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame marco = (JFrame) SwingUtilities.getWindowAncestor((Component) e.getSource());
				marco.setContentPane(new PropVerDetalles(p));
				marco.validate();
			}
		});
		btnAtras.setBounds(358, 484, 89, 23);
		add(btnAtras);
		
		}

	}
