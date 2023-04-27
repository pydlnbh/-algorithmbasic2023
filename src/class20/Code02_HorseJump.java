package src.class20;

/**
 * 请同学们自行搜索或者想象一个象棋的棋盘， 然后把整个棋盘放入第一象限，棋盘的最左下角是(0,0)位置
 * 那么整个棋盘就是横坐标上9条线、纵坐标上10条线的区域 给你三个 参数 x，y，k 返回“马”从(0,0)位置出发，必须走k步
 * 最后落在(x,y)上的方法数有多少种?
 */
public class Code02_HorseJump {
	// solution one
	public static int jump1(int x, int y, int k) {
		return process1(0, 0, k, x, y);
	}
	
	// recursive method one
	public static int process1(int a, int b, int rest, int x, int y) {
		if (a < 0 || a > 9 || b < 0 || b > 8) {
			return 0;
		}
		
		if (rest == 0) {
			return (a == x && b == y) ? 1 : 0;
		}
		
		int ways = process1(a + 2, b + 1, rest - 1, x, y);
		ways += process1(a + 1, b + 2, rest - 1, x, y);
		ways += process1(a - 1, b + 2, rest - 1, x, y);
		ways += process1(a - 2, b + 1, rest - 1, x, y);
		ways += process1(a - 2, b - 1, rest - 1, x, y);
		ways += process1(a - 1, b - 2, rest - 1, x, y);
		ways += process1(a + 1, b - 2, rest - 1, x, y);
		ways += process1(a + 2, b - 1, rest - 1, x, y);
		
		return ways;
	}
	
	// solution two
	public static int jump2(int x, int y, int k) {
		int[][][] dp = new int[10][9][k + 1];
		dp[x][y][0] = 1;
		
		for (int rest = 1; rest <= k; rest++) {
			for (int i = 0; i < 10; i++) {
				for (int j = 0; j < 9; j++) {
					int ways = pick(dp, i + 2, j + 1, rest - 1);
					ways += pick(dp, i + 1, j + 2, rest - 1);
					ways += pick(dp, i - 1, j + 2, rest - 1);
					ways += pick(dp, i - 2, j + 1, rest - 1);
					ways += pick(dp, i - 2, j - 1, rest - 1);
					ways += pick(dp, i - 1, j - 2, rest - 1);
					ways += pick(dp, i + 1, j - 2, rest - 1);
					ways += pick(dp, i + 2, j - 1, rest - 1);
					dp[i][j][rest] = ways;
				}
			}
		}
		
		return dp[0][0][k];
	}
	
	// pick method
	public static int pick(int[][][] dp, int x, int y, int rest) {
		if (x < 0 || x > 9 || y < 0 || y > 8) {
			return 0;
		}
		
		return dp[x][y][rest];
	}
	
	// main
	public static void main(String[] args) {
		int x = 7;
		int y = 7;
		int step = 10;
		int ans1 = jump1(x, y, step);
		System.out.println(ans1);
		
		int ans2 = jump2(x, y, step);
		System.out.println(ans2);
	}
}
