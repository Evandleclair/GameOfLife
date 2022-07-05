/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.personalprojects.GameOfLife.UtilityClasses;




/**
 * A utility class to allow easy access to string manipulations used multiple times throughout the program//
 * @author evandleclair
 */
public class StringMaster {

    /**
     * Takes a matrix and turns it into an easy to read multi line string. Mostly for internal and debugging use. 
     * @param inMatrix
     * @return
     */
    public static String matrixToString(int[][] inMatrix )
    {
        StringBuilder sb = new StringBuilder();
        String retString;
        for (int h=0; h<inMatrix.length ; h++) 
        {
            for (int w=0; w<inMatrix[0].length; w++)
            {
                sb.append(String.valueOf(inMatrix[h][w]));
            }
            if (h!=inMatrix.length-1)
            {
                sb.append("\r\n");
            }
        }
    retString= sb.toString();
    return retString;
    }
    
    /**
     * Uses a string builder to combine a 1d array of strings into a single string. 
     * @param inStrings
     * @return a single string created out of the array of strings given 
     */
    public static String combineStrings(String[] inStrings)
    {
         StringBuilder str
            = new StringBuilder();
         for (String s : inStrings)
         {
             str.append(s);
         }
         return str.toString();
    }//end combineStrings
}//end class//
