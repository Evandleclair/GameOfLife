/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject1;

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

/**
 *
 * @author toast
 */
public class SimulatorWindow extends JDialog{
    static int openFrameCount = 0;
    private int boardDim;
    String IDname;
    private MainInterface creator;
    private Simulator simMaster;
    private static final int X_OFFSET = 30, Y_OFFSET = 30;
    JTextArea textArea;
    private Graphics gr;
    private static final Font BOARD_FONT = new Font(Font.MONOSPACED, Font.PLAIN, 12);
    public SimulatorWindow(int dim, String idName, MainInterface c) {
        gr = this.getGraphics();
        boardDim=dim;
        creator=c;
        IDname=idName;
        //...Then set the window size or call pack...
        openFrameCount=creator.getOpenFramesCount();
        SpinUpSim();
        CreateAndShowGUI();
        this.addWindowListener(new WindowAdapter()
            {
                @Override
                public void windowClosed(WindowEvent e)
                {
                   System.out.println("closing self. I am " + IDname);
                   creator.RemoveFrame(new simWindowInfo(IDname,this));
                }

                @Override
                public void windowClosing(WindowEvent e)
                {
                    System.out.println("closing self. I am " + IDname);
                   creator.RemoveFrame(new simWindowInfo(IDname,this));
                }
            });
        System.out.println("aaaa");
        //Set the window's location.
        setLocation(X_OFFSET*openFrameCount, Y_OFFSET*openFrameCount);
    }
    private void SpinUpSim()
    {
        simMaster=new Simulator(this);
       
    }
    public void PleaseLookAtMe()
    {
        requestFocus();
    }
    public void EstablishBoard()
    {
         simMaster.StartSimulation(boardDim, creator.GetProb());
    }
    public void CreateAndShowGUI()
    {
         textArea = new JTextArea(boardDim, boardDim);
         
        textArea.setFont(BOARD_FONT);
        textArea.setLineWrap(false);
        textArea.setEditable(false);
        textArea.setBackground(Color.BLACK);
        textArea.setForeground(Color.WHITE);
        textArea.setOpaque(true);
        setTitle(IDname);
        //...Create the GUI and put it in the window...
        JPanel textPanel = new JPanel();
        EstablishBoard();
        textPanel.add(textArea);
        Container cc = this.getContentPane();
        cc.add(textPanel);
        
        // set the size of frame
        setSize(100, 100);
    }
    public void SimulationStep()
    {
        simMaster.SimulationTick();
    }
    public void RefreshBoard(String s)
    {
        textArea.setText(s);
        ScaleToFont();
        textArea.update(textArea.getGraphics());
    }
    private void ScaleToFont()
    {
         FontMetrics fm =  textArea.getGraphics().getFontMetrics(BOARD_FONT);
        var boxHeight = (fm.getMaxAscent()*boardDim);
        var boxWidth = (fm.getMaxAdvance()*boardDim);
        System.out.println("height and width"  + boxHeight + "  " +boxWidth);
        textArea.setSize(boxWidth,boxHeight);
        pack();
        
        //setSize(boxWidth,boxHeight);
    }
   
 @Override
    public void paint(Graphics g) {
        super.paint(g);
        gr=g;

    }
}
