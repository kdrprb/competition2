import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Scanner;

public class StackOld {

    static Scanner newInput() throws IOException {
        return new Scanner(new File("stack.in"));
    }
    
    static PrintWriter newOutput() throws IOException {
        return new PrintWriter("stack.out");
    }

    
    public static void main(String[] args) throws IOException {
        try (Scanner in = newInput(); PrintWriter out = newOutput()) {
            int numOperations = in.nextInt();
            //System.out.println("numOperations = " + numOperations);
            if(numOperations <=0) System.exit(1);
            LinkedList<Integer> ll = new LinkedList<Integer>();
            in.nextLine();
            //String output = "";
            //ArrayList<String> outputList = new ArrayList<String>();
            StringBuilder sb = new StringBuilder();
            
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
                    //out.println(ll.removeFirst());
                    //output = output + ll.removeFirst() + "\n";
                    //outputList.add(ll.removeFirst() + "\n");
                    sb.append(ll.removeFirst() + "\n");
                }
                //System.out.println("linked list = " + ll.toString());
            }
            
            //out.print(output);
            //Iterator<String> outputListIterator = outputList.iterator();
            //while(outputListIterator.hasNext()) out.print(outputListIterator.next());
            out.print(sb.toString());
        }
    }

}
