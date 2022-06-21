/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.GameOfLife;


import com.mycompany.GameOfLife.popupWindows.FileManager;
import com.mycompany.GameOfLife.popupWindows.GenerationEntryPopup;
import com.mycompany.GameOfLife.popupWindows.RulesCustomizerPopup;
import com.mycompany.GameOfLife.popupWindows.TablePopUp;
import com.mycompany.mavenproject1.DataTypes.RulesBundle;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Hashtable;
import java.beans.PropertyVetoException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.JSpinner.DefaultEditor;
import javax.swing.table.TableModel;
import javax.swing.text.AbstractDocument;



/**
 *
 * @author evandleclair
 */
public class MainWindow extends javax.swing.JFrame {
 
 private GameRunner gameRunner;
  private final DocFilter docFilter;
 private static TablePopUp tablePopUpMenu;
 private static RulesCustomizerPopup rulesCustomizerPopup;
 private static FileManager fileManager;
 private static RulesBundle customRules = new RulesBundle(0,2,3,4);
 private static final int maxSpinnerValue=2500, spinnerIncrement=25, spinnerDefValue=250;

 private AbstractDocument genRunDoc, boxDimDoc;
    //protected static SimulatorWindow simWindow = new SimulatorWindow();
    /**
     * Creates new form MainInterface
     */
    public MainWindow(DocFilter df) 
    {
        docFilter = df;
        initComponents();
        setDocFilters();
        setUpSpinner();
        //addRightClickMenuToTable();
    }//end constructor//
    
    public void invokeMainWindow()
    {
         java.awt.EventQueue.invokeLater(() -> {
            createAndShowMainWindow();
         });
    }//end startGameRunner./
    
     protected void quit() {
        System.exit(0);
    }
    
    //this is used so that it is not responsible for creating it's own gamerunner object//
    public void setGameRunner(GameRunner gameRunnerArg)
    {
        gameRunner = gameRunnerArg;
    }//end setGameRunner//
    
    
    public void setFileManager(FileManager fm)
    {
        fileManager=fm;
    }//end setFileManager//
    
    private void setUpSpinner()
    {
        SpinnerModel model =
        new SpinnerNumberModel(spinnerDefValue, //initial value
                               0, //min
                               maxSpinnerValue, //max
                               spinnerIncrement);                //step
        
        generationTimeSpinner.setModel(model);
        ((DefaultEditor) generationTimeSpinner.getEditor()).getTextField().setEditable(false);
    }
    
