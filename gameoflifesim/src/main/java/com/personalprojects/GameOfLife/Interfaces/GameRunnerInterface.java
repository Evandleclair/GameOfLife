/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.personalprojects.GameOfLife.Interfaces;

import com.personalprojects.GameOfLife.BoardObject;
import com.personalprojects.GameOfLife.SimCanvasWindow;
import com.personalprojects.GameOfLife.DataTypes.simWindowInfo;

/**
 *
 * @author evandleclair
 */
public interface GameRunnerInterface 
{        
    public void createSimWindowAndStartSim(int dimensions);
    public void createSimWindowAndStartSim(BoardObject BOb);
    public void destroyGame(simWindowInfo s);
    public int getGamesRunning();
    public void setGamesRunning(int GamesRunning);
    public SimCanvasWindow getSimWindowByID(int rowID);
    public int getOpenGamesCount();
    public void UpdateTableOnMainWindow();
    public void reportClosedGame();
}
