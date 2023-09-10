/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author danie
 */
public class EllipticalOrbit {
    public static final double gravitationalConstant = 6.67430 * Math.pow(10,-11);
    public double period, semiMajorAxis, eccentricity, semiMinorAxis;
    private double aphelion, perihelion;
    private double focalPoint = 0;
    
    public EllipticalOrbit(double eccentricity, double semiMajorAxis) {
        this.semiMajorAxis = semiMajorAxis;
        this.eccentricity = eccentricity;
        this.aphelion = this.semiMajorAxis*(1 + this.eccentricity);
        this.perihelion = this.semiMajorAxis*(1-this.eccentricity);
        this.period = Math.sqrt(Math.pow(this.semiMajorAxis, 3)) * 365;
        this.semiMinorAxis = this.semiMajorAxis*Math.sqrt(1-Math.pow(this.eccentricity, 2));
    }
}
