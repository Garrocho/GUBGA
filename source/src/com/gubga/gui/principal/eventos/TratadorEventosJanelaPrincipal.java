package com.gubga.gui.principal.eventos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.table.DefaultTableModel;

import com.gubga.gui.principal.JanelaPrincipal;

public class TratadorEventosJanelaPrincipal extends MouseAdapter implements ActionListener, KeyListener {

	private DefaultTableModel modeloTabela;
	private JanelaPrincipal janelaPrincipal;

	public TratadorEventosJanelaPrincipal(JanelaPrincipal janelaPrincipal) {

		super();
		this.janelaPrincipal = janelaPrincipal;
	}

	public void actionPerformed(ActionEvent evento) {

		//1modeloTabela = ((DefaultTableModel)(janelaPrincipal.getTabelaUsuarios().getModel()));
		//modeloTabela.setNumRows(0);
	}

	/*private void pesquisarUsuarios() {
		String diretorio = janelaPrincipal.getCampoTextoDiretorio().getText();
		if (diretorio.equalsIgnoreCase("Diretório do Garena Plus."))
			JOptionPane.showMessageDialog(janelaPrincipal, "O Diretório do Garena Não Foi Selecionado.", "Erro ao Abrir Diretório", JOptionPane.ERROR_MESSAGE);
		else {
			File d2 = new File(diretorio + "/room/user/");
			if (!d2.exists())
				JOptionPane.showMessageDialog(janelaPrincipal, "O Diretório Selecionado Não é Um Diretório do Garena Plus.", "Erro de Diretório Inválido", JOptionPane.ERROR_MESSAGE);
			else {
				String userIds[] = d2.list();
				System.out.println(d2.getAbsolutePath());
				modeloTabela = ((DefaultTableModel)(janelaPrincipal.getTabelaUsuarios().getModel()));
				modeloTabela.setNumRows(0);
				Object[] linha = new Object[1];
				for (String user : userIds) {
					linha[0] = user;
					modeloTabela.addRow(linha);
				}
			}
		}
	}*/

	@Override
	public void mouseClicked(MouseEvent evento) {
		if (evento.getSource() == janelaPrincipal.getTabelaUsuarios()) {
			int selecionado = janelaPrincipal.getTabelaUsuarios().getSelectedRow();
			String usuario = janelaPrincipal.getTabelaUsuarios().getValueAt(selecionado, 0).toString();
			new JanelaPrincipal(janelaPrincipal, usuario);
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
	}
}
