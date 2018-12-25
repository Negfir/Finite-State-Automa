
import static java.awt.Frame.NORMAL;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Negin Firouzian
 */
public class MainFrame extends JFrame {

    int tableSize;
    LinkList[][] matrix;
    JTextField text1;
    JTextField text2;
    JTextField seq;
    JTextField result;
    JButton enter;
    JButton adj;
    JButton circle;
    JButton delete;
    JButton deleteprime;
    JButton build;
    JButton graph;
    JPanel Panel1;
    JPanel Panel2;
    JPanel Panel3;
    JPanel Panel4;
    JPanel Panel5;
    JTable table;
    int count;
    int c;
    DefaultTableModel model;

    public MainFrame(int a) {
        super("FSA");
        tableSize = a;

        matrix = new LinkList[a][a];

        text1 = new JTextField("First State");
        text2 = new JTextField("Final States");

        Panel1 = new JPanel();
        Panel1.add(text1);
        Panel1.add(text2);
        //Panel1.add(enter);
        //Panel1.add(adj);
        Panel1.setBounds(0, 340, 500, 40);
        add(Panel1);

        Panel5 = new JPanel();
        build = new JButton("Build Graph");
        graph = new JButton("Show Graph");
        adj = new JButton("Adjacent List");
        Panel5.add(build);
        Panel5.add(graph);
        Panel5.add(adj);
        Panel5.setBounds(0, 300, 500, 40);
        add(Panel5);

        Panel4 = new JPanel();
        circle = new JButton("Is there cycle?");
        delete = new JButton("Delete cycle");
        deleteprime=new JButton("Delete cycle with prim");
        Panel4.setBounds(0, 380, 500, 40);
        Panel4.add(circle);
        Panel4.add(delete);
        Panel4.add(deleteprime);
        add(Panel4);

        seq = new JTextField(10);
        result = new JTextField(10);
        enter = new JButton("Check");
        Panel3 = new JPanel();
        Panel3.setBounds(0, 420, 500, 40);
        Panel3.add(seq);
        Panel3.add(enter);
        Panel3.add(result);
        add(Panel3);

        Panel2 = new JPanel();
        model = new DefaultTableModel(tableSize + 1, tableSize + 1);
        model.setValueAt("State", 0, 0);

        for (int i = 1; i <= tableSize; i++) {
            model.setValueAt(i - 1, i, 0);
        }
        for (int i = 1; i <= tableSize; i++) {
            model.setValueAt(i - 1, 0, i);
        }
        table = new JTable(model);
        table.setEditingColumn(1);
        table.setBounds(900, 900, 100, 100);
        Panel2.add(table);
        Panel2.setBounds(80, 500, 500, 500);
        add(Panel2);

        enter.addActionListener(new enterHandler());
        adj.addActionListener(new adjHandler());
        circle.addActionListener(new circleHandler());
        delete.addActionListener(new deleteHandler());
        graph.addActionListener(new graphHandler());
        build.addActionListener(new buildHandler());
        deleteprime.addActionListener(new primHandler());
        System.out.println(model.getValueAt(tableSize, tableSize));

    }

    public void createMatrix() {
        for (int i = 0; i < tableSize; i++) {
            for (int j = 0; j < tableSize; j++) {
                matrix[i][j] = new LinkList();
            }
        }

        for (int i = 1; i <= tableSize; i++) {
            for (int j = 1; j <= tableSize; j++) {
                int a = model.getValueAt(i, j).toString().length();
                for (int k = 0; k < a; k++) {
                    //System.out.println(table.getValueAt(i, j).toString().charAt(k));
                    String s = String.valueOf(table.getValueAt(i, j).toString().charAt(k));

                    if (!s.equalsIgnoreCase(",")) {
                        matrix[i - 1][j - 1].insertAtEnd(s);
                    }
                }
                // System.out.println(matrix[i-1][j-1].display());

            }
        }
        count = 0;
        for (int i = 0; i < tableSize; i++) {
            for (int j = 0; j < tableSize; j++) {
                if (!matrix[i][j].isinList("-")) {
                    count++;

                }
            }
        }

        System.out.println("count: " + count);
    }

    public String firstState() {
        return text1.getText();
    }

    public boolean isEnd(String s) {
        return text2.getText().contains(s);
    }

