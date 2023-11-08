/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package FXMLDocuments;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;

/**
 * FXML Controller class
 *
 * @author Samuel
 */
public class MainScreenController implements Initializable {

    @FXML
    private VBox controleBox;
    @FXML
    private ComboBox<?> presetBox;
    @FXML
    private TextField massText;
    @FXML
    private TextField radiusText;
    @FXML
    private Button startButton;
    @FXML
    private Button clearButton;
    @FXML
    private Rectangle graphPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        
        // TODO
    }    

    @FXML
    private void clearAll(ActionEvent event) {

        massText.clear();
        radiusText.clear();
        
    }
    
}
