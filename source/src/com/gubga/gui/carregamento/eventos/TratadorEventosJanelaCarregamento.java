package com.gubga.gui.carregamento.eventos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import com.gubga.gui.Janela;
import com.gubga.gui.carregamento.JanelaCarregamento;
import com.sun.org.apache.bcel.internal.classfile.Field;

public class TratadorEventosJanelaCarregamento extends KeyAdapter implements ActionListener, MouseListener {

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
	}

	public void keyReleased(KeyEvent event) {
	}

	private void pesquisarUsuarios() {
	}

	public void mouseClicked(MouseEvent evento) {
	}

	// Os metodos abaixo nao sao implementados.
	public void mouseEntered(MouseEvent evento) {
	}
	public void mouseExited(MouseEvent evento) {
	}
	public void mousePressed(MouseEvent evento) {
	}
	public void mouseReleased(MouseEvent e) {
	}
}
