package com.mycompany.GameOfLife;

import java.util.TimerTask;

/**
 *
 * @author toast
 */
public class SimulatorRunnable implements Runnable{
    private Thread t;
    private BoardMaster boardMaster; //it will create a board master//
    private final SimulatorWindow masterWindow; //the simulator window that created this//
    private int boardDims, gensToRun, currentGen;
    private double aliveProb;
    private String name;
    public SimulatorRunnable(SimulatorWindow m, String n, int d, double prob, int GensToRun)
    {
        masterWindow=m;
        name=n;
        aliveProb=prob;
        boardDims = d;
        gensToRun=GensToRun;
    }
    public void startSimulation()
    {
        boardMaster = new BoardMaster(boardDims, aliveProb);
        boardMaster.setupBoard();
 
    }
        
    public void simulationTick()
    {
        boardMaster.boardTick();
        masterWindow.refreshBoard(boardMaster.reportBoard());
    }

    @Override
    public void run() {
        try 
        {
            currentGen=1;
            masterWindow.printMyName();
            for (int i=0; i<gensToRun; i++)
            {
                simulationTick();
                currentGen++;
                Thread.sleep(500);
            }
        }
        catch(InterruptedException e)
        {
            e.printStackTrace();
        }
        finally
        {
            currentGen++;
        }
    }
     public void start () {
      System.out.println("Starting " +  name );
      if (t == null) {
         t = new Thread (this, name);
         t.start ();
      }
   }
    public void setSimulationThreadReference()
    {
        
    }
    public int getCurrentGen()
    {
        return currentGen;
    }
    public void interuptThread()
    {
        t.interrupt();
    }
}

