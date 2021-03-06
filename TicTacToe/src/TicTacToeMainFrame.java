import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;

public class TicTacToeMainFrame {
	
	static HashSet<Integer> positionsFilled = new HashSet<>();
	
	// print the gameBoard
	public static void printGameBoard(char[][] ticTacToeGameBoard) {
		System.out.println("\n");
		for(int i=0;i<ticTacToeGameBoard[0].length;i++) {
			for(int j=0;j<ticTacToeGameBoard[1].length;j++) {
				System.out.print(ticTacToeGameBoard[i][j]);
			}
			System.out.println();
		}
	}
	
	// update player positions
	public static void updatePlayerPositions(char[][] ticTacToeGameBoard, int position, char player) {
		
		switch(position) {
			case 1:
				ticTacToeGameBoard[0][0] = player;
				break;
			case 2:
				ticTacToeGameBoard[0][2] = player;
				break;
			case 3:
				ticTacToeGameBoard[0][4] = player;
				break;
			case 4:
				ticTacToeGameBoard[2][0] = player;
				break;
			case 5:
				ticTacToeGameBoard[2][2] = player;
				break;
			case 6:
				ticTacToeGameBoard[2][4] = player;
				break;
			case 7:
				ticTacToeGameBoard[4][0] = player;
				break;
			case 8:
				ticTacToeGameBoard[4][2] = player;
				break;
			case 9:
				ticTacToeGameBoard[4][4] = player;
				break;
			default:
				System.out.println("Enter Correct Choice: ");
				Scanner scanObj = new Scanner(System.in);
				isPositionAvailable(ticTacToeGameBoard, scanObj.nextInt(), player);
				break;
		}
	}
	
	// Check if the position is available. If yes, update the position
	public static void isPositionAvailable(char[][] ticTacToeGameBoard, int pos, char player) {
		
		if(!positionsFilled.contains(pos)) { // base condition
			positionsFilled.add(pos);
			updatePlayerPositions(ticTacToeGameBoard, pos, player);
			return;
		}
		int input = -1;
		if(player == 'X') {
			System.out.println("Position "+pos+" already filled. Choose another valid option");
			System.out.println("Player "+player+" :: Enter your choice : ");
			Scanner scanObj = new Scanner(System.in);
			input = scanObj.nextInt();
		} else {
			// Doing this because the random function can choose an already filled position and I don't want a prompt for it.
			input = cpuPositionUpdate();
		}
		
		isPositionAvailable(ticTacToeGameBoard, input, player);
	}
	
	// CPU Position Update
	public static int cpuPositionUpdate() {
		Random rand = new Random();
		int cpuPos = rand.nextInt(9) + 1;
		return cpuPos;
	}
	
	// check for the winner positions
	public static boolean checkWinner(char[][] ticTacToeGameBoard, char symbol) {
		
		int winSum = 0;
		if(symbol == 'X') {
			winSum = 264; //3*88 => where 88 is the ASCII value of 'X'
		} else {
			winSum = 237; //3*79 => where 79 is the ASCII value of 'O'
		}
		
		// condition for a winner
		if( ticTacToeGameBoard[0][0]+ticTacToeGameBoard[0][2]+ticTacToeGameBoard[0][4] == winSum ||   // topRow
			ticTacToeGameBoard[2][0]+ticTacToeGameBoard[2][2]+ticTacToeGameBoard[2][4] == winSum ||   // midRow
			ticTacToeGameBoard[4][0]+ticTacToeGameBoard[4][2]+ticTacToeGameBoard[4][4] == winSum ||   // bottomRow
			ticTacToeGameBoard[0][0]+ticTacToeGameBoard[2][0]+ticTacToeGameBoard[4][0] == winSum ||   // leftCol
			ticTacToeGameBoard[0][2]+ticTacToeGameBoard[2][2]+ticTacToeGameBoard[4][2] == winSum ||   // midCol
			ticTacToeGameBoard[0][4]+ticTacToeGameBoard[2][4]+ticTacToeGameBoard[4][4] == winSum ||   // rightCol
			ticTacToeGameBoard[0][0]+ticTacToeGameBoard[2][2]+ticTacToeGameBoard[4][4] == winSum ||	  // leftTopBottomRightDiagonal
			ticTacToeGameBoard[0][4]+ticTacToeGameBoard[2][2]+ticTacToeGameBoard[4][0] == winSum 	  // topRigthBottomLeftDiagonal
		) {
				System.out.println("\nCongratulations : "+symbol+" won");
				return true;
		} else if(positionsFilled.size() == 9) {
			System.out.println("\nGame Tied !");
			return true;
		}
		return false;
	}
	
	// main entry to the program
	public static void main(String[] args) throws IOException {
		
		char player1 = 'X';
		char cpuPlayer = 'O';
		boolean gameEnd = false;
		char[][] ticTacToeGameBoard = new char[][] {
			{' ','|',' ','|',' '},
			{'-','+','-','+','-'},
			{' ','|',' ','|',' '},
			{'-','+','-','+','-'},
			{' ','|',' ','|',' '}
		};
		
		printGameBoard(ticTacToeGameBoard);

		while(true) {
			Scanner scan = new Scanner(System.in);
			System.out.println("Player X :: Enter a position (1-9): ");
			// Player 1 chance
			int player1Pos = scan.nextInt();
			
			isPositionAvailable(ticTacToeGameBoard, player1Pos, player1);
			printGameBoard(ticTacToeGameBoard);
			gameEnd = checkWinner(ticTacToeGameBoard, player1);
			if(gameEnd) break;
			// CPU chance
			int cpuPos = cpuPositionUpdate();
			isPositionAvailable(ticTacToeGameBoard, cpuPos, cpuPlayer);
			printGameBoard(ticTacToeGameBoard);
			gameEnd = checkWinner(ticTacToeGameBoard, cpuPlayer);
			if(gameEnd) break;
			
			System.out.println("\n ___________________ \n ");
			
			// things remaining : 
			/* 1. Check if the user enters the valid position on the gameBoard
			 * 		Position > 9 are invalid
			 * 		Handling for non-integer inputs from the user 
			 * 
			 * 2. Make CPU choices intelligent - Game Theory
			 * 
			 * */
			
			//scan.close();
		}

	}

}
