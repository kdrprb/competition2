import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Snowmen {

    static BufferedReader newInput() throws IOException {
        return new BufferedReader(new FileReader("snowmen.in"));
    }
    
    static BufferedWriter newOutput() throws IOException {
        return new BufferedWriter(new FileWriter("snowmen.out"));
    }
    
    private static class mySnowman
    {
        private long mySize;
        private mySnowman myChild;
        
        protected mySnowman(long size) {this.mySize = size;}
        //protected mySnowman(mySnowman child) {this.myChild = child;}
        protected mySnowman(long size, mySnowman child) {this.mySize=size; this.myChild = child;}
        
        protected long getSize() {return this.mySize;}
        //protected void setChild(int size) {this.mySize = size;}
        protected mySnowman getChild() {return this.myChild;}
        //protected void setChild(mySnowman child) {this.myChild = child;}
    }

    
    public static void main(String[] args) throws IOException {
        try (BufferedReader in = newInput(); BufferedWriter out = newOutput()) {
            int numOperations = Integer.parseInt(in.readLine());
            if(numOperations <=0) System.exit(1);
            
            // initialize array
            ArrayList<mySnowman> snowmen = new ArrayList<mySnowman>(numOperations+1);
            snowmen.add(new mySnowman(0));
            long runningTotal = 0;

            
            for(int n=1; n<=numOperations; n++)
            {
                String[] currentLine = in.readLine().split(" ");
                //System.out.println("n = " + n);
                //System.out.println("runningTotal (before) = " + runningTotal);
                //System.out.println("currentLines = " + currentLine[0] + "|" + currentLine[1]);
                int currentIndex = Integer.parseInt(currentLine[0]);
                long currentAdd = Long.parseLong(currentLine[1]);
                mySnowman pointer = snowmen.get(currentIndex);
                long pointerSize = pointer.getSize();
                
                if(currentAdd==0)
                {
                    snowmen.add(pointer.getChild());
                    runningTotal += pointer.getChild().getSize();                    
                }
                else
                {
                    long newTotal = pointerSize+currentAdd;
                    snowmen.add(new mySnowman(newTotal, pointer));
                    runningTotal += newTotal;
                }
                //System.out.println("runningTotal (after) = " + runningTotal);
                
            }
            
            out.write(String.valueOf(runningTotal) + "\n");
            out.flush();
        }
    }

}
