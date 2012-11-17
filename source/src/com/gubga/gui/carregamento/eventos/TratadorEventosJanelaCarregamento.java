package com.gubga.gui.carregamento.eventos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.net.DatagramSocket;
import java.net.SocketException;

import javax.swing.table.DefaultTableModel;

import com.gubga.gui.carregamento.JanelaCarregamento;

public class TratadorEventosJanelaCarregamento extends KeyAdapter implements ActionListener, MouseListener {

	private DefaultTableModel modeloTabela;
	private JanelaCarregamento janelaCarregamento;

	public TratadorEventosJanelaCarregamento(JanelaCarregamento janelaCarregamento) {

		super();
		this.janelaCarregamento = janelaCarregamento;
	}

	public void actionPerformed(ActionEvent evento) {
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
