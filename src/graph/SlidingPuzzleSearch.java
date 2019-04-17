package graph;

import java.util.HashSet;
import java.util.Stack;

public class SlidingPuzzleSearch implements IPuzzleSearch {
	private HashSet<Integer> set = new HashSet<Integer>();
	private Stack<INode> stack = new Stack<INode>();
	private boolean solutionFound;
	private INode currentNode;
	private int bound = 0;//for iterative deepening
	@Override
	public void search(INode searchNode) 
	{   
		int timesDidDFS = 0;
		while(!solutionFound)
		{	
			set.clear();
			bound ++;
			currentNode = searchNode;
			stack.push(currentNode);
			doBoundedDFS();
			timesDidDFS++;
			if(timesDidDFS > 50)//prevent infinite loop in unsolvable puzzles
			{
				System.out.println("This puzzle is unsolvable.");
				break;
			}
		}
		if(currentNode.isSolution()) 
		{
			System.out.println("Solution found on level "+ currentNode.getLevel());
			System.out.println();
			System.out.println(currentNode.toString());
		}
	}
	
	private void doBoundedDFS() 
	{   
		while (!stack.isEmpty())
		{   
			set.add(currentNode.hashCode());
			System.out.println("Searching on level " + currentNode.getLevel());
			if(currentNode.isSolution())
			{
				solutionFound = true;
				break;
			}
			if(currentNode.getNextNodes() != null && currentNode.getLevel() <= bound)
	    	{
			    for(INode child : currentNode.getNextNodes()) 
			    {
				   	if(!set.contains(child.hashCode()))
				   	{
				   		stack.push(child);
				   	}
				}
	    	}
	    	currentNode = stack.pop();
	    }
	}
}
