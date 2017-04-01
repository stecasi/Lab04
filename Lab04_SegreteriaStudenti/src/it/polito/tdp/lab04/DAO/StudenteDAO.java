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
	
	/*
	 * Data uno studente, ottengo i corsi a cui è iscritto
	 */
	public List<Corso> getCorso(String matricola) {
		
		final String sql = "SELECT c.codins, c.nome, c.crediti, c.pd "
				+ "FROM iscrizione i, corso c "
				+ "WHERE i.matricola = ? AND i.codins=c.codins ";
		
		List <Corso> corsiStudente = new LinkedList <Corso>();
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			st.setString(1, matricola);
			
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				
				String codins = rs.getString("c.codins");
				String nomeCorso = rs.getString("c.nome");
				int creditiCorso = rs.getInt("c.crediti");
				int pd = rs.getInt("c.pd");
				
				Corso cor = new Corso (codins, creditiCorso, nomeCorso,pd);
						
				
				// Aggiungi la matricola studente alla lista
					
					corsiStudente.add(cor);
						
			}

			return corsiStudente;
			
		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
	}
		
		
	}


