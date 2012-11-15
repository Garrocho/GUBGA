package com.gabalise.app;

import java.io.IOException;

import jpcap.JpcapCaptor;

import com.gabalise.pacote.RecebimentoPacote;


public class IntGabalise {

	public static void main(String args[]) throws IOException {
		jpcap.NetworkInterface[] devices = JpcapCaptor.getDeviceList();
		JpcapCaptor jpcap = JpcapCaptor.openDevice(devices[0], 2000, true, 20);
		jpcap.loopPacket(-1, new RecebimentoPacote());
	}
}