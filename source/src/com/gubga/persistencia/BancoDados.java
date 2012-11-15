package com.gubga.persistencia;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Esta classe e responsavel pela insercao e atualizacao do banco de dados.
 *  
 * @author Charles Garrocho
 * @author Thiago Garrocho
 */
public class BancoDados {
	private Connection conexao;
	private Statement afirmacao;
	private final File DATABASE;

	/**
	 * Este e o construtor.
	 */
	public BancoDados(String endereco) {
		super();
		DATABASE = new File(endereco);
	}

	/**
	 * Executa uma atualizacao. Nada e retornado para o usuario.
	 * 
	 * @param SQL um <code>String</code> com a query.
	 * @throws SQLException Dispara uma excecao SQL.
	 */
	public void executaSemRetorno(String SQL) throws SQLException {
		afirmacao.executeUpdate(SQL);
	}

	/**
	 * Executa uma query. E retornado um <code>ResultSet</code>.
	 * 
	 * @param SQL um <code>String</code> com a query.
	 * @return um <code>ResultSet</code> 	/**
	 * 
	 * @throws SQLException 
	 * @throws SQLException Dispara uma excecao SQL.
	 */
	public ResultSet executaComando(String SQL) throws SQLException {
		return afirmacao.executeQuery(SQL);
	}

	/**
	 * Inicia uma conexao com o banco de dados.
	 * 
	 * @throws ClassNotFoundException Dispara uma excecao Classe Nao Encontrada.
	 * @throws SQLException Dispara uma excecao SQL.
	 */
	public void iniciaConexao() throws ClassNotFoundException, SQLException {

		Class.forName("org.sqlite.JDBC");
		conexao = DriverManager.getConnection("jdbc:sqlite:" + DATABASE.getPath());

		// Utilizamos o metodo createStatement de conexao para criar o Statement.
		afirmacao = conexao.createStatement();
	}

	/**
	 * Fecha uma conexao com o banco de dados.
	 * 
	 * @throws SQLException Dispara uma excecao SQL.
	 */
	public void fechaConexao() throws SQLException {
		conexao.close();
	}

	/**
	 * Verifica se o banco de dados existe. Caso nao exista, o programa roda o metodo <code>criaNovoBancoDados</code>
	 * para criar um arquivo de banco de dados.
	 * 
	 * @see BancoDados#criaNovoBancoDados()
	 */
	public Boolean verificaBancoDados() {
		return DATABASE.exists();
	}
}