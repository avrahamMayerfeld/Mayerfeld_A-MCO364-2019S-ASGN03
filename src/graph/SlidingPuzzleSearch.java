package graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.Set;
import java.util.Stack;
import java.util.stream.Stream;


public class SlidingPuzzleSearch implements IPuzzleSearch 
{
	static int nodesSearched = 1;
	private LinkedHashMap<Integer, Integer> totalTimesNodeConsidered_EvenIfUnsearched = new LinkedHashMap<Integer,Integer>();
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
			
			bound ++;
			currentNode = searchNode;
			stack.push(currentNode);
			doBoundedDFS();
			timesDidDFS++;
			if(timesDidDFS > 50)//prevent infinite loop in unsolvable puzzles, 50 is probably very generouse			{
				System.out.println("This puzzle is unsolvable.");
				break;
			}
		}
		if(currentNode.isSolution()) 
		{
			System.out.println("Solution found on level "+ currentNode.getLevel());
			System.out.println("Number of nodes searched: " + nodesSearched);
			System.out.println();
			System.out.println(currentNode.toString());
		}
		printNodesOccurences();
	}
	
	private void doBoundedDFS() 
	{   
		while (!stack.isEmpty())
		{   
			nodesSearched++;
			set.add(currentNode.hashCode());
			System.out.println("Searching on level " + currentNode.getLevel());
			if(currentNode.isSolution())
			{
				solutionFound = true;
				break;
			}
			ArrayList<INode> childList = currentNode.getNextNodes(); 
			if(childList != null && currentNode.getLevel() <= bound)
	    		{
			    for(INode child : childList) 
			    {
			    	mapOccurences(child);
			    	
				   	if(!set.contains(child.hashCode()))
				   	{
				   		stack.push(child);
				   	}
			    }
	    	 	}
			currentNode = stack.pop();
		}
		set.clear();
		stack.clear();
	}
	
	public void mapOccurences(INode child) {
		if(totalTimesNodeConsidered_EvenIfUnsearched.containsKey(child.getBoardAsInt()))
    		{
    			totalTimesNodeConsidered_EvenIfUnsearched.put( child.getBoardAsInt(),
    				totalTimesNodeConsidered_EvenIfUnsearched.get(child.getBoardAsInt()) + 1 );
    		}
    		else
    		{
    			totalTimesNodeConsidered_EvenIfUnsearched.put(child.getBoardAsInt(), 1);
    		}
	}
	
	static int numberOfNodes = 0;
	public void printNodesOccurences() {
		System.out.println("The following tracks occurences of vertices, although each was pushed onto the stack only once; their encounters as children are tracked.");
		Set<Entry<Integer, Integer>> totalEntry = totalTimesNodeConsidered_EvenIfUnsearched.entrySet();
		totalEntry.stream().forEach(e -> 
		{
			System.out.println("vertex # "+ ++numberOfNodes);
			if(e.getKey() < 100000000)
				System.out.print("0");
			System.out.println(e.getKey() + "------" + e.getValue() + " occurences");
		});
	}
}
