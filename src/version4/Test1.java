package version4;

import java.awt.EventQueue;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

import javax.swing.JFrame;

public class Test1 {

	public static void main(String[] args)throws IOException  {
		EventQueue.invokeLater(new Runnable(){  //时间分派线程
			public void run()
			{
				SimpleFrame frame=new SimpleFrame();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //关闭
				frame.setVisible(true); //显示框架
			}
		});
		
		//Graph g = new Graph();
	    //g.createGraph("D://test2.txt");
		//g.showDirectedGraph();
		//System.out.println(g.queryBridgeWords("strange", "worlds"));
		//System.out.println(g.queryBridgeWords("strane", "worlds"));
		//System.out.println(g.queryBridgeWords("new", "and"));
		//System.out.println(g.queryBridgeWords("seek", "to"));
	//	System.out.println(g.randomWalk());
	//	System.out.println(g.randomWalk());
	//	System.out.println(g.randomWalk());
		//System.out.println(Arrays.toString(g.calcShortesePath("to", null)));
	//	System.out.println(Arrays.toString(g.calcShortesePath("to","worlds")));
		
		//Scanner in = new Scanner(System.in);
		//System.out.println("Input:");
		//String s = in.nextLine();
		//System.out.println(g.generateNewText(s));
		
		//remote test
		//To@explore strange ne1w worlds,
		//To seek out new life and new civilizations
		
		//git test2
	}
}
