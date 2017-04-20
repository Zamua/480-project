import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Driver
{
	private static Scanner sc;

	public static void main(String[] args) throws FileNotFoundException
	{
		int width;
		int height;
		int t = 0;
		
		sc = new Scanner(new File("testinput"));
		
		height = sc.nextInt();
		width = sc.nextInt();
		
		int[][] grid = new int[height][width];
	
		for (int i = 0; i < height; i++){
			for (int j = 0; j < width; j++){
				if (sc.hasNextInt())
					t = sc.nextInt();
				else
					throw new IllegalArgumentException(); //not enough values
				
				if (t == 1 || t == 0)
					grid[i][j] = t;
				else
					throw new IllegalArgumentException(); //not 0 or 1

			}
		}
		
		if (sc.hasNextInt()){
			throw new IllegalArgumentException(); //too many values
		}
		
		
		sc.close();
		
		System.out.println(width);
		System.out.println(height);
		
		for (int i = 0; i < height; i++)
			for (int j = 0; j < width; j++){
				System.out.print(grid[i][j] + " ");
			}
			System.out.println("");
	}
}
