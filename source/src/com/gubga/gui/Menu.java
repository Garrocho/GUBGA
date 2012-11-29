package com.gubga.gui;

import javax.swing.JMenu;

import com.gubga.gui.banlist.JanelaBanList;

public abstract class Menu extends JMenu {

	private static final long serialVersionUID = 1L;
	private JanelaBanList janelaBanList;

	public Menu(String texto, JanelaBanList janelaBanList) {
		super(texto);
		this.janelaBanList = janelaBanList;
	}
	
	protected abstract void criarElementos();
	
	protected abstract void adicionarElementos();
	
	protected abstract void customizarElementos();
	
	protected abstract void configurarEventos();
	
	protected JanelaBanList getJanelaPrincipal() {
		return janelaBanList;
	}
}