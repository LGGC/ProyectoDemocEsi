package vista;


import javax.swing.JScrollPane;

import dao.CategoriaDAO;
import dao.MaterialDAO;
import dao.MtInstitucionalDAO;
import dao.MtOrigenPorPropuestaDAO;
import dao.PropuestaDAO;
import modelo.Material;
import modelo.MtInstitucional;
import modelo.MtOrigenPorPropuesta;

import java.util.ArrayList;
import java.util.List;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.Position;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.time.LocalDate;
import java.awt.event.ActionEvent;
import java.awt.Component;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JCheckBox;



public class MateMPanel extends MatePaneles {
	private JTextField txtTitulo = new JTextField();
	private JTextField txtDesc;
	private JTextField txtFuente;
	private JTextField txtEnlaceDoc;
	private JTextField txtProcedencia;

	/**
	 * Create the panel.
	 */

	public MateMPanel(Material mSelec) {
		setLayout(null);
		
		PropuestaDAO pDAO = new PropuestaDAO();
		MaterialDAO mDAO = new MaterialDAO();
		MtInstitucionalDAO mIDAO = new MtInstitucionalDAO();
		MtOrigenPorPropuestaDAO mPDAO = new MtOrigenPorPropuestaDAO();
		JScrollPane panelTabla = new JScrollPane();
		panelTabla.setBounds(8, 10, 201, 442);
		add(panelTabla);
		
		JCheckBox chckbxNueva = new JCheckBox("Es nuevo");
		chckbxNueva.setBounds(483, 67, 89, 21);
		chckbxNueva.setEnabled(false);
		
		if(mSelec == null) {
			chckbxNueva.setSelected(true);
			mSelec = new MtInstitucional();
		}
		
		JLabel lblTitulo = new JLabel("T\u00EDtulo (obligatorio):");
		lblTitulo.setBounds(263, 70, 150, 14);
		add(lblTitulo);
		
		JLabel lblCategoria = new JLabel("Categor\u00EDa:");
		lblCategoria.setBounds(263, 303, 94, 14);
		add(lblCategoria);
		
		JComboBox<String> combTipo = new JComboBox<String>();
		combTipo.setModel(conseguirTipos());
		combTipo.setBounds(263, 271, 127, 22);
		if(mDAO.pedirTipo(mSelec.getTitulo()) != null) {
			combTipo.setSelectedItem(mDAO.pedirTipo(mSelec.getTitulo()));			
		}
		add(combTipo);
		
		JLabel lblOrigen = new JLabel("Tipo:");
		lblOrigen.setBounds(263, 238, 46, 14);
		add(lblOrigen);
		
		JLabel lblJornada = new JLabel("Jornada:");
		lblJornada.setBounds(445, 238, 66, 14);
		add(lblJornada);
		
		JComboBox<String> combCategoria = new JComboBox<String>();
		combCategoria.setModel(conseguirCategorias());
		combCategoria.setBounds(263, 327, 127, 22);
		if(mSelec.getCategoria() != null) {
			combCategoria.setSelectedItem(mSelec.getCategoria());
		}
		add(combCategoria);
		
		JLabel lblEnlaceDoc = new JLabel("Enlace a documento:");
		lblEnlaceDoc.setBounds(263, 360, 189, 14);
		add(lblEnlaceDoc);
		
		JLabel lblBreveDescripcin = new JLabel("Breve descripci\u00F3n:");
		lblBreveDescripcin.setBounds(263, 126, 127, 14);
		add(lblBreveDescripcin);
		
		txtDesc = new JTextField();
		txtDesc.setColumns(10);
		txtDesc.setBounds(263, 151, 309, 20);
		txtDesc.setText(mSelec.getDescripcion());
		add(txtDesc);
		
		txtFuente = new JTextField();
		txtFuente.setColumns(10);
		txtFuente.setBounds(263, 207, 309, 20);
		txtFuente.setText(mSelec.getFuente());
		add(txtFuente);
		
		JLabel lblFuente = new JLabel("Fuente:");
		lblFuente.setBounds(263, 182, 127, 14);
		add(lblFuente);
		
		JComboBox<String> combJornada = new JComboBox<String>();
		combJornada.setModel(conseguirJornadas());
		combJornada.setBounds(445, 272, 127, 21);
		combJornada.setSelectedItem(mSelec.getJornada());
		add(combJornada);
		
		JButton btnNvProp = new JButton("+ Nuevo material...");
		btnNvProp.setEnabled(false);
		btnNvProp.setBounds(263, 507, 309, 21);
		
		
		chckbxNueva.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				btnNvProp.setEnabled(!chckbxNueva.isSelected());
			}

		});
		add(chckbxNueva);
		
		JList<String> listaMate = new JList<String>();
		listaMate.setModel(refrescarLista(mDAO));
		listaMate.addListSelectionListener(new ListSelectionListener() {
			@Override
		    public void valueChanged(ListSelectionEvent arg0) {
		        if (!arg0.getValueIsAdjusting() && !listaMate.isSelectionEmpty()) {
		        	// Consigue todos los valores de la propuesta y los muestra en pantalla
		        	chckbxNueva.setSelected(false);
		        	txtTitulo.setEnabled(false);
		        	String s = listaMate.getSelectedValue();
		        	Material m = mDAO.pedirUnMaterial(s);
		        	String tipo = mDAO.pedirTipo(m.getTitulo());
		        	if (tipo.equalsIgnoreCase("institucional")){
		        		m = mIDAO.pedirUnMaterial(s);
		        	} else {
		        		m = mPDAO.pedirUnMaterial(s);
		        	}
		        	txtTitulo.setText(m.getTitulo());
		        	txtDesc.setText(m.getDescripcion());
		        	txtFuente.setText(m.getFuente());
		        	combTipo.setSelectedItem(tipo);
		        	combCategoria.setSelectedItem(m.getCategoria());
		        	combJornada.setSelectedItem(m.getJornada());
		        }
		    }
		});
		listaMate.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		panelTabla.setViewportView(listaMate);
		
		btnNvProp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listaMate.clearSelection();
				chckbxNueva.setSelected(true);
				txtTitulo.setEnabled(true);
				txtTitulo.setText("");
				txtDesc.setText("");
				txtFuente.setText("");
				combTipo.setSelectedIndex(0);
				combCategoria.setSelectedIndex(0);
				combJornada.setSelectedIndex(0);
			}
		});
		add(btnNvProp);

		txtTitulo = new JTextField();
		txtTitulo.setBounds(263, 95, 309, 20);
		txtTitulo.setColumns(10);
		txtTitulo.setText(mSelec.getTitulo());
		
		txtEnlaceDoc = new JTextField();
		txtEnlaceDoc.setText((String) null);
		txtEnlaceDoc.setColumns(10);
		txtEnlaceDoc.setBounds(263, 385, 309, 20);
		add(txtEnlaceDoc);
		
		JLabel lblProcedencia = new JLabel("Procedencia:");
		lblProcedencia.setBounds(263, 428, 189, 14);
		add(lblProcedencia);
		
		txtProcedencia = new JTextField();
		txtProcedencia.setBounds(263, 454, 309, 20);
		add(txtProcedencia);
		txtProcedencia.setColumns(10);
		
		JCheckBox chckbxPrioritario = new JCheckBox("Es prioritario");
		chckbxPrioritario.setBounds(371, 66, 97, 23);
		add(chckbxPrioritario);
		
		JButton btnEnviar = new JButton("Env\u00EDar");
		btnEnviar.setEnabled(!txtTitulo.getText().isBlank());
		btnEnviar.setBounds(263, 538, 127, 23);
		btnEnviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String tituloActual = txtTitulo.getText().trim(); 
				String descActual = txtDesc.getText().trim();
				String fuenteActual = txtFuente.getText().trim();
				String enlaceActual = txtEnlaceDoc.getText().trim();
				String procActual = txtProcedencia.getText().trim();
				boolean priorActual = chckbxPrioritario.isSelected();
				String tipoActual = (String) combTipo.getSelectedItem();
				String catActual = (String) combCategoria.getSelectedItem();
				String jornActual = (String) combJornada.getSelectedItem();
				DefaultListModel<String> ls = (DefaultListModel<String>) listaMate.getModel();
				Material m = null;
				if (tipoActual.equalsIgnoreCase("institucional")){
					m = new MtInstitucional(jornActual, tituloActual, catActual, descActual, fuenteActual, enlaceActual, procActual, priorActual);
	        		mIDAO.guardarMaterial((MtInstitucional) m);
	        	} else {
	        		m = new MtOrigenPorPropuesta(jornActual, tituloActual, catActual, descActual, fuenteActual, enlaceActual, pDAO.pedirPropuestasDeMaterial(tituloActual));
	        		mPDAO.guardarMaterial((MtOrigenPorPropuesta) m);
	        	}
				if(chckbxNueva.isSelected() == false) {
					if (JOptionPane.showConfirmDialog(null, "¿Está seguro de que quiere modificar \""+ m.getTitulo() +"\"?") == 0) {
						Material mSelec = m;
						if(tipoActual.equalsIgnoreCase("origen por propuesta")) {
							((MtOrigenPorPropuesta) m).setPropuestas(pDAO.pedirPropuestasDeMaterial(tituloActual));
						}
						if (tipoActual.equalsIgnoreCase("institucional")){
			        		mIDAO.modificarMaterial((MtInstitucional) m);
			        	} else {
			        		mPDAO.modificarMaterial((MtOrigenPorPropuesta) m);
			        	}
					}
				} else {
					if (JOptionPane.showConfirmDialog(null, "¿Está seguro de que quiere a\u00F1adir \""+ m.getTitulo() +"\"?") == 0) {
						if(!ls.contains(txtTitulo.getText())) {
							if (tipoActual.equalsIgnoreCase("institucional")){
				        		mIDAO.guardarMaterial((MtInstitucional) m);
				        	} else {
				        		mPDAO.guardarMaterial((MtOrigenPorPropuesta) m);
				        	}
						}
					}
				}
				JFrame marco = (JFrame) SwingUtilities.getWindowAncestor((Component) e.getSource());
				marco.setContentPane(new PropMPanel(null));
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
				marco.setContentPane(new PropCPanel());
				marco.validate();
			}
		});
		btnAtras.setBounds(483, 538, 89, 23);
		add(btnAtras);
		
	}
	
}
