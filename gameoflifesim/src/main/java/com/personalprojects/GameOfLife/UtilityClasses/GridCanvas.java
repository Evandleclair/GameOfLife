/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.personalprojects.GameOfLife.UtilityClasses;

import com.personalprojects.GameOfLife.DataTypes.CellObject;
import java.awt.Canvas;
import java.awt.Graphics;

/**
 * A canvas class that holds and draws cell objects. It has no part in calculation; it is told what to draw by the SimCanvasWindow it belongs to. 
 * @author evandleclair
 */
public class GridCanvas extends Canvas 
{
    private CellObject[][] cellArray;
    private int cellSize, dimensions;
    
    /**
     * Constructor method that takes the dimensions of the board and the size of the cell so it knows how big the canvas should be. 
     * @param Dimensions
     * @param CellSize
     */
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
                cellArray[r][c] = new CellObject(x, y, cellSize); //create our matrix of cell objects//
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
    
    /**
     * Returns the actual size of the canvas, calculated by multiplying the number of cells on one side (The canvas is square) by their size in pixels.
     * @return An int representing the size of the board. 
     */
    public int getCanvasSize()
    {
        return dimensions*(cellSize); 
    }//end getCanvasSize//
    
    /**
     * Access a specific cell by row and column value, and alters its state to whatever is passed as the third argument
     * @param r the row the cell is in
     * @param c the column the cell is in
     * @param state the state to set the cell to. 
     */
    public void setCellState(int r, int c, int state)
    {
        if (state==1)
            cellArray[r][c].turnOn();
        else
            cellArray[r][c].turnOff();
    }//end setCellState//
    
    /**
     * Invoked when the user manually turns on a cell to ensure the board gets updated even if the simulation is not ticking//
     * @param r the row of the cell to activate
     * @param c the column of the cell to activate. 
     */
    public void userToggleCell(int r, int c)
    {
        cellArray[r][c].turnOn(); 
        draw(this.getGraphics());
    } //end userToggleCell//
    
    /**
     * Takes a matrix from the actually SimCanvasWindow, and sets its own cells to match the values in that argument.
     * @param boardState a matrix of integers representing the current state of the board.
     */
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
    
    /**
     * Returns the cell at the specific coordinates. 
     * @param r the row of the cell we want
     * @param c the column of the cell we want
     * @return a variable of type CellObject, the specific cell we wanted to retreive. 
     */
    public CellObject getCell(int r, int c)
    {
        return cellArray[r][c];
    }//end getCell
   // public Dimension getPreferredSize() 
}//end draw//
