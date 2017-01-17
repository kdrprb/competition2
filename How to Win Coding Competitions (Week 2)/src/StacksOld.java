import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

public class StacksOld {

    static BufferedReader newInput() throws IOException {
        return new BufferedReader(new FileReader("stacks.in"));
    }
    
    static BufferedWriter newOutput() throws IOException {
        return new BufferedWriter(new FileWriter("stacks.out"));
    }
    
    private static class myStack
    {
/*        private int numCups;
        public myStack() { this.numCups = 1; }
        public void addCup() { this.numCups += 1; }*/

    }
    
    private static void addNewStacktoTray(SortedMap<Integer,Set<myStack>> tray)
    {
        if(tray.containsKey(1))
        {
            tray.get(1).add(new myStack());
        }
        else
        {
            HashSet<myStack> newSet = new HashSet<myStack>();
            newSet.add(new myStack());
            tray.put(1, newSet);
        }
    }

    private static void addCupToLowestStack(SortedMap<Integer,Set<myStack>> tray)
    {
        int lowestStackHeight = tray.firstKey();
        Set<myStack> lowestStacks = tray.get(lowestStackHeight);
        myStack lowStack = lowestStacks.iterator().next();
        lowestStacks.remove(lowStack);
        if(lowestStacks.isEmpty()) tray.remove(lowestStackHeight);
        //lowStack.addCup();
        
        if(tray.containsKey(lowestStackHeight+1))
        {
            tray.get(lowestStackHeight+1).add(lowStack);
        }
        else
        {
            HashSet<myStack> newSet = new HashSet<myStack>();
            newSet.add(lowStack);
            tray.put(lowestStackHeight+1, newSet);
        }
       
    }

    
    public static void main(String[] args) throws IOException {
        try (BufferedReader in = newInput(); BufferedWriter out = newOutput()) {
            int numPassengers = Integer.parseInt(in.readLine());
            //System.out.println("numPassengers = " + numPassengers);
            if(numPassengers <=0) System.exit(1);
            SortedMap<Integer,Set<myStack>> sortedMap = new TreeMap<Integer,Set<myStack>>();
            String[] passengerCups = in.readLine().split(" ");
            
            for(int n=0; n<numPassengers; n++)
            {
                //System.out.println("n = " + n);
                int currentCup = Integer.parseInt(passengerCups[n]);
                //System.out.println("currentCup = " + currentCup);

                if(currentCup > 0) // non-empty cup gets new stack
                {
                    addNewStacktoTray(sortedMap);
                }
                else // empty cup either gets new stack or gets added to existing stack
                {
                    if(sortedMap.isEmpty())
                    {
                        addNewStacktoTray(sortedMap);
                    }
                    else
                    {
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
