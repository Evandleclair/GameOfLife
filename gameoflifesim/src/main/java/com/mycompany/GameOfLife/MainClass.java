/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.GameOfLife;

import com.mycompany.GameOfLife.popupWindows.FileManager;
import javax.xml.parsers.ParserConfigurationException;

/**
 *
 * @author toast
 */
public class MainClass 
{    
    public static void main(String[] args) throws ParserConfigurationException
    {      
        MainWindow mainInterface = new MainWindow(new DocFilter()); //create a new main interface window//
        GameRunner GR=new GameRunner(mainInterface); //create a new game runner object, which handles the actual boards and respective games//
        FileManager FM=new FileManager(mainInterface,GR);
        mainInterface.setGameRunner(GR); //assign our game runner object to the main window//
        mainInterface.setFileManager(FM);
        mainInterface.addRightClickMenuToTable(); //can only be done AFTER a game runner has been assigned//
        GR.UpdateTableOnMainWindow(); //links the GameRunner's table model to that of the main interface window, so they update in unision.//
        mainInterface.showTheMainWindow();//now that all the prep is done, we can show the window//
    }
}
