/* Name: Maricruz Tolosa Amaya
 * Net ID: mtolosaa
 * ID#: 29988518
 * CSC 172 Project#1
 * Lab: MW 2:00-3:15
 * 
 */
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Test {
	 public static void main(String[] args) throws IOException 
	    {
	        // Scan file.
		 	File text = new File("infix_expr_short");
	        Scanner scanner = new Scanner(text);
	        List<String> line = new ArrayList<String>();
	        BufferedWriter writer = new BufferedWriter(new FileWriter(args[1]));
	        
	        //runs while there is something in the text file
	        while (scanner.hasNextLine()) 
	            line.add(scanner.nextLine());
	        
	        scanner.close();

	        //adding to queue
	        String[] array = line.toArray(new String[0]);
	        Queue<String> postfix = new Queue<String>();
	        String[] answer = new String[array.length];
	        
	        // going from infix to postfix
	        for (int i = 0; i < array.length; ++i) 
	        { 
	            String input = array[i];
	            postfix = InfixToPostfix.toPostfix(input);
	            
	            String temp = "";
	            while (postfix.returnSize() > 0) 
	                temp += postfix.dequeue() + " ";
	            
	            answer[i] = Postfix.calculate(temp);
	        }
	        
	        // Write answer string to a text file.
	        for (String i : answer) 
	        {
	            writer.write(i);
	            writer.newLine();
	        }
	        
	        writer.close();
	    }
	    
	    public static String toString(String[] array) 
	    {
	        String string = "";
	        for (String element : array)
	            string += element + "\n";
	        return string;
	    }
	    
	    public static <T> void printArray(T[] genArray) 
	    {
	        for (T element : genArray) 
	            System.out.printf("%s", element);
	    }
}
