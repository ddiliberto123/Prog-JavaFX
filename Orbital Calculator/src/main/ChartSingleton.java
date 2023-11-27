/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import javafx.fxml.FXML;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;

/**
 *
 * @author danie
 */
public class ChartSingleton {
    private static final ChartSingleton instance = new ChartSingleton();

    @FXML
    private static ScatterChart<Double, Double> scatterChart;
    
    private ChartSingleton() {
        
    }

    public static ChartSingleton getInstance(){
        return instance;
    }
    
    public ScatterChart getTheChart() {
        return scatterChart;
    }
    
    public static void addPlanet(Planet planet){
        XYChart.Series series = new XYChart.Series<>();
        series.setName(planet.getPlanetName());
        for (int i = 0; i < planet.getxCords().size(); i++) 
            series.getData().add(new XYChart.Data(planet.getxCords().get(i),planet.getyCords().get(i)));
        scatterChart.getData().add(series);
    }
    
}
