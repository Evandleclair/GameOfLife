/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.GameOfLife;

import com.mycompany.mavenproject1.DataTypes.simWindowInfo;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author toast
 */
public class GameRunner implements GameRunnerInterface {
    
    private final MainWindow mainInterface;
    private ArrayList<simWindowInfo> simWindows = new ArrayList<>();
    private static int gamesRunning=0;
    private String[] colNames = {"Game","Generation"};
    DefaultTableModel dtm = new DefaultTableModel(null,colNames);
    
    private JTable gameTable = new JTable(dtm);
    public GameRunner(MainWindow MI)
    {
    mainInterface=MI;
    HideGameColumn();
    }
    private void HideGameColumn()
    {
        gameTable.getColumnModel().getColumn(1).setMaxWidth(0);
        
    }
            
    @Override
    public void createGame(int dims) {
        SimulatorWindow frame = new SimulatorWindow(dims, "GAME "+gamesRunning, mainInterface);
        simWindows.add(new simWindowInfo("GAME "+gamesRunning,frame));
        gamesRunning++;
        frame.setVisible(true); //necessary as of 1.3
        frame.setMyGraphics();
        frame.spinUpSim();
        updateSimWindowTable();
        
    }

    @Override
    public void destroyGame(simWindowInfo s) {
        System.out.println("removing "  + s.getID());
        DefaultTableModel model = (DefaultTableModel) gameTable.getModel();
       
        
        for (simWindowInfo s2 : simWindows)
        {
            System.out.println(s2.getID());
            if (s2.getID().equals(s.getID()))
            {
                simWindows.remove(s2);
                break;
            }
        }
        for (int i=0; i<gameTable.getRowCount();i++)
        {
             final String rowGameID = (String)gameTable.getValueAt(i, 0);
             if (rowGameID.equals(s.getID()))
                 {
                 model.removeRow(i);
                 }
        }
    }//end destroygame//
    @Override
    public int getGamesRunning() {
       return gamesRunning;
    }
    
    public int getOpenGamesCount()
    {
        int retInt;
        retInt=gameTable.getRowCount();
        return retInt;
    }
    
    @Override
    public void setGamesRunning(int GamesRunning) {
       gamesRunning=GamesRunning;
    }
    
    @Override
    public SimulatorWindow getSimWindowByID(int rowID)
    {
        SimulatorWindow sw = null;
        //String idToFind = gameTable.getModel().getValueAt(rowID, 0).toString();
        String idToFind = gameTable.getValueAt(rowID, 0).toString();
        if (rowID>-1)
        {
            for (simWindowInfo s : simWindows)
            {
                if (s.getID()==idToFind)
                {
                    sw=(SimulatorWindow) s.getOBJ();
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
            for (int i=0; i<gameTable.getRowCount();i++)
            {
                 final String rowGameID = (String)gameTable.getValueAt(i, 0);
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
        DefaultTableModel model = (DefaultTableModel) gameTable.getModel();
        model.addRow(new Object[]{s.getID(),"oo",s.getOBJ()});
    }
      
    public void focusFrame(int rowID)
    {
        //simWindowInfo focusedWindow = simWindows.get(simWindows.indexOf(gameTable.getModel().getValueAt(rowID, 0)));
        // SimulatorWindow sw= (SimulatorWindow)focusedWindow.getOBJ();
        /// sw.pleaseLookAtMe();
        getSimWindowByID(rowID).requestFocus();
    }
       
    public void closeFrame(int rowID)
    {
         getSimWindowByID(rowID).pleaseCloseMe();
    }
   
    @Override
    public void UpdateTableOnWindow() {
        mainInterface.updateTable(gameTable.getModel());
    }
     /*
      private  int getRowByValue(TableModel model, Object value) 
    {
    int retInt = -1;
    for (int i = model.getRowCount() - 1; i >= 0; --i) 
        {
        System.out.println(model.getValueAt(i, 0) + " versus " + value);
        if (model.getValueAt(i, 0).equals(value)) 
            {
            
            retInt=i;
            }    
        }
    return retInt;
    }
      */

}//end class//