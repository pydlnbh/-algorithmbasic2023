package src.class17;

import java.util.HashSet;
import java.util.Stack;

// 打印n层汉诺塔从最左边移动到最右边的全部过程（递归+非递归实现）
public class Code01_Hanoi {
	// solution one
	public static void hanoi1(int n) {
		leftToRight(n);
	}
	
	// left to right
	public static void leftToRight(int n) {
		if (n == 1) {
			System.out.println("Move 1 from left to right");
			return;
		}
		
		leftToMid(n - 1);
		System.out.println("Move " + n + " from left to right");
		midToRight(n - 1);
	}
	
	// left to mid
	public static void leftToMid(int n) {
		if (n == 1) {
			System.out.println("Move 1 from left to mid");
			return;
		}
		
		leftToRight(n - 1);
		System.out.println("Move " + n + " from left to mid");
		rightToMid(n - 1);
	}
	
	// right to mid
	public static void rightToMid(int n) {
		if (n == 1) {
			System.out.println("Move 1 from right to mid");
			return;
		}
		
		rightToLeft(n - 1);
		System.out.println("Move " + n + " from right to mid");
		leftToMid(n - 1);
	}
	
	// mid to right
	public static void midToRight(int n) {
		if (n == 1) {
			System.out.println("Move 1 from mid to right");
			return;
		}
		
		midToLeft(n - 1);
		System.out.println("Move " + n + " from mid to right");
		leftToRight(n - 1);
	}
	
	// mid to left
	public static void midToLeft(int n) {
		if (n == 1) {
			System.out.println("Move 1 from mid to left");
			return;
		}
		
		midToRight(n - 1);
		System.out.println("Move " + n + "from mid to right");
		rightToLeft(n - 1);
	}
	
	// right to left
	public static void rightToLeft(int n) {
		if (n == 1) {
			System.out.println("Move 1 form right to left");
			return;
		}
		
		rightToMid(n - 1);
		System.out.println("Move " + n + "from right to left");
		midToLeft(n - 1);
	}
	
	// solution two
	public static void hanoi2(int n) {
		if (n > 0) {
			func(n, "left", "right", "mid");
		}
	}
	
	// recursive method two
	public static void func(int n, String from, String to, String other) {
		if (n == 1) {
			System.out.println("Move 1 from "+ from + " to " + to);
			return;
		}
		
		func(n - 1, from, other, to);
		System.out.println("Move " + n + " from " + from + " to " + to);
		func(n - 1, other, to, from);
	}
	
	// solution three no recursive
	public static void hanoi3(int n) {
		if (n < 1) {
			return;
		}
		
		Stack<Record> stack = new Stack<>();
		HashSet<Record> set = new HashSet<>();
		stack.add(new Record(n, "left", "right", "mid"));
		
		while (!stack.isEmpty()) {
			Record cur = stack.pop();
			
			if (cur.level == 1) {
				System.out.println("Move 1 form " + cur.from + " to " + cur.to);
			} else {
				if (!set.contains(cur)) {
					set.add(cur);
					stack.push(cur);
					stack.push(new Record(cur.level - 1, cur.from, cur.other, cur.to));
				} else {
					System.out.println("Move " + cur.level + " from " + cur.from + " to " + cur.to);
					stack.push(new Record(cur.level - 1, cur.other, cur.to, cur.from));
				}
			}
		}
	}
	
	// auxiliary class
	public static class Record {
		public int level;
		public String from;
		public String to;
		public String other;
		
		public Record(int l, String f, String t, String o) {
			level = l;
			from = f;
			to = t;
			other = o;
		}
	}
	
	// main
	public static void main(String[] args) {
		int n = 3;
		hanoi1(n);
		System.out.println("---");
		hanoi2(n);
		System.out.println("---");
		hanoi3(n);
	}
	
}
