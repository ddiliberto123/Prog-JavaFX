/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package FXMLDocuments;


import main.Planet;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;

import javafx.scene.input.KeyEvent;

import javafx.scene.layout.BorderPane;

import javafx.scene.layout.VBox;
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
    private ComboBox<Planet> presetBox;
    @FXML
    private TextField massText;
    @FXML
    private TextField radiusText;
    @FXML
    private Button startButton;
    @FXML
    private Button clearButton;
    
    @FXML
    private Button saveButton;
    @FXML
    private Button deleteButton;
    @FXML
    private MenuItem editClear;
    @FXML
    private MenuItem fileClose;

    public ScatterChart<Double, Double> scatterChart;
    @FXML
    private BorderPane borderPane;
    @FXML
    private TextField xVel;
    @FXML
    private TextField yVel;
    
    


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        saveButton.setDisable(true); 
        

        NumberAxis xAxis = new NumberAxis();
        xAxis.setLabel("x");
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("y");
        scatterChart = new ScatterChart(xAxis,yAxis);
        borderPane.setCenter(scatterChart);
        Planet.setTheChart(scatterChart);
        Planet earth = new Planet(1.5*Math.pow(10, 11),5.97*Math.pow(10, 24),"Earth");
        System.out.println(scatterChart.getData().get(0));

        // TODO
    }


    @FXML
    private void clearAll(ActionEvent event) {

        massText.clear();
        radiusText.clear();
        xVel.clear();
        yVel.clear();
        
    }

    @FXML
    private void savePreset(ActionEvent event) {
        
        presetBox.setConverter(new StringConverter<Planet>() {
    @Override
    
    public String toString(Planet object) {
        return object.getPlanetName();
    }

    @Override
    public Planet fromString(String string) {
        return null;
    }
});
        // System.out.println(presetBox.getEditor().getText());
         
        Planet toAdd = new Planet(Double.parseDouble(radiusText.getText()),Double.parseDouble(massText.getText()),presetBox.getEditor().getText());
         
        presetBox.getItems().add(toAdd); 
        Preset.presets.put(toAdd.getPlanetName(), toAdd); 
         
         
        
        
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
        xVel.clear();
        yVel.clear();
        
    }

    @FXML
    private void closeApp(ActionEvent event) {
        
        Platform.exit(); 
        
    }

    @FXML
    private void checkIfDouble(KeyEvent event) {
        try{
        Double.parseDouble(massText.getText());
        saveButton.setDisable(false);
        }
        catch(Exception e){
            saveButton.setDisable(true); 
             
        }
        
        
    }

    @FXML
    private void checkIfDouble2(KeyEvent event) {
        
        try{
        Double.parseDouble(radiusText.getText()); 
        }
        catch(Exception e){
            saveButton.setDisable(true); 
             
        }
    }

    @FXML
    private void checkIfDouble3(KeyEvent event) {
        try{
        Double.parseDouble(xVel.getText());
        saveButton.setDisable(false);
        }
        catch(Exception e){
            saveButton.setDisable(true); 
        }
    }

    @FXML
    private void checkIfDouble(ActionEvent event) {
    }

    @FXML
    private void checkIfDouble4(KeyEvent event) {
        
        try{
        Double.parseDouble(yVel.getText());
        saveButton.setDisable(false);
        }
        catch(Exception e){
            saveButton.setDisable(true); 
        }
    }
}

