/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.personalprojects.GameOfLife;

import com.personalprojects.GameOfLife.Interfaces.SimWindowInterface;
import com.personalprojects.GameOfLife.popupWindows.GenerationEntryPopup;
import com.personalprojects.GameOfLife.UtilityClasses.GridCanvas;
import com.personalprojects.GameOfLife.DataTypes.RulesBundle;
import com.personalprojects.GameOfLife.DataTypes.simWindowInfo;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.AbstractAction;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

/**
 * This object is responsible for containing the thread that runs the simulation, and the canvas to draw it on.
 * @author evandleclair
 */
public class SimCanvasWindow extends JDialog implements SimWindowInterface{

    static int openFrameCount = 0;
    private final int boardDim, genTime;
    private final String IDname;
    private final MainWindow mainWindow;
    private final GameRunner gameRunner;
    private JLabel clickToEditLabel; 
    private JMenuBar menuBar;
    private JMenu menu;
    private JMenuItem menuItem;
    private SimulatorRunnable simRunnable;
    private final RulesBundle myRules;
    private static final int X_OFFSET = 30, Y_OFFSET = 30;
    private static final String PAUSE_MESSAGE = "Game Paused:click cells to kill/revive", RUN_MESSAGE = "Game Running";
    private static final Font RUN_FONT = new Font("TimesRoman", Font.PLAIN, 12); 
     private static final Font PAUSE_FONT = new Font("TimesRoman", Font.PLAIN, 10); 
    GridCanvas boardGameCanvas;
    BoardObject bOb= null;
    Graphics gr, canvasGr;
    
    /**
     * Constructor method. Creates our window based on the arguments given.
     * @param dim the dimensions of our grid of cells
     * @param idName the name of the window, which matches the name of our simulation/
     * @param creatorWindow the main GUI window of the program
     * @param rulesToUse a RulesBundle object.
     */
    public SimCanvasWindow(int dim, String idName, MainWindow creatorWindow, RulesBundle rulesToUse)
    {
       
        boardDim=dim;
        mainWindow=creatorWindow;
        myRules=rulesToUse;
        genTime=creatorWindow.getTickTime();
        gameRunner=creatorWindow.getGameRunner();
        IDname=idName;
        //...Then set the window size or call pack...
        openFrameCount=gameRunner.getGamesRunning();
        callCreateAndShowGUI();
        //Set the window's location.
        setLocation(X_OFFSET*openFrameCount, Y_OFFSET*openFrameCount);
    }
    
    /**
     * Much the same as the other constructor, but used when we have a boardObject that we want to import. 
     * @param creatorWindow the main GUI window
     * @param BOb the boardObject we are importing. 
     */
    public SimCanvasWindow(MainWindow creatorWindow, BoardObject BOb)
    {
        bOb=BOb;
        boardDim=bOb.getDimensions();
        mainWindow=creatorWindow;
        myRules=bOb.getMyRules();
        genTime=creatorWindow.getTickTime();
        gameRunner=creatorWindow.getGameRunner();
        IDname=bOb.getName();
        //...Then set the window size or call pack...
        openFrameCount=gameRunner.getGamesRunning();
        callCreateAndShowGUI();
        //Set the window's location.
        setLocation(X_OFFSET*openFrameCount, Y_OFFSET*openFrameCount);
    }
    
    /**
     * Exists so we are not directly calling an overridable method in the constructor.
     */
    private void callCreateAndShowGUI()
    {
        createAndShowGUI();
    }
    
   
    
    @Override
    public void startSimRunnable() {
        setVisible(true); //necessary as of 1.3
        setMyGraphics();
        simRunnable.start();
    }
    
    /**
     * Public method that allows other windows to focus on specific games.
     */
    @Override
    public void pleaseLookAtMe()
    {
        requestFocus();
        toFront();
        
    }
    
    /**
     * Public method that allows closing of specific games
     */
    @Override
    public void pleaseCloseMe()
    {
        dispose();
    }
    
    /**
     * Public method that takes a generation count and adds it to the running simulation. 
     * @param gens the ammount of generations to add. 
     */
    @Override
    public void pleaseAddGenerations(int gens)
    {
        simRunnable.addGens(gens);
    }
    
    /**
     * Creates a simRunnable object, with a matching board, starts the simulation, stores the board here for referencing, then focuses on this window. 
     */
    @Override
    public void establishBoardAndStartSim() 
    {
        simRunnable = new SimulatorRunnable(this, IDname,boardDim, mainWindow.getInitialAliveProbability(), mainWindow.getGenerationsToRun(),myRules);
        simRunnable.startSimulation(genTime); //start the simulation//
        bOb=simRunnable.grabBoard(); //store the board created by our simRunnable object.//
        pleaseLookAtMe(); //focus on our new game so we do not miss the first generations//
    }
    
