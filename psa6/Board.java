/* File Header:
 * This file contains methods that setup a board for 
 * a new game of 2048, load a previous game of 2048, add 
 * random tiles to the board, save the current game progress,
 * move the tiles, and end the game. Tha
 * is file also contains
 * getters for this class.
 * 
 * Mark Choe
 * A13917840
 * mychoe@ucsd.edu
 * 
 * Class Header:
 * The purpose of this class is to allow for the game to be 
 * setup and played.
 */

/**
 * Sample Board
 * <p/>
 * 0   1   2   3
 * 0   -   -   -   -
 * 1   -   -   -   -
 * 2   -   -   -   -
 * 3   -   -   -   -
 * <p/>
 * The sample board shows the index values for the columns and rows
 * Remember that you access a 2D array by first specifying the row
 * and then the column: grid[row][column]
 */

import java.util.*;
import java.io.*;

public class Board {
    public final int NUM_START_TILES = 2; 
    public final int TWO_PROBABILITY = 90;
    public final int GRID_SIZE;


    private final Random random; // a reference to the Random object, passed in 
                                 // as a parameter in Boards constructors
    private int[][] grid;  // a 2D int array, its size being boardSize*boardSize
    private int score;     // the current score, incremented as tiles merge 


    // TODO PSA3
    // Constructs a fresh board with random tiles
    // Input: a random object and the size of the board
    // Return: a new board with 2 filled in tiles
    public Board(Random random, int boardSize) {
        this.random = random;
        //Setting GRID_SIZE
        GRID_SIZE = boardSize;
        //Creating the 2D array
        grid = new int[boardSize][boardSize];
        for (int x = 0; x < boardSize; x++){
           for (int y = 0; y < boardSize; y++){
              grid[x][y] = 0;
           }
        }
        //Adding 2 random tiles
        for (int index = 0; index < NUM_START_TILES; index++){
        addRandomTile();
        }
    }

    // TODO PSA3
    // Construct a board based off of an input file
    // assume board is valid
    // Input: a random object and a string from an inputted board
    // Return: the inputted board
    public Board(Random random, String inputBoard) throws IOException {
        this.random = random;
        //Opening the file
        File file = new File(inputBoard);
        //Reading the file
        Scanner scanner = new Scanner(file);
        // The Grid Size
        GRID_SIZE = Integer.parseInt(scanner.next());
        // Making the grid
        grid = new int[GRID_SIZE][GRID_SIZE];
        // The Score
        score = Integer.parseInt(scanner.next());
        // The Tiles
        for (int x = 0; x < GRID_SIZE; x++){
           for (int y = 0; y < GRID_SIZE; y++){
              grid[x][y] = Integer.parseInt(scanner.next());
           }
        }
    }

    // TODO PSA3
    // Saves the current board to a file
    // Input: the name of the file that will have the saved game
    // Return: N/A
    public void saveBoard(String outputBoard) throws IOException {
       // Creating the File and PrintWriter Objects
       File file = new File(outputBoard);
       PrintWriter myWriter = new PrintWriter(file);
       // Saving the grid size, the score, and the tiles
       myWriter.print(GRID_SIZE);
       myWriter.println();
       myWriter.print(score);
       myWriter.println();
       for (int x = 0; x < GRID_SIZE; x++){
         for (int y = 0; y < GRID_SIZE; y++){
           myWriter.print(grid[x][y]);
           myWriter.print(" ");
         }
         myWriter.println();
       }       
       // Closing the stream
       myWriter.close();
    }

    // TODO PSA3
    // Adds a random tile (of value 2 or 4) to a
    // random empty space on the board
    // Input: N/A
    // Return: N/A
    public void addRandomTile(){
       //Setting up the counts
       int count = 0;
       int count2 = -1;
       //Counting the empty tiles
       for (int x = 0; x < GRID_SIZE; x++){
          for (int y = 0; y < GRID_SIZE; y++){
             if (grid[x][y] == 0){
                count++;
             }
          }
       }
       //If the board is full
       if (count == 0){
          return;
       }
       //Getting random integers for the location and value
       int location = random.nextInt(count);
       int value = random.nextInt(100);
       //Choosing a random empty tile
       for (int x = 0; x < GRID_SIZE; x++){
          for (int y = 0; y < GRID_SIZE; y++){
             if (grid[x][y] == 0){
                count2++;
             }
             if (count2 == location){
                if (value < TWO_PROBABILITY){
                   grid[x][y] = 2;
                } else {
                   grid[x][y] = 4;
                }
                return;
             }
          }
       }

    }

    // TODO PSA3
    // determins whether the board can move in a certain direction
    // return true if such a move is possible
    // Input: the direction to be checked
    // Return: "true" if it is possible to move in this direction;
    // "false" if it is not possible
    public boolean canMove(Direction direction){
       // Check if moving down is possible
       if (direction == Direction.DOWN){
          for (int x = 0; x < GRID_SIZE - 1; x++){
             for (int y = 0; y < GRID_SIZE; y++){
                if (grid[x][y] > 0 && (grid[x+1][y] == 0 ||
                   grid[x][y] == grid[x+1][y])){
                   return true;
                }
             }
          }
       }
       // Check if moving up is possible
       if (direction ==  Direction.UP){
          for (int x = 1; x < GRID_SIZE; x++){
             for (int y = 0; y < GRID_SIZE; y++){
                if (grid[x][y] > 0 && (grid[x-1][y] == 0 ||
                   grid[x][y] == grid[x-1][y])){
                   return true;
                }
             }
          }
       }
       // Check if moving right is possible
       if (direction == Direction.RIGHT){
          for (int x = 0; x < GRID_SIZE; x++){
             for (int y = 0; y < GRID_SIZE-1; y++){
                if (grid[x][y] > 0 && (grid[x][y+1] == 0 ||
                   grid[x][y] == grid[x][y+1])){
                   return true;
                }
             }
          }
       }
       // Check if moving left is possible
       if (direction == Direction.LEFT){
          for (int x = 0; x < GRID_SIZE; x++){
             for (int y = 1; y < GRID_SIZE; y++){
                if (grid[x][y] > 0 && (grid[x][y-1] == 0 ||
                   grid[x][y] == grid[x][y-1])){
                   return true;
                }
             }
          }
       }
        return false;
    }

