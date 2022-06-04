/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.GameOfLife;
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

/**
 *
 * @author toast
 */
public class SimulatorWindow extends JDialog{
    static int openFrameCount = 0;
    private int boardDim;
    private String IDname, origTitle;
    private MainWindow myCreator;
    private GameRunner myRunner;
    private SimulatorRunnable simMaster;
    private static final int X_OFFSET = 30, Y_OFFSET = 30;
    JTextArea textArea;
    Graphics gr, textAreaGr;
    private static Font BOARD_FONT = new Font(Font.MONOSPACED, Font.PLAIN, 12);
    public SimulatorWindow(int dim, String idName, MainWindow c) {
        gr = this.getGraphics();
        boardDim=dim;
        myCreator=c;
        myRunner=c.getGameRunner();
        IDname=idName;
        //...Then set the window size or call pack...
        openFrameCount=myRunner.getGamesRunning();
        createAndShowGUI();
       
        System.out.println(textAreaGr);
        
        this.addWindowListener(new WindowAdapter()
            {
                @Override
                public void windowClosed(WindowEvent e)
                {
                   System.out.println("closing self. I am " + IDname);
                   myRunner.destroyGame(new simWindowInfo(IDname,this));
                   simMaster.interuptThread();
                }

                @Override
                public void windowClosing(WindowEvent e)
                {
                    System.out.println("closing self. I am " + IDname);
                    myRunner.destroyGame(new simWindowInfo(IDname,this));
                    simMaster.interuptThread();
                }
            });
        System.out.println("aaaa");
        //Set the window's location.
        setLocation(X_OFFSET*openFrameCount, Y_OFFSET*openFrameCount);
    }
    public void spinUpSim()
    {
        simMaster.start();
    }
    public void pleaseLookAtMe()
    {
        requestFocus();
    }
    public void pleaseCloseMe()
    {
        dispose();
    }
    public void establishBoard()
    {
         simMaster = new SimulatorRunnable(this, IDname,boardDim, myCreator.getAliveProbability(), myCreator.getGenToRun());
         simMaster.startSimulation();
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
        establishBoard();
        textPanel.add(textArea);
        Container cc = this.getContentPane();
        cc.add(textPanel);
        
        // set the size of frame
        setSize(100, 100);
    }
   
    public void refreshBoard(String s)
    {
        try 
        {
        textArea.setText(s);
        scaleToFont();
        //textArea.update(textArea.getGraphics());
        repaint();
        this.setTitle(origTitle + " GEN:" + simMaster.getCurrentGen());
        }
        catch (IllegalPathStateException e) {
            System.out.println("board error occured. why?");
        }
    }
    private void scaleToFont()
    {
        FontMetrics fm =  textAreaGr.getFontMetrics(BOARD_FONT);
        var boxHeight = (fm.getMaxAscent()*boardDim);
        var boxWidth = (fm.getMaxAdvance()*boardDim);
        System.out.println("height and width"  + boxHeight + "  " +boxWidth);
        textArea.setSize(boxWidth,boxHeight);
        pack();
        //setSize(boxWidth,boxHeight);
    }
    public void printMyName()
    {
        System.out.println(IDname);
    }
    public void setMyGraphics()
    {
        textAreaGr=textArea.getGraphics();
    }
   
 @Override
    public void paint(Graphics g) {
        super.paint(g);
        gr=g;
    }
}
