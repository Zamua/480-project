import java.util.Random;

public class ClusterSeeder
{
    private Random rowRand;
    private Random colRand;
    private int a1Row, a1Col;
    private int a2Row, a2Col;
    private int b1Row, b1Col;
    private int b2Row, b2Col;
    private int[][] gridA, gridB;

    private final int[] row = { -1, 0, 1, 0 };
    private final int[] col = { 0, -1, 0, 1 };

    public ClusterSeeder(int[][] gridA, int[][] gridB)
    {
        this.rowRand = new Random();
        this.colRand = new Random();

        this.a1Row = rowRand.nextInt(gridA.length);
        this.a1Col = colRand.nextInt(gridA[0].length);
        this.a2Row = rowRand.nextInt(gridA.length);
        this.a2Col = colRand.nextInt(gridA[0].length);

        this.b1Row = rowRand.nextInt(gridA.length);
        this.b1Col = colRand.nextInt(gridA[0].length);
        this.b2Row = rowRand.nextInt(gridA.length);
        this.b2Col = colRand.nextInt(gridA[0].length);

        this.gridA = gridA;
        this.gridB = gridB;
    }

    public void seed()
    {
        tandemFloodFill(gridA, a1Row, a1Col, a2Row, a2Col);
        tandemFloodFill(gridB, b1Row, b1Col, b2Row, b2Col);
    }

    private void tandemFloodFill(int[][] grid, int row1, int col1, int row2, int col2)
    {
        if (inBounds(grid, row1, col1) && grid[row1][col1] != 0 &&
            inBounds(grid, row2, col2) && grid[row2][col2] != 0) {
            return;
        }

        if (inBounds(grid, row1, col1) && grid[row1][col1] == 0) {
            grid[row1][col1] = 1;
        }

        if (inBounds(grid, row2, col2) && grid[row2][col2] == 0) {
            grid[row2][col2] = 2;
        }

        for (int i = 0; i < 4; i++) {
            int ri1 = row1 + row[i];
            int ri2 = row2 + row[i];
            int ci1 = col1 + col[i];
            int ci2 = col2 + col[i];

            System.err.printf("ri1: %d\tri2: %d\tci1: %d\tci2: %d\n", ri1, ri2, ci1, ci2);
            tandemFloodFill(grid, ri1, ci1, ri2, ci2);
        }
    }

    private boolean inBounds(int[][] grid, int row, int col)
    {
        System.err.println(row > 0 && col > 0 && row < grid.length && col < grid[row].length);
        return row > 0 && col > 0 && row < grid.length && col < grid[row].length;
    }
}
