import java.awt.Point;
import java.util.Random;

public class ClusterSeeder
{
    private Random rowRand;
    private Random colRand;
    private int[][] gridA, gridB;
    private Point a1Seed;
    private Point a2Seed;
    private Point b1Seed;
    private Point b2Seed;

    private final int[] row = { 1, 0, -1, 0 };
    private final int[] col = { 0, 1, 0, -1 };

    public ClusterSeeder(int[][] gridA, int[][] gridB)
    {
        this.gridA = gridA;
        this.gridB = gridB;

        this.rowRand = new Random();
        this.colRand = new Random();

        this.a1Seed = generateSeed(gridA);
        this.b1Seed = generateSeed(gridB);

        // Ensure seeds aren't equivalent for gridA
        this.a2Seed = generateSeed(gridA);
        while (a2Seed.equals(a1Seed)) {
            this.a2Seed = generateSeed(gridA);
        }

        // Ensure seeds aren't equivalent for gridB
        this.b2Seed = generateSeed(gridB);
        while (b2Seed.equals(b1Seed)) {
            this.a2Seed = generateSeed(gridB);
        }
    }

    public void seed()
    {
        System.err.printf("A: (%d,%d)\t B: (%d,%d)\n", a1Seed.y, a1Seed.x, a2Seed.y, a2Seed.x);
        tandemFloodFill(gridA, a1Seed.y, a1Seed.x, a2Seed.y, a2Seed.x, true);
        // tandemFloodFill(gridB, b1Row, b1Col, b2Row, b2Col, true);
    }

    private Point generateSeed(int[][] grid)
    {
        Point seed = new Point(colRand.nextInt(grid[0].length), rowRand.nextInt(grid.length));

        return seed;
    }

    private void tandemFloodFill(int[][] grid, int row1, int col1, int row2, int col2, boolean toggle)
    {
        if (toggle) { // seed 1
            grid[row1][col1] = 1;
        } else { // seed 2
            grid[row2][col2] = 2;
        }

        for (int i = 0; i < 4; i++) {
            int newRow = row[i];
            int newCol = col[i];
            if (toggle) {
                newRow += row1;
                newCol += col1;
                if (newRow == row2 && newCol == col2) {
                    continue;
                }
                if (inBounds(grid, newRow, newCol) && grid[newRow][newCol] == 0) {
                    // give old values to seed2 because seed1 just filled
                    tandemFloodFill(grid, newRow, newCol, row2, col2, !toggle);
                }
            } else {
                newRow += row2;
                newCol += col2;

                if (newRow == row1 && newCol == col1) {
                    continue;
                }

                if (inBounds(grid, newRow, newCol) && grid[newRow][newCol] == 0) {
                    // give old values to seed1 because seed2 just filled
                    tandemFloodFill(grid, row1, col1, newRow, newCol, !toggle);
                }
            }
        }
    }

    private boolean inBounds(int[][] grid, int row, int col)
    {
        return row >= 0 && col >= 0 && row < grid.length && col < grid[row].length;
    }
}
