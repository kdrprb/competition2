import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Scanner;

public class QueueOld {

    static Scanner newInput() throws IOException {
        return new Scanner(new File("queue.in"));
    }
    
    static PrintWriter newOutput() throws IOException {
        return new PrintWriter("queue.out");
    }

    
    public static void main(String[] args) throws IOException {
        try (Scanner in = newInput(); PrintWriter out = newOutput()) {
            int numOperations = in.nextInt();
            //System.out.println("numOperations = " + numOperations);
            if(numOperations <=0) System.exit(1);
            LinkedList<Integer> ll = new LinkedList<Integer>();
            in.nextLine();
            String output = "";
            
            for(int n=0; n<numOperations; n++)
            {
                //System.out.println("n = " + n);
                String currentLine = in.nextLine();
                //System.out.println("currentLine = " + currentLine);
                String op = currentLine.substring(0, 1);
                if(op.equals("+")) 
                {
                    //System.out.println("adding: " + currentLine.substring(2));
                    ll.addFirst(Integer.valueOf(currentLine.substring(2)));
                }
                else
                {
                    out.println(ll.removeLast());
                    output = output + ll.removeLast() + "\n";
               }
                //System.out.println("linked list = " + ll.toString());
            }

            out.print(output);
            
        }
    }

}
