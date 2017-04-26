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

    private final int[] directions = { -1, 1 };

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
		if (row1 >= grid.length || row2 >= grid.length || col1 >= grid[0].length ||
			col2 >= grid[0].length || row1 < 0 || row2 < 0 || col1 < 0 || col2 < 0) {
			return;
		}

        if (grid[row1][col1] != 0 && grid[row2][col2] != 0) {
            return;
        }

        if (grid[row1][col1] == 0) {
            grid[row1][col1] = 1;
        }

        if (grid[row2][col2] == 0) {
            grid[row2][col2] = 2;
        }

        for (int r = 0; r < directions.length; r++) {
			tandemFloodFill(grid, row1 + directions[r], col1, row2 + directions[r], col2);
		}

        for (int c = 0; c < directions.length; c++) {
			tandemFloodFill(grid, row1, col1 + directions[c], row2, col2 + directions[c]);
		}
	}
}
