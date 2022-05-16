/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject1;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class FrameMaster extends JPanel implements ActionListener {
   
    static JTextArea textArea;
    static JFrame mainFrame;
    static Simulator simMaster;
    static FrameMaster FM;
    protected BoardMaster boardMaster;
    private final static String newline = "\n";

    public FrameMaster() {
      
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
      
        //textArea.append(text + newline);

        //Make sure the new text is visible, even if there
        //was a selection in the text area.
        //textArea.setCaretPosition(textArea.getDocument().getLength());
    }

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event dispatch thread.
     */
    private static void createAndShowGUI(FrameMaster FM) {
        //Create and set up the window.
        
        
    }
    private void EstablishBoard()
    {
        simMaster=new Simulator();
        simMaster.StartSimulation();
    }
    public void RefreshBoard(String s)
    {
        textArea.setText("");
        textArea.setText(s);
        textArea.update(textArea.getGraphics());
        textArea.append("sss");
    }
   

    public static void main(String[] args) {
        mainFrame = new JFrame("textfield");
        textArea = new JTextArea(10, 10);
        JPanel p = new JPanel();
        p.add(textArea);
        mainFrame.add(p);
        // set the size of frame
        mainFrame.setSize(300, 300);
        FM = new FrameMaster();
        FM.EstablishBoard();
        
        mainFrame.show();
        
    }
}