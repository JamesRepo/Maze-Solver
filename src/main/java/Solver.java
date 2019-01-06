/*
 * ==== Solver ====
 * ----------------
 * Class to handle all of the logic for solving the mazes.
 * A maze is passed as an argument in the class' instantiation.
 * 
 */
public class Solver {
	/* Maze is passed as an argument at object's creation */ 
	private Maze maze;

	public Solver(Maze maze) {
		this.maze = maze;
	}
	
	/* Solve method separate to Solve Maze. Allows printing of the maze before and after solving and we don't have to pass argument in the main method */
	public void solve() {
		maze.printMaze();
		solveMaze(maze.getStartX(), maze.getStartY());
		maze.printMaze();
	}
	
	/* Recursive method to solve the maze */ 
	public boolean solveMaze(int x, int y) {
		// Would mean it was outside of the maze
		if (!maze.isValidPosition(x, y)) {
			return false;
		}
		// If we have reached the goal
		else if (maze.isGoal(x, y)) {
			maze.markStart();
			return true;
		}
		// Position already occupied with an X
		else if (maze.isOccupied(x, y)) {
			return false;
		}
		// Where its moving into is a wall so can't go there
		else if (maze.isWall(x, y)) {
			return false;
		}
		// If none of the above then mark this x and y coordinate as part of the solution path
		maze.setPath(x, y);
		// Recursion
		// South
		if (solveMaze(x + 1, y) == true) {
			return true;
		}
		// North 
		else if (solveMaze(x - 1, y) == true) {
			return true;
		}
		// East
		else if (solveMaze(x, y + 1) == true) {
			return true;
		}
		// West
		else if (solveMaze(x, y - 1) == true) {
			return true;
		}
		// Un-mark from solution path
		maze.unmarkPath(x, y);
		return false;
	}
}