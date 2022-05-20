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
public class InsufficientIngredientAmountException extends Exception{
    
    public String getMessage(){
        return "try less amount";
    }
    
}
