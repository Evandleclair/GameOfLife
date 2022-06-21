/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.GameOfLife;

import com.mycompany.GameOfLife.Interfaces.SimWindowInterface;
import com.mycompany.GameOfLife.popupWindows.GenerationEntryPopup;
import com.mycompany.GameOfLife.UtilityClasses.GridCanvas;
import com.mycompany.mavenproject1.DataTypes.RulesBundle;
import com.mycompany.mavenproject1.DataTypes.simWindowInfo;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.AbstractAction;
import javax.swing.JDialog;
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
    private final int boardDim;
    private int genTime;
    private final String IDname;
    private final MainWindow mainWindow;
    private final GameRunner gameRunner;
    private JMenuBar menuBar;
    private JMenu menu, ioMenu;
    private JMenuItem menuItem;
    private SimulatorRunnable simRunnable;
    private RulesBundle myRules;
    private static final int X_OFFSET = 30, Y_OFFSET = 30;
    GridCanvas boardGameCanvas;
    BoardObject bOb= null;
    Graphics gr, canvasGr;
    
    public SimCanvasWindow(int dim, String idName, MainWindow c, RulesBundle MyRules)
    {
       
        boardDim=dim;
        mainWindow=c;
        myRules=MyRules;
        genTime=c.getTickTime();
        gameRunner=c.getGameRunner();
        IDname=idName;
        //...Then set the window size or call pack...
        openFrameCount=gameRunner.getGamesRunning();
        createAndShowGUI();
        //Set the window's location.
        setLocation(X_OFFSET*openFrameCount, Y_OFFSET*openFrameCount);
    }
    
    public SimCanvasWindow(MainWindow c, BoardObject BOb)
    {
        bOb=BOb;
        boardDim=bOb.getDimensions();
        mainWindow=c;
        myRules=bOb.getMyRules();
        genTime=c.getTickTime();
        gameRunner=c.getGameRunner();
        IDname=bOb.getName();
        //...Then set the window size or call pack...
        openFrameCount=gameRunner.getGamesRunning();
        createAndShowGUI();
        //Set the window's location.
        setLocation(X_OFFSET*openFrameCount, Y_OFFSET*openFrameCount);
    }
    
    public void runSimWindowStartupTasks()
    {
        setVisible(true); //necessary as of 1.3
        setMyGraphics();
        
    }
    
    @Override
    public void startSimRunnable() {
        simRunnable.start();
    }
    
    @Override
    public void pleaseLookAtMe()
    {
        requestFocus();
        toFront();
        
    }
    
    @Override
    public void pleaseCloseMe()
    {
        dispose();
    }
    
    @Override
    public void pleaseAddGenerations(int gens)
    {
        simRunnable.addGens(gens);
        
    }

    @Override
    public void establishBoardAndStartSim() 
    {
        simRunnable = new SimulatorRunnable(this, IDname,boardDim, mainWindow.getInitialAliveProbability(), mainWindow.getGenerationsToRun(),myRules);
        simRunnable.startSimulation(genTime);
        bOb=simRunnable.grabBoard();
        pleaseLookAtMe();
    }
    
    @Override
    public void importBoardAndStartSim(BoardObject BOb) {
        simRunnable = new SimulatorRunnable(this, BOb,  gameRunner.getImportedGens());
        simRunnable.startImportedSimulation(genTime);
        pleaseLookAtMe();
    }
    
    public void setMyGraphics()
    {
        canvasGr=boardGameCanvas.getGraphics();
        gr = this.getGraphics();
    }//end setMyGraphics//

    @Override
    public void displayUpdatedBoard(int[][] boardState) {
        boardGameCanvas.setCellsEqualToBoardState(boardState);
        boardGameCanvas.draw(canvasGr);
    }
    
    public void updateTickSpeed(int speed)
    {
        bOb.setTickSpeed(speed);
    }
    
    public void createAndShowGUI()
    {
        boardGameCanvas = new GridCanvas(boardDim, 10);
        int prefSize= boardDim*(10);
        boardGameCanvas.setPreferredSize(new Dimension(prefSize,prefSize));
        
        setTitle(IDname);
        addMouseListener(new MouseAdapter() {

                @Override
                public void mousePressed(MouseEvent e) {
                    System.out.println("Big window");
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                   
                }
            });
        
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
                    setCellAtCoords(x,y);
                }

                @Override
                public void mouseReleased(MouseEvent e) {}
            });
        canvasPanel.add(boardGameCanvas);
        canvasPanel.setSize(boardGameCanvas.getSize());
        
        add(canvasPanel);
        this.addWindowListener(new WindowAdapter()
            {
                @Override
                public void windowClosed(WindowEvent e)
                {
                   System.out.println("closing self. I am " + IDname);
                   gameRunner.destroyGame(new simWindowInfo(IDname,this));
                   simRunnable.interuptThread();
                }

                @Override
                public void windowClosing(WindowEvent e)
                {
                    System.out.println("closing self. I am " + IDname);
                    gameRunner.destroyGame(new simWindowInfo(IDname,this));
                    simRunnable.interuptThread();
                }
            });
        this.setJMenuBar(establishMenuBar());
        pack(); 
        setResizable(false);
    }//end createAndShowGUI//

    private void setCellAtCoords(int x, int y)
    {
        int c = Math.round(x/boardGameCanvas.getCellSize());
        int r = Math.round(y/boardGameCanvas.getCellSize());
        System.out.println(" c " + c + " r " + r);
        bOb.setCellAlive(r, c);
        boardGameCanvas.userToggleCell(r, c);
    }
    
    
    
    @Override
    public void passSimStatusToMainWindow(String simStatus, int currentGen, int tickSpeed) {
        //System.out.println("passing");
        gameRunner.updateSimColumnsOnTable(IDname, simStatus, currentGen, tickSpeed);
    }

    @Override
    public BoardObject getBoardFromRunnable() 
    {
       return simRunnable.grabBoard();
    }

    @Override
    public void pleasePauseSim() {
       simRunnable.setPause(true);
    }

    @Override
    public void pleaseResumeSim() {
        simRunnable.setPause(false);
        pleaseLookAtMe();
    }
    
    private void togglePause()
    {
        simRunnable.setPause(!simRunnable.getPause());
        System.out.println("toggling pause");
    }

    @Override
    public int getTickTime() 
    {
        return bOb.getTickSpeed();
    }
    
    private JMenuBar establishMenuBar()
    {
        menuBar = new JMenuBar();
        menu = new JMenu("Simulator Options");
        menu.setMnemonic(KeyEvent.VK_A);
        menu.getAccessibleContext().setAccessibleDescription(
        "The only menu in this program that has menu items");
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
}
