package com.gubga.app;

import java.text.ParseException;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.gubga.gui.banlist.JanelaBanList;

import de.javasoft.plaf.synthetica.SyntheticaBlackEyeLookAndFeel;


public class initGubga {

	//public static void main(String args[]) throws IOException {
		//jpcap.NetworkInterface[] devices = JpcapCaptor.getDeviceList();
		//JpcapCaptor jpcap = JpcapCaptor.openDevice(devices[0], 2000, true, 20);
		//jpcap.loopPacket(-1, new RecebimentoPacote());
	//}
	
	public static void main(String args[]) throws UnsupportedLookAndFeelException, ParseException {
		UIManager.setLookAndFeel(new SyntheticaBlackEyeLookAndFeel());
		new JanelaBanList(null, null);
	}
}