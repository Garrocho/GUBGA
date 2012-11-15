package com.gubga.app;

import java.sql.SQLException;
import java.util.ArrayList;

import com.gubga.classes.Usuario;
import com.gubga.persistencia.BancoDadosBanList;

/**
 * @author Charles Garrocho
 * @author Thiago Garrocho
 *
 */
public class Init {

	static String path = "C:/Program Files/Garena Plus/Room/user/13569386/ban.dat";

	public static void main(String[] args) throws SQLException {
		BancoDadosBanList dbBanList = new BancoDadosBanList(path);
		System.out.println("[DATABASE BAN LIST] Verificando o Banco de Dados.");
		if (dbBanList.verificaBancoDados()) {
			System.out.println("[DATABASE BAN LIST] O Banco de Dados Existe.");
		}
		else {
			System.out.println("[DATABASE BAN LIST] O Banco de Dados N�o Existe.");
		}
		System.out.println("[DATABASE BAN LIST] Calculando o Tamanho do Banco.");
		System.out.println("[DATABASE BAN LIST] Quatidade de Usuarios: " + dbBanList.qtdeTotalBan());
		//dbBanList.addUsuario(6666777, "GUSTAVO", "MAPA HACK.");
		ArrayList<Usuario> usuarios = dbBanList.getTodosUsuarios();
		for (Usuario user : usuarios) {
			System.out.println(user.toString());
		}
	}
}
