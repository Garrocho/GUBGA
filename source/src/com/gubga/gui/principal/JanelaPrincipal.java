package com.gubga.gui.principal;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.ParseException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.gubga.gui.Janela;
import com.gubga.gui.ajuda.MenuAjuda;
import com.gubga.gui.banlist.MenuBanList;

import de.javasoft.plaf.synthetica.SyntheticaBlackEyeLookAndFeel;


/**
 * Esta e a classe que contem a janela principal do programa. Ela e constituida de um conjunto de menus que sao os modulos do
 * sistema.
 * 
 * @author Charles Garrocho
 */
public class JanelaPrincipal extends Janela {

	private static final long serialVersionUID = 1L;
	private JMenuBar barraMenu;
	private JMenuItem menuSair;
	private JPanel painelLogo;
	private GridBagConstraints inserts;
	private JLabel rotuloLogo, rotuloHelp;
	private String usuario;
	
	public static void main(String args[]) throws UnsupportedLookAndFeelException, ParseException {
		UIManager.put("Synthetica.window.decoration", Boolean.FALSE);
		UIManager.setLookAndFeel(new SyntheticaBlackEyeLookAndFeel());
		new JanelaPrincipal(null, "10661343");
	}

	/**
	 * Este e o construtor. Ele cria os menus e adiciona a janela a barra de menus contendo os modulos do sistema.
	 */
	public JanelaPrincipal(JFrame janelaPai, String usuario) {
		super();
		this.usuario = usuario;
		criarElementos();
		customizarElementos();
		configurarEventos();
		adicionarElementos();
		definirPropriedades(janelaPai, "GUBGA - Gerenciador de Usuários Banidos do Garena", new Dimension(650, 600));
	}

	@Override
	protected void adicionarElementos() {
		
		new MenuBanList(this);
		new MenuAjuda(this);
		
		barraMenu.add(menuSair);
		setJMenuBar(barraMenu);
		inserts.gridx = 0;
		inserts.gridy = 0;
		//painelLogo.add(rotuloLogo, inserts);
		inserts.gridx = 0;
		inserts.gridy = 1;
		//painelLogo.add(rotuloHelp, inserts);
		add(painelLogo);
	}

	@Override
	protected void configurarEventos() {
		
		menuSair.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		
		addWindowListener( new WindowAdapter() {
			public void windowClosing(WindowEvent evento) {
				System.exit(0);
			}
		});
	}

	@Override
	protected void criarElementos() {
		painelLogo = new JPanel(new GridBagLayout());
		inserts = new GridBagConstraints();
		//rotuloLogo = new JLabel(new ImageIcon(Janela.getResource("logo.png")));
		//rotuloHelp = new JLabel(new ImageIcon(Janela.getResource("descricao.png")));
		barraMenu = new JMenuBar();
		menuSair  = new JMenuItem("Sair");
	}

	@Override
	protected void customizarElementos() {
		menuSair.setMnemonic(KeyEvent.VK_F4);
		menuSair.setIcon(new ImageIcon(Janela.getResource("sair.png")));
		menuSair.setFont(new Font("Arial", Font.BOLD, 12));
		menuSair.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	}
	
	/**
	 * Retorna uma referencia da Barra de Menu.
	 * 
	 * @return um <code>JMenuBar</code> com a Barra de Menu.
	 */
	public JMenuBar getBarraMenu() {
		return barraMenu;
	}
	
	public String getUsuario() {
		return usuario;
	}
}