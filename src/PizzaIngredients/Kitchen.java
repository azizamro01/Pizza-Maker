/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PizzaIngredients;

import Loggers.myLogger;
import Listeners.doughIngredsMouseListener;
import Listeners.panIngredsMouseListener;
import Exceptions.InsufficeintSizeException;
import Frames.MixerFrame;
import Frames.Pizza;
import Frames.PanFrame;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

/**
 *
 * @author lenovo
 */
public class Kitchen {

    private MixerFrame blender = new MixerFrame();
    private PanFrame pan = new PanFrame();
    private Pizza finalPizza = new Pizza();
    private myLogger logger;

    public Kitchen(myLogger logger) {
        this.logger = logger;

    }

    private void bringPan() {
        pan.setVisible(true);
        pan.getActionInfo().setText("Choose your pan size");
        pan.requestFocus();
        pan.getActionInfo().setForeground(Color.BLACK);
        pan.getSmall().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                pan.getPizzaPan().setSize(1);
                logger.log(pan.getPizzaPan().getSize() + " pan has been brough");
                pourDough();
            }
        });
        pan.getMedium().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                pan.getPizzaPan().setSize(2);
                logger.log(pan.getPizzaPan().getSize() + " pan has been brough");

                pourDough();
            }
        });
        pan.getLarge().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                pan.getPizzaPan().setSize(3);
                logger.log(pan.getPizzaPan().getSize() + " pan has been brough");
                pourDough();
            }
        });
    }

    public void prepareDough() {
        /*first process of making pizza is to prepare the dough*/
        blender.setVisible(true);
        /* declare listners for the labels , listeners here is like simulating the real-life physics*/
        doughIngredsMouseListener flourListener = new doughIngredsMouseListener(Ingredient.Flour,
                0, blender, logger);
        doughIngredsMouseListener yeastListener = new doughIngredsMouseListener(Ingredient.Yeast,
                1, blender, logger);
        doughIngredsMouseListener waterListener = new doughIngredsMouseListener(Ingredient.Water,
                2, blender, logger);
        doughIngredsMouseListener sugarListener = new doughIngredsMouseListener(Ingredient.Sugar,
                3, blender, logger);
        doughIngredsMouseListener saltListener = new doughIngredsMouseListener(Ingredient.Salt,
                4, blender, logger);
        doughIngredsMouseListener oilListener = new doughIngredsMouseListener(Ingredient.Oil,
                5, blender, logger);
        /* end of declaration */
 /* adding listeners to the labels*/
        blender.getFlour().addMouseListener(flourListener);
        blender.getYeast().addMouseListener(yeastListener);
        blender.getWater().addMouseListener(waterListener);
        blender.getSugar().addMouseListener(sugarListener);
        blender.getSalt().addMouseListener(saltListener);
        blender.getOil().addMouseListener(oilListener);
        blender.getMix().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (blender.getPizzaDough().getDoughIngredients().size() < 5) {
                    blender.getInfo().setText("you must have missed something");
                    blender.getChat().setText("I kneeeeew it ! do your homeworks not pizzas..press Stop now");

                } else {
                    bringPan();
                }
            }
        });
        blender.getStop().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                blender.getChat().setText("lol ,yeah . That thing doesn't do anything..proceed");
            }
        });
    }

    private void pourDough() {
        try {

            pan.getPizzaPan().pourPizzaDough(blender.getPizzaDough());
            pan.getMainPan().setBackground(Color.ORANGE);
            pan.getMainPan().setBorder(BorderFactory.createTitledBorder(pan.getPizzaPan().getSize()
                    + " pan"));
            pan.getActionInfo().setText("you added the dough to the pan"
                    + "\nnow customize your pizza");
            logger.log("the dough has been added to the pan");
            addPizzaItemsToThePan();
        } catch (InsufficeintSizeException ex) {
            pan.getChat().setText("I'm out of here !!!");
            pan.getActionInfo().setForeground(Color.RED);
            pan.getActionInfo().setText("choose a bigger pan");
            bringPan();
        }
    }

    private void addPizzaItemsToThePan() {

        pan.setVisible(true);
        pan.setFocusable(true);
        pan.requestFocus();
        panIngredsMouseListener tomato = new panIngredsMouseListener(pan, Ingredient.Tomato, 0, logger);
        panIngredsMouseListener sauce = new panIngredsMouseListener(pan, Ingredient.PizzaSauce, 1, logger);
        panIngredsMouseListener oregano = new panIngredsMouseListener(pan, Ingredient.Oregano, 2, logger);
        panIngredsMouseListener garlic = new panIngredsMouseListener(pan, Ingredient.Garlic, 3, logger);
        panIngredsMouseListener mozarilla = new panIngredsMouseListener(pan, Ingredient.Mozarilla, 4, logger);
        panIngredsMouseListener onion = new panIngredsMouseListener(pan, Ingredient.Onion, 5, logger);
        pan.getTomato().addMouseListener(tomato);
        pan.getSauce().addMouseListener(sauce);
        pan.getOrigano().addMouseListener(oregano);
        pan.getMozarilla().addMouseListener(mozarilla);
        pan.getOnion().addMouseListener(onion);
        pan.getGarlic().addMouseListener(garlic);
        pan.getBake().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                eatPizza();
            }

        });

    }

    private void eatPizza() {
        finalPizza.setVisible(true);
        finalPizza.getFiveSlices().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                finalPizza.getPlate().setListData(pan.getPizzaPan().cutSlices(5));
                logger.log("Pizza has been cut into 5 slices");
            }
        });
        finalPizza.getEightSlices().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                finalPizza.getPlate().setListData(pan.getPizzaPan().cutSlices(8));
                logger.log("Pizza has been cut into 8 slices");

            }
        });
        finalPizza.getTwelveSlices().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                finalPizza.getPlate().setListData(pan.getPizzaPan().cutSlices(12));
                logger.log("Pizza has been cut into 12 slices");

            }
        });
    }

}
