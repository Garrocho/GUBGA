package com.gubga.gui.banlist;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.ParseException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import com.gubga.gui.Janela;
import com.gubga.gui.banlist.eventos.TratadorEventosJanelaBanList;
import com.gubga.gui.recurso.SomenteNumeros;
import com.gubga.gui.recurso.TamanhoMaximo;

import de.javasoft.plaf.synthetica.SyntheticaBlackEyeLookAndFeel;


/**
 * Esta e a classe que contem a janela principal do programa. Ela e constituida de um conjunto de menus que sao os modulos do
 * sistema.
 * 
 * @author Charles Garrocho
 */
public class JanelaBanList extends Janela {

	private static final long serialVersionUID = 1L;
	private String pathUser;
	private JPopupMenu popMenuConfiguracoes, popMenuTabela;
	private JTable tabelaUsuarios;
	private JScrollPane scrollPane;
	private JTextField campoPesquisa;
	private long timeLastShown = 100;
	private JButton botaoConfiguracao, botaoBanir;
	private JMenuItem menuAlternarConta, menuSair, menuDesbanir;
	private JPanel painelNorte, painelCentro, painelSul, bgPanel;
	private JTextField campoTextoUserId, campoTextoUserName, campoTextoReason;

	public static void main(String args[]) throws UnsupportedLookAndFeelException, ParseException {
		UIManager.setLookAndFeel(new SyntheticaBlackEyeLookAndFeel());
		new JanelaBanList(null, "C:/Program Files/Garena Plus/Room/user/70290658/ban.dat");
	}

	/**
	 * Este e o construtor. Ele cria os menus e adiciona a janela a barra de menus contendo os modulos do sistema.
	 */
	public JanelaBanList(JFrame janelaPai, String pathUser) {
		super();
		this.pathUser = pathUser;
		criarElementos();
		customizarElementos();
		configurarEventos();
		adicionarElementos();
		definirPropriedades(janelaPai, "Gerenciador de Usuarios Banidos do Garena", null);
	}

	public Janela getThis() {
		return this;
	}

	@Override
	protected void adicionarElementos() {

		popMenuConfiguracoes.add(menuAlternarConta);
		popMenuConfiguracoes.add(menuSair);
		
		popMenuTabela.add(menuDesbanir);

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
		TratadorEventosJanelaBanList tratadorEventos = new TratadorEventosJanelaBanList(this);

		campoPesquisa.addKeyListener(tratadorEventos);
		menuSair.addActionListener(tratadorEventos);
		menuDesbanir.addActionListener(tratadorEventos);
		botaoBanir.addActionListener(tratadorEventos);
		tabelaUsuarios.addMouseListener(tratadorEventos);
		menuAlternarConta.addActionListener(tratadorEventos);
		botaoConfiguracao.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if ((System.currentTimeMillis() - timeLastShown) > 300) {
					Component c = (Component) e.getSource();
					popMenuConfiguracoes.show(c, 25, c.getHeight());
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

		popMenuConfiguracoes = new JPopupMenu();
		popMenuTabela = new JPopupMenu();

		menuAlternarConta = new JMenuItem("Trocar", new ImageIcon(getResource("alternar.png")));
		menuSair = new JMenuItem("Sair", new ImageIcon(getResource("sair.png")));
		menuDesbanir = new JMenuItem("Desbanir", new ImageIcon(getResource("delete.png")));

		botaoConfiguracao = new JButton(new ImageIcon(getResource("configuracao.png")));
		botaoBanir = new JButton(new ImageIcon(getResource("gravar.png")));

		campoPesquisa = new JTextField(22);
		campoTextoUserId = new JTextField(6);
		campoTextoUserName = new JTextField(9);
		campoTextoReason = new JTextField(10);
		
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
		
		campoTextoUserId.setDocument(new SomenteNumeros());
		campoTextoUserName.setDocument(new TamanhoMaximo(15));
		campoTextoReason.setDocument(new TamanhoMaximo(30));

		menuAlternarConta.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		menuSair.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		menuDesbanir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		campoPesquisa.setToolTipText("Digite Aqui o Nick do Usuario.");
		campoPesquisa.setDocument(new TamanhoMaximo(25));

		botaoConfiguracao.setBorder(null);
		botaoConfiguracao.setText("OPCOES               ");
		botaoConfiguracao.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		botaoBanir.setBorder(null);
		botaoBanir.setText("Banir    ");
		botaoBanir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		campoPesquisa.setPreferredSize(new Dimension(500, 28));
		campoTextoUserId.setPreferredSize(new Dimension(500, 28));
		campoTextoUserName.setPreferredSize(new Dimension(500, 28));
		campoTextoReason.setPreferredSize(new Dimension(500, 28));

		botaoBanir.setToolTipText("Bane um usuario de acordo com os Dados Inseridos.");
		menuSair.setToolTipText("Sair do GUBGA.");
		menuDesbanir.setToolTipText("Remove o Usuario da BanList.");
		menuAlternarConta.setToolTipText("Trocar a Conta Atual por Outra.");
		tabelaUsuarios.setToolTipText("De um Duplo Clique na Conta a Ser Utilizada.");

		tabelaUsuarios.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		tabelaUsuarios.setSelectionBackground(Color.BLACK);
		tabelaUsuarios.setSelectionForeground(Color.GREEN);
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
		return popMenuConfiguracoes;
	}

	public JButton getBotaoBanir() {
		return botaoBanir;
	}

	public String getPathUser() {
		return pathUser;
	}

	public void setPathUser(String pathUser) {
		this.pathUser = pathUser;
	}

	public JTextField getCampoTextoUserId() {
		return campoTextoUserId;
	}

	public JTextField getCampoTextoUserName() {
		return campoTextoUserName;
	}

	public JTextField getCampoTextoReason() {
		return campoTextoReason;
	}

	public JMenuItem getMenuDesbanir() {
		return menuDesbanir;
	}

	public JPopupMenu getPopMenuTabela() {
		return popMenuTabela;
	}
}
