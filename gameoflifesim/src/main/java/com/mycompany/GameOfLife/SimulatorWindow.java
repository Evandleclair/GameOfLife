/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.GameOfLife;
import com.mycompany.mavenproject1.DataTypes.RulesBundle;
import com.mycompany.mavenproject1.DataTypes.simWindowInfo;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.font.*;
import javax.swing.*;
import java.awt.FontMetrics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.IllegalPathStateException;
import java.util.Hashtable;

/**
 *
 * @author toast
 */
public class SimulatorWindow extends JDialog implements SimWindowInterface{
    static int openFrameCount = 0;
    private int boardDim, genTime;
    private String IDname, origTitle;
    private final MainWindow myCreator;
    private GameRunner gameRunner;
    private SimulatorRunnable simRunnable;
    private RulesBundle myRules;
    private static final int X_OFFSET = 30, Y_OFFSET = 30;
    JTextArea textArea;
    Graphics gr, textAreaGr;
    private static final Font BOARD_FONT = StringMaster.getGlobalFont();
    public SimulatorWindow(int dim, String idName, MainWindow c, RulesBundle MyRules) {
        gr = this.getGraphics();
        boardDim=dim;
        myCreator=c;
        myRules=MyRules;
        genTime=c.getGenTime();
        gameRunner=c.getGameRunner();
        IDname=idName;
        //...Then set the window size or call pack...
        openFrameCount=gameRunner.getGamesRunning();
        System.out.println(textAreaGr);
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
    public void startSimRunnable()
    {
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
    public void establishBoardAndStartSim()
    {
         //simRunnable = new SimulatorRunnable(this, IDname,boardDim, myCreator.getInitialAliveProbability(), myCreator.getGenerationsToRun(),myRules);
         simRunnable.startSimulation(genTime);
    }
    
    public void establishBoardAndStartSim(int[][] importedBoard)
    {
         //simRunnable = new SimulatorRunnable(this, IDname,boardDim, myCreator.getInitialAliveProbability(), myCreator.getGenerationsToRun(),myRules);
         simRunnable.startImportedSimulation(importedBoard, genTime);
    }
     
    public void createAndShowGUI()
    {
        textArea = new JTextArea(boardDim, boardDim);
        textArea.setFont(BOARD_FONT);
        textArea.setLineWrap(false);
        textArea.setEditable(false);
        textArea.setBackground(Color.BLACK);
        textArea.setForeground(Color.WHITE);
        textArea.setOpaque(true);
        setTitle(IDname);
        origTitle=IDname;
        //...Create the GUI and put it in the window...
        JPanel textPanel = new JPanel();
        establishBoardAndStartSim();
        textPanel.add(textArea);
        Container cc = this.getContentPane();
        cc.add(textPanel);
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
    public void displayUpdatedBoard(int[][] boardState)
    {
      /*  try 
        {
            textArea.setText(s);
            scaleBoardToFont();
            repaint();
            this.setTitle(origTitle + " GEN:" + simRunnable.getCurrentGen());
        }//end try//
        catch (IllegalPathStateException e) 
        {
            System.out.println("board error occured!?");
        }//end catch//*/
    }//end displayUpdatedBoard//
    
    private void scaleBoardToFont()
    {
        FontMetrics fm =  textAreaGr.getFontMetrics(BOARD_FONT);
        var boxHeight = (fm.getMaxAscent()*boardDim);
        var boxWidth = (fm.getMaxAdvance()*boardDim);
        System.out.println("height and width"  + boxHeight + "  " +boxWidth);
        textArea.setSize(boxWidth,boxHeight);
        pack();
        //setSize(boxWidth,boxHeight);
    }//end scaleBoardToFont//
    
    public void printMyName()
    {
        System.out.println(IDname);
    }
    
    public void setMyGraphics()
    {
        textAreaGr=textArea.getGraphics();
    }//end setMyGraphics//
   
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        gr=g;
    }

    @Override
    public void passSimStatusToMainWindow(String simStatus, int currentGen) {
        System.out.println("passing");
        gameRunner.updateSimColumnsOnTable(IDname, simStatus, currentGen);
    }
}
