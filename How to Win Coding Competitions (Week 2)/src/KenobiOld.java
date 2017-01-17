import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class KenobiOld {

    static BufferedReader newInput() throws IOException {
        return new BufferedReader(new FileReader("kenobi.in"));
    }
    
    static BufferedWriter newOutput() throws IOException {
        return new BufferedWriter(new FileWriter("kenobi.out"));
    }
    
    private static class LightSaberTable
    {
        private int numLightSabers;
        private LightSaber first;
        private LightSaber middle;
        private LightSaber last;
        
        private static class LightSaber
        {
            private int ID;
            private LightSaber previous;
            private LightSaber next;
            public int getID() { return ID; }
            public void setID(int iD) { this.ID = iD;}
            public LightSaber getPrevious() { return previous; }
            public void setPrevious(LightSaber previous) { this.previous = previous; }
            public LightSaber getNext() { return next; }
            public void setNext(LightSaber next) { this.next = next; }
        }// end class LightSaber
        

        public LightSaberTable() { numLightSabers=0; }
        
        public StringBuilder printable() {
            StringBuilder sb = new StringBuilder();
            LightSaber current = this.first;
            if(!(current == null)) sb.append(current.getID());
            
            for(int n=1; n<this.numLightSabers; n++)
            {
                current = current.getNext();
                sb.append(" " + current.getID());
            }
            
            return sb;
        }
        
        public void addLightSaber(int iD)
        {
            LightSaber toAdd = new LightSaber();
            toAdd.setID(iD);
            
            if(numLightSabers==0)
            {
                this.first=toAdd;
                this.middle=toAdd;
                this.last=toAdd;
                this.numLightSabers=1;
            }
            else
            {
                this.last.setNext(toAdd);
                toAdd.setPrevious(this.last);
                this.last = toAdd;
                this.numLightSabers+=1;
                if(this.numLightSabers%2==0) this.middle = this.middle.getNext();
            }
        }// end addLightSaber

        public void takeLightSaber()
        {
            this.numLightSabers-=1;
            if(numLightSabers > 0)
            {
                this.last = this.last.getPrevious();
                this.last.setNext(null);
                if(!(this.numLightSabers%2==0)) this.middle = this.middle.getPrevious();                
            }
            else this.first = null;
        }// end takeLightSaber

        public void reorgLightSabers()
        {
            if(numLightSabers == 0 || numLightSabers == 1) return;
            LightSaber origFirst = this.first;
            LightSaber origMiddle = this.middle;
            LightSaber origLast = this.last;
            
            if(this.numLightSabers%2==0) 
            {
                this.middle = origFirst;
            }
            else
            {
                this.middle = origLast;
            }

            origLast.setNext(origFirst);
            origFirst.setPrevious(origLast);
            this.last = origMiddle.getPrevious();

            origMiddle.getPrevious().setNext(null);
            origMiddle.setPrevious(null);
            this.first = origMiddle;
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
            
            out.write(myTable.numLightSabers + "\n");
            out.write(myTable.printable().toString());
            out.flush();
        }// end try
    }// end main

}