    public boolean isCorrect() {
        createMatrix();
        boolean a = false, b = false;
        for (int i = 0; i < tableSize; i++) {
            if ((matrix[Integer.parseInt(text1.getText())][i].isinList(String.valueOf(seq.getText().charAt(0))))) {
                System.out.println(matrix[Integer.parseInt(text1.getText())][i].display());
                System.out.println("MainFrame.isCorrect()");
                b = true;
                break;
            }
        }
        int nextState = Integer.parseInt(text1.getText());
        for (int i = 0; i < seq.getText().length(); i++) {
            a = false;
            for (int j = 0; j < tableSize; j++) {
                if ((matrix[nextState][j].isinList(String.valueOf(seq.getText().charAt(i))))) {
                    System.out.println(matrix[Integer.parseInt(text1.getText())][j].display());
                    System.out.println("MainFrame.isCorrect()");
                    a = true;
                    nextState = j;
                    break;
                }
            }
        }
        if (!text2.getText().contains(String.valueOf(nextState))) {
            a = false;
        }

        return a && b;
    }

    boolean DFS(int u, int color[]) {

        color[u] = 1;

        for (int i = 0; i < tableSize; ++i) {
            if (!matrix[u][i].isinList("-")) {
                int v = i;

                if (color[v] == 1) {
                    return true;
                }

                if (color[v] == 0 && DFS(v, color)) {
                    return true;
                }
            }
        }

        color[u] = 2;

        return false;
    }

    boolean isCyclic() {

        int[] color = new int[tableSize];
        for (int i = 0; i < tableSize; i++) {
            color[i] = 0;
        }

        for (int i = 0; i < tableSize; i++) {
            if (color[i] == 0) {
                if (DFS(i, color) == true) {
                    return true;
                }
            }
        }

        return false;
    }

    boolean delete(int u, int color[]) {
        // GRAY : 1 black 2 white 3

        color[u] = 1;

        // Iterate through all adjacent vertices
        for (int i = 0; i < tableSize; i++) {
            if (!matrix[u][i].isinList("-")) {
                int v = i;  // An adjacent of u

                // If there is
                if (color[v] == 1) {
                    matrix[u][v] = new LinkList();
                    matrix[u][v].insertAtEnd("-");
                    System.out.println("" + u + "-" + v);
                    return true;
                }

                // If v is not processed and there is a back
                // edge in subtree rooted with v
                if (color[v] == 0 && DFS(v, color)) {
                    matrix[u][v] = new LinkList();
                    matrix[u][v].insertAtEnd("-");
                    System.out.println("" + u + "-" + v);

                    return true;
                }
            }
        }

        // Mark this vertex as processed
        color[u] = 2;

        return false;
    }

// Returns true if there is a cycle in graph
    boolean deleteCyclic() {
        // Initialize color of all vertices as WHITE
        int[] color = new int[tableSize];
        for (int i = 0; i < tableSize; i++) {
            color[i] = 0;
        }

        // Do a DFS traversal beginning with all
        // vertices
        for (int i = 0; i < tableSize; i++) {
            if (color[i] == 0) {
                if (delete(i, color) == true) {
                    return true;
                }
            }
        }

        return false;
    }

    void delete() {

        while (isCyclic()) {
            deleteCyclic();
            //DFStt();
        }
    }

    public void graph() {
        GraphViz2 gv = new GraphViz2();
        gv.addln(gv.start_graph()); // Initializing the Graph

        //String s=""
        for (int i = 0; i < tableSize; i++) {
            if (text2.getText().contains(String.valueOf(i))) {
                System.out.println(i);
                gv.addln("node [shape = doublecircle];" + i + ";");

            }
        }

        gv.addln("node [shape = circle];");

        for (int i = 0; i < tableSize; i++) {

            for (int j = 0; j < tableSize; j++) {

                for (int k = 1; k <= matrix[i][j].size; k++) {
                    if (!matrix[i][j].FindAtPos(k).data.equals("-")) {

                        gv.addln("" + i + "->" + j + "[label=" + "\"" + matrix[i][j].FindAtPos(k).data + "\"" + ",len=1.00];");

                        //gv.addln(""+i+"->"+j+"[label=\"n\",len=1.00]");
                    }

                }
            }
        }

        gv.addln(gv.end_graph());
        //Print out Digraph
        System.out.println(gv.getDotSource());

        //Store digraph in image file      
        File out = new File("Automata.gif");
        gv.writeGraphToFile(gv.getGraph(gv.getDotSource()), out);
    }


