/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package orbitalcalculator.javafxtermproject;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Optional;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Control;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import org.controlsfx.control.CheckComboBox;
import org.controlsfx.control.IndexedCheckModel;
import org.gillius.jfxutils.chart.AxisConstraint;
import org.gillius.jfxutils.chart.AxisConstraintStrategies;
import org.gillius.jfxutils.chart.AxisConstraintStrategy;
import org.gillius.jfxutils.chart.AxisTickFormatter;
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
    
//    private ComboBox<Planet> presetBox;
    
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
    private Button generateAllOrbits;

    public LineChart<Double, Double> lineChart;

    public HashMap<String, Planet> allPlanets = new HashMap<>();
    
    @FXML
    public CheckComboBox<Planet> checkComboBox = new CheckComboBox();
    @FXML
    private TextField nameField;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        checkComboBox.getCheckModel().getCheckedItems().addListener(new ListChangeListener<>() {
            @Override
            public void onChanged(ListChangeListener.Change<? extends Planet> change) {
                while(change.next()){
                    if(change.wasAdded()){ 
                        for(Planet planet : change.getAddedSubList()){
                            if (planet.shown)
                                continue;
                            planet.show();
                            nameField.setText(planet.getPlanetName());
                            massText.setText(String.valueOf(planet.getMass()));
                            radiusText.setText(String.valueOf(planet.getDistanceFromSun()));
                            xVel.setText(String.valueOf(planet.getxInitalVelocity()));
                            yVel.setText(String.valueOf(planet.getyInitalVelocity()));
                        }
                    }
                    else if (change.wasRemoved()){
                        for(Planet planet : change.getRemoved()){
                            if (planet.shown)
                                planet.hide();
                        }
                    }
                }
            }
        });
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
        nameField.clear();
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
            if (nameField.getText().isEmpty())
                return false;
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

    private void savePreset() {
        String name = nameField.getText();
        double distanceFromSun = Double.parseDouble(radiusText.getText());
        double mass = Double.parseDouble(massText.getText());
        double xInitialVelocity = Double.parseDouble(xVel.getText());
        double yInitialVelocity = Double.parseDouble(yVel.getText());
        if(allPlanets.containsKey(name)){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("Preset already existant");
            alert.setContentText("There is already a preset with the name " + name + " would you like to override?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK){
                deletePreset();
                savePlanet(distanceFromSun,mass,xInitialVelocity,yInitialVelocity,name);
            }
        } else 
            savePlanet(distanceFromSun,mass,xInitialVelocity,yInitialVelocity,name);
    }

    private void savePlanet(double distanceFromSun, double mass, double xInitialVelocity, double yInitialVelocity, String name) {
        allPlanets.put(name, new Planet(distanceFromSun,mass,xInitialVelocity,yInitialVelocity,name));
        checkComboBox.getItems().add(allPlanets.get(name));
    }

    private void initializeChart() {
        NumberAxis xAxis = new NumberAxis();
        xAxis.setLabel("x");
        xAxis.setTickLabelFormatter(new NumberAxis.DefaultFormatter(xAxis, null, " AU"));
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("y");
        yAxis.setTickLabelFormatter(new NumberAxis.DefaultFormatter(yAxis, null, " AU"));
        lineChart = new LineChart(xAxis, yAxis);
        lineChart.setAxisSortingPolicy(LineChart.SortingPolicy.NONE);
        lineChart.setCreateSymbols(false);
        lineChart.setPrefSize(400, 400);
        lineChart.setMinSize(Control.USE_PREF_SIZE, Control.USE_PREF_SIZE);
        lineChart.setMaxSize(Control.USE_PREF_SIZE, Control.USE_PREF_SIZE);
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

    private void deletePreset() {
        String name = nameField.getText();
        checkComboBox.getItems().remove(allPlanets.get(name));
        if (allPlanets.get(name).shown)
            allPlanets.get(name).hide();
        allPlanets.remove(name);
    }

    @FXML
    private void deleteAllPresets(ActionEvent event) {
        checkComboBox.getItems().clear();
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
    private void generateAllOrbits(ActionEvent event) {
        for (Planet planet : allPlanets.values()){
            if (!planet.shown)
                planet.show();
        }
    }

    @FXML
    private void savePresetButton(ActionEvent event) {
        savePreset();
    }

    @FXML
    private void deletePresetButton(ActionEvent event) {
        deletePreset();
    }

    @FXML
    private void switchToAbout(ActionEvent event) throws IOException {
        App.setRoot("AboutScreen");
    }


}
