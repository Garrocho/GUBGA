package com.gubga.gui.principal;

import static com.gubga.classes.Recursos.customizarBotao;

import java.awt.BorderLayout;
import java.awt.Color;
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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;

import com.gubga.classes.TamanhoMaximo;
import com.gubga.gui.Janela;
import com.gubga.gui.ajuda.MenuAjuda;
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
	private JPopupMenu popMenu;
	private JTable tabelaUsuarios;
	private JScrollPane scrollPane;
	private JTextField campoPesquisa;
	private long timeLastShown = 100;
	private JButton botaoConfiguracao, botaoCarregar, botaoLimpar, botaoSair;;
	private JMenuItem menuAlternarConta;
	private JPanel painelNorte, painelCentro, painelSul;
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
		definirPropriedades(janelaPai, "GUBGA - Gerenciador de Usuários Banidos do Garena", new Dimension(470, 500));
	}

	@Override
	protected void adicionarElementos() {

		new MenuAjuda(this);

		popMenu.add(menuAlternarConta);  

		setJMenuBar(barraMenu);

		painelNorte.add(rotuloGubga);
		painelNorte.add(campoPesquisa);
		painelNorte.add(botaoConfiguracao);

		painelCentro.add(scrollPane);
		
		painelSul.add(botaoCarregar);
		painelSul.add(botaoLimpar);
		painelSul.add(botaoSair);

		add(painelNorte, BorderLayout.NORTH);
		add(painelCentro, BorderLayout.CENTER);
		add(painelSul, BorderLayout.SOUTH);
	}

	@Override
	protected void configurarEventos() {

		botaoSair.addActionListener(tratadorEventos);
		botaoCarregar.addActionListener(tratadorEventos);
		botaoLimpar.addActionListener(tratadorEventos);
		tabelaUsuarios.addMouseListener(tratadorEventos);
		menuAlternarConta.addActionListener(tratadorEventos);
		botaoConfiguracao.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if ((System.currentTimeMillis() - timeLastShown) > 300) {
					Component c = (Component) e.getSource();
					popMenu.show(c, 25, c.getHeight());
				}
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
		menuAlternarConta = new JMenuItem("Alternar Conta");

		botaoConfiguracao = new JButton(new ImageIcon(getResource("configuracao.png")));
		botaoCarregar = new JButton(new ImageIcon(getResource("popular.png")));
		botaoSair = new JButton(new ImageIcon(getResource("sair2.png")));
		botaoLimpar = new JButton(new ImageIcon(getResource("limpar.png")));

		rotuloGubga = new JLabel(new ImageIcon(getResource("logo100.png")));
		campoPesquisa = new JTextField(25);

		String colunasTabela[] = {"UserId", "UserName", "Reason"},
		dadosTabela[][] = new String[0][0];

		tabelaUsuarios = new JTable(new DefaultTableModel(dadosTabela, colunasTabela)){
			private static final long serialVersionUID = 5727320816550514929L;
			public boolean isCellEditable(int rowIndex, int colIndex) {
				return false;
			};
		};

		scrollPane = new JScrollPane(tabelaUsuarios);
		Object[] linha = new Object[3];
		linha[0] = "10661343";
		linha[1] = "lGARROCHOl";
		linha[2] = "Hacker";
		DefaultTableModel modeloTabela = ((DefaultTableModel)(tabelaUsuarios.getModel()));
		modeloTabela.addRow(linha);
	}

	@Override
	protected void customizarElementos() {
		menuAlternarConta.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		campoPesquisa.setToolTipText("Digite Aqui o Nick do Usuário.");
		campoPesquisa.setPreferredSize(new Dimension(500, 29));
		campoPesquisa.setDocument(new TamanhoMaximo(25));

		botaoConfiguracao.setBorder(null);
		botaoConfiguracao.setText("OPCOES                ");
		botaoConfiguracao.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		customizarBotao(botaoCarregar);
		customizarBotao(botaoSair);
		customizarBotao(botaoLimpar);
		
		botaoCarregar.setText("Carregar");
		botaoSair.setText("Sair    ");
		botaoLimpar.setText("Limpar  ");
		
		botaoConfiguracao.setToolTipText("Alternar de Conta.");
		botaoCarregar.setToolTipText("Carregar Contas Existentes da Pasta do Garena Plus.");
		botaoSair.setToolTipText("Sair do GUBGA.");
		botaoLimpar.setToolTipText("Limpar Tabela de Usuários do Garena Plus.");
		tabelaUsuarios.setToolTipText("Dê um Duplo Clique na Conta a Ser Utilizada.");
		
		tabelaUsuarios.setForeground(new Color(0,185,242));
		scrollPane.setPreferredSize(new Dimension(450, 330));
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