package com.gubga.gui.principal;

import static com.gubga.classes.Recursos.customizarBotao;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.RoundRectangle2D;
import java.text.ParseException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JRootPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import com.gubga.classes.TamanhoMaximo;
import com.gubga.gui.EventoMoverJanelaPeloPainel;
import com.gubga.gui.Janela;
import com.gubga.gui.principal.eventos.TratadorEventosJanelaPrincipal;
import com.sun.awt.AWTUtilities;

import de.javasoft.plaf.synthetica.SyntheticaBlackEyeLookAndFeel;


/**
 * Esta e a classe que contem a janela principal do programa. Ela e constituida de um conjunto de menus que sao os modulos do
 * sistema.
 * 
 * @author Charles Garrocho
 */
public class JanelaPrincipal extends Janela {

	private static final long serialVersionUID = 1L;
	private String pathUser;
	private JPopupMenu popMenu;
	private JTable tabelaUsuarios;
	private JScrollPane scrollPane;
	private JTextField campoPesquisa;
	private long timeLastShown = 100;
	private JButton botaoConfiguracao, botaoLimpar, botaoBanir;
	private JMenuItem menuAlternarConta, menuSair;
	private JPanel painelNorte, painelCentro, painelSul, bgPanel;
	private JTextField campoTextoUserId, campoTextoUserName, campoTextoReason;

	public static void main(String args[]) throws UnsupportedLookAndFeelException, ParseException {
		//UIManager.put("Synthetica.window.decoration", Boolean.FALSE);
		UIManager.setLookAndFeel(new SyntheticaBlackEyeLookAndFeel());
		new JanelaPrincipal(null, "C:/Program Files/Garena Plus/Room/user/70290658/ban.dat");
	}

	/**
	 * Este e o construtor. Ele cria os menus e adiciona a janela a barra de menus contendo os modulos do sistema.
	 */
	public JanelaPrincipal(JFrame janelaPai, String pathUser) {
		super();
		this.pathUser = pathUser;
		criarElementos();
		customizarElementos();
		configurarEventos();
		adicionarElementos();
		this.getRootPane().setBorder(new ShadowBorder());
		definirPropriedades(janelaPai, "Gerenciador de Usu�rios Banidos do Garena", null);
	}

	public Janela getThis() {
		return this;
	}

	@Override
	protected void adicionarElementos() {

		popMenu.add(menuAlternarConta);
		popMenu.add(menuSair);

		painelSul.add(campoTextoUserId);
		painelSul.add(campoTextoUserName);
		painelSul.add(campoTextoReason);
		painelSul.add(botaoBanir);

		painelNorte.add(campoPesquisa);
		painelNorte.add(botaoConfiguracao);

		painelCentro.add(scrollPane);

		bgPanel = new Painel(new ImageIcon(getResource("teste3.png")));
		bgPanel.setLayout(new BorderLayout());

		bgPanel.add(painelNorte, BorderLayout.NORTH);
		bgPanel.add(painelCentro, BorderLayout.CENTER);
		bgPanel.add(painelSul, BorderLayout.SOUTH);

		bgPanel.setOpaque(false);

		setContentPane(bgPanel);
	}

	@Override
	protected void configurarEventos() {
		TratadorEventosJanelaPrincipal tratadorEventos = new TratadorEventosJanelaPrincipal(this);

		EventoMoverJanelaPeloPainel a = new EventoMoverJanelaPeloPainel(painelNorte);
		this.addMouseListener(a);
		this.addMouseMotionListener(a);
		campoPesquisa.addKeyListener(tratadorEventos);
		menuSair.addActionListener(tratadorEventos);
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

		menuAlternarConta = new JMenuItem("Trocar", new ImageIcon(getResource("alternar.png")));
		menuSair = new JMenuItem("Sair", new ImageIcon(getResource("sair.png")));

		botaoConfiguracao = new JButton(new ImageIcon(getResource("configuracao.png")));
		botaoLimpar = new JButton(new ImageIcon(getResource("limpar.png")));
		botaoBanir = new JButton(new ImageIcon(getResource("gravar.png")));

		campoPesquisa = new JTextField(15);
		campoTextoUserId = new JTextField(5);
		campoTextoUserName = new JTextField(5);
		campoTextoReason = new JTextField(7);

		String colunasTabela[] = {"UserId", "UserName", "Reason"},
				dadosTabela[][] = new String[0][0];

		tabelaUsuarios = new JTable(new DefaultTableModel(dadosTabela, colunasTabela)){
			private static final long serialVersionUID = 5727320816550514929L;
			public boolean isCellEditable(int rowIndex, int colIndex) {
				return false;
			};
		};

		scrollPane = new JScrollPane(tabelaUsuarios);
	}

	@Override
	protected void customizarElementos() {
		painelNorte.setOpaque(false);
		painelCentro.setOpaque(false);
		painelSul.setOpaque(false);

		painelNorte.setLayout(new FlowLayout());
		painelCentro.setLayout(new FlowLayout());
		painelSul.setLayout(new FlowLayout());

		menuAlternarConta.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		menuSair.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		campoPesquisa.setToolTipText("Digite Aqui o Nick do Usu�rio.");
		campoPesquisa.setDocument(new TamanhoMaximo(25));

		botaoConfiguracao.setBorder(null);
		botaoConfiguracao.setText("OPCOES           ");
		botaoConfiguracao.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		botaoBanir.setBorder(null);
		botaoBanir.setText("Banir    ");
		botaoBanir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		campoPesquisa.setPreferredSize(new Dimension(500, 28));
		campoTextoUserId.setPreferredSize(new Dimension(500, 28));
		campoTextoUserName.setPreferredSize(new Dimension(500, 28));
		campoTextoReason.setPreferredSize(new Dimension(500, 28));

		customizarBotao(botaoLimpar);

		botaoLimpar.setText("Limpar");

		botaoBanir.setToolTipText("Bane um usu�rio de acordo com os Dados Inseridos.");
		menuSair.setToolTipText("Sair do GUBGA.");
		menuAlternarConta.setToolTipText("Trocar a Conta Atual por Outra.");
		botaoLimpar.setToolTipText("Limpar Tabela de Usu�rios do Garena Plus.");
		tabelaUsuarios.setToolTipText("D� um Duplo Clique na Conta a Ser Utilizada.");

		tabelaUsuarios.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		tabelaUsuarios.setSelectionBackground(Color.BLACK);
		tabelaUsuarios.setSelectionForeground(Color.RED);
		tabelaUsuarios.setFocusable(false);
		tabelaUsuarios.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		tabelaUsuarios.getColumn("UserId").setPreferredWidth(10);
		tabelaUsuarios.getColumn("UserName").setPreferredWidth(40);
		tabelaUsuarios.getColumn("Reason").setPreferredWidth(60);

		scrollPane.setPreferredSize(new Dimension(315, 297));
		scrollPane.setBackground(new Color(0,0,0,0));
		scrollPane.setFocusable(false);

		tabelaUsuarios.setDragEnabled(false);
		tabelaUsuarios.getTableHeader().setReorderingAllowed(false);

		JTableHeader header = tabelaUsuarios.getTableHeader();
		header.setResizingAllowed(false);
		header.setForeground(Color.WHITE);
		header.setFont(new Font("Tahoma", Font.PLAIN, 13));
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

	public JButton getBotaoLimpar() {
		return botaoLimpar;
	}

	public String getPathUser() {
		return pathUser;
	}

	public void setPathUser(String pathUser) {
		this.pathUser = pathUser;
	}
}
