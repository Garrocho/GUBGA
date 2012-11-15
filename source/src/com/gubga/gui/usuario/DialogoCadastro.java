package com.gubga.gui.usuario;

import static com.gubga.classes.Recursos.customizarBotao;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.gubga.classes.TamanhoMaximo;
import com.gubga.gui.Dialogo;

public class DialogoCadastro extends Dialogo {

	private static final long serialVersionUID = 1L;
	private JTextField fieldNick;
	private JPasswordField fieldSenha;
	private JButton botaoCadastrar, botaoSair;
	private JLabel labelNick, labelSenha, labelImagem;
	private JPanel painelNorte, painelCentro, painelSul, painelNick, painelSenha;
	
	public static void main(String args[]) {
		new DialogoCadastro(null);
	}

	public DialogoCadastro(Component janelaPai) {
		super();
		criarElementos();
		customizarElementos();
		configurarEventos();
		adicionarElementos();
		definirPropriedades(janelaPai, "Gerenciador de Usuarios Banidos do Garena- Cadastro de Usuario");
	}
	
	@Override
	protected void adicionarElementos() {
		painelNick.add(labelNick);
		painelNick.add(fieldNick);
		
		painelSenha.add(labelSenha);
		painelSenha.add(fieldSenha);
		
		painelNorte.add(labelImagem);
		
		painelCentro.add(painelNick);
		painelCentro.add(painelSenha);
		
		painelSul.add(botaoCadastrar);
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
		painelCentro = new JPanel(new GridLayout(2,1));
		painelSul = new JPanel();
		painelNick = new JPanel();
		painelSenha = new JPanel();
		
		labelNick = new JLabel("NICK   ");
		labelSenha = new JLabel("SENHA");
		
		fieldNick = new JTextField(13);
		fieldSenha = new JPasswordField(13);
		
		labelImagem = new JLabel(new ImageIcon("Recursos//Icones//Imagens//logo_150.png"));
		
		botaoCadastrar = new JButton("CADASTRAR");
		botaoSair = new JButton("SAIR");
	}

	@Override
	protected void customizarElementos() {
		labelNick.setFont(new Font(Font.DIALOG, Font.BOLD, 17));
		labelSenha.setFont(new Font(Font.DIALOG, Font.BOLD, 17));
		
		fieldNick.setPreferredSize(new Dimension(110, 30));
		fieldNick.setDocument(new TamanhoMaximo(15));
		fieldNick.setToolTipText("Descreva um nick que sera utilizado para efetuar login.");
		
		fieldSenha.setPreferredSize(new Dimension(110, 30));
		fieldSenha.setDocument(new TamanhoMaximo(15));
		fieldSenha.setToolTipText("Descreva uma senha de no minimo 6 caracteres e no maximo 15.");

		botaoCadastrar.setPreferredSize(new Dimension(140,50));
		botaoCadastrar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		customizarBotao(botaoCadastrar);

		botaoSair.setPreferredSize(new Dimension(85,50));
		botaoSair.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		customizarBotao(botaoSair);
	}
	
	public DialogoCadastro getThis() {
		return this;
	}

	public JPasswordField getFieldSenha() {
		return fieldSenha;
	}

	public JButton getBotaoCadastrar() {
		return botaoCadastrar;
	}

	public JButton getbotaoSair() {
		return botaoSair;
	}

	public JTextField getFieldNick() {
		return fieldNick;
	}
}