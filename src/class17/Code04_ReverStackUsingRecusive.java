package src.class17;

import java.util.Stack;

// 给定一个栈，请逆序这个栈，不能申请额外的数据结构，只能使用递归函数
public class Code04_ReverStackUsingRecusive {
	// solution one use recursive no extra structure
	public static void reverse(Stack<Integer> stack) {
		if (stack.isEmpty()) {
			return;
		}
		
		int i = f(stack);
		reverse(stack);
		stack.push(i);
	}
	
	// recursive method
	public static int f(Stack<Integer> stack) {
		int result = stack.pop();
		if (stack.isEmpty()) {
			return result;
		} else {
			int last = f(stack);
			stack.push(result);
			return last;
		}
	}
	
	// main
	public static void main(String[] agrs) {
		Stack<Integer> stack = new Stack<>();
		stack.push(1);
		stack.push(2);
		stack.push(3);
		
		Stack<Integer> stack1 = new Stack<>();
		stack1.push(1);
		stack1.push(2);
		stack1.push(3);
		
		while (!stack.isEmpty()) {
			System.out.println(stack.pop());
		}
		
		System.out.println("---");
		
		reverse(stack1);
		
		while (!stack1.isEmpty()) {
			System.out.println(stack1.pop());
		}
	}
}
