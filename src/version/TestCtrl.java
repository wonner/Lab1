package version;

public class TestCtrl {
    
    private Graph g=LoadCtrl.getGraph();
    
    public TestCtrl() {}
    
    public String generate(String inputText) {
         return g.generateNewText(inputText);
    }

}
