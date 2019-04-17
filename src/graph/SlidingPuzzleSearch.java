package graph;

import java.util.HashSet;
import java.util.Stack;

public class SlidingPuzzleSearch implements IPuzzleSearch {

	@Override
	public void search(INode searchNode) 
	{   
		HashSet<Integer> set = new HashSet<Integer>();
		Stack<INode> stack = new Stack<INode>();
		int iterativeDepth = 0;
		INode currentNode = searchNode;
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
	    	iterativeDepth ++;
	    	if(currentNode.getNextNodes() != null && currentNode.getLevel() <= iterativeDepth)
	    	{
			    for(INode child : currentNode.getNextNodes()) 
			    {
				   	if(!set.contains(child.hashCode()))
				   	{
				   		stack.push(child);
				    	set.add(child.hashCode());
				    }
			    }
	    	}
	    	currentNode = stack.pop();
	    }
	}
}
