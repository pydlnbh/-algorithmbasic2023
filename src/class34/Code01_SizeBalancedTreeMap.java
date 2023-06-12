package src.class34;

// size-balanced-tree实现
public class Code01_SizeBalancedTreeMap {
	// auxiliary class
	public static class SBTNode<K extends Comparable<K>, V> {
		public K k;
		public V v;
		public SBTNode<K, V> l;
		public SBTNode<K, V> r;
		public int size;
		
		public SBTNode(K key, V value) {
			k = key;
			v = value;
			l = null;
			r = null;
			size = 1;
		}
	}
	
	// solution
	public static class SizeBalancedTreeMap<K extends Comparable<K>, V> {
		private SBTNode<K, V> root;
		
		public SizeBalancedTreeMap() {
			root = null;
		}
		
		private SBTNode<K, V> rightRotate(SBTNode<K, V> cur) {
			SBTNode<K, V> left = cur.l;
			cur.l = left.r;
			left.r = cur;
			left.size = cur.size;
			cur.size = (cur.l != null ? cur.l.size : 0)
			    + (cur.r != null ? cur.r.size : 0) + 1;
			return left;
		}
		
		private SBTNode<K, V> leftRotate(SBTNode<K, V> cur) {
			SBTNode<K, V> right = cur.r;
			cur.r = right.l;
			right.l = cur;
			right.size = cur.size;
			cur.size = (cur.l != null ? cur.l.size : 0)
			    + (cur.r != null ? cur.r.size : 0) + 1;
			return right;
		}
		
		private SBTNode<K, V> maintain(SBTNode<K, V> cur) {
			if (cur == null) {
				return null;
			}
			
			int leftSize = cur.l != null ? cur.l.size : 0;
			int leftLeftSize = cur.l != null && cur.l.l != null ? cur.l.l.size : 0;
			int leftRightSize = cur.l != null && cur.l.r != null ? cur.l.r.size : 0;
			
			int rightSize = cur.r != null ? cur.r.size : 0;
			int rightLeftSize = cur.r != null && cur.r.l != null ? cur.r.l.size : 0;
			int rightRightSize = cur.r != null && cur.r.r != null ? cur.r.r.size : 0;
			
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
			} else {
				
			}
		}
	}
}
