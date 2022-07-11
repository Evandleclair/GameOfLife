/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.personalprojects.GameOfLife;

import com.personalprojects.GameOfLife.Interfaces.GameRunnerInterface;
import com.personalprojects.GameOfLife.DataTypes.RulesBundle;
import com.personalprojects.GameOfLife.DataTypes.simWindowInfo;
import java.io.File;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author evandleclair
 */
public class GameRunner implements GameRunnerInterface {
    
    private final MainWindow mainWindow; //this is the main GUI window. passed by argument during the constructor//
    private final ArrayList<simWindowInfo> simWindows = new ArrayList<>(); //an array list of our currently running simulations, stored seperate from the table//
    private static int gamesRunning=0, gamesRan =0; //games running is the number of active games, games ran is the ammount of games that have been run since the program was opened//
    //gamesRan is used for naming simulations//
    private final String[] colNames = {"Game","Generation","Lifespan","Status","Tick Speed"}; 
    DefaultTableModel dtm = new DefaultTableModel(null,colNames); //create a new table model using our column names..
    private JTable simTable = new JTable(dtm); //simTable holds onto all the games. 
    private final RulesBundle conwayDefault = new RulesBundle(0,2,3,4); //conway's default rules are always the same//
    private RulesBundle customRules = new RulesBundle(0,2,3,4);
    private File storedFileToImport=null; //used by file import functions. for now it is null//
    private int importedGensToRun=0; 
    
    /**
     * This constructor takes our main window as an argument, so it knows what window is being used as the GUI 
     * @param MI
     */
    public GameRunner(MainWindow MI)
    {
        mainWindow=MI;
        MI.setRulesTableFieldValues(conwayDefault);
    }//end constructor//
    
    /**
     * Creates a new simulator window according to the dimensions given, and then adds that to the table tracking the existing windows. 
     * This will also create a new board object
     * @param dims 
     */
    @Override
    public void createSimWindowAndStartSim(int dims) 
    {
        SimCanvasWindow simWindowObj= new SimCanvasWindow(dims, "GAME " + gamesRan, mainWindow, getRulesSet());
        addWindowToListAndUpdateTable(simWindowObj);
    }
    
    /**
     * Creates a new simulator window using an existing board object. 
     * @param BOb 
     */
    @Override
    public void createSimWindowAndStartSim(BoardObject BOb) 
    {
        BOb.setName("GAME "+gamesRan);
        SimCanvasWindow simWindowObj = new SimCanvasWindow(mainWindow, BOb);
        addWindowToListAndUpdateTable(simWindowObj);
    }
    
    /**
     * Adds the simulator window to the list that tracks all existing windows. It then invokes the function that updates the table according to the list. 
     * @param sW the window to be added to the table. 
     */
    public void addWindowToListAndUpdateTable(SimCanvasWindow sW)
    {
        if (gamesRunning<20)
        {
            simWindows.add(new simWindowInfo("GAME "+gamesRan,sW));
            gamesRan++; //total number of games ran increases by 1.
            gamesRunning++;
            //sW.runSimWindowStartupTasks();
            sW.startSimRunnable();
            updateSimWindowTableToMatchList();
        }//end if gamesRunning is smaller 20//
        else
        {
            JOptionPane.showMessageDialog(mainWindow, "Cannot have more than twenty games running at the same time");
        }//end else
    }//end addWindowToListAndUpdateTable//

    /**
     * Destroys the window that matches the info given as argument. 
     * @param s the simWindowInfo package that should be deleted from the table. 
     */
    @Override
    public void destroyGame(simWindowInfo s) 
    {
        System.out.println("removing "  + s.getID());
        DefaultTableModel model = (DefaultTableModel) simTable.getModel();

        for (simWindowInfo s2 : simWindows)
        {
            System.out.println(s2.getID());
            if (s2.getID().equals(s.getID()))
            {
                simWindows.remove(s2);
                break;
            }//end if//
        }//end enhanced for loops// 
        for (int i=0; i<simTable.getRowCount();i++)
        {
            final String rowGameID = (String)simTable.getValueAt(i, 0);
            if (rowGameID.equals(s.getID()))
            {
                model.removeRow(i);
            }//end if//
        }//end for loop//
    }//end destroygame//
    
   
    /**
     * Gets the rules bundle. If we do not have custom rules enabled, it gets the default conway rules. If not, gets the custom ones.
     * @return  a rules bundle from the GUI window. 
     */
    private RulesBundle getRulesSet()
    {
        RulesBundle rb = conwayDefault;
        if (mainWindow.useCustomRules())
        {
            rb=customRules;
        }
        return rb;
    }
    
