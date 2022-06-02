/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.GameOfLife;


import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeListener;
import javax.swing.*;

/**
 *
 * @author toast
 */
public class TablePopUp extends JFrame implements ActionListener {
    
    JPopupMenu popup;
   
    //protected Action bringToFrontAction;
    protected JMenuItem bringToFrontMenuItem = new JMenuItem (new bringToFrontAction());
    protected JMenuItem pauseResumeMenuItem = new JMenuItem (new pauseResumeAction());
    protected JMenuItem closeMenuItem = new JMenuItem (new closeGameAction());
    MainInterface mainInterface;
    int callingRow;
    
    
    public TablePopUp(MainInterface mi)
    {
        popup = new JPopupMenu();
        mainInterface=mi;
        bringToFrontMenuItem.addActionListener(this);
        JMenuItem[] menuItems = {bringToFrontMenuItem, closeMenuItem};
        for (JMenuItem j : menuItems)
        {
            popup.add(j);
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        Component c = (Component)e.getSource();
        JPopupMenu popup = (JPopupMenu)c.getParent();
        JTable table = (JTable)popup.getInvoker();
        System.out.println(table.getSelectedRow() + " : " + table.getSelectedColumn());
        callingRow=table.getSelectedRow();
    }
    public void ShowPopUp(MouseEvent e)
    {
        popup.show(e.getComponent(), e.getX(), e.getY());
    }
   
    private void Test()
    {
        System.out.println("eee");
    }

    private Icon bringToFrontAction() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public class bringToFrontAction extends AbstractAction {
        public bringToFrontAction()
        {
            super("Bring to Front");
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("This will bring window to front");
            mainInterface.focusFrame(callingRow);
            
        }
        
    }
    
     public class pauseResumeAction extends AbstractAction {
        public pauseResumeAction()
        {
            super("Pause / Resume");
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("This will pause or resume a game");
            throw new java.lang.UnsupportedOperationException("Not supported yet.");
            
        }
    }//end pauseresume action//
     
    public class exportGameAction extends AbstractAction {
        public exportGameAction()
        {
            super("Export Game to Text File");
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("This will export a game");
            throw new java.lang.UnsupportedOperationException("Not supported yet.");
            
        }
    }//end exportgame action//
    
     public class closeGameAction extends AbstractAction {
        public closeGameAction()
        {
            super("Close Game");
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("This will close a game");
            mainInterface.closeFrame(callingRow);
        }
    }//end pauseresume action//
    
}
