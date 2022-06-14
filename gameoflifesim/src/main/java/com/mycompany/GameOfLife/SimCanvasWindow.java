/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.GameOfLife;

import com.mycompany.GameOfLife.popupWindows.GridCanvas;
import com.mycompany.mavenproject1.DataTypes.RulesBundle;
import com.mycompany.mavenproject1.DataTypes.simWindowInfo;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 *
 * @author 12035
 */
public class SimCanvasWindow extends JDialog implements SimWindowInterface{

    static int openFrameCount = 0;
    private int boardDim, genTime;
    private String IDname, origTitle;
    private final MainWindow myCreator;
    private GameRunner gameRunner;
    private SimulatorRunnable simRunnable;
    private RulesBundle myRules;
    private static final int X_OFFSET = 30, Y_OFFSET = 30;
    GridCanvas boardGameCanvas;
    BoardObject bOb= null;
    Graphics gr, canvasGr;
    public SimCanvasWindow(int dim, String idName, MainWindow c, RulesBundle MyRules)
    {
       
        boardDim=dim;
        myCreator=c;
        myRules=MyRules;
        genTime=c.getGenTime();
        gameRunner=c.getGameRunner();
        IDname=idName;
        //...Then set the window size or call pack...
        openFrameCount=gameRunner.getGamesRunning();
        createAndShowGUI();
        //Set the window's location.
        setLocation(X_OFFSET*openFrameCount, Y_OFFSET*openFrameCount);
    }
     public SimCanvasWindow(MainWindow c, BoardObject BOb)
    {
        bOb=BOb;
        boardDim=bOb.getDimensions();
        myCreator=c;
        myRules=bOb.getMyRules();
        genTime=40;
        gameRunner=c.getGameRunner();
        IDname=bOb.getName();
        //...Then set the window size or call pack...
        openFrameCount=gameRunner.getGamesRunning();
        createAndShowGUI();
        //Set the window's location.
        setLocation(X_OFFSET*openFrameCount, Y_OFFSET*openFrameCount);
    }
    
    
    public void runSimWindowStartupTasks()
    {
        setVisible(true); //necessary as of 1.3
        setMyGraphics();
        
    }
    @Override
    public void startSimRunnable() {
        simRunnable.start();
    }
    
    @Override
    public void pleaseLookAtMe()
    {
        requestFocus();
    }
    
    @Override
    public void pleaseCloseMe()
    {
        dispose();
    }
    
    @Override
    public void pleaseAddGenerations(int gens)
    {
        simRunnable.addGens(gens);
    }

    @Override
    public void establishBoardAndStartSim() {
       simRunnable = new SimulatorRunnable(this, IDname,boardDim, myCreator.getInitialAliveProbability(), myCreator.getGenerationsToRun(),myRules);
       simRunnable.startSimulation(genTime);
    }
    
    @Override
    public void importBoardAndStartSim() {
        simRunnable = new SimulatorRunnable(this, IDname,boardDim, myCreator.getInitialAliveProbability(), myCreator.getGenerationsToRun(),myRules);
        simRunnable.startSimulation(genTime);
    }
    
    public void setMyGraphics()
    {
        canvasGr=boardGameCanvas.getGraphics();
        gr = this.getGraphics();
    }//end setMyGraphics//
  

    @Override
    public void displayUpdatedBoard(int[][] boardState) {
        boardGameCanvas.setCellsEqualToBoardState(boardState);
        boardGameCanvas.draw(canvasGr);
    }
    
      public void createAndShowGUI()
    {
        boardGameCanvas = new GridCanvas(boardDim, 10);
        int prefSize= boardDim*(10);
        boardGameCanvas.setPreferredSize(new Dimension(prefSize,prefSize));
        setTitle(IDname);
        origTitle=IDname;
        //...Create the GUI and put it in the window...
        JPanel canvasPanel = new JPanel();
        if (bOb==null)
        {
            establishBoardAndStartSim();
        }
        else
        {
            importBoardAndStartSim();
        }
        canvasPanel.add(boardGameCanvas);
        canvasPanel.setSize(boardGameCanvas.getSize());
        add(canvasPanel);
        this.addWindowListener(new WindowAdapter()
            {
                @Override
                public void windowClosed(WindowEvent e)
                {
                   System.out.println("closing self. I am " + IDname);
                   gameRunner.destroyGame(new simWindowInfo(IDname,this));
                   simRunnable.interuptThread();
                   simRunnable.hearingExam();
                }

                @Override
                public void windowClosing(WindowEvent e)
                {
                    System.out.println("closing self. I am " + IDname);
                    gameRunner.destroyGame(new simWindowInfo(IDname,this));
                    simRunnable.interuptThread();
                    simRunnable.hearingExam();
                }
            });
        // set the size of frame
       
        //setVisible(true);
        //boardGameCanvas.draw(gr);
         pack(); 
        setResizable(false);
        //int sizeToScale = boardGameCanvas.getSizeScale();
        //setPreferredSize(new Dimension(sizeToScale, sizeToScale));
        
       
        //setSize(sizeToScale,sizeToScale);
         
    }//end createAndShowGUI//

    @Override
    public void passSimStatusToMainWindow(String simStatus, int currentGen) {
        System.out.println("passing");
        gameRunner.updateSimColumnsOnTable(IDname, simStatus, currentGen);
    }

    @Override
    public BoardObject getBoardFromRunnable() {
       return simRunnable.grabBoard();
    }

  

  
    
}
