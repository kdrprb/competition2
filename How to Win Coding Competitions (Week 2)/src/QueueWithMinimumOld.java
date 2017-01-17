import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.TreeSet;

public class QueueWithMinimumOld {

    static Scanner newInput() throws IOException {
        return new Scanner(new File("queuemin.in"));
    }
    
    static PrintWriter newOutput() throws IOException {
        return new PrintWriter("queuemin.out");
    }

    
    public static void main(String[] args) throws IOException {
        try (Scanner in = newInput(); PrintWriter out = newOutput()) {
            int numOperations = in.nextInt();
            //System.out.println("numOperations = " + numOperations);
            if(numOperations <=0) System.exit(1);
            LinkedList<Integer> ll = new LinkedList<Integer>();
            TreeSet<Integer> sortedTree = new TreeSet<Integer>();
            
            in.nextLine();
            for(int n=0; n<numOperations; n++)
            {
                System.out.println("n = " + n);
                String currentLine = in.nextLine();
                System.out.println("currentLine = " + currentLine);
                String op = currentLine.substring(0, 1);
                if(op.equals("+")) 
                {
                    System.out.println("adding: " + currentLine.substring(2));
                    Integer addInt = Integer.valueOf(currentLine.substring(2)); 
                    ll.addFirst(addInt);
                    sortedTree.add(addInt);
                }
                else if(op.equals("-"))
                {
                    Integer removeInt = ll.removeLast();
                    System.out.println("removing: " + removeInt);
                    sortedTree.remove(removeInt);
                }
                else // assume query operation
                {
                    System.out.println("query min = " + sortedTree.first());
                    out.println(sortedTree.first());
                }
                System.out.println("linked list = " + ll.toString());
                System.out.println("sorted tree = " + sortedTree.toString());
            }
            
        }
    }

}
