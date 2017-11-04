package version;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;
import java.util.Stack;
/**
 * @author liudandan yangwan.
 */

public class Graph {
  /**
   * @author liudandan yangwan.
   */
  public int verNum;
  /**
   * @author liudandan yangwan.
   */
  public ArrayList<Vertex> digraph;
  /**
   * @author liudandan yangwan.
   */
  public transient ArrayList<Integer> randomPath = new ArrayList<>();
  /**
   * @author liudandan yangwan.
   */

  public int lastLoc;
  /**
   * @author liudandan yangwan.
   */
  public int cnt;
  /**
   * @author liudandan yangwan.
   */
  public String fname;
  /**
   * @author liudandan yangwan.
   */

  public class Vertex {
    /**
     * @author liudandan yangwan.
     */
    public transient String vertexNode;
    /**
     * @author liudandan yangwan.
     */
    public transient LinkedList<Edgename> edgelist;

    /**
     * @author liudandan yangwan.
     */
    
    public Vertex(final String vertexNode, final LinkedList<Edgename> edgelist) {
      this.vertexNode = vertexNode;
      this.edgelist = edgelist;
    }
  }
  /**
   * @author liudandan yangwan.
   */
  
  public static int imgNum;
  /**
   * @author liudandan yangwan.
   */
  
  public class Edgename {
    /**
     * @author liudandan yangwan.
     */
    public transient int edgeNode;
    /**
     * @author liudandan yangwan.
     */
    public transient int weight;
    /**
     * @author liudandan yangwan.
     */
    
    public Edgename(final int edgeNode, final int weight) {
      this.edgeNode = edgeNode;
      this.weight = weight;
    }
  }
  /**
   * @author liudandan yangwan.
   */
  
  public int findLoc(final String str) {
    for (int i = 0; i < digraph.size(); i++) {
      if (digraph.get(i).vertexNode.equals(str)) {
        return i;
      }
    }
    return -1;
  }
  /**
   * @author liudandan yangwan.
   */
  
  public Graph() {
    verNum = 0;
    digraph = new ArrayList<>();
  }
  /**
   * @author liudandan yangwan.
   */
  
  public void show() {
    for (Vertex v : digraph) {
      System.out.print("<" + v.vertexNode + "> : ");
      for (Edgename e : v.edgelist) {
        System.out.print("<" + digraph.get(e.edgeNode).vertexNode + "," 
            + e.weight + ">  ");
      }
    }
  }
  /**
   * @author liudandan yangwan.
   */
  
  public void addVertex(final String ver) { 
    
    for (final Vertex v : digraph) {
      if (v.vertexNode.equals(ver)) {
        return;
      }
    }
    digraph.add(new Vertex(ver, new LinkedList<Edgename>()));
    verNum++;
  }
  /**
   * @author liudandan yangwan.
   */

  public void addEdge(final String ver1, final String ver2) {
    LinkedList<Edgename> elist;
    final int v1Loc = findLoc(ver1);
    final int v2Loc = findLoc(ver2);

    elist = digraph.get(v1Loc).edgelist;
    for (Edgename e : elist) {
      if (e.edgeNode == v2Loc) {
        e.weight++;
        return;
      }
    }
    elist.add(new Edgename(v2Loc, 1));
    return;
  }
  /**
   * @author liudandan yangwan.
   */

  public void createGraph(final String fileName) throws IOException {
    final BufferedReader inname = new BufferedReader(new FileReader(fileName));
    String sname;
    final StringBuilder word = new StringBuilder();
    while ((sname = inname.readLine()) != null) {
      sname = sname.replaceAll("\\pP", " ");
      sname = sname.replaceAll("[\\pS\\pN\\pC\\pM]", "");
      word.append(sname);
    }
    inname.close();
    final String words = word.toString().toLowerCase();
    final String[] wordarr = words.split("\\s+");
    // System.out.println(Arrays.toString(wordarr));

    addVertex(wordarr[0]);
    for (int i = 1; i < wordarr.length; i++) {
      addVertex(wordarr[i]);
      addEdge(wordarr[i - 1], wordarr[i]);

    }

    fname = fileName.substring(fileName.lastIndexOf('/') + 1, fileName.lastIndexOf("."));
  }
  /**
   * @author liudandan yangwan.
   */

