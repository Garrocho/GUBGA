package com.gubga.gui;

import java.awt.Component;

import javax.swing.JDialog;
import javax.swing.WindowConstants;

public abstract class Dialogo extends JDialog {

	private static final long serialVersionUID = 1L;

	public Dialogo() {
		super();
	}
	
	protected abstract void criarElementos();
	
	protected abstract void adicionarElementos();
	
	protected abstract void customizarElementos();
	
	protected abstract void configurarEventos();

	protected void definirPropriedades(Component janelaPai, String titulo) {
		pack();
		setTitle(titulo);
		//setIconImage(Toolkit.getDefaultToolkit().getImage("Recursos//Icones//Imagens//logo.png"));
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(janelaPai);
		setResizable(false);
		setVisible(true);
	}
}