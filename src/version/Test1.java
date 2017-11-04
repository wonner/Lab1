package version;

import java.awt.EventQueue;
import java.io.IOException;
import javax.swing.JFrame;

/** 
*  @author George Bush. */

public final class Test1 {
  /**

  * @throws Exception if has error(异常说明).

  */
  private Test1() {
    
  }
  /**

   * @throws Exception if has error(异常说明).

   */
  public static void main(final String[] args) throws IOException {
    EventQueue.invokeLater(new Runnable() { // 时间分派线程
      /** 
      *  @author George Bush. */
      
      public void run() {
        final SimpleFrame frame = new SimpleFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 关闭
        frame.setVisible(true); // 显示框架
      }
    });
    Graph g = new Graph();
    g.createGraph("D:\\test.txt");
    System.out.println(g.generateNewText("Seek to explore\nnew and exciting synergies"));
    System.out.println("Seek to explore\nnew and exciting synergies");
  }
}