package version;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Logger;

/** 
*  @author George Bush. */

class GraphViz {
  
  /** 
  *  @author George Bush. */
  public static final Logger log = Logger.getLogger(SimpleFrame.class.getName());
  /** 
  *  @author George Bush. */
  public transient String runPath = "";
  /** 
  *  @author George Bush. */
  public transient String dotPath = "";
  /** 
  *  @author George Bush. */
  public static String runOrder = "";

  /** 
  *  @author George Bush. */
  public static String dotCodeFile = "dotcode";
  /** 
  *  @author George Bush. */
  public static final String resultGif = "dotGif";
  /** 
  *  @author George Bush. */
  
  public static StringBuilder graph = new StringBuilder();
  /** 
  *  @author George Bush. */
  public static final Runtime runtime = Runtime.getRuntime();

  /** 
  *  @author George Bush. */
  public void run(final int iiii) {
    final File file = new File(runPath);
    file.mkdirs();
    writeGraphToFile(graph.toString(), runPath, iiii);

    creatOrder(iiii);
    try {
      runtime.exec(runOrder);
    } catch (IOException e) {
      log.fine(e.getMessage());
    }
  }

  /** 
  *  @author George Bush. */
  
  public void creatOrder(final int iiii) {
    runOrder += dotPath + " ";
    runOrder += runPath;
    runOrder += "\\" + dotCodeFile + iiii + ".txt" + " ";
    runOrder += "-T gif ";
    runOrder += "-o ";
    runOrder += runPath;
    runOrder += "\\" + resultGif + iiii + ".gif";
    log.fine(runOrder);
  }

  /** 
  *  @author George Bush. */
  public void writeGraphToFile(final String dotcode, final String filename, final int int2) {
    try {
      final File file = new File(filename + "\\" + dotCodeFile + int2 + ".txt");
      if (!file.exists()) {
        file.createNewFile();
      }
      final FileOutputStream fos = new FileOutputStream(file);
      fos.write(dotcode.getBytes());
      fos.flush();
      fos.close();

    } catch (IOException ioe) {
      log.fine(ioe.getMessage());
    }
  }

  /** 
  *  @author George Bush. */
  public GraphViz(final String runPath, final String dotPath) {
    this.runPath = runPath;
    this.dotPath = dotPath;
  }

  /** 
  *  @author George Bush. */
  
  public void add(final String line) {
    graph.append('\t');
    graph.append(line);
  }

  /** 
  *  @author George Bush. */
  
  public void addln(final String line) {
    graph.append("\t" + line + "\n");
  }

  /** 
  *  @author George Bush. */
  
  public void addln() {
    graph.append('\n');
  }

  /** 
  *  @author George Bush. */
  
  public void start_graph() {
    graph.append("digraph G {\n");
  }

  /** 
  *  @author George Bush. */
  
  public void end_graph() {
    graph.append('}');
  }
}
