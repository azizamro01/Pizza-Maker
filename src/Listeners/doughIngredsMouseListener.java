/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Listeners;


import Frames.MixerFrame;
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
public class doughIngredsMouseListener implements MouseListener {

    private Ingredient targetSelectedIngredient;
    private int targetExcept;
    private MixerFrame targetBlender;
    private myLogger targetLogger;

    public doughIngredsMouseListener(Ingredient selectedIngredient, int targetExcept,
            MixerFrame targetBlender, myLogger targetLogger) {
        this.targetSelectedIngredient = selectedIngredient;
        this.targetExcept = targetExcept;
        this.targetBlender = targetBlender;
        this.targetLogger = targetLogger;
    }

    @Override
    public void mouseClicked(MouseEvent me) {
       /*
        to make sure that if a key listener was added for another ingredient , the amount to be set 
        by the current-needed ingredient will not be listened to by others 
        */
        for (KeyListener kl : targetBlender.getKeyListeners()) {
            targetBlender.removeKeyListener(kl);
        }
        /*start of filling the needed inforamtion about the action currently being performed*/
        targetBlender.getAmountSetter().setVisible(true);
        targetBlender.getAmountSetter().setForeground(null);
        targetBlender.getAmountSetter().setText("");
        targetBlender.getInfo().setText("you have selected " + targetSelectedIngredient.getName() + "\n"
                + "Recommended amount for the small size is : \n"
                + Math.round(targetSelectedIngredient.getRecommendedWeightPerOneSizeUnit()
                        / targetSelectedIngredient.getOneUnitWeight() * 100) / 100.0
                + " " + targetSelectedIngredient.getUnitName());
        targetBlender.setLabelsColorsExept(Color.RED, targetExcept);
        targetBlender.requestFocus();
        targetBlender.addKeyListener(new KeyListener() {
            /*
            now , if key up is pressed , the amount will increase
            if down , will decrease 
            if Enter ,will be finally added
            */
            float amount = 0;

            @Override
            public void keyTyped(KeyEvent ke) {

            }

            @Override
            public void keyPressed(KeyEvent ke) {
                /*key up*/
                if (ke.getKeyCode() ==38) {

                    amount += 0.25;
                    targetBlender.getAmountSetter().setText(amount + " " + targetSelectedIngredient.getUnitName()
                            + " (" + targetSelectedIngredient.getOneUnitWeight() * amount + " grams)");
                }
                /*key down*/
                if (ke.getKeyCode() == 40) {
                    if (amount <= 0) {
                        return;
                    }
                    amount -= 0.25;
                    targetBlender.getAmountSetter().setText(amount + " " + targetSelectedIngredient.getUnitName()
                            + " (" + targetSelectedIngredient.getOneUnitWeight() * amount + " grams)");
                }
                /*Enter*/
                if (ke.getKeyCode() == 10) {
                    if (amount <= 0) {
                        targetBlender.getInfo().setText(amount + " amount ?? \n "
                                + "that doesn't make any sense");
                        return;
                    }
                    targetBlender.getChat().setText("Ok,Good !!");
                    targetBlender.getPizzaDough().addIngredient(targetSelectedIngredient, amount);
                    targetBlender.setLabelsColorsExept(Color.GREEN, targetExcept);
                    targetBlender.getAmountSetter().setForeground(Color.BLUE);
                    targetBlender.getAmountSetter().setText("Amount Added");
                    String message = amount + " " + targetSelectedIngredient.
                            getUnitName() + " of "
                            + targetSelectedIngredient.getName()
                            + " has been added to the dough";
                    targetBlender.getInfo().setText(message);
                    targetLogger.log(message);
                    targetBlender.getMixer().setText(targetBlender.getPizzaDough().getInfoV2());

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
