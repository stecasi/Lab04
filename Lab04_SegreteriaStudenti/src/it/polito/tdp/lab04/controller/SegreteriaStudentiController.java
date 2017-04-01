package it.polito.tdp.lab04.controller;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Model;
import it.polito.tdp.lab04.model.Studente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class SegreteriaStudentiController {
	
	Model model;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> cmbCorsi;

    @FXML
    private Button btnCercaIscrittiCorso;

    @FXML
    private TextField txtMatricola;

    @FXML
    private Button btnTrova;

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtCognome;

    @FXML
    private Button btnIscrivi;

    @FXML
    private Button btnCercaCorsi;

    @FXML
    private TextArea txtResult;

    @FXML
    private Button btnClear;

    @FXML
    void doCercaCorsi(ActionEvent event) {
    	String matricola = txtMatricola.getText();
    	
    	for (Corso c : model.getCorsiSingoloStudente(matricola)){
    		txtResult.appendText(c.getCodins()+" "+c.getNome()+" "+c.getCrediti()+" crediti "+c.getPd()+" semestre\n");
    	}

    }

    @FXML
    void doCercaIscrittiCorso(ActionEvent event) {
    	String nomeCorso = cmbCorsi.getValue();
    	Corso c = model.cercaCorso(nomeCorso);
    	txtResult.clear();
    	if (c==null){
    		txtResult.appendText("Non hai selezionato alcun corso");
    	}
    	else {
    	for (Studente s : model.getIscritti(c)){
    	txtResult.appendText(s.getMatricola()+" "+s.getCognome()+" "+s.getNome()+" "+s.getCds()+"\n");
    	}
    	}
    }

    @FXML
    void doCercaNome(ActionEvent event) {
    	String matricola = txtMatricola.getText();
    	
    	Studente st = model.getStudente(matricola);
    	
    	if (st==null){
    		txtResult.appendText("Studente non trovato\n");
    	} else {
    		txtResult.appendText("Studente trovato\n");
    		txtNome.setText(st.getNome());
    		txtCognome.setText(st.getCognome());
    	}
    }

    @FXML
    void doIscrivi(ActionEvent event) {
    	String nomeCorso = cmbCorsi.getValue();
    	String matricola = txtMatricola.getText();
    	Corso c = model.cercaCorso(nomeCorso);
    	Studente s = model.getStudente(matricola);
    	
    	
    	if (c==null || s==null){
    		if (c==null){
        		txtResult.appendText("Non hai selezionato alcun corso");
        	}
        	if (s==null){
        		txtResult.appendText("Studente non presente nel database");
        	}
    	}
    	else {
    		boolean result = model.iscrivi(s, c);
    		if (result){
    		txtResult.appendText("Studente:\n "+s.getCognome()+" "+s.getNome()+" "+s.getMatricola()+" iscritto al corso " +c.getCodins()+" "+c.getNome()+"\n");
    		}
    		else {
    			txtResult.appendText("Errore");
    		}
    	}
    	
    	
    	

    }

    @FXML
    void doReset(ActionEvent event) {
    	txtMatricola.clear();
    	txtNome.clear();
    	txtCognome.clear();
    	txtResult.clear();
    }
    
    
    public void setModel(Model model) {
		this.model = model;
		//LA LISTA DELLA COMBO VA INIZIALIZZATA QUI
		cmbCorsi.getItems().addAll(model.getCodiceCorsi());
		//METODO 1
	       // for (String s : model.getCodiceCorsi()){
	        	// cmbCorsi.getItems().addAll(s);
	        //}
	}
    
    
    @FXML
    void initialize() {
        assert cmbCorsi != null : "fx:id=\"cmbCorsi\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btnCercaIscrittiCorso != null : "fx:id=\"btnCercaIscrittiCorso\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert txtMatricola != null : "fx:id=\"txtMatricola\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btnTrova != null : "fx:id=\"btnTrova\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert txtNome != null : "fx:id=\"txtNome\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert txtCognome != null : "fx:id=\"txtCognome\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btnIscrivi != null : "fx:id=\"btnIscrivi\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btnCercaCorsi != null : "fx:id=\"btnCercaCorsi\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btnClear != null : "fx:id=\"btnClear\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";

        //
        //
        //IMPORTANTE!!!
        //NON SI PUO' INIZIALIZZARE QUI LA COMBO CON LA LISTA DEI CORSI
        //LO SI DEVE FARE NEL METODO CHE PASSA IL MODEL AL CONTROLLER
        //
        //
        
        
      
        
    }

	
}
