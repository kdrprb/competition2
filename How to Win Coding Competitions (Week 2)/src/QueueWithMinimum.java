import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.SortedMap;
import java.util.TreeMap;

public class QueueWithMinimum {

    static BufferedReader newInput() throws IOException {
        return new BufferedReader(new FileReader("queuemin.in"));
    }
    
    static BufferedWriter newOutput() throws IOException {
        return new BufferedWriter(new FileWriter("queuemin.out"));
    }

    
    public static void main(String[] args) throws IOException {
        try (BufferedReader in = newInput(); BufferedWriter out = newOutput()) {
            int numOperations = Integer.parseInt(in.readLine());
            //System.out.println("numOperations = " + numOperations);
            if(numOperations <=0) System.exit(1);
            LinkedList<Integer> ll = new LinkedList<Integer>();
            SortedMap<Integer,Integer> sortedMap = new TreeMap<Integer,Integer>();
            
            for(int n=0; n<numOperations; n++)
            {
                //System.out.println("n = " + n);
                String currentLine = in.readLine();
                //System.out.println("currentLine = " + currentLine);
                String op = currentLine.substring(0, 1);
                if(op.equals("+")) 
                {
                    //System.out.println("adding: " + currentLine.substring(2));
                    Integer addInt = Integer.valueOf(currentLine.substring(2)); 
                    ll.addFirst(addInt);
                    if(!sortedMap.containsKey(addInt)) 
                    {
                        sortedMap.put(addInt, new Integer(1));
                    }
                    else
                    {
                        sortedMap.put(addInt, new Integer(sortedMap.get(addInt)+1));
                    }
                }
                else if(op.equals("-"))
                {
                    Integer removeInt = ll.removeLast();
                    //System.out.println("removing: " + removeInt);
                    if(sortedMap.get(removeInt).compareTo(1)>0) 
                    {
                        sortedMap.put(removeInt, new Integer(sortedMap.get(removeInt)-1));
                    }
                    else
                    {
                        sortedMap.remove(removeInt);
                    }
                }
                else // assume query operation
                {
                    //System.out.println("query min = " + sortedMap.firstKey());
                    out.write(sortedMap.firstKey() + "\n");
                    out.flush();
                }
                //System.out.println("linked list = " + ll.toString());
                //System.out.println("sorted tree = " + sortedMap.toString());
            }
            
        }
    }

}
