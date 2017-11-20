package test1;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;
import java.util.Stack;

public class Graph {
  private int verNum;
  private ArrayList<Vertex> digraph;
  private ArrayList<Integer> randomPath = new ArrayList<>();
  private int lastLoc, cnt;

  private String fName;

  public int imgNum;

  private int findLoc(String str) {
    for (int i = 0; i < digraph.size(); i++) {
      if (digraph.get(i).getVertexNode().equals(str))
        return i;
    }
    return -1;
  }

  public Graph() {
    verNum = 0;
    digraph = new ArrayList<>();
  }

  public void addVertex(String ver) {
    for (Vertex v : digraph) {
      if (v.getVertexNode().equals(ver))
        return;
    }
    digraph.add(new Vertex(ver, new LinkedList<Edge>()));
    verNum++;
  }

  public void addEdge(String ver1, String ver2) {
    LinkedList<Edge> eList;
    int v1Loc = findLoc(ver1);
    int v2Loc = findLoc(ver2);

    eList = digraph.get(v1Loc).getEdgeList();
    for (Edge e : eList) {
      if (e.getEdgeNode() == v2Loc) {
        e.setWeight(e.getWeight() + 1);
        return;
      }
    }
    eList.add(new Edge(v2Loc, 1));
    return;
  }

  public void createGraph(String fileName) throws IOException {
    BufferedReader in = new BufferedReader(new FileReader(fileName));
    String s;
    StringBuilder word = new StringBuilder();
    while ((s = in.readLine()) != null) {
      s = s.replaceAll("\\pP", " ");
      s = s.replaceAll("[\\pS\\pN\\pC\\pM]", "");
      word.append(s);
    }
    in.close();
    String words = word.toString().toLowerCase();
    String[] word_arr = words.split("\\s+");

    addVertex(word_arr[0]);
    for (int i = 1; i < word_arr.length; i++) {
      addVertex(word_arr[i]);
      addEdge(word_arr[i - 1], word_arr[i]);

    }

    fName = fileName.substring(fileName.lastIndexOf("/") + 1, fileName.lastIndexOf("."));
  }

  public String queryBridgeWords(String word1, String word2) {
    int v1Loc = findLoc(word1);
    int v2Loc = findLoc(word2);

    if (v1Loc == -1 && v2Loc == -1)
      return "No ��" + word1 + "�� and ��" + word2 + "�� in the graph!";
    else if (v1Loc == -1)
      return "No ��" + word1 + "�� in the graph!";
    else if (v2Loc == -1)
      return "No ��" + word2 + "�� in the graph!";
    else {
      String[] bridgeWords = findBridge(v1Loc, v2Loc);
      if (bridgeWords.length == 0)
        return "No bridge words from ��" + word1 + "�� to ��" + word2 + "��!";
      else if (bridgeWords.length == 1)
        return "The bridge words from ��" + word1 + "�� to ��" + word2 + "�� is:" + bridgeWords[0];
      else {
        StringBuilder bw = new StringBuilder();
        int bwNum = bridgeWords.length - 1;
        bw.append("The bridge words from ��");
        bw.append(word1);
        bw.append("�� to ��");
        bw.append(word2);
        bw.append("�� is:");
        for (int i = 0; i < bwNum; i++) {
          bw.append(bridgeWords[i]);
          bw.append(",");
        }
        bw.append("and ");
        bw.append(bridgeWords[bwNum]);
        return bw.toString();
      }
    }

  }

  public String[] findBridge(int v1Loc, int v2Loc) // �����������ֵ����Ȼû���ŽӴʻ�v1��v2�����ڶ��᷵�ؿգ�queryBridgeWords�����޷��ֱ�.ֻ�������ʶ�����ʱ���ٵ��ô˺���
  {
    ArrayList<String> bridgeWords = new ArrayList<String>();
    LinkedList<Edge> eList = digraph.get(v1Loc).getEdgeList();
    for (Edge e : eList) {
      for (Edge e1 : digraph.get(e.getEdgeNode()).getEdgeList()) {
        if (e1.getEdgeNode() == v2Loc) {
          bridgeWords.add(digraph.get(e.getEdgeNode()).getVertexNode());
          break;
        }
      }
    }
    return (String[]) bridgeWords.toArray(new String[bridgeWords.size()]);
  }

  public String generateNewText(String inputText) {
    /* String[] text = inputText.split("\\s+"); */
    String[] text = inputText.split(" ");
    StringBuilder newText = new StringBuilder();

    for (int i = 0; i < text.length - 1; i++) {
      newText.append(text[i] + " ");
      int v1Loc = findLoc(text[i]);
      int v2Loc = findLoc(text[i + 1]);

      if (v1Loc != -1 && v2Loc != -1) {
        String[] bw = findBridge(v1Loc, v2Loc);
        if (bw != null) {
          if (bw.length == 1)
            newText.append(bw[0] + " ");
          else if (bw.length > 1) {
            Random rand = new Random();
            int index = rand.nextInt(bw.length);
            newText.append(bw[index] + " ");
          }
        }
      }
    }
    newText.append(text[text.length - 1]);
    return newText.toString();
  }

