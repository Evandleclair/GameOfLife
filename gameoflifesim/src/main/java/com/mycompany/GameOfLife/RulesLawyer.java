/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.GameOfLife;

/**
 *
 * @author toast
 */
public class RulesLawyer  {
    private static int starveNumber=1; //the number of neighbors that is not enough to sustain a cell but is not zero//
    private static int stayAliveNumber=2; //the number of neighbors that is enough to sustain a cell but not revive a dead cell//
    private static int reviveNumber=3; //the number of neighbors that would revive a dead cell//
    private static int overPopulationNumber=4; //the number of neighbors that would kill a cell due to overpopulation//

    /**
     *
     * @return
     */
    public static int getStarveNumber() {
        return starveNumber;
    }

    public static int getStayAliveNumber() {
       return stayAliveNumber;
    }

    public static int getReviveNumber() {
       return reviveNumber;
    }

    public static int getOverpopulationNumber() {
       return overPopulationNumber;
    }

    public static void setStarveNumber(int n) {
      starveNumber=n;
    }

    public static void setStayAliveNumber(int n) {
       stayAliveNumber=n;
    }

    public static void setReviveNumber(int n) {
        reviveNumber=n;
    }

    public static void setOverpopulationNumber(int n) {
       overPopulationNumber=n;
    }
    
   
}
