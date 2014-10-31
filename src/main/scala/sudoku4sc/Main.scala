package sudoku4sc

import sudoku4sc.Sudoku._

object Main extends App {

  // Returns a completely blank Sudoku board
  def allBlankSudoku: Sudoku = {
    val myBoard = Vector.fill(9, 9)(0)
    new Sudoku(myBoard)
  }

  // Returns a solved valid Sudoku board
  def solvedSudoku: Sudoku = {
    val solvedBoard = Vector(Vector(4,1,7,3,6,9,8,2,5),
                             Vector(6,3,2,1,5,8,9,4,7),
                             Vector(9,5,8,7,2,4,3,1,6),
                             Vector(8,2,5,4,3,7,1,6,9),
                             Vector(7,9,1,5,8,6,4,3,2),
                             Vector(3,4,6,9,1,2,7,5,8),
                             Vector(2,8,9,6,4,3,5,7,1),
                             Vector(5,7,3,2,9,1,6,8,4),
                             Vector(1,6,4,8,7,5,2,9,3))
    new Sudoku(solvedBoard)
  }

}
