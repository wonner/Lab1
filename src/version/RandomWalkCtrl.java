package test1;

public class RandomWalkCtrl {
  private Graph g = LoadCtrl.getGraph();
  
  RandomWalkCtrl(){}
  public void startRandomWalk() {
    g.startRandomWalk();
  }
  
  public int randomWalk(int i) {
    return g.randomWalk(i);
  }
  
  public String getRandPath() {
    return g.getRandPath();
  }
}
