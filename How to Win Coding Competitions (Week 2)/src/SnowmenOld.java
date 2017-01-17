import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class SnowmenOld {

    static BufferedReader newInput() throws IOException {
        return new BufferedReader(new FileReader("snowmen.in"));
    }
    
    static BufferedWriter newOutput() throws IOException {
        return new BufferedWriter(new FileWriter("snowmen.out"));
    }

    
    public static void main(String[] args) throws IOException {
        try (BufferedReader in = newInput(); BufferedWriter out = newOutput()) {
            int numOperations = Integer.parseInt(in.readLine());
            if(numOperations <=0) System.exit(1);
            
            // initialize array
            ArrayList<Integer> snowmen = new ArrayList<Integer>(numOperations+1);
            snowmen.add(0);
            int runningTotal = 0;

            
            for(int n=1; n<=numOperations; n++)
            {
                String[] currentLine = in.readLine().split(" ");
                System.out.println("n = " + n);
                System.out.println("runningTotal (before) = " + runningTotal);
                System.out.println("currentLines = " + currentLine[0] + "|" + currentLine[1]);
                int currentIndex = Integer.parseInt(currentLine[0]);
                int currentAdd = Integer.parseInt(currentLine[1]);
                int oldValue = snowmen.get(currentIndex);
                
                if(currentAdd==0)
                {
                    int newSnowman = oldValue-1;
                    snowmen.add(newSnowman);
                    runningTotal += newSnowman;                    
                }
                else
                {
                    int newSnowman = oldValue+currentAdd;
                    snowmen.add(newSnowman);
                    runningTotal += newSnowman;
                }
                System.out.println("runningTotal (after) = " + runningTotal);
                
            }
            
            out.write(String.valueOf(runningTotal) + "\n");
            out.flush();
        }
    }

}
