package com.bridgelabz.tictactoe;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class TicTacToeGame {
	public enum Player {
		USER, COMPUTER
	}

	public enum GameStatus {
		WIN, TIE, CHANGE_TURN
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
		while (true) {
			System.out.println("Make your move.\nEnter index ( 1 to 9)");
			int index = scannerObj.nextInt();
			if (index < 1 || index > 9) {
				System.out.println("Invalid index! Enter again");
			} else if (!isFreeSpace(board, index)) {
				System.out.println("Board position is not empty. Try again!");
			} else
				return index;
		}
	}

	// Method to make a move
	public static void makeMove(char[] board, int index, char player) {
		if (isFreeSpace(board, index))
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
		int cornerMove = getCornerMove(board);
		if (cornerMove != 0)
			return cornerMove;
		if (isFreeSpace(board, 5))
			return 5;
		int sideMove = getSideMove(board);
		if (sideMove != 0)
			return sideMove;
		return 0;
	}

	// Corner move
	public static int getCornerMove(char[] board) {
		int[] corners = { 1, 3, 7, 9 };

		for (int index = 0; index < corners.length; index++) {
			if (isFreeSpace(board, corners[index])) {
				return corners[index];
			}
		}
		return 0;
	}

	// Side move
	public static int getSideMove(char[] board) {
		int[] sides = { 2, 4, 6, 8 };

		for (int index = 0; index < sides.length; index++) {
			if (isFreeSpace(board, sides[index])) {
				return sides[index];
			}
		}
		return 0;
	}

	// Method to check game status
	public static GameStatus getGameStatus(char[] board, int index, char charOfPlayer, Player player) {
		makeMove(board, index, charOfPlayer);
		if (isWinningPosition(board, charOfPlayer) && player == Player.USER) {
			showBoard(board);
			System.out.println("Player wins!");
			return GameStatus.WIN;
		}
		if (isWinningPosition(board, charOfPlayer) && player == Player.COMPUTER) {
			showBoard(board);
			System.out.println("Computer wins!");
			return GameStatus.WIN;
		}

		if (isBoardFull(board)) {
			showBoard(board);
			System.out.println("It's a tie");
			return GameStatus.TIE;

		}
		return GameStatus.CHANGE_TURN;
	}

	// Method to check if board is filled i.e. tie
	public static boolean isBoardFull(char[] board) {
		for (int index = 1; index < board.length; index++) {
			if (isFreeSpace(board, index))
				return false;
		}
		return true;
	}

	public static void main(String args[]) {
		Scanner scannerObj = new Scanner(System.in);
		int playMore = 1;
		while (playMore == 1) {
			char[] board = createBoard();
			char user = chooseCharacterForPlayer(scannerObj);
			char computer = (user == 'X') ? 'O' : 'X';
			System.out.println("Player chooses: " + user);
			System.out.println("Computer character: " + computer);
			Player player = whoStarts(scannerObj);
			System.out.println("Starts first:" + player);
			boolean isGameOn = true;
			GameStatus gameStatus;
			while (isGameOn) {
				if (player == Player.USER) {
					int userMove = getUserIndex(scannerObj, board);
					gameStatus = getGameStatus(board, userMove, user, player);
					if (gameStatus == GameStatus.WIN || gameStatus == GameStatus.TIE)
						break;
					System.out.println("Board after USER's move");
					showBoard(board);
					player = Player.COMPUTER;
				} else {
					int computerMove = getComputerMove(board, computer, user);
					gameStatus = getGameStatus(board, computerMove, computer, player);
					if (gameStatus == GameStatus.WIN || gameStatus == GameStatus.TIE)
						break;

					System.out.println("Board after COMPUTER's move");
					showBoard(board);
					player = Player.USER;
				}
				if (gameStatus == GameStatus.CHANGE_TURN)
					continue;
			}
			System.out.println("For Another game: Press 1 \nFor Exiting: Press 2");
			playMore = scannerObj.nextInt();
			if (playMore == 2) {
				System.out.println("EXITING!");
				return;
			}
		}
	}
}
