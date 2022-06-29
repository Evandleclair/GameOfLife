/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.personalprojects.GameOfLife.UtilityClasses;
import java.io.File;
import java.io.IOException;
import java.util.logging.*;
/**
 *
 * @author evandleclair
 */
public class LoggingClass {
    
    private static final String OUTPUT_DESTINATION = System.getenv("APPDATA") + "\\GameOfLifeSim";  
    private static final Logger LOGGER = Logger.getLogger(LoggingClass.class.getName());
    static Handler fileHandler = null;
    
    public static void setup() 
    {
        try 
        {
            File directory = new File(OUTPUT_DESTINATION);
            if (!directory.exists())
            {
                directory.mkdir();
            }
            fileHandler = new FileHandler(OUTPUT_DESTINATION+"./logfile.log");//file
            SimpleFormatter simple = new SimpleFormatter();
            fileHandler.setFormatter(simple);

            LOGGER.addHandler(fileHandler);//adding Handler for file

        } catch (IOException e) {
            System.out.println("Logger setup failed.");
        }
    }//end setup//

    
    public static void WriteToLog(Exception e, String customMessage, String severity)
    {
        Level sevLevel;
        sevLevel = switch (severity.toUpperCase()) {
            case "WARNING" -> Level.WARNING;
            case "SEVERE" -> Level.SEVERE;
            default -> Level.FINE;
        }; //end switch//
        System.out.println(StringMaster.combineStrings(new String[]{"Logger logged error", e.getStackTrace().toString()}));
        LOGGER.log(sevLevel,customMessage,e);
    }
}