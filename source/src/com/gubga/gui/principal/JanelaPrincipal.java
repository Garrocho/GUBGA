package com.gubga.gui.principal;

import static com.gubga.classes.Recursos.customizarBotao;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.ParseException;

import javax.swing.BorderFactory;
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
	private String usuario, endereco;
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

	public static void main(String args[]) throws UnsupportedLookAndFeelException, ParseException {
		UIManager.put("Synthetica.window.decoration", Boolean.FALSE);
		UIManager.setLookAndFeel(new SyntheticaBlackEyeLookAndFeel());
		new JanelaPrincipal(null, "10661343", "C:/Program Files/Garena Plus/Room/user/70290658/ban.dat");
	}

	/**
	 * Este e o construtor. Ele cria os menus e adiciona a janela a barra de menus contendo os modulos do sistema.
	 */
	public JanelaPrincipal(JFrame janelaPai, String usuario, String endereco) {
		super();
		this.usuario = usuario;
		this.endereco = endereco;
		criarElementos();
		customizarElementos();
		configurarEventos();
		adicionarElementos();
		definirPropriedades(janelaPai, "GUBGA - Gerenciador de Usuários Banidos do Garena", null);
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

		JPanel bgPanel = new Painel(new ImageIcon(getResource("gg4.png")));
        bgPanel.setLayout(new BorderLayout());

        bgPanel.add(painelNorte, BorderLayout.NORTH);
        bgPanel.add(painelCentro, BorderLayout.CENTER);
        bgPanel.add(painelSul, BorderLayout.SOUTH);
        
        setContentPane(bgPanel);
	}

	@Override
	protected void configurarEventos() {
		TratadorEventosJanelaPrincipal tratadorEventos = new TratadorEventosJanelaPrincipal(this);
		campoPesquisa.addKeyListener(tratadorEventos);
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
		
		painelNorte.setOpaque(false);
		painelCentro.setOpaque(false);
		painelSul.setOpaque(false);
		
		painelNorte.setLayout(new FlowLayout());
		painelCentro.setLayout(new FlowLayout());
		painelSul.setLayout(new FlowLayout());

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
		
		tabelaUsuarios.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		tabelaUsuarios.setSelectionBackground(Color.BLACK);
		tabelaUsuarios.setSelectionForeground(Color.RED);
		tabelaUsuarios.setFocusable(false);
		tabelaUsuarios.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		scrollPane.setPreferredSize(new Dimension(450, 300));
		scrollPane.setBackground(new Color(0,0,0,0));
		scrollPane.setFocusable(false);
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

	public JButton getBotaoCarregar() {
		return botaoCarregar;
	}

	public JButton getBotaoLimpar() {
		return botaoLimpar;
	}

	public JButton getBotaoSair() {
		return botaoSair;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getEndereco() {
		return endereco;
	}
}

class Painel extends JPanel {
	private static final long serialVersionUID = 1L;
	private Image bg;
	
	public Painel(ImageIcon imagem) {
		this.bg = imagem.getImage();
	}
    
    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
    }
}