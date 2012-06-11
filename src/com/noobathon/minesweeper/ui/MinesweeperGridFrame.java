package com.noobathon.minesweeper.ui;

import java.awt.GridLayout;
import javax.swing.JPanel;

public class MinesweeperGridFrame extends JPanel
{
	private static final long serialVersionUID = -7507810309330039636L;
	private GridSquare[][] gridSquare2DArray;
	private int max_xCoordinate;
	private int max_yCoordinate;
	
	public MinesweeperGridFrame(int gridRows, int gridColumns)
	{
		super();
		
		max_xCoordinate = gridColumns;
		max_yCoordinate = gridRows;
		
		gridSquare2DArray = new GridSquare[gridRows][gridColumns];
		setLayout(new GridLayout(gridRows, gridColumns));
		buildGrid(gridRows, gridColumns);
		setVisible(true);
	}

	private void buildGrid(int gridRows, int gridColumns) 
	{
		GridSquare temp;
		for (int y = 0; y < gridRows; ++y)
		{
			for (int x = 0; x < gridColumns; ++x)
			{
				temp = GridSquare.newGridSquare(y, x, this);
				add(temp);
				
				gridSquare2DArray[y][x] = temp;
			}
		}
	}

	public void uncover(int xCoordinate, int yCoordinate) 
	{
		if (xCoordinate < 0 || xCoordinate > max_xCoordinate)
			return;
		if (yCoordinate  < 0 || yCoordinate > max_yCoordinate)
			return;
		
		GridSquare start = gridSquare2DArray[yCoordinate][xCoordinate];
		
		if (start.getType() == GridSquare.EMPTY)
		{
			start.uncover();
			uncoverSurrounding(xCoordinate, yCoordinate);
		}
	}

	private void uncoverSurrounding(int xCoordinate, int yCoordinate) 
	{
		gridSquare2DArray[yCoordinate - 1][xCoordinate].uncover();
		gridSquare2DArray[yCoordinate - 1][xCoordinate - 1].uncover();
		gridSquare2DArray[yCoordinate - 1][xCoordinate + 1].uncover();
		gridSquare2DArray[yCoordinate][xCoordinate - 1].uncover();
		gridSquare2DArray[yCoordinate][xCoordinate + 1].uncover();
		gridSquare2DArray[yCoordinate + 1][xCoordinate].uncover();
		gridSquare2DArray[yCoordinate + 1][xCoordinate + 1].uncover();
		gridSquare2DArray[yCoordinate + 1][xCoordinate - 1].uncover();
	}
}
