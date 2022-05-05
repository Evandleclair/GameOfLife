/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.mavenproject1;

import java.util.Arrays;

/**
 *
 * @author toast
 */
public class BoardMaster extends CellAutomaton{

    int boardstate[][];
    int dimensions;
    double probAlive=0.5;
    
    public BoardMaster(int d)
    {
        //boardstate = new int[d][d];
        
        dimensions=d;
    }
    public void setupBoard()
    {
        deadState(dimensions,dimensions);
        seedBoard();
        reportBoard();
    }
    public void deadState(int width, int height)
    {
        boardstate= new int[width][height];
    }
    public void reportBoard()
    {
        if (boardstate!=null)
        {
            System.out.println(Arrays.deepToString(boardstate));
        }
    }
    public void seedBoard()
    {
        double randstate;
        for(int r=0;r<dimensions;r++)
        {
        for(int c=0;c<dimensions;c++)
            {
             randstate = Math.random();
            if (randstate>probAlive)
                {
                boardstate[r][c]=1;
                }
            }
        }
    }//end seedboard//
    @Override
     public boolean rules(int num){
        return num == 3 || num == 12 || num == 13;
    }

}//end boardmaster//
