package com.mycompany.mavenproject1;

/**
 *
 * @author toast
 */
public class Simulator {
        
    protected BoardMaster boardMaster;
    protected SimulatorWindow masterWindow;
    static int boardDims; 
    public Simulator(SimulatorWindow m)
    {
        masterWindow=m;
    }
    public void StartSimulation(int d)
    {
        boardDims=d;
        boardMaster = new BoardMaster(boardDims);
        boardMaster.setupBoard();
        SimulationTick();
    }
        
    public void SimulationTick()
    {
        masterWindow.RefreshBoard(boardMaster.reportBoard());
    }
}
