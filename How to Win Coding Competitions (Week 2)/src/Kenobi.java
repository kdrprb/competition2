import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Iterator;

public class Kenobi {

    static BufferedReader newInput() throws IOException {
        return new BufferedReader(new FileReader("kenobi.in"));
    }
    
    static BufferedWriter newOutput() throws IOException {
        return new BufferedWriter(new FileWriter("kenobi.out"));
    }
    
    private static class LightSaberTable
    {
        private ArrayDeque<Integer> left;
        private int leftCount;
        private ArrayDeque<Integer> right;
        private int rightCount;


        public LightSaberTable() 
        { 
            left = new ArrayDeque<Integer>();
            leftCount = 0;
            right = new ArrayDeque<Integer>();
            rightCount = 0;
        }
        
        public StringBuilder printable() {
            StringBuilder sb = new StringBuilder();
            
            if(leftCount+rightCount == 0) return sb;
            
            if(leftCount == 0) {
                sb.append(right.iterator().next());
                return sb;
            }
            
            // assume left and right both have 1+ elements
            Iterator<Integer> leftIterator = left.iterator();
            sb.append(leftIterator.next());            
            while(leftIterator.hasNext()) sb.append(" " + leftIterator.next());

            Iterator<Integer> rightIterator = right.iterator();
            while(rightIterator.hasNext()) sb.append(" " + rightIterator.next());

            return sb;
        }
        
        public void addLightSaber(int iD)
        {
            // Either left = right or left = right - 1
            if(leftCount<rightCount)
            {
                left.add(right.pop());
                leftCount++;
                right.add(iD);
            }
            else
            {
                right.add(iD);
                rightCount++;
            }
        }// end addLightSaber

        public void takeLightSaber()
        {
            // Either left = right or left = right - 1
            if(leftCount==rightCount)
            {
                right.push(left.removeLast());
                leftCount--;
                right.removeLast();
            }
            else
            {
                right.removeLast();
                rightCount--;
            }
            
        }// end takeLightSaber

        public void reorgLightSabers()
        {
            ArrayDeque<Integer> origRight = right;
            int origRightCount = rightCount;
            ArrayDeque<Integer> origLeft = left;
            int origLeftCount = leftCount;
            
            left = origRight;
            leftCount = origRightCount;
            right = origLeft;
            rightCount = origLeftCount;
            
            if(leftCount > rightCount)
            {
                right.push(left.removeLast());
                leftCount--;
                rightCount++;
            }
            
        }// end reorgLightSabers

    }// end class LightSaberTable

    
    public static void main(String[] args) throws IOException {
        try (BufferedReader in = newInput(); BufferedWriter out = newOutput()) {
            int numOperations = Integer.parseInt(in.readLine());
            //System.out.println("numOperations = " + numOperations);
            if(numOperations <=0) System.exit(1);
            
            LightSaberTable myTable = new LightSaberTable();
            
            for(int n=0; n<numOperations; n++)
            {
                //System.out.println("n="+ n);
                String currentLine = in.readLine();
                //System.out.println("currentLine="+currentLine);
                String[] operation = currentLine.split(" ");
                //System.out.println("operation="+operation[0]);

                if(operation[0].equals("add"))
                {
                    myTable.addLightSaber(Integer.parseInt(operation[1]));
                    //System.out.println("called myTable.addLightSaber("+Integer.parseInt(operation[1])+");");
                }
                else if (operation[0].equals("take"))
                {
                    myTable.takeLightSaber();
                    //System.out.println("called myTable.takeLightSaber();");
                }
                else // assume "mum!"
                {
                    myTable.reorgLightSabers();
                    //System.out.println("called myTable.reorgLightSabers();");
                }

            }// end for
            
            out.write((myTable.leftCount+myTable.rightCount) + "\n");
            out.write(myTable.printable().toString());
            out.flush();
        }// end try
    }// end main

}
