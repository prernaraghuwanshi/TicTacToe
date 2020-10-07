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

	// Method for user to select an index
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
	}

	// Method to check free space
	public static boolean isFreeSpace(char[] board, int index) {
		return board[index] == ' ';
	}

	public static Player whoStarts(Scanner scannerObj) {
		int toss = (int) Math.floor(Math.random() * 10 % 2);
		return (toss == 1) ? Player.USER : Player.COMPUTER;

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

	// Winning move
	public static int isWinningMove(char[] board, char player) {
		for (int position = 1; position < board.length; position++) {
			char[] copyOfBoard = board.clone();
			if (isFreeSpace(copyOfBoard, position))
				makeMove(copyOfBoard, position, player);
			if (isWinningPosition(copyOfBoard, player))
				return position;
		}
		return 0;
	}

	// Method for computer move
	public static int getComputerMove(char[] board, char computer, char user) {
		int winMove = isWinningMove(board, computer);
		if (winMove != 0)
			return winMove;
		int userWinMove = isWinningMove(board, user);
		if (userWinMove != 0)
			return userWinMove;
		return 0;
	}

	public static void main(String args[]) {
		Scanner scannerObj = new Scanner(System.in);
		char[] board = createBoard();
		char user = chooseCharacterForPlayer(scannerObj);
		char computer = (user == 'X') ? 'O' : 'X';
		System.out.println("Player chooses: " + user);
		System.out.println("Computer character: " + computer);
		showBoard(board);

		Player p = whoStarts(scannerObj);
		System.out.println("Starts first:" + p);
		int index = getUserIndex(scannerObj, board);
		makeMove(board, index, user);
		showBoard(board);
		int compMove = getComputerMove(board, computer, user);
		makeMove(board, compMove, computer);
		showBoard(board);

	}
}
