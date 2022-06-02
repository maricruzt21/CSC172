/* Name: Maricruz Tolosa Amaya
 * Net ID: mtolosaa
 * ID#: 29988518
 * CSC 172 Project#1
 * Lab: MW 2:00-3:15
 * 
 */
import java.util.StringTokenizer;

public class InfixToPostfix {
	 private static Queue<String> postfix;
     
	    // Create postfix queue.
	    public static Queue<String> toPostfix(String input) 
	    {
	        postfix = new Queue<String>();
	             
	        input = input.replace("(", "( ");
	        input = input.replace(")", " )");
	        input = input.replace("!", "! ");
	        input = input.replace("^", " ^ ");
	        
	        // Tokenize infix.
	    	Queue<String> infix = new Queue<String>();
	        StringTokenizer token = new StringTokenizer(input, " ");
	        
	        while (token.hasMoreTokens())
	            infix.enqueue(token.nextToken());

	    	// Initialize operator stack.
	    	Stack<String> stack = new Stack<String>();
	    	
	        while (infix.returnSize() > 0) //something has to be in queue
	        { 
	           //checking to see what is next
	        	
	        	if (infix.peek().equals("&") | infix.peek().equals("|") | infix.peek().equals("=") | infix.peek().equals("<") | infix.peek().equals(">")) 
	            {
	                while (stack.returnSize() > 1 && !stack.peek().equals("(") ) 
	                    postfix.enqueue(stack.pop()); // Pop from stack and enqueue to postfix queue.
	                
	                stack.push(infix.dequeue());
	            }
	                        
	            else if (isDouble(infix.peek()))  
	            	postfix.enqueue(infix.dequeue()); 
	       
	           
	            else if (infix.peek().equals("("))  
	            	stack.push(infix.dequeue()); 
			
	            else if (infix.peek().equals(")"))  
	            {
	            	while ((stack.returnSize() > 0) && (!stack.peek().equals("(")))  
	                    postfix.enqueue(stack.pop()); 
	               
	                stack.pop(); // Pop ( off stack.
	                infix.dequeue(); // Dequeue ) from infix queue.
	            }
	    		
	            else if (infix.peek().equals("+") | infix.peek().equals("-") | infix.peek().equals("*") | infix.peek().equals("/") | infix.peek().equals("^") ) // If first in infix is operator ... 
	            { 
	                while (stack.returnSize() > 0 && (getPriority(stack.peek()) >= getPriority(infix.peek())) && !infix.peek().equals("^")) 
	                    postfix.enqueue(stack.pop()); // Pop operator off stack and enqueue to postfix queue.
	                
	                stack.push(infix.dequeue());
	            }
	            
	            else // If first in infix queue is unrecognized character  ...
	            	stack.push(infix.dequeue()); // Dequeue infix queue and push to stack. Error will be handled in PostfixCalc.java.
	    	}
	    	
	        while (stack.returnSize() > 0)  // While there are things left in stack ...
	            postfix.enqueue(stack.pop()); // Pop from stack and enqueue to postfix queue.
	        
	        return postfix;
	    }
	    
	    // Check if string is parseable to double.
	    private static boolean isDouble(String string)
	    {
	        try 
	        {
	            Double.parseDouble(string);
	            return true;
	        }
	        
	        catch (Exception exception)
	        {
	            return false;
	        }
	    }
	    
	    // assign operators to integers
	    private static final int ADDORSUBTRACT = 2;
	    private static final int MULTIPLYORDIVIDE = 3;
	    private static final int BOOLEAN = 0;
	    private static final int NOT = -4;
	    private static final int EXP = 6;
	    private static final int PAREN = -2;
	   
	    
	    //checking to see what priority it has
	    private static int getPriority(String operator) 
	    {
	        switch (operator) 
	        {
	            case "+":
	            case "-":
	                return ADDORSUBTRACT;
	                
	            case "^":
	                return EXP;
	                
	            case "&":
	            case "|":
	            case "=":
	            case ">":
	            case "<":
	                return BOOLEAN;
	                
	            case "%":
	            case "*":
	            case "/":
	                return MULTIPLYORDIVIDE;
	                
	            case "!":
	                return NOT;
	                
	            case "(":
	            case ")":
	                return PAREN;
	                
	          
	                
	            default: 
	                throw new IllegalArgumentException("Operator or operand not recognized."); 
	        }
	    }    
}
