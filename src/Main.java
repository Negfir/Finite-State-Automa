
import GraphViz.GraphDrawer;
import GraphViz.GraphViz;

import java.io.File;
import java.io.FileNotFoundException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author applenegin
 */
public class Main {

    public static void main(String[] args) throws FileNotFoundException {

String firstNumber= JOptionPane.showInputDialog( "Enter number of your states:" );
        System.out.println(firstNumber );

   MainFrame frame = new MainFrame(Integer.parseInt(firstNumber));
//frame.buidTree();
frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
frame.setSize(600, 500);
frame.setVisible(true);
 

////LinkList l = new LinkList();
////l.insertAtEnd("daf");
////l.insertAtEnd("d");
////l.insertAtEnd("cvs");
////l.insertAtEnd("ijn");
////       for(int i=0; i<l.getSize(); i++){
////           System.out.println(l.FindAtPos(i));
////       }
//       
//       
//      LinkList[][] matrix = new LinkList[2][2];
//      matrix[0][0]=new LinkList();
//      matrix[0][1]=new LinkList();
//      matrix[1][0]=new LinkList();
//      matrix[1][1]=new LinkList();
//      matrix[0][0].insertAtEnd("sgsg");
//      matrix[1][0].insertAtEnd("bbbb");
//      matrix[0][1].insertAtEnd("hhh");
//      matrix[1][1].insertAtEnd("oplk");
//      
//        System.out.println(matrix[0][1].display());

      
//        String dotFormat="1->2;1->3;1->4;4->5;4->6;6->7;5->7;3->8;3->6;8->7;2->8;2->5;";
//        createDotGraph(dotFormat, "DotGraph");


//GraphViz2 gv = new GraphViz2();
//		gv.addln(gv.start_graph()); // Initializing the Graph
//      		
//      		gv.addln( "Hello->World [label=\"k\",len=1.00]");
//                gv.addln( "World->Hello [label=\"n\",len=1.00]");
////      		gv.addln( "o -> " + "9" +";");      		
////      		gv.addln( "node [shape=circle];");
//                
//                gv.addln(gv.end_graph());
//      		//Print out Digraph
//      		System.out.println(gv.getDotSource());
//		
//		//Store digraph in image file      
//      		File out = new File("Automata.gif");
//      		gv.writeGraphToFile(gv.getGraph(gv.getDotSource()), out);
 
//
//   GraphViz gv = new GraphViz();
//    gv.addln(gv.start_graph());
//    gv.addln("A -> B;");
//    gv.addln("A -> C;");
//    gv.addln(gv.end_graph());
//    System.out.println(gv.getDotSource());
//
//    String type = "gif";    String representationType="dot";
//    File out = new File("out." + type);   // out.gif in this example
//    gv.writeGraphToFile( gv.getGraph(gv.getDotSource(), type, representationType), out );


    }
//    public static void createDotGraph(String dotFormat,String fileName)
//{
//    GraphViz gv=new GraphViz();
//    gv.addln(gv.start_graph());
//    gv.add(dotFormat);
//    gv.addln(gv.end_graph());
//   // String type = "gif";
//    String type = "pdf";
//  // gv.increaseDpi();
//    gv.decreaseDpi();
//    gv.decreaseDpi();
//    File out = new File(fileName+"."+ type); 
//    gv.writeGraphToFile( gv.getGraph( gv.getDotSource(), type ), out );
//}
}
