/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.personalprojects.GameOfLife;

import com.personalprojects.GameOfLife.UtilityClasses.DocFilter;
import com.personalprojects.GameOfLife.UtilityClasses.LoggingClass;
import com.personalprojects.GameOfLife.popupWindows.FileManagerPopup;
import javax.xml.parsers.ParserConfigurationException;

/**
 *
 * @author evandleclair
 */
public class MainClass 
{    
    public static void main(String[] args) throws ParserConfigurationException
    {      
        LoggingClass.setup(); //sets up our error logging system//
        MainWindow mainInterface = new MainWindow(new DocFilter()); //create a new main interface window//
        GameRunner GR=new GameRunner(mainInterface); //create a new game runner object, which handles the actual boards and respective games//
        FileManagerPopup FM=new FileManagerPopup(mainInterface,GR);
        mainInterface.setGameRunner(GR); //assign our game runner object to the main window//
        mainInterface.setFileManager(FM);
        mainInterface.addRightClickMenuToTable(); //can only be done AFTER a game runner has been assigned//
        GR.UpdateTableOnMainWindow(); //links the GameRunner's table model to that of the main interface window, so they update in unision.//
        mainInterface.invokeMainWindow();//now that all the prep is done, we can show the window//
    }
}