    // TODO PSA3
    // move the board in a certain direction
    // Input: the direction the tiles are to be moved
    // Return: return true if such a move is successful
    // return false if such a move is not possible
    public boolean move(Direction direction) {
       // 
       if (canMove(direction)==false){
         return false;
       }else{
         // Moving Left
         if (direction == Direction.LEFT){
           // Combining
           for (int x = 0; x < GRID_SIZE; x++){
             for (int y = 0; y < GRID_SIZE-1; y++){
               if (grid[x][y] == grid[x][y+1]){
                 grid[x][y] = grid[x][y]+grid[x][y+1];
                 grid[x][y+1] = 0;
                 score = score + grid[x][y];
               } else if (grid[x][y+1] == 0){
                 grid[x][y+1] = grid[x][y];
                 grid[x][y] = 0;
               }
             }
           }
           // Shifting
           for (int x = 0; x < GRID_SIZE; x++){
             for (int y = 0; y < GRID_SIZE-1; y++){
               if (grid[x][y] == 0 && grid[x][y+1] != 0){
                 grid[x][y] = grid[x][y+1];
                 grid[x][y+1] = 0;
                 y=-1;
               }
             }
           }
         }
         // Moving Right
         if (direction == Direction.RIGHT){
           // Combining
           for (int x = 0; x < GRID_SIZE; x++){
             for (int y = GRID_SIZE-1; y > 0; y--){
               if (grid[x][y] == grid[x][y-1]){
                 grid[x][y] = grid[x][y]+grid[x][y-1];
                 grid[x][y-1] = 0;
                 score = score + grid[x][y];
               } else if (grid[x][y-1] == 0){
                 grid[x][y-1] = grid[x][y];
                 grid[x][y] = 0;
               }
             }
           }
           // Shifting
           for (int x = 0; x < GRID_SIZE; x++){
             for (int y = GRID_SIZE-1; y > 0; y--){
               if (grid[x][y] == 0 && grid[x][y-1] != 0){
                 grid[x][y] = grid[x][y-1];
                 grid[x][y-1] = 0;
                 y= GRID_SIZE;
               }
             }
           }
         }
         // Moving Up
         if (direction == Direction.UP){
           // Combining
           for (int y = 0; y < GRID_SIZE; y++){
             for (int x = 0; x < GRID_SIZE-1; x++){
               if (grid[x][y] == grid[x+1][y]){
                 grid[x][y] = grid[x][y]+grid[x+1][y];
                 grid[x+1][y] = 0;
                 score = score + grid[x][y];
               } else if (grid[x+1][y] == 0){
                 grid[x+1][y] = grid[x][y];
                 grid[x][y] = 0;
               }
             }
           }
           // Shifting
           for (int y = 0; y < GRID_SIZE; y++){
             for (int x = 0; x < GRID_SIZE-1; x++){
               if (grid[x][y] == 0 && grid[x+1][y] != 0){
                 grid[x][y] = grid[x+1][y];
                 grid[x+1][y] = 0;
                 x= -1;
               }
             }
           }
         }
         // Moving Down
         if (direction == Direction.DOWN){
           // Combining
           for (int y = 0; y < GRID_SIZE; y++){
             for (int x = GRID_SIZE-1; x > 0; x--){
               if (grid[x][y] == grid[x-1][y]){
                 grid[x][y] = grid[x][y]+grid[x-1][y];
                 grid[x-1][y] = 0;
                 score = score + grid[x][y];
               } else if (grid[x-1][y] == 0){
                 grid[x-1][y] = grid[x][y];
                 grid[x][y] = 0;
               }
             }
           }
           // Shifting
           for (int y = 0; y < GRID_SIZE; y++){
             for (int x = GRID_SIZE-1; x > 0; x--){
               if (grid[x][y] == 0 && grid[x-1][y] != 0){
                 grid[x][y] = grid[x-1][y];
                 grid[x-1][y] = 0;
                 x= GRID_SIZE;
               }
             }
           }
         }
         return true;
       }
    }
    
    // No need to change this for PSA3
    // Check to see if we have a game over
    // Input: N/A
    // Return: N/A
    public boolean isGameOver() {
      if (canMove(Direction.LEFT) == false &&
          canMove(Direction.RIGHT) == false &&
          canMove(Direction.DOWN) == false &&
          canMove(Direction.UP) == false){
        return true;
      }
      return false;
    }
    
    // Return the reference to the 2048 Grid
    public int[][] getGrid() {
      return grid;
    }
    
    // Return the score
    public int getScore() {
      return score;
    }

    @Override
    public String toString() {
        StringBuilder outputString = new StringBuilder();
        outputString.append(String.format("Score: %d\n", score));
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int column = 0; column < GRID_SIZE; column++)
                outputString.append(grid[row][column] == 0 ? "    -" :
                        String.format("%5d", grid[row][column]));

            outputString.append("\n");
        }
        return outputString.toString();
    }
}
