package com.bridgelabz.tictactoe;

import java.util.Arrays;
import java.util.Scanner;

public class TicTacToeGame {
	public static char[] board;
	static Scanner sc = new Scanner(System.in);
	public static char PLAYER;
	public static char COMPUTER;
	//Method to create board
	public static char[] createBoard() {
		board = new char[10];
		Arrays.fill(board, ' ');
		return board;
	}
	
	//Method to allow player to choose character
	public static char chooseCharacterForPlayer() {
		System.out.println("Choose character: 'X' or 'O' ");
		char userChar = sc.nextLine().charAt(0);
		return userChar;
	}

	public static void main(String args[]) {
		char[] board = createBoard();
		PLAYER = chooseCharacterForPlayer();
		System.out.println(PLAYER);
	}

}
