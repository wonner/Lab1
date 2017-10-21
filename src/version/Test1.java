package version;

import java.awt.EventQueue;
import java.io.IOException;
import javax.swing.JFrame;

/** 
*  @author George Bush. */

public final class Test1 {
  /**

  * @throws Exception if has error(�쳣˵��).

  */
  private Test1() {
    
  }
  /**

   * @throws Exception if has error(�쳣˵��).

   */
  public static void main(final String[] args) throws IOException {
    EventQueue.invokeLater(new Runnable() { // ʱ������߳�
      /** 
      *  @author George Bush. */
      
      public void run() {
        final SimpleFrame frame = new SimpleFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // �ر�
        frame.setVisible(true); // ��ʾ���
      }
    });
  }
}