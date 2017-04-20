import java.io.FileNotFoundException;

public class Driver
{
    public static void main(String[] args) throws FileNotFoundException
    {
        ClusterSeeder cs;

        int[][] A = GridUtils.read("A");
        int[][] B = GridUtils.read("B");

        System.err.println("hi");

        cs = new ClusterSeeder(A, B);

        System.err.println("BEFORE");
        System.err.println("A");
        GridUtils.print(A);
        System.err.println("B");
        GridUtils.print(B);

        cs.seed();

        System.err.println("A");
        GridUtils.print(A);
        System.err.println("B");
        GridUtils.print(B);
    }
}
