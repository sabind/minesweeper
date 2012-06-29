package com.noobathon.minesweeper.test;

import static org.junit.Assert.assertEquals;

import com.noobathon.minesweeper.ui.MinesweeperGridFrame;
import org.junit.Before;
import org.junit.Test;

public class MinesweeperGridFrameTest
{
    private MinesweeperGridFrame frame;
    private int initialNumBombs;

    @Before
    public void setUp()
    {
        frame = new MinesweeperGridFrame(15, 15);
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
}
