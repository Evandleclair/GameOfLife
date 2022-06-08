/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject1.DataTypes;

/**
 *
 * @author toast
 */
public class BoardDataBundle {
    

    private String id;
    private int starveNumber, aliveNumber, reviveNumber, overpopNumber, tickSpeed, dimensions, currentGen;
    private int[][] boardState;
    
    public int[][] getBoardState(){return boardState;}
    public void saveBoardInfo(int[][] BoardState, int CurrentGen, int TickSpeed){boardState=BoardState; currentGen=CurrentGen; tickSpeed=TickSpeed;}
    public void saveRules(int[] rules){}
    public int[] getRulesValues(){return new int[]{starveNumber,aliveNumber,reviveNumber,overpopNumber};}
    public int getGeneration(){return currentGen;}
    public String getID(){ return id; }
    public void setID(String id){ this.id = id; }
}
