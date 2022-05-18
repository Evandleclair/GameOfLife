/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject1;

import com.mycompany.mavenproject1.DataTypes.simWindowInfo;
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
    static int boardDim;
    String IDname;
    static MainInterface creator;
    static Simulator simMaster;
    static final int xOffset = 30, yOffset = 30;
    static JTextArea textArea;
    static Graphics gr;
    static final Font boardFont = new Font(Font.MONOSPACED, Font.PLAIN, 12);
    public SimulatorWindow(int dim, String idName, MainInterface c) {
        gr = this.getGraphics();
        boardDim=dim;
        creator=c;
        IDname=idName;
        //...Then set the window size or call pack...
       
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
        setLocation(xOffset*openFrameCount, yOffset*openFrameCount);
    }
    private void SpinUpSim()
    {
        simMaster=new Simulator(this);
       
    }
    public void EstablishBoard()
    {
         simMaster.StartSimulation(boardDim);
    }
    public void CreateAndShowGUI()
    {
         textArea = new JTextArea(boardDim, boardDim);
         
        textArea.setFont(boardFont);
        textArea.setLineWrap(false);
        textArea.setEditable(false);
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
         FontMetrics fm =  textArea.getGraphics().getFontMetrics(boardFont);
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
