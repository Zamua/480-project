public class BipartGraph
{
    Cluster[] a;
    Cluster[] b;
    int[][] weightMatrix;
   
    private int[][] mask;
   
    public BipartGraph(Cluster[] a, Cluster[] b)
    {
      this.a = a;
      this.b = b;
      this.weightMatrix = new int[a.length][b.length];
      calcWeightMatrix();
      mask = weightMatrix;
    }
   
    /**
     * This populates a 2d array with the distances between elements of
     * sets a and b.  Element [0][0] of weightMatrix is the distance between
     * [0] of set a and [0] of set b.
     */
    private void calcWeightMatrix()
    {
      for (int i = 0; i < a.length; i++) {
        for (int j = 0; j < b.length; j++) {
          weightMatrix[i][j] = a[i].minDistance(b[j]);
        }
      }
    }
   
    /**
     * This uses the Hungarian algorithm to solve the problem of assigning
     * each cluster in set a, a pair in set b, such that the total sum of
     * distances between clusters in minimized.  It also sets the 'pair' field
     * of each cluster, so that it points to that cluster's pair.
     */
    public int matching()
    {
      // Subtract row minima
      for (int i = 0; i < weightMatrix.length; i++) {
          int min = Integer.MAX_VALUE;
          for (int j = 0; j < weightMatrix[i].length; j++) {
              if (weightMatrix[i][j] < min) min = weightMatrix[i][j];
          }
          for (int j = 0; j < weightMatrix[i].length; j++) {
              weightMatrix[i][j] -= min;
          }
      }
      // Subtract column minima
      for (int i = 0; i < weightMatrix.length; i++) {
          int min = Integer.MAX_VALUE;
          for (int j = 0; j < weightMatrix[i].length; j++) {
              if (weightMatrix[j][i] < min) min = weightMatrix[j][i];
          }
          for (int j = 0; j < weightMatrix[i].length; j++) {
              weightMatrix[j][i] -= min;
          }
      }
      // Cover all zeros with a minimum number of lines
      while (coverZeros() != weightMatrix.length) {
          // Find smallest uncovered edge
          int minUncovered = Integer.MAX_VALUE;
          for (int i = 0; i < mask.length; i++) {
              for (int j = 0; j < mask.length; j++) {
                  if (mask[i][j] > -1 && mask[i][j] < minUncovered)
                    minUncovered = mask[i][j];
              }
          }
          // Subtract smallest from all uncovered edges
          for (int i = 0; i < mask.length; i++) {
              for (int j = 0; j < mask.length; j++) {
                  if (mask[i][j] > -1) mask[i][j] -= minUncovered;
              }
          }
          // Add minUncovered to all doubly covered elements
      }
     
      return -1;
    }
   
    /**
     * This is a helper method for matching.  It calculates the minimum
     * number of lines needed to draw over the weight matrix such that
     * all zeros are covered.
     *
     * @return the number of lines 
     */
    public int coverZeros()
    {
        int numberLines = 0;
       
        while (hasZeros(mask)) {
            int maxZeroRow = -1;
            int maxZeroCol = -1;
            int maxZero    = -1;
            int zerosCount = 0;
            
            // Search for ROW with most zeros
            for (int i = 0; i < mask.length; i++) {
                for (int j = 0; j < mask.length; j++) {
                    if (mask[i][j] == 0) zerosCount++;
                }
                if (zerosCount > maxZero) {
                    maxZero = zerosCount;
                    maxZeroRow = i;
                }
                zerosCount = 0;
            }
            //Search for COLUMN with most zeros
            for (int i = 0; i < mask.length; i++) {
                for (int j = 0; j < mask.length; j++) {
                    if (mask[j][i] == 0) zerosCount++;
                }
                if (zerosCount > maxZero) {
                    maxZero = zerosCount;
                    maxZeroCol = i;
                }
                zerosCount = 0;
            }
            // _Cover_ the row/col with the most zeros by marking it
            if (maxZeroCol > -1) { // maxZeroCol will only be set if it has more zeros than any row
                for (int i = 0; i < mask.length; i++) {
                    if (mask[i][maxZeroCol] == -1) {
                        mask[i][maxZeroCol] = -2;
                    }
                    mask[i][maxZeroCol] = -1;
                }
            } else {
                for (int i = 0; i < mask.length; i++) {
                    if (mask[maxZeroRow][i] == -1) {
                        mask[maxZeroRow][i] = -2;
                    }
                    mask[maxZeroRow][i] = -1;
                }
            }
            numberLines++;
        }
        
        return numberLines; // Return the number of lines
    }
   
    private boolean hasZeros(int[][] a)
    {
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a.length; j++) {
                if(a[i][j] == 0) return true;
            }
        }
        return false;
    }
}