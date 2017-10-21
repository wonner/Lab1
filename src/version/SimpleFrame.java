package version;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileSystemView;


/**
 * @author dell.
 *
 */
@SuppressWarnings("serial")
public class SimpleFrame extends JFrame {
  /** 
  *  @author George Bush. */
  public static final Logger log = Logger.getLogger(SimpleFrame.class.getName());
  /**
   *  @author George Bush.
   */
  public JTextField textField;
  /**
  *  @author George Bush.
  */
  public JTextField textField1; 
  /**
   *  @author George Bush.
   */
  public JTextField textField2;
  /**
   *  @author George Bush.
   */
  public int cnt;
  /**
   *  @author George Bush.
   */
  public int piccnt;
  /**
   *  @author George Bush.
   */
  public int randPath;
  /**
   *  @author George Bush.
   */
  //private static final int width = 400;
  /**
   *  @author George Bush.
   */
  //private static final int height = 600;
  // String[] path;
  /**
   *  @author George Bush.
   */
  private String fname;
  /** 
  *  @author George Bush. */
  
  public SimpleFrame() {
    // 获取分辨率
    super();
    final Toolkit kit = Toolkit.getDefaultToolkit();
    final Dimension screenSize = kit.getScreenSize();
    final int screenHeight = screenSize.height;
    final int screenWidth = screenSize.width;

    // git test3
    // remote test
    // 设置框架长宽及位置
    // add(new ImageComponent());
    setSize(screenWidth / 2, screenHeight / 2);
    setLocationByPlatform(true);
    // setLayout(new FlowLayout());

    // 添加按钮及文本框我
    final JPanel northpanel = new JPanel();
    setLayout(new BorderLayout(10, 10));
    northpanel.add(new JLabel("FileName:", SwingConstants.RIGHT));
    textField = new JTextField(20);
    northpanel.add(textField);
    final JButton scanButton = new JButton("浏览");
    final JButton certainButton = new JButton("确定");
    northpanel.add(scanButton);
    northpanel.add(certainButton);

    add(northpanel, BorderLayout.NORTH);
    //String fileName=textField.getText().trim();
    final JPanel southPanel = new JPanel();

    add(southPanel, BorderLayout.SOUTH);

    final JLabel centerPanel = new JLabel();
    final JScrollPane scollPane1 = new JScrollPane(centerPanel);
    // centerPanel.setBounds(0,0, screenWidth/2, screenHeight);
    scollPane1.setPreferredSize(new Dimension(screenWidth / 3 * 2, screenHeight));
    add(scollPane1, BorderLayout.WEST);

    final JTextArea textArea = new JTextArea(8, 40);
    final JScrollPane scollPane = new JScrollPane(textArea);
    add(scollPane, BorderLayout.EAST);

    // add(scollPane,BorderLayout.CENTER);
    // JPanel eastPanel = new JPanel();
    // add(eastPanel,BorderLayout.EAST);

    // 设置监听
    final ScanAction scanAction = new ScanAction();
    scanButton.addActionListener(scanAction);

    certainButton.addActionListener(new ActionListener() {
      /** 
      *  @author George Bush. */
      public void actionPerformed(final ActionEvent event) {
        southPanel.removeAll();
        final Graph graph = new Graph();
        try {
          graph.createGraph(textField.getText());
        } catch (IOException e) {
          // TODO 自动生成的 catch 块
          log.fine(e.getMessage());
        }
        final JButton showButton = new JButton("展示有向图");
        final JButton spButton = new JButton("最短路径查询");
        final JButton rdButton = new JButton("随机游走");
        final JButton qbButton = new JButton("查询桥接词");
        final JButton gtButton = new JButton("生成新文本");
        southPanel.add(showButton);
        southPanel.add(spButton);
        southPanel.add(rdButton);
        southPanel.add(qbButton);
        southPanel.add(gtButton);
        validate();

        showButton.addActionListener(new ActionListener() {
          /** 
          *  @author George Bush. */
          public void actionPerformed(final ActionEvent event) {
            centerPanel.setIcon(new ImageIcon("D://temp/" + fname + "/dotGif0.gif"));
            validate();
          }
        });

        qbButton.addActionListener(new ActionListener() {
          /** 
          *  @author George Bush. */
          public void actionPerformed(final ActionEvent event) {
            northpanel.removeAll();
            northpanel.add(new JLabel("word1:", SwingConstants.CENTER));
            textField1 = new JTextField(20);
            northpanel.add(textField1);

            northpanel.add(new JLabel("word2:", SwingConstants.CENTER));
            textField2 = new JTextField(20);
            northpanel.add(textField2);

            final JButton button = new JButton("确定");
            northpanel.add(button);

            validate();

            button.addActionListener(new ActionListener() {
              /** 
              *  @author George Bush. */
              public void actionPerformed(final ActionEvent event) {
                String word1;
                String word2;
                word1 = textField1.getText();
                word2 = textField2.getText();
                textArea.append(graph.queryBridgeWords(word1, word2) + "\n");
              }
            });
          }
        });

        gtButton.addActionListener(new ActionListener() {
          /** 
          *  @author George Bush. */
          public void actionPerformed(final ActionEvent event) {
            northpanel.removeAll();
            northpanel.add(new JLabel("Text:", SwingConstants.CENTER));
            textField1 = new JTextField(20);
            northpanel.add(textField1);

            final JButton button = new JButton("确定");
            northpanel.add(button);

            validate();

            button.addActionListener(new ActionListener() {
              /** 
              *  @author George Bush. */
              public void actionPerformed(final ActionEvent event) {
                String word5;
                word5 = textField1.getText();
                textArea.append(graph.generateNewText(word5) + "\n");
              }
            });
          }
        });

        spButton.addActionListener(new ActionListener() {
          /** 
          *  @author George Bush. */
          private String[] path;
          /** 
          *  @author George Bush. */
          public void actionPerformed(final ActionEvent event) {

            northpanel.removeAll();
            northpanel.add(new JLabel("word1:", SwingConstants.CENTER));
            textField1 = new JTextField(20);
            northpanel.add(textField1);

            northpanel.add(new JLabel("word2:", SwingConstants.CENTER));
            textField2 = new JTextField(20);
            northpanel.add(textField2);

            final JButton button = new JButton("确定");
            northpanel.add(button);

            final JButton nextButton = new JButton("下一条");
            northpanel.add(nextButton);

            validate();
            button.addActionListener(new ActionListener() {
              /** 
              *  @author George Bush. */
              public void actionPerformed(final ActionEvent event) {
                String word3;
                String word4;
                word3 = textField1.getText();
                word4 = textField2.getText();

                path = graph.calcShortesePath(word3, word4);
                final String str1 = path[0].split(" ")[0];
                final boolean aaaa = path.length == 1 && "No".equals(str1);
                if (aaaa) {
                  textArea.append("0条路径\n");
                } else {
                  textArea.append(path.length + "条路径\n");
                }
                cnt = 0;
                piccnt = 0;

              }
            });
            nextButton.addActionListener(new ActionListener() {
              /** 
              *  @author George Bush. */
              public void actionPerformed(final ActionEvent event) {
                if (cnt < path.length) {
                  final String str2 = path[cnt];
                  final String str3 = str2.split(" ")[0];
                  if ("No".equals(str3)) {
                    textArea.append("第" + (cnt + 1) + "条:" + path[cnt] + "\n");
                  } else {
                    textArea.append("第" + (cnt + 1) + "条:" + path[cnt] + "\n");
                    try {
                      centerPanel.setIcon(new ImageIcon(
                          ImageIO.read(new File("D://temp/" + fname 
                          + "/sPath/dotGif" + (piccnt + 1) + ".gif"))));
                    } catch (IOException e) {
                      // TODO Auto-generated catch block
                      log.fine(e.getMessage());
                    }
                    validate();
                    piccnt++;
                  }
                  cnt++;
                }

              }
            });

          }
        });
        rdButton.addActionListener(new ActionListener() {
          /** 
          *  @author George Bush. */
          int loc;
          /** 
          *  @author George Bush. */
          int loc2;
          /** 
          *  @author George Bush. */
          public void actionPerformed(final ActionEvent event) {

            final JButton startButton = new JButton("开始");
            final JButton continueButton = new JButton("继续");
            final JButton stopButton = new JButton("结束");

            southPanel.add(startButton);
            southPanel.add(continueButton);
            southPanel.add(stopButton);

            validate();

            startButton.addActionListener(new ActionListener() {
              /** 
              *  @author George Bush. */
              public void actionPerformed(final ActionEvent event) {
                graph.startRandomWalk();
                loc = graph.randomWalk(-1);
                randPath = 1;

              }
            });

            continueButton.addActionListener(new ActionListener() {
              /** 
              *  @author George Bush. */
              public void actionPerformed(final ActionEvent event) {
                final Random rand = new Random();
                loc2 = graph.randomWalk(rand.nextInt(loc));
                if (loc2 == -1) {
                  southPanel.remove(continueButton);
                  textArea.append(graph.getRandPath() + "\n");
                  textArea.append("finish!");
                  return;
                }
                //log.fine("D://temp/" + fname + "/rPath\\dotGif");
                try {
                  Thread.sleep(1000);
                } catch (InterruptedException e) {
                  // TODO Auto-generated catch block
                  log.fine(e.getMessage());
                }
                try {
                  centerPanel.setIcon(
                      new ImageIcon(ImageIO.read(
                      new File("D://temp/" + fname + "/rPath/dotGif" + randPath + ".gif"))));
                } catch (IOException e) {
                  // TODO Auto-generated catch block
                  log.fine(e.getMessage());
                }
                // centerPanel.setIcon(new
                // ImageIcon("D://temp/"+fname+"/rPath/dotGif"+randPath+".gif"));
                //log.(randPath);
                validate();
                textArea.append(graph.getRandPath() + "\n");
                //System.out.println(randPath);
                randPath++;
              }
            });

            stopButton.addActionListener(new ActionListener() {
              /** 
              *  @author George Bush. */
              public void actionPerformed(final ActionEvent event) {
                southPanel.remove(startButton);
                southPanel.remove(continueButton);
                southPanel.remove(stopButton);
                validate();
              }
            });
          }
        });
        graph.show();
        graph.showDirectedGraph();
      }
    });

  }
  /** 
  *  @author George Bush. */
  
  private class ScanAction implements ActionListener {
    /** 
    *  @author George Bush. */
    private final JFileChooser chooser = new JFileChooser();
    /** 
    *  @author George Bush. */
    
    public ScanAction() {
      log.fine("构造函数");
    }
    /** 
    *  @author George Bush. */
    
    public void actionPerformed(final ActionEvent event) {
      final FileSystemView fsv = FileSystemView.getFileSystemView();
      chooser.setCurrentDirectory(fsv.getHomeDirectory());
      final int result = chooser.showOpenDialog(SimpleFrame.this);
      if (result == JFileChooser.APPROVE_OPTION) {
        final File fil = chooser.getSelectedFile();
        final String name = fil.getPath();
        final String namenew = name.replaceAll("\\\\", "/");
        textField.setText(namenew);
        fname = namenew.substring(namenew.lastIndexOf('/') + 1, namenew.lastIndexOf('.'));
      }
    }
  }
}

