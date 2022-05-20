/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Exceptions;

/**
 *
 * @author lenovo
 */
public class InsufficeintSizeException extends Exception {
    @Override 
    
    public String getMessage(){
        return "this size of the dough exceeds the size of the pan";
    }
    
}
