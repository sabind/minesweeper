package com.noobathon.minesweeper.test;

import com.noobathon.minesweeper.ui.BombSquare;
import com.noobathon.minesweeper.ui.GridSquare;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

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

    public void decrementFlaggedBombsTest()
    {
        frame.decrementFlaggedBombs();
        assertEquals(initialNumBombs - 1, frame.numActiveBombs());
    }

    public void incrementFlaggedBombsTest()
    {
        frame.incrementFlaggedBombs();
        assertEquals(initialNumBombs + 1, frame.numActiveBombs());
    }

    public void decrementBadFlagsTest()
    {
        frame.decrementBadFlags();
        assertEquals(-1, frame.numBadFlags());
    }

    public void incrementBadFlagsTest()
    {
        frame.incrementBadFlags();
        assertEquals(1, frame.numBadFlags());
    }
}
