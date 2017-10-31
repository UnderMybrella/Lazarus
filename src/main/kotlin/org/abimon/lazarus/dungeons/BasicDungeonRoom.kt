package org.abimon.lazarus.dungeons

import org.abimon.lazarus.and
import java.awt.Point
import java.awt.Shape
import java.util.*

open class BasicDungeonRoom(override val shape: Shape) : DungeonRoom {
    private val _exits: MutableList<Triple<Point, EnumSide, DungeonRoom>> = ArrayList()
    override val exits: Array<Triple<Point, EnumSide, DungeonRoom>>
        get() = _exits.toTypedArray()
    override val uid: String = UUID.randomUUID().toString()

    override fun addExit(point: Point, side: EnumSide, room: DungeonRoom) {
        _exits.add(point to side and room)
    }
}