    /**
     * Retrieves from the table the simulator window that matches the ID given as argument. 
     * @param rowID
     * @return 
     */
    @Override
    public SimCanvasWindow getSimWindowFromSimTableByID(int rowID)
    {
        SimCanvasWindow sw = null;
        //String idToFind = simTable.getModel().getValueAt(rowID, 0).toString();
        String idToFind = simTable.getValueAt(rowID, 0).toString();
        if (rowID>-1)
        {
            for (simWindowInfo s : simWindows)
            {
                if (s.getID()==idToFind)
                {
                    sw=(SimCanvasWindow) s.getOBJ();
                }
            } 
        }
        System.out.println("row " + rowID + " game " + " "+ idToFind);
        return sw;
    }//end getSimWindowFromSimTableByID//
     
    /**
     * Matches the table of simulator windows to the contents of the simWindows list. 
     */
    private void updateSimWindowTableToMatchList()
    {
        for ( simWindowInfo s : simWindows)
        {
            boolean itemPresent=false;
            for (int i=0; i<simTable.getRowCount();i++)
            {
                 final String rowGameID = (String)simTable.getValueAt(i, 0);
                 if (rowGameID==s.getID())
                 {
                     itemPresent=true;
                     break;
                 }
            }
            if (itemPresent==false)
            {
                addSimWindowToTable(s);
            }
        }
    }//end updateSimWindowTableToMatchList//
    
    /**
     * Adds a given simWindowInfo bundle to the simulator table. TODO
     * @param s 
     */
    private void addSimWindowToTable(simWindowInfo s)
    {
        DefaultTableModel model = (DefaultTableModel) simTable.getModel();
        model.addRow(new Object[]{s.getID(),"name","generation","Lifespan","tickspeed",s.getOBJ()});
    }
    
    /**
     * Invokes the "please look at me" function on a specified simulator window, by ID.
     * @param rowID the row ID of the window to be focused on. 
     */
    public void focusOnSpecificSimWindow(int rowID)
    {
        getSimWindowFromSimTableByID(rowID).pleaseLookAtMe();
    }
    
    /**
     * Updates the simulator tick speed on a specific window, by ID. 
     * @param rowID the row ID of the window to be focused on. 
     */
    public void updateTickSpeedOnSpecificWindow(int rowID)
    {
        int tickTime =mainWindow.getTickTime();
        getSimWindowFromSimTableByID(rowID).setTickSpeed(tickTime);
        updateTickTime(rowID,tickTime);
        
    }
    
    /**
     * Invokes the close event on the window associated with that row ID.
     * @param rowID the row ID of the window to be focused on. 
     */
    public void closeSpecificSimWindow(int rowID)
    {
         getSimWindowFromSimTableByID(rowID).pleaseCloseMe();
    }
    
    /**
     * Invokes the add generations event on the window associated with a rowID. 
     * @param rowID the row ID of the window to be focused on. 
     * @param gensToAdd 
     */
    public void addGenerationsToSpecificSimWindow(int rowID, int gensToAdd)
    {
        SimCanvasWindow SW= getSimWindowFromSimTableByID(rowID);
        SW.pleaseAddGenerations(gensToAdd);
        SW.pleaseLookAtMe();
    }
    
    /**
     * Finds a specific window by name and invokes the add generations function on it. 
     * @param name
     * @param gensToAdd 
     */
    public void addGenerationsToSpecificSimWindow(String name, int gensToAdd)
    {
        SimCanvasWindow SW= getSimWindowFromSimTableByID(getRowIDFromSimTableByName(name));
        SW.pleaseAddGenerations(gensToAdd);
        SW.pleaseLookAtMe();
    }
    
