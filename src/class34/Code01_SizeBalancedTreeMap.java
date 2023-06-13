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
			} else if (rightLeftSize > leftSize) {
				cur.r = rightRotate(cur.r);
				cur = leftRotate(cur);
				cur.l = maintain(cur.l);
				cur.r = maintain(cur.r);
				cur = maintain(cur);
			}
			
			return cur;
		}
		
		private SBTNode<K, V> findLastIndex(K key) {
			SBTNode<K, V> pre = root;
			SBTNode<K, V> cur = root;
			
			while (cur != null) {
				pre = cur;
				if (key.compareTo(cur.k) == 0) {
					break;
				} else if (key.compareTo(cur.k) < 0) {
					cur = cur.l;
				} else {
					cur = cur.r;
				}
			}
			
			return pre;
		}
		
		private SBTNode<K, V> findLastNoSmallIndex(K key) {
			SBTNode<K, V> ans = null;
			SBTNode<K, V> cur = root;
			
			while (cur != null) {
				if (key.compareTo(cur.k) == 0) {
					ans = cur;
					break;
				} else if (key.compareTo(cur.k) < 0) {
					cur = cur.l;
				} else {
					ans = cur;
					cur = cur.r;
				}
			}
			
			return ans;
		}
		
		private SBTNode<K, V> findLastNoBigIndex(K key) {
			SBTNode<K, V> ans = null;
			SBTNode<K, V> cur = root;
			
			while (cur != null) {
				if (key.compareTo(cur.k) == 0) {
					ans = cur;
					break;
				} else if (key.compareTo(cur.k) < 0) {
					ans = cur;
					cur = cur.l;
				} else {
					cur = cur.r;
				}
			}
			
			return ans;
		}
		
		private SBTNode<K, V> add(SBTNode<K, V> cur, K key, V value) {
			if (cur == null) {
				return new SBTNode<>(key, value);
			} else {
				cur.size++;
				if (key.compareTo(cur.k) < 0) {
					cur.l = add(cur.l, key, value);
				} else {
					cur.r = add(cur.r, key, value);
				}
			}
			
			return maintain(cur);
 		}
		
		private SBTNode<K, V> delete(SBTNode<K, V> cur, K key) {
			cur.size--;
			if (key.compareTo(cur.k) > 0) {
				cur.r = delete(cur.r, key);
			} else if (key.compareTo(cur.k) < 0) {
				cur.l = delete(cur.l, key);
			} else {
				if (cur.l == null && cur.r == null) {
					cur = null;
				} else if (cur.l != null && cur.r == null) {
					cur = cur.l;
				} else if (cur.l == null && cur.r != null){
					cur = cur.r;
				} else {
					SBTNode<K, V> pre = null;
					SBTNode<K, V> des = cur.r;
					des.size--;
					while (des.l != null) {
						pre = des;
						des = des.l;
						des.size--;
					}
					
					if (pre != null) {
						pre.l = des.r;
						des.r = cur.r;
					}
					
					des.l = cur.l;
					des.size = des.l.size + (des.r == null ? 0 : des.r.size) + 1;
					cur = des;
				}
			}
			
			return cur;
		}
		
		private SBTNode<K, V> getIndex(SBTNode<K, V> cur, int kth) {
			if (kth == (cur.l != null ? cur.l.size : 0) + 1) {
				return cur;
			} else if (kth <= (cur.l != null ? cur.l.size : 0)) {
				return getIndex(cur.l, kth);
			} else {
				return getIndex(cur.r, kth - (cur.l != null ? cur.l.size : 0) - 1);
			}
		}
		
		public int size() {
			return root == null ? 0 : root.size;
		}
		
		public boolean containsKey(K key) {
			if (key == null) {
				return false;
			}
			
			SBTNode<K, V> lastNode = findLastIndex(key);
			return lastNode != null && key.compareTo(lastNode.k) == 0 ? true : false;
		}
		
		public void put(K key, V value) {
			if (key == null) {
				return;
			}
			
			SBTNode<K, V> lastNode = findLastIndex(key);
			if (lastNode != null && key.compareTo(lastNode.k) == 0) {
				lastNode.v = value;
			} else {
				root = add(root, key, value);
			}
		}
		
		public void remove(K key) {
			if (key == null) {
				return;
			}
			
			if (containsKey(key)) {
				root = delete(root, key);
			}
		}
		
		public K getIndexKey(int index) {
			if (index < 0 || index >= this.size()) {
				return null;
			}
			
			return getIndex(root, index + 1).k;
		}
		
		public V getIndexValue(int index) {
			if (index < 0 || index >= this.size()) {
				return null;
			}
			
			return getIndex(root, index + 1).v;
		}
		
		public V get(K key) {
			if (key == null) {
				return null;
			}
			
			SBTNode<K, V> lastNode = findLastIndex(key);
			if (lastNode != null && lastNode.k.compareTo(key) == 0) {
				return lastNode.v;
			}
			
			return null;
		}
		
		public K firstKey() {
			if (root == null) {
				return null;
			}
			
			SBTNode<K, V> ans = root;
			
			while (ans.l != null) {
				ans = ans.l;
			}
			
			return ans == null ? null : ans.k;
		}
		
		public K lastKey() {
			if (root == null) {
				return null;
			}
			
			SBTNode<K, V> ans = root;
			
			while (ans.r != null) {
				ans = ans.r;
			}
			
			return ans == null ? null : ans.k;
		}
		
		public K floorKey(K key) {
			if (key == null) {
				return null;
			}
			
			SBTNode<K, V> lastNoBigNode = findLastNoBigIndex(key);
			
			return lastNoBigNode == null ? null : lastNoBigNode.k;
		}
		
		public K ceilingKey(K key) {
			if (key == null) {
				return null;
			}
			
			SBTNode<K, V> lastNoSmallNode = findLastNoSmallIndex(key);
			
			return lastNoSmallNode == null ? null : lastNoSmallNode.k;
		}
	}
}
