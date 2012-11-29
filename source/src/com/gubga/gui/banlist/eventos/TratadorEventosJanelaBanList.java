package com.gubga.gui.banlist.eventos;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import com.gubga.classes.Usuario;
import com.gubga.gui.banlist.JanelaBanList;
import com.gubga.gui.carregamento.JanelaAlternarConta;
import com.gubga.gui.recurso.DialogoErro;
import com.gubga.gui.recurso.DialogoSucesso;
import com.gubga.persistencia.BancoDadosBanList;

public class TratadorEventosJanelaBanList extends MouseAdapter implements ActionListener, KeyListener {

	private DefaultTableModel modeloTabela;
	private JanelaBanList janelaBanList;
	private Point click;

	public TratadorEventosJanelaBanList(JanelaBanList janelaBanList) {

		super();
		this.janelaBanList = janelaBanList;
	}

	public void actionPerformed(ActionEvent evento) {

		// Caso o evento tenha ocorrido no botao Sair.
		if (evento.getSource() == janelaBanList.getMenuSair()) {
			System.exit(0);
		}

		// Caso o evento tenha ocorrido no botao Configurações.
		else if (evento.getSource() == janelaBanList.getMenuAlternarConta()) {
			new JanelaAlternarConta(janelaBanList);
		}

		// Caso o evento tenha ocorrido no botao Banir.
		else if (evento.getSource() == janelaBanList.getBotaoBanir()) {
			banirUsuario();
		}
		
		// Caso o evento tenha ocorrido no menu Desbanir.
		else if (evento.getSource() == janelaBanList.getMenuDesbanir()) {
			desbanirUsuario();
		}
	}

	public void banirUsuario() {
		String UserId = janelaBanList.getCampoTextoUserId().getText();
		String UserName = janelaBanList.getCampoTextoUserName().getText();
		String Reason = janelaBanList.getCampoTextoReason().getText();
		if (UserId.length() == 0 || UserName.length() == 0 || Reason.length() == 0)
			new DialogoErro(janelaBanList, "Erro", "Dados Incompletos Para Banir.");
		else {
			BancoDadosBanList bdBanList = new BancoDadosBanList(janelaBanList.getPathUser());
			if (bdBanList.addUsuario(Integer.parseInt(UserId), UserName, Reason)) {
				new DialogoSucesso(janelaBanList, "Sucesso", "Usuario Adicionado na BanList.");
				janelaBanList.getCampoTextoUserId().setText("");
				janelaBanList.getCampoTextoUserName().setText("");
				janelaBanList.getCampoTextoReason().setText("");
			}
			else
				new DialogoErro(janelaBanList, "Erro", "Usuario Ja Existe Na BanList.");
		}
	}

	public void desbanirUsuario() {
		int coluna = janelaBanList.getTabelaUsuarios().rowAtPoint(click);
		String UserId = janelaBanList.getTabelaUsuarios().getValueAt(coluna, 0).toString();
		BancoDadosBanList bdBanList = new BancoDadosBanList(janelaBanList.getPathUser());
		if (bdBanList.delUsuario(Integer.parseInt(UserId))) {
			new DialogoSucesso(janelaBanList, "Sucesso", "Usuario Removido na BanList.");
			modeloTabela = ((DefaultTableModel)(janelaBanList.getTabelaUsuarios().getModel()));
			modeloTabela.removeRow(coluna);
		}
		else
			new DialogoErro(janelaBanList, "Erro", "Nao Foi Possivel Banir o Usuario.");
	}

	private void pesquisarUsuarios() {
		String texto = janelaBanList.getCampoPesquisa().getText();

		// Obtem a referencia da tabela de usuários. Seta a numero de linhas data tabela usuários com 0.
		modeloTabela = ((DefaultTableModel)(janelaBanList.getTabelaUsuarios().getModel()));
		modeloTabela.setNumRows(0);

		// Adiciona a variavel "%" para pesquisar todos os nomes a partir das letras ja inseridas.
		if (texto.length() > 0) {
			texto += "%";
			BancoDadosBanList bdBanList = new BancoDadosBanList(janelaBanList.getPathUser());
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
		if ( evento.getButton() == MouseEvent.BUTTON3) {
			if (evento.getSource() == janelaBanList.getTabelaUsuarios()) {
				janelaBanList.getPopMenuTabela().show( evento.getComponent(), evento.getX(), evento.getY() );
				click = new Point(evento.getX(), evento.getY());
			}
		}
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
