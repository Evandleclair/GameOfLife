/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.GameOfLife;

import com.mycompany.mavenproject1.DataTypes.RulesBundle;
import com.mycompany.mavenproject1.DataTypes.simWindowInfo;

/**
 *
 * @author toast
 */
interface GameRunnerInterface {
        
        public void createSimWindowAndStartSim(int dimensions);
        public void createSimWindowAndStartSim(int[][] importedBoard, RulesBundle importedRules);
        public void destroyGame(simWindowInfo s);
        public int getGamesRunning();
        public void setGamesRunning(int GamesRunning);
        public SimulatorWindow getSimWindowByID(int rowID);
        public int getOpenGamesCount();
        public void UpdateTableOnMainWindow();
       
        
}