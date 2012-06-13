package com.noobathon.minesweeper.ui;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.JPanel;

public class MinesweeperGridFrame extends JPanel
{
	private static final long serialVersionUID = -7507810309330039636L;
	private GridSquare[][] gridSquare2DArray;
    private int numActiveBombs;
	private int max_xCoordinate;
	private int max_yCoordinate;
	
	public MinesweeperGridFrame(int gridRows, int gridColumns)
	{
		super();
        numActiveBombs = 0;
		
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
                if (temp.getSquareType() == GridSquare.BOMB)
                    numActiveBombs++;
				
				gridSquare2DArray[y][x] = temp;
			}
		}
	}

    public void decrementFlaggedBombs()
    {
        numActiveBombs--;
    }

    public void incrementFlaggedBombs()
    {
        numActiveBombs++;
    }

    public boolean isGameWon()
    {
        return numActiveBombs == 0;
    }

	public void uncover(GridSquare origin)
	{
        ArrayList<GridSquare> toUncover = new ArrayList<GridSquare>();

        toUncover.add(origin);
        int bombsAround;
        GridSquare square;

        while (!toUncover.isEmpty())
        {
            square = toUncover.remove(0);
            square.covered = false;
            bombsAround = countNeighboringBombs(square);

            if (bombsAround > 0)
            {
                square.setBackground(Color.YELLOW);
            }
            else
            {
                square.setBackground(GridSquare.CLEARED);
                addNeighbors(square, toUncover);
            }
        }
	}

    private int countNeighboringBombs(GridSquare square)
    {
        int xCoordinate = square.getXCoordinate();
        int yCoordinate = square.getYCoordinate();

        int numBombs = 0;

        if (coordinatesAreInGrid(yCoordinate, xCoordinate - 1))
        {
            if (gridSquare2DArray[yCoordinate][xCoordinate - 1].getSquareType() == GridSquare.BOMB)
                numBombs++;
        }
        if (coordinatesAreInGrid(yCoordinate, xCoordinate + 1))
        {
            if (gridSquare2DArray[yCoordinate][xCoordinate + 1].getSquareType() == GridSquare.BOMB)
                numBombs++;
        }
        if (coordinatesAreInGrid(yCoordinate + 1, xCoordinate - 1))
        {
            if (gridSquare2DArray[yCoordinate + 1][xCoordinate - 1].getSquareType() == GridSquare.BOMB)
                numBombs++;
        }
        if (coordinatesAreInGrid(yCoordinate + 1, xCoordinate + 1))
        {
            if (gridSquare2DArray[yCoordinate + 1][xCoordinate + 1].getSquareType() == GridSquare.BOMB)
                numBombs++;
        }
        if (coordinatesAreInGrid(yCoordinate + 1, xCoordinate))
        {
            if (gridSquare2DArray[yCoordinate + 1][xCoordinate].getSquareType() == GridSquare.BOMB)
                numBombs++;
        }
        if (coordinatesAreInGrid(yCoordinate - 1, xCoordinate - 1))
        {
            if (gridSquare2DArray[yCoordinate - 1][xCoordinate - 1].getSquareType() == GridSquare.BOMB)
                numBombs++;
        }
        if (coordinatesAreInGrid(yCoordinate - 1, xCoordinate + 1))
        {
            if (gridSquare2DArray[yCoordinate - 1][xCoordinate + 1].getSquareType() == GridSquare.BOMB)
                numBombs++;
        }
        if (coordinatesAreInGrid(yCoordinate - 1, xCoordinate))
        {
            if (gridSquare2DArray[yCoordinate - 1][xCoordinate].getSquareType() == GridSquare.BOMB)
                numBombs++;
        }

        return numBombs;
    }

    private void addNeighbors(GridSquare square, ArrayList<GridSquare> toUncover)
    {
        int xCoordinate = square.getXCoordinate();
        int yCoordinate = square.getYCoordinate();

        if (coordinatesAreInGrid(yCoordinate, xCoordinate - 1) && gridSquare2DArray[yCoordinate][xCoordinate - 1].getSquareType() != GridSquare.BOMB)
        {
            if (gridSquare2DArray[yCoordinate][xCoordinate - 1].isCovered() && !gridSquare2DArray[yCoordinate][xCoordinate - 1].inProcessing)
            {
                toUncover.add(gridSquare2DArray[yCoordinate][xCoordinate - 1]);
                gridSquare2DArray[yCoordinate][xCoordinate - 1].inProcessing = true;
            }
        }
        if (coordinatesAreInGrid(yCoordinate, xCoordinate + 1) && gridSquare2DArray[yCoordinate][xCoordinate + 1].getSquareType() != GridSquare.BOMB)
        {
            if (gridSquare2DArray[yCoordinate][xCoordinate + 1].isCovered() && !gridSquare2DArray[yCoordinate][xCoordinate + 1].inProcessing)
            {
                toUncover.add(gridSquare2DArray[yCoordinate][xCoordinate + 1]);
                gridSquare2DArray[yCoordinate][xCoordinate + 1].inProcessing = false;
            }
        }
        if (coordinatesAreInGrid(yCoordinate + 1, xCoordinate - 1) && gridSquare2DArray[yCoordinate + 1][xCoordinate - 1].getSquareType() != GridSquare.BOMB)
        {
            if (gridSquare2DArray[yCoordinate + 1][xCoordinate - 1].isCovered() && !gridSquare2DArray[yCoordinate + 1][xCoordinate - 1].inProcessing)
            {
                toUncover.add(gridSquare2DArray[yCoordinate + 1][xCoordinate - 1]);
                gridSquare2DArray[yCoordinate + 1][xCoordinate - 1].inProcessing = true;
            }
        }
        if (coordinatesAreInGrid(yCoordinate + 1, xCoordinate + 1) && gridSquare2DArray[yCoordinate + 1][xCoordinate + 1].getSquareType() != GridSquare.BOMB)
        {
            if (gridSquare2DArray[yCoordinate + 1][xCoordinate + 1].isCovered() && !gridSquare2DArray[yCoordinate + 1][xCoordinate + 1].inProcessing)
            {
                toUncover.add(gridSquare2DArray[yCoordinate + 1][xCoordinate + 1]);
                gridSquare2DArray[yCoordinate + 1][xCoordinate + 1].inProcessing = true;
            }
        }
        if (coordinatesAreInGrid(yCoordinate + 1, xCoordinate) && gridSquare2DArray[yCoordinate + 1][xCoordinate].getSquareType() != GridSquare.BOMB)
        {
            if (gridSquare2DArray[yCoordinate + 1][xCoordinate].isCovered() && !gridSquare2DArray[yCoordinate + 1][xCoordinate].inProcessing)
            {
                toUncover.add(gridSquare2DArray[yCoordinate + 1][xCoordinate]);
                gridSquare2DArray[yCoordinate + 1][xCoordinate].inProcessing = true;
            }
        }
        if (coordinatesAreInGrid(yCoordinate - 1, xCoordinate - 1) && gridSquare2DArray[yCoordinate - 1][xCoordinate - 1].getSquareType() != GridSquare.BOMB)
        {
            if (gridSquare2DArray[yCoordinate - 1][xCoordinate - 1].isCovered() && !gridSquare2DArray[yCoordinate - 1][xCoordinate - 1].inProcessing)
            {
                toUncover.add(gridSquare2DArray[yCoordinate - 1][xCoordinate - 1]);
                gridSquare2DArray[yCoordinate - 1][xCoordinate - 1].inProcessing = true;
            }
        }
        if (coordinatesAreInGrid(yCoordinate - 1, xCoordinate + 1) && gridSquare2DArray[yCoordinate - 1][xCoordinate + 1].getSquareType() != GridSquare.BOMB)
        {
            if (gridSquare2DArray[yCoordinate - 1][xCoordinate + 1].isCovered() && !gridSquare2DArray[yCoordinate - 1][xCoordinate + 1].inProcessing)
            {
                toUncover.add(gridSquare2DArray[yCoordinate - 1][xCoordinate + 1]);
                gridSquare2DArray[yCoordinate - 1][xCoordinate + 1].inProcessing = true;
            }
        }
        if (coordinatesAreInGrid(yCoordinate - 1, xCoordinate) && gridSquare2DArray[yCoordinate - 1][xCoordinate].getSquareType() != GridSquare.BOMB)
        {
            if (gridSquare2DArray[yCoordinate - 1][xCoordinate].isCovered() && !gridSquare2DArray[yCoordinate - 1][xCoordinate].inProcessing)
            {
                toUncover.add(gridSquare2DArray[yCoordinate - 1][xCoordinate]);
                gridSquare2DArray[yCoordinate - 1][xCoordinate].inProcessing = true;
            }
        }
    }

    private boolean coordinatesAreInGrid(int yCoordinate, int xCoordinate)
    {
        return (yCoordinate >= 0 && yCoordinate < max_yCoordinate && xCoordinate >= 0 && xCoordinate < max_xCoordinate);
    }
}
