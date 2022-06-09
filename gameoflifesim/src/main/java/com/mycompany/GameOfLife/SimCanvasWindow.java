/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.GameOfLife;

import static com.mycompany.GameOfLife.SimulatorWindow.openFrameCount;
import com.mycompany.GameOfLife.popupWindows.GridCanvas;
import com.mycompany.mavenproject1.DataTypes.RulesBundle;
import com.mycompany.mavenproject1.DataTypes.simWindowInfo;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Container;
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
    Graphics gr, canvasGr;
    public SimCanvasWindow(int dim, String idName, MainWindow c, RulesBundle MyRules)
    {
        gr = this.getGraphics();
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
    
    
    public void runSimWindowStartupTasks()
    {
        setVisible(true); //necessary as of 1.3
        setMyGraphics();
        
    }
    @Override
    public void startSimRunnable() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    public void setMyGraphics()
    {
        canvasGr=boardGameCanvas.getGraphics();
    }//end setMyGraphics//
  

    @Override
    public void displayUpdatedBoardText(String s) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
      public void createAndShowGUI()
    {
        GridCanvas boardGameCanvas = new GridCanvas(boardDim, 10);
       
        setTitle(IDname);
        origTitle=IDname;
        //...Create the GUI and put it in the window...
        JPanel canvasPanel = new JPanel();
        establishBoardAndStartSim();
        canvasPanel.add(boardGameCanvas);
        Container cc = this.getContentPane();
        cc.add(canvasPanel);
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
        setSize(100, 100);
    }//end createAndShowGUI//

    @Override
    public void passSimStatusToMainWindow(String simStatus, int currentGen) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
