package src;

import java.util.Scanner;

/**
 * 随机题目
 */
public class Solution {
	/**
	 * main
	 * 
	 * @param args 标准入参
	 */
	public static void main(String[] args) {
		randomPractice();
	}
	
	/**
	 * 随机练习题目
	 */
	public static void randomPractice() {
		try (Scanner sc = new Scanner(System.in)) {
			System.out.print("一共学习了几节课: ");
			int iCalss = sc.nextInt();
			
			System.out.println("你今天要练习的课是: " + ((int) (Math.random() * iCalss) + 1));
			
			System.out.print("一节课有几道题: ");
			int iCode = sc.nextInt();
			System.out.println("你今天要做的题目是: " + ((int) (Math.random() * iCode) + 1));
		}
	}
	
	
	
}