    /**
     * Takes a board object and starts a simulation using it. 
     * @param BOb the board object to base the simulation on.
     */
    @Override
    public void importBoardAndStartSim(BoardObject BOb) {
        simRunnable = new SimulatorRunnable(this, BOb,  gameRunner.getImportedGens());
        simRunnable.startImportedSimulation(genTime);
        pleaseLookAtMe();
    }
    
    /**
     * Stores our boardGameCanvas graphics as well as our own graphis as easily referenced variables.
     */
    public void setMyGraphics()
    {
        canvasGr=boardGameCanvas.getGraphics();
        gr = this.getGraphics();
    }//end setMyGraphics//

    /**
     * Given a matrix of ints, sets the stored canvas to match that state, and then redraws it.//
     * @param boardState  a matrix of ints representing a board state.
     */
    @Override
    public void displayUpdatedBoard(int[][] boardState) {
        boardGameCanvas.setCellsEqualToBoardState(boardState);
        boardGameCanvas.draw(canvasGr);
    }
    
   
    
    /**
     * Invoked once upon creation. Creates the GUI and shows it.
     */
    public void createAndShowGUI()
    {
        clickToEditLabel = new JLabel(RUN_MESSAGE);
        clickToEditLabel.setVisible(true);
        boardGameCanvas = new GridCanvas(boardDim, 10);
        int prefSize= boardDim*(10);
        boardGameCanvas.setPreferredSize(new Dimension(prefSize,prefSize));
        Container cc = this.getContentPane();
        cc.setLayout(new GridBagLayout());
         GridBagConstraints gbc = new GridBagConstraints();
        setTitle(IDname);
        addMouseListener(new MouseAdapter() 
        {

            @Override
            public void mousePressed(MouseEvent e) {
                System.out.println("Big window");
            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }
        });
        JPanel labelPanel = new JPanel();
        
        labelPanel.add(clickToEditLabel);
        clickToEditLabel.setHorizontalTextPosition(JLabel.CENTER);
        gbc.fill=GridBagConstraints.HORIZONTAL;
        gbc.gridwidth=1;
        gbc.gridx=0;
        gbc.gridy=0;
        cc.add(labelPanel, gbc);
        //...Create the GUI and put it in the window...
        JPanel canvasPanel = new JPanel();
        
        if (bOb==null)
        {
            establishBoardAndStartSim();
        }
        else
        {
            importBoardAndStartSim(bOb);
        }
        boardGameCanvas.addMouseListener(new MouseAdapter() {

                @Override
                public void mousePressed(MouseEvent e) 
                {         
                    int x=e.getX();
                    int y=e.getY();
                    System.out.println("On the canvas" + " x " + x + " y " + y);
                    toggleCellAtCoords(x,y);
                }

                @Override
                public void mouseReleased(MouseEvent e) {}
            });
      
        canvasPanel.add(boardGameCanvas);
        canvasPanel.setSize(boardGameCanvas.getSize());
        gbc.fill=GridBagConstraints.HORIZONTAL;
        gbc.gridwidth=1;
        gbc.gridx=0;
        gbc.gridy=1;
        cc.add(canvasPanel, gbc);
        //add(canvasPanel);
        this.addWindowListener(new WindowAdapter()
            {
                @Override
                public void windowClosed(WindowEvent e)
                {
                   System.out.println("closing self. I am " + IDname);
                   gameRunner.destroyGame(new simWindowInfo(IDname,this));
                   simRunnable.interuptThread();
                   gameRunner.reportClosedGame();
                }

                @Override
                public void windowClosing(WindowEvent e)
                {
                    System.out.println("closing self. I am " + IDname);
                    gameRunner.destroyGame(new simWindowInfo(IDname,this));
                    simRunnable.interuptThread();
                    gameRunner.reportClosedGame();
                }
            });
        this.setJMenuBar(establishMenuBar());
        pack(); 
        setResizable(false);
    }//end createAndShowGUI//

    /**
     * toggles the cell at the specified coordinates
     * @param C an Integer representing a column
     * @param R an Integer representing a row
     */
    private void toggleCellAtCoords(int C, int R)
    {
        int c = Math.round(C/boardGameCanvas.getCellSize());
        int r = Math.round(R/boardGameCanvas.getCellSize());
        System.out.println(" c " + c + " r " + r);
        bOb.setCellToggle(r, c);
        boardGameCanvas.userToggleCell(r, c);
    }
    
