package graph;

import java.util.Stack;

public class PuzzleDemo  
{

	public static void main(String[]args)
	{
		SlidingPuzzleNode spn = new SlidingPuzzleNode(8,6,7,2,5,4,3,0,1);
		SlidingPuzzleSearch sps = new SlidingPuzzleSearch();
		sps.search(spn);
	}

}
