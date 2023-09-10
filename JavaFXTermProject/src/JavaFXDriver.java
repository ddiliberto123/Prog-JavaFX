/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */

import java.util.ArrayList;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.chart.*;

/**
 *
 * @author danie
 */
public class JavaFXDriver extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        
        NumberAxis xAxis = new NumberAxis();
        NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("x (AU)");
        yAxis.setLabel("y (AU)");
        final ScatterChart<Number,Number> scatterChart = new ScatterChart<>(xAxis,yAxis);
        scatterChart.setTitle("Orbits");
        EllipticalOrbit earth = new EllipticalOrbit(0.017,1.00000);
        ArrayList<EllipticalOrbit> bodiesArray = new ArrayList<>();
        bodiesArray.add(earth);
        double rate = .01;
        XYChart.Series series = new XYChart.Series();
        for (EllipticalOrbit e : bodiesArray){
            System.out.println(e.semiMajorAxis + " " + e.semiMinorAxis);
            System.out.println((1-(Math.pow(5,2)/(Math.pow(e.semiMajorAxis,2)))));
            System.out.println(Math.sqrt((1-(Math.pow(5,2)/(Math.pow(e.semiMajorAxis,2))))*Math.pow(e.semiMinorAxis,2)));
            for (double i = -e.semiMajorAxis; i < e.semiMajorAxis; i+=rate){
                series.getData().add(new XYChart.Data(i,Math.sqrt((1-(Math.pow(i,2)/(Math.pow(e.semiMajorAxis,2))
                        ))*Math.pow(e.semiMinorAxis,2))));
                series.getData().add(new XYChart.Data(i,-(Math.sqrt((1-(Math.pow(i,2)/(Math.pow(e.semiMajorAxis,2))
                        ))*Math.pow(e.semiMinorAxis,2)))));
            }
        }
        
        Scene scene = new Scene(scatterChart,600,500);
        scatterChart.getData().add(series);
        primaryStage.setTitle("Planets");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
