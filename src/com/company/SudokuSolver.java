package com.company;

//import

import java.util.Scanner;

//class where algorithm takes place
class SolveSudoku {

    boolean solveSudoku(int[][] mat, int l) {
    /*Takes a partially filled-in grid and attempts to assign values to all
        unassigned locations in such a way to meet the requirements for Sudoku
        solution (non-duplication across rows, columns, and boxes). The function
        operates via recursive backtracking: first it Searches the grid to find
        an entry that is still unassigned. If found, the reference parameters row,
        col will be set the location that is unassigned, and true is returned. If
        no unassigned entries remain, false is returned and then it finds an
        unassigned location with the grid and then considers all digits from 1 to
        9 in a loop. If a digit is found that has no existing conflicts,
        tentatively assign it and recur to attempt to fill in rest of grid. If
        this was successful, the puzzle is solved. If not, unmake that decision
        and try again. If all digits have been examined and none worked out,
        return false to backtrack to previous decision point.*/
        int row = -1, col = -1;
        boolean isEmpty = true;
        for (int i = 0; i < l; i++) {
            for (int j = 0; j < l; j++) {
                if (mat[i][j] == 0) {
                    row = i;
                    col = j;
                    isEmpty = false;
                    break;
                }
            }
            if (!isEmpty) {
                break;
            }
        }
        if (isEmpty) {
            return true;
        }

        for (int num = 1; num <= 9; num++) {
            if (noConflicts(mat, row, col, num)) {
                mat[row][col] = num;
                if (solveSudoku(mat, l))
                    return true;
                int unassigned = 0;
                mat[row][col] = unassigned;
            }
        }
        return false;
    }

    private boolean noConflicts(int[][] mat, int row, int col, int num) {
    /*Returns a boolean which indicates whether it will be legal to assign
        num to the given row,col location. As assignment is legal if it that
        number is not already used in the row, col, or box.*/
        return (!usedInRow(mat, row, num) && !usedInCol(mat, col, num) &&
                !usedInBox(mat, row - (row % 3), col - (col % 3), num));
    }

    private boolean usedInRow(int[][] mat, int row, int num) {
    /*Returns a boolean which indicates whether any assigned entry
        in the specified row matches the given number.*/
        for (int col = 0; col < 9; col++)
            if (mat[row][col] == num)
                return true;
        return false;
    }

    private boolean usedInCol(int[][] mat, int col, int num) {
    /*Returns a boolean which indicates whether any assigned entry
        in the specified column matches the given number.*/
        for (int row = 0; row < 9; row++)
            if (mat[row][col] == num)
                return true;
        return false;
    }

    private boolean usedInBox(int[][] mat, int boxStartRow, int boxStartCol, int num) {
    /*Returns a boolean which indicates whether any assigned entry
    within the specified 3x3 box matches the given number.*/
        for (int row = 0; row < 3; row++)
            for (int col = 0; col < 3; col++)
                if (mat[row + boxStartRow][col + boxStartCol] == num)
                    return true;
        return false;
    }

    void printSudoku(int[][] mat, int l) {
        System.out.println("After solving the Sudoko Here is the result: ");
        System.out.println(" ______________________ ");
        for (int i = 0; i < l; i++) {
            System.out.print("|");
            for (int j = 0; j < l; j++) {
                System.out.print(mat[i][j] + " ");
                if ((j + 1) % 3 == 0)
                    System.out.print("| ");
            }
            System.out.println();
            if ((i + 1) % 3 == 0)
                System.out.println(" _______________________ ");
        }
    }
}

//driver class
public class SudokuSolver {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        SolveSudoku ss = new SolveSudoku();
        int n = 9;
        char ch, ch1;
        int[][] mat = new int[n][n];

        /* Use this block when testing
            Comment the User scanner input for array elements section
            int[][] mat = new int[][]
               {{3, 0, 6, 5, 0, 8, 4, 0, 0},
                {5, 2, 0, 0, 0, 0, 0, 0, 0},
                {0, 8, 7, 0, 0, 0, 0, 3, 1},
                {0, 0, 3, 0, 1, 0, 0, 8, 0},
                {9, 0, 0, 8, 6, 3, 0, 0, 5},
                {0, 5, 0, 0, 9, 0, 6, 0, 0},
                {1, 3, 0, 0, 0, 0, 2, 5, 0},
                {0, 0, 0, 0, 0, 0, 0, 7, 4},
                {0, 0, 5, 2, 0, 6, 3, 0, 0}};*/
    /*for debugging or testing
3 0 6 5 0 8 4 0 0
5 2 0 0 0 0 0 0 0
0 8 7 0 0 0 0 3 1
0 0 3 0 1 0 0 8 0
9 0 0 8 6 3 0 0 5
0 5 0 0 9 0 6 0 0
1 3 0 0 0 0 2 5 0
0 0 0 0 0 0 0 7 4
0 0 5 2 0 6 3 0 0
    */

        do {
            do {
                //Entering elements of array from user
                System.out.println("Enter 9*9 Sudoku only: ");
                for (int i = 0; i < 9; i++)
                    for (int j = 0; j < 9; j++)
                        mat[i][j] = sc.nextInt();

                //Showing elements of array for user
                System.out.println("You have entered the following values: ");
                System.out.println(" ______________________ ");
                for (int i = 0; i < 9; i++) {
                    System.out.print("|");
                    for (int j = 0; j < 9; j++) {
                        System.out.print(mat[i][j] + " ");
                        if ((j + 1) % 3 == 0)
                            System.out.print("| ");
                    }
                    System.out.println();
                    if ((i + 1) % 3 == 0)
                        System.out.println(" ______________________ ");
                }
                System.out.println("Enter Y to input values again" +
                        "\npress any other key to continue: ");
                ch = sc.next().charAt(0);
            } while (ch == 'y' || ch == 'Y');
            int l = mat.length;
            if (ss.solveSudoku(mat, l)) {
                ss.printSudoku(mat, l);
            } else
                System.out.println("No Solution");
            System.out.println("Enter Y to run the program again" +
                    "press any other key to continue: ");
            ch1 = sc.next().charAt(0);
        } while (ch1 == 'y' || ch1 == 'Y');
        sc.close();
    }
}

