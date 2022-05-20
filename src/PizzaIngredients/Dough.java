/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PizzaIngredients;

import java.util.ArrayList;

/**
 *
 * @author lenovo
 */
public class Dough {

    private ArrayList<Ingredient> DoughIngredients = new ArrayList<>();
    private double weight = 0;
    private double calories = 0;
    private int size;

    public void addIngredient(Ingredient I, float amount) {
            for (int i = 0; i < DoughIngredients.size(); i++) {
                if (DoughIngredients.get(i).getName().equals(I.getName())) {
                
                    double previousWeight = I.getWeight();
                    double previousCalories = I.getCalories();
                    DoughIngredients.get(i).setAmount(amount,false);
                    
                    /* so that the calories/weight of the previous amount won't be added twice*/
                   
                    this.calories = this.calories - previousCalories + I.getCalories();
                    this.weight = this.weight - previousWeight + I.getWeight();
                    /*------*/
                   
                  /*update the size of the dough as it has new amount of some Ingredient*/
                    setSize();
                    
                    return;
                }
            }
        

        I.setAmount(amount, true);
        DoughIngredients.add(I);
        this.calories = this.calories + I.getCalories();
        this.weight = this.weight + I.getWeight();
        setSize();
    }

    private void setSize() {
        if (this.weight < 400) {
            size = 1;
            return;
        } else if (this.weight < 800) {
            size = 2;
            return;
        } else {
            size = 3;
            return;
        }
    }

    public ArrayList<Ingredient> getDoughIngredients() {
        return DoughIngredients;
    }

    public void setDoughIngredients(ArrayList<Ingredient> DoughIngredients) {
        this.DoughIngredients = DoughIngredients;
    }

    public double getWeight() {
        return weight;
    }

    public double getCalories() {
        return calories;
    }

    public int getSize() {
        return size;
    }

    public String getInfo() {
        String info = "";
        for (int i = 0; i < DoughIngredients.size(); i++) {
            info = info + "ingredient " + (i + 1) + " : " + DoughIngredients.get(i).getName() + " , weight :  "
                    + DoughIngredients.get(i).getWeight() + " , calories :  "
                    + DoughIngredients.get(i).getCalories() + "\n";
        }
        info = info + "total calories = " + this.calories + "\n";
        return info;
    }

    public String getInfoV2() {
        String info = "";
        for (int i = 0; i < DoughIngredients.size(); i++) {
            
            info = info + DoughIngredients.get(i).getName() + " " + DoughIngredients.get(i).getWeight()
                    + " grams\n";

        }
        info = info + "total calories = " + this.calories + "\ntotal weight : " + weight;
        return info;
    }
}
