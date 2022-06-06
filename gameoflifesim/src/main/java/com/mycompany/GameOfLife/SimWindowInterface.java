/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.GameOfLife;

/**
 *
 * @author toast
 */
public interface SimWindowInterface {
    public void startSimRunnable(); //window must be able to start a new sim runnable object//
    public void pleaseLookAtMe(); //external objects should be able to bring this window to the front//
    public void pleaseCloseMe(); //window must be able to be closed by external object//
    public void establishBoardAndStartSim(); //
    public void pleaseAddGenerations(int gens); //window must be able to add generations to the sim runnable it is associated with//
    public void displayUpdatedBoardText(String s);
    
}
