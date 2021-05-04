package _04_Maze_Maker;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

public class MazeMaker {

    private static int rows;
    private static int cols;

    private static Maze maze;

    private static Random randGen = new Random();
    private static Stack<Cell> uncheckedCells = new Stack<Cell>();

    public static Maze generateMaze(int r, int c) {
        rows = r;
        cols = c;
        maze = new Maze(rows, cols);

        // 1. Pick a random cell along the border and remove its exterior wall.
        //    This will be the starting point. Then select a random cell along
        //    the opposite wall and remove its exterior wall. This will be the
        //    finish line.
        int rand = (int)(Math.random()*rows);
        maze.getCell(rand, 0).setWestWall(false);
        rand = (int)(Math.random()*rows);
        maze.getCell(rand, cols-1).setEastWall(false);
        
        // 2. select a random cell in the maze to start
        rand = (int)(Math.random()*rows);
        int rand2 = (int)(Math.random()*cols);
        Cell cell = maze.getCell(rand, rand2);
        
        // 3. call the selectNextPath method with the randomly selected cell
        selectNextPath(cell);

        return maze;
    }

    // 4. Complete the selectNextPathMethod
    private static void selectNextPath(Cell currentCell) {
        // A. SET currentCell as visited
    	currentCell.setBeenVisited(true);
        // B. check for unvisited neighbors using the cell
    	ArrayList<Cell> list = getUnvisitedNeighbors(currentCell);

        // C. if has unvisited neighbors,
    	if(!list.isEmpty()) {
    		int rand = (int)(Math.random()*list.size());
    		Cell c = list.get(rand);
    		uncheckedCells.push(c);
    		if(currentCell.getCol()==c.getCol()-1) {
    			currentCell.setEastWall(false);
    			c.setWestWall(false);
    		}
    		if(currentCell.getCol()==c.getCol()+1) {
    			currentCell.setWestWall(false);
    			c.setEastWall(false);
    		}
    		if(currentCell.getRow()==c.getRow()-1) {
    			currentCell.setSouthWall(false);
    			c.setNorthWall(false);
    		}
    		if(currentCell.getRow()==c.getRow()+1) {
    			currentCell.setNorthWall(false);
    			c.setSouthWall(false);
    		}
    		
    		currentCell = c;
    		currentCell.setBeenVisited(true);
    		selectNextPath(c);
    		
    	}else {
    		if(!uncheckedCells.isEmpty()) {
    			currentCell = uncheckedCells.pop();
    			selectNextPath(currentCell);
    		}
    	}

        // C1. select one at random.

        // C2. push it to the stack

        // C3. remove the wall between the two cells

        // C4. make the new cell the current cell and SET it as visited

        // C5. call the selectNextPath method with the current cell

    	
        // D. if all neighbors are visited

        // D1. if the stack is not empty

        // D1a. pop a cell from the stack

        // D1b. make that the current cell

        // D1c. call the selectNextPath method with the current cell

    }

    // This method will check if c1 and c2 are adjacent.
    // If they are, the walls between them are removed.
    private static void removeWalls(Cell c1, Cell c2) {
        if (c1.getRow() == c2.getRow()) {
            if (c1.getCol() > c2.getCol()) {
                c1.setNorthWall(false);
                c2.setSouthWall(false);
            } else {
                c2.setNorthWall(false);
                c1.setSouthWall(false);
            }
        } else {
            if (c1.getRow() > c2.getRow()) {
                c1.setWestWall(false);
                c2.setEastWall(false);
            } else {
                c2.setWestWall(false);
                c1.setEastWall(false);
            }
        }
    }

    // This method returns a list of all the neighbors around the specified
    // cell that have not been visited. There are up to 4 neighbors per cell.
    //          1
    //       2 cell 3
    //          4
    private static ArrayList<Cell> getUnvisitedNeighbors(Cell c) {
        int row = c.getRow();
        int col = c.getCol();

        ArrayList<Cell> unvisitedNeighbors = new ArrayList<Cell>();

        if (row > 0 && !maze.getCell(row - 1, col).hasBeenVisited()) {
            unvisitedNeighbors.add(maze.getCell(row - 1, col));
        }

        if (col > 0 && !maze.getCell(row, col - 1).hasBeenVisited()) {
            unvisitedNeighbors.add(maze.getCell(row, col - 1));
        }

        if (row < rows - 1 && !maze.getCell(row + 1, col).hasBeenVisited()) {
            unvisitedNeighbors.add(maze.getCell(row + 1, col));
        }

        if (col < cols - 1 && !maze.getCell(row, col + 1).hasBeenVisited()) {
            unvisitedNeighbors.add(maze.getCell(row, col + 1));
        }

        return unvisitedNeighbors;
    }
}
