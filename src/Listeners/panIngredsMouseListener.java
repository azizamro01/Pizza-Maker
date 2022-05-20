/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Listeners;

import Exceptions.InsufficientIngredientAmountException;
import Frames.PanFrame;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import PizzaIngredients.Ingredient;
import Loggers.myLogger;

/**
 *
 * @author lenovo
 */
public class panIngredsMouseListener implements MouseListener {

    private PanFrame targetPanFrame;
    private Ingredient targetSelectedIngredient;
    private int targetExcept;
    private myLogger targetLogger;

    public panIngredsMouseListener(PanFrame targetPanFrame,
            Ingredient targetSelectedIngredient, int targetExcept, myLogger targetLogger) {
        this.targetPanFrame = targetPanFrame;
        this.targetSelectedIngredient = targetSelectedIngredient;
        this.targetExcept = targetExcept;
        this.targetLogger = targetLogger;

    }

    @Override
    public void mouseClicked(MouseEvent me) {
        for (KeyListener kl : targetPanFrame.getKeyListeners()) {
            targetPanFrame.removeKeyListener(kl);
        }
        targetPanFrame.getAmountSetter().setForeground(null);
        targetPanFrame.getAmountSetter().setText("");
        targetPanFrame.setLabelsColorsExcept(Color.RED, targetExcept);
        targetPanFrame.getAmountSetter().setVisible(true);
        targetPanFrame.requestFocus();
        targetPanFrame.addKeyListener(new KeyListener() {
            float amount = 0;

            @Override
            public void keyTyped(KeyEvent ke) {
            }

            @Override
            public void keyPressed(KeyEvent ke) {

                if (ke.getKeyCode() == 38) {

                    amount += 0.25;
                    targetPanFrame.getAmountSetter().setText(amount + " " + targetSelectedIngredient.getUnitName()
                            + " (" + targetSelectedIngredient.getOneUnitWeight() * amount + " grams)");
                    targetPanFrame.getActionInfo().setText("Recommended amount is : \n"
                            + targetSelectedIngredient.getRecommendedWeightPerOneSizeUnit()
                            * targetPanFrame.getPizzaPan().getSizeLevel()
                            / targetSelectedIngredient.getOneUnitWeight()
                            + " " + targetSelectedIngredient.getUnitName());

                }
                if (ke.getKeyCode() == 40) {
                    if (amount <= 0) {
                        return;
                    }
                    targetPanFrame.getActionInfo().setText("Recommended amount is : \n"
                            + targetSelectedIngredient.getRecommendedWeightPerOneSizeUnit()
                            * targetPanFrame.getPizzaPan().getSizeLevel()
                            / targetSelectedIngredient.getOneUnitWeight()
                            + " " + targetSelectedIngredient.getUnitName());
                    amount -= 0.25;
                    targetPanFrame.getAmountSetter().setText(amount + " " + targetSelectedIngredient.getUnitName());
                    targetPanFrame.getAmountSetter().setText(amount + " " + targetSelectedIngredient.getUnitName()
                            + " (" + targetSelectedIngredient.getOneUnitWeight() * amount + " grams)"
                    );

                }
                if (ke.getKeyCode() == 10) {
                    if (amount <= 0) {
                        targetPanFrame.getActionInfo().setText(amount + " amount ?? \n that doesn't make any sense");
                        return;
                    }

                    try {
                        targetPanFrame.getPizzaPan().addIngredient(targetSelectedIngredient, amount);

                    } catch (InsufficientIngredientAmountException ex) {
                        targetPanFrame.getChat().setText("there is no more ( the two of us ) \n"
                                + "in this world");
                        if (targetSelectedIngredient.getWeight()
                                > targetSelectedIngredient.getRecommendedWeightPerOneSizeUnit()) {

                            targetPanFrame.getActionInfo().setText("this amount will destroy \n the "
                                    + "pizza .. try less");

                        } else if (targetSelectedIngredient.getWeight()
                                < targetSelectedIngredient.getRecommendedWeightPerOneSizeUnit()) {

                            targetPanFrame.getActionInfo().setText("this amount will destroy \n the "
                                    + "pizza .. try more");

                        } else {
                            targetPanFrame.getActionInfo().setText("You've added enough");
                        }

                        return;
                    }
                    targetPanFrame.setLabelsColorsExcept(Color.GREEN, targetExcept);
                    targetPanFrame.getAmountSetter().setForeground(Color.BLUE);
                    targetPanFrame.getAmountSetter().setText("Amount Added");
                    String message = "you added " + amount + " "
                            + targetSelectedIngredient.getUnitName()
                            + " of " + targetSelectedIngredient.getClass().getSimpleName() + " \nto the pan..";

                    targetPanFrame.getActionInfo().setText(message);
                    targetLogger.log(message);
                    targetPanFrame.getMainPan().setText(targetPanFrame.getPizzaPan().getInfo());

                }
            }

            @Override
            public void keyReleased(KeyEvent ke) {
            }
        });
    }

    @Override

    public void mousePressed(MouseEvent me) {
    }

    @Override
    public void mouseReleased(MouseEvent me) {
    }

    @Override
    public void mouseEntered(MouseEvent me) {
    }

    @Override
    public void mouseExited(MouseEvent me) {
    }

}
