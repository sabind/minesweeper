package com.noobathon.minesweeper.test;

import com.noobathon.minesweeper.ui.BombSquare;
import com.noobathon.minesweeper.ui.GridSquare;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import org.junit.Before;
import org.junit.Test;

public class BombSquareTest
{
    private GridSquare square0;

    @Before
    public void setUp()
    {
        square0 = new BombSquare(0,0);
    }

    @Test
    public void bombSquareShouldReturnTypeOfBomb()
    {
        assertEquals(square0.getSquareType(), GridSquare.BOMB);
    }

    @Test
    public void bombSquareShouldBlowUpWhenLeftClickedAndNotFlagged()
    {
        square0.leftClick();
        assertTrue(square0.getBackground().equals(BombSquare.BLOW_UP));
    }

    @Test
    public void bombSquareShouldNotBlowUpWhenLeftClickedAndFlagged()
    {
        square0.rightClick();
        square0.leftClick();
        assertFalse(square0.getBackground().equals(BombSquare.BLOW_UP));
    }
}
