/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.personalprojects.GameOfLife.popupWindows;

import com.personalprojects.GameOfLife.GameRunner;
import com.personalprojects.GameOfLife.MainWindow;
import com.personalprojects.GameOfLife.UtilityClasses.DocFilter;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.text.AbstractDocument;

/**
 * Re-usable popup window used to enter generation values in various context. 
 * @author evandleclair
 */
public class GenerationEntryPopup extends JDialog 
{
    private JButton addButton, cancelButton;
    private int gensToAdd;
    private final MainWindow mainWindow; //largely unused, only imported so that we can set this window as Modal to it.//'
    private final DocFilter docFilter = new DocFilter();
    private final GameRunner gr; //this is where we will send our info//
    private JTextField genEntryField;
    private final int callingRow;
    
    /**
     * constructor without a specified row. Used for board imports. 
     * @param Frame the frame that called the popup. 
     */
    public GenerationEntryPopup(JFrame Frame)
    {
        super(Frame);
        setModal(true);
        
        mainWindow=(MainWindow)Frame;
        gr=mainWindow.getGameRunner();
        gensToAdd=0;
        callingRow=-1; //the -1 as the calling row is the signifier that this was not called by a specific row. 
        createAndShowGUI();
        //setDocumentFilters();
    }//end constructor//
    
    /**
     * This version of the constructor is called by tables. 
     * @param Frame the frame that created this. 
     * @param CallingRow the row of the table that invoked this. Used to point back to that table and make changes to that row. 
     */
    public GenerationEntryPopup(JFrame Frame, int CallingRow)
    {
        super(Frame);
        callingRow=CallingRow;
        setModal(true);
        mainWindow=(MainWindow)Frame;
        gensToAdd=0;
        gr=mainWindow.getGameRunner();
        createAndShowGUI(); 
        //setDocumentFilters();
    }//end constructor//
    
    public GenerationEntryPopup(JFrame Frame, String name)
    {
        super(Frame);
        setModal(true);
        mainWindow=(MainWindow)Frame;
        gensToAdd=0;
        gr=mainWindow.getGameRunner();
        callingRow=gr.getRowIDFromSimTableByName(name);
        createAndShowGUI(); 
        //setDocumentFilters();
    }//end constructor//
     
    private void createAndShowGUI()
    {
        setTitle("Enter Generations to Add");
        Container cc = this.getContentPane();
        cc.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        int genInt = mainWindow.getGenerationsToRun();
        String startGenString="10";
        
        if (genInt>0)
        {
            startGenString=String.valueOf(genInt);
        }
        
        genEntryField= new JTextField(startGenString);
        AbstractDocument abstractGenEntry = (AbstractDocument) genEntryField.getDocument();
        abstractGenEntry.setDocumentFilter(docFilter);
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
        if (callingRow!=-1)
        {
            addButton.addActionListener((ActionEvent e) -> {
                sendGenerationsToSelectedRow();
            });
        }
        else
        {
            addButton.addActionListener((ActionEvent e) -> {
                int gens = Integer.parseInt(genEntryField.getText());
                if (gens>1)
                    setImportedGenerationsToRun(gens);
                else
                {
                    JOptionPane.showMessageDialog(mainWindow," Generations to add must be larger than 1.");
                    System.out.println("invalid character");
                }
                ///show warning popup
            }); 
        }
        
        cancelButton=new JButton("CANCEL");
        gbc.fill=GridBagConstraints.HORIZONTAL;
        gbc.gridwidth=1;
        gbc.gridx=1;
        gbc.gridy=1;
      
            
        cancelButton.addActionListener((ActionEvent e) -> {
            closeMe();
        });
        cc.add(cancelButton, gbc);
        
        
        pack();
        setLocationRelativeTo(mainWindow);
        setVisible(true);
        
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
     
     
    private void closeMe()
    {
        if (callingRow!=-1)
        {
            System.out.println("focusing on specific window");
            gr.focusOnSpecificSimWindow(callingRow);
        }
        dispose();
    }
    
    /**
     * uses the callingrow variable to add generations to a specific game from the table.//
     */
    private void sendGenerationsToSelectedRow()
    {
        gensToAdd = Integer.parseInt(genEntryField.getText());
        gr.addGenerationsToSpecificSimWindow(callingRow, gensToAdd);
        closeMe();
    }
    
    /**
     * Passes an integer over to the game runner, which knows that any imported games should be run 'i' many generations before stopping. 
     * @param i the number of generations that imported simulations should run. 
     */
    private void setImportedGenerationsToRun(int i)
    {
      
        gr.setImportedGens(i);
        closeMe();
    }
     
}//end GenerationEntryPopup//

