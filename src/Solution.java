package src;

import java.util.*;

public class Solution {
	// Randomly generate a question number from the total number of questions learned
	private static void randomQuestionNo() {
		try (Scanner sc = new Scanner(System.in)) {			
			System.out.print("一共学习了几道题: ");
			System.out.print("你今天要做的题目是: " + ((int) (Math.random() * sc.nextInt()) + 1));
		}
	}

	// main
	public static void main(String[] args) {
		randomQuestionNo();
	}

	// Talk is cheap.Show me your code
	// TODO
	
}