  public String queryBridgeWords(final String word1, final String word2) {
    final int v1Loc = findLoc(word1);
    final int v2Loc = findLoc(word2);
    final StringBuilder bwname = new StringBuilder();
    if (v1Loc == -1 && v2Loc == -1) {
      return  "No “" + word1 + "” and “" + word2 + "” in the graph!";
    } else if (v1Loc == -1) {
      return "No “" + word1 + "” in the graph!";
    } else if (v2Loc == -1) {
      return "No “" + word2 + "” in the graph!";
    } else {
      final String[] bridgeWords = findBridge(v1Loc, v2Loc);
      if (bridgeWords.length == 0) {
        return "No bridge words from ”" + word1 + "” to ”" + word2 + "”!";
      } else if (bridgeWords.length == 1) {
        return "The bridge words from ”" + word1 + "” to ”" + word2 + "” is:" + bridgeWords[0];
      } else {
        
        bwname.append("The bridge words from ”");
        bwname.append(word1);
        bwname.append("” to ”");
        bwname.append(word2);
        bwname.append("” is:");
        final int bwnameNum = bridgeWords.length - 1;
        for (int i = 0; i < bwnameNum; i++) {
          bwname.append(bridgeWords[i]);
          bwname.append(" , ");
        }
        bwname.append("and ");
        bwname.append(bridgeWords[bwnameNum]);
        return bwname.toString();
      }
    }
  }
  /**
   * @author liudandan yangwan.
   */

  public String[] findBridge(final int v1Loc,final int v2Loc) {
    //传入的是索引值，不然没有桥接词或v1，v2不存在都会返回空，queryBridgeWords无法分辨.只有两个词都存在时，调用此函数
    final ArrayList<String> bridgeWords = new ArrayList<String>();
    final LinkedList<Edgename> elist = digraph.get(v1Loc).edgelist;
    for (final Edgename e : elist) {
      for (final Edgename e1 : digraph.get(e.edgeNode).edgelist) {
        if (e1.edgeNode == v2Loc) {
          bridgeWords.add(digraph.get(e.edgeNode).vertexNode);
          break;
        }
      }
    }
    return (String[]) bridgeWords.toArray(new String[bridgeWords.size()]);
  }
  /**
   * @author liudandan yangwan.
   */

  public String generateNewText(final String inputText) {
    final String[] text = inputText.split(" ");
    final StringBuilder newText = new StringBuilder();
    
    for (int i = 0; i < text.length - 1; i++) {
      newText.append(text[i] + " ");
      final int v1Loc = findLoc(text[i]);
      final int v2Loc = findLoc(text[i + 1]);

      if (v1Loc != -1 && v2Loc != -1) {
        final String[] bwname = findBridge(v1Loc, v2Loc);
        Random rand;
        if (bwname != null) {
          if (bwname.length == 1) {
            newText.append(bwname[0] + " ");
          } else if (bwname.length > 1) {
            rand = new Random();
            final int index = rand.nextInt(bwname.length);
            newText.append(bwname[index] + " ");
          }
        }
      }
    }
    newText.append(text[text.length - 1]);
    return newText.toString();
  }
  /**
   * @author liudandan yangwan.
   */

