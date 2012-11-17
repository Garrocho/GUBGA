package com.gubga.gui;

import java.awt.Component;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.net.URL;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;


public abstract class Janela extends JFrame {

	private static final long serialVersionUID = 1L;

	public Janela() {
		super();
	}
	
	protected abstract void criarElementos();
	
	protected abstract void adicionarElementos();
	
	protected abstract void customizarElementos();
	
	protected abstract void configurarEventos();

	public void definirPropriedades(Component janelaPai, String titulo) {
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent arg0) {
				System.exit(0);
			}
		});
		setTitle(titulo);
		pack();
		setIconImage(Toolkit.getDefaultToolkit().getImage(getResource("garena_logo.png")));
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
	}
	
	/** Encontra o recurso no diretorio de recursos do jar
	 * @param enderecoArquivo <code>String</code> caminho do recurso
	 * @return <code>URL</code> com o endereco do recurso
	 */
	public static URL getResource(String enderecoArquivo){
		return Janela.class.getResource("/icones/" + enderecoArquivo);
	}
	
	/** Janela para abrir arquivos
	 * @param componentePai <code>Component</code> sobre o qual esta janela será aberta, a qual esta sera "filha"
	 * @param titulo <code>String</code> com o titulo da janela
	 * @param diretorioCorrente <code>String</code> com o diretorio onde a janela de abrir inicializara, pode ser <code>null</code>
	 * @param opcaoTodosArquivos <code>boolean</code> informando se a opcao(filtro) Todos Arquivos será mostrada ou não
	 * @param nomeFiltro <code>String</code> com o nome do filtro de extensões
	 * @param extensao <code>String...</code> com as extensões usadas no filtro
	 * @return <code>String</code> com o endereço do arquivo selecionado, caso nenhum arquivo seja selecionado, é retornado <code>null</code>
	 * @see  JFileChooser
	 */
	public static String janelaAbrirArquivo(Component componentePai, String titulo, String diretorioCorrente, boolean opcaoTodosArquivos, String nomeFiltro, String... extensao){
		JFileChooser janelaAbrir = new JFileChooser(titulo);

		janelaAbrir.setAcceptAllFileFilterUsed(false);
		janelaAbrir.setDialogType(JFileChooser.OPEN_DIALOG);
		janelaAbrir.setFileSelectionMode(JFileChooser.FILES_ONLY);
		janelaAbrir.setDialogTitle(titulo);
		janelaAbrir.setFileFilter(new FileNameExtensionFilter(nomeFiltro, extensao));
		janelaAbrir.setAcceptAllFileFilterUsed(opcaoTodosArquivos);

		if(diretorioCorrente != null){
			if (diretorioCorrente.isEmpty()){
				janelaAbrir.setCurrentDirectory(new File("."));
			}
			else{
				janelaAbrir.setCurrentDirectory(new File(diretorioCorrente));
			}
		}

		janelaAbrir.showOpenDialog(componentePai);

		return janelaAbrir.getSelectedFile() != null ? janelaAbrir.getSelectedFile().getPath() : null;
	}

	/** Janela para selecionar um diretorio
	 * @param componentePai <code>Component</code> sobre o qual esta janela será aberta, a qual esta sera "filha"
	 * @param titulo <code>String</code> com o titulo da janela
	 * @param diretorioCorrente <code>String</code> com o diretorio onde a janela de abrir inicializara
	 * @return <code>String</code> com o endereço do diretorio selecionado, caso nenhum diretorio seja selecionado, é retornado <code>null</code>
	 * @see  JFileChooser 
	 */
	public static String janelaAbrirDiretorio(Component componentePai, String titulo, String diretorioCorrente){
		JFileChooser janelaAbrir = new JFileChooser(titulo);

		janelaAbrir.setAcceptAllFileFilterUsed(false);
		janelaAbrir.setDialogType(JFileChooser.OPEN_DIALOG);
		janelaAbrir.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		janelaAbrir.setDialogTitle(titulo);

		if(diretorioCorrente != null){
			if (diretorioCorrente.isEmpty()){
				janelaAbrir.setCurrentDirectory(new File("."));
			}
			else{
				janelaAbrir.setCurrentDirectory(new File(diretorioCorrente));
			}
		}

		janelaAbrir.showOpenDialog(componentePai);

		return janelaAbrir.getSelectedFile() != null ? janelaAbrir.getSelectedFile().getPath() : null;
	}

	/** Janela para salvar um arquivo
	 * @param componentePai <code>Component</code> sobre o qual esta janela será aberta, a qual esta sera "filha"
	 * @param titulo <code>String</code> com o titulo da janela
	 * @param diretorioCorrente <code>String</code> com o diretorio onde a janela de abrir inicializara
	 * @param opcaoTodosArquivos <code>boolean</code> informando se a opcao(filtro) Todos Arquivos será mostrada ou não
	 * @param nomeFiltro <code>String</code> com o nome do filtro de extensões
	 * @param extensao <code>String...</code> com as extensões usadas no filtro
	 * @return <code>String</code> com o endereço onde o arquivo será salvo, caso nenhum arquivo seja selecionado, é retornado <code>null</code>
	 * @see  JFileChooser 
	 */
	public static String janelaSalvarArquivo(Component componentePai, String titulo, String diretorioCorrente, boolean opcaoTodosArquivos, String nomeFiltro, String... extensao){
		JFileChooser janelaSalvar = new JFileChooser(titulo);

		janelaSalvar.setAcceptAllFileFilterUsed(false);
		janelaSalvar.setDialogType(JFileChooser.SAVE_DIALOG);
		janelaSalvar.setFileSelectionMode(JFileChooser.FILES_ONLY);
		janelaSalvar.setDialogTitle(titulo);
		janelaSalvar.setFileFilter(new FileNameExtensionFilter(nomeFiltro, extensao));
		janelaSalvar.setAcceptAllFileFilterUsed(opcaoTodosArquivos);

		if(diretorioCorrente != null){
			if (diretorioCorrente.isEmpty()){
				janelaSalvar.setCurrentDirectory(new File("."));
			}
			else{
				janelaSalvar.setCurrentDirectory(new File(diretorioCorrente));
			}
		}

		janelaSalvar.showSaveDialog(componentePai);

		return janelaSalvar.getSelectedFile() != null ? janelaSalvar.getSelectedFile().getPath() : null;
	}
}