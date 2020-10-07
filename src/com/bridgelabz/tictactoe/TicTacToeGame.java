package com.bridgelabz.tictactoe;

import java.util.Arrays;
import java.util.Scanner;

public class TicTacToeGame {
	// Method to create board
	public static char[] createBoard() {
		char[] board = new char[10];
		Arrays.fill(board, ' ');
		return board;
	}

	// Method to allow player to choose character
	public static char chooseCharacterForPlayer(Scanner scannerObj) {
		System.out.println("Choose character: 'X' or 'O' ");
		return scannerObj.next().toUpperCase().charAt(0);
	}

	// Method to show board
	public static void showBoard(char[] board) {
		System.out.println("Board:");
		for (int position = 1; position < 10; position++) {
			System.out.print(board[position]);
			if (position % 3 == 0)
				System.out.println("");
			else
				System.out.print(" | ");
		}
	}

	// Method for user to make a move
	public static void userMove(Scanner scannerObj, char[] board, char player) {
		System.out.println("Make your move.\nEnter index ( 1 to 9)");
		int index = scannerObj.nextInt();
		if (index < 1 || index > 9) {
			System.out.println("Invalid index! Enter again");
			index = scannerObj.nextInt();
		}
		if (!isFreeSpace(board, index)) {
			System.out.println("Board position is not empty. Try again!");
			userMove(scannerObj, board, player);
		}
		board[index] = player;
		showBoard(board);
	}

	// Method to check free space
	public static boolean isFreeSpace(char[] board, int index) {
		return board[index] == ' ';
	}

	// Method for toss
	public static boolean toss(Scanner scannerObj) {
		System.out.println("Choose Heads or Tails");
		Scanner scannerObject = new Scanner(System.in);
		String userTossInput = scannerObject.nextLine();
		int tossValue = (int) Math.floor(Math.random() * 10) % 2;
		String tossOutput = "";
		if (tossValue == 0)
			tossOutput = "Heads";
		else
			tossOutput = "Tails";
		System.out.println("Toss outcome: " + tossOutput);
		scannerObject.close();
		return tossOutput.equals(userTossInput);
	}

	public static void main(String args[]) {
		Scanner scannerObj = new Scanner(System.in);
		char[] board = createBoard();
		char player = chooseCharacterForPlayer(scannerObj);
		char computer = (player == 'X') ? 'O' : 'X';
		System.out.println("Player chooses: " + player);
		System.out.println("Computer character: " + computer);
		showBoard(board);
		if (toss(scannerObj)) {
			// User Wins Toss
			userMove(scannerObj, board, player);
		}
	}

}
