/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.GameOfLife.popupWindows;

import com.mycompany.GameOfLife.Interfaces.FileManagerInterface;
import com.mycompany.GameOfLife.BoardObject;
import com.mycompany.GameOfLife.GameRunner;
import com.mycompany.GameOfLife.MainWindow;
import com.mycompany.GameOfLife.SimCanvasWindow;
import com.mycompany.GameOfLife.UtilityClasses.LoggingClass;
import com.mycompany.GameOfLife.UtilityClasses.XMLWriter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.swing.*;
import javax.swing.JFileChooser;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import org.xml.sax.SAXException;


/**
 *
 * @author evandleclair
 */
public class FileManagerPopup extends JPanel implements FileManagerInterface, ActionListener {

    private final MainWindow mainWindow;
    private final GameRunner gr;
    private BoardObject boardObject;
    static private final String newline = "\n";
    JButton openButton, saveButton;
    private final XMLWriter xmlWriter;  
    JTextArea log;
    int callingRow=0; //used for exporting//
    JFileChooser fc;
    
    public FileManagerPopup(MainWindow MainWindow, GameRunner Gr) throws ParserConfigurationException
    {
        this.xmlWriter = new XMLWriter();
        fc = new JFileChooser();
        mainWindow=MainWindow;
        gr=Gr;
        fc.addChoosableFileFilter(new FileNameExtensionFilter("Game Of Life", "GOL", "gol"));
    }

    @Override
    public void exportBoard(File FileToExport) 
    {
        SimCanvasWindow sw= gr.getSimWindowByID(callingRow);
        BoardObject boardDataBundle= sw.getBoardFromRunnable();
        try 
        {
            xmlWriter.createFileFromBoard(boardDataBundle, FileToExport);
        }//end try// 
        catch (TransformerException ex) 
        {
            
            //Logger.getLogger(FileManagerPopup.class.getName()).log(Level.SEVERE, null, ex);
        }//end catch////end catch//
    }//end exportBoard//
    
    @Override
    public void importBoard(File FileToImport) {
        BoardObject bOb=null;
        try {
            System.out.println("passing board to XML writer");
            bOb=xmlWriter.getBoardFromXML(FileToImport);
        } catch (SAXException | ParserConfigurationException | IOException ex) {
           LoggingClass.WriteToLog(ex, "Error when loading Board Object", "SEVERE");
        }
        if (bOb!=null)
            gr.createSimWindowAndStartSim(bOb); 
        else
        {
            System.out.println("Failed to import board"); //NULL means it failed to import a board correctly//
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {}
    
    public void setCallingRow(int crow)
    {
        callingRow=crow;
    }//end setCallingRow//
    
    @Override
    public void showSaveInterface()
    {
        int returnVal = fc.showSaveDialog(FileManagerPopup.this);
        
             if (returnVal == JFileChooser.APPROVE_OPTION) {
                 File fileToSave = fc.getSelectedFile();
                 exportBoard(fileToSave);
            } else {
               System.out.println("file opening cancelled by user");
            }
    }
    
    @Override
    public void showOpenInterface() {
        int returnVal = fc.showOpenDialog(FileManagerPopup.this);
          if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                //System.out.println(file.getName().toString());
                mainWindow.setTextBoxToFilename(file.getAbsolutePath());
                gr.storeGameFile(file);
                
            } else {
               System.out.println("file opening cancelled by user");
            }
    }//end showOpenInterface//
}//end FileManagerPopup class//
