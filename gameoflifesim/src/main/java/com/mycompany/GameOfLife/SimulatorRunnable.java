package com.mycompany.GameOfLife;

import com.mycompany.mavenproject1.DataTypes.RulesBundle;
import java.util.TimerTask;

/**
 *
 * @author toast
 */
public class SimulatorRunnable implements Runnable{
    private Thread t;
    private BoardObject boardObject; //it will create a board master//
    //private final SimulatorWindow mySimWindow; //the simulator window that created this//
    private final SimCanvasWindow mySimWindow;
    private int gensToRun, currentGen=0;
    private final int boardDims;
    private final double aliveProb;
    private int genTime =250;
    private RulesBundle rulesBundle;
    private String name;
    //public SimulatorRunnable(SimulatorWindow m, String n, int d, double prob, int GensToRun)
    //{
    //    mySimWindow=m;
    //    name=n;
    //    aliveProb=prob;
    //    boardDims = d;
    //    gensToRun=GensToRun;
    //}
    public SimulatorRunnable(SimCanvasWindow m, String n, int d, double prob, int GensToRun, RulesBundle rBundle)
    {
        rulesBundle=rBundle;
        mySimWindow=m;
        name=n;
        aliveProb=prob;
        boardDims = d;
        gensToRun=GensToRun;
       
    }
  
    public void startSimulation(int GenTime)
    {
        genTime=GenTime;
        boardObject = new BoardObject(boardDims);
        boardObject.setupBoard(aliveProb,rulesBundle);
    }
    public void startImportedSimulation(int[][] importedBoard, int GenTime)
    {
        genTime=GenTime;
        boardObject = new BoardObject(boardDims);
        boardObject.setupBoard(importedBoard);
    }
        
    public void simulationTick()
    {
        boardObject.boardTick();
        mySimWindow.passSimStatusToMainWindow(getSimStatusAsString(),currentGen);
        mySimWindow.displayUpdatedBoard(boardObject.getBoardState());
    }

    @Override
    public void run() 
    {
        while (!Thread.currentThread().isInterrupted())
            try 
            {
               
                //masterWindow.printMyName();
                for (int i=0; i<gensToRun; i++)
                {
                    currentGen++;
                    if (i!=0)
                    {
                        simulationTick();
                    }
                    Thread.sleep(genTime);
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
                mySimWindow.displayUpdatedBoard(boardObject.getBoardState());
                mySimWindow.passSimStatusToMainWindow("COMPLETE",currentGen);
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
    
    public String getSimStatusAsString()
    {
        String retString="eururu";
        if (t!=null)
        {
            switch (t.getState().toString())
            {
                case "TERMINATED":
                    retString="COMPLETE";
                default:
                    retString="RUNNING";
            }
        }
        else
        {
            retString="COMPLETE";
        }
        return retString;
    }
}

