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
    private int cellSize;
    public GridCanvas(int dimensions, int CellSize)
    {
        cellSize=CellSize;
        cellArray = new CellObject[dimensions][dimensions];
         for (int r = 0; r < dimensions; r++) {
            int y = r * dimensions;
            for (int c = 0; c < dimensions; c++) {
                int x = c * dimensions;
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
    public void paint(Graphics g) 
    {
        draw(g);
    }
    public void setCellState(int c, int r, int state)
    {
        if (state==1)
            cellArray[c][r].turnOn();
        else
            cellArray[c][r].turnOff();
    }
    
    public void setCellsEqualToBoardState(int[][] boardState)
    {
       for (int r=0; r<boardState.length ; r++) 
       {
            for (int c=0; c<boardState[0].length; c++)
            {
                setCellState(c,r,boardState[c][r]);
            }
       } 
    }
}//end draw//
