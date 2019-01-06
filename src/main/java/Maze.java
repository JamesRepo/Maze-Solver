import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
/*
 * ==== Maze ====
 * --------------
 * Class to store details on the inputed maze.
 * Handles file reading and creating a 2D array representing the maze.
 * 
 */
public class Maze {
	
	/* Maze outputs. Put these as constants up here to make it easier to change if needed */
	private final char WALL = '#';
	private final char PASSAGE = ' ';
	private final char START = 'S';
	private final char END = 'E';
	private final char PATH = 'X';
	/* 2D Array to store the maze */
	private char [][] maze; // Found it easier to have the 2D array as char instead of int 
	/* Variables for maze details */
	private int width, height;
	private int startX, startY;
	private int endX, endY;
	/* Stores the file name */
	private String fileName;
	
	/*
	 * Maze
	 */
	public Maze(String file) {
		// Initialise variables
		width = 0;
		height = 0;
		startX = 0;
		startY = 0;
		endX = 0;
		endY = 0;
		fileName = "src/main/resources/" + file;
		// Calls create maze method in constructor
		createMaze();;
	}
	
	public void createMaze() {
		// Scanner over Buffered Reader for this as we want to parse integers
		try {
			Scanner scan = new Scanner(new File(fileName));
			// Width
			width = scan.nextInt();
			// Height
			height = scan.nextInt();
			// Start position X
			startX = scan.nextInt();
			// Start position Y
			startY = scan.nextInt();
			// End position X
			endX = scan.nextInt();
			// End position Y 
			endY = scan.nextInt();
			// Initialise maze now we have width and height
			maze = new char [width][height];
			// Loops to fill array
			for (int i = 0; i < height; i++) {
				for (int a = 0; a < width; a++) {
					maze[a][i] = (char)(scan.nextInt() + '0'); // Doesn't appear clean doing this first but will get No Such Element exception if trying to convert straight into array
					if (maze[a][i] == '1') {
						maze[a][i] = WALL; // Walls are hash #
					}
					else if (maze[a][i] == '0') {
						maze[a][i] = PASSAGE; // Passages are a space
					}
				}
			}
			scan.close(); // Close the scanner to make sure of no resource leak. Not really needed but is cleaner
		} 
		catch (FileNotFoundException e) {
			System.out.println("File not found."); // Print error, not stack trace as we are using the console for input/output
			System.exit(0);; // Exits the program if the file name is incorrect. Will have to restart program to re-enter
		}
		// Mark start and end points in the maze
		markStart();
		markEnd();
	}
	
	/*
	 * Method to print the Maze. 
	 */
	public void printMaze() {
		StringBuilder printMaze = new StringBuilder();
		for (int i = 0; i < height; i++) {
			printMaze.append("\n");
			for (int a = 0; a < width; a++) {
				printMaze.append(maze[a][i]);
			}
		}
		printMaze.append("\n");
		System.out.print(printMaze.toString());
	}
	
	/*
	 * Methods to be used in Solver class, they are used to check the positions in the maze. Having these helps with encapsulation
	 */
	public boolean isValidPosition(int x, int y) { // Checks if the position is inside the maze
		return !(x < 0 || x > height || y < 0 || y > width);
	}
	public boolean isGoal(int x, int y) { // Checks if we are at the end of the maze
		return (x == endX && y == endY);
	}
	public boolean isWall(int x, int y) { // Checks if is a wall
		return (maze[x][y] == '#');
	}
	public boolean isOccupied(int x, int y) { // Checks if already occupied
		return (maze[x][y] == 'X');
	}
	
	/* Methods to mark/un-mark a position as part of the solution */
	public void setPath(int x, int y) {
		maze[x][y] = PATH;
	}
	public void unmarkPath(int x, int y) {
		maze[x][y] = PASSAGE;
	}
	
	/* Mark the start and end on the maze. In methods as we call it in the Solver class to remark */
	public void markStart() {
		maze[startX][startY] = START;
	}
	public void markEnd() {
		maze[endX][endY] = END;
	}
	
	/*
	 * Get methods to access information.
	 */
	public int getWidth() {
		return width;
	}
	public int getHeight() {
		return height;
	}
	public int getStartX() {
		return startX;
	}
	public int getStartY() {
		return startY;
	}
	public int getEndX() {
		return endX;
	}
	public int getEndY() {
		return endY;
	}
	public char [][] getMaze() {
		return maze;
	}
}