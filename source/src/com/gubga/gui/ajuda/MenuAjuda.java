package com.gubga.gui.ajuda;

import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JMenuItem;

import com.gubga.gui.Menu;
import com.gubga.gui.principal.JanelaPrincipal;

/**
 * Esta classe extende um <code>JMenu</code> e cria o menu ajuda.
 * 
 * @author Charles Garrocho
 */
public class MenuAjuda extends Menu {

	private static final long serialVersionUID = 1L;
	private JMenuItem menuItemSobre;
	
	/**
	 * Este e o construtor. Ele constroi o menu "Ajuda" e o adiciona na barra de menus na <code>JanelaPrincipal</code>.
	 * 
	 * @param janelaPrincipal <code>JanelaPrincipal</code> Janela principal da aplicacao, ela e utilizada para adicionar o menu Ajuda.
	 */
	public MenuAjuda(JanelaPrincipal janelaPrincipal) {
		super("Ajuda", janelaPrincipal);
		criarElementos();
		customizarElementos();
		configurarEventos();
		adicionarElementos();
	}
	
	@Override
	protected void adicionarElementos() {
		add(menuItemSobre);
		getJanelaPrincipal().getBarraMenu().add(this);
	}

	@Override
	protected void configurarEventos() {
		menuItemSobre.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				new DialogoSobre(getJanelaPrincipal());
			}
		});
	}

	@Override
	protected void criarElementos() {
		menuItemSobre = new JMenuItem("Sobre");
	}

	@Override
	protected void customizarElementos() {
		setFont(new Font("Arial", Font.BOLD, 12));
		setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		setIcon(new ImageIcon(getClass().getResource("/icones/AJUDA.png")));
		setMnemonic(KeyEvent.VK_A);
		
		menuItemSobre.setFont(new Font("Arial", Font.BOLD, 12));
		menuItemSobre.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		menuItemSobre.setMnemonic(KeyEvent.VK_S);
	}
}
