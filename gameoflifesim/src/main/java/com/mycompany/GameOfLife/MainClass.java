/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.GameOfLife;

/**
 *
 * @author toast
 */
public class MainClass {
    
    public static void main(String[] args)
    {
        MainInterface mainInterface = new MainInterface(new DocFilter());
        mainInterface.setGameRunner(new GameRunner(mainInterface));
        mainInterface.startTheGame();
    }
    
}
