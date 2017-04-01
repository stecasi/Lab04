package it.polito.tdp.lab04.model;

import java.util.*;

import it.polito.tdp.lab04.DAO.CorsoDAO;
import it.polito.tdp.lab04.DAO.StudenteDAO;

public class Model {
	
	List <String> corsiLista = new LinkedList <String>();
	
	
	public List <String> getCodiceCorsi(){
		
		
		CorsoDAO dao = new CorsoDAO();
		corsiLista.add("");
		for (Corso  c : dao.getTuttiICorsi()){
			corsiLista.add(c.getNome());
		}
		
		return corsiLista;
		
		
	}
	
	public Studente getStudente(String matricola){
		
		StudenteDAO dao = new StudenteDAO();
		
		Studente studente = dao.completamentoStudente(matricola);
		
		return studente;
		
	}
	
	public List <Studente> getIscritti(Corso corso){
		CorsoDAO dao = new CorsoDAO();
		List <Studente> iscrittiLista = new LinkedList <Studente>();
		iscrittiLista.addAll(dao.getStudentiIscrittiAlCorso(corso));
		return iscrittiLista;
	}
	
	public Corso cercaCorso (String nomeCorso){
		CorsoDAO dao = new CorsoDAO();
		for (Corso c : dao.getTuttiICorsi()){
			if (c.getNome().equals(nomeCorso)){
				return c;
			}
		}
		
		return null;
	}
	
	public List <Corso> getCorsiSingoloStudente(String matricola){
		StudenteDAO dao = new StudenteDAO();
		List <Corso> corsiLista = new LinkedList <Corso>();
		corsiLista.addAll(dao.getCorso(matricola));
		return corsiLista;
	}

	public boolean iscrivi(Studente stud, Corso cor){
		CorsoDAO dao = new CorsoDAO();
		return dao.iscriviStudenteACorso(stud, cor);
	}
}
