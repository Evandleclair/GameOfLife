/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.GameOfLife.popupWindows;

import com.mycompany.GameOfLife.GameRunner;
import com.mycompany.GameOfLife.MainWindow;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;

/**
 *
 * @author toast
 */
public class GenerationEntryPopup extends JDialog 
{
    private JSpinner starveSpinner;
    private JButton addButton, cancelButton;
    private int gensToAdd;
    private MainWindow mainWindow; //largely unused, only imported so that we can set this window as Modal to it.//'
    private GameRunner gr; //this is where we will send our info//
    private JTextField genEntryField;
    private final int callingRow;
    
            
    public GenerationEntryPopup(JFrame Frame)
    {
        super(Frame);
        setModal(true);
        
        mainWindow=(MainWindow)Frame;
        gr=mainWindow.getGameRunner();
        gensToAdd=0;
        callingRow=1;
        createAndShowGUI();
    }//end constructor//
     public GenerationEntryPopup(JFrame Frame, int CallingRow)
    {
        super(Frame);
        callingRow=CallingRow;
        setModal(true);
        mainWindow=(MainWindow)Frame;
        gensToAdd=0;
        gr=mainWindow.getGameRunner();
        createAndShowGUI();
    }//end constructor//
     
   
     
     private void createAndShowGUI()
    {
        setTitle("Enter Generations to Add");
        Container cc = this.getContentPane();
        cc.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        
        genEntryField= new JTextField();
        gbc.fill=GridBagConstraints.HORIZONTAL;
        gbc.gridwidth=2;
        gbc.gridx=0;
        gbc.gridy=0;
        cc.add(genEntryField, gbc);
        
        addButton=new JButton("ADD GENERATIONS");
        gbc.fill=GridBagConstraints.HORIZONTAL;
        gbc.gridwidth=1;
        gbc.gridx=0;
        gbc.gridy=1;
        cc.add(addButton, gbc);
        addButton.addActionListener(new ActionListener() 
            { 
                @Override
                public void actionPerformed(ActionEvent e) { 
                sendGenerationsToSelectedRow();
                } 
            } );
        
        cancelButton=new JButton("CANCEL");
        gbc.fill=GridBagConstraints.HORIZONTAL;
        gbc.gridwidth=1;
        gbc.gridx=1;
        gbc.gridy=1;
        cancelButton.addActionListener(new ActionListener() 
            { 
                @Override
                public void actionPerformed(ActionEvent e) { 
                closeMe();
                } 
            } );
        cc.add(cancelButton, gbc);
        
        
        pack();
        setLocationRelativeTo(mainWindow);
        setVisible(true);
        
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
     
    private void closeMe()
    {
        dispose();
    }
    
    private void sendGenerationsToSelectedRow()
    {
        gensToAdd = Integer.parseInt(genEntryField.getText());
        gr.addGenerationsToSpecificSimWindow(callingRow, gensToAdd);
        closeMe();
    }
    
}//end GenerationEntryPopup//

