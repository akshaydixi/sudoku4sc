package sudoku4sc

import scala.io.Source
import scalax.io._

object Sudoku {

  type Block = Vector[Int]
  type Position = (Int, Int)

  class Sudoku(val board: Vector[Vector[Int]], val blocks: Vector[Block]) {
    val ALL_INTS = Vector(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)

    def this(board: Vector[Vector[Int]]) {
      this(board, toBlocks(board))
    }

    // Constructor to read a Sudoku board from a CSV file
    def this(fileName: String) {
      this(Source
            .fromFile(fileName)
            .getLines()
            .toVector
            .map(line => line.split(',')
            .map(x => x.toInt).toVector))
    }

    // To check if a Sudoku board is valid -> All rows are of length '9' and all elements are between 0 and 9 inclusive
    def isSudoku: Boolean =
      board.length == 9 &&
      board
        .forall(x => x.length == 9 && x.forall(ALL_INTS.contains(_)))

    // To check if a Sudoku has been solved -> Simply check if there aren't any zeros
    def isSolved: Boolean =
      !board
        .exists(_.contains(0))


    // To check if all the blocks in the Sudoku are valid
    def isOkay: Boolean =
      blocks
        .forall(isValidBlock)

    // Returns a blank position in the current Sudoku grid
    // Current Heuristic : Get the first blank in the row
    def blank: Option[Position] = {
      if (this.isSolved) None
      else {
        val positions =
          for (i <- 0 until board.length if board(i).contains(0))
          yield (i, board(i).indexOf(0))
        Some(positions.head)
      }
    }

    // Update a position in the Sudoku grid by returning a new updated Sudoku object
    def update(position: Position, value: Int): Sudoku = {
      new Sudoku(board
                  .updated(position._1, board(position._1)
                                          .updated(position._2, value)))
    }

    // Tries to output a solved Sudoku board from the original one
    // Brute backtracking algorithm implementation
    def solve: Option[Sudoku] = {
      if (this.isSolved && this.isOkay) Some(this)
      else {
        this.blank match {
          case None =>  None
          case Some(pos) =>
            val solutions = (for (i <- 1 to 9 ) yield update(pos, i)).filter(_.isOkay)
            lazy val completeSolutions = (for (solution <- solutions) yield solution.solve).filter(_.isInstanceOf[Some[Sudoku]])
            if (completeSolutions.length > 0) completeSolutions.head
            else None
        }
      }
    }

    // To show Sudoku boards cleanlyPosition
    override def toString: String = {
      val str = for (row <- board) yield row.mkString(" ")
      str.mkString("\n", "\n", "\n")
    }
  }

  // To check if a Block( row, column or a (3 x 3) block is valid -> Simply check if all numbers appear once
  def isValidBlock(block: Block): Boolean = {
    val blockWithoutZero = block.filter(_ > 0)
    blockWithoutZero.distinct == blockWithoutZero && block.length == 9
  }



  // Convert a Sudoku board into blocks -> 9 rows, 9 columns, 9 (3x3) blocks
  def toBlocks(board: Vector[Vector[Int]]): Vector[Block] = {
    val rows = board
    val cols = for (i <- 0 until board.length) yield board.map(_(i))
    val blocks = for {
      i <- 0 until board.length by 3
      j <- 0 until board.length by 3
    } yield rows.slice(i, i + 3).flatMap(_.slice(j, j + 3))
    rows ++ cols ++ blocks
  }

  // Takes an input Sudoku CSV file and tries to solve it and outputs the attempt to given output path
  def solveFromFile(inputFile: String, outputFile: String) = {
    val result = new Sudoku(inputFile).solve match {
      case None => "Could not solve"
      case Some(sud) => sud.toString
    }
    val output = Resource.fromFile(outputFile)
    output.write(result)
  }

}

