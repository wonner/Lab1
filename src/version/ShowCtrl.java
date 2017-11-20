package test1;

public class ShowCtrl {
private Graph g = LoadCtrl.getGraph();
  
  public void show() {
    g.show();
    g.showDirectedGraph();
  }
  
  
}