  public String[] calcShortesePath(final String word1, final String word2) {
    final int source = findLoc(word1);
    int end = -1;
    int count = 0;
    int[] dist = new int[verNum]; // 最短路长
    boolean[] sname = new boolean[verNum]; // 已完成顶点标签
    final ArrayList<ArrayList<Integer>> pname = new ArrayList<>(); // 路径经过顶点索引
    ArrayList<ArrayList<Integer>> path = null; // 路径
    String[] ret = null;

    // 初始化
    for (int i = 0; i < verNum; i++) {
      dist[i] = Integer.MAX_VALUE;
    }
    for (int i = 0; i < verNum; i++) {
      sname[i] = false;
    }
    for (int i = 0; i < verNum; i++) {
      final ArrayList<Integer> temp = new ArrayList<>();
      temp.add(source);
      pname.add(temp);
    }
    LinkedList<Edgename> edge;
    sname[source] = true;
    edge = digraph.get(source).edgelist;
    for (final Edgename e : edge) {
      dist[e.edgeNode] = e.weight;
    }
    // 求最短路
    for (int i = 0; i < verNum - 1; i++) {
      // 找当前最小值
      int temp = Integer.MAX_VALUE;
      int wname = -1;
      for (int j = 0; j < verNum; j++) {
        if (!sname[j] && dist[j] <= temp) {
          temp = dist[j];
          wname = j;
        }
      }
      // 更新最短路径
      if (wname == -1) {
        break; // 未添加所有顶点 因有些顶点不可达？
      }
      sname[wname] = true;
      edge = digraph.get(wname).edgelist;
      for (final Edgename e : edge) {
        final int vname = e.edgeNode;
        if (!sname[vname]) {
          final int sum = dist[wname] + e.weight;
          if (sum < dist[vname]) {
            dist[vname] = sum;
            pname.get(vname).clear();
            pname.get(vname).add(wname);
          } else if (sum == dist[vname]) {
            pname.get(vname).add(wname);
          }
        }
      }
    }
    // 最短路径输出
    if (!word2.equals("")) { // 两点 
      end = findLoc(word2);
      if (dist[end] < Integer.MAX_VALUE) { // 可达
        path = pathArr(source, end, pname);
        ret = pathString(source, end, path);
        showShortestPath(path, count);
        count += path.size();
      } else {
        ret = new String[1];
        ret[0] = "No way from " + "\"" + digraph.get(source).vertexNode + "\"" + " to " + "\""
            + digraph.get(end).vertexNode + "\"";
      }
    } else { // 任意点
      final ArrayList<String> tmp = new ArrayList<>();
      String[] temp;
      for (int i = 0; i < verNum; i++) {
        if (dist[i] < Integer.MAX_VALUE) { // 路径中任一一点 
          path = pathArr(source, i, pname);
          temp = pathString(source, i, path);
          for (final String t : temp) {
            tmp.add(t);
          }

          showShortestPath(path, count);

          count += path.size();

        } else {
          tmp.add("No way from " + "\"" + digraph.get(source).vertexNode + "\"" + " to " + "\""
              + digraph.get(i).vertexNode + "\"");
        }
      }
      ret = tmp.toArray(new String[tmp.size()]);
    }
    imgNum = count;
    return ret;
  }
  /**
   * @author liudandan yangwan.
   */

  public String[] pathString(final int source, final int end, 
      final ArrayList<ArrayList<Integer>> path) { // 输出最短路径
    final ArrayList<String> ret = new ArrayList<>();
    for (final ArrayList<Integer> p : path) {
      final StringBuilder sbname = new StringBuilder();
      sbname.append("The path from " + "\"" + digraph.get(source).vertexNode + "\"" + " to " + "\""
          + digraph.get(end).vertexNode + "\"" + ":");
      for (int i = p.size() - 1; i > 0; i--) {
        sbname.append(digraph.get(p.get(i)).vertexNode + "->");
      }
      sbname.append(digraph.get(p.get(0)).vertexNode);
      ret.add(sbname.toString());
    }
    return (String[]) ret.toArray(new String[ret.size()]);
  }
  /**
   * @author liudandan yangwan.
   */

  public ArrayList<ArrayList<Integer>> pathArr(final int source, 
      final int end, final ArrayList<ArrayList<Integer>> pbig) {
    final Stack<int[]> stack = new Stack<>();
    final ArrayList<ArrayList<Integer>> path = new ArrayList<>();
    int pname = end;
    ArrayList<Integer> temp = new ArrayList<>();
    ArrayList<Integer> tmp;
    while (pname != source || !stack.isEmpty()) {
      temp.add(pname);
      if (pname == source) {
        path.add(temp);
        if (!stack.isEmpty()) {
          final int[] lost = stack.pop();
          pname = lost[1];
          tmp = new ArrayList<>();
          for (int i = 0; i < lost[0]; i++) {
            tmp.add(temp.get(i));
          }
          temp = tmp;
        }
      } else {
        if (pbig.get(pname).size() == 1) {
          pname = pbig.get(pname).get(0);
        } else {
          for (int i = 1; i < pbig.get(pname).size(); i++) {
            int[] lost = new int[2];
            lost[0] = temp.size(); // 字符串分叉处
            lost[1] = pbig.get(pname).get(i); // 图分叉路口
            stack.push(lost);
          }
          pname = pbig.get(pname).get(0);
        }
      }

    }
    temp.add(pname);
    path.add(temp);
    return path;
  }
  /**
   * @author liudandan yangwan.
   */
  
  public Boolean find(final ArrayList<Integer> pname, final int vname1, final int vname2) {
    for (int i = 1; i < pname.size(); i++) {
      if (vname1 == pname.get(i) && vname2 == pname.get(i - 1)) {
        return true;
      }
    }
    return false;
  }
  /**
   * @author liudandan yangwan.
   */

