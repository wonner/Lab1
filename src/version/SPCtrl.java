package test1;

public class SPCtrl {
  private Graph g = LoadCtrl.getGraph();
  
  public String[] calcShortesePath(String word1, String word2) {
    return g.calcShortesePath(word1, word2);
  }
}
