package sudoku4sc

import scala.io.Source

class Sudoku(val board: Vector[Vector[Int]]) {

  def this(fileName: String) {
    this(Source.fromFile(fileName).getLines().toVector.map(line => line.split(',').map(x => x.toInt).toVector))
  }
  def isSudoku: Boolean = board.length == 9 && board.map( x => x.length == 9 && x.exists(Vector(0,1,2,3,4,5,6,7,8,9).contains(_))).foldLeft(true)( (x,y) => x & y)

  def isSolved: Boolean = {
    !board.map( x => x.contains(0)).foldLeft(false)( (x,y) => x | y)
  }

  override def toString: String = {
    val str = for (row <- board) yield row.mkString(" ")
    str.mkString("\n", "\n", "\n")
  }
}


