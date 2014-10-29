package sudoku4sc

import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers
import scala.util.Random

class SudokuTest extends FlatSpec with ShouldMatchers {
  val unsolvedBoard = new Sudoku("src/main/resources/unsolved1.csv")
  val solvedBoard = new Sudoku("src/main/resources/solved1.csv")
  val emptyBoard = new Sudoku("src/main/resources/empty.csv")
  val disfiguredBoard = new Sudoku("src/main/resources/disfigured1.csv")
  val random = new Random(System.currentTimeMillis)

  def cell: Int = {
    if (random.nextInt(100) > 10) random.nextInt(9) + 1
    else 0
  }

  def arbitrarySudoku: Sudoku = {
    val randomBoard = Vector.fill(9,9)(cell)
    new Sudoku(randomBoard)
  }

  "Sudoku" should "read from csv files properly" in {
    emptyBoard.board should be (Main.allBlankSudoku.board)
  }

  "Sudoku" should "implement isSudoku properly" in {
    unsolvedBoard.isSudoku should be (true)
    solvedBoard.isSudoku should be (true)
    disfiguredBoard.isSudoku should be (false)
    arbitrarySudoku.isSudoku should be (true)
  }

  "Sudoku" should "implement isSolved properly" in {
    unsolvedBoard.isSolved should be (false)
    solvedBoard.isSolved should be (true)
  }

  "Sudoku" should "implement isValidBlock properly" in {
    solvedBoard.isValidBlock(solvedBoard.board.head) should be (true)
    unsolvedBoard.isValidBlock(unsolvedBoard.board.head) should be (true)
    emptyBoard.isValidBlock(Vector(1,2,1,1,2,3,4,5,6)) should be (false)
  }

}
