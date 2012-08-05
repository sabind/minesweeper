package com.noobathon.minesweeper.test;

import com.noobathon.minesweeper.ui.GridSquare;
import com.noobathon.minesweeper.ui.MinesweeperGridFrame;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

public class GridSquareTest
{
    private GridSquare square0;
    private GridSquare square1;
    private GridSquare square2;
    private GridSquare square3;

    @Before
    public void setUp()
    {
    	MinesweeperGridFrame frame = EasyMock.createNiceMock(MinesweeperGridFrame.class);
        square0 = new GridSquare(0,0);
        square0.setParentFrame(frame);
        square1 = new GridSquare(1,1);
        square1.setParentFrame(frame);
        square2 = new GridSquare(2,2);
        square2.setParentFrame(frame);
        square3 = new GridSquare(3,3); 
        square3.setParentFrame(frame);
    }

    @Test
    public void shouldBeAbleToRetrieveXCoordinate()
    {
        assertEquals(square0.getXCoordinate(), 0);
        assertEquals(square1.getXCoordinate(), 1);
        assertEquals(square2.getXCoordinate(), 2);
        assertEquals(square3.getXCoordinate(), 3);
    }

    @Test
    public void shouldBeAbleToRetrieveYCoordinate()
    {
        assertEquals(square0.getXCoordinate(), 0);
        assertEquals(square1.getXCoordinate(), 1);
        assertEquals(square2.getXCoordinate(), 2);
        assertEquals(square3.getXCoordinate(), 3);
    }

    @Test
    public void gridSquareShouldReturnTypeOfEmpty()
    {
        assertEquals(square0.getSquareType(), GridSquare.EMPTY);
    }


    @Test
    public void gridSquareShouldBeInitializedToCovered()
    {
        assertTrue(square0.isCovered());
    }

    @Test
    public void gridSquareShouldBeInitializedToUnFlagged()
    {
        assertFalse(square0.isFlagged());
    }

    @Test
    public void rightClickingAGridSquareShouldToggleIsFlagged()
    {
        assertFalse(square0.isFlagged());
        square0.rightClick();
        assertTrue(square0.isFlagged());
        square0.rightClick();
        assertFalse(square0.isFlagged());
    }

    @Test
    public void rightClickingAGridSquareShouldToggleColorsOfSquare()
    {
        assertEquals(GridSquare.NON_ACTIVE_COLOR, square0.getBackground());
        square0.rightClick();
        assertEquals(GridSquare.FLAGGED_COLOR, square0.getBackground());
        square0.rightClick();
        assertEquals(GridSquare.NON_ACTIVE_COLOR, square0.getBackground());
    }
}
