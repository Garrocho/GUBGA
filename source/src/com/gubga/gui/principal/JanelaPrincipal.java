package com.gubga.gui.principal;

import static com.gubga.classes.Recursos.customizarBotao;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;

import com.gubga.classes.TamanhoMaximo;
import com.gubga.gui.Janela;

import de.javasoft.plaf.synthetica.SyntheticaBlackEyeLookAndFeel;

public class JanelaPrincipal extends Janela {

	private static final long serialVersionUID = 1L;
	private JTable tabelaBanidos;
	private JLabel rotuloNick;
	private JTextField campoTextoNick;
	private JButton botaoLimpar, botaoSair;
	private JPanel painelNorte, painelCentro, painelSul;

	public static void main(String args[]) throws UnsupportedLookAndFeelException, ParseException {
		UIManager.put("Synthetica.window.decoration", Boolean.FALSE);
		UIManager.setLookAndFeel(new SyntheticaBlackEyeLookAndFeel());
		new JanelaPrincipal(null);
	}

	public JanelaPrincipal(Component janelaPai) {
		super();
		criarElementos();
		customizarElementos();
		configurarEventos();
		adicionarElementos();
		definirPropriedades(janelaPai, "GUBGA - Gerenciador de Usuarios Banidos do Garena");
	}

	@Override
	protected void adicionarElementos() {
		
		painelNorte.add(rotuloNick);
		painelNorte.add(campoTextoNick);
		
		painelCentro.add(new JScrollPane(tabelaBanidos));
		
		painelSul.add(botaoLimpar);
		painelSul.add(botaoSair);

		add(painelNorte, BorderLayout.NORTH);
		add(painelCentro, BorderLayout.CENTER);
		add(painelSul, BorderLayout.SOUTH);
	}

	@Override
	protected void configurarEventos() {
	}

	@Override
	protected void criarElementos() {
		painelNorte = new JPanel();
		painelCentro = new JPanel();
		painelSul = new JPanel();
		
		campoTextoNick = new JTextField(50);
		rotuloNick = new JLabel("NICK");
		
		botaoLimpar = new JButton("LIMPAR");
		botaoSair = new JButton("SAIR");

		String colunasTabela[] = {"ID", "NICK", "MOTIVO"},
		dadosTabela[][] = new String[0][0];

		tabelaBanidos = new JTable(new DefaultTableModel(dadosTabela, colunasTabela)){
			private static final long serialVersionUID = 5727320816550514929L;
			public boolean isCellEditable(int rowIndex, int colIndex) {
				if (colIndex == getColumn("ID").getModelIndex() || colIndex == getColumn("NICK").getModelIndex() || colIndex == getColumn("MOTIVO").getModelIndex() ){
					return false;
				}
				else
					return true;
			};
		};
	}

	@Override
	protected void customizarElementos() {
		rotuloNick.setFont(new Font(Font.DIALOG, Font.BOLD, 17));

		campoTextoNick.setPreferredSize(new Dimension(110, 30));
		campoTextoNick.setDocument(new TamanhoMaximo(15));
		campoTextoNick.setToolTipText("Descreva o nick que deseja procurar na lista de banidos.");

		tabelaBanidos.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		tabelaBanidos.setForeground(new Color(0,185,242));
		tabelaBanidos.getColumn("ID").setPreferredWidth(5);
		tabelaBanidos.getColumn("NICK").setPreferredWidth(30);
		tabelaBanidos.getColumn("MOTIVO").setPreferredWidth(160);
		
		customizarBotao(botaoLimpar);
		customizarBotao(botaoSair);
	}

	public JanelaPrincipal getThis() {
		return this;
	}
}