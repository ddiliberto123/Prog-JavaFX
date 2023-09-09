/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author danie
 */
public class Orbit {
    public static final double gravitationalConstant = 6.67430 * Math.pow(10,-11);
    private double bodyMass;
    private double bodyRadius;
    public double orbitalVelocity;
    public double period;

    public Orbit(double bodyMass, double bodyRadius) {
        this.bodyMass = bodyMass;
        this.bodyRadius = bodyRadius;
        this.orbitalVelocity = Math.sqrt((gravitationalConstant*this.bodyMass)/this.bodyRadius);
        this.period = Math.PI*2*Math.sqrt(Math.pow(this.bodyRadius, 3)/(gravitationalConstant*this.bodyMass));
    }
    
    
}
