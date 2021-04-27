package _01_TestMatchingBrackets;

import java.util.Stack;

public class TestMatchingBrackets {
    /*
     * Use a Stack to complete the method for checking if every opening bracket
     * has a matching closing bracket
     */
    public static boolean doBracketsMatch(String b) {
    	Stack<String> stack = new Stack<String>();
    	int right = 0;
    	int left = 0;
    	while(b.length()>0) {
    		stack.push(b.substring(0,1));
    		b = b.substring(1);
    	}
    	
    	while(!stack.isEmpty()) {
    		String s = stack.pop();
    		if(s.equals("}")) right++;
    		if(s.equals("{")) left++;
    		if(left>right) return false;
    	}
    	
        return true;
    }
}