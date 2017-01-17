import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.Stack;

public class Postfix {

    static Scanner newInput() throws IOException {
        return new Scanner(new File("postfix.in"));
    }
    
    static PrintWriter newOutput() throws IOException {
        return new PrintWriter("postfix.out");
    }

    
    public static void main(String[] args) throws IOException {
        try (Scanner in = newInput(); PrintWriter out = newOutput()) {
            String operators = "+-*";
            Stack<String> myStack = new Stack<String>();
            
            while(in.hasNext())
            {
                String currentElement = in.next();
                
                if(!operators.contains(currentElement))
                {
                    myStack.push(currentElement);
                    //System.out.println("pushed currentElement:" + currentElement);
                }
                else
                {
                    Long a = Long.valueOf(myStack.pop());
                    //System.out.println("a=" + a);
                    Long b = Long.valueOf(myStack.pop());
                    //System.out.println("b=" + b);

                    switch(currentElement)
                    {
                    case "+":
                        myStack.push(String.valueOf(a+b));
                        //System.out.println("operator(+) with operands:" + b + "+" + a);
                        break;
                    case "-":
                        myStack.push(String.valueOf(b-a));
                        //System.out.println("operator(-) with operands:" + b + "-" + a);
                        break;
                    case "*":
                        myStack.push(String.valueOf(a*b));
                        //System.out.println("operator(*) with operands:" + b + "*" + a);
                        break;
                    }
                }

            }
            
            out.println(myStack.pop());
        }
    }

}
