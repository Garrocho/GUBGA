package com.gubga.gui;

import java.awt.Component;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

public abstract class Janela extends JFrame {

	private static final long serialVersionUID = 1L;

	public Janela() {
		super();
	}
	
	protected abstract void criarElementos();
	
	protected abstract void adicionarElementos();
	
	protected abstract void customizarElementos();
	
	protected abstract void configurarEventos();

	public void definirPropriedades(Component janelaPai, String titulo) {
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent arg0) {
				System.exit(0);
			}
		});
		setTitle(titulo);
		pack();
		//setIconImage(Toolkit.getDefaultToolkit().getImage("Recursos//Icones//Imagens//logo.png"));
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
	}
}