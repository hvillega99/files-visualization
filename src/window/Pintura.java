/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package window;

import java.io.Serializable;
import javafx.scene.paint.Color;

/**
 *
 * @author CDHTN
 */
public class Pintura implements Serializable {
   
    private double red;
    private double green;
    private double blue;
 
    public Pintura(Color color) {
        red=color.getRed();
        green=color.getGreen();
        blue=color.getBlue();
    }

    public double getRed() {
        return red;
    }

    public void setRed(double red) {
        this.red = red;
    }

    public double getGreen() {
        return green;
    }

    public void setGreen(double green) {
        this.green = green;
    }

    public double getBlue() {
        return blue;
    }

    public void setBlue(double blue) {
        this.blue = blue;
    }
    
    
    
    
    
    
    
}
