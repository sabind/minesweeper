package com.noobathon.minesweeper.test;

import com.noobathon.minesweeper.ui.BombSquare;
import com.noobathon.minesweeper.ui.GridSquare;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import com.noobathon.minesweeper.ui.MinesweeperGridFrame;
import org.junit.Before;
import org.junit.Test;

public class BombSquareTest
{
    private GridSquare square0;
    private GridSquare square1;
    private GridSquare square2;
    private GridSquare square3;
    private MinesweeperGridFrame frame;

    @Before
    public void setUp()
    {
        frame = new MinesweeperGridFrame(15, 15);

        square0 = new BombSquare(0,0,frame);
        square1 = new BombSquare(1,1,frame);
        square2 = new BombSquare(2,2,frame);
        square3 = new BombSquare(3,3,frame);
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
