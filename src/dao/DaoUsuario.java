package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.BeanCursoJSP;
import connection.SingleConnection;

/**
 * 
 * @author jeff_
 *
 */
public class DaoUsuario {

	private Connection connection;

	public DaoUsuario() {
		connection = SingleConnection.getConnection();
	}

	public void salvar(BeanCursoJSP usuario) {
		try {

			String sql = "insert into usuario(login, senha, nome, telefone,cep, rua, bairro, cidade, estado, ibge, fotobase64, contenttype, curriculobase64, contenttypecurriculo, fotobase64miniatura) "
					+ "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement insert = connection.prepareStatement(sql);
			insert.setString(1, usuario.getLogin());
			insert.setString(2, usuario.getSenha());
			insert.setString(3, usuario.getNome());
			insert.setString(4, usuario.getTelefone());
			insert.setString(5, usuario.getCep());
			insert.setString(6, usuario.getRua());
			insert.setString(7, usuario.getBairro());
			insert.setString(8, usuario.getCidade());
			insert.setString(9, usuario.getEstado());
			insert.setString(10, usuario.getIbge());
			insert.setString(11, usuario.getFotoBase64());
			insert.setString(12, usuario.getContentType());
			insert.setString(13, usuario.getCurriculoBase64());
			insert.setString(14, usuario.getContenttypeCurriculo());
			insert.setString(15, usuario.getFotoBase64Miniatura());
			insert.execute();
			connection.commit();

		} catch (Exception e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}

		}
	}

	public List<BeanCursoJSP> listar() throws Exception {

		List<BeanCursoJSP> listar = new ArrayList<BeanCursoJSP>();

		String sql = "select * from usuario";

		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();
		while (resultSet.next()) {
			BeanCursoJSP beanCursoJSP = new BeanCursoJSP();
			beanCursoJSP.setId(resultSet.getLong("id"));
			beanCursoJSP.setLogin(resultSet.getString("login"));
			beanCursoJSP.setSenha(resultSet.getString("senha"));
			beanCursoJSP.setNome(resultSet.getString("nome"));
			beanCursoJSP.setTelefone(resultSet.getString("telefone"));
			beanCursoJSP.setCep(resultSet.getString("cep"));
			beanCursoJSP.setRua(resultSet.getString("rua"));
			beanCursoJSP.setBairro(resultSet.getString("bairro"));
			beanCursoJSP.setCidade(resultSet.getString("cidade"));
			beanCursoJSP.setEstado(resultSet.getString("estado"));
			beanCursoJSP.setIbge(resultSet.getString("ibge"));
			//beanCursoJSP.setFotoBase64(resultSet.getString("fotobase64"));
			beanCursoJSP.setFotoBase64Miniatura(resultSet.getString("fotoBase64miniatura"));
			beanCursoJSP.setContentType(resultSet.getString("contenttype"));
			beanCursoJSP.setCurriculoBase64(resultSet.getString("curriculobase64"));
			beanCursoJSP.setContenttypeCurriculo(resultSet.getString("contenttypecurriculo"));
			listar.add(beanCursoJSP);
		}

		return listar;
	}

	public void delete(String id) {
		try {
			String sql = "delete from usuario where id = '" + id + "'";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.execute();

			connection.commit();

		} catch (Exception e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}

	public BeanCursoJSP consultar(String id) throws Exception {
		String sql = "select * from usuario where id= '" + id + "'";

		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet resultSet = preparedStatement.executeQuery();
		if (resultSet.next()) {
			BeanCursoJSP beanCursoJSP = new BeanCursoJSP();
			beanCursoJSP.setId(resultSet.getLong("id"));
			beanCursoJSP.setLogin(resultSet.getString("login"));
			beanCursoJSP.setSenha(resultSet.getString("senha"));
			beanCursoJSP.setNome(resultSet.getString("nome"));
			beanCursoJSP.setTelefone(resultSet.getString("telefone"));
			beanCursoJSP.setCep(resultSet.getString("cep"));
			beanCursoJSP.setRua(resultSet.getString("rua"));
			beanCursoJSP.setBairro(resultSet.getString("bairro"));
			beanCursoJSP.setCidade(resultSet.getString("cidade"));
			beanCursoJSP.setEstado(resultSet.getString("estado"));
			beanCursoJSP.setIbge(resultSet.getString("ibge"));
			beanCursoJSP.setFotoBase64(resultSet.getString("fotobase64"));
			beanCursoJSP.setFotoBase64(resultSet.getString("fotobase64miniatura"));
			beanCursoJSP.setContentType(resultSet.getString("contenttype"));
			beanCursoJSP.setCurriculoBase64(resultSet.getString("curriculobase64"));
			beanCursoJSP.setContenttypeCurriculo(resultSet.getString("contenttypecurriculo"));
			

			return beanCursoJSP;
		}

		return null;
	}

	public boolean validarLoginSenha(String login, String senha) throws SQLException {
		String sql = "SELECT COUNT(1) AS qtd FROM usuario WHERE login = '" + login + "' or senha = '" + senha + "'";
		try (PreparedStatement statement = connection.prepareStatement(sql)) {
			try (ResultSet result = statement.executeQuery()) {
				if (result.next()) {
					return result.getInt("qtd") <= 0;
				}
			}
		}
		return false;
	}

	public boolean validarLoginUpdate(String login, String id) throws Exception {
		String sql = "select count(1) as qtd from usuario where login= '" + login + "'and id <>" + id;
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet resultSet = preparedStatement.executeQuery();
		if (resultSet.next()) {

			return resultSet.getInt("qtd") <= 0;
		}

		return false;
	}

	public void atualizar(BeanCursoJSP usuario) {

		try {
			String sql = "update usuario set login = ?, senha  = ?, nome = ?, telefone = ?, "
					+ "cep = ?, rua = ?, bairro = ?, cidade = ?, estado = ?, ibge = ?, "
					+ "fotobase64 = ?,  contenttype = ?, curriculobase64 = ?, contenttypecurriculo = ?, fotobase64miniatura = ?  where id = " + usuario.getId();

			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, usuario.getLogin());
			preparedStatement.setString(2, usuario.getSenha());
			preparedStatement.setString(3, usuario.getNome());
			preparedStatement.setString(4, usuario.getTelefone());
			preparedStatement.setString(5, usuario.getCep());
			preparedStatement.setString(6, usuario.getRua());
			preparedStatement.setString(7, usuario.getBairro());
			preparedStatement.setString(8, usuario.getCidade());
			preparedStatement.setString(9, usuario.getEstado());
			preparedStatement.setString(10, usuario.getIbge());
			preparedStatement.setString(11, usuario.getFotoBase64());
			preparedStatement.setString(12, usuario.getContentType());
			preparedStatement.setString(13, usuario.getCurriculoBase64());
			preparedStatement.setString(14, usuario.getContenttypeCurriculo());
			preparedStatement.setString(15, usuario.getFotoBase64Miniatura());
			preparedStatement.executeUpdate();
			connection.commit();

		} catch (Exception e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}

	/*
	 * public boolean validarSenha(String senha) throws Exception { String sql =
	 * "select count(1) as qtd from usuario where senha= '" + senha + "'";
	 * PreparedStatement preparedStatement = connection.prepareStatement(sql);
	 * ResultSet resultSet = preparedStatement.executeQuery(); if (resultSet.next())
	 * {
	 * 
	 * return resultSet.getInt("qtd") <= 0; }
	 * 
	 * return false;
	 * 
	 * }
	 */
}
