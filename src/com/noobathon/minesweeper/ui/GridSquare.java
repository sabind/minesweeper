package com.noobathon.minesweeper.ui;

import java.awt.Color;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class GridSquare extends JPanel implements MouseListener
{
	private static final long serialVersionUID = 1523739159832757191L;
	
	private static final int SQUARE_SIDE_LENGTH = 75;
	private static final Color NON_ACTIVE_COLOR = Color.LIGHT_GRAY;
	private static final Color ACTIVE_COLOR = Color.RED;
	private static final Color BORDER_COLOR = Color.BLACK;
	
	private int xCoordinate, yCoordinate;
	private MinesweeperGridFrame parentFrame;
	
	public static final int BOMB = 1;
	public static final int EMPTY = 0;

	public static GridSquare newGridSquare(int yCoordinate, int xCoordinate, MinesweeperGridFrame parentFrame)
	{
		if (BombSquare.shouldBeABomb())
			return new BombSquare(xCoordinate, yCoordinate, parentFrame);
		else
			return new GridSquare(xCoordinate, yCoordinate, parentFrame);
	}

	
	public GridSquare(int xCoordinate, int yCoordinate, MinesweeperGridFrame parentFrame)
	{
		super();
		
		this.xCoordinate = xCoordinate;
		this.yCoordinate = yCoordinate;
		
		this.addMouseListener(this);
		
		this.parentFrame = parentFrame;
		setSize(SQUARE_SIDE_LENGTH, SQUARE_SIDE_LENGTH);
		setBorder(BorderFactory.createLineBorder(BORDER_COLOR));
		setBackground(NON_ACTIVE_COLOR);
	}
	
	public int getType()
	{
		return EMPTY;
	}
	
	public void leftClick() 
	{
		uncover();
	}
	
	public void rightClick() 
	{
		swapColor();
	}
	
	private void swapColor()
	{
		if (this.getBackground().equals(NON_ACTIVE_COLOR))
			this.setBackground(ACTIVE_COLOR);
		else
			this.setBackground(NON_ACTIVE_COLOR);
	}
	
	public void uncover()
	{
		parentFrame.uncover(xCoordinate, yCoordinate);
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
		// TODO Auto-generated method stub
		
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