    private class enterHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            long startTime = System.nanoTime();
            System.out.println(isCorrect());
            result.setText(String.valueOf(isCorrect()));
            long estimatedTime = System.nanoTime() - startTime;
            System.out.println(estimatedTime);

        }

    }
    
    int minKey(int key[], Boolean mstSet[]) {

        int min = Integer.MAX_VALUE, min_index = -1;

        for (int v = 0; v < tableSize; v++) {
            if (mstSet[v] == false && key[v] < min) {
                min = key[v];
                min_index = v;
            }
        }

        return min_index;
    }

    LinkList[][] printMST(int parent[], int n, LinkList graph[][]) {
        System.out.println("Edge   Weight");
        
        for (int i = 1; i < tableSize; i++) {
            
            System.out.println(parent[i] + " - " + i + "    "
                    + graph[i][parent[i]].display());
            for(int j=0 ;j<tableSize;j++){
            if(j!=parent[i]){
                matrix[i][j]=new LinkList();
                matrix[i][j].insertAtEnd("-");
            }}
        }
        return graph;
    }

   
    LinkList[][] primMST(LinkList graph[][]) {
        int parent[] = new int[tableSize];

        int key[] = new int[tableSize];

        Boolean mstSet[] = new Boolean[tableSize];

        for (int i = 0; i < tableSize; i++) {
            key[i] = Integer.MAX_VALUE;
            mstSet[i] = false;
        }

        key[0] = 0;     
        parent[0] = -1; 

        for (int count = 0; count < tableSize - 1; count++) {
           
            int u = minKey(key, mstSet);

            mstSet[u] = true;

            for (int v = 0; v < tableSize; v++) 
  
            {
                if (!graph[u][v].isEmpty() && mstSet[v] == false
                        && graph[u][v].size < key[v]) {
                    parent[v] = u;
                    key[v] = graph[u][v].size;
                }
            }
        }

        return printMST(parent, tableSize, graph);
    }

    private class adjHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            //createMatrix();
            long startTime = System.nanoTime();

            String s;
            DefaultTableModel model1 = new DefaultTableModel(tableSize + 1, 2);
            model1.setValueAt("State", 0, 0);
            model1.setValueAt("Adjacent", 0, 1);

            for (int i = 1; i <= tableSize; i++) {
                model1.setValueAt(i - 1, i, 0);
            }
            for (int i = 0; i < tableSize; i++) {
                s = "";
                for (int j = 0; j < tableSize; j++) {
                    for (int k = 1; k <= matrix[i][j].size; k++) {
                        if (!matrix[i][j].FindAtPos(k).data.equals("-")) {
                            System.out.print(matrix[i][j].FindAtPos(k).data);
                            s = s.concat("(" + j + "," + matrix[i][j].FindAtPos(k).data + ")");
                        }

                    }
                }
                System.out.println("");
                model1.setValueAt(s, i + 1, 1);

            }
            JTable table2 = new JTable(model1);
            table2.setEditingColumn(1);
            table2.setBounds(900, 900, 100, 100);
            JPanel p = new JPanel();
            p.add(table2);
            p.setBounds(80, 500, 500, 500);
            JFrame f = new JFrame();
            f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            f.setVisible(true);
            f.setSize(600, 400);
            f.add(p);
            f.pack();
            long estimatedTime = System.nanoTime() - startTime;
            System.out.println(estimatedTime);

        }

    }

    private class circleHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            long startTime = System.nanoTime();
            System.out.println(isCyclic());
            long estimatedTime = System.nanoTime() - startTime;
            System.out.println(estimatedTime);
            result.setText(String.valueOf(isCyclic()));

        }

    }

    private class deleteHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            long startTime = System.nanoTime();
            delete();
            long estimatedTime = System.nanoTime() - startTime;
            System.out.println(estimatedTime);
            

            

        }

    }
        private class primHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            
            primMST(matrix);

            

        }

    }

    private class graphHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            long startTime = System.nanoTime();
            graph();

            String path = "Automata.gif";
            File file = new File(path);
            BufferedImage image = null;
            try {
                image = ImageIO.read(file);
            } catch (IOException ex) {
                Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
            JLabel label = new JLabel(new ImageIcon(image));
            JFrame f = new JFrame();
            f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            f.getContentPane().add(label);
            f.pack();
            f.setLocation(200, 200);
            f.setVisible(true);
            long estimatedTime = System.nanoTime() - startTime;
            System.out.println(estimatedTime);

        }

    }

    private class buildHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            createMatrix();
        }

    }

}
