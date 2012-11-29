package com.gubga.persistencia;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.gubga.classes.Usuario;

/**
 * Esta classe e responsavel pela insercao e atualizacao da tabela Banlist.
 *  
 * @author Charles Garrocho
 * @author Thiago Garrocho
 * 
 */
public class BancoDadosBanList extends BancoDados {
	private String SQL;
	private ResultSet resultado;

	/**
	 * Este e o construtor. Ele instancia um novo <code>BancoDados</code>.
	 * 
	 * @see BancoDados
	 */
	public BancoDadosBanList(String endereco) {
		super(endereco);
	}

	/**
	 * Retorna a quantidade total de Usuarios Banidos.
	 * 
	 * @return um <code>int</code> com a quantidade de usuarios banidos.
	 * @throws SQLException Dispara uma excecao SQL.
	 */
	public int qtdeTotalBan() {
		int resul = -1;
		try {
			iniciaConexao();
			SQL = "SELECT COUNT(*) AS qtde FROM Banlist";
			resultado = executaComando(SQL);
			resul = resultado.getInt("qtde");
			fechaConexao();
			return resul;
		} catch (Exception e) {
			return resul;
		}
	}

	/**
	 * Busca Todos Nomes de Usuarios Banidos.
	 * 
	 * @return um <code>ResultSet</code> com o resultado da pesquisa.
	 */
	public ArrayList<Usuario> getTodosUsuarios() {
		ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
		try {
			iniciaConexao();
			SQL = "SELECT UserId, UserName, Reason FROM Banlist";
			resultado = executaComando(SQL);
			while (resultado.next()) {
				Usuario usuario = new Usuario();
				usuario.setUserId(resultado.getInt("UserId"));
				usuario.setUserName(resultado.getString("UserName"));
				usuario.setReason(resultado.getString("Reason"));
				usuarios.add(usuario);
			}
			fechaConexao();
			return usuarios;
		} catch (Exception e) {
			return null;
		}
	}	
	
	/**
	 * Busca Um Determinado Nome de Usuario Banido.
	 * 
	 * @return um <code>ResultSet</code> com o resultado da pesquisa.
	 */
	public ArrayList<Usuario> getUsuario(String nome) {
		ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
		try {
			iniciaConexao();
			SQL = "SELECT UserId, UserName, Reason FROM Banlist WHERE Banlist.UserName LIKE \'" + nome + "\'";
			resultado = executaComando(SQL);
			while (resultado.next()) {
				Usuario usuario = new Usuario();
				usuario.setUserId(resultado.getInt("UserId"));
				usuario.setUserName(resultado.getString("UserName"));
				usuario.setReason(resultado.getString("Reason"));
				usuarios.add(usuario);
			}
			fechaConexao();
			return usuarios;
		} catch (Exception e) {
			return null;
		}
	}	

	/**
	 * Adiciona um novo usuarios com os dados passados como argumento.
	 * 
	 * @param UserId um <code>int</code> com o numero do candidato.
	 * @param UserName um <code>String</code> com o nome do cargo.
	 * @param Reason um <code>String</code> com o nome do partido.
	 */
	public Boolean addUsuario(int UserId, String UserName, String Reason) {
		try {
			iniciaConexao();
			SQL = "INSERT INTO Banlist (UserId, UserName, Reason) VALUES (" + UserId + ",'" + UserName + "','" + Reason + "')";
			executaSemRetorno(SQL);
			fechaConexao();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	/**
	 * Exclui um Usuario a partir do UserId.
	 * 
	 * @param UserId um <code>int</code> com o UserId do Usuario.
	 * @throws SQLException Dispara uma excecao SQL.
	 * @throws ClassNotFoundException Dispara uma excecao de Classe nao encontrada.
	 */
	public Boolean delUsuario(int UserId) {
		try {
			iniciaConexao();
			SQL = "DELETE FROM Banlist WHERE Banlist.UserId = " + UserId;
			executaSemRetorno(SQL);
			fechaConexao();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * Altera o candidato a partir dos argumentos fornecidos, altera o cargo mas nao altera o numero do candidato.
	 * 
	 * @param cargoAnterior um <code>String</code> com o cargo anterior do candidato.
	 * @param numeroCandidato um <code>int</code> com o numero do candidato.
	 * @param nome um <code>String</code> com o nome do cargo.
	 * @param partido um <code>String</code> com o nome do partido.
	 * @param cargo um <code>String</code> com o nome do cargo.
	 * @param caminhoFoto <code>String</code> com o endereco da foto do candidato.
	 * @throws SQLException Dispara uma excecao SQL.
	 * @throws ClassNotFoundException Dispara uma excecao de Classe nao encontrada.
	 */
	public void alterarCandidatoCargo(String cargoAnterior, int numeroCandidato, String nome, String partido, String cargo, String caminhoFoto) throws SQLException, ClassNotFoundException {
		SQL = "UPDATE candidato SET nome = '" + nome + "', partido = '" + partido + "', cargo = '" + cargo + "', caminhoFoto = '" + caminhoFoto + "' WHERE candidato.numero = " + numeroCandidato + " AND cargo = '" + cargoAnterior + "'";
		executaSemRetorno(SQL);
	}
}