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
	public List <String> getStudentiIscrittiAlCorso(Corso corso) {
		
		String codiceCorso = corso.getCodins();
		
		final String sql = "SELECT matricola "
				+ "FROM iscrizione "
				+ "WHERE codins = ? ";

		List<String> studenti = new LinkedList<String>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			st.setString(1, codiceCorso);
			
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				
				String matricolaStudenteCorso = rs.getString("matricola");
						
				
				// Aggiungi la matricola studente alla lista
					
					studenti.add(matricolaStudenteCorso+"\n");
						
			}

			return studenti;
			
			
		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
	}

	/*
	 * Data una matricola ed il codice insegnamento,
	 * iscrivi lo studente al corso.
	 */
	public boolean inscriviStudenteACorso(Studente studente, Corso corso) {
		// TODO
		return false;
	}
}
