// package tictactoe;

// /**
// An example of tictactoe game, with the initial
// game board looks like the following
//  ___ ___ ___

// | 1 | 2 | 3 |
//  ___ ___ ___

// | 4 | 5 | 6 | 
//  ___ ___ ___

// | 7 | 8 | 9 |
//  ___ ___ ___
// Game logic:
//     //display the game board
//     //obtain player choice
//     //check for winner:if no winner, computer makes a move
//     //check for winner: repeat the entire process if no winner


//  */
// import java.util.Scanner; 
// public class TicTacToe{
// 	public static void main(String[] args){
// 		//get the game board
// 		char[][] gameBoard = new char[3][3];
// 		initBoard(gameBoard);
// 		char turn = 'X';
// 		Scanner kb = new Scanner(System.in);
// 		Scanner keyboard = new Scanner(System.in);
// 		String answer;

		

// 		//3. check for winner or draw




// 		//COMPLETE THE FOLLOWING TASKS TO GET THE PROGRAM READY
// 		//obtain player choice
// 		//check for winner:if no winner, computer makes a move
// 		//check for winner: repeat the entire process if no winner

// 	}//end of main
	
// 	public static void play(char gameBoard[][]){
// 		boolean playing = true;
// //		PrintBoard();
// 		while(playing){
// 			System.out.println("Please enter a row and column: ");
// 			Scanner scan = new Scanner(System.in);
// 			int row = scan.nextInt()-1;
// 			int col = scan.nextInt()-1;
// 			char turn;
// 			gameBoard[row][col] = turn;
// 			if(GameOver(row,col)){
// 				playing = false;
// 				System.out.println("Game over :( Player" +turn+ "wins!");
// 			}

// //			PrintBoard();
// 			if(turn == 'X')
// 				turn = '0';
// 			else
// 				turn = 'X';
// 		}
// 	}

// 	public static boolean gameOver(int pMove, int cMove, char gameBoard[][]){
// 		if(gameBoard[0][cMove] == gameBoard[2][cMove]){
// 			return true;
// 		}
// 		if(gameBoard[pMove][0] == gameBoard[pMove][2]
// 				&& gameBoard[pMove][0] == gameBoard[pMove][2]){
// 					return true;
// 				}
// 				if(gameBoard[0][0]==gameBoard[1][1] && gameBoard[0][0] == gameBoard[2][2]
// 						&& gameBoard[1][1] != '_'){
// 					return true; 
// 				}
// 				if(gameBoard[0][2]==gameBoard[1][1] && gameBoard[0][2] == gameBoard[2][0]
// 						&& gameBoard[1][1] != '_'){
// 					return true;
// 				}
// 				return false;
// 	}

// 	/** 
//     filling the 2D array with numeric character starting from 1
//     @param: board - a two dimentional array representing the gameboard
// 	 */
// 	private static void initBoard(char[][] board){
// 		char value = '0'; //starting with character '0'

// 		//for every row
// 		for(int i = 0; i < board.length; i++) {
// 			//and every colum of the row
// 			for(int j = 0; j < board[i].length; j++){
// 				//put the next character sequence into the slot
// 				board[i][j] =  (char)++value; 
// 			}
// 		}
// 	}

// 	/** COMPLETE THIS METHOD SO THE DISPLAY WILL LOOK THE SAME AS DESIGNED 
//    displaying the gameboard in 2D 
//    @param: board - a two dimentional array representing the gameboard
// 	 */  
// 	private static void displayGameBoard(char[][] board){

// 		//scanning over the rows
// 		for(int i = 0; i < board.length; i++){
// 			//scanning over the columns
// 			for(int j = 0;  j < board[i].length; j++){
// 				//display the value of the slot  
// 				System.out.print("|" + board[i][j] + "|");
// 			} 
// 			System.out.println("\n"); //break each row display 
// 		}
// 		System.out.println("\nINCOMPLETE - THE DISPLAY IS NOT READY\n");
// 	}  

// }//end of the class



