/**
 * This class represents a cluster in a figure.  It contains several
 * methods for operating on and comparing clusters.
 *
 * @author NIKOLAI MAYO-PITTS
 */

public class Cluster
{
    int[][] cluster; // Various operations can transform the position, as well as size of bounding box
    int[][] fullCluster; // This field preserves the original position and size of the cluster's bounding box
    int h;
    int w;
    Cluster pair;
    
    public Cluster(int[][] a) {
        this.cluster     = a;
        this.fullCluster = a;
        this.h           = a.length;
        this.w           = a[0].length;
    }
  
    /**
     * Calculates the distance score of two clusters.
     * Two congruent clusters have a distance score of 0.
     * The score itself represents the number of pixel mismatches.
     * 
     * @param other
     * @return The distance score
     */
    public int distance(Cluster other) {
    	int misses = 0;
    	int newH;
    	int newW;
    	if (this.h > other.h) newH = this.h; else newH = other.h;
    	if (this.w > other.w) newW = this.w; else newW = other.w;
   
    	int[][] overlay = new int[newH][newW];
    	for (int i = 0; i < newH; i++) {
    		for (int j = 0; j < newW; j++) {
    			overlay[i][j] = 0;  // Zero out grid
    		}
    	}
    	for (int i = 0; i < cluster.length; i++) {
    		for (int j = 0; j < cluster[0].length; j++) {
    			overlay[i][j] += this.cluster[i][j];
    		}
    	}
    	for (int i = 0; i < other.cluster.length; i++) {
    		for (int j = 0; j < other.cluster[0].length; j++) {
    			overlay[i][j] += other.cluster[i][j];
    		}
    	}
    	for (int i = 0; i < newH; i++) {
    		for (int j = 0; j < newW; j++) {
    			if (overlay[i][j] == 1) misses++;
    		}
    	}
    	return misses;
    }
    
    /**
     * Finds the minimum distance between two clusters
     * across all possible rotations of both clusters.
     * 
     * This does _not_ consider all possible translations,
     * but it is good enough - and it guarantees to be 0
     * when the two clusters are congruent.
     *
     * @return The minimum distance 
     */
    public int minDistance(Cluster other) {
    	int minDis = h*w-1;
        this.minimizeBoundingBox();
        other.minimizeBoundingBox();
        
    	for (int i = 0; i < 4; i++) {
    		for (int j = 0; j < 4; j++) {
    			other.translate();
    			int temp = distance(other);
    			if (temp < minDis) minDis = temp;
    		}
    		this.translate();
    	}
    
    	return minDis;
    }
    
    /**
     * Finds the number of rotations required to minimize
     * the distance between this and another cluster.
     *
     * This is used as a helper method for Copy/Paste.
     *
     * @return The number of rotations required
     */
    public int getRotationCount(Cluster other)
    {
        int minDis = h*w-1;
        this.minimizeBoundingBox();
        other.minimizeBoundingBox();
      
        int tempDist;
        int minDist = Integer.MAX_VALUE;
        int rotationNums = 0;
        int tempRotNums = 0;
      
        for (int i = 0; i < 4; i++) {
            tempDist = distance(other);
            if (tempDist < minDist) {
                minDist = tempDist;
                rotationNums = tempRotNums;
            } else {
                tempRotNums++;
                this.translate();
            }
        }
        
        return rotationNums;
    }
  
    /**
     * Rotates the cluster object by 90 degrees
     * clockwise.
     */
    public void translate() {
    	int[][] newCluster = new int[w][h];
    
    	for (int i = 0; i < w; i++) {
    		for (int j = 0; j < h; j++) {
    			newCluster[i][j] = this.cluster[h-1-j][i];
    		}
    	}
    	this.cluster = newCluster;
    	this.h = newCluster.length;
    	this.w = newCluster[0].length;
    }
    
    /**
     *  Removes "whitespace" from a cluster.
     *  0 0 1 1				
     *  0 1 1 1  becomes   0 1 1
     *  0 0 0 0    -->     1 1 1
     *  0 0 0 0 
     *  
     *  This needs to be done to properly calculate the distance 
     *  score of two clusters.  
     */
    public void minimizeBoundingBox() {
    	boolean minimize = true;
    	int[][] newCluster;
    	for (int ii = 0; ii < 4; ii++) {
    		while (minimize) {
    			for (int i = 0; i < this.cluster[0].length; i++) { //check if row is empty
    				if (this.cluster[0][i] == 1) minimize = false;
    			}
    			if (minimize) { // If row is empty
    				newCluster = new int[this.cluster.length-1][this.cluster[1].length];
    				for (int i = 1; i < cluster.length; i++) {
    					for (int j = 0; j < cluster[1].length; j++) {
    						newCluster[i-1][j] = this.cluster[i][j];
    					}
    				}
    				this.cluster = newCluster;
    				this.h = newCluster.length;
    				this.w = newCluster[0].length;
    			}
    		}
    		translate();
    		minimize = true;
    	}
    }
}
