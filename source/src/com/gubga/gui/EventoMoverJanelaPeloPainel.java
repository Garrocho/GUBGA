package com.gubga.gui;

import java.awt.Container;
import java.awt.Cursor;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JComponent;
import javax.swing.JFrame;

/** Classe para tratar os eventos dos <code>JPopupMenu</code> usados nas tabelas de algumas janelas do sistema.

 * @see MouseAdapter
 * @see MouseMotionListener
 */
public class EventoMoverJanelaPeloPainel extends MouseAdapter implements MouseMotionListener {

	JComponent painel;
	Point localizacaoInicialDrag;
	Point localizacaoInicial;

	/** Cria uma instancia
	 * @param painel <code>JComponent</code> com o painel que pode ser movimentado
	 */
	public EventoMoverJanelaPeloPainel(JComponent painel) {
		this.painel = painel;
	}

	/** Retorna o frame onde o container esta
	 * @param container <code>Container</code> com o container
	 * @return <code>JFrame</code> com o frame onde esta o container
	 */
	public static JFrame getFrame(Container container) {
		if (container instanceof JFrame) {
			return (JFrame) container;
		}
		return getFrame(container.getParent());
	}

	/** Retorna a posicao do painel na tela
	 * @param evento <code>MouseEvent</code> com o evento do mouse
	 * @return <code>Point</code> com a posicao do painel na tela
	 */
	Point getScreenLocation(MouseEvent evento) {
		Point cursor = evento.getPoint();
		Point localizacao = this.painel.getLocationOnScreen();
		return new Point((int) (localizacao.getX() + cursor.getX()), (int) (localizacao.getY() + cursor.getY()));
	}

	@Override
	public void mousePressed(MouseEvent evento) {
		this.localizacaoInicialDrag = this.getScreenLocation(evento);
		this.localizacaoInicial = EventoMoverJanelaPeloPainel.getFrame(this.painel).getLocation();
		this.painel.setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
		if(this.painel.getCursor() != Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR)){
			this.painel.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
		}
	}

	@Override
	public void mouseReleased(MouseEvent evento){
		this.painel.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
	}

	@Override
	public void mouseDragged(MouseEvent evento) {
		Point current = this.getScreenLocation(evento);
		Point offset = new Point((int) current.getX() - (int) localizacaoInicialDrag.getX(), (int) current.getY() - (int) localizacaoInicialDrag.getY());
		JFrame frame = EventoMoverJanelaPeloPainel.getFrame(painel);
		Point new_location = new Point((int) (this.localizacaoInicial.getX() + offset.getX()), (int) (this.localizacaoInicial.getY() + offset.getY()));
		frame.setLocation(new_location);
	}
}