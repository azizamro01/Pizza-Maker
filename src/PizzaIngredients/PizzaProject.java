/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PizzaIngredients;

import Loggers.FileLogger;
import java.io.File;

/**
 *
 * @author lenovo
 */
public class PizzaProject {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Kitchen k =new Kitchen(new FileLogger(new File("activityLog.txt")));
        k.prepareDough();


    
}
}
