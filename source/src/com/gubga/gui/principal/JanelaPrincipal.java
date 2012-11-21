package com.gubga.gui.principal;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.ParseException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

import com.gubga.classes.TamanhoMaximo;
import com.gubga.gui.Janela;
import com.gubga.gui.ajuda.MenuAjuda;
import com.gubga.gui.banlist.MenuBanList;
import com.gubga.gui.principal.eventos.TratadorEventosJanelaPrincipal;

import de.javasoft.plaf.synthetica.SyntheticaBlackEyeLookAndFeel;


/**
 * Esta e a classe que contem a janela principal do programa. Ela e constituida de um conjunto de menus que sao os modulos do
 * sistema.
 * 
 * @author Charles Garrocho
 */
public class JanelaPrincipal extends Janela {

	private static final long serialVersionUID = 1L;
	private String usuario;
	private JLabel rotuloGubga;
	private JMenuBar barraMenu;
	private JMenuItem menuSair, menuAlternarConta;
	private JTable tabelaUsuarios;
	private JTextField campoPesquisa;
	private JPanel painelNorte, painelCentro, painelSul;
	private JButton botaoConfiguracao;
	private JPopupMenu popMenu;
	private long timeLastShown = 100;
	private TratadorEventosJanelaPrincipal tratadorEventos = new TratadorEventosJanelaPrincipal(this);
	
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
		definirPropriedades(janelaPai, "GUBGA - Gerenciador de Usuários Banidos do Garena", new Dimension(550, 500));
	}

	@Override
	protected void adicionarElementos() {
		
		new MenuBanList(this);
		new MenuAjuda(this);
		
		popMenu.add(menuAlternarConta);  
		popMenu.add(menuSair);
		
		barraMenu.add(menuSair);
		setJMenuBar(barraMenu);
		
		painelNorte.add(rotuloGubga);
		painelNorte.add(campoPesquisa);
		painelNorte.add(botaoConfiguracao);
		
		add(painelNorte, BorderLayout.NORTH);
	}

	@Override
	protected void configurarEventos() {
		
		botaoConfiguracao.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if ((System.currentTimeMillis() - timeLastShown) > 300) {
					Component c = (Component) e.getSource();
					popMenu.show(c, 25, c.getHeight());
				}
			}
		});
		menuAlternarConta.addActionListener(tratadorEventos);
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
		painelNorte = new JPanel();
		painelCentro = new JPanel();
		painelSul = new JPanel();
		
		popMenu = new JPopupMenu();
		
		barraMenu = new JMenuBar();
		menuSair = new JMenuItem("Sair");
		menuAlternarConta = new JMenuItem("Alternar Conta");
		
		botaoConfiguracao = new JButton(new ImageIcon(getResource("configuracao.png")));
		
		rotuloGubga = new JLabel(new ImageIcon(getResource("logo100.png")));
		campoPesquisa = new JTextField(35);
	}

	@Override
	protected void customizarElementos() {
		menuSair.setMnemonic(KeyEvent.VK_F4);
		menuSair.setIcon(new ImageIcon(Janela.getResource("sair.png")));
		menuSair.setFont(new Font("Arial", Font.BOLD, 12));
		menuSair.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		menuAlternarConta.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		campoPesquisa.setToolTipText("Descreva o uid do usuário.");
		campoPesquisa.setPreferredSize(new Dimension(500, 29));
		campoPesquisa.setDocument(new TamanhoMaximo(25));
		
		botaoConfiguracao.setBorder(null);
		botaoConfiguracao.setText("OPCOES                ");
		botaoConfiguracao.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
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

	public JMenuItem getMenuSair() {
		return menuSair;
	}

	public JMenuItem getMenuAlternarConta() {
		return menuAlternarConta;
	}

	public JTable getTabelaUsuarios() {
		return tabelaUsuarios;
	}

	public JTextField getCampoPesquisa() {
		return campoPesquisa;
	}

	public JButton getBotaoConfiguracao() {
		return botaoConfiguracao;
	}

	public JPopupMenu getPopMenu() {
		return popMenu;
	}
}