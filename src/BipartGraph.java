public class BipartGraph
{
   Cluster[] a;
   Cluster[] b;
   int[][] weightMatrix;
   
   
   public BipartGraph(Cluster[] a, Cluster[] b)
   {
     this.a = a;
     this.b = b;
     this.weightMatrix = new int[a.length][b.length];
     calcWeightMatrix();
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
     
     return -1;
   }
}