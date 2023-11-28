/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package java.main;

import FXMLDocuments.Preset;
import java.util.HashMap;
import javafx.scene.chart.ScatterChart;

/**
 *
 * @author danie
 */
public class ChartSingleton {
    
    public static HashMap presets = new HashMap<String,Preset>();
    public static ChartSingleton chartController = new ChartSingleton();
    public static ScatterChart theChart;

    private ChartSingleton() {
    }
    
}
