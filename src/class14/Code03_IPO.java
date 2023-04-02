package src.class14;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 输入正数数组costs、正数数组profits、正数K和正数M
 * costs[i]表示i号项目的花费
 * profits[i]表示i号项目在扣除花费之后还能挣到的钱(利润)
 * K表示你只能串行的最多做k个项目
 * M表示你初始的资金
 * 说明：每做完一个项目，马上获得的收益，可以支持你去做下一个项目，不能并行的做项目。
 * 输出：最后获得的最大钱数
 */
public class Code03_IPO {
	// auxiliary class
	public static class Help {
		public int p;
		public int c;
		
		public Help(int i, int j) {
			p = i;
			c = j;
		}
	}
	
	// auxiliary comparator
	public static class MinCostComparator implements Comparator<Help> {
		@Override
		public int compare(Help o1, Help o2) {
			return o1.c - o2.c;
		}
	}
	
	// auxiliary comparator
	public static class MaxProfixComparator implements Comparator<Help> {
		@Override
		public int compare(Help o1, Help o2) {
			return o2.p - o1.p;
		}
	}
	
	// solution
	public static int solution(int k, int w, int[] cost, int[] profix) {
		if (cost == null 
		|| cost.length == 0 
		|| cost.length != profix.length) {
			return 0;
		}
		
		PriorityQueue<Help> minHeap = new PriorityQueue<>(new MinCostComparator());
		PriorityQueue<Help> maxHeap = new PriorityQueue<>(new MaxProfixComparator());
		
		for (int i = 0; i < cost.length; i++) {
			minHeap.add(new Help(cost[i], profix[i]));
		}
		
		for (int i = 0; i < k; i++) {
			while (!minHeap.isEmpty() && minHeap.peek().c <= w) {
				maxHeap.add(minHeap.poll());
			}
			
			if (maxHeap.isEmpty()) {
				return w;
			}
			
			w += maxHeap.poll().p;
		}
		
		return w;
	}
}
