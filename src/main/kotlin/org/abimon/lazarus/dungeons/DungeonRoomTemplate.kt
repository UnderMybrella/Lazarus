package org.abimon.lazarus.dungeons

import org.abimon.lazarus.DiceSet

interface DungeonRoomTemplate {
    fun generateDungeonRoom(dice: DiceSet, exit: DungeonRoom?, exitSide: EnumSide?): DungeonRoom
}