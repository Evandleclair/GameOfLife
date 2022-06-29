/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.personalprojects.GameOfLife.UtilityClasses;

import com.personalprojects.GameOfLife.DataTypes.CellObject;
import java.awt.Canvas;
import java.awt.Graphics;

/**
 *
 * @author evandleclair
 */
public class GridCanvas extends Canvas 
{
    private CellObject[][] cellArray;
    private int cellSize, dimensions;
    
    public GridCanvas(int Dimensions, int CellSize)
    {
        cellSize=CellSize;
        dimensions=Dimensions;
        cellArray = new CellObject[dimensions][dimensions];
        for (int r = 0; r < dimensions; r++) 
        {
            int y = r * cellSize;
            for (int c = 0; c < dimensions; c++) 
            {
                int x = c * cellSize;
                cellArray[r][c] = new CellObject(x, y, cellSize);
            }
        }
    }//end GridCanvas//
    
    public void draw(Graphics g) 
    {
        for (CellObject[] row : cellArray) 
        {
            for (CellObject cell : row) 
            {
                cell.draw(g);
            }
        }
    }//end draw//
    
    @Override
    public void paint(Graphics g) 
    {
        draw(g);
    }//end paint//
    
    public int getCellSize()
    {
        return cellSize;
    }//end getCellSize//
    
    public int getSizeScale()
    {
        return dimensions*(cellSize); //the plus 2 is to account for border size
    }//end getSizeScale//
    
    public void setCellState(int r, int c, int state)
    {
        if (state==1)
            cellArray[r][c].turnOn();
        else
            cellArray[r][c].turnOff();
    }//end setCellState//
    
    public void userToggleCell(int r, int c)
    {
        cellArray[r][c].turnOn();
        draw(this.getGraphics());
    } //end userToggleCell//
    
    public void setCellsEqualToBoardState(int[][] boardState)
    {
       for (int r=0; r<boardState.length ; r++) 
       {
            for (int c=0; c<boardState[0].length; c++)
            {
                setCellState(r,c,boardState[r][c]);
            }
       } 
    }//end setCellsEqualToBoardState//
    
    public CellObject getCell(int r, int c)
    {
        return cellArray[r][c];
    }//end getCell
   // public Dimension getPreferredSize() 
}//end draw//
