package test1;

public class BridgeWordsCtrl {
  private Graph g = LoadCtrl.getGraph();
  public BridgeWordsCtrl() {}
  
  public String queryBridge(String word1, String word2) {
    return g.queryBridgeWords(word1, word2);
  }
  
  public String generateNewText(String text) {
    return g.generateNewText(text);
  }
}
