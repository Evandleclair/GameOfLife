/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.personalprojects.GameOfLife.UtilityClasses;

import java.awt.TextField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 * @author toast
 */
public class NumberDocumentListener implements DocumentListener
{
        private int maxLimit, minLimit;
        private JTextField owner;
        public NumberDocumentListener(int min, int max, JTextField Owner) 
        {
           maxLimit=max;
           minLimit=min;
           owner=Owner;
        }

    @Override
    public void insertUpdate(DocumentEvent e) {
       compareAgainstLimits();
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        compareAgainstLimits();
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        compareAgainstLimits();
    }
        
    private void compareAgainstLimits() {
       
        Runnable compareNums = ()
                -> {
            try {
                if (Integer.parseInt(owner.getText()) <= minLimit) {
                    owner.setText(Integer.toString(minLimit));
                } else if (Integer.parseInt(owner.getText()) >= minLimit) {
                    owner.setText(Integer.toString(maxLimit));
                }
            } catch (NumberFormatException ex) {
                LoggingClass.WriteToLog(ex, "Number format exception", "WARNING");
            }
        };
        Thread t = new Thread(compareNums);
        t.run();
        //SwingUtilities.invokeLater(compareNums);
    }
}
   