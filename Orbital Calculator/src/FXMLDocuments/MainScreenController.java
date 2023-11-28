/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package FXMLDocuments;


import main.Planet;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
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
    public LineChart<Double, Double> lineChart;
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
        lineChart = new LineChart(xAxis,yAxis);
        lineChart.setAxisSortingPolicy(LineChart.SortingPolicy.NONE);
        lineChart.setCreateSymbols(false);
        
        lineChart.setOnScroll(e -> {
            if (e.getDeltaY() == 0)
                return;
            else if (e.getDeltaY() < 0){
                
            } else if (e.getDeltaY() > 0){
            
            }
        });
        borderPane.setCenter(lineChart);
        Planet.setTheChart(lineChart);
        Planet earth = new Planet(1.5e11,6e24,0,30000,"Earth");
        saveButton.disableProperty().bind(Bindings.createBooleanBinding(() -> ! saveButtonBoolean(),
                massText.textProperty(),
                radiusText.textProperty(),
                xVel.textProperty(),
                yVel.textProperty()));
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
         
//        Planet toAdd = new Planet(Double.parseDouble(radiusText.getText()),Double.parseDouble(massText.getText()),presetBox.getEditor().getText());
//         
//        presetBox.getItems().add(toAdd); 
//        Preset.presets.put(toAdd.getPlanetName(), toAdd); 
         
         
        
        
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
    
    private boolean saveButtonBoolean(){
        try{
            Double.parseDouble(massText.getText());
            Double.parseDouble(radiusText.getText());
            Double.parseDouble(xVel.getText());
            Double.parseDouble(yVel.getText());
            if (massText.getText().isEmpty())
                return false;
            if (radiusText.getText().isEmpty())
                return false;
            if (xVel.getText().isEmpty())
                return false;
            if (yVel.getText().isEmpty())
                return false;
            return true;
        }
        catch(Exception e){
            return false;
        }
    }
}

