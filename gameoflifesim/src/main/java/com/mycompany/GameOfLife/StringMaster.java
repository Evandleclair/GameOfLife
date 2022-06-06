/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.GameOfLife;

import java.awt.Font;
import java.util.*;


/**
 *
 * @author toast
 */
public class StringMaster {
    public static final Map<Integer, String> digitCharacter = new HashMap<>();
    static {
        //▯◼◻▮ left these here for easier access incase I change my mind about the character used//
      digitCharacter.put(0, "▯");
      digitCharacter.put(1, "▮");
    }
    public static String arrayToString(int[][] inArray )
    {
        StringBuilder sb = new StringBuilder();
        String retString = "";
       for (int h=0; h<inArray.length ; h++) 
       {
            for (int w=0; w<inArray[0].length; w++)
            {
                sb.append(digitCharacter.get(inArray[h][w]));
            }
            if (h!=inArray.length-1)
            {
                sb.append("\r\n");
            }
       }
    retString= sb.toString();
    return retString;
    }
    
    public static int[][] importFileToBoard()
    {
        return new int[1][1];
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
    //StringMaster will hold onto the default font used by all boards.//
    public static Font getGlobalFont()
    {
        return new Font(Font.MONOSPACED, Font.PLAIN, 12);
    }
}
