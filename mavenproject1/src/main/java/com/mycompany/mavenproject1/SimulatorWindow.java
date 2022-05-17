/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject1;

import java.awt.Container;
import java.awt.Font;
import javax.swing.*;

/**
 *
 * @author toast
 */
public class SimulatorWindow extends JDialog{
    static int openFrameCount = 0;
    static int boardDim;
    static Simulator simMaster;
    static final int xOffset = 30, yOffset = 30;
    static JTextArea textArea;
    
    public SimulatorWindow(int dim) {
       
        boardDim=dim;
        //...Then set the window size or call pack...
        
        SpinUpSim();
        CreateAndShowGUI();
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
        textArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
        textArea.setLineWrap(false);
        textArea.setEditable(false);
        //...Create the GUI and put it in the window...
        JPanel textPanel = new JPanel();
        EstablishBoard();
        textPanel.add(textArea);
        Container cc = this.getContentPane();
        cc.add(textPanel);
        
        // set the size of frame
        setSize(300, 300);
    }
    public void RefreshBoard(String s)
    {
        textArea.setText(s);
        textArea.update(textArea.getGraphics());
    }
}
