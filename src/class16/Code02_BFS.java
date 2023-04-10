package src.class16;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

// 实现图的宽度优先遍历
public class Code02_BFS {
	// solution
	public static void bfs(Node start) {
		if (start == null) {
			return;
		}
		
		Queue<Node> queue = new LinkedList<>();
		Set<Node> set = new HashSet<>();
		
		queue.add(start);
		set.add(start);
		
		while (!queue.isEmpty()) {
			Node cur = queue.poll();
			System.out.println(cur.value);
		
			for (Node next : cur.nexts) {
				if (!set.contains(next)) {
					queue.add(next);
					set.add(next);
				}
			}
		}
	}
}
