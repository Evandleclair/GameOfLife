/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.GameOfLife;

import com.mycompany.GameOfLife.Interfaces.GameRunnerInterface;
import com.mycompany.mavenproject1.DataTypes.RulesBundle;
import com.mycompany.mavenproject1.DataTypes.simWindowInfo;
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
    
    private final MainWindow mainWindow;
    private ArrayList<simWindowInfo> simWindows = new ArrayList<>();
    private static int gamesRunning=0, gamesRan =0;
    private String[] colNames = {"Game","Generation","Status","Tick Speed"};
    DefaultTableModel dtm = new DefaultTableModel(null,colNames);
    private JTable simTable = new JTable(dtm);
    private final RulesBundle conwayDefault = new RulesBundle(0,2,3,4);
    private File storedFileToImport=null;
    private int importedGensToRun=0;
    
    
    public GameRunner(MainWindow MI)
    {
        mainWindow=MI;
        HideGameColumn();
    }//end constructor//
    
    private void HideGameColumn() //we are storing games in the column but do not want to display them as that is pointless//
    {
       // simTable.getColumnModel().getColumn(2).setMaxWidth(0);
    }
            
   
   
    
    @Override
    public void createSimWindowAndStartSim(int dims) 
    {
        SimCanvasWindow simWindowObj= new SimCanvasWindow(dims, "GAME " + gamesRan, mainWindow, getRulesSet());
        addWindowToTable(simWindowObj);
    }
    
    @Override
    public void createSimWindowAndStartSim(BoardObject BOb) 
    {
        BOb.setName("GAME "+gamesRunning);
        SimCanvasWindow simWindowObj = new SimCanvasWindow(mainWindow, BOb);
        addWindowToTable(simWindowObj);
    }
    
    public void addWindowToTable(SimCanvasWindow sW)
    {
        if (gamesRunning<20)
        {
            simWindows.add(new simWindowInfo("GAME "+gamesRan,sW));
            gamesRan++;
            gamesRunning++;
            sW.runSimWindowStartupTasks();
            sW.startSimRunnable();
            updateSimWindowTable();
        }//end if gamesRunning is smaller 20//
        else
        {
            JOptionPane.showMessageDialog(mainWindow, "Cannot have more than twenty games running at the same time");
        }//end else
    }//end addWindowToTable//

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
    
    @Override
    public int getGamesRunning() 
    {
       return gamesRunning;
    }//end getGamesRunning//
    
    @Override
    public int getOpenGamesCount()
    {
        int retInt;
        retInt=simTable.getRowCount();
        return retInt;
    }//end getOpensGamesCount//
    
    @Override
    public void setGamesRunning(int GamesRunning) {
       gamesRunning=GamesRunning;
    }
    
    private RulesBundle getRulesSet()
    {
        RulesBundle rb = conwayDefault;
        if (mainWindow.useCustomRules())
        {
            rb=mainWindow.getRules();
        }
        return rb;
    }
    
    public int getImportedGens()
    {
        return importedGensToRun;
    }
    
    public void setImportedGens(int i)
    {
        importedGensToRun=i;
    }
    
    @Override
    public SimCanvasWindow getSimWindowByID(int rowID)
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
    }//end getSimWindowByID//
     
    private void updateSimWindowTable()
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
    }//end updateSimWindowTable//
    
      private void addSimWindowToTable(simWindowInfo s)
    {
        DefaultTableModel model = (DefaultTableModel) simTable.getModel();
        model.addRow(new Object[]{s.getID(),"name","generation","tickspeed",s.getOBJ()});
    }
    
    public void focusOnSpecificSimWindow(int rowID)
    {
        getSimWindowByID(rowID).requestFocus();
    }
    
    public void updateTickSpeedOnSpecificWindow(int rowID)
    {
        int tickTime =mainWindow.getTickTime();
        getSimWindowByID(rowID).updateTickSpeed(tickTime);
        updateTickTime(rowID,tickTime);
        
    }
       
    public void closeSpecificSimWindow(int rowID)
    {
         getSimWindowByID(rowID).pleaseCloseMe();
    }
   
    public void addGenerationsToSpecificSimWindow(int rowID, int gensToAdd)
    {
        SimCanvasWindow SW= getSimWindowByID(rowID);
        SW.pleaseAddGenerations(gensToAdd);
        SW.pleaseLookAtMe();
    }
    public void addGenerationsToSpecificSimWindow(String name, int gensToAdd)
    {
        SimCanvasWindow SW= getSimWindowByID(getSimRowByName(name));
        SW.pleaseAddGenerations(gensToAdd);
        SW.pleaseLookAtMe();
    }
    
    @Override
    public void UpdateTableOnMainWindow() {
        mainWindow.updateTableModel(simTable.getModel());
    }
    
    public void updateSimColumnsOnTable(String IDname, String status, int curGen, int tSpeed)
    {
        //System.out.println("trying to find " + IDname + " which has a Gen value of " + curGen);
        int rowToUpdate = getSimRowByName(IDname);
        if (rowToUpdate != -1)
        {
            simTable.getModel().setValueAt(curGen,rowToUpdate,1);
            simTable.getModel().setValueAt(status,rowToUpdate,2);
            simTable.getModel().setValueAt(tSpeed,rowToUpdate,3);
        }
        else
        {
            System.out.println("Row did not exist");
        }
    }
    
    
    public void updateTickTime(int rowID,int tickTime)
    {
        simTable.getModel().setValueAt(tickTime,rowID,3);
    }
    
    public int getSimRowByName(String IDname)
    {
        int retInt = -1; //signifies not found//
        for (int i=0; i<simTable.getRowCount();i++)
        {
            String rowGameID = (String)simTable.getValueAt(i, 0);
            //System.out.println("comparing " + rowGameID + " to input of " + IDname);
            if (rowGameID.equals(IDname))
            {
                retInt=i;
            }
        }
        return retInt;
    }
    
    public void storeGameFile(File file)
    {
        storedFileToImport=file;
    }
    public File getStoredFile()
    {
        return storedFileToImport;
    }
    
    public void endAllGames()
    {
        SimCanvasWindow sw=null;
        for (simWindowInfo s : simWindows)
            {
                sw=(SimCanvasWindow) s.getOBJ();
                sw.pleaseCloseMe();
            } 
    }

    @Override
    public void reportClosedGame() 
    {
       gamesRunning--;
    }
}//end class//