    /**
     * Updates the table model on the GUI to match the one that is used here. 
     */
    @Override
    public void UpdateTableOnMainWindow() {
        mainWindow.updateTableModel(simTable.getModel());
    }
    
    /**
     * Passes values along to the GUI window so that they can be displayed on our table of simulators that are running. 
     * @param IDname the ID of the thread/runnable that runs that simulation. 
     * @param status the current status of the simulation IE: paused, terminated, etc.
     * @param curGen the current generation of that simulation
     * @param lifeSpan the last generation to calculate
     * @param tSpeed the time in milliseconds between each generation, called "tick speed". 
     */
    public void updateSimColumnsOnTable(String IDname, String status, int curGen, int lifeSpan, int tSpeed)
    {
        int rowToUpdate = getRowIDFromSimTableByName(IDname);
        if (rowToUpdate != -1)
        {
            simTable.getModel().setValueAt(curGen,rowToUpdate,1);
            simTable.getModel().setValueAt(lifeSpan,rowToUpdate,2);
            simTable.getModel().setValueAt(status,rowToUpdate,3);
            simTable.getModel().setValueAt(tSpeed,rowToUpdate,4);
        }
        else
        {
            System.out.println("Row did not exist");
        }
    }
    
    /**
     * 
     * @param rowID the row ID of the simulation we need to update.
     * @param tickTime the new "tick speed" to apply to the simulation 
     */
    public void updateTickTime(int rowID,int tickTime)
    {
        simTable.getModel().setValueAt(tickTime,rowID,3);
    }
    
    /**
     * Using the name of a simulation, retreived it from the sim table. 
     * @param IDname
     * @return an integer representing the row that name can be found on. 
     */
    public int getRowIDFromSimTableByName(String IDname)
    {
        int rowID = -1; //signifies not found//
        for (int i=0; i<simTable.getRowCount();i++)
        {
            String rowGameID = (String)simTable.getValueAt(i, 0);
            //System.out.println("comparing " + rowGameID + " to input of " + IDname);
            if (rowGameID.equals(IDname))
            {
                rowID=i;
            }
        }
        return rowID;
    }
    
   
    /**
     * Invokes the "please close me" event on every sim in our table of sim windows
     */
    public void endAllGames()
    {
        SimCanvasWindow sw=null;
        for (simWindowInfo s : simWindows)
        {
            sw=(SimCanvasWindow) s.getOBJ();
            sw.pleaseCloseMe();
        } 
    }
    /**
     * Invoked by windows when they close themselves, so that the game runner can keep track of window closures. Only used externally. 
     */
    @Override
    public void reportClosedGame() 
    {
       gamesRunning--;
    }
    
    /*
    Self explanatory get and set methods follow. 
    */
    
    public RulesBundle getConwayDefault()
    {
        return conwayDefault;
    }
    
    public void setCustomRules(RulesBundle rb)
    {
        customRules=rb;
    }        
   
    
    public RulesBundle getCustomRules()
    {
         return customRules;
    }
    
    public int getImportedGens()
    {
        return importedGensToRun;
    }
    
    public void setImportedGens(int i)
    {
        importedGensToRun=i;
    }
    
    public void storeGameFile(File file)
    {
        storedFileToImport=file;
    }
    
    public File getStoredFile()
    {
        return storedFileToImport;
    }
     /**
     * 
     * @return 
     */
    @Override
    public int getGamesRunning() 
    {
       return gamesRunning;
    }//end getGamesRunning//
    /**
     * 
     * @return 
     */
    @Override
    public int getOpenGamesCount()
    {
        return simTable.getRowCount();
    }//end getOpensGamesCount//
    /**
     * 
     * @param GamesRunning 
     */
    @Override
    public void setGamesRunning(int GamesRunning) {
       gamesRunning=GamesRunning;
    }
}//end class//
