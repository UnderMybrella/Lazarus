package org.abimon.lazarus.dungeons

import org.abimon.lazarus.DiceSet
import java.awt.Rectangle

object D4StartingArea: DungeonRoomTemplate {
    override fun generateDungeonRoom(dice: DiceSet, exit: DungeonRoom?, exitSide: EnumSide?): DungeonRoom = BasicDungeonRoom(Rectangle(80, 20))
}