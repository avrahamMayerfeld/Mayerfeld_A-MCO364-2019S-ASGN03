package graph;

import java.util.List;

public interface INode {
	List<INode> getNextNodes();
	int getLevel();
	INode getParent();
	boolean isSolution();
	double getHValue();
	double getGValue();
}
