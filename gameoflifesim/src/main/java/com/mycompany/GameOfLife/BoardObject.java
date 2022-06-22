/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.GameOfLife;


import com.mycompany.GameOfLife.UtilityClasses.StringMaster;
import com.mycompany.mavenproject1.DataTypes.RulesBundle;
import java.util.Arrays;

/**
 *
 * @author evandleclair
 */
public class BoardObject {

    private int[][] boardState;
    private int previousState[][];
    private final int dimensions;
    private int starveNumber, aliveNumber, reviveNumber, overpopNumber;
    private int tickSpeed, currentGen=0;
    private String id;
    private RulesBundle myRules;
    private double probAlive;
    
    public BoardObject(int d)
    {
        dimensions=d;
    }
    public BoardObject(int d, int[][] BoardState, RulesBundle Rules, int TickSpeed, int CurrentGen)
    {
        dimensions=d;
        boardState=BoardState;
        myRules=Rules;
        tickSpeed=TickSpeed;
        currentGen=CurrentGen;
        starveNumber=myRules.getStarveNumber();
        aliveNumber=myRules.getAliveNumber();
        reviveNumber=myRules.getReviveNumber();
        overpopNumber=myRules.getOverpopNumber();
        //System.out.println("Reporting as " + reportBoard());
    }
    public void setupBoard(double ProbAlive, RulesBundle myRules)
    {
        probAlive=ProbAlive;
        
        starveNumber=myRules.getStarveNumber();
        aliveNumber=myRules.getAliveNumber();
        reviveNumber=myRules.getReviveNumber();
        overpopNumber=myRules.getOverpopNumber();
        createSeedAndReportBoard();
    }
     public void setupBoard(double ProbAlive) //default method//
    {
        probAlive=ProbAlive;
        starveNumber=1;
        aliveNumber=2;
        reviveNumber=3;
        overpopNumber=4;
        createSeedAndReportBoard();
    }
   
    public void createSeedAndReportBoard()
    {
        boardState=deadState(dimensions,dimensions);
        seedBoard();
       
    }
    public int[][] deadState(int width, int height)
    {
        return new int[width][height];
    }
    
    public void clearBoard()
    {
        boardState=deadState(dimensions,dimensions);
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
                }//end else//
                else
                {
                   deadCount++; 
                }//end else//
            }//end foor loop
        }//end for loop//
        
        boolean returnVal=areWeAliveBasedOnNeighbors(liveCount,inBoard[r][c]);
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
    
    boolean areWeAliveBasedOnNeighbors(int liveN, int curVal)
    {

        if (liveN<=starveNumber) //if it is equal to or smaller than the starvation number/
        {
            return false;
        }
        else if (liveN==aliveNumber)
        {
            if (curVal!=0) //if we are not dead//
            {
                return true; //we stay alive//
            }
            else
            {
                return false; //we are dead and its not enough to revive us//
            }
        }
        else if (liveN==reviveNumber)
        {
            return true; //a three always means the cell is currently alive, even if it was dead//
        }
        else if (liveN>overpopNumber)
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
    }
     
    public String getOneLineBoardString()
    {
        StringBuilder sb = new StringBuilder();
        for (int[] boardState1 : boardState) 
        {
            for (int c = 0; c<boardState[0].length; c++) 
            {
                sb.append(boardState1[c]);
            }
        }
        return sb.toString();
    }//end getOneLineBoardString//
     
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
    
    public RulesBundle getMyRules()
    {
        return new RulesBundle(starveNumber,aliveNumber,reviveNumber,overpopNumber);
    }
    
    public int getDimensions()
    {
        return dimensions;
    }
    
    public int getCurrentGen()
    {
         return currentGen;
    }
    
    public void setCurrentGen(int cg)
    {
        currentGen=cg;
    }
    
    public int getTickSpeed()
    {
        return tickSpeed;
    }
    
    public void setTickSpeed(int ts)
    {
        tickSpeed=ts;
    }
    
    public String getName()
    {
        return id;
    }
    
    public void setName(String n)
    {
        id=n;
    }
    
    public void setCellAlive(int r, int c)
    {
        boardState[r][c]=1;
    }
}//end boardObject Class//