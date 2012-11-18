package com.gubga.gui.ajuda;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;

import com.gubga.gui.Dialogo;

/**
 * Esta classe extende um <code>JDialog</code> e cria uma interface grafica com as informacoes do software e equipe.
 * 
 * @author Charles Garrocho
 */
public class DialogoSobre extends Dialogo {
	
	private static final long serialVersionUID = 1L;
	private JTabbedPane tabbedPane;
	private JPanel painel1;
	private JPanel painel11;
	private Image img;
	private JPanel painel12;
	private JPanel painel2;
	private JPanel painel3;
	
	/**
	 * Este e o construtor. Ele constroi a interface grafica do dialogo sobre.
	 */
	public DialogoSobre(JFrame janelaPai) {
		
		super();
		criarElementos();
		customizarElementos();
		configurarEventos();
		adicionarElementos();
		definirPropriedades(janelaPai, "Sobre o GUBGA", new Dimension(610, 120));
	}

	@Override
	protected void adicionarElementos() {
		//painel11.add(new JLabel(new ImageIcon(img), SwingConstants.LEFT));
		
		painel12.add(new JLabel("Gerenciador de Usuários Banidos do Garena", SwingConstants.CENTER));
		painel12.add(new JLabel("Versao: 1.0", SwingConstants.CENTER));
		painel12.add(new JLabel("Copyright(c), BUILD SYSTEM", SwingConstants.CENTER));
		painel1.add(painel11);
		painel1.add(painel12);
		
		// Adiciona o conteúdo do painel 1 na primeira aba do TabbedPane.
		tabbedPane.addTab("Sobre", null, painel1, "Informacoes do software.");
		
		// Cria o conteúdo da segunda aba e coloca no painel 2.  
		painel3.add(new JLabel("Desenvolvedor: Charles Tim Batista Garrocho"));
		painel2.add(painel3, BorderLayout.SOUTH);
		
		// Adiciona o conteúdo do painel 2 na segunda aba do TabbedPane.
		tabbedPane.addTab("Creditos", null, painel2, "Equipe de desenvolvimento.");

		// Adiciona o tabbedPane ao container da janela.
		add(tabbedPane);
		
	}

	@Override
	protected void configurarEventos() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void criarElementos() {
		tabbedPane = new JTabbedPane();
		painel1 = new JPanel(new GridLayout(1,2));
		painel11 = new JPanel();
		//img = Toolkit.getDefaultToolkit().getImage(Dialogo.getResource("logo.png"));  
		painel12 = new JPanel(new GridLayout(3,1));
		painel2 = new JPanel();
		painel3 = new JPanel();
	}

	@Override
	protected void customizarElementos() {
		// TODO Auto-generated method stub
		
	} 
}
