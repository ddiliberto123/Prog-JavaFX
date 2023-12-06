/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package orbitalcalculator.javafxtermproject;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author 2271909
 */
public class MainMenuController implements Initializable {

    @FXML
    private Button startButton;
    @FXML
    private Button exitButton;

    /**
     * Initializes the MainMenu controller class which is responsible for the creation of the main menu;
     * @param url {@code URL} value
     * @param rb {@code ResourceBundle} value
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    @FXML
    private void goToMainPage(ActionEvent event) throws IOException {
        App.setRoot("GameScreen");
    }

    @FXML
    private void closeApp(ActionEvent event) {
        Platform.exit();
    }
    
}
