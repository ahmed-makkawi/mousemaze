package test;

import maze.grid.BoardMaker;

public class test {
	public static void main(String[] args) {
		BoardMaker bm = new BoardMaker();
		bm.GenGrid();
		System.out.println(bm.printBoard());
	}
}
