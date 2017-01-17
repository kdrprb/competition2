import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

public class Stack {

    static BufferedReader newInput() throws IOException {
        return new BufferedReader(new FileReader("stack.in"));
    }
    
    static BufferedWriter newOutput() throws IOException {
        return new BufferedWriter(new FileWriter("stack.out"));
    }

    
    public static void main(String[] args) throws IOException {
        try (BufferedReader in = newInput(); BufferedWriter out = newOutput()) {
            int numOperations = Integer.parseInt(in.readLine());
            //System.out.println("numOperations = " + numOperations);
            if(numOperations <=0) System.exit(1);
            LinkedList<Integer> ll = new LinkedList<Integer>();
            //in.nextLine();
            //String output = "";
            //ArrayList<String> outputList = new ArrayList<String>();
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
            out.write(sb.toString());
        }
    }

}
