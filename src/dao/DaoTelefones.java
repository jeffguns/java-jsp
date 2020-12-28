package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.BeanTelefonesJSP;
import connection.SingleConnection;

/**
 * 
 * @author jeff_
 *
 */
public class DaoTelefones {

	private Connection connection;

	public DaoTelefones() {
		connection = SingleConnection.getConnection();
	}

	public void salvar(BeanTelefonesJSP telefones) {
		try {

			String sql = "insert into telefones(numero, tipo, usuario) values (?, ?, ?)";
			PreparedStatement insert = connection.prepareStatement(sql);
			insert.setString(1, telefones.getNumero());
			insert.setString(2, telefones.getTipo());
			insert.setLong(3, telefones.getUsuario());
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

	public List<BeanTelefonesJSP> listar(Long user) throws Exception {

		List<BeanTelefonesJSP> listar = new ArrayList<BeanTelefonesJSP>();

		String sql = "select * from telefones where usuario = " + user;

		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();
		while (resultSet.next()) {

			BeanTelefonesJSP beanTelefonesJSP = new BeanTelefonesJSP();
			beanTelefonesJSP.setId(resultSet.getLong("id"));
			beanTelefonesJSP.setNumero(resultSet.getString("numero"));
			beanTelefonesJSP.setTipo(resultSet.getString("tipo"));
			beanTelefonesJSP.setUsuario(resultSet.getLong("usuario"));
			listar.add(beanTelefonesJSP);
		}

		return listar;
	}

	public void delete(String id) {
		try {
			String sql = "delete from telefones where id = '" + id + "'";
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

}
