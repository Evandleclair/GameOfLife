/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.mavenproject1;

import java.util.Arrays;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author toast
 */
public class BoardMasterTest {
    
    public BoardMasterTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }

    /**
     * Test of setupBoard method, of class BoardMaster.
     */
    @Test
    public void testSetupBoard() {
       
       
    }

 
    @Test
    public void testRulesOfNature()
    {
        /* System.out.println("BoardTick");
        BoardMaster instance = new BoardMaster(9,0.25);
        boolean starved = instance.rulesOfNature(1);
        boolean normal = instance.rulesOfNature(2);
        boolean revived = instance.rulesOfNature(3);
        boolean overcrowd = instance.rulesOfNature(4);
        assertEquals(starved,false);
        assertEquals(normal,true);
        assertEquals(revived,true);
        assertEquals(overcrowd,false);*/
    }

    /**
     * Test of rules method, of class BoardMaster.
     */
   

    /**
     * Test of BoardTick method, of class BoardMaster.
     */
    @Test
    public void testBoardTick() {
      
    }

    /**
     * Test of calcNextState method, of class BoardMaster.
     */
    @Test
    public void testCalcNextState() {
         BoardMaster instance = new BoardMaster(3,0.25);
        instance.boardState= new int[][]{{0,0,0},{0,0,0},{0,0,0}};
        instance.previousState=instance.boardState;
        int[][] input0 =  new int[][]{{0,0,0},{0,1,0},{0,0,0}};
        int[][] input1 =  new int[][]{{0,0,1},{0,1,1},{0,0,0}};
        int[][] input2 =  new int[][]{{0,0,0},{0,0,0},{0,0,0}};
        int[][] expresult0 =  new int[][]{{0,0,0},{0,0,0},{0,0,0}};
        int[][] expresult1 = new int[][]{{0,1,1},{0,1,1},{0,0,0}};
        int[][] expresult2 =  new int[][]{{0,0,0},{0,0,0},{0,0,0}};
        int[][] actualResult1 = instance.calcNextState(input1);
        System.out.println("testing to see if one dies");
        //assertArrayEquals(instance.calcNextState(input0),expresult0);
         System.out.println("testing to see if patterns match.");
        assertArrayEquals(actualResult1,expresult1);
        System.out.println("testing for necromancers");
        //assertArrayEquals(instance.calcNextState(input2),expresult2);
        
        
    }

    /**
     * Test of enoughNeighborsAlive method, of class BoardMaster.
     */
  
    
}
