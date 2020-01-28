package sudoku;

import java.util.*;

class GFG 
{ 
public static boolean isSafe(int[][] board,  
                             int row, int col,  
                             int num)  
{ 
    // row has the unique (row-clash) 
    for (int d = 0; d < board.length; d++)  
    { 
        // if the number we are trying to  
        // place is already present in  
        // that row, return false; 
        if (board[row][d] == num)  
        { 
            return false; 
        } 
    } 
      
    // column has the unique numbers (column-clash) 
    for (int r = 0; r < board.length; r++) 
    { 
        // if the number we are trying to 
        // place is already present in 
        // that column, return false; 
  
        if (board[r][col] == num) 
        { 
            return false; 
        } 
    } 
  
    // corresponding square has 
    // unique number (box-clash) 
    int sqrt = (int) Math.sqrt(board.length); 
    int boxRowStart = row - row % sqrt; 
    int boxColStart = col - col % sqrt; 
  
    for (int r = boxRowStart; 
             r < boxRowStart + sqrt; r++)  
    { 
        for (int d = boxColStart;  
                 d < boxColStart + sqrt; d++)  
        { 
            if (board[r][d] == num)  
            { 
                return false; 
            } 
        } 
    } 
  
        // if there is no clash, it's safe 
    return true; 
} 
  
public static boolean solveSudoku(int[][] board, int n)  
{ 
    int row = -1; 
    int col = -1; 
    boolean isEmpty = true; 
    for (int i = 0; i < n; i++) 
    { 
        for (int j = 0; j < n; j++)  
        { 
            if (board[i][j] == 0)  
            { 
                row = i; 
                col = j; 
                  
                // we still have some remaining 
                // missing values in Sudoku 
                isEmpty = false;  
                break; 
            } 
        } 
        if (!isEmpty) 
        { 
            break; 
        } 
    } 
  
    // no empty space left 
    if (isEmpty)  
    { 
        return true; 
    } 
  
    // else for each-row backtrack 
    for (int num = 1; num <= n; num++) 
    { 
        if (isSafe(board, row, col, num)) 
        { 
            board[row][col] = num; 
            if (solveSudoku(board, n))  
            { 
                // print(board, n); 
                return true; 
            }  
            else
            { 
                board[row][col] = 0; // replace it 
            } 
        } 
    } 
    return false; 
} 
  
public static void print(int[][] board, int N) 
{ 
    // we got the answer, just print it 
    for (int r = 0; r < N; r++) 
    { 
        for (int d = 0; d < N; d++) 
        { 
            System.out.print(board[r][d]); 
            if(d==2||d==5)
            	System.out.print("|");
            else
            	System.out.print(" ");
        } 
       if(r==2||r==5)
    	   System.out.println("\n-----|-----|-----");
       else
    	   System.out.println(" ");
          
        if ((r + 1) % (int) Math.sqrt(N) == 0)  
        { 
            System.out.print(""); 
        } 
    } 
} 
  
// Driver Code 
public static void main(String args[]) 
{ 
  
    int[][] board = new int[9][9]; 
    Scanner s= new Scanner(System.in);
    System.out.println("enter the puzzle");
    for(int i=0;i<9;i++)
    	for(int j=0;j<9;j++)
    		board[i][j]=s.nextInt();
    System.out.println("\n\nthe solution is\n\n");
    int N = board.length; 
  
    if (solveSudoku(board, N)) 
    { 
        print(board, N); // print solution 
    }  
    else
    { 
        System.out.println("No solution"); 
    } 
} 
} 
  
