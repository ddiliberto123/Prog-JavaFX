/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package FXMLDocuments;


import main.Planet;
//import org.gillius.jfxutils.chart.ChartZoomManager;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.Axis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;

import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import javafx.scene.layout.BorderPane;

import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
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
        initializeChart();
        //https://gillius.org/jfxutils/docs/0.3/org/gillius/jfxutils/chart/ChartZoomManager.html
//        ComboBox<ComboBoxItemWrap<Planet>> cb = new ComboBox<>();
//        
//        
//        cb.setCellFactory( c -> {
//            ListCell<ComboBoxItemWrap<Planet>> cell = new ListCell<>(){
//                @Override
//                protected void updateItem(ComboBoxItemWrap<Planet> item, boolean empty) {
//                    super.updateItem(item, empty);
//                    if (!empty) {
//                        final CheckBox cb = new CheckBox(item.toString());
//                        cb.selectedProperty().bind(item.checkProperty());
//                        setGraphic(cb);
//                    }
//                }
//            };
//
//            cell.addEventFilter(MouseEvent.MOUSE_RELEASED, event -> {
//                cell.getItem().checkProperty().set(!cell.getItem().checkProperty().get());
//                StringBuilder sb = new StringBuilder();
//                cb.getItems().filtered( f-> f!=null).filtered( f-> f.getCheck()).forEach( p -> {
//                    sb.append("; "+p.getItem());
//                });
//                final String string = sb.toString();
//                cb.setPromptText(string.substring(Integer.min(2, string.length())));
//            });
//
//            return cell;
//        });
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

    @FXML
    private void savePreset(ActionEvent event) {
        
//        presetBox.setConverter(new StringConverter<Planet>() {
//            @Override
//
//            public String toString(Planet object) {
//                return object.getPlanetName();
//            }
//
//            @Override
//            public Planet fromString(String string) {
//                return null;
//            }
//        });
        savePlanet(Double.parseDouble(radiusText.getText()),Double.parseDouble(massText.getText()),
                Double.parseDouble(xVel.getText()),Double.parseDouble(yVel.getText()),presetBox.getEditor().getText());

    }
    
    private void savePlanet(double distanceFromSun,double mass, double xInitialVelocity, double yInitialVelocity,String name){
        Planet toAdd = new Planet(distanceFromSun,mass,xInitialVelocity,yInitialVelocity,name);
        presetBox.getItems().add(toAdd); 
        Preset.presets.put(name, toAdd); 
    }
    
    private void initializeChart(){
        NumberAxis xAxis = new NumberAxis();
        xAxis.setLabel("x");
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("y");
        lineChart = new LineChart(xAxis,yAxis);
        lineChart.setAxisSortingPolicy(LineChart.SortingPolicy.NONE);
        lineChart.setCreateSymbols(false);
        lineChart.setMaxHeight(300);
        lineChart.setMaxWidth(400);
        lineChart.setOnScroll(e -> {
            if (e.getDeltaY() == 0)
                return;
            else if (e.getDeltaY() < 0){
                
            } else if (e.getDeltaY() > 0){
            
            }
        });
        XYChart.Series series = new XYChart.Series<>();
        series.setName("The Sun");
        series.getData().add(new XYChart.Data(0,0));
        lineChart.getData().add(series);
        borderPane.setCenter(lineChart);
        Planet.setTheChart(lineChart);
        
        savePlanet(1.5e11,6e24,0,30000,"Earth");
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
        lineChart.getData().clear();
        XYChart.Series series = new XYChart.Series<>();
        series.setName("The Sun");
        series.getData().add(new XYChart.Data(0,0));
        lineChart.getData().add(series);
    }

    @FXML
    private void closeApp(ActionEvent event) {
        Platform.exit(); 
        
    }
    
    @FXML
    private void loadPreset(){
        
        Planet loading = presetBox.getSelectionModel().getSelectedItem();
        if(!(loading == null)){
        massText.setText(""+loading.getMass());
        radiusText.setText(""+loading.getDistanceFromSun());
        xVel.setText(""+loading.getxInitalVelocity());
        yVel.setText(""+loading.getyInitalVelocity());
        }
        
    }
    
}

