package version;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import version.Graph;

public class WhiteBox {

  @Test
  public void testQueryBridgeWords1() throws IOException {
    Graph graph = new Graph();
    graph.createGraph("D:\\test.txt");
    String str = graph.queryBridgeWords("you", "me");
    String exp = "No ¡°you¡± and ¡°me¡± in the graph!";
    assertEquals(exp,str);
  }
  
  @Test
  public void testQueryBridgeWords2() throws IOException {
    Graph graph = new Graph();
    graph.createGraph("D:\\\\test.txt");
    String str = graph.queryBridgeWords("you", "seek");
    String exp = "No ¡°you¡± in the graph!";
    assertEquals(exp,str);
  }
  
  @Test
  public void testQueryBridgeWords3() throws IOException {
    Graph graph = new Graph();
    graph.createGraph("D:\\\\test.txt");
    String str = graph.queryBridgeWords("seek", "me");
    String exp = "No ¡°me¡± in the graph!";
    assertEquals(exp,str);
  }
  
  @Test
  public void testQueryBridgeWords4() throws IOException {
    Graph graph = new Graph();
    graph.createGraph("D:\\\\test.txt");
    String str = graph.queryBridgeWords("chance", "to");
    String exp = "No bridge words from ¡±chance¡± to ¡±to¡±!";
    assertEquals(exp,str);
  }
  
  @Test
  public void testQueryBridgeWords5() throws IOException {
    Graph graph = new Graph();
    graph.createGraph("D:\\\\test.txt");
    String str = graph.queryBridgeWords("different", "chance");
    String exp = "No bridge words from ¡±different¡± to ¡±chance¡±!";
    assertEquals(exp,str);
  }

  

  @Test
  public void testQueryBridgeWords6() throws IOException {
    Graph graph = new Graph();
    graph.createGraph("D:\\\\test.txt");
    String str = graph.queryBridgeWords("to", "seek");
    String exp = "No bridge words from ¡±to¡± to ¡±seek¡±!";
    assertEquals(exp,str);
  }
  
  @Test
  public void testQueryBridgeWords7() throws IOException {
    Graph graph = new Graph();
    graph.createGraph("D:\\\\test.txt");
    String str = graph.queryBridgeWords("new", "and");
    String exp = "The bridge words from ¡±new¡± to ¡±and¡± is:life";
    assertEquals(exp,str);
  }
  
  @Test
  public void testQueryBridgeWords8() throws IOException {
    Graph graph = new Graph();
    graph.createGraph("D:\\\\test.txt");
    String str = graph.queryBridgeWords("explore", "new");
    String exp = "The bridge words from ¡±explore¡± to ¡±new¡± is:strange , and a";
    assertEquals(exp,str);
  }

}
