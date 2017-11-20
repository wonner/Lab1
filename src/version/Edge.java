package test1;

public class Edge {
  private int edgeNode;
  private int weight;
  
  public Edge(int edgeNode, int weight)
  {
    this.edgeNode = edgeNode;
    this.weight = weight;
  }

  public int getEdgeNode() {
    return edgeNode;
  }

  public void setEdgeNode(int edgeNode) {
    this.edgeNode = edgeNode;
  }

  public int getWeight() {
    return weight;
  }

  public void setWeight(int weight) {
    this.weight = weight;
  }
  
  
}
