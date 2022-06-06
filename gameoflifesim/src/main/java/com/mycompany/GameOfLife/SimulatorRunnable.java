package com.mycompany.GameOfLife;

import java.util.TimerTask;

/**
 *
 * @author toast
 */
public class SimulatorRunnable implements Runnable{
    private Thread t;
    private BoardObject boardObject; //it will create a board master//
    private final SimulatorWindow masterWindow; //the simulator window that created this//
    private int boardDims, gensToRun, currentGen=0;
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
        boardObject = new BoardObject(boardDims);
        boardObject.setupBoard(aliveProb);
    }
    public void startImportedSimulation(int[][] importedBoard)
    {
        boardObject = new BoardObject(boardDims);
        boardObject.setupBoard(importedBoard);
    }
        
    public void simulationTick()
    {
        boardObject.boardTick();
        masterWindow.displayUpdatedBoardText(boardObject.reportBoard());
    }

    @Override
    public void run() 
    {
        while (!Thread.currentThread().isInterrupted())
            try 
            {
                if (currentGen==0)
                {
                    currentGen=1;
                }
                //masterWindow.printMyName();
                for (int i=0; i<gensToRun; i++)
                {
                    simulationTick();
                    currentGen++;
                    Thread.sleep(250);
                }
                //currentGen++;
            }
            catch(InterruptedException e)
            {
                e.printStackTrace();
                Thread.currentThread().interrupt(); //ensures the current thread will properly interrupt//
            }
            finally
            {
                interuptThread();
            }
    }//end run//
     public void start () {
      
      if (t == null) {
         System.out.println("Starting game on new thread" +  name );
         t = new Thread (this, name);
         t.start ();
      }
      else if (t.isAlive()==false)
      {
        System.out.println("Starting a new thread to continue game");
        t=null;
        start();
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
        System.out.println("ending thread");
        if (t!=null)
        {
        t.interrupt(); 
        t=null; //blank the thread//
        }
    }
    
    public void hearingExam()
    {
        //test method to see if a thread is hearing from other objects correctly.//
        System.out.println("I can hear you fine");
    }
    
    public void addGens(int gensToAdd)
    {
    if (t==null)
        {
        System.out.println("will start a new game");
        gensToRun=gensToAdd;
        start();
        }
    else
        {
         gensToRun+=gensToAdd;
        }
    }
}

