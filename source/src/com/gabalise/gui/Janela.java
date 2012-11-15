package com.gabalise.gui;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

public abstract class Janela extends JFrame {

	private static final long serialVersionUID = 1L;

	public Janela(String titulo) {
		super(titulo);
	}
	
	public abstract void criarElementos();
	
	public abstract void adicionarElementos();
	
	public abstract void configurarEventos();

	public void definirPropriedades() {
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent arg0) {
				System.exit(0);
			}
		});
		pack();
		//setIconImage(Toolkit.getDefaultToolkit().getImage("Recursos//Icones//Imagens//logo.png"));
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
	}
}