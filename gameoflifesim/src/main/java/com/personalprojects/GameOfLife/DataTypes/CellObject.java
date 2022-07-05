/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.personalprojects.GameOfLife.DataTypes;

import java.awt.Color;
import java.awt.Graphics;

/**
 * This object represents a single cell on our board. It has functions to be turned on and off, and to draw itself. 
 * @author evandleclair
 */
public class CellObject {
    private final int x;
    private final int y;
    private final int cellSize;
    private int cellState;
    private Color aliveColor, deadColor;
    private Color[] COLORS = new Color[2];
    
    /**
     * Constructor method that takes the cells location in the grid, and the size of the cell, as arguments. 
     * @param x the cell's x coordinate (its column)
     * @param y the cell's y coordinate (its row)
     * @param size the size of the cell in pixels
     */
    public CellObject(int x, int y, int size) 
    {
        this.x = x;
        this.y = y;
        this.aliveColor=Color.BLACK;
        this.deadColor=Color.WHITE;
        this.cellSize = size;
        this.cellState = 0; //zero means that a cell is dead//
        COLORS[0]=deadColor; //colors are numerically matched to the cell state. 0 is dead, 1 is alive.//
        COLORS[1]=aliveColor;
    } //end CellObject//
    
    /** 
     * Alternate constructor that allows for custom colors. 
     * @param x the cell's x coordinate (its column)
     * @param y the cell's y coordinate (its row)
     * @param size the size of the cell in pixels
     * @param AliveColor the color to be assigned to 1 aka the alive state
     * @param DeadColor the color to be assigned to 0 aka the dead state. 
     */
    public CellObject(int x, int y, int size, Color AliveColor, Color DeadColor) 
    {
        this.x = x;
        this.y = y;
        this.aliveColor=AliveColor;
        this.deadColor=DeadColor;
        this.cellSize = size;
        this.cellState = 0;
        COLORS[0]=deadColor;
        COLORS[1]=aliveColor;
    }//end CellObject//
     
    /**
     * Returns whether the cell is dead or not
     * @return A Boolean representing if the cell is dead
     */
    public boolean isDead() 
    {
        return cellState == 0;
    }

    /**
     * Returns whether the cell is alive or not. 
     * @return a Boolean representing if the cell is alive. 
     */
    public boolean isAlive() 
    {
        return cellState == 1; 
    }
    
    /**
     * Sets the cell state to zero, killing it. 
     */
    public void turnOff() 
    {
        cellState = 0;
    }
    
    /**
    *Sets the cell state to 1, aka alive.
    */
    public void turnOn() 
    {
        cellState = 1;
    }
    
    /**
     * Gets the cells current state as an int. 
     * @return
     */
    public int getState()
    {
        System.out.println("self reported x is " + x + "   y " + y);
        return cellState;
    }

    /**
     * Cells manage drawing themselves through this method.
     * @param g
     */
    public void draw(Graphics g) 
    {
        g.setColor(COLORS[cellState]);
        g.fillRect(x + 1, y + 1, cellSize - 1, cellSize - 1);
        g.setColor(Color.LIGHT_GRAY);
        g.drawRect(x, y, cellSize, cellSize);
    }//end draw//
}//end class..
