/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.personalprojects.GameOfLife.DataTypes;

/**
 * Created so that the rules could be passed between games as a package instead of individual variables.
 * @author evandleclair
 */
public class RulesBundle {
    private int starveNumber, aliveNumber, reviveNumber, overpopNumber;
    
    /**
     * Constructor method. Creates a new rules bundle with the arguments as the rule values//
     * @param StarveNumber means if a cell has this many or fewer neighbors, the cell dies
     * @param AliveNumber  means if we a cell has this many or more neighbors, the cell stays alive.
     * @param ReviveNumber  means if a dead cell has this many neighbors, it comes back to life.//
     * @param OverpopNumber means if a cell has this many or more neighbors, it dies.//
     */
    public RulesBundle(int StarveNumber, int AliveNumber, int ReviveNumber, int OverpopNumber)
    {
        starveNumber=StarveNumber;
        aliveNumber=AliveNumber;
        reviveNumber=ReviveNumber;
        overpopNumber=OverpopNumber;
    }
    
    /**
     * Takes a 1d array of integers and turns them into a rule set. 
     * @param ruleSet
     */
    public RulesBundle(int[] ruleSet)
    {
        starveNumber=ruleSet[0];
        aliveNumber=ruleSet[1];
        reviveNumber=ruleSet[2];
        overpopNumber=ruleSet[3];
    }
    
    /**
     * Default constructor. All rules set to zero. 
     */
    public RulesBundle()
    {
        starveNumber=0;
        aliveNumber=0;
        reviveNumber=0;
        overpopNumber=0;
    }
    
    /**
     * Returns a 1d array of ints that contains our rules set. 
     * @return
     */
    public int[] getRulesAsArray()
    {
        return new int[]{starveNumber,aliveNumber,reviveNumber,overpopNumber};
    }
     
    /**
     * Overwrites rules with arguments.
     * @param StarveNumber
     * @param AliveNumber
     * @param ReviveNumber
     * @param OverpopNumber
     */
    public void rewriteRules(int StarveNumber, int AliveNumber, int ReviveNumber, int OverpopNumber)
    {
        starveNumber=StarveNumber;
        aliveNumber=AliveNumber;
        reviveNumber=ReviveNumber;
        overpopNumber=OverpopNumber;
    }
    
    /* get and set methods follow */
    
    public int getStarveNumber()
    {
        return  starveNumber;
    }
    
    public int getAliveNumber()
    {
        return aliveNumber;
    }
    
    public int getReviveNumber()
    {
        return reviveNumber;
    }
    
    public int getOverpopNumber()
    {
        return overpopNumber;
    }
}//end RulesBundle class//
