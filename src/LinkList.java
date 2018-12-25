
import java.io.File;


 

class LinkList
{
    protected Node start;
    protected Node end ;
    public int size ;
    public boolean isVisited;
 
    /*  Constructor  */
    public LinkList()
    {
        start = null;
        end = null;
        size = 0;
        isVisited=false;
    }
    /*  Function to check if list is empty  */
    public boolean isEmpty()
    {
        return start == null;
    }
    /*  Function to get size of list  */
    public int getSize()
    {
        return size;
    }    
    /*  Function to insert an element at begining  */
    public void insertAtStart(String val)
    {
        Node nptr = new Node(val, null);    
        size++ ;    
        if(start == null) 
        {
            start = nptr;
            end = start;
        }
        else 
        {
            nptr.setLink(start);
            start = nptr;
        }
    }
    /*  Function to insert an element at end  */
//    public void insert(File val){
//        Nodee n=start;
//        
//        
//    }
    
    public void insertAtEnd(String val)
    {
       // if (isinList(val)){
        Node nptr = new Node(val,null);    
           
        if(start == null) 
        {
            start = nptr;
            end = start;
            size++ ; 
        }
        else 
           
        {
            if (!(isinList(val))){
            end.setLink(nptr);
            end = nptr;
            size++ ; }
        }
    }
    /*  Function to insert an element at position  */
    public void insertAtPos(String val , int pos)
    {
        Node nptr = new Node(val, null);                
        Node ptr = start;
        pos = pos - 1 ;
        for (int i = 1; i < size; i++) 
        {
            if (i == pos) 
            {
                Node tmp = ptr.getLink() ;
                ptr.setLink(nptr);
                nptr.setLink(tmp);
                break;
            }
            ptr = ptr.getLink();
        }
        size++ ;
    }
    /*  Function to delete an element at position  */
    public void deleteAtPos(int pos)
    {        
        if (pos == 1) 
        {
            start = start.getLink();
            size--; 
            return ;
        }
        if (pos == size) 
        {
            Node s = start;
            Node t = start;
            while (s != end)
            {
                t = s;
                s = s.getLink();
            }
            end = t;
            end.setLink(null);
            size --;
            return;
        }
        Node ptr = start;
        pos = pos - 1 ;
        for (int i = 1; i < size - 1; i++) 
        {
            if (i == pos) 
            {
                Node tmp = ptr.getLink();
                tmp = tmp.getLink();
                ptr.setLink(tmp);
                break;
            }
            ptr = ptr.getLink();
        }
        size-- ;
    }    
    /*  Function to display elements  */
    public String display()
    {
        String s = null;
        String f=null;
        //System.out.print("\nSingly Linked List = ");
        if (size == 0) 
        {
           // System.out.print("empty\n");
            
            return null;
        }    
        if (start.getLink() == null) 
        {
            //System.out.print(start.getData() );
            f=start.getData();
            s=f;
            return s;
        }
        Node ptr = start;
        //System.out.print(start.getData()+ " ");
       // s=f.getName();
        s=start.getData()+ " ,";
        ptr = start.getLink();
        while (ptr.getLink() != null)
        {
            //System.out.print(ptr.getData()+ " ");
           s= s.concat(ptr.getData()+ " ,");
            ptr = ptr.getLink();
        }
        //System.out.print(ptr.getData()+ " ");
        s=s.concat(ptr.getData()+ " ,");
        
        return s;
    }
    
    public Node searchFile(File f){
        Node ptr = start;
           if (size>0){
        if (start.getLink() == null) 
        {
            //System.out.print(start.getData() );
          
            return start;
        }
        
          while (!( ptr.getData().equals(f)))
        {           
            ptr = ptr.getLink();
        }
        return ptr;
           }
           return null;
        
    }
    
    public boolean isinList (String f){
        if (start==null){
            return true;
        }
        Node n=start;
        while (n!=null){
            if (n.getData().equals(f)){
                return true;
            }
            n=n.link;
            
        }
        return false;
    }
    
//        public boolean searchtrue(File f){
//        Nodee ptr = start;
//           if (size>0){
//       
//        
//          while (!( ptr.getData().equals(f)))
//        {           
//            ptr = ptr.getLink();
//        }
//        return true;
//           }
//           return null;
//        
//    }
    
    public void delete (File f){
          if (size == 0) 
        {
           
            return;
        } 
          if (start.data.equals(f)) 
        {
            start = start.getLink();
            size--; 
           
            return ;
        }
          if(end.data.equals(f)){
              Node s = start;
            Node t = start;
            while (s != end)
            {
                t = s;
                s = s.getLink();
            }
            end = t;
            end.setLink(null);
            size --;
            return;
          }
          
          Node ptr = start;
        
        while (!(ptr.equals(end)))
        {
            if (ptr.getLink().getData().equals(f)) 
            {
                Node tmp = ptr.getLink();
                tmp = tmp.getLink();
                ptr.setLink(tmp);
                break;
            }
            ptr = ptr.getLink();
        }
        size--;

    
        
    }
    
    
        public Node FindAtPos(int pos)
    {    
        int a=size;
        if (pos == 1) 
        {
            
             
            return start;
        }
        if (pos == size) 
        {
           
        
            return end;
        }
        Node ptr = start.getLink();
        pos = pos  ;
        for (int i = 2; i < a ; i++) 
        {
            if (i == pos) 
            {
              return ptr;  
            }
            ptr = ptr.getLink();
        }
        return null;
        
    } 
    
}