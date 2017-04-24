import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class GridUtils
{
    public static int[][] read(String filePath)
    {
        int[][] grid;
        int row;
        int col;
        int t = 0;

        grid = null;

        // close scanner when done
        try (Scanner scn = new Scanner(new File(filePath))) {
            row = scn.nextInt();
            col = scn.nextInt();

            grid = new int[row][col];

            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    if (scn.hasNextInt())
                        t = scn.nextInt();
                    else
                        throw new IllegalArgumentException(); // not enough
                                                                // values
                    if (t == 1 || t == 0)
                        grid[i][j] = t;
                    else
                        throw new IllegalArgumentException(); // not 0 or 1
                }
            }

            if (scn.hasNextInt()) {
                throw new IllegalArgumentException(); // too many values
            }
        } catch(FileNotFoundException fnfe) {
            fnfe.printStackTrace();
            System.exit(1);
        }

        return grid;
    }

    public static void print(int[][] a) {
        for (int i = 0; i < a.length; i++) {
            System.err.println();
            for (int j = 0; j < a[i].length; j++) {
                System.err.print(a[i][j] + " ");
            }
        }
    }
    
    // This print method accepts a cluster object 
    public static void print(Cluster a) {
    	print(a.cluster);
    }
}
