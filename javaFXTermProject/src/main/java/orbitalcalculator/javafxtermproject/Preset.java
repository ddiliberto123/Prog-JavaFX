/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package orbitalcalculator.javafxtermproject;

import java.util.HashMap;

/**
 * Class to store the data of the presets <p>
 * Made before the controlsfx api was used. <p>
 * Made obsolete with {@code CheckComboBox}
 * @author 2271909
 */
@Deprecated
public class Preset {
    /**
     * All the presets within the comboBox
     */
    public static HashMap presets = new HashMap<String,Preset>();
    
    private String name;

    /**
     * Creates the preset
     * @param name {@code String} value
     */
    public Preset(String name) {
        this.name = name;
    }

    /**
     * Gets the name 
     * @return {@code String} value
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name
     * @param name {@code String} value
     */
    public void setName(String name) {
        this.name = name;
    }
}
