package src.class29;

/**
 * 蓄水池算法实现 
 * 假设有一个源源吐出不同球的机器， 
 * 只有装下10个球的袋子，每一个吐出的球，要么放入袋子，要么永远扔掉
 * 如何做到机器吐出每一个球之后，所有吐出的球都等概率被放进袋子里
 */
public class Code04_ReservoirSampling {
	// solution
	public static class RandomBox {
		public int[] bag;
		public int N;
		public int count;
		
		public RandomBox(int capacity) {
			bag = new int[capacity];
			N = capacity;
			count = 0;
		}
		
		public int rand(int max) {
			return (int) (Math.random() * max) + 1;
		}
		
		public void add(int num) {
			count++;
			if (count <= N) {
				bag[count - 1] = num;
			} else {
				if (rand(count) <= N) {
					bag[rand(N) - 1] = num;
				}
			}
		}
		
		public int[] choices() {
			int[] ans = new int[N];
			
			for (int i = 0; i < N; i++) {
				ans[i] = bag[i];
			}
			
			return ans;
		}
 	}
	
	public static int random(int i) {
		return (int) (Math.random() * i) + 1;
	}
	
	// for test
	public static void test() {
		System.out.println("start");
		int testTimes = 10000;
		int ballNum = 17;
		int[] count = new int[ballNum + 1];
		
		for (int i = 0; i < testTimes; i++) {
			int[] bag = new int[10];
			int bagI = 0;
			
			for (int num = 1; num <= ballNum; num++) {
				if (num <= 10) {
					bag[bagI++] = num;
				} else {
					if (random(num) <= 10) {
						bagI = (int) (Math.random() * 10);
						bag[bagI] = num;
					}
				}
			}
			
			for (int num : bag) {
				count[num]++;
			}
		}
		
		for (int i = 0; i <= ballNum; i++) {
			System.out.println(count[i]);
		}
		
		System.out.println("---------");
		
		int all = 100;
		int choose = 10;
		testTimes = 50000;
		int[] counts = new int[all + 1];
		
		for (int i = 0; i < testTimes; i++) {
			RandomBox box = new RandomBox(choose);
			
			for (int num = 1; num <= all; num++) {
				box.add(num);
			}
			
			int[] ans = box.choices();
			
			for (int j = 0; j < ans.length; j++) {
				counts[ans[j]]++;
			}
		}
		
		for (int i = 0; i < counts.length; i++) {
			System.out.println(i + " times : " + counts[i]);
		}
	}
	
	public static void main(String[] args) {
		test();
	}

}
