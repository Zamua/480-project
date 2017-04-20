import java.util.Scanner;

public class GridReader
{
    private Scanner scn;

    public GridReader(String filePath)
    {
        this.scn = new Scanner(filePath);
    }

    public int[][] read()
    {
        int[][] grid;
        int width;
        int height;
        int t = 0;

        height = scn.nextInt();
        width = scn.nextInt();

        grid = new int[height][width];

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (scn.hasNextInt())
                    t = scn.nextInt();
                else
                    throw new IllegalArgumentException(); // not enough values

                if (t == 1 || t == 0)
                    grid[i][j] = t;
                else
                    throw new IllegalArgumentException(); // not 0 or 1

            }
        }

        if (scn.hasNextInt()) {
            throw new IllegalArgumentException(); // too many values
        }

        scn.close();

        return grid;
    }
}
