package app;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import vista.JornCPanel;
import vista.JornMPanel;
import vista.MateCPanel;
import vista.MateMPanel;
import vista.PropCPanel;
import vista.PropMPanel;

public class App {
	public static void main(String[] args) {
		JFrame marco = new JFrame();
		marco.setBounds(0, 0, 871, 674);
		marco.setVisible(true);
		marco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 800, 35);
		marco.setJMenuBar(menuBar);
		
		JMenu mnPropuestas = new JMenu("Propuestas");
		mnPropuestas.setMnemonic(KeyEvent.VK_1);
		menuBar.add(mnPropuestas);
		JMenuItem mntmCProp = new JMenuItem("Ver propuestas...");
		mntmCProp.setAccelerator(KeyStroke.getKeyStroke(
				KeyEvent.VK_P, ActionEvent.CTRL_MASK));
		mntmCProp.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				marco.setContentPane(new PropCPanel());
				marco.validate();
				
			}
		});
		mnPropuestas.add(mntmCProp);
		
		JMenuItem mntmMProp = new JMenuItem("A\u00F1adir/modificar propuestas...");
		mntmMProp.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				marco.setContentPane(new PropMPanel(null));
				marco.validate();
			}
		});
		mnPropuestas.add(mntmMProp);
		
		JMenu mnMateriales = new JMenu("Materiales");
		
		JMenuItem mntmCMat = new JMenuItem("Ver materiales...");
		mntmCMat.setAccelerator(KeyStroke.getKeyStroke(
				KeyEvent.VK_M, ActionEvent.CTRL_MASK));
		mntmCMat.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				marco.setContentPane(new MateCPanel());
				marco.validate();	
			}
		});
		mnMateriales.add(mntmCMat);
		mnMateriales.setMnemonic(KeyEvent.VK_2);
		menuBar.add(mnMateriales);
		
		JMenuItem mntmMMat = new JMenuItem("A\u00F1adir/modificar materiales...");
		mntmMMat.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				marco.setContentPane(new MateMPanel(null));
				marco.validate();	
			}
		});
		mnMateriales.add(mntmMMat);
		
		
		JMenu mnJornadas = new JMenu("Jornadas");
		mnJornadas.setMnemonic(KeyEvent.VK_3);
		menuBar.add(mnJornadas);
		
		JMenuItem mntmCJor = new JMenuItem("Ver jornadas...");
		mntmCJor.setAccelerator(KeyStroke.getKeyStroke(
				KeyEvent.VK_J, ActionEvent.CTRL_MASK));
		mntmCJor.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				marco.setContentPane(new JornCPanel());
				marco.validate();
			}
		});
		mnJornadas.add(mntmCJor);
		marco.validate();
		
		JMenuItem mntmMJor = new JMenuItem("A\u00F1adir/modificar jornadas...");
		mntmMJor.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				marco.setContentPane(new JornMPanel(null));
				marco.validate();
			}
		});
		mnJornadas.add(mntmMJor);
		
		marco.validate();
	}
}