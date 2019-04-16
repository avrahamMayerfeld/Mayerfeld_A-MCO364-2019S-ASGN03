package graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Stack;

import org.apache.commons.lang3.NotImplementedException;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class SlidingPuzzleNode implements INode {
	
	int [][] board = new int [3][3];
	INode parent;
	int level;
	SlidingPuzzleNode childNodeLeft;
	SlidingPuzzleNode childNodeRight;
	SlidingPuzzleNode childNodeUp;
	SlidingPuzzleNode childNodeDown;
	
	ArrayList<INode> children =  new ArrayList<INode>();
	//blank space moves
    boolean canMoveLeft() {return board[0][0] > 0 && board[1][0] > 0 && board[2][0] > 0;}
    boolean canMoveRight() {return board[0][2] > 0 && board[1][2] > 0 && board[2][2] > 0;}
    boolean canMoveUp() {return board[0][0] > 0 && board[0][1] > 0 && board[0][2] > 0;}
    boolean canMoveDown() {return board[2][0] > 0 && board[2][1] > 0 && board[2][2] > 0;}
	
	//for child nodes.
	public SlidingPuzzleNode(int oldLevel, INode parent, int [][] newBoard)
	{
		level = oldLevel +1;
		this.parent = parent;
		board = newBoard;
	 }

	//for starting position 
	public SlidingPuzzleNode(int one, int two, int three, int four, int five, int six, int seven, int eight, int nine) 
	{
		level = 1;
		parent = null;
		board [0][0] = one;
		board [0][1] = two;
	    board [0][2] = three;
		board [1][0] = four;
		board [1][1] = five;
		board [1][2] = six;
		board [2][0] = seven;
		board [2][1] = eight;
        board [2][2] = nine;
	}
	
	@Override
	public ArrayList<INode> getNextNodes() 
	{	
		createAllMoves();
        children.add(childNodeLeft);
        children.add(childNodeRight);
        children.add(childNodeUp);
        children.add(childNodeDown);
        
		if(children.isEmpty())
			return null;
		else
		{
			children.removeIf(n -> n == null);
			return children;
		}
	}

	@Override
	public int getLevel() {
		return level;
	}

	@Override
	public INode getParent() {
		return parent;
	}

	@Override
	public boolean isSolution() {
		return Arrays.deepToString(board).equals("[[1, 2, 3], [4, 5, 6], [7, 8, 0]]");
	}

	@Override
	public double getHValue() {
		throw new NotImplementedException("This method is not implemented");
	}

	@Override
	public double getGValue() {
		throw new NotImplementedException("This method is not implemented");

	}
	
	//x and y are technically reversed
	//repetition of boolean canMove invocation was necessary for arrayOutOfBounds prevention
	public SlidingPuzzleNode moveLeft() 
	{   
		SlidingPuzzleNode sp = null;
		int[][] tempBoard = new int[3][3]; 
		for (int x = 0; x <= 2; x++)
		{
			for (int y = 0; y <= 2; y++)
			{
				tempBoard[x][y] = board[x][y];
			}
		}
		for (int x = 0; x <= 2; x++)
		{
			for (int y = 0; y <= 2; y++)
			{
				if (board[x][y] == 0)
				{
					if(canMoveLeft())
					{
						tempBoard[x][y] = board[x][y-1];
						tempBoard[x][y-1] = 0;
						break;
					}
					else
						return null;
				}
			}
		}
		sp = new SlidingPuzzleNode(level,this,tempBoard);
		return sp;		
	}
	
	public SlidingPuzzleNode moveRight() 
	{	
		SlidingPuzzleNode sp = null;
		int[][] tempBoard = new int[3][3]; 
		for (int x = 0; x <= 2; x++)
		{
			for (int y = 0; y <= 2; y++)
			{
				tempBoard[x][y] = board[x][y];
			}
		}
		for (int x = 0; x <= 2; x++)
		{
			for (int y = 0; y <= 2; y++)
			{
				if (board[x][y] == 0)  
				{
					if(canMoveRight())
					{
						tempBoard[x][y] = board[x][y+1];
						tempBoard[x][y+1] = 0;
						break;
					}
					else
						return null;
				}
			}
		}
		sp = new SlidingPuzzleNode(level,this,tempBoard);
		return sp;	
	}
	
	public SlidingPuzzleNode moveUp() 
	{
		SlidingPuzzleNode sp = null;
		int[][] tempBoard = new int[3][3]; 
		for (int x = 0; x <= 2; x++)
		{
			for (int y = 0; y <= 2; y++)
			{
				tempBoard[x][y] = board[x][y];
			}
		}
		for (int x = 0; x <= 2; x++)
		{
			for (int y = 0; y <= 2; y++)
			{
				if (board[x][y] == 0)
				{
					if(canMoveUp())
					{
						tempBoard[x][y] = board[x-1][y];
						tempBoard[x-1][y] = 0;
						break;
					}
					else
						return null;
				}
			}
		}
		sp = new SlidingPuzzleNode(level,this,tempBoard);
		return sp;		
	}
	
	public SlidingPuzzleNode moveDown() 
	{
		SlidingPuzzleNode sp = null;
		int[][] tempBoard = new int[3][3]; 
		for (int x = 0; x <= 2; x++)
		{
			for (int y = 0; y <= 2; y++)
			{
				tempBoard[x][y] = board[x][y];
			}
		}
		for (int x = 0; x <= 2; x++)
		{
			for (int y = 0; y <= 2; y++)
			{
				if (board[x][y] == 0)
				{
					if(canMoveDown())
					{
						tempBoard[x][y] = board[x+1][y];
						tempBoard[x+1][y] = 0;
						break;
					}
					else
						return null;
				}
			}
		}
		sp = new SlidingPuzzleNode(level,this,tempBoard);
		return sp;		
	}
	
	public void createAllMoves() {
		if(canMoveLeft())
			childNodeLeft = moveLeft();
		if(canMoveRight())
			childNodeRight = moveRight();
		if(canMoveUp())
			childNodeUp = moveUp();
		if(canMoveDown())
			childNodeDown = moveDown();
	}
	
	@Override
    public int hashCode()
    {
        Integer hash = 0;
        StringBuilder hashStringBuilder = new StringBuilder("1");
        String hashString;
        for (int x = 0; x <= 2; x++)
		{
			for (int y = 0; y <= 2; y++)
			{        
				hashStringBuilder.append(board[x][y]);
			}
		}
        hashString = hashStringBuilder.toString();
        hash = Integer.parseInt(hashString);
        return hash;
	}
	
    public String toString() 
    {
    	StringBuilder boardBuilder = new StringBuilder();
    	for (int x = 0; x <= 2; x++)
 		{
    		for (int y = 0; y <= 2; y++)
 			{
 				boardBuilder.append(board[x][y]);
 			}
 			boardBuilder.append("\r\n");
 		}
 				
    	return parent == null ? boardBuilder.toString() : parent.toString() + "\r\n" + boardBuilder.toString();
    }
        
}
