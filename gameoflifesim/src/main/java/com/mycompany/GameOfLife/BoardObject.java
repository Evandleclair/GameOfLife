/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.GameOfLife;

import java.util.Arrays;



/**
 *
 * @author toast
 */
public class BoardObject extends CellAutomaton{

    private int[][] boardState;
    private int previousState[][];
    private int dimensions;
    private double probAlive;
    
    //public BoardObject(int d, double ProbAlive)
    public BoardObject(int d)
    {
        
        
        dimensions=d;
    }
    public void setupBoard(double ProbAlive)
    {
        probAlive=ProbAlive;
        boardState=deadState(dimensions,dimensions);
        seedBoard();
        reportBoard();
    }
    public void setupBoard(int[][] importedBoard)
    {
        boardState=importedBoard;
        reportBoard();
    }
    public int[][] deadState(int width, int height)
    {
        return new int[width][height];
    }
    public String reportBoard()
    {
        var retString ="";
        if (boardState!=null)
        {
            retString= StringMaster.arrayToString(boardState);
        }
        return retString;
    }
    public void seedBoard()
    {
        double randstate;
        for(int r=0;r<dimensions;r++)
        {
        for(int c=0;c<dimensions;c++)
            {
                randstate = Math.random();
                if (randstate<probAlive)
                {
                    boardState[r][c]=1;
                }
            }//end for columns loop//
        }//end for rows//
    }//end seedboard//
    @Override
     public boolean rules(int num)
    {
        return num == 3 || num == 12 || num == 13;
    }
     
    /*
     This method contains everything we need to tick the board forward by 1. 
     */
    void boardTick()
    {
        previousState=boardState;
        boardState=calcNextState(boardState);
    }
    int[][] calcNextState(int[][] prevState)
    {
       int[][] nextState = deadState(dimensions,dimensions);
       for (int r=0; r<prevState.length ; r++) 
       {
            for (int c=0; c<prevState[0].length; c++)
            {
                nextState[r][c]=  enoughNeighborsAlive(r,c, prevState) ? 1 : 0;
            }
       }
       printBoardStateToConsole(prevState);
       //System.out.println("-----");
       printBoardStateToConsole(nextState);
       return nextState;
    }
    
    boolean enoughNeighborsAlive(int r, int c, int[][] inBoard)
    {
        
        int liveCount=0, deadCount=0;
        for (int colOffset=-1; colOffset<=1;colOffset++)
        {
            for (int rowOffset=-1; rowOffset<=1;rowOffset++)
            {
                if ((colOffset!=0||rowOffset!=0)&&isCellAliveAndValid(r+rowOffset,c+colOffset, inBoard))
                {
                    liveCount++;
                }
                else
                {
                   deadCount++; 
                }
            }
        }
        
        boolean returnVal=rulesOfNature(liveCount,inBoard[r][c]);
        //System.out.println("row " + r + " col " + c + " value is " + inBoard[r][c] + " and it has " + liveCount + "living neighbors and alive is " + returnVal);
        return returnVal;
    }
    
    private boolean isCellAliveAndValid(int r, int c, int[][] inBoard)
    {
        
        if (r >= 0 && c >= 0 && r < dimensions && c < dimensions )
        {
        //System.out.println("within bounds. coordinate at rc " + r + " " + c + " is "+inBoard[r][c]);
        }
        return (r >= 0 && c >= 0 && r < dimensions && c < dimensions && inBoard[r][c]==1);
    }
    
     boolean rulesOfNature(int liveN, int curVal)
    {

        if (liveN<2)
        {
            return false;
        }
        else if (liveN==2)
        {
            if (curVal==0)
            {
            return false;
            }
            else
            {
            return true; //a two cannot revive a dead cell/
            }
        }
        else if (liveN==3)
        {
            return true; //a three always means the cell is currently alive, even if it was dead//
        }
        else if (liveN>3)
        {
            return false;
        }
        return false;
    }
     
     private void printBoardStateToConsole(int[][] mat)
     {
         StringBuilder sb = new StringBuilder();
           for (int r=0; r<mat.length ; r++) 
           {
            for (int c=0; c<mat[0].length; c++)
            {
               sb.append(mat[r][c]);
            }
            sb.append("\r\n");
            }
           //System.out.print(sb.toString());
     }
     public int[][] getBoardState()
     {
         return boardState;
     }
     void setBoardState(int[][] ia)
     {
          boardState=ia;
     }
      public int[][] getPreviousState()
     {
         return previousState;
     }
     void setPreviousState(int[][] ia)
     {
          previousState=ia;
     }
}//end boardmaster//
