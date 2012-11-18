package com.gubga.gui;

import javax.swing.JMenu;

import com.gubga.gui.principal.JanelaPrincipal;

public abstract class Menu extends JMenu {

	private static final long serialVersionUID = 1L;
	private JanelaPrincipal janelaPrincipal;

	public Menu(String texto, JanelaPrincipal janelaPrincipal) {
		super(texto);
		this.janelaPrincipal = janelaPrincipal;
	}
	
	protected abstract void criarElementos();
	
	protected abstract void adicionarElementos();
	
	protected abstract void customizarElementos();
	
	protected abstract void configurarEventos();
	
	protected JanelaPrincipal getJanelaPrincipal() {
		return janelaPrincipal;
	}
}