    /**
     * Passes status information about this window and it's simulation to the main window, so its coresponding row on the table can be updated./ /
     * @param simStatus The current status of the sim, stored as a String. 
     * @param currentGen The curretn generation of the sim, stored as an Integer.
     * @param tickSpeed the current time between generations, also called TickSpeed, stored as an Integer. 
     */
    @Override
    public void passSimStatusToMainWindow(String simStatus, int currentGen, int tickSpeed) {
        //System.out.println("passing");
        gameRunner.updateSimColumnsOnTable(IDname, simStatus, currentGen, simRunnable.getGensToRun(), tickSpeed);
    }

    /**
     * Gets the stored boardObject from this windows associated simulation.
     * @return a BoardObject belonging to the stored simRunnable. 
     */
    @Override
    public BoardObject getBoardObjectFromRunnable() 
    {
       return simRunnable.grabBoard();
    }

    /**
     * Invokes the pause method on this windows associated sim, and invokes a method to update our label accordingly. 
     */
    @Override
    public void pleasePauseSim() 
    {
    simRunnable.setPause(true);
    updatePauseLabel();   
    }

    /**
     * Invokes the resume method on this windows associated sim, and invokes a method to update our label accordingly. Also focuses on the window. 
     */
    @Override
    public void pleaseResumeSim() 
    {
        simRunnable.setPause(false);
        updatePauseLabel();
        pleaseLookAtMe();
    }
    
    private void updatePauseLabel()
    {
        if (simRunnable.getPause()==true)
            {
                clickToEditLabel.setText(PAUSE_MESSAGE);
                clickToEditLabel.setFont(PAUSE_FONT);
            }
            else
            {
                 clickToEditLabel.setText(RUN_MESSAGE);
                 clickToEditLabel.setFont(RUN_FONT);
            }
        clickToEditLabel.paintImmediately(clickToEditLabel.getVisibleRect());
        validate();
    }
    
    /**
     * Sets our simulations pause status to it's current opposite, and then updates the label according. 
     */
    private void togglePause()
    {
        simRunnable.setPause(!simRunnable.getPause());
        updatePauseLabel();
        System.out.println("toggling pause");
    }

  
    /**
     * Creates a menu bar that includes various options for interacting with the simulation
     * @return  a new menu bar, that will be attached to this window 
     */
    private JMenuBar establishMenuBar()
    {
        menuBar = new JMenuBar();
        menu = new JMenu("Simulator Options");
        menu.setMnemonic(KeyEvent.VK_A);
        menu.getAccessibleContext().setAccessibleDescription(
        "Simulation Interactions Menu");
        
        menuBar.add(menu);
        menuItem = new JMenuItem(new AbstractAction("Pause/Resume") 
        {
            @Override
            public void actionPerformed(ActionEvent ae) 
            {
                togglePause();
            }
        }
        );
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
        KeyEvent.VK_P, ActionEvent.ALT_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription("Pauses or unpauses the game");
        
        menu.add(menuItem);
        menuItem = new JMenuItem(new AbstractAction("Export board") 
        {
            @Override
            public void actionPerformed(ActionEvent ae) 
            {
                mainWindow.showFileSaveInterface(IDname);
            }
        }
        );
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
        KeyEvent.VK_E, ActionEvent.ALT_MASK));    
        menu.add(menuItem);
        
        menuItem = new JMenuItem(new AbstractAction("Clear board") 
        {
            @Override
            public void actionPerformed(ActionEvent ae) 
            {
                 bOb.clearBoard();
            }
        }
        );
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
        KeyEvent.VK_C, ActionEvent.ALT_MASK));    
        menu.add(menuItem);
         
        menuItem = new JMenuItem(new AbstractAction("Add generations") 
        {
            @Override
            public void actionPerformed(ActionEvent ae) 
            {
                 new GenerationEntryPopup(mainWindow, IDname);
            }
        }
        );
        
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
        KeyEvent.VK_G, ActionEvent.ALT_MASK));    
        menu.add(menuItem);
        
        menuItem = new JMenuItem(new AbstractAction("Close this Simulation") 
        {
            @Override
            public void actionPerformed(ActionEvent ae) 
            {
                pleaseCloseMe();
            }
        }
        );
        
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
        KeyEvent.VK_Q, ActionEvent.ALT_MASK));    
        menu.add(menuItem);
        return menuBar;
    }
    
    @Override
    public int getTickSpeed() 
    {
        return bOb.getTickSpeed();
    }
    
     /**
     * Changes the tick speed in milliseconds to match the argument given
     * @param tSpeed  A tick speed in milliseconds, stored as an integer. 
     */
    @Override
    public void setTickSpeed(int tSpeed)
    {
        bOb.setTickSpeed(tSpeed);
    }
    
}
