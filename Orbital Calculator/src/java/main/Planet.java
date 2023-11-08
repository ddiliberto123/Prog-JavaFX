/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package java.main;

import java.util.ArrayList;
import java.util.Vector;

/**
 *
 * @author danie
 */
public class Planet {
    public final double bigG = 6.6743*Math.pow(10, -11);
    public double x,y,mass,radius;
    public int[] acceleration = {0,0};
    
    public Planet(double mass, double radius,double x, double y) {
        this.mass = mass;
        this.radius = radius;
        this.x = x;
        this.y = y;
    }
    
    public double calculatePlanetaryFnet(Planet planetTwo){
        return bigG*(this.mass * planetTwo.mass)/Math.pow(Math.abs(this.radius - planetTwo.radius),2);
    }
    
    public double applyForce(double force){
//        return this.accelerationthis.mass;
        return 0;
    }
    
    public void plotXY(){
        
    }
}
