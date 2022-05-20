/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PizzaIngredients;

/**
 *
 * @author lenovo
 */
public enum Ingredient {
    Tomato(27, 100, "Tomato", "Piece", 75),
    Yeast(18, 17, "Yeast", "Spoon", 18),
    Oil(120, 13, "Oil", "Cup", 40),
    Salt(2, 6, "Salt", "Spoon", 1.5),
    Sugar(25, 6, "Sugar", "Spoon", 3),
    Garlic(4, 80, "Garlic", "Piece", 20),
    Onion(44, 115, "Onion", "Piece", 28.75),
    PizzaSauce(10, 10, "Pizza Sauce", "Spoon", 10),// fill the information (10,10,...) ain't right
    Mozarilla(85, 28, "Mozarilla", "Bag", 14),
    Water(4, 200, "Water", "Cup", 150),
    Oregano(3, 1, "Oregano", "Piece", 3),
    Flour(32, 88, "Flour", "Cup", 200);

    private final double oneUnitCalories;
    private final double oneUnitWeight;
    private final String name;
    private final String unitName;
    private double amount;
    private double weight;
    private double calories;
    private boolean isAdded = false;
    private final double recommendedWeightPerOneSizeUnit;

    public double getRecommendedWeightPerOneSizeUnit() {
        return recommendedWeightPerOneSizeUnit;
    }

    private Ingredient(double oneUnitCalories, double oneUnitWeight, String name, String unitName,
            double recommendedWeightPerOneSizeUnit) {
        this.oneUnitCalories = oneUnitCalories;
        this.oneUnitWeight = oneUnitWeight;
        this.name = name;
        this.unitName = unitName;
        this.recommendedWeightPerOneSizeUnit = recommendedWeightPerOneSizeUnit;

    }

    public static Ingredient getTomato() {
        return Tomato;
    }

    public static Ingredient getYeast() {
        return Yeast;
    }

    public static Ingredient getOil() {
        return Oil;
    }

    public static Ingredient getSalt() {
        return Salt;
    }

    public static Ingredient getSugar() {
        return Sugar;
    }

    public static Ingredient getGarlic() {
        return Garlic;
    }

    public static Ingredient getOnion() {
        return Onion;
    }

    public static Ingredient getPizzaSauce() {
        return PizzaSauce;
    }

    public static Ingredient getMozarilla() {
        return Mozarilla;
    }

    public static Ingredient getWater() {
        return Water;
    }

    public static Ingredient getOregano() {
        return Oregano;
    }

    public static Ingredient getFlour() {
        return Flour;
    }

    public double getOneUnitCalories() {
        return oneUnitCalories;
    }

    public double getOneUnitWeight() {
        return oneUnitWeight;
    }

    public String getName() {
        return name;
    }

    public String getUnitName() {
        return unitName;
    }

    public double getAmount() {
        return amount;
    }

    public double getWeight() {
        return weight;
    }

    public double getCalories() {
        return calories;
    }

    public void setAmount(double amount, boolean overwrite) {
        if (overwrite) {
            this.amount = amount;
        } else {
            this.amount += amount;
        }
        calories = calories + amount * oneUnitCalories;
        weight = weight + amount * oneUnitWeight;
       
    }

    public void setAdded(boolean isAdded) {
        this.isAdded = isAdded;
    }

    public boolean isAdded() {
        return isAdded;
    }

}
