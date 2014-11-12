package maze.solver;

import java.util.ArrayList;
import java.util.PriorityQueue;

import maze.grid.BoardMaker;
import maze.utilities.Node;

public class ToBeRenamed {
	public static void main(String[] args) {
		BoardMaker bm = new BoardMaker();
		bm.GenGrid();

		Node x1 = new Node(null, bm.getBoard(), 0, 0);
		x1.costHeuristicValue = 10;
		Node x2 = new Node(null, bm.getBoard(), 0, 0);
		x2.costHeuristicValue = 1;
		Node x3 = new Node(null, bm.getBoard(), 0, 0);
		x3.costHeuristicValue = 1;
		Node x4 = new Node(null, bm.getBoard(), 0, 0);
		x4.costHeuristicValue = 2;

		ArrayList<Node> nodes = new ArrayList<>();
		nodes.add(x1);
		nodes.add(x2);
		nodes.add(x3);
		nodes.add(x4);

		PriorityQueue<Node> tree = new PriorityQueue<>();
		tree.add(x1);
		tree.add(x2);
		tree.add(x3);
		tree.add(x4);

		tree.addAll(nodes);
		System.out.println("Tree set data: " + tree.size());
		Node t;
		// Displaying the Tree set data
		while ((t = tree.poll()) != null) {
			System.out.println(t.costHeuristicValue + " ");
		}

	}
}
