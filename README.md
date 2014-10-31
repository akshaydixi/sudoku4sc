sudoku4sc
---------

sudoku4sc currently supports Sudoku boards of the form
```
1,2,3,4,5,6,7,8,9
1,2,3,4,5,6,7,8,9
1,2,3,4,5,6,7,8,9
1,2,3,4,5,6,7,8,9
1,2,3,4,0,6,7,8,9
1,2,3,4,5,6,7,8,9
1,2,3,4,5,6,7,8,9
1,2,3,4,5,6,7,8,9
1,2,3,4,5,6,7,8,9
```

where 1-9 denote the value of each cell, and 0 denotes an empty cell.

Usage
-------
To solve a Sudoku board, you can use the `solveFromFile` function as shown below
to read a Sudoku board from a file and save the solution as another file

```
import sudoku4sc.Sudoku._
solveFromFile('mySudokuBoard.csv','mySolution.csv')
```
