/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package orbitalcalculator.javafxtermproject;

import java.util.HashMap;

/**
 *
 * @author 2271909
 */
public class Preset {
    
    public static HashMap presets = new HashMap<String,Preset>();
    
    private String name;

    public Preset(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
