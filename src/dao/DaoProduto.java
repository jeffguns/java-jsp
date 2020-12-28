package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.BeanProdutoJSP;
import connection.SingleConnection;

/**
 * 
 * @author jeff_
 *
 */
public class DaoProduto {

	private Connection connection;

	public DaoProduto() {
		connection = SingleConnection.getConnection();
	}

	public void salvar(BeanProdutoJSP produto) {
		try {

			String sql = "insert into produto(nome, quantidade, valor) values (?, ?, ?)";
			PreparedStatement insert = connection.prepareStatement(sql);
			insert.setString(1, produto.getNome());
			insert.setDouble(2, produto.getQuantidade());
			insert.setDouble(3, produto.getValor());
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

	public List<BeanProdutoJSP> listar() throws Exception {

		List<BeanProdutoJSP> listar = new ArrayList<BeanProdutoJSP>();

		String sql = "select * from produto";

		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();
		while (resultSet.next()) {
			BeanProdutoJSP beanProdutoJSP = new BeanProdutoJSP();
			beanProdutoJSP.setId(resultSet.getLong("id"));
			beanProdutoJSP.setNome(resultSet.getString("nome"));
			beanProdutoJSP.setQuantidade(resultSet.getDouble("quantidade"));
			beanProdutoJSP.setValor(resultSet.getDouble("valor"));

			listar.add(beanProdutoJSP);
		}

		return listar;
	}

	public void delete(String id) {
		try {
			String sql = "delete from produto where id = '" + id + "'";
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

	public BeanProdutoJSP consultar(String id) throws Exception {

		String sql = "select * from produto where id= '" + id + "'";

		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet resultSet = preparedStatement.executeQuery();
		if (resultSet.next()) {
			BeanProdutoJSP beanProdutoJSP = new BeanProdutoJSP();
			beanProdutoJSP.setId(resultSet.getLong("id"));
			beanProdutoJSP.setNome(resultSet.getString("nome"));
			beanProdutoJSP.setQuantidade(resultSet.getDouble("quantidade"));
			beanProdutoJSP.setValor(resultSet.getDouble("valor"));

			return beanProdutoJSP;
		}

		return null;
	}

	public boolean validarNome(String nome) throws Exception {
		String sql = "select count(1) as qtd from produto where nome= '" + nome + "'";

		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet resultSet = preparedStatement.executeQuery();
		if (resultSet.next()) {

			return resultSet.getInt("qtd") <= 0;
		}

		return false;
	}
	
	public boolean validarNomeUpdate(String nome, String id) throws Exception {
		String sql = "select count(1) as qtd from usuario where nome= '" + nome + "'and id <>" + id;
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet resultSet = preparedStatement.executeQuery();
		if (resultSet.next()) {

			return resultSet.getInt("qtd") <= 0;
		}

		return false;
	}

	public void atualizar(BeanProdutoJSP produto) {

		try {

			String sql = "update produto set nome= ?, quantidade= ?, valor= ?  where id = " + produto.getId();

			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, produto.getNome());
			preparedStatement.setDouble(2, produto.getQuantidade());
			preparedStatement.setDouble(3, produto.getValor());
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
}
