package com.noobathon.minesweeper.ui;

import java.awt.Color;

public class BombSquare extends GridSquare
{	
	private static final long serialVersionUID = 3532242734639397004L;
	
	public static final Color BLOW_UP = Color.RED;
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
	
	public BombSquare(int yCoordinate, int xCoordinate)
	{
		super(xCoordinate, yCoordinate);
	}

	public void leftClick()
	{
        if (!this.isFlagged())
        {
        	this.setCovered(false);
            System.out.println("Game Over!");
            setBackground(BLOW_UP);
        }
	}

    public void rightClick()
    {
        super.rightClick();
    }
}
