/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.personalprojects.GameOfLife.popupWindows;



import com.personalprojects.GameOfLife.GameRunner;
import com.personalprojects.GameOfLife.MainWindow;
import com.personalprojects.GameOfLife.UtilityClasses.StringMaster;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeListener;
import javax.swing.*;

/**
 *
 * @author evandleclair
 */
public class TablePopUp extends JFrame implements ActionListener {
    
    JPopupMenu popup;
    protected JMenuItem bringToFrontMenuItem = new JMenuItem (new bringToFrontAction());
    protected JMenuItem pauseResumeMenuItem = new JMenuItem (new pauseResumeAction());
    protected JMenuItem closeMenuItem = new JMenuItem (new closeGameAction());
    protected JMenuItem addGenerationsMenuItem = new JMenuItem (new addGenerationsAction());
    protected JMenuItem exportGameMenuItem = new JMenuItem (new exportGameAction());
    protected JMenuItem updateTickTimeMenuItem = new JMenuItem (new updateTickTimeAction());
    private String addGenLabel, updateTickLabel;
    MainWindow mainWindow;
    GenerationEntryPopup genEntryPopup;
    GameRunner gr;
    int callingRow;
    
    public TablePopUp(MainWindow mi)
    {
        popup = new JPopupMenu();
        mainWindow=mi;
        gr = mi.getGameRunner();
        bringToFrontMenuItem.addActionListener(this);
        JMenuItem[] menuItems = {bringToFrontMenuItem, closeMenuItem, addGenerationsMenuItem, exportGameMenuItem,updateTickTimeMenuItem};
        for (JMenuItem j : menuItems)
        {
            popup.add(j);
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) 
    {
        Component c = (Component)e.getSource();
        JPopupMenu popup = (JPopupMenu)c.getParent();
        JTable table = (JTable)popup.getInvoker();
        System.out.println(table.getSelectedRow() + " : " + table.getSelectedColumn());
        //addGenLabel=StringMaster.combineStrings(new String[]{"Add ",String.valueOf(mainWindow.getGenerationsToRun())," Generations"});
        callingRow=table.getSelectedRow();
    }
    public void ShowPopUp(MouseEvent e, int rowselected)
    {
        callingRow = rowselected;
        updateTickLabel=StringMaster.combineStrings(new String[]{"Update tick time to: ",String.valueOf(mainWindow.getTickTime()), " MS"});
        updateTickTimeMenuItem.setText(updateTickLabel);
        popup.show(e.getComponent(), e.getX(), e.getY());
    }
    
    public class bringToFrontAction extends AbstractAction {
        public bringToFrontAction()
        {
            super("Bring to Front");
        }
        @Override
        public void actionPerformed(ActionEvent e) 
        {
            System.out.println("This will bring window to front");
            gr.focusOnSpecificSimWindow(callingRow);
        }
    }
    
    public class pauseResumeAction extends AbstractAction 
    {
        public pauseResumeAction()
        {
           super("Pause / Resume");
        }
        
        @Override
        public void actionPerformed(ActionEvent e) 
        {
            System.out.println("This will pause or resume a game");
            throw new java.lang.UnsupportedOperationException("Not supported yet.");
        }
    }//end pauseresume action//
     
    public class exportGameAction extends AbstractAction 
    {
        public exportGameAction()
        {
            super("Export Game to .GOL File");
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            mainWindow.showFileSaveInterface(callingRow);
        }
    }//end exportgame action//
    
    public class closeGameAction extends AbstractAction 
    {
        public closeGameAction()
        {
            super("Close Game");
        }
        @Override
        public void actionPerformed(ActionEvent e) 
        {
            System.out.println("This will close a game");
            gr.closeSpecificSimWindow(callingRow);
        }
    }//end pauseresume action//
     
    public class addGenerationsAction extends AbstractAction 
    {
        public addGenerationsAction()
        {
            super("Open menu to add Generations");
        }
        
        @Override
        public void actionPerformed(ActionEvent e) 
        {    
            System.out.println("adding generations" + mainWindow.getGenerationsToRun());
            genEntryPopup=new GenerationEntryPopup(mainWindow,callingRow);
            gr.focusOnSpecificSimWindow(callingRow);
        }
    }//end addGenerationsAction//
    
    public class updateTickTimeAction extends AbstractAction 
    {
        public updateTickTimeAction(){}
        
        @Override
        public void actionPerformed(ActionEvent e) 
        {    
            System.out.println("adding generations");
            gr.updateTickSpeedOnSpecificWindow(callingRow);
        }
    }//end addGenerationsAction//
}//end tablepopupclass/
