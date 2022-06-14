/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.GameOfLife;

import java.io.File;
import javax.xml.parsers.ParserConfigurationException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author toast
 */
public class XMLWriterTest {
    
   
    @Test
    public void testConvertStringToBoardMatrix() throws ParserConfigurationException {
        System.out.println("convertStringToBoardMatrix");
        int dimensions = 3;
        String board = "010111010";
        XMLWriter instance = new XMLWriter();
        int[][] expResult = null;
        int[][] result = instance.convertStringToBoardMatrix(dimensions, board);
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        
    }
    
}
