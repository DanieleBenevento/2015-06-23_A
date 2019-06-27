package it.polito.tdp.music;

import java.net.URL;
import java.util.Collections;
import java.util.ResourceBundle;

import it.polito.tdp.music.model.AutoreAscolti;
import it.polito.tdp.music.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;

public class MusicController {

	Model model=new Model();
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> boxMese;

    @FXML
    private Button btnArtisti;

    @FXML
    private Button btnNazioni;

    @FXML
    private TextArea txtResult;
    

    @FXML
    void doArtisti(ActionEvent event) {

    	String mese= this.boxMese.getValue().toString();
    	int valore=0;
    	switch(mese) {
    	case "Gennaio":
    		valore=1;
    		break;
    	case "Febbraio":
    		valore=2;
    		break;
    	case "Marzo":
    		valore=3;
    		break;
    	case "Aprile":
    		valore=4;
    		break;
    	case "Maggio":
    		valore=5;
    		break;
    	case "Giugno":
    		valore=6;
    		break;
    	case "Luglio":
    		valore=7;
    		break;
    	case "Agosto":
    		valore=8;
    		break;
    	case "Settembre":
    		valore=9;
    		break;
    	case "Ottobre":
    		valore=10;
    		break;
    	case "Novembre":
    		valore=11;
    		break;
    	case "Dicembre":
    		valore=12;
    		break;
    	}
    	for(AutoreAscolti a:model.elencoAscolti(valore)) {
    	txtResult.appendText(""+a.getArtista()+" "+a.getAscolti());
    	}
    }

    @FXML
    void doDistanza(ActionEvent event) {
    	try {
    		String mese= this.boxMese.getValue().toString();
        	int valore=0;
        	switch(mese) {
        	case "Gennaio":
        		valore=1;
        		break;
        	case "Febbraio":
        		valore=2;
        		break;
        	case "Marzo":
        		valore=3;
        		break;
        	case "Aprile":
        		valore=4;
        		break;
        	case "Maggio":
        		valore=5;
        		break;
        	case "Giugno":
        		valore=6;
        		break;
        	case "Luglio":
        		valore=7;
        		break;
        	case "Agosto":
        		valore=8;
        		break;
        	case "Settembre":
        		valore=9;
        		break;
        	case "Ottobre":
        		valore=10;
        		break;
        	case "Novembre":
        		valore=11;
        		break;
        	case "Dicembre":
        		valore=12;
        		break;
        	}
    	model.creaGrafo(valore);
    	txtResult.appendText("La max distanza è: "+model.getBest());
    	}
    	catch(NumberFormatException nfe) {
    		txtResult.appendText("Devi Inserire un numero\n");
    	}
    }

    @FXML
    void initialize() {
        assert boxMese != null : "fx:id=\"boxMese\" was not injected: check your FXML file 'MusicA.fxml'.";
        assert btnArtisti != null : "fx:id=\"btnArtisti\" was not injected: check your FXML file 'MusicA.fxml'.";
        assert btnNazioni != null : "fx:id=\"btnNazioni\" was not injected: check your FXML file 'MusicA.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'MusicA.fxml'.";

    }
    
    public void setModel(Model model) {
    	this.model=model;
    	this.boxMese.getItems().add("Gennaio");
    	this.boxMese.getItems().add("Febbraio");
    	this.boxMese.getItems().add("Marzo");
    	this.boxMese.getItems().add("Aprile");
    	this.boxMese.getItems().add("Maggio");
    	this.boxMese.getItems().add("Giugno");
    	this.boxMese.getItems().add("Luglio");
    	this.boxMese.getItems().add("Agosto");
    	this.boxMese.getItems().add("Settembre");
    	this.boxMese.getItems().add("Ottobre");
    	this.boxMese.getItems().add("Novembre");
    	this.boxMese.getItems().add("Dicembre");
    }
}
