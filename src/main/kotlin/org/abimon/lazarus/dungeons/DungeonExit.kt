package org.abimon.lazarus.dungeons

import java.awt.Point
import java.awt.Rectangle
import java.awt.Shape

data class DungeonExit(val startingArea: DungeonRoom): DungeonRoom {
    private lateinit var exit: Triple<Point, EnumSide, DungeonRoom>
    override val exits: Array<Triple<Point, EnumSide, DungeonRoom>>
        get() = arrayOf(exit)
    override val shape: Shape = Rectangle(10, 10)
    override val uid: String = "DUNGEON-EXIT"

    override fun addExit(point: Point, side: EnumSide, room: DungeonRoom) { exit = Triple(point, side, room) }
}