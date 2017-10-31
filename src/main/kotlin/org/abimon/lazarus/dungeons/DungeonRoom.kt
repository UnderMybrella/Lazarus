package org.abimon.lazarus.dungeons

import org.abimon.lazarus.nextColour
import java.awt.Color
import java.awt.Point
import java.awt.Shape
import java.util.*

interface DungeonRoom {
//    val passages: Array<Pair<Point, DungeonRoom>>
//    val doors: Array<Pair<Point, DungeonRoom>>

    val exits: Array<Triple<Point, EnumSide, DungeonRoom>>
    val shape: Shape
    val uid: String

    val colour: Color
        get() {
            if(!colours.containsKey(uid))
                colours[uid] = colourRNG.nextColour()

            return colours[uid]!!
        }

    fun addExit(point: Point, side: Int, room: DungeonRoom) = addExit(point, EnumSide forSide side, room)
    fun addExit(point: Point, side: EnumSide, room: DungeonRoom)

    companion object {
        fun getExitForSide(side: Int, width: Int, height: Int): Point = getExitForSide(EnumSide forSide side, width, height)
//        fun getExitForSide(side: EnumSide, width: Int, height: Int, room: DungeonRoom): Point {
//            when(side) {
//                EnumSide.NORTH -> return Point(0 - room.shape.bounds.width / 2, height / 2)
//                EnumSide.EAST -> return Point(width / 2, 0 - room.shape.bounds.height / 2)
//                EnumSide.SOUTH -> return Point(0 - room.shape.bounds.width / 2, -height / 2)
//                EnumSide.WEST -> return Point(-width / 2, 0 - room.shape.bounds.height / 2)
//            }
//        }

        fun getExitForSide(side: EnumSide, width: Int, height: Int): Point {
            when(side) {
                EnumSide.NORTH -> return Point(0, -(height / 2))
                EnumSide.EAST -> return Point(width / 2, 0)
                EnumSide.SOUTH -> return Point(0, height / 2)
                EnumSide.WEST -> return Point(-(width / 2), 0)
            }
        }

        val colours = HashMap<String, Color>()
        val colourRNG = Random()
    }
}