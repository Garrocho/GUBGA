package com.gubga.gui;

import javax.swing.UIManager;

/** Classe para manipular os temas(look and feel) do sistema.
 * @author Charles Garrocho
 * 
 * @see UIManager
 */
public class Temas {

	/** <code>String</code> com o nome do tema */
	public static final String METAL = "Metal" ;
	/** <code>String</code> com o nome do tema */
	public static final String NIMBUS = "Nimbus";
	/** <code>String</code> com o nome do tema */
	public static final String NIMBUS_DARK = "NimbusDark";
	/** <code>String</code> com o nome do tema */
	public static final String CDE_MOTIF = "CDE/Motif";
	/** <code>String</code> com o nome do tema */
	public static final String WINDOWS = "Windows";
	/** <code>String</code> com o nome do tema */
	public static final String WINDOWS_CLASSIC = "Windows Classic";
	/** <code>String</code> com o nome do tema */
	public static final String GTK = "GTK+";

	/** Muda o tema para o padrão do sistema. Caso não consiga usar o tema padrão do sistema, usa o tema Nimbus<br>
	 * Ex: se o sistema é windows, usará o tema Windows, se o sistema é linux rodando GTK, o tema será GTK+
	 * 
	 */
	public static void mudaTema(){ //Padrao é o do sistema
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (Exception e) {
			try{
				UIManager.setLookAndFeel(Temas.NIMBUS);
			}
			catch(Exception ee){
				//nao muda
			}
		}
	};
}

