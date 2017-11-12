package org.abimon.lazarus

import org.abimon.lazarus.characters.EnumGoodAlignment
import org.abimon.lazarus.characters.EnumLawfulAlignment
import org.abimon.lazarus.dungeons.EnumSide
import java.awt.Point
import java.awt.Rectangle
import java.awt.Shape
import java.awt.geom.AffineTransform
import java.awt.geom.Area
import java.awt.geom.PathIterator

typealias Alignment=Pair<EnumLawfulAlignment, EnumGoodAlignment>
val Alignment.name: String
    get() = if (first == EnumLawfulAlignment.NEUTRAL && second == EnumGoodAlignment.NEUTRAL) "TN" else "${first.name[0]}${second.name[0]}"

operator inline fun <reified T> Array<T>.get(dice: DiceSet): Array<T> {
    val traits: MutableList<T> = ArrayList()

    var reroll: Boolean

    do {
        reroll = dice[20] >= 18 + traits.size

        traits.add(this[dice[size]])
    } while(reroll)

    return traits.toTypedArray()
}

infix fun <A, B, C> Pair<A, B>.and(third: C): Triple<A, B, C> = Triple(first, second, third)

fun Rectangle.shiftToOrigin(x: Int, y: Int, side: EnumSide): Pair<Int, Int> {
    when(side) {
        EnumSide.NORTH -> return x to y - height / 2
        EnumSide.EAST -> return x + width / 2 to y
        EnumSide.SOUTH -> return x to y + height / 2
        EnumSide.WEST -> return x - width / 2 to y
    }

    //return x to y
}

fun Shape.mapOutEdges(): Array<Pair<Point, Point>> {
    val path = getPathIterator(null)
    val edges: MutableList<Pair<Point, Point>> = ArrayList()
    var prev: Point? = null

    while(!path.isDone) {
        val coords = DoubleArray(6)
        val result = path.currentSegment(coords)
        when(result) {
            PathIterator.SEG_MOVETO -> prev = Point(coords[0].toInt(), coords[1].toInt())
            PathIterator.SEG_LINETO -> {
                val point = Point(coords[0].toInt(), coords[1].toInt())

                if(prev != null) edges.add(prev to point)
                prev = point
            }
        }
        path.next()
    }

    return edges.toTypedArray()
}

fun Shape.mapOutEdgesAndSides(): Array<Triple<Point, Point, EnumSide?>> {
    val bounds = this.bounds

    val path = getPathIterator(null)
    val edges: MutableList<Triple<Point, Point, EnumSide?>> = ArrayList()
    var prev: Point? = null

    while(!path.isDone) {
        val coords = DoubleArray(6)
        val result = path.currentSegment(coords)
        when(result) {
            PathIterator.SEG_MOVETO -> prev = Point(coords[0].toInt(), coords[1].toInt())
            PathIterator.SEG_LINETO -> {
                val point = Point(coords[0].toInt(), coords[1].toInt())

                if(prev != null) {
                    if (point.x == prev.x) { //Vertical
                        if (point.x < bounds.width / 2)
                            edges.add(prev to point and EnumSide.WEST)
                        else
                            edges.add(prev to point and EnumSide.EAST)
                    } else if (point.y == prev.y) { //Horizontal
                        if(point.y < bounds.height / 2)
                            edges.add(prev to point and EnumSide.NORTH)
                        else
                            edges.add(prev to point and EnumSide.SOUTH)
                    } else
                        edges.add(prev to point and null)
                }
                prev = point
            }
        }
        path.next()
    }

    return edges.toTypedArray()
}

val Area.roomContents: Rectangle
    get() {
        val bounds = bounds
        return Rectangle(bounds.x + 1, bounds.y + 1, bounds.width - 2, bounds.height - 2)
    }

val TRANSFORMATIONS = arrayOf(0.0 to 1.0, 1.0 to 0.0, 0.0 to -1.0, -1.0 to 0.0)
operator fun Area.contains(area: Area): Boolean {
    val bounds = area.bounds
    val xMod = bounds.x.toDouble()
    val yMod = bounds.y.toDouble()

    for(x in 0 until bounds.width)
        for(y in 0 until bounds.height) {
            if(area.contains(x + xMod, y + yMod)) {
                val edgePiece = TRANSFORMATIONS.any { (a, b) -> area.contains(x + xMod + a, y + yMod + b) }
                if(!edgePiece) {
                    if(!this.contains(x + xMod, y + yMod))
                        return true
                }
            }
        }

    return false
}