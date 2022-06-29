/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.personalprojects.GameOfLife.DataTypes;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author evandleclair
 */
public class CellObject {
    private final int x;
    private final int y;
    private final int cellSize;
    private int cellState;
    private Color aliveColor, deadColor;
    private Color[] COLORS = new Color[2];
    
    public CellObject(int x, int y, int size) 
    {
        this.x = x;
        this.y = y;
        this.aliveColor=Color.BLACK;
        this.deadColor=Color.WHITE;
        this.cellSize = size;
        this.cellState = 0;
        COLORS[0]=deadColor;
        COLORS[1]=aliveColor;
    }
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
    }
    public boolean isDead() 
    {
        return cellState == 0;
    }

    public boolean isAlive() 
    {
        return cellState == 1;
    }
    public void turnOff() 
    {
        cellState = 0;
    }
    
    public int getState()
    {
        System.out.println("self reported x is " + x + "   y " + y);
        return cellState;
    }

    public void turnOn() 
    {
        cellState = 1;
    }
    public void draw(Graphics g) 
    {
        g.setColor(COLORS[cellState]);
        g.fillRect(x + 1, y + 1, cellSize - 1, cellSize - 1);
        g.setColor(Color.LIGHT_GRAY);
        g.drawRect(x, y, cellSize, cellSize);
    }//end draw//
    
}

