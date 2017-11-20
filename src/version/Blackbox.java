package test1;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

public class Blackbox {

    
    @Test
    public void testGenerateNewText1() throws IOException {
        Graph graph = new Graph();
        graph.createGraph("C:\\Users\\lenovo\\Desktop\\test.txt");
        String str = graph.generateNewText("Seek to explore new and exciting synergies");
        String exp = "Seek to explore strange new life and exciting synergies";
        String exp2 = "Seek to explore a new life and exciting synergies";
        assertTrue(exp.equals(str) || exp2.equals(str));
    }
    
    
    @Test
    public void testGenerateNewText2() throws IOException {
        Graph graph = new Graph();
        graph.createGraph("C:\\Users\\lenovo\\Desktop\\test.txt");
        String str = graph.generateNewText("Seek to explore\nnew and exciting synergies");
        String exp = "Seek to explore\nnew and exciting synergies";
        assertEquals(exp,str);
    }
    
    
    
    @Test
    public void testGenerateNewText3() throws IOException {
        Graph graph = new Graph();
        graph.createGraph("C:\\Users\\lenovo\\Desktop\\test.txt");
        String str = graph.generateNewText("Ñ°ÕÒ ºÍ Ì½Ë÷ see@k a*nd expl(ore");
        String exp = "Ñ°ÕÒ ºÍ Ì½Ë÷ see@k a*nd expl(ore";
        assertEquals(exp,str);
    }
    
    
    @Test
    public void testGenerateNewText4() throws IOException {
        Graph graph = new Graph();
        graph.createGraph("C:\\Users\\lenovo\\Desktop\\test.txt");
        String str = graph.generateNewText("Seek,to;explore!new:and/exciting-synergies");
        String exp = "Seek,to;explore!new:and/exciting-synergies";
        assertEquals(exp,str);
    }
    
   
}

