package sudoku4sc

import org.scalameter.api._
import sudoku4sc.Sudoku.Sudoku

object SudokuBenchmark extends PerformanceTest.Quickbenchmark {

  val sizes: Gen[Int] = Gen.range("size")(5, 15, 5)
  val ranges: Gen[Range] = for {
    size <- sizes
  } yield 0 until size

  performance of "Sudoku" in {
    measure method "solve" in {
      val unsolvedBoard = new Sudoku("src/main/resources/unsolved1.csv")
      using(ranges) in {
        r => for(i <- r)unsolvedBoard.solve
      }
    }
  }
}
