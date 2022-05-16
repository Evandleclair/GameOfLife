/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject1;

/**
 *
 * @author toast
 */
public class Simulator extends FrameMaster{
        
    protected BoardMaster boardMaster;
        public void StartSimulation()
        {
        boardMaster = new BoardMaster(50);
        boardMaster.setupBoard();
        SimulationTick();
        }
        public void SimulationTick()
        {
            super.RefreshBoard(boardMaster.reportBoard());
            //super.RefreshBoard("aaaaaaaaaaaaaaaaaaaaaaaaaa");
        }
}
