/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package FXMLDocuments;

import java.main.Planet;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.ScatterChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.util.StringConverter;

/**
 * FXML Controller class
 *
 * @author Samuel
 */
public class MainScreenController implements Initializable {

    @FXML
    private VBox controleBox;
    @FXML
    private ComboBox<Preset> presetBox;
    @FXML
    private TextField massText;
    @FXML
    private TextField radiusText;
    @FXML
    private Button startButton;
    @FXML
    private Button clearButton;
    @FXML
    private TextField extraValueText;
    @FXML
    private Button saveButton;
    @FXML
    private ScatterChart<Double, Double> scatterChart;
    @FXML
    private Button deleteButton;
    @FXML
    private MenuItem editClear;
    @FXML
    private MenuItem fileClose;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Planet earth = new Planet(1.5*Math.pow(10, 11),5.97*Math.pow(10, 24));
        // TODO
    }    

    @FXML
    private void clearAll(ActionEvent event) {

        massText.clear();
        radiusText.clear();
        extraValueText.clear();
        
    }

    @FXML
    private void savePreset(ActionEvent event) {
        
        presetBox.setConverter(new StringConverter<Preset>() {
        @Override
        public String toString(Preset object) {
            return object.getName();
        }

    @Override
    public Preset fromString(String string) {
        return null;
    }
});
        // System.out.println(presetBox.getEditor().getText());
         
        Preset toAdd = new Preset(presetBox.getEditor().getText());
         
        presetBox.getItems().add(toAdd); 
        Preset.presets.put(toAdd.getName(), toAdd); 
         
         
        
        
    }

    @FXML
    private void deletePreset(ActionEvent event) {
        
         String name = presetBox.getEditor().getText(); 
        presetBox.getItems().remove(Preset.presets.get(name)); 
        
    }

    @FXML
    private void deleteAllPresets(ActionEvent event) {
        
        presetBox.getItems().clear();
        massText.clear();
        radiusText.clear();
        extraValueText.clear();
        
    }

    @FXML
    private void closeApp(ActionEvent event) {
        
        Platform.exit(); 
        
    }
    
}
