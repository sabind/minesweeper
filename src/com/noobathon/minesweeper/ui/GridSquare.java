package com.noobathon.minesweeper.ui;

import java.awt.Color;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GridSquare extends JPanel implements MouseListener
{
	private static final long serialVersionUID = 1523739159832757191L;
	
	private static final int SQUARE_SIDE_LENGTH = 75;
	public static final Color NON_ACTIVE_COLOR = Color.LIGHT_GRAY;
    public static final Color CLEARED = Color.WHITE;
    public static final Color ALERT = Color.YELLOW;
    public static final Color FLAGGED_COLOR = new Color(0,150,0);
    public static final Color BORDER_COLOR = Color.BLACK;
	
	private int xCoordinate, yCoordinate;
	private JLabel numberOfBombsAround;
	protected MinesweeperGridFrame parentFrame;
	
	public static final int BOMB = 1;
	public static final int EMPTY = 0;

    private boolean isCovered;
    private boolean isFlagged;
    private boolean inProcessing;

	public static GridSquare newGridSquare(int yCoordinate, int xCoordinate)
	{
		if (BombSquare.shouldBeABomb())
			return new BombSquare(xCoordinate, yCoordinate);
		else
			return new GridSquare(xCoordinate, yCoordinate);
	}

	
	public GridSquare(int xCoordinate, int yCoordinate)
	{
		super();

        this.isCovered = true;
        this.isFlagged = false;
        this.inProcessing = false;
        this.parentFrame = null;
        this.numberOfBombsAround = new JLabel("");
        this.add(numberOfBombsAround);
        
		this.xCoordinate = xCoordinate;
		this.yCoordinate = yCoordinate;
		
		this.addMouseListener(this);

		setSize(SQUARE_SIDE_LENGTH, SQUARE_SIDE_LENGTH);
		setBorder(BorderFactory.createLineBorder(BORDER_COLOR));
		setBackground(NON_ACTIVE_COLOR);
	}

	public void setParentFrame(MinesweeperGridFrame parent)
	{
		this.parentFrame = parent;
	}
	
    public int getXCoordinate()
    {
        return xCoordinate;
    }

    public int getYCoordinate()
    {
        return yCoordinate;
    }

	public int getSquareType()
	{
		return EMPTY;
	}

	public void setCovered(boolean covered) {
		this.isCovered = covered;
	}
	
    public boolean isCovered()
    {
        return isCovered;
    }

    public boolean isFlagged()
    {
        return isFlagged;
    }
    
    public boolean isInProcessing()
    {
    	return inProcessing;
    }
    
    public void startProcessing()
    {
    	inProcessing = true;
    }
    
    public void stopProcessing()
    {
    	inProcessing = false;
    }
	
	public void leftClick() 
	{
        if (!isFlagged)
		    uncover();
        if (parentFrame.isGameWon())
            System.out.println("YOU WIN!");
	}
	
	public void rightClick() 
	{
		if (this.isCovered())
        {
			if (!isFlagged && this.getSquareType() == BOMB)
	        {
	            parentFrame.decrementFlaggedBombs();
	        }
	        else if (isFlagged && this.getSquareType() == BOMB)
	        {
	            parentFrame.incrementFlaggedBombs();
	        }
	        else if (!isFlagged && this.getSquareType() != BOMB)
	        {
	            parentFrame.decrementBadFlags();
	        }
	        else if (isFlagged && this.getSquareType() != BOMB)
	        {
	            parentFrame.incrementBadFlags();
	        }
	
	        isFlagged = !isFlagged;
			swapColor();
	
	        if (parentFrame.isGameWon())
	            System.out.println("YOU WIN!");
        }
	}
	
	private void swapColor()
	{
        if (isCovered())
        {
            if (this.isFlagged)
                this.setBackground(FLAGGED_COLOR);
            else
                this.setBackground(NON_ACTIVE_COLOR);
        }
	}
	
	public void uncover()
	{
		parentFrame.uncover(this);
	}

    public void unFlag()
    {
        this.isFlagged = false;
    }
	
	public void setBombText(int numBombsAround)
	{
		numberOfBombsAround.setText("" + numBombsAround);
	}

	@Override
	public void mouseClicked(MouseEvent e) 
	{
		if ((e.getModifiers() & InputEvent.BUTTON1_MASK) == InputEvent.BUTTON1_MASK)
			leftClick();
		else
			rightClick();
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// fTODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
