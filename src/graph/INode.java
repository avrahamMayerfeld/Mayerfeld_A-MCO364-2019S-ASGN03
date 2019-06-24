package graph;


import java.util.ArrayList;
import java.util.List;

public interface INode {
	ArrayList<INode> getNextNodes();
	int getLevel();
	INode getParent();
	boolean isSolution();
	double getHValue();
	double getGValue();
	int getBoardAsInt();
}
