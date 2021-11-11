package org.exposeproject.dao.impelments;

import java.sql.Connection;
import java.sql.PreparedStatement;

import org.exposeproject.dao.config.DAOFactory;
import org.exposeproject.dao.interfaces.UserInterface;
import org.exposeproject.models.User;

public class UserImplement implements UserInterface{

	private DAOFactory daoFactory;
	
	public UserImplement(DAOFactory daoFactory) {
		this.daoFactory = daoFactory;
	}
	
	@Override
	public User getUserById(Long id) {
		Connection conn = daoFactory.getConnection();
	    PreparedStatement ps ;
	    int idProf =0;
	    try {
	        ps = conn.prepareStatement("select id_professeur from professeur where nom_professeur=? and prenom_professeur=?");
	        ps.setString(1, nom);
	        ps.setString(2, prenom);
	        ResultSet rs = ps.executeQuery();
	        while(rs.next()) {
	            idProf = rs.getInt("id_professeur");
	        }
	        ps.close();
	        conn.close();
	    }catch (Exception e) {
	            // TODO: handle exception
	            e.printStackTrace();
	        }
	    return idProf ;
	}
	
	@Override
	public User getUserById(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User editUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteUser(User e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public User addUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}

}
