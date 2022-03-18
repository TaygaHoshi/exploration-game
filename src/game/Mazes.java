package game;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

public class Mazes {
	
	Random rnd = new Random();
	

	public void generateByDFS(MapNode[][] board) {
		
		//fill alternating
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				board[i][j] = new MapNode(5);
				board[i][j].x = i;
				board[i][j].y = j;
				board[i][j].setVisible(true);
				if(i%2 == 0 && j%2 == 0)
					board[i][j].setType(2);
					
			}
		}
		
		Stack<MapNode> pathstack = new Stack<MapNode>();
		
		//select a random cell and add it to the stack
		pathstack.push(board[2][2]);
		
		while(!pathstack.isEmpty()) {
			
			MapNode temp = pathstack.pop();
			ArrayList<MapNode> neighbors = getNeightbors(board, temp);
			
			if(!neighbors.isEmpty()) {
				
				int randomlySelectedNeighbor = genrng(0, neighbors.size() - 1);
				
				for (int i = 0; i < neighbors.size(); i++) {
					connectNodes(board, temp, neighbors.get(i));
					
					if(i != randomlySelectedNeighbor) pathstack.push(neighbors.get(i));
				}
				
				pathstack.push(neighbors.get(randomlySelectedNeighbor));
			}
			
			
		}
		
		
		
		//fillsides(board);
		
	}
	
	private void connectNodes(MapNode[][] board, MapNode cell1, MapNode cell2) {
		
		board[(cell1.x + cell2.x)/2][(cell1.y + cell2.y)/2].setType(2);
		cell1.isVisited = true;
		cell2.isVisited = true;
		
	}
	
	private ArrayList<MapNode> getNeightbors(MapNode[][] board, MapNode cell) {
		ArrayList<MapNode> nei = new ArrayList<MapNode>();
		
		if(cell.x - 2 >= 0 && !board[cell.x - 2][cell.y].isVisited) nei.add(board[cell.x - 2][cell.y]);

		if(cell.x + 2 < board.length && !board[cell.x + 2][cell.y].isVisited) nei.add(board[cell.x + 2][cell.y]);

		if(cell.y - 2 >= 0 && !board[cell.x][cell.y - 2].isVisited) nei.add(board[cell.x][cell.y - 2]);

		if(cell.y + 2 < board[0].length && !board[cell.x][cell.y + 2].isVisited) nei.add(board[cell.x][cell.y + 2]);
		
		/*
		if(cell.x - 2 >= 0 && cell.y - 2 >= 0 && !board[cell.x - 2][cell.y - 2].isVisited) nei.add(board[cell.x - 2][cell.y - 2]);
		
		if(cell.x + 2 < board.length && cell.y - 2 >= 0 && !board[cell.x + 2][cell.y - 2].isVisited) nei.add(board[cell.x + 2][cell.y - 2]);
		
		if(cell.x - 2 >= 0 && cell.y + 2 < board[0].length && !board[cell.x - 2][cell.y + 2].isVisited) nei.add(board[cell.x - 2][cell.y + 2]);
		
		if(cell.x + 2 < board.length && cell.y + 2 < board[0].length && !board[cell.x + 2][cell.y + 2].isVisited) nei.add(board[cell.x + 2][cell.y + 2]);
		*/
		return nei;
	}
	
	public void generateByRecursion(MapNode[][] board) {
		
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				board[i][j] = new MapNode(2);
				board[i][j].setVisible(true);
			}
		}
		
		recurseDivMaze(board, 0, 0, board.length - 1, board[0].length - 1);
		
		fillsides(board);
		
	}
	
	private void fillsides(MapNode[][] board) {
		
		for (int i = 0; i < board.length; i++) {
			board[i][0].setType(5);
			board[i][board[0].length - 1].setType(5);
		}
		for (int i = 0; i < board[0].length; i++) {
			board[0][i].setType(5);
			board[board.length - 1][i].setType(5);
		}
		
	}

	private void recurseDivMaze(MapNode[][] board, int startx, int starty, int endx, int endy) {
		
		// base case
		if(endx - startx <= 2 || endy - starty <= 2) {
			return;
		}
		
		// recursive case
		/// randomly select either horizontally or vertically
		boolean isHorizontal = (endx - startx) > (endy - starty) ? true : false;
		
		/// fill it with walls and select an opening
		int select;
		int doorway;
		if(isHorizontal) {
			select = genrng(startx, endx); // select a row in a box
			
			for (int j = starty; j <= endy; j++) { // fill that 	
					board[select][j].setType(5);
			}
			
			doorway = genrng(starty, endy);
			board[select][doorway].setType(2);
			
		}
		else {
			
			select = genrng(starty, endy); // select a column in a box
			
			for (int j = startx; j <= endx; j++) { // fill that 	
					board[j][select].setType(5);
			}
			
			doorway = genrng(startx, endx);
			board[doorway][select].setType(2);
			
			
		}
		
		
		
		
		/// continue
		
		if(isHorizontal) {
			// select top and bottom

			recurseDivMaze(board, startx, starty, select, endy); //top

			recurseDivMaze(board, select, starty, endx, endy); //bottom

		}
		else {
			// select left and right

			recurseDivMaze(board, startx, starty, endx, select);

			recurseDivMaze(board, startx, select, endx, endy);
		}
		
		
	}
	
	public int genrng(int start, int end) {
		return rnd.nextInt((end - start) + 1) + start;
		
	}
	
}
