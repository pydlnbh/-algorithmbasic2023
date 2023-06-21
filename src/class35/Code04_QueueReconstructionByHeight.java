package src.class35;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;

/**
 * 假设有打乱顺序的一群人站成一个队列，数组people表示队列中一些人的属性（不一定按顺序）
 * 每个people[i]=[hi, ki]表示第i个人的身高为hi，前面正好有ki个身高大于或等于hi的人
 * 请你重新构造并返回输入数组people所表示的队列，返回的队列应该格式化为数组queue
 * 其中queue[j]=[hj, kj]是队列中第j个人的属性（queue[0] 是排在队列前面的人）。
 * Leetcode题目：https://leetcode.com/problems/queue-reconstruction-by-height/
 */
public class Code04_QueueReconstructionByHeight {
	// solution one
	public static int[][] reconstructQueue1(int[][] people) {
		int N = people.length;
		Unit[] units = new Unit[N];
		
		for (int i = 0; i < N; i++) {
			units[i] = new Unit(people[i][0], people[i][1]);
		}
		
		Arrays.sort(units, new UnitComparator());
		ArrayList<Unit> arrList = new ArrayList<>();
		
		for (Unit unit : units) {
			arrList.add(unit.k, unit);
		}
		
		int[][] ans = new int[N][2];
		int index = 0;
		for (Unit unit : arrList) {
			ans[index][0] = unit.h;
			ans[index++][1] = unit.k;
		}
		
		return ans;
	}
	
	// solution two
	public static int[][] reconstructQueue2(int[][] people) {
		int N = people.length;
		Unit[] units = new Unit[N];
		
		for (int i = 0; i < N; i++) {
			units[i] = new Unit(people[i][0], people[i][1]);
		}
		
		Arrays.sort(units, new UnitComparator());
		
		SBTree tree = new SBTree();
		
		for (int i = 0; i < N; i++) {
			tree.insert(units[i].k, i);
		}
		
		LinkedList<Integer> allIndexes = tree.allIndexes();
		
		int[][] ans = new int[N][2];
		int index = 0;
		
		for (Integer arri : allIndexes) {
			ans[index][0] = units[arri].h;
			ans[index++][1] = units[arri].k;
		}
		
		return ans;
	}
	
	// auxiliary class
	public static class Unit {
		public int h;
		public int k;
		
		public Unit(int height, int greater) {
			h = height;
			k = greater;
		}
	}
	
	// auxiliary class
	public static class UnitComparator implements Comparator<Unit> {
		@Override
		public int compare(Unit o1, Unit o2) {
			return o1.h != o2.h ? o2.h - o1.h : o1.k - o2.k;
		}
	}
	
	// auxiliary class
	public static class SBTNode {
		public int value;
		public SBTNode l;
		public SBTNode r;
		public int size;
		
		public SBTNode(int arrIndex) {
			value = arrIndex;
			size = 1;
		}
	}
	
	// auxiliary class
	public static class SBTree {
		private SBTNode root;
		
		private SBTNode rightRotate(SBTNode cur) {
			SBTNode left = cur.l;
			cur.l = left.r;
			left.r = cur;
			left.size = cur.size;
			cur.size = (cur.l != null ? cur.l.size : 0)
			    + (cur.r != null ? cur.r.size : 0)
			    + 1;
			return left;
		}
		
		private SBTNode leftRotate(SBTNode cur) {
			SBTNode right = cur.r;
			cur.r = right.l;
			right.l = cur;
			right.size = cur.size;
			cur.size = (cur.l != null ? cur.l.size : 0)
			    + (cur.r != null ? cur.r.size : 0)
		        + 1;
			return right;
		}
		
		private SBTNode maintain(SBTNode cur) {
			if (cur == null) {
				return null;
			}
			
			int leftSize = cur.l != null ? cur.l.size : 0;
			int leftLeftSize = cur.l != null && cur.l.l != null ? cur.l.l.size : 0;
			int leftRightSize = cur.l != null && cur.l.r != null ? cur.l.r.size : 0;
			int rightSize = cur.r != null ? cur.r.size : 0;
			int rightRightSize = cur.r != null && cur.r.r != null ? cur.r.r.size : 0;
			int rightLeftSize = cur.r != null && cur.r.l != null ? cur.r.l.size : 0;
			
			if (leftLeftSize > rightSize) {
				cur = rightRotate(cur);
				cur.r = maintain(cur.r);
				cur = maintain(cur);
			} else if (leftRightSize > rightSize) {
				cur.l = leftRotate(cur.l);
				cur = rightRotate(cur);
				cur.l = maintain(cur.l);
				cur.r = maintain(cur.r);
				cur = maintain(cur);
			} else if (rightRightSize > leftSize) {
				cur = leftRotate(cur);
				cur.l = maintain(cur.l);
				cur = maintain(cur);
			} else if (rightLeftSize > leftSize) {
				cur.r = rightRotate(cur.r);
				cur = leftRotate(cur);
				cur.l = maintain(cur.l);
				cur.r = maintain(cur.r);
				cur = maintain(cur);
			}
			
			return cur;
		}
		
		private SBTNode insert(SBTNode root, int index, SBTNode cur) {
			if (root == null) {
				return cur;
			}
			
			root.size++;
			int leftAndHeadSize = (root.l != null ? root.l.size : 0) + 1;
			
			if (index < leftAndHeadSize) {
				root.l = insert(root.l, index, cur);
			} else {
				root.r = insert(root.r, index - leftAndHeadSize, cur);
			}
			
			return maintain(root);
		}
		
		private SBTNode get(SBTNode root, int index) {
			int leftSize = root.l != null ? root.l.size : 0;
			
			if (index < leftSize) {
				return get(root.l, index);
			} else if (index == leftSize) {
				return root;
			} else {
				return get(root.r, index - leftSize - 1);
			}
		}
		
		private void process(SBTNode head, LinkedList<Integer> indexes) {
			if (head == null) {
				return;
			}
			
			process(head.l, indexes);
			indexes.addLast(head.value);
			process(head.r, indexes);
		}
		
		public void insert(int index, int value) {
			SBTNode cur = new SBTNode(value);
			
			if (root == null) {
				root = cur;
			} else {
				if (index <= root.size) {
					root = insert(root, index, cur);
				}
			}
		}
		
		public int get(int index) {
			SBTNode ans = get(root, index);
			return ans.value;
		}
		
		public LinkedList<Integer> allIndexes() {
			LinkedList<Integer> indexes = new LinkedList<>();
			process(root, indexes);
			return indexes;
		}
	}
}
