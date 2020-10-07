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
		if (board[index] != ' ') {
			System.out.println("Board position is not empty. Try again!");
			userMove(scannerObj, board, player);
		}
		board[index] = player;
		showBoard(board);
	}

	public static void main(String args[]) {
		Scanner scannerObj = new Scanner(System.in);
		char[] board = createBoard();
		char player = chooseCharacterForPlayer(scannerObj);
		char computer = (player == 'X') ? 'O' : 'X';
		System.out.println("Player chooses: " + player);
		System.out.println("Computer character: " + computer);
		showBoard(board);
		userMove(scannerObj, board, player);
	}

}
