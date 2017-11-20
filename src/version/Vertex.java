package test1;

import java.util.LinkedList;

public class Vertex {
  private String vertexNode ;
  private LinkedList<Edge> edgeList;
  
  public Vertex(String vertexNode, LinkedList<Edge> edgeList)
  {
    this.vertexNode = vertexNode;
    this.edgeList = edgeList;
  }

  public String getVertexNode() {
    return vertexNode;
  }

  public void setVertexNode(String vertexNode) {
    this.vertexNode = vertexNode;
  }

  public LinkedList<Edge> getEdgeList() {
    return edgeList;
  }

  public void setEdgeList(LinkedList<Edge> edgeList) {
    this.edgeList = edgeList;
  }
}