  public void showShortestPath(final ArrayList<ArrayList<Integer>> path, final int count) {
    int addition = 0;
    for (int m = 0; m < path.size(); m++) {
      final ArrayList<Integer> pname = path.get(m);
      final GraphViz gname = new GraphViz(
          "D://temp/" + fname + "/sPath", "F://files/release//bin//dot.exe");
      gname.start_graph();/*
      for (int k : p) {
        System.out.print(k);
      }*/
      for (int i = 0; i < digraph.size(); i++) {
        final Vertex vname = digraph.get(i);
        for (final Edgename e : vname.edgelist) {
          if (find(pname, i, e.edgeNode)) {
            gname.addln(vname.vertexNode + "->" + digraph.get(e.edgeNode).vertexNode + " "
                + "[ label = \"" + e.weight + " \",color=\"red\" ];");
          } else {
            gname.addln(vname.vertexNode + "->" + digraph.get(e.edgeNode).vertexNode 
                + " [ label = \"" + e.weight + " \" ];");
          }
        }
      }
      addition++;
      gname.end_graph();
      try {
        gname.run(count + addition);
      } catch (Exception e) {
        e.printStackTrace();

      }
    }
  }
  /**
   * @author liudandan yangwan.
   */

  public void showDirectedGraph() {
    final GraphViz gviz = new GraphViz("D://temp/" + fname, "F://files/release//bin//dot.exe");
    gviz.start_graph();
    for (final Vertex v : digraph) {
      for (final Edgename e : v.edgelist) {
        gviz.addln(v.vertexNode + "->" + digraph.get(e.edgeNode).vertexNode + " [ label = \"" 
            + e.weight + " \" ];");
      }
    }
    gviz.end_graph();
    try {
      gviz.run(0);
    } catch (Exception e) {
      e.printStackTrace();    
    }
  }
  /**
   * @author liudandan yangwan.
   */
  /**
   * @author liudandan yangwan.
   */
  
  public void startRandomWalk() {
    randomPath.clear();
    lastLoc = -1;
    cnt = 0;
  }
  /**
   * @author liudandan yangwan.
   */

  public String getRandPath() {
    final StringBuilder rpath = new StringBuilder();
    rpath.append("Random path : ");
    for (int i = 0; i < randomPath.size() - 1; i++) {
      rpath.append(digraph.get(randomPath.get(i)).vertexNode + "->");
    }
    rpath.append(digraph.get(randomPath.get(randomPath.size() - 1)).vertexNode + ".");
    return rpath.toString();
  }
  /**
   * @author liudandan yangwan.
   */

  public int randomWalk(final int iname) { // 返回-1发现重复，停止，， 传入-1，则是第一个点，不进行比较；
    Boolean flag = false;
    int result ;
    if (iname == -1) {
      result = verNum - 1;
    } else {
      int thisLoc;
      if (lastLoc == -1) {
        thisLoc = iname;
      } else {
        thisLoc = digraph.get(lastLoc).edgelist.get(iname).edgeNode;
      }
      final int nextVerNum = digraph.get(thisLoc).edgelist.size();
      if (find(randomPath, thisLoc, lastLoc) || nextVerNum == 0) {
        flag = true;
      }
      randomPath.add(thisLoc);/*
      for (int k : randomPath) {
        System.out.print(k);
      }*/
      showRandomWalk(++cnt);
      lastLoc = thisLoc;
      if (flag == true) {
        result = -1;
      } else {
        result = nextVerNum;
      }
    }
    return result;
  }

  // git test1
  // remote test
  /**
   * @author liudandan yangwan.
   */
  
  public void showRandomWalk(final int cnt) {
    final GraphViz gname = new GraphViz(
        "D://temp/" + fname + "/rpath", "F://files/release//bin//dot.exe");
    gname.start_graph();/*
    for (int k : randomPath) {
      System.out.print(digraph.get(k).vertexNode + "  ");
    }*/
    for (int i = 0; i < digraph.size(); i++) {
      final Vertex vname = digraph.get(i);
      for (final Edgename e : vname.edgelist) {
        if (find(randomPath, e.edgeNode, i)) {
          gname.addln(vname.vertexNode + "->" + digraph.get(e.edgeNode).vertexNode 
              + " [ label = \"" + e.weight + " \",color=\"red\" ];");
        } else {
          gname.addln(vname.vertexNode + "->" + digraph.get(e.edgeNode).vertexNode 
              + " [ label = \"" + e.weight + " \" ];");
        }
      }
    }
    gname.end_graph();
    try {
      gname.run(cnt);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}