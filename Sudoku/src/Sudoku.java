/**
 *
 * @purpose: This program takes in a file from the user that hold an unsolved sudkoku puzzle.
 * the program then uses several checking methods and a solve method to completely solve and
 * print out the solved sudoku puzzle.
 *
 * @bugs: none
 * @author Jacob Kleeburg
 * @course: CSCI 340
 * @date: May 4th 2018
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Sudoku
    {
        private static int[][] grid; // Global variable used to hold the original puzzle

        // Sucoku method is used to fill the grid with the original puzzle to be solved
        public Sudoku(String fileName) throws FileNotFoundException
        {
            grid = new int[9][9];
            Scanner puzzle = new Scanner(new File(fileName));
            for (int row = 0; row < grid.length; row++)
            {
                for (int col = 0; col < grid[0].length; col++)
                {
                    grid[row][col] = puzzle.nextInt();
                }
            }
        }

        // Solve method is a recursive method that is used to solve the sudoku puzzle
        // this method uses recursive backtracking to completely solve the puzzle
        public boolean solve()
        {
            for(int row=0;row<9;row++)
            {
                for(int col=0;col<9;col++)
                {
                    if(grid[row][col]==0)
                    {
                        for(int number=1;number<=9;number++)
                        {
                            if(verify(row, col, number))
                            {
                                grid[row][col] = number;
                                if(solve())
                                {
                                    return true;
                                }
                                else
                                {
                                    grid[row][col] = 0;
                                }
                            }
                        }
                        return false;
                    }
                }
            }
            return true;
        }

        // CheckRow method is used to check for a number in a row
        // and return true or false
        private boolean checkRow(int row,int number)
        {
            for(int i=0;i<9;i++)
            {
                if(grid[row][i]==number)
                {
                    return true;
                }
            }
            return false;
        }

        // CheckCol method is used to check for a number in a column
        // and returns true or false
        private boolean checkCol(int col,int number)
        {
            for(int i=0;i<9;i++)
            {
                if(grid[i][col]==number)
                {
                    return true;
                }
            }
            return false;
        }

        // CheckBox method is used to check for a number in the 3x3 box
        // and returns true or false
        private boolean checkBox(int row, int col,int number)
        {
            int r = row - row%3;
            int c = col - col%3;
            for(int i=r;i<r+3;i++)
            {
                for(int j=c;j<c+3;j++)
                {
                    if(grid[i][j]==number)
                    {
                        return true;
                    }
                }

            }
            return false;
        }

        // Verify method is used to quickly return true or false for all 3 of the checking methods
        private boolean verify(int row, int col,int number)
        {
            return !(checkRow(row, number) || checkCol(col, number) || checkBox(row, col, number));
        }

        // Printing method used to print out a 9x9 sudoku puzzle
        public static void print()
        {
            for (int row = 0; row < 9; row++)
            {
                for (int col = 0; col < 9; col++)
                    System.out.print(grid[row][col] + " ");
                System.out.println();
            }
        }

        // Main method asks the user for the name of a sudoku file and
        // sends the file to the solve algorithm. This method then sends the
        // solved puzzle to the printing method.
        public static void main(String[] args) throws FileNotFoundException
        {
            Scanner in = new Scanner(System.in);
            System.out.println("Please enter name of file ");
            Sudoku test = new Sudoku(in.next());
            // Solve method
            test.solve();
            // Print method
            test.print();
        }

    }
