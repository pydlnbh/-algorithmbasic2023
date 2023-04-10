package src.class16;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

// 实现图的深度优先遍历
public class Code03_DFS {
	// solution
	public static void dfs(Node start) {
		if (start == null) {
			return;
		}
		
		Stack<Node> stack = new Stack<>();
		Set<Node> set = new HashSet<>();
		
		stack.push(start);
		set.add(start);
		System.out.println(start.value);
		
		while (!stack.isEmpty()) {
			Node cur = stack.pop();
			
			for (Node next : cur.nexts) {
				if (!set.contains(next)) {
					stack.push(cur);
					stack.push(next);
					set.add(next);
					
					System.out.println(next.value);
					break;
				}
			}
		}
	}
}
