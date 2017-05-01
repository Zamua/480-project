import java.awt.Point;
import java.util.HashSet;
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

    private HashSet<Point> seeds;

    public ClusterSeeder(int[][] gridA, int[][] gridB)
    {
        this.rowRand = new Random();
        this.colRand = new Random();

        this.seeds = new HashSet<Point>();

        this.a1Seed = generateUniqueSeed(gridA);
        this.a2Seed = generateUniqueSeed(gridA);
        this.b1Seed = generateUniqueSeed(gridB);
        this.b2Seed = generateUniqueSeed(gridB);

        this.gridA = gridA;
        this.gridB = gridB;
    }

    public void seed()
    {
        System.err.printf("A: (%d,%d)\t B: (%d,%d)\n", a1Seed.y, a1Seed.x, a2Seed.y, a2Seed.x);
        tandemFloodFill(gridA, a1Seed.y, a1Seed.x, a2Seed.y, a2Seed.x, true);
        // tandemFloodFill(gridB, b1Row, b1Col, b2Row, b2Col, true);
    }

    private Point generateUniqueSeed(int[][] grid)
    {
        Point seed = new Point(colRand.nextInt(grid[0].length), rowRand.nextInt(grid.length));

        while (seeds.contains(seed)) {
            seed = new Point(colRand.nextInt(grid[0].length), rowRand.nextInt(grid.length));
        }

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
            int ri1 = row1 + row[i];
            int ci1 = col1 + col[i];
            int ri2 = row2 + row[i];
            int ci2 = col2 + col[i];
            if (toggle) {
                if (inBounds(grid, ri1, ci1) && grid[ri1][ci1] == 0) {
                    tandemFloodFill(grid, ri1, ci1, row2, col2, !toggle);
                }
            } else {
                if (inBounds(grid, ri2, ci2) && grid[ri2][ci2] == 0) {
                    tandemFloodFill(grid, row1, col1, ri2, ci2, !toggle);
                }
            }
        }
    }

    private boolean inBounds(int[][] grid, int row, int col)
    {
        return row >= 0 && col >= 0 && row < grid.length && col < grid[row].length;
    }
}
