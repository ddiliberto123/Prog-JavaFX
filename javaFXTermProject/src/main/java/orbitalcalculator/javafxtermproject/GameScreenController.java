/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package orbitalcalculator.javafxtermproject;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import org.controlsfx.control.CheckComboBox;
import org.controlsfx.control.IndexedCheckModel;
import org.gillius.jfxutils.chart.AxisConstraint;
import org.gillius.jfxutils.chart.AxisConstraintStrategies;
import org.gillius.jfxutils.chart.AxisConstraintStrategy;
import org.gillius.jfxutils.chart.ChartPanManager;
import org.gillius.jfxutils.chart.ChartZoomManager;

/**
 * FXML Controller class
 *
 * @author 2271909
 */
public class GameScreenController implements Initializable {

    @FXML
    private BorderPane borderPane;
    @FXML
    private MenuItem fileClose;
    @FXML
    private MenuItem editClear;
    @FXML
    private VBox controleBox;
    @FXML
    private ComboBox<Planet> presetBox;
    @FXML
    private TextField massText;
    @FXML
    private TextField radiusText;
    @FXML
    private TextField xVel;
    @FXML
    private TextField yVel;
    @FXML
    private Button clearButton;
    @FXML
    private Button saveButton;
    @FXML
    private Button deleteButton;
    @FXML
    private Button generateOrbitButton;
    @FXML
    private Button generateAllOrbits;

    public LineChart<Double, Double> lineChart;

    public HashMap<String, Planet> allPlanets = new HashMap<>();
    public CheckComboBox<Planet> checkComboBox = new CheckComboBox();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        controleBox.getChildren().add(1, checkComboBox);
        checkComboBox.addEventHandler(EventType.ROOT, e -> {
//            System.out.println(e.);
        });
        checkComboBox.getCheckModel().getCheckedItems().addListener(new ListChangeListener<>() {
            @Override
            public void onChanged(ListChangeListener.Change<? extends Planet> change) {
                while(change.next()){
                    if(change.wasAdded()){ 
                        for(Planet planet : change.getAddedSubList()){
                            if (checkComboBox.getItems().contains(planet)){
                                checkComboBox.setTitle(planet.getPlanetName());
                                System.out.println(change.getAddedSubList());
                                planet.show();
                            }
                        }
                    }
                    else if (change.wasRemoved()){
                        for(Planet planet : change.getRemoved())
                            planet.hide();
                        if (checkComboBox.getItems().size() == 1){
                            checkComboBox.setTitle("");
                        }
                    }
                }
                
            }
        });
//        checkComboBox.getCheckModel().getCheckedItems().addListener((ListChangeListener.Change<? extends Planet> c) -> {
//            while(c.next()){
//                if (c.wasUpdated()){
//                    
//                }
//            }
//        });
        
//        checkComboBox.setCheckModel();
        initializeChart();
        ChartPanManager chartPanManager = new ChartPanManager(lineChart);
        chartPanManager.start();
        //https://gillius.org/jfxutils/docs/0.3/org/gillius/jfxutils/chart/ChartZoomManager.html
        saveButton.disableProperty().bind(Bindings.createBooleanBinding(() -> !saveButtonBoolean(),
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

    private boolean saveButtonBoolean() {
        try {
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
        } catch (Exception e) {
            return false;
        }
    }

    @FXML
    private void savePreset(ActionEvent event) {
        double distanceFromSun = Double.parseDouble(radiusText.getText());
        double mass = Double.parseDouble(massText.getText());
        double xInitialVelocity = Double.parseDouble(xVel.getText());
        double yInitialVelocity = Double.parseDouble(yVel.getText());
        String name = presetBox.getEditor().getText();
        
//        allPlanets.put(name, new Planet(distanceFromSun,mass,xInitialVelocity,yInitialVelocity,name));
        savePlanet(distanceFromSun,mass,xInitialVelocity,yInitialVelocity,name);
    }

    private void savePlanet(double distanceFromSun, double mass, double xInitialVelocity, double yInitialVelocity, String name) {
//        Planet toAdd = new Planet(distanceFromSun, mass, xInitialVelocity, yInitialVelocity, name);
//
//        presetBox.getItems().add(toAdd);
//        Preset.presets.put(name, toAdd);
        allPlanets.put(name, new Planet(distanceFromSun,mass,xInitialVelocity,yInitialVelocity,name));
        checkComboBox.getItems().add(allPlanets.get(name));
    }

    private void initializeChart() {
        NumberAxis xAxis = new NumberAxis();
        xAxis.setLabel("x");
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("y");
        lineChart = new LineChart(xAxis, yAxis);
        lineChart.setAxisSortingPolicy(LineChart.SortingPolicy.NONE);
        lineChart.setCreateSymbols(false);
        lineChart.setMaxHeight(300);
        lineChart.setMaxWidth(400);
        //https://gillius.org/jfxutils/docs/latest/
        //https://www.javatips.net/api/jfxutils-master/jfxutils/src/main/java/org/gillius/jfxutils/chart/DefaultAxisTickFormatter.java
        ChartPanManager chartPanManager = new ChartPanManager(lineChart);
        chartPanManager.start();
        Rectangle zoomRect = new Rectangle();
        zoomRect.setWidth(10);
        zoomRect.setHeight(10);
        ChartZoomManager chartZoomManager = new ChartZoomManager(borderPane,zoomRect,lineChart);
        AxisConstraintStrategy axisConstraintStrategy = AxisConstraintStrategies.getFixed(AxisConstraint.None);
        chartZoomManager.setAxisConstraintStrategy(axisConstraintStrategy);
        chartZoomManager.start();
        lineChart.setAnimated(false);
        XYChart.Series series = new XYChart.Series<>();
        series.setName("The Sun");
        series.getData().add(new XYChart.Data(0, 0));
        lineChart.getData().add(series);
        borderPane.setCenter(lineChart);
        Planet.setTheChart(lineChart);

        savePlanet(1.4748e11, 5.97219e24, 0, 30000, "Earth");
        savePlanet(230e9,6.39e23,0,23e3,"Mars");
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
        series.getData().add(new XYChart.Data(0, 0));
        lineChart.getData().add(series);
    }

    @FXML
    private void closeApp(ActionEvent event) {
        Platform.exit();

    }

    @FXML
    private void loadPreset() {

        Planet loading = presetBox.getSelectionModel().getSelectedItem();
        if (!(loading == null)) {
            massText.setText("" + loading.getMass());
            radiusText.setText("" + loading.getDistanceFromSun());
            xVel.setText("" + loading.getxInitalVelocity());
            yVel.setText("" + loading.getyInitalVelocity());
        }

    }

    @FXML
    private void generateOrbit(ActionEvent event) {

        String name = presetBox.getEditor().getText();
        Planet toBeGenerated = (Planet) Preset.presets.get(name);
        toBeGenerated.plotOrbit();

    }

    @FXML
    private void generateAllOrbits(ActionEvent event) {

        for (int i = 0; i < presetBox.getVisibleRowCount(); i++) {

            Planet toBeGenerated = presetBox.getItems().get(i);
            toBeGenerated.plotOrbit();

        }

    }

}
