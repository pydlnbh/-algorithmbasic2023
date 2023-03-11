package src.class09;

public class Code03_SmallerEqualBigger {

	public static class Node {
		public int value;
		public Node next;

		public Node(int v) {
			value = v;
		}
	}
	
	public static Node listPartition1(Node head, int pivot) {
		if (head == null) {
			return head;
		}
		
		Node cur = head;
		int i = 0;
		while (cur != null) {
			i++;
			cur = cur.next;
		}
		
		Node[] nodeArr = new Node[i];
		cur = head;
		for (i = 0; i < nodeArr.length; i++) {
			nodeArr[i] = cur;
			cur = cur.next;
		}
		
		partition(nodeArr, pivot);
		
		for (i = 1; i < nodeArr.length; i++) {
			nodeArr[i - 1].next = nodeArr[i];
		}
		
		nodeArr[i - 1].next = null;
		
		return nodeArr[0];
	}
	
	public static void partition(Node[] nodeArr, int pivot) {
		int lessL = -1;
		int more = nodeArr.length;
		int index = 0;
		
		while (index < more) {
			if (nodeArr[index].value == pivot) {
				index++;
			} else if (nodeArr[index].value < pivot) {
				swap(nodeArr, index++, ++lessL);
			} else {
				swap(nodeArr, index, --more);
			}
		}
	}
	
	public static void swap(Node[] nodeArr, int i, int j) {
		Node t = nodeArr[i];
		nodeArr[i] = nodeArr[j];
		nodeArr[j] = t;
	}

	public static Node listPartition2(Node head, int pivot) {
		Node sH = null;
		Node sT = null;
		Node eH = null;
		Node eT = null;
		Node mH = null;
		Node mT = null;
		Node next = null;
		
		while (head != null) {
			next = head.next;
			head.next = null;
			
			if (head.value < pivot) {
				if (sH == null) {
					sH = head;
					sT = head;
				} else {
					sT.next = head;
					sT = head;
				}
			} else if (head.value == pivot) {
				if (eH == null) {
					eH = head;
					eT = head;
				} else {
					eT.next = head;
					eT = head;
				}
			} else {
				if (mH == null) {
					mH = head;
					mT = head;
				} else {
					mT.next = head;
					mT = head;
				}
			}
			
			head = next;
		}
		
		if (sT != null) {
			sT.next = eH;
			eT = eT == null ? sT : eT;
		}
		
		if (eT != null) {
			eT.next = mH;
		}
		
		return sH != null ? sH : (eH != null ? eH : mH); 
	}
	
	public static void printLinkedList(Node head) {
		System.out.println("Linked List: ");
		while (head != null) {
			System.out.print(head.value + " ");
			head = head.next;
		}
		System.out.println();
	}

	/**
	 * 对数器
	 */
	public static void test() {
		Node head1 = new Node(7);
		head1.next = new Node(9);
		head1.next.next = new Node(1);
		head1.next.next.next = new Node(8);
		head1.next.next.next.next = new Node(5);
		head1.next.next.next.next.next = new Node(2);
		head1.next.next.next.next.next.next = new Node(5);
		printLinkedList(head1);
	    head1 = listPartition1(head1, 5);
//		head1 = listPartition2(head1, 5);
		printLinkedList(head1);
	}
	
	/**
	 * main方法
	 * 
	 * @param args 标准入参
	 */
	public static void main(String[] args) {
		test();
	}
	
}
