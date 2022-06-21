package com.mycompany.GameOfLife;


import com.mycompany.mavenproject1.DataTypes.RulesBundle;
import java.util.TimerTask;

/**
 *
 * @author evandleclair
 */
public class SimulatorRunnable implements Runnable{
    private Thread t;
    private BoardObject boardObject; //it will create a board master//
    private final SimCanvasWindow mySimWindow;
    private int gensToRun, currentGen=0;
    private final int boardDims;
    private final double aliveProb;
    private int tickTime =250;
    private RulesBundle rulesBundle;
    private String name;
    private boolean paused=false;
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
     public SimulatorRunnable(SimCanvasWindow m, BoardObject BOb, int GensToRun)
    {
        boardObject=BOb;
        rulesBundle=boardObject.getMyRules();
        mySimWindow=m;
        name=boardObject.getName();
        aliveProb=0.5;
        boardDims = boardObject.getDimensions();
        gensToRun=GensToRun;
    }
  
    public void startSimulation(int TickTime)
    {
        tickTime=TickTime;
        boardObject = new BoardObject(boardDims);
        boardObject.setTickSpeed(tickTime);
        boardObject.setupBoard(aliveProb,rulesBundle);
    }
    public void startImportedSimulation(int TickTime)
    {
        tickTime=TickTime;
        boardObject.setTickSpeed(tickTime);
    }
        
    public void simulationTick()
    {
        boardObject.boardTick();
        mySimWindow.passSimStatusToMainWindow(getSimStatusAsString(),boardObject.getCurrentGen(),boardObject.getTickSpeed());
        mySimWindow.displayUpdatedBoard(boardObject.getBoardState());
    }
    
    public void pauseTick()
    {
        mySimWindow.passSimStatusToMainWindow(getSimStatusAsString(),boardObject.getCurrentGen(),boardObject.getTickSpeed());
        mySimWindow.displayUpdatedBoard(boardObject.getBoardState());
    }

    @Override
    public void run() 
    {
        while (!Thread.currentThread().isInterrupted())
        try 
        {
           currentGen = boardObject.getCurrentGen();
           if (currentGen>0)
           {
               gensToRun+=currentGen;
           }
           gameBody();
        }//end try//
        catch(InterruptedException e)
        {
            e.printStackTrace();
            System.out.println("aaaaaa");
            Thread.currentThread().interrupt(); //ensures the current thread will properly interrupt//
        }//end catch
        finally
        {
            mySimWindow.displayUpdatedBoard(boardObject.getBoardState());
            mySimWindow.passSimStatusToMainWindow("COMPLETE",boardObject.getCurrentGen(),boardObject.getTickSpeed());
            interuptThread();
        }//end finally
    }//end run//
    private void gameBody() throws InterruptedException
    {
        if (!paused)
        {
            if (currentGen<gensToRun)
            {
                if (currentGen!=0)
                {
                    simulationTick();
                }
                currentGen+=1;
                boardObject.setCurrentGen(currentGen);
                Thread.sleep(boardObject.getTickSpeed());
                gameBody();
            }
            else
            {
                System.out.println("We done");
            }
        }
        else
        {
            //System.out.println("Paused");
            mySimWindow.passSimStatusToMainWindow("PAUSED",boardObject.getCurrentGen(),boardObject.getTickSpeed());
            Thread.sleep(150);
            pauseTick();
            gameBody();
        }
        /* while (!paused)
            {
                for (int i=0; i<gensToRun; i++)
                {
                    int cGen = boardObject.getCurrentGen();
                    cGen+=1;
                    boardObject.setCurrentGen(cGen);
                    if (i!=0)
                    {
                        simulationTick();
                    }
                    Thread.sleep(boardObject.getTickSpeed());
                    if (paused)
                        break;
                        
                }//end for loop i smaller than gens to run
            }//end paused//
            if (paused)
            {
                Thread.sleep(50);
            }
*/
        
    }
    public void start () 
    {
        if (t == null) 
        {
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
   }//end start//
    
   public void setSimulationThreadReference()
   {
        
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
    
    public void setPause(boolean pStatus)
    {
        paused=pStatus;
        System.out.println("paused is now" + paused);
    }
    public boolean getPause()
    {
        return paused;
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
        String retString=null;
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
    public BoardObject grabBoard()
    {
        return this.boardObject;
    }
}//end class//

