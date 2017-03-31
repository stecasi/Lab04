package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Studente;

public class StudenteDAO {
	
	private String nome;
	private String cognome;
	private String matricola;
	
	private Studente result = null;
	
	public StudenteDAO(){
		
	}

	public Studente completamentoStudente(String matricola){
		
		final String sql = "SELECT * "
				+ "FROM studente "
				+ "WHERE matricola=?";
		
		try {
			
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			st.setString(1, matricola);
			ResultSet res = st.executeQuery();
			
			
			
			if (res.next()){
				
				Studente studente = new Studente (
						res.getString("matricola"),
						res.getString("nome"),
						res.getString("cognome"),
						res.getString("CDS")
						);
				
				
				result = studente;
				
				return result;
				
				
				
			} else {
				
				 result=null;
			}
			
			conn.close();
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return result;
		
	}

}
