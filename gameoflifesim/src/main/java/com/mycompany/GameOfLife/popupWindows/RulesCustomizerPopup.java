/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.GameOfLife.popupWindows;

import com.mycompany.GameOfLife.MainWindow;
import com.mycompany.mavenproject1.DataTypes.RulesBundle;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JSpinner;
/**
 *
 * @author toast
 */
public class RulesCustomizerPopup extends JDialog implements WindowFocusListener
{
    Graphics gr;
    private final RulesBundle startingRules;
    private JSpinner starveSpinner, aliveSpinner, reviveSpinner, overpopSpinner;
    private JButton finishButton;
    private JLabel starveLabel, aliveLabel, reviveLabel, overpopLabel;
    private final String starveLabelText = "Cell starves with this many or fewer neighbors: ", aliveLabelText = "Cell survives with exactly this many neighbors: ", reviveLabelText="Dead cells revive with this many neighbors: ", overpopLabelText="Cells die of overpopulation with this many neighbors: ";
    private final String finishButtonText = "Confirm custom rules";
    private MainWindow mainWindow;
    public RulesCustomizerPopup(MainWindow MW)
    {
        mainWindow=MW;
        startingRules=MW.getRules();
        createAndShowGUI();
    }
    private void exportRulesToMainWindow()
    {
        RulesBundle rb = new RulesBundle();
        mainWindow.setRules(rb);
    }
    private void createAndShowGUI()
    {
        Container cc = this.getContentPane();
        cc.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        //setAlwaysOnTop(true);
        
        
        
        
        starveLabel = new JLabel(starveLabelText);
        gbc.fill=GridBagConstraints.HORIZONTAL;
        gbc.gridx=0;
        gbc.gridy=0;
        cc.add(starveLabel, gbc);
        
        aliveLabel= new JLabel(aliveLabelText);
        gbc.fill=GridBagConstraints.HORIZONTAL;
        gbc.gridx=0;
        gbc.gridy=1;
        cc.add(aliveLabel, gbc);
        
        reviveLabel = new JLabel(reviveLabelText);
        gbc.fill=GridBagConstraints.HORIZONTAL;
        gbc.gridx=0;
        gbc.gridy=2;
        cc.add(reviveLabel, gbc);
        
        overpopLabel = new JLabel(overpopLabelText);
        gbc.fill=GridBagConstraints.HORIZONTAL;
        gbc.gridx=0;
        gbc.gridy=3;
        cc.add(overpopLabel, gbc);
        
        
        starveSpinner = new JSpinner();
        gbc.fill=GridBagConstraints.HORIZONTAL;
        gbc.gridx=2;
        gbc.gridy=0;
        starveSpinner.setValue((Integer)startingRules.getStarveNumber());
        cc.add(starveSpinner,gbc);
        
        aliveSpinner = new JSpinner();
        gbc.fill=GridBagConstraints.HORIZONTAL;
        gbc.gridx=2;
        gbc.gridy=1;
        aliveSpinner.setValue((Integer)startingRules.getAliveNumber());
        cc.add(aliveSpinner,gbc);
        
        reviveSpinner = new JSpinner();
        gbc.fill=GridBagConstraints.HORIZONTAL;
        gbc.gridx=2;
        gbc.gridy=2;
        reviveSpinner.setValue((Integer)startingRules.getReviveNumber());
        cc.add(reviveSpinner,gbc);
        
        overpopSpinner = new JSpinner();
        gbc.fill=GridBagConstraints.HORIZONTAL;
        gbc.gridx=2;
        gbc.gridy=3;
        overpopSpinner.setValue((Integer)startingRules.getOverpopNumber());
        cc.add(overpopSpinner,gbc);
        
        
        finishButton = new JButton(finishButtonText);
        gbc.fill=GridBagConstraints.HORIZONTAL;
        gbc.gridx=0;
        gbc.gridy=4;
        gbc.gridwidth=3;
        finishButton.addActionListener(new ActionListener() 
            { 
                public void actionPerformed(ActionEvent e) { 
               exportRulesAndClose();
                } 
            } );
        cc.add(finishButton,gbc);
        pack();
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        addWindowListener(new WindowAdapter()
        {
            public void windowClosed(WindowEvent e)
                {
                    mainWindow.nullRulesWindow();
                }
                });
        addWindowFocusListener(this);
        
    }
    
    private void exportRulesAndClose()
    {
        RulesBundle exportedRules = new RulesBundle((Integer)starveSpinner.getValue(),(Integer)aliveSpinner.getValue(),(Integer)reviveSpinner.getValue(),(Integer)overpopSpinner.getValue());
        mainWindow.setRules(exportedRules);
        dispose();
    }
    
    /**
     *
     * @param e
     */
    @Override
    public void windowLostFocus(WindowEvent e) 
    {
        requestFocus();
    }

    @Override
    public void windowGainedFocus(WindowEvent e) {
      //do nothing, added for override//
    }

}//end RulesCustomizerPopup//
