package com.bridgelabz.tictactoe;

import java.util.Arrays;
import java.util.Scanner;

public class TicTacToeGame {
	public enum Player {
		USER, COMPUTER
	}

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
	public static int getUserIndex(Scanner scannerObj, char[] board) {
		System.out.println("Make your move.\nEnter index ( 1 to 9)");
		int index = scannerObj.nextInt();
		if (index < 1 || index > 9) {
			System.out.println("Invalid index! Enter again");
			index = scannerObj.nextInt();
		}
		if (!isFreeSpace(board, index)) {
			System.out.println("Board position is not empty. Try again!");
			getUserIndex(scannerObj, board);
		}
		return index;
	}

	// Method to make a move
	public static void makeMove(char[] board, int index, char player) {
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
		String userTossInput = scannerObj.next();
		int tossValue = (int) Math.floor(Math.random() * 10) % 2;
		String tossOutput = "";
		if (tossValue == 0)
			tossOutput = "Heads";
		else
			tossOutput = "Tails";
		System.out.println("Toss outcome: " + tossOutput);

		return tossOutput.equals(userTossInput);
	}

	public static Player whoStarts(Scanner scannerObj) {
		if (toss(scannerObj))
			return Player.USER;
		else
			return Player.COMPUTER;

	}

	// Winning position decider function
	public static boolean isWinningPosition(char[] board, char index) {
		if ((board[1] == index && board[2] == index && board[3] == index)
				|| (board[4] == index && board[5] == index && board[6] == index)
				|| (board[7] == index && board[8] == index && board[9] == index)
				|| (board[1] == index && board[5] == index && board[9] == index)
				|| (board[3] == index && board[5] == index && board[7] == index)
				|| (board[1] == index && board[4] == index && board[7] == index)
				|| (board[2] == index && board[5] == index && board[8] == index)
				|| (board[3] == index && board[6] == index && board[9] == index))
			return true;
		else
			return false;
	}

	public static void main(String args[]) {
		Scanner scannerObj = new Scanner(System.in);
		char[] board = createBoard();
		Player player = whoStarts(scannerObj);
		char user = chooseCharacterForPlayer(scannerObj);
		char computer = (user == 'X') ? 'O' : 'X';
		System.out.println("Player chooses: " + user);
		System.out.println("Computer character: " + computer);
		showBoard(board);

		while (true) {
			int userIndex = getUserIndex(scannerObj, board);
			makeMove(board, userIndex, user);
			if (isWinningPosition(board, user)) {
				System.out.println("Player wins");
				break;
			}
		}
		String currentPlayer = (player == Player.USER) ? "User" : "Computer";
		System.out.println("Current Player:- " + currentPlayer);

	}

}
