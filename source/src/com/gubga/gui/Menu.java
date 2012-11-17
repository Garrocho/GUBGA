package com.gubga.gui;

import javax.swing.JMenu;


public abstract class Menu extends JMenu {

	private static final long serialVersionUID = 1L;

	public Menu() {
		super();
	}
	
	protected abstract void criarElementos();
	
	protected abstract void adicionarElementos();
	
	protected abstract void customizarElementos();
	
	protected abstract void configurarEventos();
}