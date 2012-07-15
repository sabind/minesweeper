package com.noobathon.minesweeper.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

public class MinesweeperMainFrame extends JFrame implements ActionListener
{
	private static final long serialVersionUID = -5099377961864547691L;
	private final static int DEFAULT_GRID_ROWS = 15;
	private final static int DEFAULT_GRID_COLUMNS = 15;
	private MinesweeperGridFrame theGrid;
	private JMenuBar theMenuBar;
	private JMenu fileMenu;
	
	public MinesweeperMainFrame()
	{
		super("Minesweeper");
		setLayout(new BorderLayout());
		setupMenu();
		setupGrid();
		setupFrame();
	}
	
	private void setupGrid()
	{
		theGrid = new MinesweeperGridFrame(DEFAULT_GRID_ROWS, DEFAULT_GRID_COLUMNS);
		this.add(theGrid, BorderLayout.CENTER);
	}
	
	private void setupFrame()
	{
		this.setVisible(true);
		this.pack();
		this.setMinimumSize(new Dimension(400, 400));
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	private void setupMenu()
	{
		theMenuBar = new JMenuBar();
		fileMenu = new JMenu("File");
		theMenuBar.add(fileMenu);
		
		JMenuItem newGame = new JMenuItem("New Game");
		newGame.addActionListener(this);
		
		newGame.setAccelerator(KeyStroke.getKeyStroke(
		        KeyEvent.VK_1, ActionEvent.ALT_MASK));
		newGame.getAccessibleContext().setAccessibleDescription(
		        "Start a new game");
		
		fileMenu.add(newGame);
		
		this.setJMenuBar(theMenuBar);
	}
	
	public void actionPerformed(ActionEvent e) 
	{
		this.remove(theGrid);
		theGrid = new MinesweeperGridFrame(DEFAULT_GRID_ROWS, DEFAULT_GRID_COLUMNS);
		this.add(theGrid);
		this.pack();
    }
}
