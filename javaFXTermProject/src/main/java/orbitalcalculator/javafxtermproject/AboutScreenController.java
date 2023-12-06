/**
 * This package contains the orbital calculator and its respective .java files
 */
package orbitalcalculator.javafxtermproject;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author danie
 */
public class AboutScreenController implements Initializable {

    @FXML
    private BorderPane borderPane;
    @FXML
    private MenuItem fileClose;

    /**
     * Initializes the AboutScreen controller class which is responsible for the about page.
     * @param url {@code URL} value
     * @param rb {@code ResourceBundle} value
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void switchToGame(ActionEvent event) throws IOException {
        App.setRoot("GameScreen");
    }

    @FXML
    private void closeApp(ActionEvent event) {
        Platform.exit();
    }
    
}
