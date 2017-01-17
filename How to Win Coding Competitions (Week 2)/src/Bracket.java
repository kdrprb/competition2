import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.Stack;

public class Bracket {

    static Scanner newInput() throws IOException {
        return new Scanner(new File("brackets.in"));
    }
    
    static PrintWriter newOutput() throws IOException {
        return new PrintWriter("brackets.out");
    }

    
    public static void main(String[] args) throws IOException {
        try (Scanner in = newInput(); PrintWriter out = newOutput()) {
            
            while(in.hasNext())
            {
                String currentLine = in.nextLine();
                boolean currentLineError = false; 
                Stack<Character> myStack = new Stack<Character>();
                //System.out.println("*******************");
                //System.out.println("currentLine=" + currentLine);
                
                CURRENTLINE: for(int n=0; n<currentLine.length(); n++)
                {
                    char currentChar = currentLine.charAt(n);
                    //System.out.println("currentChar="+currentChar);
                    switch(currentChar) {
                    case '(': myStack.push(currentChar);
                    break;
                    case '[': myStack.push(currentChar);
                    break;
                    case ')': 
                        if(myStack.isEmpty() || !myStack.pop().equals('(')) 
                        {
                            out.println("NO");
                            currentLineError = true;
                            break CURRENTLINE; // exit because you're done
                        }
                        break;
                    case ']': 
                        if(myStack.isEmpty() || !myStack.pop().equals('[')) 
                        {
                            out.println("NO");
                            currentLineError = true;
                            break CURRENTLINE; // exit because you're done
                        }
                        break;
                    }
                }
                
                if(!currentLineError)
                {
                    if(myStack.isEmpty())
                    {
                        out.println("YES");                        
                    }
                    else
                    {
                        out.println("NO");
                        
                    }
                }
            }
            
        }
    }

}
