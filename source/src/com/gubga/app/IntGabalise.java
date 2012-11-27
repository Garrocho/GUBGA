package com.gubga.app;

import java.text.ParseException;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.gubga.gui.principal.JanelaPrincipal;

import de.javasoft.plaf.synthetica.SyntheticaBlackEyeLookAndFeel;


public class IntGabalise {

	//public static void main(String args[]) throws IOException {
		//jpcap.NetworkInterface[] devices = JpcapCaptor.getDeviceList();
		//JpcapCaptor jpcap = JpcapCaptor.openDevice(devices[0], 2000, true, 20);
		//jpcap.loopPacket(-1, new RecebimentoPacote());
	//}
	
	public static void main(String args[]) throws UnsupportedLookAndFeelException, ParseException {
		UIManager.put("Synthetica.window.decoration", Boolean.FALSE);
		UIManager.setLookAndFeel(new SyntheticaBlackEyeLookAndFeel());
		//new JanelaCarregamento(null);
		//new JanelaPrincipal(null, "10661343");
	}
}