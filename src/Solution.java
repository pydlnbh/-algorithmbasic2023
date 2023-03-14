package src;

import java.util.Scanner;

public class Solution {
	/**
	 * 随机复习
	 */
	public static void main(String[] args) {
		try (Scanner sc = new Scanner(System.in)) {			
			System.out.print("一共学习了几道题: ");
			System.out.print("你今天要做的题目是: " + ((int) (Math.random() * sc.nextInt()) + 1));
		}
	}
}
