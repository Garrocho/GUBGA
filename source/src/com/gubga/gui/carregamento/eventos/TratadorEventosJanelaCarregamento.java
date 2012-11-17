package com.gubga.gui.carregamento.eventos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import com.gubga.gui.Janela;
import com.gubga.gui.carregamento.JanelaCarregamento;

public class TratadorEventosJanelaCarregamento extends MouseAdapter implements ActionListener {

	private DefaultTableModel modeloTabela;
	private JanelaCarregamento janelaCarregamento;

	public TratadorEventosJanelaCarregamento(JanelaCarregamento janelaCarregamento) {

		super();
		this.janelaCarregamento = janelaCarregamento;
	}

	public void actionPerformed(ActionEvent evento) {
		
		// Caso o evento tenha ocorrido no botao Diretorio.
		if (evento.getSource() == janelaCarregamento.getBotaoDiretorio()) {
			String diretorio = Janela.dialogoAbrirDiretorio(janelaCarregamento, "Selecione o Diretório da Pasta do Garena Plus.");
			if (diretorio != null)
				janelaCarregamento.getCampoTextoDiretorio().setText(diretorio);
			else
				janelaCarregamento.getCampoTextoDiretorio().setText("Diretório do Garena Plus.");
		}
		
		// Caso o evento tenha ocorrido no botao Sair.
		else if (evento.getSource() == janelaCarregamento.getBotaoSair()) {
			System.exit(0);
		}
		
		// Caso o evento tenha ocorrido no botao Limpar.
		else if (evento.getSource() == janelaCarregamento.getBotaoLimpar()) {

			modeloTabela = ((DefaultTableModel)(janelaCarregamento.getTabelaUsuarios().getModel()));
			modeloTabela.setNumRows(0);
		}
		
		// Caso o evento tenha ocorrido no botao Carregar.
		else if (evento.getSource() == janelaCarregamento.getBotaoCarregar()) {
			pesquisarUsuarios();
		}
	}

	private void pesquisarUsuarios() {
		String diretorio = janelaCarregamento.getCampoTextoDiretorio().getText();
		if (diretorio.equalsIgnoreCase("Diretório do Garena Plus."))
			JOptionPane.showMessageDialog(janelaCarregamento, "O Diretório do Garena Não Foi Selecionado.", "Erro ao Abrir Diretório", JOptionPane.ERROR_MESSAGE);
		else {
			File d2 = new File(diretorio + "/room/user/");
			if (!d2.exists())
				JOptionPane.showMessageDialog(janelaCarregamento, "O Diretório Selecionado Não é Um Diretório do Garena Plus.", "Erro de Diretório Inválido", JOptionPane.ERROR_MESSAGE);
			else {
				String userIds[] = d2.list();
				System.out.println(d2.getAbsolutePath());
				modeloTabela = ((DefaultTableModel)(janelaCarregamento.getTabelaUsuarios().getModel()));
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
		if (evento.getClickCount() == 2 && evento.getSource() == janelaCarregamento.getTabelaUsuarios()) {
			int selecionado = janelaCarregamento.getTabelaUsuarios().getSelectedRow();
			String userId = janelaCarregamento.getTabelaUsuarios().getValueAt(selecionado, 0).toString();
			System.out.println(userId);
		}
	}
}
