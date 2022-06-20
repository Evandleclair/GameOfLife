/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject1.DataTypes;

/**
 *
 * @author toast
 */
public class RulesBundle {
    private int starveNumber, aliveNumber, reviveNumber, overpopNumber;
    
    public RulesBundle(int StarveNumber, int AliveNumber, int ReviveNumber, int OverpopNumber)
    {
        starveNumber=StarveNumber;
        aliveNumber=AliveNumber;
        reviveNumber=ReviveNumber;
        overpopNumber=OverpopNumber;
    }
    
    public RulesBundle(int[] ruleSet)
    {
        starveNumber=ruleSet[0];
        aliveNumber=ruleSet[1];
        reviveNumber=ruleSet[2];
        overpopNumber=ruleSet[3];
    }
    
    public RulesBundle()
    {
        starveNumber=0;
        aliveNumber=0;
        reviveNumber=0;
        overpopNumber=0;
    }
    
    public int[] getRules()
    {
        return new int[]{starveNumber,aliveNumber,reviveNumber,overpopNumber};
    }
      
    public void rewriteRules(int StarveNumber, int AliveNumber, int ReviveNumber, int OverpopNumber)
    {
        starveNumber=StarveNumber;
        aliveNumber=AliveNumber;
        reviveNumber=ReviveNumber;
        overpopNumber=OverpopNumber;
    }
    
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
