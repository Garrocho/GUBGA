package com.gubga.gui.carregamento.eventos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import com.gubga.gui.Janela;
import com.gubga.gui.carregamento.JanelaAlternarConta;

public class TratadorEventosDialogoCarregamento extends MouseAdapter implements ActionListener {

	private DefaultTableModel modeloTabela;
	private JanelaAlternarConta janelaAlternarConta;

	public TratadorEventosDialogoCarregamento(JanelaAlternarConta janelaAlternarConta) {

		super();
		this.janelaAlternarConta = janelaAlternarConta;
	}

	public void actionPerformed(ActionEvent evento) {
		
		// Caso o evento tenha ocorrido no botao Diretorio.
		if (evento.getSource() == janelaAlternarConta.getBotaoDiretorio()) {
			String diretorio = Janela.dialogoAbrirDiretorio(janelaAlternarConta, "Selecione o Diretório da Pasta do Garena Plus.");
			if (diretorio != null)
				janelaAlternarConta.getCampoTextoDiretorio().setText(diretorio);
			else
				janelaAlternarConta.getCampoTextoDiretorio().setText("Diretório do Garena Plus.");
		}
		
		// Caso o evento tenha ocorrido no botao Sair.
		else if (evento.getSource() == janelaAlternarConta.getBotaoSair()) {
			janelaAlternarConta.dispose();
		}
		
		// Caso o evento tenha ocorrido no botao Limpar.
		else if (evento.getSource() == janelaAlternarConta.getBotaoLimpar()) {

			modeloTabela = ((DefaultTableModel)(janelaAlternarConta.getTabelaUsuarios().getModel()));
			modeloTabela.setNumRows(0);
		}
		
		// Caso o evento tenha ocorrido no botao Carregar.
		else if (evento.getSource() == janelaAlternarConta.getBotaoCarregar()) {
			pesquisarUsuarios();
		}
	}

	private void pesquisarUsuarios() {
		String diretorio = janelaAlternarConta.getCampoTextoDiretorio().getText();
		if (diretorio.equalsIgnoreCase("Diretório do Garena Plus."))
			JOptionPane.showMessageDialog(janelaAlternarConta, "O Diretório do Garena Não Foi Selecionado.", "Erro ao Abrir Diretório", JOptionPane.ERROR_MESSAGE);
		else {
			File d2 = new File(diretorio + "/room/user/");
			if (!d2.exists())
				JOptionPane.showMessageDialog(janelaAlternarConta, "O Diretório Selecionado Não é Um Diretório do Garena Plus.", "Erro de Diretório Inválido", JOptionPane.ERROR_MESSAGE);
			else {
				String userIds[] = d2.list();
				System.out.println(d2.getAbsolutePath());
				modeloTabela = ((DefaultTableModel)(janelaAlternarConta.getTabelaUsuarios().getModel()));
				modeloTabela.setNumRows(0);
				Object[] linha = new Object[1];
				for (String user : userIds) {
					linha[0] = user;
					modeloTabela.addRow(linha);
				}
			}
		}
	}

	public void mouseClicked(MouseEvent evento) {
		if (evento.getClickCount() == 2 && evento.getSource() == janelaAlternarConta.getTabelaUsuarios()) {
			int selecionado = janelaAlternarConta.getTabelaUsuarios().getSelectedRow();
			String diretorio = janelaAlternarConta.getCampoTextoDiretorio().getText();
			String usuario = janelaAlternarConta.getTabelaUsuarios().getValueAt(selecionado, 0).toString();
			janelaAlternarConta.getJanelaPrincipal().setPathUser(diretorio + "/room/user/" + usuario + "/ban.dat");
			janelaAlternarConta.dispose();
		}
	}
}
