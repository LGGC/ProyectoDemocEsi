package vista;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import dao.JornadaDAO;
import dao.MaterialDAO;
import modelo.Jornada;

public class JornMPanel extends JornPaneles {
	private JTextField txtTitulo = new JTextField();
	private JTextField txtObj;
	private JTextField txtRef;

	public JornMPanel(Jornada jSelec) {
		setLayout(null);
		
		JornadaDAO jDAO = new JornadaDAO();
		JScrollPane panelTabla = new JScrollPane();
		panelTabla.setBounds(8, 10, 201, 359);
		add(panelTabla);
		
		JCheckBox chckbxNueva = new JCheckBox("Es nueva");
		JButton btnNvJorn = new JButton("+ Nueva jornada...");
		chckbxNueva.setBounds(483, 67, 89, 21);
		chckbxNueva.setEnabled(false);
		chckbxNueva.setSelected(jSelec == null);
		JButton btnAgregarMaterial = new JButton("+ Agregar materiales...");
		btnAgregarMaterial.setEnabled(jSelec != null);
		btnNvJorn.setEnabled(jSelec != null);
		if(jSelec == null) {
			jSelec = new Jornada();
		}
		
		JLabel lblTitulo = new JLabel("T\u00EDtulo (obligatorio):");
		lblTitulo.setBounds(263, 70, 150, 14);
		add(lblTitulo);
		
		JLabel lblObjetivo = new JLabel("Objetivo:");
		lblObjetivo.setBounds(263, 126, 127, 14);
		add(lblObjetivo);
		
		txtObj = new JTextField();
		txtObj.setColumns(10);
		txtObj.setBounds(263, 151, 309, 20);
		txtObj.setText(jSelec.getObjetivo());
		add(txtObj);
		
		txtRef = new JTextField();
		txtRef.setColumns(10);
		txtRef.setBounds(263, 207, 309, 20);
		txtRef.setText(jSelec.getReferenteInstitucional());
		add(txtRef);
		
		JLabel lblReferente = new JLabel("Referente institucional:");
		lblReferente.setBounds(263, 182, 150, 14);
		add(lblReferente);
		

		btnNvJorn.setBounds(263, 315, 309, 21);
		
		JList<String> listaJorn = new JList<String>();
		listaJorn.setModel(refrescarLista(jDAO));
		listaJorn.addListSelectionListener(new ListSelectionListener() {
			@Override
		    public void valueChanged(ListSelectionEvent arg0) {
		        if (!arg0.getValueIsAdjusting() && !listaJorn.isSelectionEmpty()) {
		        	 // Consigue todos los valores de la jornada y los muestra en pantalla
		        	chckbxNueva.setSelected(false);
		        	txtTitulo.setEnabled(false);
		        	String s = listaJorn.getSelectedValue();
		        	Jornada j = jDAO.pedirUnaJornada(s);
		        	txtTitulo.setText(j.getTitulo());
		        	txtObj.setText(j.getObjetivo());
		        	txtRef.setText(j.getReferenteInstitucional());
		        	btnAgregarMaterial.setEnabled(true);
		        	btnNvJorn.setEnabled(true);
		        }
		    }
		});
		listaJorn.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		panelTabla.setViewportView(listaJorn);
		
		btnAgregarMaterial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String s = txtTitulo.getText();
				Jornada j = jDAO.pedirUnaJornada(s);
				JFrame marco = (JFrame) SwingUtilities.getWindowAncestor((Component) e.getSource());
				marco.setContentPane(new AñadirMate(j));
				marco.validate();
			}
		});

		btnAgregarMaterial.setBounds(263, 281, 309, 23);
		add(btnAgregarMaterial);
		
		chckbxNueva.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				btnNvJorn.setEnabled(!chckbxNueva.isSelected());
				btnAgregarMaterial.setEnabled(!chckbxNueva.isSelected());
			}

		});
		add(chckbxNueva);
		
		btnNvJorn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listaJorn.clearSelection();
				chckbxNueva.setSelected(true);
				txtTitulo.setEnabled(true);
				txtTitulo.setText("");
				txtObj.setText("");
				txtRef.setText("");
			}
		});
		add(btnNvJorn);
		
		txtTitulo = new JTextField();
		txtTitulo.setBounds(263, 95, 309, 20);
		txtTitulo.setColumns(10);
		txtTitulo.setText(jSelec.getTitulo());

		JButton btnEnviar = new JButton("Env\u00EDar");
		btnEnviar.setEnabled(!txtTitulo.getText().isBlank());
		btnEnviar.setBounds(263, 346, 127, 23);
		btnEnviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String tituloActual = txtTitulo.getText().trim(); 
				String objActual = txtObj.getText().trim();
				String refActual = txtRef.getText().trim();
				DefaultListModel<String> ls = (DefaultListModel<String>) listaJorn.getModel();
				Jornada j = new Jornada(refActual, objActual, tituloActual);
				if(chckbxNueva.isSelected() == false) {
					if (JOptionPane.showConfirmDialog(null, "�Est� seguro de que quiere modificar \""+ j.getTitulo() +"\"?") == 0) {
						jDAO.modificarJornada(j);
					}
				} else {
					if (JOptionPane.showConfirmDialog(null, "�Est� seguro de que quiere a\u00F1adir \""+ j.getTitulo() +"\"?") == 0) {
						if(!ls.contains(txtTitulo.getText())) {
						jDAO.guardarJornada(j);
						}
					}
				}
				JFrame marco = (JFrame) SwingUtilities.getWindowAncestor((Component) e.getSource());
				marco.setContentPane(new JornMPanel(null));
				marco.validate();
			}
		});
		add(btnEnviar);
		
		txtTitulo.setEnabled(chckbxNueva.isSelected());
		txtTitulo.getDocument().addDocumentListener(new DocumentListener() {
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
				 String s = txtTitulo.getText();
			     if (!s.isBlank()){
			    	 btnEnviar.setEnabled(true);
			     } else {
			    	 btnEnviar.setEnabled(false);
			     }
			    }
			});
		add(txtTitulo);
		
		JButton btnAtras = new JButton("Atr\u00E1s");
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame marco = (JFrame) SwingUtilities.getWindowAncestor((Component) e.getSource());
				marco.setContentPane(new JornCPanel());
				marco.validate();
			}
		});
		btnAtras.setBounds(483, 346, 89, 23);
		add(btnAtras);
		
	}
}

