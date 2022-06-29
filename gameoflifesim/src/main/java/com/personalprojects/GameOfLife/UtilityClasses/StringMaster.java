/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.personalprojects.GameOfLife.UtilityClasses;




/**
 *
 * @author evandleclair
 */
public class StringMaster {

    public static String arrayToString(int[][] inArray )
    {
        StringBuilder sb = new StringBuilder();
        String retString;
        for (int h=0; h<inArray.length ; h++) 
        {
            for (int w=0; w<inArray[0].length; w++)
            {
                sb.append(String.valueOf(inArray[h][w]));
            }
            if (h!=inArray.length-1)
            {
                sb.append("\r\n");
            }
        }
    retString= sb.toString();
    return retString;
    }
    
    public static String combineStrings(String[] inStrings)
    {
         StringBuilder str
            = new StringBuilder();
         for (String s : inStrings)
         {
             str.append(s);
         }
         return str.toString();
    }
}
