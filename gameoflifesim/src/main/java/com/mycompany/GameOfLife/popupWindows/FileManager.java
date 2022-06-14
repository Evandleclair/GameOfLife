/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.GameOfLife.popupWindows;

import com.mycompany.GameOfLife.BoardObject;
import com.mycompany.GameOfLife.GameRunner;
import com.mycompany.GameOfLife.MainWindow;
import com.mycompany.GameOfLife.SimCanvasWindow;
import com.mycompany.GameOfLife.XMLWriter;
import java.awt.BorderLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.JFileChooser;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;


/**
 *
 * @author toast
 */
public class FileManager extends JPanel implements FileManagerInterface, ActionListener {

    private final MainWindow mainInterface;
    private final GameRunner gr;
    private BoardObject boardObject;
    static private final String newline = "\n";
    JButton openButton, saveButton;
    private final XMLWriter xmlWriter;  
    JTextArea log;
    int callingRow=0; //used for exporting//
    JFileChooser fc;
    public FileManager(MainWindow MainWindow, GameRunner Gr) throws ParserConfigurationException
    {
        this.xmlWriter = new XMLWriter();
        fc = new JFileChooser();
        mainInterface=MainWindow;
        gr=Gr;
    }
    
    
    
    @Override
    public String convertBoardToString() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String convertStringToBoard() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void ExportBoard(File FileToExport) {
        SimCanvasWindow sw= gr.getSimWindowByID(callingRow);
        BoardObject boardDataBundle= sw.getBoardFromRunnable();
        try {
            xmlWriter.createFileFromBoard(boardDataBundle, FileToExport);
        } catch (TransformerException ex) {
            Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void ImportBoard(File FileToImport) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    
    public void setCallingRow(int crow)
    {
        callingRow=crow;
    }
    
    @Override
    public void ShowSaveInterface()
    {
        int returnVal = fc.showSaveDialog(FileManager.this);
             if (returnVal == JFileChooser.APPROVE_OPTION) {
                 File fileToSave = fc.getSelectedFile();
                 ExportBoard(fileToSave);
            } else {
               System.out.println("file opening cancelled by user");
            }
    }
    
    @Override
    public void ShowOpenInterface() {
        int returnVal = fc.showOpenDialog(FileManager.this);
          if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                //System.out.println(file.getName().toString());
                ImportBoard(file);
            } else {
               System.out.println("file opening cancelled by user");
            }
    }
    /*
    
    
    We need a class to handle XML functions, no other files need these functions so we keep them here//
    */
}
