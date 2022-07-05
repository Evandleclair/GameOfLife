/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.personalprojects.GameOfLife.popupWindows;

import com.personalprojects.GameOfLife.MainWindow;
import com.personalprojects.GameOfLife.DataTypes.RulesBundle;
import com.personalprojects.GameOfLife.GameRunner;
import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
/**
 *
 * @author evandleclair
 */
public class RulesCustomizerPopup extends JDialog
{
    Graphics gr;
    private final RulesBundle startingRules;
    private JSpinner starveSpinner, aliveSpinner, reviveSpinner, overpopSpinner;
    private JButton finishButton, cancelButton;
    private JLabel starveLabel, aliveLabel, reviveLabel, overpopLabel;
    private final String starveLabelText = "Cell starves with this many or fewer neighbors: ", aliveLabelText = "Cell survives with exactly this many neighbors: ", reviveLabelText="Dead cells revive with this many neighbors: ", overpopLabelText="Cells die of overpopulation with this many neighbors: ";
    private final String finishButtonText = "Confirm custom rules", cancelButtonText = "Cancel";
    private MainWindow mainWindow;
    private GameRunner gameRunner;
    
    public RulesCustomizerPopup(JFrame Frame)
    {
        super(Frame);
        setModal(true);
        mainWindow=(MainWindow)Frame;
        gameRunner=mainWindow.getGameRunner();
        startingRules=gameRunner.getCustomRules();
        createAndShowGUI();
    }
   
    private void createAndShowGUI()
    {
        Container cc = this.getContentPane();
        cc.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        //setAlwaysOnTop(true);
        int startVal=0;
        SpinnerModel model;
        setUndecorated(true);
        
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
        startVal = (Integer)startingRules.getStarveNumber();
        
        model  =
        new SpinnerNumberModel(startVal, //initial value
                               0, //min
                               99, //max
                               1);                //step
        starveSpinner.setModel(model);
        ((JSpinner.DefaultEditor) starveSpinner.getEditor()).getTextField().setEditable(false);
        cc.add(starveSpinner,gbc);
        
        aliveSpinner = new JSpinner();
        gbc.fill=GridBagConstraints.HORIZONTAL;
        gbc.gridx=2;
        gbc.gridy=1;
        //aliveSpinner.setValue();
        startVal=(Integer)startingRules.getAliveNumber();
        model  =
        new SpinnerNumberModel(startVal, //initial value
                               0, //min
                               99, //max
                               1);                //step
        aliveSpinner.setModel(model);
        ((JSpinner.DefaultEditor) aliveSpinner.getEditor()).getTextField().setEditable(false);
        cc.add(aliveSpinner,gbc);
        
        
        reviveSpinner = new JSpinner();
        gbc.fill=GridBagConstraints.HORIZONTAL;
        gbc.gridx=2;
        gbc.gridy=2;
        //reviveSpinner.setValue((Integer)startingRules.getReviveNumber());
        startVal=(Integer)startingRules.getReviveNumber();
        model  =
        new SpinnerNumberModel(startVal, //initial value
                               0, //min
                               99, //max
                               1);                //step
        reviveSpinner.setModel(model);
        ((JSpinner.DefaultEditor) reviveSpinner.getEditor()).getTextField().setEditable(false);
        cc.add(reviveSpinner,gbc);
        
        overpopSpinner = new JSpinner();
        gbc.fill=GridBagConstraints.HORIZONTAL;
        gbc.gridx=2;
        gbc.gridy=3;
        startVal=(Integer)startingRules.getOverpopNumber();
        model  =
        new SpinnerNumberModel(startVal, //initial value
                               0, //min
                               99, //max
                               1);                //step
        overpopSpinner.setModel(model);
        ((JSpinner.DefaultEditor) overpopSpinner.getEditor()).getTextField().setEditable(false);
        cc.add(overpopSpinner,gbc);
        
        
        finishButton = new JButton(finishButtonText);
        gbc.fill=GridBagConstraints.HORIZONTAL;
        gbc.gridx=0;
        gbc.gridy=4;
        gbc.gridwidth=1;
        finishButton.addActionListener((ActionEvent e) -> {
            exportRulesAndClose();
        });
        cc.add(finishButton,gbc);
        
        cancelButton = new JButton(cancelButtonText);
        gbc.fill=GridBagConstraints.HORIZONTAL;
        gbc.gridx=1;
        gbc.gridy=4;
        gbc.gridwidth=2;
        cancelButton.addActionListener((ActionEvent e) -> {
            closeMe();
        });
        
        cc.add(cancelButton,gbc);
        cc.setBackground(Color.WHITE);
        pack();
        setLocationRelativeTo(mainWindow);
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        
        addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosed(WindowEvent e)
            {
                mainWindow.nullRulesWindow();
            }
        });
    }
    
    private void exportRulesAndClose()
    {
        RulesBundle exportedRules = new RulesBundle((Integer)starveSpinner.getValue(),(Integer)aliveSpinner.getValue(),(Integer)reviveSpinner.getValue(),(Integer)overpopSpinner.getValue());
        gameRunner.setCustomRules(exportedRules);
        closeMe();
    }
    
    private void closeMe()
    {
        dispose();
    }
}//end RulesCustomizerPopup//
