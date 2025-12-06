import o1.grid.CompassDir.{East, North, South, West}
import o1.grid.{CompassDir, GridPos}

import scala.collection.mutable.{ArrayBuffer, Buffer}
import scala.util.Random

class DoubleArray(): //class to follow weights  of each icon
  val strings = ArrayBuffer[String]() ++ AllTiles.allStrings
  var weights = Buffer.fill[Int](AllTiles.allStrings.size)(100) //each icon can be drawn 100 times
  for (i <- 0 until AllTiles.allStrings.size){
    if (strings(i).contains("00000000")) weights(i) = 1 //each element containing will be drawn 1 time
    if (strings(i).contains("11111111")) weights(i) = 1

  }


var DA = new DoubleArray()


class Square(grid: WaveFunctionCollapse, val location: GridPos):
  var tile: Option[String] = None
  val boundary       = Buffer.fill[Option[Char]](8)(None)
  val plausibleTiles = ArrayBuffer[String]() ++ AllTiles.allStrings





  /**
    * Fixes a single element in the boundary and removes incompatible tiles.
    * @param placeOnBoundary index of the element on the boundary
    * @param value value to be set in the boundary
    */
  def fix(placeOnBoundary: Int, value: Char): Unit =
    var x: Option[Char] = Option(value)
    boundary.update(placeOnBoundary, x)






  end fix

  /**
   * Calculates the entropy of the tile, that is - the number of plausible tiles.
   * @return the number of plausible tiles
   */
  def entropy: Int = plausibleTiles.size

  /**
  * Selects a random tile from all plausible tiles. Updates neighboring tiles if necessary.
  */
  def collapse(): Unit =
    var random = Random.nextInt(plausibleTiles.size)
    var weight = DA.weights(DA.strings.indexOf(plausibleTiles(random)))
    while (weight <= 0){ //if element's weight is lower than 1 searches for new
      random = Random.nextInt(plausibleTiles.size)
      weight = DA.weights(DA.strings.indexOf(plausibleTiles(random)))
    }
    var y = plausibleTiles(random)
    DA.weights(DA.strings.indexOf(plausibleTiles(random))) -= 1
    var x: Option[String] = Option(y)
    tile = x
    //takes boundary of chosen tile and adds it to square

    var id = 0
    while (id < 8) {
      fix(id, y(id))
      id+=1
    }
    // if we are on grid's edge we do not go out of it

    if (this.location.y != 0) {
      var north = grid.elementAt(this.location.neighbor(North)) // get neigbour square
      var n = north.plausibleTiles
      var nn = ArrayBuffer[String]() // no match north neigbours
      for (i <- n) {
        if (this.boundary(0) != Option(i(6)) || this.boundary(1) != Option(i(5)) || this.boundary(2) != Option(i(4))) nn += i // if current square's boundary's do not match with some neigbour's plausibleBoundaries according to neighbourBoundaries, they must be removed
      }
      for (i <- nn) n -= i //removing from neighbour's plausibleTiles
    }



    if (this.location.x != grid.width-1) {
      var east = grid.elementAt(this.location.neighbor(East))
      var e = east.plausibleTiles
      var ne = ArrayBuffer[String]()
      for (i <- e) {
        if (this.boundary(2) != Option(i(0)) || this.boundary(3) != Option(i(7)) || this.boundary(4) != Option(i(6))) ne += i
      }
      for (i <- ne) e -= i
    }


    if (this.location.y != grid.height -1) {

      var south = grid.elementAt(this.location.neighbor(South))

      var s = south.plausibleTiles
      var ns = ArrayBuffer[String]()
      for (i <- s) {

        if(this.boundary(4) != Option(i(2)) || this.boundary(5) != Option(i(1)) || this.boundary(6) != Option(i(0))) ns += i
      }
      for (i <- ns) s -= i
    }


    if (this.location.x != 0) {
      var west = grid.elementAt(this.location.neighbor(West))
      var w = west.plausibleTiles
      var nw = ArrayBuffer[String]()
      for (i <- w) {
        if (this.boundary(0) != Option(i(2)) || this.boundary(6) != Option(i(4)) || this.boundary(7) != Option(i(3))) nw += i
      }
      for (i <- nw) w -= i
    }



  end collapse

  /**
   * Propagates the change in this square's boundary to boundaries and plausible tiles of neighboring squares.
   * @param boundaryIndex index in this squares boundary
   * @param fixedValue the value to be set in the boundary
   */
  def propagateChange(boundaryIndex: Int, fixedValue: Char): Unit =
    fix(boundaryIndex, fixedValue)

  end propagateChange


end Square
