/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.personalprojects.GameOfLife.popupWindows;

import com.personalprojects.GameOfLife.Interfaces.FileManagerInterface;
import com.personalprojects.GameOfLife.BoardObject;
import com.personalprojects.GameOfLife.GameRunner;
import com.personalprojects.GameOfLife.MainWindow;
import com.personalprojects.GameOfLife.SimCanvasWindow;
import com.personalprojects.GameOfLife.UtilityClasses.LoggingClass;
import com.personalprojects.GameOfLife.UtilityClasses.XMLWriter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    
    /**
     * Constructor method. 
     * @param MainWindow
     * @param Gr
     * @throws ParserConfigurationException
     */
    public FileManagerPopup(MainWindow MainWindow, GameRunner Gr) throws ParserConfigurationException
    {
        this.xmlWriter = new XMLWriter();
        fc = new JFileChooser();
        mainWindow=MainWindow;
        gr=Gr;
        
        var ff = new FileNameExtensionFilter("Game Of Life", "GOL", "gol");
        fc.addChoosableFileFilter(ff);
        fc.setFileFilter(ff);
    }

    /**
     * Takes a file as an argument and uses an XML writer to turn it into a file. 
     * @param FileToExport The file object to be exported. 
     */
    @Override
    public void exportBoard(File FileToExport) 
    {
      
        SimCanvasWindow sw= gr.getSimWindowFromSimTableByID(callingRow);
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
    
    /**
     * Takes a file and turns it into a board object, and then starts a simulation using that board object.//
     * @param FileToImport the file object to be imported into our game. 
     */
    @Override
    public void importBoard(File FileToImport) {
        BoardObject bOb=null;
        try {
            System.out.println("passing board to XML writer");
            bOb=xmlWriter.getBoardFromXML(FileToImport);
        } catch (SAXException | ParserConfigurationException | IOException ex) {
           LoggingClass.WriteToLog(ex, "Error when loading Board Object", "SEVERE");
        } catch (URISyntaxException ex) {
            Logger.getLogger(FileManagerPopup.class.getName()).log(Level.SEVERE, null, ex);
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
    
    /**
     * Externally used. Lets a table tell us what row is calling this method sp we can point back to that row.
     * @param crow
     */
    public void setCallingRow(int crow)
    {
        callingRow=crow;
    }//end setCallingRow//
    
    /**
     * Shows the "save file" version of the interface. 
     */
    @Override
    public void showSaveInterface()
    {
        fc.setSelectedFile(new File("myGame.GOL")); //set default game file name//
        int returnVal = fc.showSaveDialog(FileManagerPopup.this);
        
             if (returnVal == JFileChooser.APPROVE_OPTION) {
                 File fileToSave = fc.getSelectedFile();
                 exportBoard(fileToSave);
            } else {
               System.out.println("file opening cancelled by user");
            }
    }
    
    /**
     * Shows the "open file" version of the interface
     */
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
