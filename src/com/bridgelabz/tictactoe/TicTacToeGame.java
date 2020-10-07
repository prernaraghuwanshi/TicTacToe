package com.bridgelabz.tictactoe;

import java.util.Arrays;

public class TicTacToeGame {
	
	public static char[] board;
	
	public static void createBoard()
	{
		board = new char[10];
		Arrays.fill(board, ' ');
		
	}
	public static void main(String args[]) 
	{
		createBoard();
	}

}
