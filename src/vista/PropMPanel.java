package vista;


import javax.swing.JScrollPane;

import dao.CategoriaDAO;
import dao.PropuestaDAO;
import modelo.Propuesta;

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



public class PropMPanel extends PropPaneles {
	private JTextField txtTitulo = new JTextField();;
	private JTextField txtAutor;
	private JTextField txtDesc;
	private JTextField txtMotiv;

	/**
	 * Create the panel.
	 */

	public PropMPanel(Propuesta pSelec) {
		setLayout(null);
		
		PropuestaDAO pDAO = new PropuestaDAO();
		JScrollPane panelTabla = new JScrollPane();
		panelTabla.setBounds(8, 10, 201, 442);
		add(panelTabla);
		
		JCheckBox chckbxNueva = new JCheckBox("Es nueva");
		chckbxNueva.setBounds(483, 67, 89, 21);
		chckbxNueva.setEnabled(false);
		
		if(pSelec == null) {
			chckbxNueva.setSelected(true);
			pSelec = new Propuesta();
		}
		
		JLabel lblTitulo = new JLabel("T\u00EDtulo (obligatorio):");
		lblTitulo.setBounds(263, 70, 150, 14);
		add(lblTitulo);
		
		JLabel lblAutor = new JLabel("Autor:");
		lblAutor.setBounds(263, 303, 46, 14);
		add(lblAutor);
		
		txtAutor = new JTextField();
		txtAutor.setColumns(10);
		txtAutor.setBounds(266, 338, 306, 20);
		txtAutor.setText(pSelec.getAutor());
		add(txtAutor);
		
		JComboBox<String> combOrigen = new JComboBox<String>();
		combOrigen.setModel(conseguirOrigenes());
		combOrigen.setBounds(263, 271, 127, 22);
		if(pSelec.getOrigen() != null) {
			combOrigen.setSelectedItem(pSelec.getOrigen());			
		}
		add(combOrigen);
		
		JLabel lblOrigen = new JLabel("Origen:");
		lblOrigen.setBounds(263, 238, 46, 14);
		add(lblOrigen);
		
		JLabel lblCategora = new JLabel("Categor\u00EDa:");
		lblCategora.setBounds(445, 238, 66, 14);
		add(lblCategora);
		
		JComboBox<String> combCategoria = new JComboBox<String>();
		combCategoria.setModel(conseguirCategorias());
		combCategoria.setBounds(445, 271, 127, 22);
		if(pSelec.getCategoria() != null) {
			combCategoria.setSelectedItem(pSelec.getCategoria());
		}
		add(combCategoria);
		
		JLabel lblFecha = new JLabel("Fecha:");
		lblFecha.setBounds(263, 385, 33, 14);
		add(lblFecha);
		
		JLabel lblBreveDescripcin = new JLabel("Breve descripci\u00F3n:");
		lblBreveDescripcin.setBounds(263, 126, 127, 14);
		add(lblBreveDescripcin);
		
		txtDesc = new JTextField();
		txtDesc.setColumns(10);
		txtDesc.setBounds(263, 151, 309, 20);
		txtDesc.setText(pSelec.getDescripcion());
		add(txtDesc);
		
		txtMotiv = new JTextField();
		txtMotiv.setColumns(10);
		txtMotiv.setBounds(263, 207, 309, 20);
		txtMotiv.setText(pSelec.getMotivacion());
		add(txtMotiv);
		
		JLabel lblMotivacin = new JLabel("Motivaci\u00F3n:");
		lblMotivacin.setBounds(263, 182, 127, 14);
		add(lblMotivacin);
		
		LocalDate fechaLocal = pSelec.getFecha().toLocalDate();
		
		JComboBox<String> combMes = new JComboBox<String>();
		for(int i = 1; i <= 12; i++) {
			combMes.addItem(String.format("%02d", i));
		}
		combMes.setBounds(368, 382, 45, 21);
		combMes.setSelectedItem(String.format("%02d", fechaLocal.getMonthValue()));
		add(combMes);
		
		JLabel lblFormatoFecha = new JLabel("(dd/mm/aaaa)");
		lblFormatoFecha.setBounds(506, 386, 94, 13);
		add(lblFormatoFecha);
		
		JComboBox<String> combDia = new JComboBox<String>();
		for(int i = 1; i <= 31; i++) {
			combDia.addItem(String.format("%02d", i));
		}
		combDia.setBounds(304, 382, 45, 21);
		combDia.setSelectedItem(String.format("%02d", fechaLocal.getDayOfMonth()));
		add(combDia);
		
		JComboBox<String> combAnio = new JComboBox<String>();
		for(int i = 1990; i <= 2022; i++) {
			combAnio.addItem(String.format("%d", i));
		}
		combAnio.setSelectedItem(String.format("%d", fechaLocal.getYear()));
		combAnio.setBounds(424, 382, 61, 21);
		add(combAnio);
		
		JButton btnNvProp = new JButton("+ Nueva propuesta...");
		btnNvProp.setEnabled(false);
		btnNvProp.setBounds(263, 426, 309, 21);
		
		
		chckbxNueva.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				btnNvProp.setEnabled(!chckbxNueva.isSelected());
			}

		});
		add(chckbxNueva);
		
		JList<String> listaProp = new JList<String>();
		listaProp.setModel(refrescarLista(pDAO));
		listaProp.addListSelectionListener(new ListSelectionListener() {
			@Override
		    public void valueChanged(ListSelectionEvent arg0) {
		        if (!arg0.getValueIsAdjusting() && !listaProp.isSelectionEmpty()) {
		        	// Consigue todos los valores de la propuesta y los muestra en pantalla
		        	chckbxNueva.setSelected(false);
		        	txtTitulo.setEnabled(false);
		        	String s = listaProp.getSelectedValue();
		        	Propuesta p = pDAO.pedirUnaPropuesta(s);
					LocalDate lD = p.getFecha().toLocalDate();
		        	txtTitulo.setText(p.getTitulo());
		        	txtDesc.setText(p.getDescripcion());
		        	txtMotiv.setText(p.getMotivacion());
		        	txtAutor.setText(p.getAutor());
		        	combOrigen.setSelectedItem(p.getOrigen());
		        	combCategoria.setSelectedItem(p.getCategoria());
		        	combDia.setSelectedItem(String.format("%02d", lD.getDayOfMonth()));
		        	combMes.setSelectedItem(String.format("%02d", lD.getMonthValue()));
		        	combAnio.setSelectedItem(String.format("%d", lD.getYear()));
		        }
		    }
		});
		listaProp.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		panelTabla.setViewportView(listaProp);
		
		btnNvProp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listaProp.clearSelection();
				chckbxNueva.setSelected(true);
				txtTitulo.setEnabled(true);
				txtTitulo.setText("");
				txtDesc.setText("");
				txtMotiv.setText("");
				txtAutor.setText("");
				combOrigen.setSelectedIndex(0);
				combCategoria.setSelectedIndex(0);
				combDia.setSelectedIndex(0);
				combMes.setSelectedIndex(0);
				combAnio.setSelectedIndex(32);
			}
		});
		add(btnNvProp);

		txtTitulo = new JTextField();
		txtTitulo.setBounds(263, 95, 309, 20);
		txtTitulo.setColumns(10);
		txtTitulo.setText(pSelec.getTitulo());
		
		JButton btnEnviar = new JButton("Env\u00EDar");
		btnEnviar.setEnabled(!txtTitulo.getText().isBlank());
		btnEnviar.setBounds(263, 457, 127, 23);
		btnEnviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String tituloActual = txtTitulo.getText().trim(); 
				String descActual = txtDesc.getText().trim();
				String motivActual = txtMotiv.getText().trim();
				String autorActual = txtAutor.getText().trim();
				String origenActual = (String) combOrigen.getSelectedItem();
				String catActual = (String) combCategoria.getSelectedItem();
				Date fechaActual = stringsAFecha((String) combDia.getSelectedItem(),(String) combMes.getSelectedItem(),(String) combAnio.getSelectedItem());
				DefaultListModel<String> ls = (DefaultListModel<String>) listaProp.getModel();
				Propuesta p = new Propuesta(origenActual, catActual, autorActual, fechaActual, tituloActual, descActual, motivActual,"");
				if(chckbxNueva.isSelected() == false) {
					if (JOptionPane.showConfirmDialog(null, "¿Está seguro de que quiere modificar \""+ p.getTitulo() +"\"?") == 0) {
						Propuesta pSelec = pDAO.pedirUnaPropuesta(listaProp.getSelectedValue());
						p.setEstado(pSelec.getEstado());
						p.setMotivoRechazo(pSelec.getMotivoRechazo());
						pDAO.modificarPropuesta(p);
					}
				} else {
					if (JOptionPane.showConfirmDialog(null, "¿Está seguro de que quiere a\u00F1adir \""+ p.getTitulo() +"\"?") == 0) {
						if(!ls.contains(txtTitulo.getText())) {
				
						pDAO.guardarPropuesta(p);
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
		btnAtras.setBounds(483, 457, 89, 23);
		add(btnAtras);
	}
	
	private DefaultComboBoxModel<String> conseguirCategorias(){
		CategoriaDAO cDAO = new CategoriaDAO();
		List<String> ls = cDAO.pedirCategorias();
		return new DefaultComboBoxModel<String>(ls.toArray(new String[0]));
	}
	
	private DefaultComboBoxModel<String> conseguirOrigenes() {
		List<String> ls = new ArrayList<>(); 
		ls.add("Docente");
		ls.add("Estudiante");
		return new DefaultComboBoxModel<String>(ls.toArray(new String[0]));
	}
	
	private Date stringsAFecha(String dd, String mm, String aaaa) {
		String fechaString = aaaa + "-" + mm + "-" + dd;
		return Date.valueOf(fechaString);
	}
}
