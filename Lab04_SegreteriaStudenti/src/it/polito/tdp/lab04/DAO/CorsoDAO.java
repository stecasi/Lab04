package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Studente;

public class CorsoDAO {

	/*
	 * Ottengo tutti i corsi salvati nel Db
	 */
	
	public List<Corso> getTuttiICorsi() {

		final String sql = "SELECT * FROM corso";

		List<Corso> corsi = new LinkedList<Corso>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				// Crea un nuovo JAVA Bean Corso
					
					Corso ex = new Corso (
						rs.getString("codIns"),
						rs.getInt("crediti"),
						rs.getString("nome"),
						rs.getInt("pd")
						);
				// Aggiungi il nuovo Corso alla lista
					
					corsi.add(ex);
						
			}

			return corsi;
			
			
		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
	}

	/*
	 * Dato un codice insegnamento, ottengo il corso
	 */
	public void getCorso(Corso corso) {
		// TODO
	}

	

	
	/*
	 * Ottengo tutti gli studenti iscritti al Corso
	 */
	public List <Studente> getStudentiIscrittiAlCorso(Corso corso) {
		
		String codiceCorso = corso.getCodins();
		
		
		
		final String sql = "SELECT * "
				+ "FROM iscrizione i, studente s "
				+ "WHERE codins = ? AND i.matricola=s.matricola ";

		List<Studente> studentiIscritti = new LinkedList<Studente>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			st.setString(1, codiceCorso);
			
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				
				String matricolaStudenteCorso = rs.getString("i.matricola");
				String nomeStudente = rs.getString("nome");
				String cognomeStudente = rs.getString("cognome");
				String cds = rs.getString("s.cds");
				
				Studente stud = new Studente (matricolaStudenteCorso, nomeStudente, cognomeStudente,cds);
						
				
				// Aggiungi la matricola studente alla lista
					
					studentiIscritti.add(stud);
						
			}

			return studentiIscritti;
			
			
		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
	}

	/*
	 * Data una matricola ed il codice insegnamento,
	 * iscrivi lo studente al corso.
	 */
	public boolean iscriviStudenteACorso(Studente studente, Corso corso) {
		
		
		String sql = "INSERT INTO `iscritticorsi`.`iscrizione` (`matricola`, `codins`) VALUES (?, ?);";
		

		try {
			Connection conn = ConnectDB.getConnection() ;
			
			PreparedStatement st = conn.prepareStatement(sql) ;
			
			st.setString(1, studente.getMatricola()) ;
			st.setString(2, corso.getCodins()) ;
			
			
			int result = st.executeUpdate() ;
			
			conn.close();
			
			if(result==1) {
				return true ;
			} else {
				return false ;
			}
			
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return false ;
		
	}
		

}
