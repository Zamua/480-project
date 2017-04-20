public class Cluster
{
  int[][] cluster;
  int h;
  int w;
  
  public void Cluster(int[][] a, int height, int width) {
    this.cluster = a;
    this.h       = height;
    this.w       = width;
  }
  
  public void translate() {
    int[][] newCluster = new int[w][h];
    
    for (int i = 0; i < w; i++) {
      for (int j = 0; j < h; j++) {
        newCluster[i][j] = this.cluster[h-1-j][i];
      }
    }
    this.cluster = newCluster;
  }
  
  // Calculates the minimum distance between translations of clusters
  public int minDistance(Cluster other) {
    int minDis = h*w-1;
  
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
  
  // Calculate the distance score to another cluster
  public int distance(Cluster other) {
    int misses = 0;
    int newH;
    int newW;
    if (this.h > other.h) newH = this.h; else newH = other.h;
    if (this.w > other.w) newW = this.w; else newW = other.w;
    
    int[][] overlay = new int[h][w]; // Add the matrices
    for (int i = 0; i < newH; i++) {
      for (int j = 0; j < newW; j++) {
        overlay[i][j] = this.cluster[i][j] + other.cluster[i][j];
      }
    }
    for (int i = 0; i < newH; i++) {
      for (int j = 0; j < newW; j++) {
        if (overlay[i][j] == 2) misses++;
      }
    }
    
    return misses;
  }
}