    private void setDocFilters()
    {
        AbstractDocument genRunDoc = (AbstractDocument) genRunBox.getDocument();
        AbstractDocument dimBoxDoc = (AbstractDocument) dimensionBox.getDocument();
        genRunDoc.setDocumentFilter(docFilter);
        dimBoxDoc.setDocumentFilter(docFilter);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        rulesButtonGroup = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        customRulesButton = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        label1 = new java.awt.Label();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        dimensionBox = new javax.swing.JTextField();
        genRunBox = new javax.swing.JTextField();
        percSlider = new javax.swing.JSlider();
        generationTimeSpinner = new javax.swing.JSpinner();
        jLabel4 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        startButton = new javax.swing.JButton();
        endAllButton = new javax.swing.JButton();
        endHighlightedGameButton = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        gameJTable = new javax.swing.JTable();
        fileNameField = new javax.swing.JTextField();
        selectFileButton = new javax.swing.JButton();
        importButton = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        rulesButtonGroup.add(jRadioButton1);
        jRadioButton1.setSelected(true);
        jRadioButton1.setText("Use Conway's default rules");

        rulesButtonGroup.add(jRadioButton2);
        jRadioButton2.setText("Use Custom Rules");

        customRulesButton.setText("EDIT CUSTOM RULES");
        customRulesButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                customRulesButtonMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jRadioButton1)
                    .addComponent(jRadioButton2))
                .addGap(18, 18, 18)
                .addComponent(customRulesButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(customRulesButton, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jRadioButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jRadioButton2)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        label1.setText("DIMENSIONS:");

        jLabel2.setText("GENERATIONS TO RUN: ");

        jLabel1.setText("% ALIVE ON START:");

        dimensionBox.setText("25");

        genRunBox.setText("30");
        genRunBox.setToolTipText("");

        Hashtable<Integer, JLabel> labels = new Hashtable<>();
        labels.put(0, new JLabel("0%"));
        labels.put(50, new JLabel("50%"));
        labels.put(100, new JLabel("100%"));
        percSlider.setLabelTable(labels);
        percSlider.setPaintLabels(true);
        percSlider.setPaintTicks(true);
        percSlider.setName("percSlider"); // NOI18N

        jLabel4.setText("Time between generations (ms)");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(67, 67, 67)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(genRunBox, javax.swing.GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE)
                            .addComponent(dimensionBox)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jLabel1)))
                        .addGap(29, 29, 29)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(percSlider, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(generationTimeSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(59, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2))
                    .addComponent(dimensionBox, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(genRunBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(percSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(generationTimeSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9))
        );

        label1.getAccessibleContext().setAccessibleName("dimLabel");
        dimensionBox.getAccessibleContext().setAccessibleName("dimensionBox");
        genRunBox.getAccessibleContext().setAccessibleName("genNumField");
        percSlider.getAccessibleContext().setAccessibleName("");
        generationTimeSpinner.getAccessibleContext().setAccessibleName("generationTimeSpinner");

        jPanel5.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        startButton.setText("Start Game");
        startButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        startButton.setIconTextGap(2);
        startButton.setMargin(new java.awt.Insets(2, 7, 2, 7));
        startButton.setName(""); // NOI18N
        startButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                startButtonMouseClicked(evt);
            }
        });

        endAllButton.setText("End All Games");
        endAllButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                EndButtonClicked(evt);
            }
        });

        endHighlightedGameButton.setText("End Highlighted Game");
        endHighlightedGameButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                endHighlightedGameButtonMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(endHighlightedGameButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(startButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(endAllButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(startButton, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(endAllButton, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(endHighlightedGameButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        endAllButton.getAccessibleContext().setAccessibleName("endAllButton");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        gameJTable.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        gameJTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        gameJTable.setToolTipText("");
        jScrollPane2.setViewportView(gameJTable);
        gameJTable.getAccessibleContext().setAccessibleName("");

        selectFileButton.setText("SELECT FILE");
        selectFileButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                selectFileButtonMouseClicked(evt);
            }
        });

        importButton.setText("IMPORT FILE");
        importButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                importButtonMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane2)
                        .addContainerGap())
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(selectFileButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fileNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(importButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(106, 106, 106))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fileNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(importButton)
                    .addComponent(selectFileButton))
                .addContainerGap())
        );

        jLabel3.setText("Right click on a running game in the table below\\ to open up an interactions menu");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(2, 2, 2)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void startButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_startButtonMouseClicked
        createFrameAndGame();
    }//GEN-LAST:event_startButtonMouseClicked

    private void EndButtonClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_EndButtonClicked
       gameRunner.endAllGames();
    }//GEN-LAST:event_EndButtonClicked

    private void selectFileButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_selectFileButtonMouseClicked
        fileManager.ShowOpenInterface();
    }//GEN-LAST:event_selectFileButtonMouseClicked

    private void customRulesButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_customRulesButtonMouseClicked
        if (rulesCustomizerPopup == null) {
            rulesCustomizerPopup = new RulesCustomizerPopup(this);
     }
        
    }//GEN-LAST:event_customRulesButtonMouseClicked

    private void endHighlightedGameButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_endHighlightedGameButtonMouseClicked
       
    }//GEN-LAST:event_endHighlightedGameButtonMouseClicked

    private void importButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_importButtonMouseClicked
        if (gameRunner.getStoredFile()!=null)
        {
            showGenerationPopupAndImportFile();
        }
        else
        {
           System.out.println("no board to import");
        }
    }//GEN-LAST:event_importButtonMouseClicked
   
    void showGenerationPopupAndImportFile()
    {
        GenerationEntryPopup genEntryPopup=new GenerationEntryPopup(this);
        fileManager.ImportBoard(gameRunner.getStoredFile());
    }
    void updateTableModel(TableModel tm)
    {
        gameJTable.setModel((tm));
    }
        
    public void nullRulesWindow()
    {
       if (rulesCustomizerPopup.isVisible()==false)
       {
           rulesCustomizerPopup=null;
       }
    }
    
    protected void createFrameAndGame()  
    {
        int dims = Integer.parseInt(dimensionBox.getText());
        gameRunner.createSimWindowAndStartSim(dims);
    }
    
   
    
    private void createAndShowMainWindow()
    {   
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
  
    public void addRightClickMenuToTable() 
    {
        tablePopUpMenu = new TablePopUp(this);
        gameJTable.addMouseListener( new MouseAdapter()
        {
            @Override
            public void mousePressed(MouseEvent e) { }
            @Override
            public void mouseReleased(MouseEvent e)
            {
                if (e.isPopupTrigger())
                {
                    JTable source = (JTable)e.getSource();
                    int row = source.rowAtPoint( e.getPoint() );
                    int column = source.columnAtPoint( e.getPoint() );

                    if (! source.isRowSelected(row))
                        source.changeSelection(row, column, false, false);
                
                    tablePopUpMenu.ShowPopUp(e, row);
                }
            }
        });
    }

    public boolean useCustomRules()
    {
        boolean retValue = false;
         for (Enumeration<AbstractButton> buttons = rulesButtonGroup.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();
            if (button.isSelected()) {
                if (button.getText()=="Use Custom Rules")
                {
                    retValue=true;
                }
            }
        }
        return retValue;
    }
    
    public void showFileSaveInterface(int rowID)
    {
          fileManager.setCallingRow(rowID);
          fileManager.ShowSaveInterface();
    }
    
    public void showFileSaveInterface(String boardName)
    {
          fileManager.setCallingRow(gameRunner.getSimRowByName(boardName));
          fileManager.ShowSaveInterface();
    }
    
    /*------------------------------------------------------------*/
    /* get and set section */
     public double getInitialAliveProbability()
    {
        return (percSlider.getValue()*0.01);
    }
  
    
    public int getGenerationsToRun()
    {
        return Integer.parseInt(genRunBox.getText());
    }
    
    public void setTextBoxToFilename(String s)
    {
        fileNameField.setText(s);
    }
    
    public GameRunner getGameRunner()
    {
        return gameRunner;
    }
    
    public int getTickTime()
    {
        return (Integer)generationTimeSpinner.getValue();
    }
    
    public RulesBundle getRules()
    {
        return customRules;
    }
    
    public void setRules(RulesBundle rb)
    {
        customRules=rb;
    }
    
    /*---------end get and set section----------*/
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton customRulesButton;
    private javax.swing.JTextField dimensionBox;
    private javax.swing.JButton endAllButton;
    private javax.swing.JButton endHighlightedGameButton;
    private javax.swing.JTextField fileNameField;
    private javax.swing.JTable gameJTable;
    private javax.swing.JTextField genRunBox;
    private javax.swing.JSpinner generationTimeSpinner;
    private javax.swing.JButton importButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private java.awt.Label label1;
    private javax.swing.JSlider percSlider;
    private javax.swing.ButtonGroup rulesButtonGroup;
    private javax.swing.JButton selectFileButton;
    private javax.swing.JButton startButton;
    // End of variables declaration//GEN-END:variables

   
    
    
    
  
}


