package com.noobathon.minesweeper.test;

import static org.junit.Assert.assertEquals;

import com.noobathon.minesweeper.ui.GridSquare;
import com.noobathon.minesweeper.ui.MinesweeperGridFrame;
import org.junit.Before;
import org.junit.Test;

public class MinesweeperGridFrameTest
{
    private MinesweeperGridFrame frame;
    private int initialNumBombs;
    private final int FRAME_WIDTH = 15;
    private final int FRAME_HEIGHT = 15;

    @Before
    public void setUp()
    {
        frame = new MinesweeperGridFrame(FRAME_HEIGHT, FRAME_WIDTH);
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
    	frame.uncover(new GridSquare(-1, -1, frame));
    }
    
    public void uncoverDoesNotFailWhenCalledOutsideGridRangeTopRight()
    {
    	frame.uncover(new GridSquare(-1, FRAME_WIDTH + 1, frame));
    }
    
    @Test 
    public void uncoverDoesNotFailWhenCalledOutsideGridRangeBottomRight()
    {
    	frame.uncover(new GridSquare(FRAME_HEIGHT + 1, FRAME_WIDTH + 1, frame));
    }
    
    @Test 
    public void uncoverDoesNotFailWhenCalledOutsideGridRangeBottomLeft()
    {
    	frame.uncover(new GridSquare(FRAME_HEIGHT + 1, -1, frame));
    }
    
    @Test 
    public void uncoverDoesNotFailWhenCalledOutsideGridRangeBottom()
    {
    	frame.uncover(new GridSquare(FRAME_HEIGHT + 1, FRAME_WIDTH, frame));
    }
    
    @Test 
    public void uncoverDoesNotFailWhenCalledOutsideGridRangeTop()
    {
    	frame.uncover(new GridSquare(-1, FRAME_WIDTH, frame));
    }
    
    @Test 
    public void uncoverDoesNotFailWhenCalledOutsideGridRangeLeft()
    {
    	frame.uncover(new GridSquare(FRAME_HEIGHT, -1, frame));
    }
    
    @Test 
    public void uncoverDoesNotFailWhenCalledOutsideGridRangeRight()
    {
    	frame.uncover(new GridSquare(FRAME_HEIGHT, FRAME_WIDTH + 1, frame));
    }
}