  public String[] calcShortesePath(String word1, String word2) {
    int source = findLoc(word1), end = -1;
    int count = 0;
    int[] D = new int[verNum]; // ���·��
    boolean[] S = new boolean[verNum]; // ����ɶ����ǩ
    ArrayList<ArrayList<Integer>> P = new ArrayList<>(); // ·��������������
    ArrayList<ArrayList<Integer>> path = null; // ·��
    LinkedList<Edge> edge;
    String[] ret = null;

    // ��ʼ��
    for (int i = 0; i < verNum; i++)
      D[i] = Integer.MAX_VALUE;
    for (int i = 0; i < verNum; i++)
      S[i] = false;
    for (int i = 0; i < verNum; i++) {
      ArrayList<Integer> temp = new ArrayList<>();
      temp.add(source);
      P.add(temp);
    }
    S[source] = true;
    edge = digraph.get(source).getEdgeList();
    for (Edge e : edge) {
      D[e.getEdgeNode()] = e.getWeight();
    }
    // �����·
    for (int i = 0; i < verNum - 1; i++) {
      // �ҵ�ǰ��Сֵ
      int temp = Integer.MAX_VALUE, w = -1;
      for (int j = 0; j < verNum; j++) {
        if (!S[j] && D[j] <= temp) {
          temp = D[j];
          w = j;
        }
      }
      // �������·��
      if (w == -1)
        break; // δ������ж��� ����Щ���㲻�ɴ
      S[w] = true;
      edge = digraph.get(w).getEdgeList();
      for (Edge e : edge) {
        int v = e.getEdgeNode();
        if (S[v] == false) {
          int sum = D[w] + e.getWeight();
          if (sum < D[v]) {
            D[v] = sum;
            P.get(v).clear();
            P.get(v).add(w);
          } else if (sum == D[v]) {
            P.get(v).add(w);
          }
        }
      }
    }
    // ���·�����
    if (!word2.equals("")) // ����
    {
      end = findLoc(word2);
      if (D[end] < Integer.MAX_VALUE) // �ɴ�
      {
        path = pathArr(source, end, P);
        ret = pathString(source, end, path);
        showShortestPath(path, count);
        count += path.size();

      } else {
        ret = new String[1];
        ret[0] = "No way from " + "\"" + digraph.get(source).getVertexNode() + "\"" + " to " + "\""
            + digraph.get(end).getVertexNode() + "\"";
      }
    } else // �����
    {
      ArrayList<String> tmp = new ArrayList<>();
      String[] temp;
      for (int i = 0; i < verNum; i++) {
        if (D[i] < Integer.MAX_VALUE) // ·������һһ��
        {
          path = pathArr(source, i, P);
          temp = pathString(source, i, path);
          for (String t : temp) {
            tmp.add(t);
          }

          showShortestPath(path, count);

          count += path.size();

        } else
          tmp.add("No way from " + "\"" + digraph.get(source).getVertexNode() + "\"" + " to " + "\""
              + digraph.get(i).getVertexNode() + "\"");
      }
      ret = tmp.toArray(new String[tmp.size()]);
    }
    imgNum = count;
    return ret;
  }

  public String[] pathString(int source, int end, ArrayList<ArrayList<Integer>> path) // ������·��
  {
    ArrayList<String> ret = new ArrayList<>();
    for (ArrayList<Integer> p : path) {
      StringBuilder sb = new StringBuilder();
      sb.append("The path from " + "\"" + digraph.get(source).getVertexNode() + "\"" + " to " + "\""
          + digraph.get(end).getVertexNode() + "\"" + ":");
      for (int i = p.size() - 1; i > 0; i--) {
        sb.append(digraph.get(p.get(i)).getVertexNode() + "->");
      }
      sb.append(digraph.get(p.get(0)).getVertexNode());
      ret.add(sb.toString());
    }
    return (String[]) ret.toArray(new String[ret.size()]);
  }

  public ArrayList<ArrayList<Integer>> pathArr(int source, int end, ArrayList<ArrayList<Integer>> P) {
    Stack<int[]> stack = new Stack<>();
    ArrayList<ArrayList<Integer>> path = new ArrayList<>();
    int p = end;
    ArrayList<Integer> temp = new ArrayList<>();
    while (p != source || !stack.isEmpty()) {
      temp.add(p);
      if (p == source) {
        path.add(temp);
        if (!stack.isEmpty()) {
          int[] lost = stack.pop();
          p = lost[1];
          ArrayList<Integer> tmp = new ArrayList<>();
          for (int i = 0; i < lost[0]; i++) {
            tmp.add(temp.get(i));
          }
          temp = tmp;
        }
      } else {
        if (P.get(p).size() == 1)
          p = P.get(p).get(0);
        else {
          for (int i = 1; i < P.get(p).size(); i++) {
            int[] lost = new int[2];
            lost[0] = temp.size(); // �ַ����ֲ洦
            lost[1] = P.get(p).get(i); // ͼ�ֲ�·��
            stack.push(lost);
          }
          p = P.get(p).get(0);
        }
      }

    }
    temp.add(p);
    path.add(temp);
    return path;
  }

