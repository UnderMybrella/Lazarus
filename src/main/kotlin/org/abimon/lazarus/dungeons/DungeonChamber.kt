package org.abimon.lazarus.dungeons

import org.abimon.lazarus.DiceSet
import org.abimon.lazarus.mapOutEdgesAndSides
import java.awt.Point
import java.awt.Shape

class DungeonChamber(val shape: Shape, val large: Boolean): DungeonRoomTemplate {
    val edges: Map<EnumSide, Pair<Point, Point>> = shape.mapOutEdgesAndSides().filter { (_, _, side) -> side != null }.map { (a, b, side) -> side!! to (a to b) }.toMap()

    override fun generateDungeonRoom(dice: DiceSet, exit: DungeonRoom?, exitSide: EnumSide?): DungeonRoom {
        val room = BasicDungeonRoom(shape)

        if(exit != null && exitSide != null) {
            val exitSides = IntArray(4)

            for (i in 0 until 4)
                exitSides[dice[4]]++

            exitSides.forEachIndexed { index, numberOfExits ->
                when(index) {
                    0 -> {
                        val (start, end) = edges[exitSide] ?: return@forEachIndexed
                        val len = if(exitSide.isHorizontal) (maxOf(start.x, end.x) - minOf(start.x, end.x)) else (maxOf(start.y, end.y) - minOf(start.y, end.y))
                        val perSide = (len - ((numberOfExits + 2) * 5)) / (numberOfExits + 1)

                        if(exitSide.isHorizontal) {
                            var exitChance = 100 / (numberOfExits + 1)
                            val startX = minOf(start.x, end.x) + 5
                            for(i in 0 until numberOfExits + 1) {
                                if(exitChance != -1 && dice[100] < (i + 1) * exitChance) {
                                    room.addExit(Point(startX + (i * perSide) + 5, start.y), exitSide.opposite, exit)
                                    exitChance = -1
                                }
                                else
                                    room.addExit(Point(startX + (i * perSide) + 5, start.y), exitSide.opposite, DungeonResources.getPassage(dice, exitSide.opposite, true))
                            }
                        }
                    }
                }
            }
        }

        return room
    }
}