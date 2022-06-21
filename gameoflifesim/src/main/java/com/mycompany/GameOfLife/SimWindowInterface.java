/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.GameOfLife;



/**
 *
 * @author evandleclair
 */
public interface SimWindowInterface {
    public void startSimRunnable(); //window must be able to start a new sim runnable object//
    public void pleaseLookAtMe(); //external objects should be able to bring this window to the front//
    public void pleaseCloseMe(); //window must be able to be closed by external object//
    public void establishBoardAndStartSim(); //
    public void importBoardAndStartSim(BoardObject BOb);
    public void pleaseAddGenerations(int gens); //window must be able to add generations to the sim runnable it is associated with//
    public void displayUpdatedBoard(int[][] board);
    public void passSimStatusToMainWindow(String simStatus, int currentGen, int tSpeed);
    public void pleasePauseSim();
    public void pleaseResumeSim();
    public int getTickTime();
    public BoardObject getBoardFromRunnable();

}
