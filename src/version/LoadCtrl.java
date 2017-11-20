package test1;

import java.io.IOException;

public class LoadCtrl {
  private static Graph g;
  
  public static void createGraph(String filename) throws IOException {
    g = new Graph();
    g.createGraph(filename);
  }
  public static Graph getGraph() {
    return g;
  }
}
