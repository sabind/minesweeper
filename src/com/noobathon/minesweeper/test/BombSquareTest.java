package com.noobathon.minesweeper.test;

import com.noobathon.minesweeper.ui.BombSquare;
import com.noobathon.minesweeper.ui.GridSquare;
import com.noobathon.minesweeper.ui.MinesweeperGridFrame;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import org.easymock.EasyMockSupport;
import org.junit.Before;
import org.junit.Test;

public class BombSquareTest extends EasyMockSupport
{
    private GridSquare square0;

    @Before
    public void setUp()
    {
    	MinesweeperGridFrame frame = createNiceMock(MinesweeperGridFrame.class);
        square0 = new BombSquare(0,0);
        square0.setParentFrame(frame);
    }

    @Test
    public void bombSquareShouldReturnTypeOfBomb()
    {
    	replayAll();
        assertEquals(square0.getSquareType(), GridSquare.BOMB);
    }

    @Test
    public void bombSquareShouldBlowUpWhenLeftClickedAndNotFlagged()
    {
    	replayAll();
        square0.leftClick();
        assertTrue(square0.getBackground().equals(BombSquare.BLOW_UP));
    }

    @Test
    public void bombSquareShouldNotBlowUpWhenLeftClickedAndFlagged()
    {
    	replayAll();
        square0.rightClick();
        square0.leftClick();
        assertFalse(square0.getBackground().equals(BombSquare.BLOW_UP));
    }
}
