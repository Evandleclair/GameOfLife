package com.personalprojects.GameOfLife;


import com.personalprojects.GameOfLife.UtilityClasses.LoggingClass;
import com.personalprojects.GameOfLife.DataTypes.RulesBundle;

/**
 * The actual events performed on the BoardObject are performed by this class, to ensure it can be run asynchronously. 
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
    private final RulesBundle rulesBundle;
    private final String name;
    private boolean paused=false;
  
    /**
     *
     * @param m
     * @param n
     * @param d
     * @param prob
     * @param GensToRun
     * @param rBundle
     */
    public SimulatorRunnable(SimCanvasWindow m, String n, int d, double prob, int GensToRun, RulesBundle rBundle)
    {
        rulesBundle=rBundle;
        mySimWindow=m;
        name=n;
        aliveProb=prob;
        boardDims = d;
        gensToRun=GensToRun;
       
    }

    /**
     *
     * @param m
     * @param BOb
     * @param GensToRun
     */
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
  
    /**
     *
     * @param TickTime
     */
    public void startSimulation(int TickTime)
    {
        tickTime=TickTime;
        boardObject = new BoardObject(boardDims);
        boardObject.setTickSpeed(tickTime);
        boardObject.setupBoard(aliveProb,rulesBundle);
    }

    /**
     *
     * @param TickTime
     */
    public void startImportedSimulation(int TickTime)
    {
        tickTime=TickTime;
        boardObject.setTickSpeed(tickTime);
    }
        
    /**
     *
     */
    public void simulationTick()
    {
        boardObject.boardTick();
        mySimWindow.passSimStatusToMainWindow(getSimStatusAsString(),boardObject.getCurrentGen(),boardObject.getTickSpeed());
        mySimWindow.displayUpdatedBoard(boardObject.getBoardState());
    }
    
    /**
     *
     */
    public void pauseTick()
    {
        mySimWindow.passSimStatusToMainWindow(getSimStatusAsString(),boardObject.getCurrentGen(),boardObject.getTickSpeed());
        mySimWindow.displayUpdatedBoard(boardObject.getBoardState());
    }

    /**
     *
     */
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
                LoggingClass.WriteToLog(e, "Thread was interrupted", "WARNING");
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
    }//end Gamebody//
    
    /**
     *
     */
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
    
    /**
     *
     */
    public void interuptThread()
    {
        System.out.println("ending thread");
        if (t!=null)
        {
            t.interrupt(); 
            t=null; //blank the thread//
        }
    }
    
   
    /**
     *
     * @param gensToAdd
     */
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
    
    /**
     *
     * @return
     */
    public String getSimStatusAsString()
    {
        String retString=null;
        if (t!=null)
        {
            retString = switch (t.getState().toString()) {
                case "TERMINATED" -> "COMPLETE";
                default -> "RUNNING";
            };
        }
        else
        {
            retString="COMPLETE";
        }
        return retString;
    }

    /**
     *
     * @return
     */
    public BoardObject grabBoard()
    {
        return this.boardObject;
    }
    
    /**
     *
     * @return
     */
    public int getGensToRun()
    {
        return gensToRun;
    }
     /**
     *
     * @param pStatus
     */
    public void setPause(boolean pStatus)
    {
        paused=pStatus;
        System.out.println("paused is now" + paused);
    }

    /**
     *
     * @return
     */
    public boolean getPause()
    {
        return paused;
    }
   
    
}//end class//