  private Boolean find(ArrayList<Integer> p, int v1, int v2) {
    for (int i = 1; i < p.size(); i++) {
      if (v1 == p.get(i) && v2 == p.get(i - 1))
        return true;
    }
    return false;
  }

  public void showShortestPath(ArrayList<ArrayList<Integer>> path, int count) {
    int addition = 0;
    for (int m = 0; m < path.size(); m++) {
      ArrayList<Integer> p = path.get(m);
      GraphViz g = new GraphViz("D://temp/" + fName + "/sPath", "D:\\download\\graphviz-2.38\\release\\bin\\dot.exe");
      g.start_graph();
      for (int k : p)
        System.out.print(k);
      System.out.println("");
      for (int i = 0; i < digraph.size(); i++) {
        Vertex v = digraph.get(i);
        for (Edge e : v.getEdgeList()) {
          if (find(p, i, e.getEdgeNode())) {
            g.addln(v.getVertexNode() + "->" + digraph.get(e.getEdgeNode()).getVertexNode() + " [ label = \""
                + e.getWeight() + " \",color=\"red\" ];");
          } else {
            g.addln(v.getVertexNode() + "->" + digraph.get(e.getEdgeNode()).getVertexNode() + " [ label = \""
                + e.getWeight() + " \" ];");
          }
        }
      }
      addition++;
      g.end_graph();
      try {
        g.run(count + addition);
      } catch (Exception e) {
        e.printStackTrace();

      }
    }
  }

  public void showDirectedGraph() {
    GraphViz gViz = new GraphViz("D://temp/" + fName, "D:\\download\\graphviz-2.38\\release\\bin\\dot.exe");
    System.out.println("D://temp/" + fName);
    gViz.start_graph();
    for (Vertex v : digraph) {
      for (Edge e : v.getEdgeList()) {
        gViz.addln(v.getVertexNode() + "->" + digraph.get(e.getEdgeNode()).getVertexNode() + " [ label = \""
            + e.getWeight() + " \" ];");
      }
    }
    gViz.end_graph();
    try {
      gViz.run(0);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void show() {
    for (Vertex v : digraph) {
      System.out.print("<" + v.getVertexNode() + "> : ");
      for (Edge e : v.getEdgeList()) {
        System.out.print("<" + digraph.get(e.getEdgeNode()).getVertexNode() + "," + e.getWeight() + ">  ");
      }
      System.out.println(" ");
    }
  }

  public void startRandomWalk() {
    randomPath.clear();
    lastLoc = -1;
    cnt = 0;
  }

  public String getRandPath() {
    StringBuilder rPath = new StringBuilder();
    rPath.append("Random path : ");
    for (int i = 0; i < randomPath.size() - 1; i++)
      rPath.append(digraph.get(randomPath.get(i)).getVertexNode() + "->");
    rPath.append(digraph.get(randomPath.get(randomPath.size() - 1)).getVertexNode() + ".");
    return rPath.toString();
  }

  public int randomWalk(int i) // ����-1�����ظ���ֹͣ���� ����-1�����ǵ�һ���㣬�����бȽϣ�
  {
    Boolean flag = false;
    if (i == -1)
      return verNum - 1;
    else {
      int thisLoc;
      if (lastLoc == -1)
        thisLoc = i;
      else
        thisLoc = digraph.get(lastLoc).getEdgeList().get(i).getEdgeNode();
      int nextVerNum = digraph.get(thisLoc).getEdgeList().size();
      if (find(randomPath, thisLoc, lastLoc) || nextVerNum == 0)
        flag = true;
      randomPath.add(thisLoc);
      for (int k : randomPath)
        System.out.print(k);
      showRandomWalk(++cnt);
      lastLoc = thisLoc;
      if (flag == true)
        return -1;
      else
        return nextVerNum;
    }
  }

  public void showRandomWalk(int cnt) {
    GraphViz g = new GraphViz("D://temp/" + fName + "/rPath", "D:\\download\\graphviz-2.38\\release\\bin\\dot.exe");
    g.start_graph();
    System.out.println("");
    System.out.println("*************");
    for (int k : randomPath)
      System.out.print(digraph.get(k).getVertexNode() + "  ");
    System.out.println("");
    for (int i = 0; i < digraph.size(); i++) {
      Vertex v = digraph.get(i);
      for (Edge e : v.getEdgeList()) {
        if (find(randomPath, e.getEdgeNode(), i)) {
          g.addln(v.getVertexNode() + "->" + digraph.get(e.getEdgeNode()).getVertexNode() + " [ label = \""
              + e.getWeight() + " \",color=\"red\" ];");
        } else {
          g.addln(v.getVertexNode() + "->" + digraph.get(e.getEdgeNode()).getVertexNode() + " [ label = \""
              + e.getWeight() + " \" ];");
        }
      }
    }
    g.end_graph();
    try {
      g.run(cnt);
    } catch (Exception e) {
      e.printStackTrace();

    }
  }
}
