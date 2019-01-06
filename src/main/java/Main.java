import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.print("Enter File Name: ");
		Solver solver = new Solver(new Maze(input.nextLine()));
		solver.solve();
	}
}