package com.gubga.gui.carregamento;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.text.ParseException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;

import com.gubga.gui.Janela;
import com.gubga.gui.carregamento.eventos.TratadorEventosJanelaCarregamento;

import static com.gubga.classes.Recursos.*;

import de.javasoft.plaf.synthetica.SyntheticaBlackEyeLookAndFeel;

public class JanelaCarregamento extends Janela {

	private static final long serialVersionUID = 1L;
	private JScrollPane scrollPane;
	private JTable tabelaUsuarios;
	private JTextField campoTextoDiretorio;
	private JButton botaoCarregar, botaoLimpar, botaoSair, botaoDiretorio;
	private JPanel painelNorte, painelCentro, painelSul, painelDiretorio;

	public static void main(String args[]) throws UnsupportedLookAndFeelException, ParseException {
		UIManager.put("Synthetica.window.decoration", Boolean.FALSE);
		UIManager.setLookAndFeel(new SyntheticaBlackEyeLookAndFeel());
		new JanelaCarregamento(null);
	}

	public JanelaCarregamento(Component janelaPai) {
		super();
		criarElementos();
		customizarElementos();
		configurarEventos();
		adicionarElementos();
		definirPropriedades(janelaPai, "GUBGA - Carregamento de Usuários");
	}

	@Override
	protected void adicionarElementos() {
		
		painelDiretorio.add(campoTextoDiretorio);
		painelDiretorio.add(botaoDiretorio);
		
		painelNorte.add(painelDiretorio);
		
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
		TratadorEventosJanelaCarregamento tratadorEventos = new TratadorEventosJanelaCarregamento(this);
		botaoDiretorio.addActionListener(tratadorEventos);
		botaoSair.addActionListener(tratadorEventos);
		botaoCarregar.addActionListener(tratadorEventos);
		botaoLimpar.addActionListener(tratadorEventos);
	}

	@Override
	protected void criarElementos() {
		painelNorte = new JPanel();
		painelCentro = new JPanel();
		painelSul = new JPanel();
		painelDiretorio = new JPanel();
		
		campoTextoDiretorio = new JTextField(29);
		
		botaoCarregar = new JButton(new ImageIcon(Janela.getResource("popular.png")));
		botaoSair = new JButton(new ImageIcon(Janela.getResource("sair2.png")));
		botaoDiretorio = new JButton(new ImageIcon(Janela.getResource("abrir.png")));
		botaoLimpar = new JButton(new ImageIcon(Janela.getResource("limpar.png")));

		String colunasTabela[] = {"Selecione Abaixo uma Conta do Garena Plus"},
		dadosTabela[][] = new String[0][0];

		tabelaUsuarios = new JTable(new DefaultTableModel(dadosTabela, colunasTabela)){
			private static final long serialVersionUID = 5727320816550514929L;
			public boolean isCellEditable(int rowIndex, int colIndex) {
				if (colIndex == getColumn("Selecione Abaixo uma Conta do Garena Plus").getModelIndex()){
					return false;
				}
				else
					return true;
			};
		};
		
		scrollPane = new JScrollPane(tabelaUsuarios);
	}

	@Override
	protected void customizarElementos() {
		
		campoTextoDiretorio.setText("Diretório do Garena Plus.");
		
		campoTextoDiretorio.setPreferredSize(new Dimension(110, 30));
		campoTextoDiretorio.setEditable(false);

		tabelaUsuarios.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		tabelaUsuarios.setForeground(new Color(0,185,242));
		
		scrollPane.setPreferredSize(new Dimension(310, 200));
		
		customizarBotao(botaoCarregar);
		customizarBotao(botaoSair);
		customizarBotao(botaoDiretorio);
		customizarBotao(botaoLimpar);
		
		botaoCarregar.setText("Carregar");
		botaoSair.setText("Sair");
		botaoLimpar.setText("Limpar");
		
		botaoDiretorio.setToolTipText("Selecione o Diretório da Pasta do Garena Plus.");
		botaoCarregar.setToolTipText("Carregar Contas Existentes da Pasta do Garena Plus.");
		botaoSair.setToolTipText("Sair do GUBGA.");
		botaoLimpar.setToolTipText("Limpar Tabela de Usuários do Garena Plus.");
	}

	public JanelaCarregamento getThis() {
		return this;
	}

	public JTable getTabelaUsuarios() {
		return tabelaUsuarios;
	}

	public JTextField getCampoTextoDiretorio() {
		return campoTextoDiretorio;
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

	public JButton getBotaoDiretorio() {
		return botaoDiretorio;
	}
}