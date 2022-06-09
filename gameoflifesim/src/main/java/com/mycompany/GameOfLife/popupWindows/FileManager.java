/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.GameOfLife.popupWindows;

import com.mycompany.GameOfLife.MainWindow;
import java.awt.BorderLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
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
       static private final String newline = "\n";
    JButton openButton, saveButton;
    JTextArea log;
    JFileChooser fc;
    public FileManager(MainWindow MainWindow)
    {
        fc = new JFileChooser();
        mainInterface=MainWindow;
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
    public void ExportBoard() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    @Override
    public void ImportBoard() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void ShowOpenInterface() {
        int returnVal = fc.showOpenDialog(FileManager.this);
          if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                System.out.println(file.getName().toString());
        
            } else {
               System.out.println("file opening cancelled by user");
            }
    }
    /*
    We need a class to handle XML functions, no other files need these functions so we keep them here//
    */
    private class XMLfunctions {
        
        static String filePathXML = "";
        
        public void createFileFromBoard()
        {
            
        }
       // public 
        
    }
    
}
