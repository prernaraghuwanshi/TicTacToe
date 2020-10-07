package com.bridgelabz.tictactoe;

import java.util.Arrays;
import java.util.Scanner;

public class TicTacToeGame {
	static Scanner sc = new Scanner(System.in);
	
	//Method to create board
	public static char[] createBoard() {
		char[] board = new char[10];
		Arrays.fill(board, ' ');
		return board;
	}	
	//Method to allow player to choose character
	public static char chooseCharacterForPlayer() {
		System.out.println("Choose character: 'X' or 'O' ");
		char userChar = sc.nextLine().charAt(0);
		return userChar;
	}	
	//Method to show board
	public static void showBoard(char[] board)
	{
		for (int position = 1; position < 10; position++) {
			System.out.print(board[position]);
			if (position % 3 == 0)
				System.out.println("");
			else
				System.out.print(" | ");
		}
	}

	public static void main(String args[]) {
		char[] board = createBoard();
		char player = chooseCharacterForPlayer();
		char computer = (player == 'X') ? 'O' : 'X';
		System.out.println("Player chooses: "+ player);
		System.out.println("Computer character: "+ computer);
		showBoard(board);
	}

}
