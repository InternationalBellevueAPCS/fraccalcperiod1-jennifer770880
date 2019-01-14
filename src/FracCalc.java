import java.util.*;

public class FracCalc {

	/**
	 * Prompts user for input, passes that input to produceAnswer, then outputs the
	 * result.
	 * 
	 * @param args - unused
	 */
	public static String first_operand;
	public static String second_operand;
	public static String operator;
	public static int whole1;
	public static int whole2;
	public static int num1;
	public static int num2;
	public static int deno1;
	public static int deno2;

	public static void main(String[] args) {
		Scanner console = new Scanner(System.in);
		System.out.println("Please enter a faction operation: ");
		String user_input = console.nextLine();
		String answer = produceAnswer(user_input);
		System.out.println(answer);
		// TODO: Read the input from the user and call produceAnswer with an equation
		// Checkpoint 1: Create a Scanner, read one line of input, pass that input to
		// produceAnswer, print the result.
		// Checkpoint 2: Accept user input multiple times.
	}

	/**
	 * produceAnswer - This function takes a String 'input' and produces the result.
	 * 
	 * @param input - A fraction string that needs to be evaluated. For your
	 *              program, this will be the user input. Example: input ==> "1/2 +
	 *              3/4"
	 * @return the result of the fraction after it has been calculated. Example:
	 *         return ==> "1_1/4"
	 */
	public static String produceAnswer(String input) {
		for (int i = 0; i < input.length(); i++) {
			int count = 0;
			int[] index = new int[3];
			if (input.charAt(i) == '+' || input.charAt(i) == '*' || input.charAt(i) == '-') {
				operator = String.valueOf(input.charAt(i));
				first_operand = input.substring(0, i - 1);
				second_operand = input.substring(i + 2, input.length());
				// System.out.println(second_operand); /* used to check the stored value(shown
				// as output in console.) */
			}
			if (input.charAt(i) == '/') {
				count++;
				index[count - 1] = i;
				if (count > 3) {
					operator = "/";
					first_operand = input.substring(0, index[1]);
					second_operand = input.substring(index[1] - 1, input.length());
				}
			}
		}
		return processFractions(first_operand, operator, second_operand);
		// TODO: Implement this function to produce the solution to the input
		// Checkpoint 1: Return the second operand. Example "4/5 * 1_2/4" returns
		// "1_2/4".
		// Checkpoint 2: Return the second operand as a string representing each part.
		// Example "4/5 * 1_2/4" returns "whole:1 numerator:2 denominator:4".
		// Checkpoint 3: Evaluate the formula and return the result as a fraction.
		// Example "4/5 * 1_2/4" returns "6/5".
		// Note: Answer does not need to be reduced, but it must be correct.
		// Final project: All answers must be reduced.
		// Example "4/5 * 1_2/4" returns "1_1/5".

	}

	// identifying the denominator, numerator, and whole number
	public static String processFractions(String f1, String op, String f2) {
		// get int variables from fractions

		// testing fraction 1 to get int values
		if (f1.contains("_")) { // testing for mixed number
			whole1 = Integer.parseInt(f1.substring(0, f1.indexOf("_")));
			num1 = Integer.parseInt(f1.substring(f1.indexOf("_") + 1, f1.indexOf("/")));
			deno1 = Integer.parseInt(f1.substring(f1.indexOf("/") + 1));
			num1 = (whole1 * deno1) + num1; // making mixed number improper
		} else if (f1.contains("/")) { // testing for fraction
			num1 = Integer.parseInt(f1.substring(0, f1.indexOf("/")));
			deno1 = Integer.parseInt(f1.substring(f1.indexOf("/") + 1));
		} else {// testing for whole number
			whole1 = Integer.parseInt(f1.substring(0));
			num1 = whole1;
			deno1 = 1;
		}

		// testing fraction 2 to get int values
		if (f2.contains("_")) { // mixed fraction
			whole2 = Integer.parseInt(f2.substring(0, f2.indexOf("_")));
			num2 = Integer.parseInt(f2.substring(f2.indexOf("_") + 1, f2.indexOf("/")));
			deno2 = Integer.parseInt(f2.substring(f2.indexOf("/") + 1));
			num2 = whole2 * deno2 + num2;
		} else if (f2.contains("/")) { // fraction
			num2 = Integer.parseInt(f2.substring(0, f2.indexOf("/")));
			deno2 = Integer.parseInt(f2.substring(f2.indexOf("/") + 1));
		} else { // whole number
			whole2 = Integer.parseInt(f2.substring(0));
			num2 = whole2;
			deno2 = 1;
		}
		return identifyOperator(num1, num2, deno1, deno2, operator);
	}
	// TODO: Fill in the space below with helper methods

