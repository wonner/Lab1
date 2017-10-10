package version4;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Random;

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



public class SimpleFrame extends JFrame {
	
	JTextField textField,textField1,textField2;
	int cnt,piccnt,randPath;
	int width = 400, height = 600;
	//String[] path;
	//git branch
	
	String fName;
	
	public SimpleFrame()
	{
		//获取分辨率
		Toolkit kit= Toolkit.getDefaultToolkit();
		Dimension screenSize=kit.getScreenSize();
		int screenHeight=screenSize.height;
		int screenWidth = screenSize.width;
		
		//git test3
		// git branchtest
		//设置框架长宽及位置
		//add(new ImageComponent());
		setSize(screenWidth/2,screenHeight/2);
		setLocationByPlatform(true);
		//setLayout(new FlowLayout());
		
		//添加按钮及文本框我
		JPanel northpanel=new JPanel();
		setLayout(new BorderLayout(10,10));
		northpanel.add(new JLabel("FileName:",SwingConstants.RIGHT));
		textField = new JTextField(20);
		northpanel.add(textField);
		JButton scanButton=new JButton("浏览");
		JButton certainButton =new JButton("确定");
		northpanel.add(scanButton);
		northpanel.add(certainButton);
		
		add(northpanel,BorderLayout.NORTH);
		//String fileName=textField.getText().trim();
		JPanel southPanel = new JPanel();
		
		add(southPanel,BorderLayout.SOUTH);
		
		JLabel centerPanel = new JLabel();
		JScrollPane scollPane1=new JScrollPane(centerPanel);
		//centerPanel.setBounds(0,0, screenWidth/2, screenHeight);
		scollPane1.setPreferredSize(new Dimension(screenWidth/3*2, screenHeight));
		add(scollPane1,BorderLayout.WEST);
		
		
		
		JTextArea textArea=new JTextArea(8,40);
		JScrollPane scollPane=new JScrollPane(textArea);
		add(scollPane,BorderLayout.EAST);
		
		//add(scollPane,BorderLayout.CENTER);
		//JPanel eastPanel = new JPanel();
		//add(eastPanel,BorderLayout.EAST);
		
		//设置监听
		ScanAction scanAction=new ScanAction();
		scanButton.addActionListener(scanAction);
		
		certainButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
				southPanel.removeAll();
				Graph graph=new Graph();
				try {
					graph.createGraph(textField.getText());
				} catch (IOException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
				JButton showButton= new JButton("展示有向图");
				JButton spButton = new JButton("最短路径查询");
				JButton rdButton = new JButton("随机游走");
				JButton qbButton = new JButton("查询桥接词");
				JButton gtButton = new JButton("生成新文本");
				southPanel.add(showButton);
				southPanel.add(spButton);
				southPanel.add(rdButton);
				southPanel.add(qbButton);
				southPanel.add(gtButton);
				validate();
				
				showButton.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent event)
					{
						centerPanel.setIcon(new ImageIcon("D://temp/"+fName+"/dotGif0.gif"));
						validate();
					}
				});
				
				qbButton.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent event)
					{
						northpanel.removeAll();
						northpanel.add(new JLabel("word1:",SwingConstants.CENTER));
						textField1 = new JTextField(20);
						northpanel.add(textField1);
						
						northpanel.add(new JLabel("word2:",SwingConstants.CENTER));
						textField2 = new JTextField(20);
						northpanel.add(textField2);
						
						JButton cButton = new JButton("确定");
						northpanel.add(cButton);
						
						validate();
						
						cButton.addActionListener(new ActionListener(){
							public void actionPerformed(ActionEvent event)
							{
								String word1 = null, word2 = null; 
								word1 = textField1.getText();
								word2 = textField2.getText();
								textArea.append(graph.queryBridgeWords(word1, word2)+"\n");
							}
						});
					}
				});
				
				gtButton.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent event)
					{
						northpanel.removeAll();
						northpanel.add(new JLabel("Text:",SwingConstants.CENTER));
						textField1 = new JTextField(20);
						northpanel.add(textField1);
						
						JButton cButton = new JButton("确定");
						northpanel.add(cButton);
						
						validate();
						
						cButton.addActionListener(new ActionListener(){
							public void actionPerformed(ActionEvent event)
							{
								String word1 = null;
								word1 = textField1.getText();
								textArea.append(graph.generateNewText(word1)+"\n");
							}
						});
					}
				});
				
				spButton.addActionListener(new ActionListener()
				{
					private String[] path;
					public void actionPerformed(ActionEvent event)
					{
						
						northpanel.removeAll();
						northpanel.add(new JLabel("word1:",SwingConstants.CENTER));
						textField1 = new JTextField(20);
						northpanel.add(textField1);
						
						northpanel.add(new JLabel("word2:",SwingConstants.CENTER));
						textField2 = new JTextField(20);
						northpanel.add(textField2);
						
						JButton cButton = new JButton("确定");
						northpanel.add(cButton);

						JButton nextButton = new JButton("下一条");
						northpanel.add(nextButton);
						
						validate();
						cButton.addActionListener(new ActionListener()
								{
									
									public void actionPerformed(ActionEvent event)
									{
										String word1 = null, word2 = null; 
										word1 = textField1.getText();
										word2 = textField2.getText();
								      
										path=graph.calcShortesePath(word1, word2);
										
										if(path.length==1&&path[0].split(" ")[0].equals("No")) { textArea.append("0条路径\n"); }
										else { textArea.append(path.length+"条路径\n"); }
										cnt=0; piccnt=0;
										
										
									}
								});
						nextButton.addActionListener(new ActionListener(){
							
							public void actionPerformed(ActionEvent event)
							{
								if(cnt<path.length)
								{
									if(path[cnt].split(" ")[0].equals("No"))
									{
										textArea.append("第"+(cnt+1)+"条:"+path[cnt]+"\n");
									}
									else
									{
										textArea.append("第"+(cnt+1)+"条:"+path[cnt]+"\n");
										try {
											centerPanel.setIcon(new ImageIcon(ImageIO.read(new File("D://temp/"+fName+"/sPath/dotGif"+(piccnt+1)+".gif"))));
										} catch (IOException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
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
				rdButton.addActionListener(new ActionListener()
				{
					int loc;
					public void actionPerformed(ActionEvent event)
					{

						JButton startButton= new JButton("开始");
						JButton continueButton = new JButton("继续");
						JButton stopButton = new JButton("结束");
						
						southPanel.add(startButton);
						southPanel.add(continueButton);
						southPanel.add(stopButton);
						
						validate();	
						
						startButton.addActionListener(new ActionListener()
						{
							public void actionPerformed(ActionEvent event)
							{
								graph.startRandomWalk();
								loc = graph.randomWalk(-1);
								randPath = 1;
								
							}
						});
						
						continueButton.addActionListener(new ActionListener()
						{
							public void actionPerformed(ActionEvent event)
							{
								Random rand = new Random();
								loc = graph.randomWalk(rand.nextInt(loc));
								if (loc == -1) 
								{
									southPanel.remove(continueButton);
									textArea.append(graph.getRandPath()+"\n");
									textArea.append("finish!");
									return;
								}
								System.out.println("D://temp/"+fName+"/rPath\\dotGif");
								try {
									Thread.sleep(1000);
								} catch (InterruptedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}							
								try {
									centerPanel.setIcon(new ImageIcon(ImageIO.read(new File("D://temp/"+fName+"/rPath/dotGif"+randPath+".gif"))));
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								//centerPanel.setIcon(new ImageIcon("D://temp/"+fName+"/rPath/dotGif"+randPath+".gif"));
								System.out.println(randPath);
								validate();
								textArea.append(graph.getRandPath()+"\n");
								System.out.println(randPath);
								randPath++;
							}
						});
						
						stopButton.addActionListener(new ActionListener()
						{
							public void actionPerformed(ActionEvent event)
							{
								southPanel.remove(startButton);
								southPanel.remove(continueButton);
								southPanel.remove(stopButton);
								validate();
							}
						});
					}
					
							/*public void actionPerformed(ActionEvent event) 
							{
								String str;
								str=graph.randomWalk();
								FileWriter fileWritter=null;
								try {
									fileWritter = new FileWriter(new File("D://temp/generatetext.txt"),false);
								} catch (IOException e) {
									// TODO 自动生成的 catch 块
									e.printStackTrace();
								}
					             BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
					             try {
									bufferWritter.write(str);
								} catch (IOException e) {
									// TODO 自动生成的 catch 块
									e.printStackTrace();
								}
					             try {
									bufferWritter.close();
								} catch (IOException e) {
									// TODO 自动生成的 catch 块
									e.printStackTrace();
								}
								
							}*/
						});
				graph.show();
				graph.showDirectedGraph();
			}
		});

	}
	
	private class ScanAction implements ActionListener
	{
		private JFileChooser chooser=new JFileChooser();
		public void actionPerformed(ActionEvent event)
		{
			FileSystemView fsv = FileSystemView.getFileSystemView(); 
			chooser.setCurrentDirectory(fsv.getHomeDirectory());
			int result=chooser.showOpenDialog(SimpleFrame.this);
			if(result==JFileChooser.APPROVE_OPTION)
			{
				String name=chooser.getSelectedFile().getPath();
				name=name.replaceAll("\\\\", "/");
				textField.setText(name);
				fName =  name.substring(name.lastIndexOf("/")+1, name.lastIndexOf("."));;
			}
		}
	}
}


/*
class ImageComponent extends JPanel
{
	public static final int DEFAULT_WIDTH = 300;
	public static final int DEFAULT_HEIGHT = 200;
	
	private Image image;
	
	public ImageComponent(int i)
	{
		image = new ImageIcon("D://temp/dotGif"+i+".gif").getImage();
	}
	
	public void paintComponent(Graphics g)  //绘制组件
	{
		if(image==null) return;
		g.drawImage(image, 0, 0, null);
	}
	
	public Dimension getPerferredSize() { return new Dimension(DEFAULT_WIDTH,DEFAULT_HEIGHT); } //组件大小？
}*/