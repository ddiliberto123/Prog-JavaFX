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
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Control;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
//https://controlsfx.github.io/javadoc/11.0.3/index.html
import org.controlsfx.control.CheckComboBox;
//https://gillius.org/jfxutils/docs/latest/
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

    /**
     * Check ComboBox, holds the planets inside to be selected
     */
    @FXML
    public CheckComboBox<Planet> checkComboBox = new CheckComboBox();
    @FXML
    private TextField nameField;
    @FXML
    private Slider yearSlider;
    
    /**
     * The {@code LineChart} where the planets will be plotted
     */
    public static LineChart<Double, Double> lineChart;

    /**
     * The {@code HashMap} that stores all planets
     */
    public static HashMap<String, Planet> allPlanets = new HashMap<>();
    /**
     * Initializes the GameScreen controller class which is responsible for the game creation.
     * @param url {@code URL} value
     * @param rb {@code ResourceBundle} value
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Listener for the checkboxes/click events
        checkComboBox.getCheckModel().getCheckedItems().addListener(new ListChangeListener<>() {
            @Override
            public void onChanged(ListChangeListener.Change<? extends Planet> change) {
                //Checks if theres a next value otherwise it ends
                while(change.next()){
                    //Checks if the value was added
                    if(change.wasAdded()){ 
                        //Checks through the added planets
                        for(Planet planet : change.getAddedSubList()){
                            //Checks if the planet is already shown
                            if (planet.shown)
                                //continues (doesnt want duplicated
                                continue;
                            //Otherwise, it changes the planet
                            planet.show();
                            nameField.setText(planet.getPlanetName());
                            massText.setText(String.valueOf(planet.getMass()));
                            radiusText.setText(String.valueOf(planet.getDistanceFromSun()));
                            xVel.setText(String.valueOf(planet.getxInitialVelocity()));
                            yVel.setText(String.valueOf(planet.getyInitialVelocity()));
                        }
                    }
                    //Checks if the planet was removed
                    else if (change.wasRemoved()){
                        for(Planet planet : change.getRemoved()){
                            //If the planet was shown
                            if (planet.shown)
                                //Hides the planet
                                planet.hide();
                        }
                    }
                }
            }
        });
        //Sets the year slider to use the planet years... would have been deleted between switching FXMLs
        yearSlider.setValue(Planet.getYears());
        //Slider implimentation
        yearSlider.valueProperty().addListener((v,oldValue,newValue) -> {
            //Converts the slider to a integer (too much computation if double)
            Planet.setYears((double) newValue.intValue());
            for(Planet planet : allPlanets.values()){
                //For the planets, hides them, computes and shows if they were already shown
                if(planet.shown){
                    planet.hide();
                    planet.plotOrbit();
                    planet.show();
                } else
                    //Otherwise, it just computes
                    planet.plotOrbit();
            }
        });
        //Checks if the linechart was already made... dont want to make duplicates when switching between FXMLs
        if(lineChart == null){
            //Sets the initial chart values
            initializeChart(borderPane);
            //Sets the presets i.e. earth and mars
            savePlanet(1.4748e11, 5.97219e24, 0, 30000, "Earth");
            savePlanet(230e9, 6.39e23, 0, 23e3, "Mars");
        } else {
            initializeChart(borderPane);
            for(Planet planet : allPlanets.values()){
                checkComboBox.getItems().add(planet);
                if(planet.shown)
                    planet.shown = false;
            }
        }
        //Makes sure data is inside the fields
        saveButton.disableProperty().bind(Bindings.createBooleanBinding(() -> !saveButtonBoolean(),
                //Checks if the following fields are changed:
                nameField.textProperty(),
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

    //Returns true if valid input
    //Returns false if invalid
    private boolean saveButtonBoolean() {
        try {
            //Checks if the values are doubles
            Double.parseDouble(massText.getText());
            Double.parseDouble(radiusText.getText());
            Double.parseDouble(xVel.getText());
            Double.parseDouble(yVel.getText());
            //Checks if there are values in the fields
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

    //Saving the presets
    private void savePreset() {
        //Gets all the values
        String name = nameField.getText();
        double distanceFromSun = Double.parseDouble(radiusText.getText());
        double mass = Double.parseDouble(massText.getText());
        double xInitialVelocity = Double.parseDouble(xVel.getText());
        double yInitialVelocity = Double.parseDouble(yVel.getText());
        //Checks if there is already a planet with the name
        if(allPlanets.containsKey(name)){
            //Creates an alert
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("Preset already existant");
            alert.setContentText("There is already a preset with the name \"" + name + "\" would you like to override?");
            Optional<ButtonType> result = alert.showAndWait();
            //If the user approves
            if (result.isPresent() && result.get() == ButtonType.OK){
                //sets the planet that is already existance to the new ones
                allPlanets.get(name).hide();
                allPlanets.get(name).setDistanceFromSun(distanceFromSun);
                allPlanets.get(name).setMass(mass);
                allPlanets.get(name).setxInitialVelocity(xInitialVelocity);
                allPlanets.get(name).setyInitialVelocity(yInitialVelocity);
                allPlanets.get(name).show();
            }
        } else 
            savePlanet(distanceFromSun,mass,xInitialVelocity,yInitialVelocity,name);
    }

    private void savePlanet(double distanceFromSun, double mass, double xInitialVelocity, double yInitialVelocity, String name) {
        //Saves the planet and adds it to the allPlanets hashmap
        allPlanets.put(name, new Planet(distanceFromSun,mass,xInitialVelocity,yInitialVelocity,name));
        checkComboBox.getItems().add(allPlanets.get(name));
    }

    private static void initializeChart(BorderPane borderPane) {
        //Creates the linechart
        NumberAxis xAxis = new NumberAxis();
        xAxis.setLabel("x");
        //Sets the suffixes on the axis
        xAxis.setTickLabelFormatter(new NumberAxis.DefaultFormatter(xAxis, null, " AU" ));
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("y");
        yAxis.setTickLabelFormatter(new NumberAxis.DefaultFormatter(yAxis, null, " AU"));
        lineChart = new LineChart(xAxis, yAxis);
        //Sets the Planet class static value to the line chart
        Planet.setTheChart(lineChart);
        lineChart.setAxisSortingPolicy(LineChart.SortingPolicy.NONE);
        lineChart.setCreateSymbols(false);
        lineChart.setPrefSize(400, 400);
        lineChart.setMinSize(Control.USE_PREF_SIZE, Control.USE_PREF_SIZE);
        lineChart.setMaxSize(Control.USE_PREF_SIZE, Control.USE_PREF_SIZE);
        //https://gillius.org/jfxutils/docs/latest/
        //https://www.javatips.net/api/jfxutils-master/jfxutils/src/main/java/org/gillius/jfxutils/chart/DefaultAxisTickFormatter.java
        //Creates a pan manager and chart zoom manager from the API above (jfxutils)
        ChartPanManager chartPanManager = new ChartPanManager(lineChart);
        chartPanManager.start();
        Rectangle zoomRect = new Rectangle();
        zoomRect.setWidth(10);
        zoomRect.setHeight(10);
        ChartZoomManager chartZoomManager = new ChartZoomManager(borderPane,zoomRect,lineChart);
        //Sets a constraint so the user cant zoom accidentaly with their mouse (it was initially a thing)
        AxisConstraintStrategy axisConstraintStrategy = AxisConstraintStrategies.getFixed(AxisConstraint.None);
        chartZoomManager.setAxisConstraintStrategy(axisConstraintStrategy);
        chartZoomManager.start();
        //Removes animation or else duplication can occur
        lineChart.setAnimated(false);
        XYChart.Series series = new XYChart.Series<>();
        series.setName("The Sun");
        series.getData().add(new XYChart.Data(0, 0));
        lineChart.getData().add(series);
        borderPane.setCenter(lineChart);
        
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
//        nameField.clear();
//        massText.clear();
//        radiusText.clear();
//        xVel.clear();
//        yVel.clear();
        lineChart.getData().clear();
        allPlanets.clear();
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
            //If planet hiden, show
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
