package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import connection.SingleConnection;

/**
 * 
 * @author jeff_
 *
 */
public class DaoLogin {
	
	private Connection connection;
	
	public DaoLogin() {
		connection = SingleConnection .getConnection();
	}
	
	public boolean validarLogin(String login, String senha)throws Exception {
		
		String sql = "select * from usuario where login = '"+login+"' and senha = '"+ senha + "'";
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();
		if(resultSet.next()) {
			return true;
		}else {
			return false;
		}
		
	}

}
