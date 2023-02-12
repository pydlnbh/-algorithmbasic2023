package src.class02;

/**
 * 怎么把一个int类型的数，提取出二进制中最右侧的1来
 * 
 * @author peiyiding
 *
 */
public class Code03_RightOne {

	/**
	 * 怎么把一个int类型的数，提取出二进制中最右侧的1来
	 * 
	 * @param num int类型的数
	 * @return int 最右侧的1 
	 */
	public static int rightOneBit(int num) {
		int ans = 0;
		
		for (int i = 0; i < 32; i++) {
			if ((num & (1 << i)) != 0) {
				ans |= (1 << i);
				break;
			}
		}
		
		return ans;
	}
	
	/**
	 * 把一个int类型的数，提取出二进制中最右侧的1来(简洁写法)
	 * 
	 * @param num int类型的数
	 * @return int 最右侧的1
	 */
	public static int rightOneBitSimple(int num) {
		return num & (~num + 1);
	}
	
	/**
	 * 对数器
	 */
	public static void test() {
		int range = 100;
		int testTimes = 10000;
		boolean printFlag = false;
		
		System.out.println("start");
		
		for (int i = 0; i < testTimes; i++) {
			int num = ((int) (Math.random() * range) + 1) - ((int) (Math.random() * range) + 1);
					
		    // int ans1 = rightOneBit(num);
			int ans1 = rightOneBitSimple(num);
			int ans2 = testRightOneBit(num);
			
			if (Math.random() < 0.5 && printFlag) {
				System.out.println("num = " + num + ", ans1 = " + ans1 + ", ans2 = " + ans2);
			}
			
			if (ans1 != ans2) {
				System.out.println("Oops");
				break;
			}
		}
		
		System.out.println("end");
	}
	
	/**
	 * 对数器测试方法
	 * 
	 * @param num 数值
	 * @return int num最右侧的1
	 */
	public static int testRightOneBit(int num) {
		int ans = 0;
		int[] arr = new int[32];
		
		for (int i = 0; i < 32; i++) {
			arr[i] += num & (1 << i);
		}
		
		for (int i = 0; i < 32; i++) {
			if (arr[i] != 0) {
				ans |= (1 << i);
				break;
			}
		}
		
		return ans;
	}
	
	/**
	 * main
	 * 
	 * @parm args 标准入参
	 */
	public static void main(String[] args) {
		test();
	}
}
