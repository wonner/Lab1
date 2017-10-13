package test1;

// GraphViz API
// show and save digraph as .gif  

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;


class  GraphViz{
    private String runPath = "";
    private String dotPath = ""; 
    private String runOrder="";
    
    private String dotCodeFile="dotcode";
    private String resultGif="dotGif";
    private StringBuilder graph = new StringBuilder();

    Runtime runtime=Runtime.getRuntime();

    public void run(int i) {
        File file=new File(runPath);
        file.mkdirs();
        writeGraphToFile(graph.toString(), runPath,i);
        
        creatOrder(i);
        try {
            runtime.exec(runOrder);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void creatOrder(int i){
        runOrder+=dotPath+" ";
        runOrder+=runPath;
        runOrder+="\\"+dotCodeFile+i+".txt"+" ";
        runOrder+="-T gif ";
        runOrder+="-o ";
        runOrder+=runPath;
        runOrder+="\\"+resultGif+i+".gif";
        System.out.println(runOrder);
    }

    public void writeGraphToFile(String dotcode, String filename,int i) {
        try {
            File file = new File(filename+"\\"+dotCodeFile+i+".txt");
            if(!file.exists()){
                file.createNewFile();
            }
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(dotcode.getBytes());
            fos.flush();
            fos.close();
            
        } catch (java.io.IOException ioe) { 
            ioe.printStackTrace();
        }
     }  

    public GraphViz(String runPath,String dotPath) {
        this.runPath=runPath;
        this.dotPath=dotPath;
    }

    public void add(String line) {
        graph.append("\t"+line);
    }

    public void addln(String line) {
        graph.append("\t"+line + "\n");
    }

    public void addln() {
        graph.append('\n');
    }

    public void start_graph() {
        graph.append("digraph G {\n") ;
    }

    public void end_graph() {
        graph.append("}") ;
    }   
} 