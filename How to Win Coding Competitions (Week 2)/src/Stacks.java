import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.SortedMap;
import java.util.TreeMap;

public class Stacks {

    static BufferedReader newInput() throws IOException {
        return new BufferedReader(new FileReader("stacks.in"));
    }
    
    static BufferedWriter newOutput() throws IOException {
        return new BufferedWriter(new FileWriter("stacks.out"));
    }
    
    private static void addNewStacktoTray(SortedMap<Integer,Integer> tray)
    {
        if(tray.containsKey(1))
        {
            tray.put(1, tray.get(1)+1);
        }
        else
        {
            tray.put(1, 1);
        }
    }

    private static void addCupToLowestStack(SortedMap<Integer,Integer> tray)
    {
        int lowestStackHeight = tray.firstKey();
        int numtrays = tray.get(lowestStackHeight);
        if(numtrays==1) tray.remove(lowestStackHeight); else tray.put(lowestStackHeight, numtrays-1);
        
        if(tray.containsKey(lowestStackHeight+1))
        {
            tray.put(lowestStackHeight+1,tray.get(lowestStackHeight+1)+1);
        }
        else
        {
            tray.put(lowestStackHeight+1,1);
        }
       
    }

    
    public static void main(String[] args) throws IOException {
        try (BufferedReader in = newInput(); BufferedWriter out = newOutput()) {
            int numPassengers = Integer.parseInt(in.readLine());
            //System.out.println("numPassengers = " + numPassengers);
            if(numPassengers <=0) System.exit(1);
            SortedMap<Integer,Integer> sortedMap = new TreeMap<Integer,Integer>();
            String[] passengerCups = in.readLine().split(" ");
            
            for(int n=0; n<numPassengers; n++)
            {
                //System.out.println("n = " + n);
                int currentCup = Integer.parseInt(passengerCups[n]);
                //System.out.println("currentCup = " + currentCup);

                if(currentCup > 0) // non-empty cup gets new stack
                {
                    //System.out.println("calling addNewStacktoTray(sortedMap)");
                    addNewStacktoTray(sortedMap);
                }
                else // empty cup either gets new stack or gets added to existing stack
                {
                    if(sortedMap.isEmpty())
                    {
                        //System.out.println("calling addNewStacktoTray(sortedMap)");
                        addNewStacktoTray(sortedMap);
                    }
                    else
                    {
                        //System.out.println("calling addCupToLowestStack(sortedMap)");
                        addCupToLowestStack(sortedMap);                        
                    }
                }
                //System.out.println(sortedMap.toString());
            }// end for
            
            out.write(sortedMap.lastKey() + "\n");
            out.flush();
        }// end try
    }// end main

}
