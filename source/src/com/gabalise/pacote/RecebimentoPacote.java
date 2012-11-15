package com.gabalise.pacote;

import static com.gabalise.classes.Descriptografar.byteArrayToInt;
import static com.gabalise.classes.Descriptografar.strFromBytes;
import static com.gabalise.classes.Descriptografar.strFromBytes16;
import static com.gabalise.classes.Descriptografar.unsignedByte;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import jpcap.JpcapCaptor;
import jpcap.PacketReceiver;
import jpcap.packet.IPPacket;
import jpcap.packet.Packet;
import jpcap.packet.TCPPacket;
import jpcap.packet.UDPPacket;

import com.gabalise.classes.Constantes;


public class RecebimentoPacote implements PacketReceiver {

	static int i = 0;
	String protocoll[] = {"HOPOPT", "ICMP", "IGMP", "GGP", "IPV4", "ST", "TCP", "CBT", "EGP", "IGP", "BBN", "NV2", "PUP", "ARGUS", "EMCON", "XNET", "CHAOS", "UDP", "mux"};

	public void receivePacket(Packet packet) {
		IPPacket tpt=(IPPacket)packet;
		if (packet != null) {

			int ppp = tpt.protocol;
			String proto = protocoll[ppp];

			if (proto.equals(("TCP"))) {
				TCPPacket tp = (TCPPacket) packet;
				try {
					System.out.println("Dados: " + new String(tp.data, "UTF-8"));
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				if (tp.src_port == 8687) {
					byte[] packetType = new byte[1];

					ByteBuffer buf = ByteBuffer.allocate(4096);
					buf.order(ByteOrder.LITTLE_ENDIAN);
					buf.put(tp.data);
					buf.position(4);
					buf.get(packetType);
					int tipo = byteArrayToInt(packetType);

					if (tipo == Constantes.ROOM_MENSAGEM_SALA) {
						byte[] packetMensagem = new byte[tp.data.length];
						int RoomId = buf.getInt(5);
						int UserId = buf.getInt(9);
						int Unknow = buf.getInt(12);
						buf.position(17);
						buf.get(packetMensagem);
						System.out.println("MENSAGEM CHAT GARENA");
						System.out.println("ROOM ID: " + RoomId);
						System.out.println("USER ID: " + UserId);
						System.out.println("UNKNOW: " + Unknow);
						System.out.println("MENSAGEM: " + strFromBytes16(packetMensagem));
						System.out.println("******************************************************");
					}
					else if (tipo == Constantes.ROOM_ENTROU_SALA){
						System.out.println("USUÁRIO ENTROU NA SALA");
						System.out.println("USER ID: " + buf.getInt(5));
						byte[] username = new byte[15];
						byte[] pais = new byte[2];
						buf.position(9);
						buf.get(username);
						buf.position(25);
						buf.get(pais);
						System.out.println("USERNAME: " + strFromBytes(username));
						System.out.println("PAIS: " + strFromBytes(pais));
						System.out.println("LEVEL: " + unsignedByte(buf.get(30)));
						System.out.println("******************************************************");
					}
					else if (tipo == Constantes.ROOM_SAIU_SALA) {
						System.out.println("USUÁRIO SAIU DA SALA");
						System.out.println("USER ID: " + buf.getInt(5));
						System.out.println("******************************************************");
					}
				}
			}
			else if (proto.equals(("UDP"))) {
				UDPPacket pac = (UDPPacket)packet;
				if (pac.src_port == 6112) {
					System.out.println("this is udp packet \n");
					System.out.println("this is source port :"+pac.src_port);
					System.out.println("this is destination port :"+pac.dst_port);
				}
			}
		}
	}

	public static void main(String args[]) throws IOException {
		jpcap.NetworkInterface[] devices = JpcapCaptor.getDeviceList();
		JpcapCaptor jpcap = JpcapCaptor.openDevice(devices[0], 2000, true, 20);
		jpcap.loopPacket(-1, new RecebimentoPacote());
	}
}