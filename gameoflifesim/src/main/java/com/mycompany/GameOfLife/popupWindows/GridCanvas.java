/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.GameOfLife.popupWindows;

import com.mycompany.mavenproject1.DataTypes.CellObject;
import java.awt.Canvas;
import java.awt.Graphics;

/**
 *
 * @author 12035
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
         for (int r = 0; r < dimensions; r++) {
            int y = r * cellSize;
            for (int c = 0; c < dimensions; c++) {
                int x = c * cellSize;
                cellArray[r][c] = new CellObject(x, y, cellSize);
            }
        }
    }
    
    public void draw(Graphics g) 
    {
        for (CellObject[] row : cellArray) 
        {
            for (CellObject cell : row) 
            {
                cell.draw(g);
            }
        }
    }
    
    @Override
    public void paint(Graphics g) 
    {
        draw(g);
    }
    
    public int getCellSize()
    {
        return cellSize;
    }
    
    public int getSizeScale()
    {
        //return getSize().height;
        return dimensions*(cellSize); //the plus 2 is to account for border size
    }
    
    public void setCellState(int r, int c, int state)
    {
        if (state==1)
            cellArray[r][c].turnOn();
        else
            cellArray[r][c].turnOff();
    }
    
    public void userToggleCell(int r, int c)
    {
        cellArray[r][c].turnOn();
        draw(this.getGraphics());
    }
    
    public void setCellsEqualToBoardState(int[][] boardState)
    {
       for (int r=0; r<boardState.length ; r++) 
       {
            for (int c=0; c<boardState[0].length; c++)
            {
                setCellState(r,c,boardState[r][c]);
            }
       } 
    }
    
    public CellObject getCell(int r, int c)
    {
        return cellArray[r][c];
    }
   // public Dimension getPreferredSize() 
}//end draw//
