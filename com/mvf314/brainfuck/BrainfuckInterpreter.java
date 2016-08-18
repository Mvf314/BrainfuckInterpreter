package com.mvf314.brainfuck;

import java.io.IOException;
import java.util.Stack;

public class BrainfuckInterpreter {

	int[] array;
	String[] fileContents;
	int ptr;
	Stack<Integer> stack;

	public BrainfuckInterpreter() {
		array = new int[256];
		ptr = 0;
		stack = new Stack<Integer>();
	}

	public void printStatus(int start, int end) {
		System.out.print("\nCell\t| ");
		for (int i = start; i <= end; i++) {
			System.out.print(i < 10 ? " 00" + i + " " : i < 100 ? " 0" + i + " " : " " + i + " ");
		}
		System.out.print("\nValue\t| ");
		for (int i = start; i <= end; i++) {
			System.out.print(array[i] < 10 ? " 00" + array[i] + " " : array[i] < 100 ? " 0" + array[i] + " " : " " + array[i] + " ");
		}
	}

	public void run(String path) {
		try {
			fileContents = FileIO.read(path, "");
			for (int i = 0; i < fileContents.length; i++) {
				if (fileContents[i].equals(">")) {
					if (ptr++ > array.length - 1) {
						System.err.println("Pointer out of bounds. Terminating program.");
						System.exit(0);
					}
				} else if (fileContents[i].equals("<")) {
					if (ptr-- < 0) {
						System.err.println("Pointer out of bounds. Terminating program.");
						System.exit(0);
					}
				} else if (fileContents[i].equals("+")) {
					if (array[ptr]++ > 254) {
						array[ptr] = 0;
					}
				} else if (fileContents[i].equals("-")) {
					if (array[ptr]-- <= 0) {
						array[ptr] = 255;
					}
				} else if (fileContents[i].equals(".")) {
					System.out.print((char) array[ptr]);
				} else if (fileContents[i].equals(",")) {

				} else if (fileContents[i].equals("[")) {
					if (array[ptr] == 0) {
						//int amountInnerLoops = 0;
						for (int j = i; j < fileContents.length; j++) {
							/*if (fileContents[j].equals("[")) {
								amountInnerLoops++;
							} else if (fileContents[j].equals("]") && amountInnerLoops > 0) {
								amountInnerLoops--;
							} else */if (fileContents[j].equals("]")/* && amountInnerLoops == 0*/) {
								i = j;
							}
						}
					}
					stack.push(i);
				} else if (fileContents[i].equals("]")) {
					i = stack.get(stack.size() - 1) - 1;
				}
			}
			System.out.println("\nProgram finished running successfully.");
		} catch (IOException e) {
			System.out.println("It looks like the file " + path + " doesn't exist. Please check the file path.");
		}
	}

	public static void main(String[] args) {
		BrainfuckInterpreter interpreter = new BrainfuckInterpreter();
		interpreter.run("/Users/Melvin/Documents/Programming/brainfuck/compact/hello.bf");
	}

}