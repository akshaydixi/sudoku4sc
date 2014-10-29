package sudoku4sc

import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers
import scala.util.Random
import sudoku4sc.Sudoku._

class SudokuTest extends FlatSpec with ShouldMatchers {
  val unsolvedBoard = new Sudoku("src/main/resources/unsolved1.csv")
  val solvedBoard = new Sudoku("src/main/resources/solved1.csv")
  val emptyBoard = new Sudoku("src/main/resources/empty.csv")
  val disfiguredBoard = new Sudoku("src/main/resources/disfigured1.csv")
  val repetitiveBoard = new Sudoku("src/main/resources/repetitive1.csv")
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
    repetitiveBoard.isSudoku should be (true)
    arbitrarySudoku.isSudoku should be (true)
  }

  "Sudoku" should "implement isSolved properly" in {
    unsolvedBoard.isSolved should be (false)
    solvedBoard.isSolved should be (true)
    repetitiveBoard.isSolved should be (true)
  }

  "Sudoku" should "implement isValidBlock properly" in {
    isValidBlock(solvedBoard.board.head) should be (true)
    isValidBlock(unsolvedBoard.board.head) should be (true)
    isValidBlock(Vector(1,2,1,1,2,3,4,5,6)) should be (false)
  }

  "Sudoku" should "implement toBlocks properly" in {
    toBlocks(solvedBoard.board).map(isValidBlock).reduce(_ & _) should be (true)
    toBlocks(unsolvedBoard.board).map(isValidBlock).reduce(_ & _) should be (true)
    toBlocks(disfiguredBoard.board).map(isValidBlock).reduce(_ & _) should be (false)
  }

  "Sudoku" should "implement isOkay properly" in {
    solvedBoard.isOkay should be (true)
    unsolvedBoard.isOkay should be (true)
    disfiguredBoard.isOkay should be (false)
    repetitiveBoard.isOkay should be (false)
  }

  "Sudoku" should "implement blank properly" in {
    emptyBoard.blank.get should be ((0,0))
    unsolvedBoard.blank.get should be ((0,5))
  }

}
