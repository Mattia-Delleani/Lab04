package it.polito.tdp.lab04;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.lab04.model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import javafx.scene.control.TextArea;


public class FXMLController {
	
	private Model model;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<Corso> comboBox;

    @FXML
    private Button btnSearchSub;
    
    @FXML
    private TextField txtID;


    @FXML
    private Button btnStudent;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtSurname;

    @FXML
    private Button btnSearchCourses;
    
    @FXML
    private TextArea txtResult;
    

    @FXML
    private Button btnSubscribe;

    @FXML
    private Button btnReset;

    @FXML
    void doChoice(ActionEvent event) {

    }

    @FXML
    void doReset(ActionEvent event) {
    	btnReset.setDisable(true);
    	txtName.clear();
    	txtSurname.clear();
    	txtResult.clear();
    	txtID.clear();
    	//risistemo la ComboBox
    	comboBox.getItems().clear();
    	comboBox.getSelectionModel().select(new Corso(null,-1,"(Selezionare un corso)",-1));
    	comboBox.getItems().addAll(this.model.getCoursesList());

    }

    @FXML
    void doSearchCourses(ActionEvent event) {
    	btnReset.setDisable(false);
    	txtName.clear();
    	txtSurname.clear();
    	//Prendo lo studente
    	int matricola = 0;
    	boolean correct = true;
    	try {
    	 matricola = Integer.parseInt(txtID.getText());
   
    	}catch(NumberFormatException nfe) {
    		correct = false;
    		txtResult.setText("Eccezzione! Bisogna inserire un valore eclusivamente numerico");
    	}
    	Studente s = model.getStudentByID(matricola);
    	
    	if(comboBox.getValue().getCodIns()==null) {
    		if(s.getCognome()!=null && s.getNome()!=null && s.getCDS()!=null) {
    			List<Corso> corsi = this.model.getCoursesByStudent(s);
    			for(Corso c: corsi) {
    				txtResult.appendText(String.format("%-8s %-40s %-2d %d\n",c.getCodIns(),c.getNome(),c.getNumeroCrediti(),c.getPeriodoDidattico()));
    			}
    		}else if (correct) {
    			txtResult.setText(String.format("La matricola %d è inesistente!!", matricola));
    		}
    	
    	}else if (comboBox.getValue().getCodIns()!=null) {
    		if(s.getCognome()!=null && s.getNome()!=null && s.getCDS()!=null) {
    			List<Corso> corsi = this.model.getCoursesByStudent(s);
    			boolean iscritto = false;
    			for(Corso c: corsi) {
    				if(c.equals(comboBox.getValue())){
    					txtResult.setText(String.format("Lo studente con matricola %d e' iscritto al corso selezionato (%s)",s.getMatricola(), c.getNome()));
    					iscritto = true;
    				}
    			}
    			if(!iscritto) {
    				txtResult.setText(String.format("Lo studente con matricola %d NON e' iscritto al corso selezionato (%s)", s.getMatricola(), comboBox.getValue().getNome()));
    			}
    		}else if (correct) {
    			txtResult.setText(String.format("La matricola %d è inesistente!!", matricola));
    		}
    		
    	}
    }

    @FXML
    void doSearchStudent(ActionEvent event) {
    	btnReset.setDisable(false);
    	txtName.clear();
    	txtSurname.clear();
    	//Prendo lo studente avete la matricola inserita dall'utente
    	int matricola = 0;
    	boolean correct = true;
    	try {
    	 matricola = Integer.parseInt(txtID.getText());
    	}catch(NumberFormatException nfe) {
    		correct = false;
    		txtResult.setText("Eccezzione! Bisogna inserire un valore eclusivamente numerico");
    	}
    	
    	Studente s = model.getStudentByID(matricola);
    	if(s.getCognome()!=null && s.getNome()!=null && s.getCDS()!=null) {
    		txtName.setText(s.getNome());
    		txtSurname.setText(s.getCognome());
    	}else if(correct) {
    		txtResult.setText(String.format("La matricola %d è inesistente!!", matricola));
    	}
    
    
    }

    @FXML
    void doSearchSubscribed(ActionEvent event) {
    	txtResult.clear();
    	
    	Corso cTemp = comboBox.getValue();
    	if(cTemp.getNumeroCrediti()==-1)
    		txtResult.setText("Selezionare un corso!!");
    	else {
    		List<Studente> studenti = this.model.getStudentsByCourse(cTemp);
    		for(Studente sTemp: studenti) {
    			txtResult.appendText(String.format("%-8d %-24s %-15s %s\n", sTemp.getMatricola(), sTemp.getNome(), sTemp.getCognome(), sTemp.getCDS()));
    		}
    	}

    }

    @FXML
    void doSubscribe(ActionEvent event) {
    	
    	btnReset.setDisable(false);
    	txtName.clear();
    	txtSurname.clear();
    	//Prendo lo studente
    	int matricola = 0;
    	boolean correct = true;
    	try {
    	 matricola = Integer.parseInt(txtID.getText());
   
    	}catch(NumberFormatException nfe) {
    		correct = false;
    		txtResult.setText("Eccezzione! Bisogna inserire un valore eclusivamente numerico");
    	}
    	Studente s = model.getStudentByID(matricola);
    	
    	if(comboBox.getValue().getCodIns()==null) {
    		txtResult.setText("Selezionare un corso");
    	}else if (!correct) {
    		txtResult.setText(String.format("La matricola %d è inesistente!!", matricola));
    	}
    	else if (comboBox.getValue().getCodIns()!=null) {
    		if(s.getCognome()!=null && s.getNome()!=null && s.getCDS()!=null) {
    			List<Corso> corsi = this.model.getCoursesByStudent(s);
    			boolean iscritto = false;
    			for(Corso c: corsi) {
    				if(c.equals(comboBox.getValue())){
    					iscritto = true;
    				}
    			}
    			if(!iscritto) {
    				txtResult.setText("Iscritto correttamente al corso");
    				
    			}else {
    				txtResult.setText("Studente gia iscritto al corso");

    			}
    		}
    	}

    }

    @FXML
    void initialize() {
        assert comboBox != null : "fx:id=\"comboBox\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnSearchSub != null : "fx:id=\"btnSearchSub\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnStudent != null : "fx:id=\"btnStudent\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtName != null : "fx:id=\"txtName\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtSurname != null : "fx:id=\"txtSurname\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnSearchCourses != null : "fx:id=\"btnSearchCourses\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnSubscribe != null : "fx:id=\"btnSubscribe\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnReset != null : "fx:id=\"btnReset\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtID != null : "fx:id=\"txtID\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";

    }
    
    public void setModel(Model model) {
    	
    	comboBox.getSelectionModel().select(new Corso(null,-1,"(Selezionare un corso)",-1));
    	btnReset.setDisable(true);
    	
    	this.model = model;
    	comboBox.getItems().addAll(this.model.getCoursesList());

    
    }
    
}
