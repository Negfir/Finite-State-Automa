
import java.io.File;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author applenegin
 */
class Node
{
     String data;
     Node link;
     int count;
 
    /*  Constructor  */
    public Node()
    {
        link = null;
        data = null;
    }    
    /*  Constructor  */
    public Node(String d,Node n)
    {
        data = d;
        link = n;
    }    
    /*  Function to set link to next Node  */
    public void setLink(Node n)
    {
        link = n;
    }    
    /*  Function to set data to current Node  */
    public void setData(String d)
    {
        data = d;
    }    
    /*  Function to get link to next node  */
    public Node getLink()
    {
        return link;
    }    
    /*  Function to get data from current Node  */
    public String getData()
    {
        return data;
    }
}