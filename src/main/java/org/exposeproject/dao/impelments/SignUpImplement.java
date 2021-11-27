package org.exposeproject.dao.impelments;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.exposeproject.dao.config.DAOFactory;
import org.exposeproject.models.User;

public class SignUpImplement {
	private DAOFactory daoFactory;

	public void addUser(User u) {
		Connection conn = null;
		try {
			conn = daoFactory.getConnection();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement("insert into uzer values(?,?,?,?,?,?,?)");
			ps.setString(1, u.getUsername());
			ps.setString(2, u.getPassword());
			ps.setString(3, u.getBin());
			ps.setInt(4, u.getCvv());
			ps.setString(5, u.getDate());
			ps.setString(6, u.getNomBanque());
			ps.setString(7, u.getImage());
			ps.close();
			conn.close();
		}catch(Exception e ) {
			System.out.println(e.getMessage());
		}
	}
}
