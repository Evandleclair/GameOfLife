/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.personalprojects.GameOfLife.UtilityClasses;
import java.io.File;
import java.io.IOException;
import java.util.logging.*;
/**
 * Custom logging class used by the program. 
 * @author evandleclair
 */
public class LoggingClass {
    
    private static final String WIN_OUTPUT_DESTINATION = System.getenv("APPDATA") + "\\GameOfLifeSim";  
    private static final String NUX_OUTPUT_DESTINATION = "/home/logs/GameOfLife";
    private static final Logger LOGGER = Logger.getLogger(LoggingClass.class.getName());
    static Handler fileHandler = null;
    private static boolean validDir; //represents if we have anywhere to save the logs
    private static String OS = System.getProperty("os.name").toLowerCase();
    /**
     *Setup method checks if the directory in Appdata exists, creates it if it does not, and then creates the filehandler
     * that is used through the other method
     */
    public static void setup() 
    {
        String destinationToUse;
        if (isWindows())
            destinationToUse=WIN_OUTPUT_DESTINATION;
        else if (isLinux())
            destinationToUse=NUX_OUTPUT_DESTINATION;
        else
        {
            validDir=false;
            return; //we do not support any other OS. break loop./ 
        }
        try 
        {
        File directory = new File(destinationToUse);
        if (!directory.exists())
            {
                directory.mkdir();
            }
            validDir=true; //we have a directory to write to
            fileHandler = new FileHandler(destinationToUse+"./logfile.log");//file
            SimpleFormatter simple = new SimpleFormatter();
            fileHandler.setFormatter(simple);

            LOGGER.addHandler(fileHandler);//adding Handler for file
            validDir=true;
        } catch (IOException e) {
            System.out.println("Logger setup failed.");
            validDir=false;
        }
    }//end setup//

    /**
     * The actual log writing method.
     * @param e the exception that occurred 
     * @param customMessage a custom message to include in the log
     * @param severity String that determines what severity level to log the error as. 
     */
    public static void WriteToLog(Exception e, String customMessage, String severity)
    {
        if (loggingSystemValid())
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
    }//end writetolog
    
    /**
     * alternate method for writing things that allows for writing things are not necessarily exceptions
     * @param customMessage
     * @param severity
     */
    public static void WriteToLog(String customMessage, String severity)
    {
        if (loggingSystemValid()) //only try to write to log if we are on windows or linux and our directory is valid
        {
            Level sevLevel;
            sevLevel = switch (severity.toUpperCase()) {
                case "WARNING" -> Level.WARNING;
                case "INFO" -> Level.INFO;    
                default -> Level.FINE;
            }; //end switch//
            System.out.println(StringMaster.combineStrings(new String[]{"Logger logged user error"}));
            LOGGER.log(sevLevel,customMessage);
        }
    }//end writetolog
    
    private static boolean isWindows() {
        return OS.contains("win");
    }
    private static boolean isLinux() {
        return OS.contains("nux");
    }
    /**
     * Checks if we are on linux or windows, then checks if we have a valid directory to log to. 
     * @return  whether we are on a supported environment and have a valid directory
     */
    private static boolean loggingSystemValid()
    {
        return validDir&&(isLinux()||isWindows());
    }
    
}//end class/