	// Addition
	public static String add(int n1, int n2, int d1, int d2) {
	    int newn = (n1*d2) + (n2*d1);
	    int newd = d1*d2;

	    int divisor = reduce(newn, newd);
	    newn/=divisor;
	    newd/=divisor;
	    int integerComponent=0;

	    while(newn >= newd) {
	        integerComponent++;
	        newn-=newd;
	    }
	    String answer ="";
	    if(integerComponent>0) {
	        answer += integerComponent +"_";
	    }
	    if(newn!=0) {
	        answer += newn+"/"+newd;
	    }
	    return answer;
	}
	//Multiplication
	public static String multiply(int n1, int n2, int d1, int d2) {
	    int newn = n1*n2;
	    int newd = d1*d2;

	    int divisor = reduce(newn, newd);
	    newn/=divisor;
	    newd/=divisor;

	    int integerComponent=0;

	    while(newn >= newd) {
	        integerComponent++;
	        newn-=newd;
	    }
	    String answer ="";
	    if(integerComponent>0) {
	        answer += integerComponent +"_";
	    }
	    if(newn!=0) {
	        answer += newn+"/"+newd;
	    }
	    return answer;
	}
	//determines the operator 
	 public static String identifyOperator(int n1, int n2, int d1, int d2, String op) {
	   if(op.equals("+")){
	       return (add(n1, n2, d1, d2));     
	    } else if(op.equals("-")) { 
	       n2=-1*n2;
	       return (add(n1, n2, d1, d2)); 
	    } else if(op.equals("*")) {
	       return (multiply(n1, n2, d1, d2));
	    } else { 
	       int x = n2;
	       int y = d2;
	       d2=x;
	       n2=y;
	       return (multiply(n1, n2, d1, d2));
	    } 

	 }
	 //reduce faction
	 public static int reduce (int newn, int newd) { //
		 int newn_abs = Math.abs (newn);
		 int newd_abs = Math.abs (newd); //

		  int min_num = Math.min (newn_abs, newd_abs);

		 int divisor = 1;

		 for (int i = 1; i <= min_num; i++) {
		  if (newn%i == 0 && newd%i == 0){

		  divisor = 1;
		  }
		    }
		    return divisor;

		 }
	/**
	 * greatestCommonDivisor - Find the largest integer that evenly divides two
	 * integers. Use this helper method in the Final Checkpoint to reduce fractions.
	 * Note: There is a different (recursive) implementation in BJP Chapter 12.
	 * 
	 * @param a - First integer.
	 * @param b - Second integer.
	 * @return The GCD.
	 */
	public static int greatestCommonDivisor(int a, int b) {
		a = Math.abs(a);
		b = Math.abs(b);
		int max = Math.max(a, b);
		int min = Math.min(a, b);
		while (min != 0) {
			int tmp = min;
			min = max % min;
			max = tmp;
		}
		return max;
	}

	/**
	 * leastCommonMultiple - Find the smallest integer that can be evenly divided by
	 * two integers. Use this helper method in Checkpoint 3 to evaluate expressions.
	 * 
	 * @param a - First integer.
	 * @param b - Second integer.
	 * @return The LCM.
	 */
	public static int leastCommonMultiple(int a, int b) {
		int gcd = greatestCommonDivisor(a, b);
		return (a * b) / gcd;
	}
}
