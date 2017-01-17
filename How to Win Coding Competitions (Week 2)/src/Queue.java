import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

public class Queue {

    static BufferedReader newInput() throws IOException {
        return new BufferedReader(new FileReader("queue.in"));
    }
    
    static BufferedWriter newOutput() throws IOException {
        return new BufferedWriter(new FileWriter("queue.out"));
    }


    
    public static void main(String[] args) throws IOException {
        try (BufferedReader in = newInput(); BufferedWriter out = newOutput()) {
            int numOperations = Integer.parseInt(in.readLine());
            //System.out.println("numOperations = " + numOperations);
            if(numOperations <=0) System.exit(1);
            LinkedList<Integer> ll = new LinkedList<Integer>();
            //in.nextLine();
            //String output = "";
            StringBuilder sb = new StringBuilder();
            
            for(int n=0; n<numOperations; n++)
            {
                //System.out.println("n = " + n);
                String currentLine = in.readLine();
                //System.out.println("currentLine = " + currentLine);
                String op = currentLine.substring(0, 1);
                if(op.equals("+")) 
                {
                    //System.out.println("adding: " + currentLine.substring(2));
                    ll.addFirst(Integer.valueOf(currentLine.substring(2)));
                }
                else
                {
                    //out.println(ll.removeLast());
                    //output = output + ll.removeLast() + "\n";
                    sb.append(ll.removeLast() + "\n");
               }
                //System.out.println("linked list = " + ll.toString());
            }

            out.write(sb.toString());
            
        }
    }

}
