import java.util.ArrayList;

/**
 * Represents an entire Figure.  Keeps track of the various
 * clusters that make up the figure.
 *
 * @author NIKOLAI MAYO-PITTS
 */
public class Figure
{
    int[][] figure;
    int rows;
    int cols;
    ArrayList<Cluster> clusters; 
    
    public Figure(int[][] a)
    {
        figure = a;
        rows = a.length;
        cols = a[0].length;
        Cluster firstCluster = new Cluster(a);
        clusters = new ArrayList<Cluster>();
        clusters.add(firstCluster);
    }
    
    public Figure(Cluster c)
    {
        figure = c.cluster;
        clusters = new ArrayList<Cluster>();
        clusters.add(c);
    }  
    
    /**
     * Redraws the figure based on the elements of the clusters array.
     * Clusters belonging to index 0 are labeled as 1s in the figure matrix.
     * Clusters belonging to index 1 are labeled as 2s in the figure matrix,
     * etc.
     */
    public void drawFigure()
    {
        figure = new int[rows][cols];
        for (int i = 0; i < clusters.size(); i++) {
            Cluster current = clusters.get(i);
            for (int ii = 0; ii < rows; ii++) {
                for (int jj = 0; jj < cols; jj++) {
                    if (current.fullCluster[ii][jj] == 1) {
                        figure[ii][jj] = i+1;
                    }
                }
            }
        }
    }
    
    /**
     * Finds the translation of c that yields the minimum
     * distance, then 
     */
    public void paste(Cluster c)
    {
        // Get the number of rotations needed to get minimum distance
        int neededTranslations = c.getRotationCount(c.pair);
        int minimumDist = c.minDistance(c.pair);
        
        for (int i = 0; i < neededTranslations; i++) {
            c.translate();
        }
    }
}