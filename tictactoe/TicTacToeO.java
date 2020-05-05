package tictactoe;

/**
An example of tictactoe game, with the initial
game board looks like the following
 ___ ___ ___

| 1 | 2 | 3 |
 ___ ___ ___

| 4 | 5 | 6 | 
 ___ ___ ___

| 7 | 8 | 9 |
 ___ ___ ___
Game logic:
    //display the game board
    //obtain player choice
    //check for winner:if no winner, computer makes a move
    //check for winner: repeat the entire process if no winner


*/

public class TicTacToeO{
  public static void main(String[] args){
    //get the game board
    char[][] gameBoard = new char[3][3];
    initBoard(gameBoard);
    
    //display the game board
    displayGameBoard(gameBoard);

//COMPLETE THE FOLLOWING TASKS TO GET THE PROGRAM READY
    //obtain player choice
    //check for winner:if no winner, computer make a move
    //check for winner: repeat the entire process if no winner

  }//end of main

  /** 
    filling the 2D array with numeric character starting from 1
    @param: board - a two dimentional array representing the gameboard
  */
  private static void initBoard(char[][] board){
    char value = '0'; //starting with character '0'
    
    //for every row
    for(int i = 0; i < board.length; i++) {
       //and every colum of the row
       for(int j = 0; j < board[i].length; j++){
	  //put the next character sequence into the slot
          board[i][j] =  (char)++value; 
       }
     }
  }

  /** COMPLETE THIS METHOD SO THE DISPLAY WILL LOOK THE SAME AS DESIGNED 
   displaying the gameboard in 2D 
   @param: board - a two dimentional array representing the gameboard
  */  
  private static void displayGameBoard(char[][] board){
    System.out.println(" ___ ___ ___\n");
    //scanning over the rows
    for(int i = 0; i < board.length; i++){
       //scanning over the columns
       for(int j = 0;  j < board[i].length; j++){
          //display the value of the slot  
          System.out.print("| " + board[i][j] + " ");
       }
       System.out.println("|");
       System.out.println(" ___ ___ ___\n"); //break each row display 
       
    }
    System.out.println("\nINCOMPLETE - THE DISPLAY IS NOT READY\n");//it's ready
  }  

}//end of the class



