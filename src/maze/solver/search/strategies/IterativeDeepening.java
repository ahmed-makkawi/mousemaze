package maze.solver.search.strategies;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Stack;

import maze.solver.search.IQueueingFunction;
import maze.solver.utilities.Constants;
import maze.solver.utilities.Node;

/**
 * Implements the Iterative Deepening Algorithm. 
 * @author Omar
 *
 */
public class IterativeDeepening implements IQueueingFunction {

    public Stack<Node> stack = new Stack<Node>();

    public int level;

    public IterativeDeepening() {
	level = -1;
    }

    @Override
    public void add(Node x) {
	stack.add(x);
	level++;
    }

    @Override
    public void addAll(Collection<? extends Node> c) {
	stack.addAll(c);
    }

    @Override
    public Node poll() {
	return stack.pop();
    }

    /**
     * @return True if level exceeded Constants.maxLevel OR stack is empty,
     *         False if level didn't exceed Constants.maxLevel AND stack isn't
     *         empty
     */
    @Override
    public boolean isEmpty() {
	return level > Constants.maxLevel;
    }

    /**
     * @return Empty list in case parent exists in maximum level allowed, else
     *         return children of parent
     */

    @Override
    public List<Node> getChildNodes(Node parent) {
	if (parent.depthCost == level)
	    return new ArrayList<Node>();
	
	return parent.getChildNodes();
    }
}
