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
    public void StartSimulation(int d, double prob)
    {
        boardDims=d;
        boardMaster = new BoardMaster(boardDims, prob);
        boardMaster.setupBoard();
 
    }
        
    public void SimulationTick()
    {
        masterWindow.RefreshBoard(boardMaster.reportBoard());
    }
}
