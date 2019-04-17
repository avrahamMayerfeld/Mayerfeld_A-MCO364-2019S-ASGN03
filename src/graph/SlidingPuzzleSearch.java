package graph;

import java.util.HashSet;
import java.util.Stack;

public class SlidingPuzzleSearch implements IPuzzleSearch {

	private HashSet<Integer> set = new HashSet<Integer>();
	private Stack<INode> stack = new Stack<INode>();
	private INode currentNode;
	private int iterativeDepth = 0;
	@Override
	public void search(INode searchNode) 
	{   
		currentNode = searchNode;
		while(!currentNode.isSolution())
		{	
			set.clear();
			iterativeDepth ++;
			performSearch();
		}
		
	}
	
	public void performSearch() 
	{
		stack.push(currentNode);
		set.add(currentNode.hashCode());
		while (!stack.isEmpty())
	    {   
			System.out.println("Searching on level " + currentNode.getLevel());
	    	if(currentNode.isSolution())
	    	{
	    		System.out.println("Solution found on level "+ currentNode.getLevel());
	    		System.out.println();
	    		System.out.println(currentNode.toString());
	    		break;// we are only searching for the first solution found
	    	}
	    	
	    	if(currentNode.getNextNodes() != null && currentNode.getLevel() <= iterativeDepth)
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
	    	set.add(currentNode.hashCode());
	    }
	}
}
