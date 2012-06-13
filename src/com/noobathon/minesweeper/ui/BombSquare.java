package com.noobathon.minesweeper.ui;

import java.awt.Color;

public class BombSquare extends GridSquare
{	
	private static final long serialVersionUID = 3532242734639397004L;
	
	private static final Color BLOW_UP = Color.RED;
	private static final double DEFAULT_BOMB_GENERATION_RATIO = 0.05;
	
	public static boolean shouldBeABomb() 
	{
		double rand = Math.random();
		if (rand < DEFAULT_BOMB_GENERATION_RATIO)
			return true;
		else
			return false;
	}
	
	public int getSquareType()
	{
		return GridSquare.BOMB;
	}
	
	public BombSquare(int yCoordinate, int xCoordinate, MinesweeperGridFrame parentFrame)
	{
		super(xCoordinate, yCoordinate, parentFrame);
	}

	public void leftClick()
	{
		System.out.println("Game Over!");
		setBackground(BLOW_UP);
	}

    public void rightClick()
    {
        super.rightClick();
        if (parentFrame.isGameWon())
            System.out.println("YOU WIN!");
    }
}
