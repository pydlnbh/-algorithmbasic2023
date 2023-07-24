package src.class38;

/**
 * int[] d，d[i]：i号怪兽的能力
 * int[] p，p[i]：i号怪兽要求的钱
 * 开始时你的能力是0，你的目标是从0号怪兽开始，通过所有的怪兽。
 * 如果你当前的能力，小于i号怪兽的能力，你必须付出p[i]的钱，贿赂这个怪兽，然后怪兽就会加入你
 * 他的能力直接累加到你的能力上；如果你当前的能力，大于等于i号怪兽的能力
 * 你可以选择直接通过，你的能力并不会下降，你也可以选择贿赂这个怪兽，然后怪兽就会加入你
 * 他的能力直接累加到你的能力上
 * 返回通过所有的怪兽，需要花的最小钱数
 * （课上会给出不同的数据量描述）
 */
public class Code04_MoneyProblem {
	// solution one
	public static long fun1(int[] d, int[] p) {
		return process1(d, p, 0, 0);
	}
	
	public static long process1(int[] d, int[] p, int ability, int index) {
		if (index == d.length) {
			return 0;
		}
		
		if (ability < d[index]) {
			return p[index] + process1(d, p, ability + d[index], index + 1);
		} else {
			return Math.min(
			    p[index] + process1(d, p, ability + d[index], index + 1),
			    process1(d, p, ability, index + 1));
		}
	}
	
	// solution two
	public static long fun2(int[] d, int[] p) {
		int sum = 0;
		for (int num : d) {
			sum += num;
		}
		
		long[][] dp = new long[d.length + 1][sum + 1];
		
		for (int cur = d.length - 1; cur >= 0; cur--) {
			for (int hp = 0; hp <= sum; hp++) {
				if (hp + d[cur] > sum) {
					continue;
				}
				
				if (hp < d[cur]) {
					dp[cur][hp] = p[cur] + dp[cur + 1][hp + d[cur]];
				} else {
					dp[cur][hp] = Math.min(p[cur] + dp[cur + 1][hp + d[cur]],
					    dp[cur + 1][hp]);
				}
			}
		}
		
		return dp[0][0];
	}
	
	// solution three
	public static int minMoney2(int[] d, int[] p) {
		int allMoney = 0;
		
		for (int i = 0; i < p.length; i++) {
			allMoney += p[i];
		}
		
		int N = d.length;
		
		for (int money = 0; money < allMoney; money++) {
			if (process2(d, p, N - 1, money) != -1) {
				return money;
			}
		}
		
		return allMoney;
	}
	
	// 从0....index号怪兽，花的钱，必须严格==money
    // 如果通过不了，返回-1
    // 如果可以通过，返回能通过情况下的最大能力值
	public static long process2(int[] d, int[] p, int index, int money) {
		if (index == -1) {
			return money == 0 ? 0 : -1;
		}
		
		long preMaxAbility = process2(d, p, index - 1, money);
		long p1 = -1;
		
		if (preMaxAbility != -1 && preMaxAbility >= d[index]) {
			p1 = preMaxAbility;
		}
		
		long preMaxAbility2 = process2(d, p, index - 1, money - p[index]);
		long p2 = -1;
		if (preMaxAbility2 != -1) {
			p2 = d[index] + preMaxAbility2;
		}
		
		return Math.max(p1, p2);
	}
	
	// solution four
	public static long fun3(int[] d, int[] p) {
		int sum = 0;
		for (int num : p) {
			sum += num;
		}
		
		// dp[i][j]含义：
		// 能经过0～i的怪兽，且花钱为j（花钱的严格等于j）时的武力值最大是多少？
		// 如果dp[i][j]==-1，表示经过0～i的怪兽，花钱为j是无法通过的，或者之前的钱怎么组合也得不到正好为j的钱数
		int[][] dp = new int[d.length][sum + 1];
		for (int i = 0; i < d.length; i++) {
			for (int j = 0; j <= sum; j++) {
				dp[i][j] = -1;
			}
		}
		
		// 经过0～i的怪兽，花钱数一定为p[0]，达到武力值d[0]的地步。其他第0行的状态一律是无效的
		dp[0][p[0]] = d[0];
		for (int i = 1; i < d.length; i++) {
			for (int j = 0; j <= sum; j++) {
				// 可能性一，为当前怪兽花钱
				// 存在条件：
				// j - p[i]要不越界，并且在钱数为j - p[i]时，要能通过0～i-1的怪兽，并且钱数组合是有效的。
				if (j >= p[i] && dp[i - 1][j - p[i]] != -1) {
					dp[i][j] = dp[i - 1][j - p[i]] + d[i];
				}
				
				// 可能性二，不为当前怪兽花钱
				// 存在条件：
				// 0~i-1怪兽在花钱为j的情况下，能保证通过当前i位置的怪兽
				if (dp[i - 1][j] >= d[i]) {
					// 两种可能性中，选武力值最大的
					dp[i][j] = Math.max(dp[i][j], dp[i - 1][j]);
				}
			}
		}
		
		int ans = 0;
		// dp表最后一行上，dp[N-1][j]代表：
		// 能经过0～N-1的怪兽，且花钱为j（花钱的严格等于j）时的武力值最大是多少？
		// 那么最后一行上，最左侧的不为-1的列数(j)，就是答案
		for (int j = 0; j <= sum; j++) {
			if (dp[d.length - 1][j] != -1) {
				ans = j;
				break;
			}
		}
		
		return ans;
	}
	
	// for test
	public static int[][] generateRandomArray(int len, int value) {
		int size = (int) (Math.random() * len) + 1;
		int[][] arrs = new int[2][size];
		
		for (int i = 0; i < size; i++) {
			arrs[0][i] = (int) (Math.random() * value) + 1;
			arrs[1][i] = (int) (Math.random() * value) + 1;
		}
		
		return arrs;
	}
	
	// for test
	public static void test() {
		int len = 10;
		int value = 20;
		int testTimes = 10000;
		
		System.out.println("start");
		for (int i = 0; i < testTimes; i++) {
			int[][] arrs = generateRandomArray(len, value);
			int[] d = arrs[0];
			int[] p = arrs[1];
			
			long ans1 = fun1(d, p);
			long ans2 = fun2(d, p);
			long ans3 = minMoney2(d, p);
			long ans4 = fun3(d, p);
			
			if (ans1 != ans2 
			    || ans2 != ans3 
			    || ans3 != ans4) {
				System.out.println("Oops");
				break;
			}
		}
		System.out.println("end");
	}
	
	// main
	public static void main(String[] args) {
		test();
	}
}
