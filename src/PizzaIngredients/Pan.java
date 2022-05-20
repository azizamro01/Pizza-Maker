/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PizzaIngredients;

import Exceptions.InsufficientIngredientAmountException;
import Exceptions.InsufficeintSizeException;
import java.util.ArrayList;

/**
 *
 * @author lenovo
 */
public class Pan {

    private Dough pizzaDough;
    private ArrayList<Ingredient> pizzaIngredients = new ArrayList<>();
    private double totalCalories;
    private double totalWeight;
    private int size;

    public Pan(int size) {
        this.size = size;

    }

    public Pan() {
    }

    public Dough getPizzaDough() {
        return pizzaDough;

    }

    // recommended weight can handle small errors , ONLY small ones .So,we need to test that
    private double calculateRelativeError(double nPut, double nRecommended) {

        return ((Math.abs((nPut - nRecommended * size) / (nRecommended * size))));

    }

    public void addIngredient(Ingredient I, float amount) throws InsufficientIngredientAmountException {

        String className = I.getName();
        for (int i = 0; i < pizzaIngredients.size(); i++) {
            if (pizzaIngredients.get(i).getName().equals(className)) {
                double previousWeight = I.getWeight();
                double previousCalories = I.getCalories();
                pizzaIngredients.get(i).setAmount(amount, false);
                if (calculateRelativeError(I.getWeight(),
                    I.getRecommendedWeightPerOneSizeUnit()) > 0.1) {
                    I.setAmount(-1 * amount, false);
                    throw new InsufficientIngredientAmountException();

                }
                /* so that the calories/weight of the previous amount won't be added twice*/
                this.totalCalories = this.totalCalories - previousCalories + I.getCalories();
                this.totalWeight = this.totalWeight - previousWeight + I.getWeight();
                /*------*/

                return;
            }

        }

        I.setAmount(amount, true);
        if (calculateRelativeError(I.getWeight(),
                I.getRecommendedWeightPerOneSizeUnit()) > 0.1) {
            I.setAmount(-1 * amount, false);// retrieve the amount taken
            throw new InsufficientIngredientAmountException();

        }

        pizzaIngredients.add(I);
        totalCalories = totalCalories + I.getCalories();
        totalWeight = totalWeight + I.getWeight();

    }

    public String getSize() {
        switch (this.size) {
            case 1:
                return "small";
            case 2:
                return "medium";
            case 3:
                return "large";
            default:
                return "medium";
        }
    }

    public int getSizeLevel() {
        return this.size;
    }

    public void pourPizzaDough(Dough pizzaDough) throws InsufficeintSizeException {
        if (pizzaDough.getSize() <= size) {
            this.pizzaDough = pizzaDough;
            totalCalories = totalCalories + pizzaDough.getCalories();
            totalWeight = totalWeight + pizzaDough.getWeight();
        } else {
            throw new InsufficeintSizeException();
        }

    }

    public double getTotalCalories() {
        return totalCalories;
    }

    public double getTotalWeight() {
        return totalWeight;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getInfo() {
        String info = "";
        for (int i = 0; i < pizzaIngredients.size(); i++) {
            info = info + pizzaIngredients.get(i).getName() + " " + pizzaIngredients.get(i).getWeight()
                    + " grams\n";
        }
        return info + "total weight : " + totalWeight + "\ntotal calories : " + totalCalories;
    }

    public String[] cutSlices(int nSlices) {
        String[] temp = new String[nSlices];
        for (int i = 0; i < nSlices; i++) {
            temp[i] = new String();

            temp[i] = "Slice " + (i + 1) + " : calories :" + totalCalories / nSlices + ", weight : "
                    + totalWeight / nSlices;

        }
        return temp;
    }

}
