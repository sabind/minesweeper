package com.noobathon.minesweeper.test;

import static org.junit.Assert.assertEquals;

import java.awt.Point;
import java.util.LinkedList;
import java.util.List;

import com.noobathon.minesweeper.ui.BombSquare;
import com.noobathon.minesweeper.ui.GridSquare;
import com.noobathon.minesweeper.ui.MinesweeperGridFrame;
import org.junit.Before;
import org.junit.Test;

public class MinesweeperGridFrameTest
{
    private GridFrameTestDouble frame;
    private int initialNumBombs;
    private final int FRAME_WIDTH = 5;
    private final int FRAME_HEIGHT = 5;
    
    @Before
    public void setUp()
    {
        String[][] testGrid = {  
        		{ "LE", "E", "E", "E", "E" },
        		{ "E", "E", "E", "E", "E" },
        		{ "E", "LE", "LB", "E", "E" },
        		{ "E", "E", "E", "E", "E" },
        		{ "E", "E", "E", "E", "E" },
        };
        frame = new GridFrameTestDouble(testGrid, FRAME_HEIGHT, FRAME_WIDTH);
        initialNumBombs = frame.numActiveBombs();
    }
    
    @Test
    public void decrementFlaggedBombsTest()
    {
        frame.decrementFlaggedBombs();
        assertEquals(initialNumBombs - 1, frame.numActiveBombs());
    }
    
    @Test
    public void incrementFlaggedBombsTest()
    {
        frame.incrementFlaggedBombs();
        assertEquals(initialNumBombs + 1, frame.numActiveBombs());
    }

    @Test
    public void decrementBadFlagsTest()
    {
        frame.decrementBadFlags();
        assertEquals(-1, frame.numBadFlags());
    }

    @Test
    public void incrementBadFlagsTest()
    {
        frame.incrementBadFlags();
        assertEquals(1, frame.numBadFlags());
    }
    
    @Test 
    public void uncoverDoesNotFailWhenCalledOutsideGridRangeTopLeft()
    {
    	frame.uncover(new GridSquare(-1, -1));
    }
    
    public void uncoverDoesNotFailWhenCalledOutsideGridRangeTopRight()
    {
    	frame.uncover(new GridSquare(-1, FRAME_WIDTH + 1));
    }
    
    @Test 
    public void uncoverDoesNotFailWhenCalledOutsideGridRangeBottomRight()
    {
    	frame.uncover(new GridSquare(FRAME_HEIGHT + 1, FRAME_WIDTH + 1));
    }
    
    @Test 
    public void uncoverDoesNotFailWhenCalledOutsideGridRangeBottomLeft()
    {
    	frame.uncover(new GridSquare(FRAME_HEIGHT + 1, -1));
    }
    
    @Test 
    public void uncoverDoesNotFailWhenCalledOutsideGridRangeBottom()
    {
    	frame.uncover(new GridSquare(FRAME_HEIGHT + 1, FRAME_WIDTH));
    }
    
    @Test 
    public void uncoverDoesNotFailWhenCalledOutsideGridRangeTop()
    {
    	frame.uncover(new GridSquare(-1, FRAME_WIDTH));
    }
    
    @Test 
    public void uncoverDoesNotFailWhenCalledOutsideGridRangeLeft()
    {
    	frame.uncover(new GridSquare(FRAME_HEIGHT, -1));
    }
    
    @Test 
    public void uncoverDoesNotFailWhenCalledOutsideGridRangeRight()
    {
    	frame.uncover(new GridSquare(FRAME_HEIGHT, FRAME_WIDTH + 1));
    }
    
    @Test public void uncoveringEmptySquareNotNearBombShouldUncoverAdditionalSquares()
    {
    	frame.click(0);
    	assertEquals(GridSquare.CLEARED, frame.theGrid[0][0].getBackground());
    	assertEquals(GridSquare.CLEARED, frame.theGrid[4][4].getBackground());
    }
    
    @Test public void uncoveringEmptySquareNearBombShouldNotUncoverAdditionalSquares()
    {
    	frame.click(1);
    	assertEquals(GridSquare.ALERT, frame.theGrid[2][1].getBackground());
    	assertEquals(GridSquare.NON_ACTIVE_COLOR, frame.theGrid[4][4].getBackground());
    }
    
    @Test public void uncoveringABombShouldMakeItExplode()
    {
    	frame.click(2);
    	assertEquals(BombSquare.BLOW_UP, frame.theGrid[2][2].getBackground());
    }
    
    private class Click extends Point
    {
		private static final long serialVersionUID = 7910437653391332778L;
		private static final int LEFT_CLICK = 1;
    	private static final int RIGHT_CLICK = 2;
    	
    	int type;
    	
    	public Click (int x, int y, int type)
    	{
    		super(x, y);
    		this.type = type;
    	}

		public int getType() 
		{
			return type;
		}
    }
    
    private class GridFrameTestDouble extends MinesweeperGridFrame
    {
		private static final long serialVersionUID = -1539229673077452006L;
		private GridSquare[][] theGrid;
    	private List<Click> clicks;
    	private int numActiveBombs;
    	
    	public GridFrameTestDouble(String[][] grid, int gridRows, int gridColumns) 
    	{
    		theGrid = new GridSquare[gridRows][gridColumns];
    		clicks = new LinkedList<Click>();
    		numActiveBombs = 0;
    		
    		int i = 0, j = 0;
    		for (String[] rows : grid)
    		{
    			j = 0;
    			for (String square : rows)
    			{
    				if (square.contains("E"))
    				{
    					theGrid[i][j] = new GridSquare(j,j);
    					theGrid[i][j].setParentFrame(this);
    				}
    				else if (square.contains("B"))
    				{
    					numActiveBombs++;
    					theGrid[i][j] = new BombSquare(i,j);
    				}
    				if (square.contains("L"))
    					clicks.add(new Click(j, i, Click.LEFT_CLICK));
    				if (square.contains("R"))
    					clicks.add(new Click(j, i, Click.RIGHT_CLICK));
    				j++;
    			}
    			i++;
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
        
        public void click(int index)
        {
        	Click click = clicks.get(index);
        	if (click.getType() == Click.LEFT_CLICK)
        		theGrid[click.y][click.x].leftClick();
        	else if (click.getType() == Click.RIGHT_CLICK)
        		theGrid[click.y][click.x].rightClick();
        }
        
        public int numActiveBombs()
        {
        	return numActiveBombs;
        }
    }
}
