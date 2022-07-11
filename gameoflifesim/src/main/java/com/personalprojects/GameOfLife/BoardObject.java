/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.personalprojects.GameOfLife;


import com.personalprojects.GameOfLife.UtilityClasses.StringMaster;
import com.personalprojects.GameOfLife.DataTypes.RulesBundle;
import java.util.Arrays;

/**
 * Board objects encapsulate the data needed for the board itself,as well as the functions required to alter it's state. 
 * It does not handle these actions internally, those are done by a separate thread. It also does not draw itself, that too is handled elsewhere. 
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
    
    /*
    * This class has two constructors. The first is used when we only know the dimensions 
    * (creating a board from scratch) while the second is for creating a board from known values
    */
    
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

    /**
     * The setupBoard function takes the probability of cells starting the game alive, and uses that number to seed a new board.
     * it also has an alternate form for importing rules, if we decide to use custom rule sets. If we do not import any rules, 
     * it will use the default rules of 
     * Conway's Game of Life
     * @param ProbAlive the initial probability of a cell being alive on generation zero
     * @param myRules the imported rule package/
     */

    public void setupBoard(double ProbAlive, RulesBundle myRules)
    {
        probAlive=ProbAlive;
        
        starveNumber=myRules.getStarveNumber();
        aliveNumber=myRules.getAliveNumber();
        reviveNumber=myRules.getReviveNumber();
        overpopNumber=myRules.getOverpopNumber();
        createSeedAndReportBoard();
    }
    
    /**
     * This is the alternate version of setupBoard that uses the default game of life rules as laid out by Conway. 
     * @param ProbAlive the initial probability of a cell being alive on generation zero
     */
    public void setupBoard(double ProbAlive) //default method//
    {
        probAlive=ProbAlive;
        starveNumber=1;
        aliveNumber=2;
        reviveNumber=3;
        overpopNumber=4;
        createSeedAndReportBoard();
    }
   
    /**
     * Creates a blank board, then invokes the seed board function//
     */
    public void createSeedAndReportBoard()
    {
        boardState=deadState(dimensions,dimensions);
        seedBoard();
       
    }
    
    /**
     * takes dimensions and returns a matrix of that size that is entirely 0. Used for initializing and clearing boards
     * @param width
     * @param height
     * @return
     */
    public int[][] deadState(int width, int height)
    {
        return new int[width][height];
    }
    
    /**
     *Uses the deadState function to clear the board
     */
    public void clearBoard()
    {
        boardState=deadState(dimensions,dimensions);
    }
    
    /**
     * Makes the board report it's current status as a string
     * @return returns the board as a string
     */
    public String reportBoard()
    {
        var retString ="";
        if (boardState!=null)
        {
            retString= StringMaster.matrixToString(boardState);
        }
        return retString;
    }
    
    /**
     * Using the alive probability passed in from the constructor, goes through each cell and decides if it should be alive for generation zero
     */
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
     
    /**
    *  Ticks the board forward by 1 generation. First it saves the previous state of the board, then invokes the method that
    * uses that state to calculate the new state
    */
    void boardTick()
    {
        previousState=boardState;
        boardState=calcNextState(previousState);
    }
    
     /**
     * Takes the previous state, and then goes through each cell, running calculations to see if it will be alive in the next generation. It then returns
     * the new board state.
     * @param prevState a matrix of integers, represents the board on the previous generation.
     * @return a matrix of integers representing the next generation of the board. 
     */
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
    
     /**
     * Takes the previous state, and then goes through each cell, running calculations to see if it will be alive in the next generation. It then returns
     * @param r the row of the cell to be checked
     * @param c the column of the cell to be checked
     * @param inBoard a matrix of integers. The current board state
     * @return a Boolean of if the cell will be alive or not next generation 
     */
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
    
    /**
     * Takes sees if a cell is within the bounds of the board, and if so, if it is alive.
     * @param r the row of the cell to be checked
     * @param c the column of the cell to be checked
     * @param inBoard a matrix of integers. The current board state
     * @return a Boolean of if the cell was within bounds and was alive.
     */
    private boolean isCellAliveAndValid(int r, int c, int[][] inBoard)
    {
        if (r >= 0 && c >= 0 && r < dimensions && c < dimensions )
        {
        //System.out.println("within bounds. coordinate at rc " + r + " " + c + " is "+inBoard[r][c]);
        }
        return (r >= 0 && c >= 0 && r < dimensions && c < dimensions && inBoard[r][c]==1);
    }
    
    
    /**
     * Using its current state and the amount of neighbors alive, returns a Boolean about whether a specific cell should be dead, alive, or revived 
     * next generation
     * @param liveN number of neighbors alive.
     * @param curVal if the cell is current a 0 (dead) or a 1 (alive)
     * @return a Boolean of if the cell will be alive or not next generation 
     */
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
    }//end areWeAliveBasedOnNeighbors//

    /**
     * A method used for testing. prints the matrix given to the console.
     * @param mat  the matrix to be printed
     */
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
    }//end printBoardStateToConsole
    
    
    /**
     * Used when saving the board as a GOL file. Takes the board and saves it as a single line string so it can be stored in a GOL file.
     * @return  a single line string that represents the entire board.
     */ 
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
     
    /*
     * Mostly self explanatory get and set methods follow:
     */
    
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
    
    public void setCellToggle(int r, int c)
    {
        if (boardState[r][c]==1)
            boardState[r][c]=0;
        else if (boardState[r][c]==0)
            boardState[r][c]=1;
    }
}//end boardObject Class//