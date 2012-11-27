package com.gubga.gui.principal.eventos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import com.gubga.classes.Usuario;
import com.gubga.gui.carregamento.JanelaCarregamento;
import com.gubga.gui.principal.JanelaPrincipal;
import com.gubga.persistencia.BancoDadosBanList;

public class TratadorEventosJanelaPrincipal extends MouseAdapter implements ActionListener, KeyListener {

	private DefaultTableModel modeloTabela;
	private JanelaPrincipal janelaPrincipal;

	public TratadorEventosJanelaPrincipal(JanelaPrincipal janelaPrincipal) {

		super();
		this.janelaPrincipal = janelaPrincipal;
	}

	public void actionPerformed(ActionEvent evento) {

		// Caso o evento tenha ocorrido no botao Sair.
		if (evento.getSource() == janelaPrincipal.getBotaoSair()) {
			System.exit(0);
		}
		
		// Caso o evento tenha ocorrido no botao Configurações.
		else if (evento.getSource() == janelaPrincipal.getMenuAlternarConta()) {
				new JanelaCarregamento(janelaPrincipal);
			}

		// Caso o evento tenha ocorrido no botao Limpar.
		else if (evento.getSource() == janelaPrincipal.getBotaoLimpar()) {

			modeloTabela = ((DefaultTableModel)(janelaPrincipal.getTabelaUsuarios().getModel()));
			modeloTabela.setNumRows(0);
		}

		// Caso o evento tenha ocorrido no botao Carregar.
		else if (evento.getSource() == janelaPrincipal.getBotaoCarregar()) {
			pesquisarUsuarios();
		}
	}

	private void pesquisarUsuarios() {
		String texto = janelaPrincipal.getCampoPesquisa().getText();

		// Obtem a referencia da tabela de usuários. Seta a numero de linhas data tabela usuários com 0.
		modeloTabela = ((DefaultTableModel)(janelaPrincipal.getTabelaUsuarios().getModel()));
		modeloTabela.setNumRows(0);

		// Adiciona a variavel "%" para pesquisar todos os nomes a partir das letras ja inseridas.
		if (texto.length() > 0) {
			texto += "%";
			BancoDadosBanList bdBanList = new BancoDadosBanList(janelaPrincipal.getEndereco());
			ArrayList<Usuario> usuarios = bdBanList.getUsuario(texto);
			for (Usuario user : usuarios) {
				Object[] linha = new Object[3];
				linha[0] = user.getUserId();
				linha[1] = user.getUserName();
				linha[2] = user.getReason();
				modeloTabela.addRow(linha);
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent evento) {
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		pesquisarUsuarios();
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
	}
}
