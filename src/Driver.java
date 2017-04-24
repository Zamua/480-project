import java.io.FileNotFoundException;

public class Driver
{
    public static void main(String[] args) throws FileNotFoundException
    {
        int[][] A = GridUtils.read("A");
        int[][] B = GridUtils.read("B");

        //cs = new ClusterSeeder(A, B);

        //System.err.println("BEFORE");
        //System.err.println("A");
        //GridUtils.print(A);
        //System.err.println("B");
        //GridUtils.print(B);

        //cs.seed();
        
		//System.err.println("After seeding");
        //System.err.println("A");
        //GridUtils.print(A);
        //System.err.println("B");
        //GridUtils.print(B);
        
        Cluster c1 = new Cluster(A);
        Cluster c2 = new Cluster(B);
        
        System.err.print("\n Minimum Distance :");
        System.err.print(c1.minDistance(c2));
        
        GridUtils.print(c1);
        System.err.println("");
        GridUtils.print(c2);
    }
}
