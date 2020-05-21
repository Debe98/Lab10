/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.bar;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.bar.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class FXMLController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML
    void handleSimula(ActionEvent event) {
    	model.avviaSimulazione();
    	int clienti = model.getClienti();
    	int insoddisfatti = model.getInsoddisfatti();
    	txtResult.setText("Serviti "+clienti+" clienti.\nClienti insoddisfatti: "+insoddisfatti);
    	txtResult.appendText(String.format("\nPercentuale di soddisfazione: %.2f", (float) (clienti-insoddisfatti)/clienti));
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";

    }

    private Model model;
	public void setModel(Model model) {
		this.model = model;
	}
}
