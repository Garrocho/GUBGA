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
			e.printStackTrace();
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
			e.printStackTrace();
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
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Busca candidatos a partir do nome e cargo fornecidos.
	 * 
	 * @param nome um <code>String</code> com o nome do candidato.
	 * @param cargo um <code>String</code> com o nome do cargo.
	 * @return um <code>ResultSet</code> com o resultado da pesquisa.
	 * @throws SQLException Dispara uma excecao SQL.
	 * @throws ClassNotFoundException Dispara uma excecao de Classe nao encontrada.
	 */
	public ResultSet obterCandidatoPorCargo(String nome, String cargo) throws ClassNotFoundException, SQLException {
		SQL = "SELECT numero, nome, partido, cargo FROM candidato WHERE candidato.nome LIKE \'" + nome + "\' AND candidato.nome IN (SELECT candidato.nome FROM candidato WHERE candidato.cargo LIKE \'" + cargo + "\')";
		resultado = executaComando(SQL);
		return resultado;
	}

	/**
	 * Busca candidatos a partir do cargo fornecido.
	 * 
	 * @param cargo um <code>String</code> com o nome do cargo.
	 * @return um <code>ResultSet</code> com o resultado da pesquisa.
	 * @throws SQLException Dispara uma excecao SQL.
	 * @throws ClassNotFoundException Dispara uma excecao de Classe nao encontrada.
	 */
	public ResultSet obterCandidatoPorCargo(String cargo) throws ClassNotFoundException, SQLException {
		SQL = "SELECT numero, nome, partido, cargo FROM candidato WHERE candidato.cargo LIKE \'" + cargo + "\'";
		resultado = executaComando(SQL);
		return resultado;
	}

	/**
	 * Busca candidatos a partir do numero e cargo fornecidos.
	 * 
	 * @param numero um <code>int</code> com o numero do candidato.
	 * @param cargo um <code>String</code> com o nome do cargo.
	 * @return um <code>ResultSet</code> com o resultado da pesquisa.
	 * @throws SQLException Dispara uma excecao SQL.
	 * @throws ClassNotFoundException Dispara uma excecao de Classe nao encontrada.
	 */
	public ResultSet obterCandidatoPorCargo(int numero, String cargo) throws ClassNotFoundException, SQLException {
		SQL = "SELECT numero, nome, partido, cargo, caminhoFoto FROM candidato WHERE candidato.numero = " + numero + " AND candidato.cargo = \'" + cargo + "\'";
		resultado = executaComando(SQL);
		return resultado;
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

	/**
	 * Exclui um candidato a partir do numero e nome passados como argumento.
	 * 
	 * @param numero um <code>int</code> com o numero do candidato.
	 * @param nome um <code>String</code> com o nome do cargo.
	 * @throws SQLException Dispara uma excecao SQL.
	 * @throws ClassNotFoundException Dispara uma excecao de Classe nao encontrada.
	 */
	public void excluirCandidato(int numero, String nome) throws SQLException, ClassNotFoundException {
		SQL = "DELETE FROM candidato WHERE candidato.numero = " + numero + " AND candidato.nome = '" + nome + "'";
		executaSemRetorno(SQL);
	}

}