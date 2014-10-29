package sudoku4sc

import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers

class SudokuTest extends FlatSpec with ShouldMatchers {
  val unsolvedBoard = new Sudoku("src/main/resources/unsolved1.csv")
  val solvedBoard = new Sudoku("src/main/resources/solved1.csv")
  val emptyBoard = new Sudoku("src/main/resources/empty.csv")
  val disfiguredBoard = new Sudoku("src/main/resources/disfigured1.csv")

  "Sudoku" should "read from csv files properly" in {
    emptyBoard.board should be (Main.allBlankSudoku.board)
  }

  "Sudoku" should "implement isSudoku properly" in {
    unsolvedBoard.isSudoku should be (true)
    solvedBoard.isSudoku should be (true)
    disfiguredBoard.isSudoku should be (false)
  }

  "Sudoku" should "implement isSolved properly" in {
    unsolvedBoard.isSolved should be (false)
    solvedBoard.isSolved should be (true)
  }


}
