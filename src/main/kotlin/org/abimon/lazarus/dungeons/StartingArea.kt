package org.abimon.lazarus.dungeons

import org.abimon.lazarus.DiceSet
import org.abimon.lazarus.dungeons.DungeonRoom.Companion.getExitForSide
import java.awt.Shape

open class StartingArea(
        val shape: Shape,
        val doors: Int,
        val passages: Int
): DungeonRoomTemplate {

    override fun generateDungeonRoom(dice: DiceSet, exit: DungeonRoom?, exitSide: EnumSide?): DungeonRoom {
        val room = BasicDungeonRoom(shape)

        val bounds = shape.bounds
        var individualChance = 100 / (doors + passages)
        var side = 0

        for(i in 0 until doors) {
            val thisSide = EnumSide forSide side
            val dungeonRoom: DungeonRoom
            if(individualChance != -1 && dice[100] < ((side + 1) * individualChance)) {
                dungeonRoom = DungeonExit(room)
                individualChance = -1
            } else
                dungeonRoom = DungeonResources.getDoor(dice, thisSide, bounds.width / 2)

            dungeonRoom.addExit(getExitForSide(thisSide.inverse, dungeonRoom.shape.bounds.width, dungeonRoom.shape.bounds.height), thisSide.inverse, room)
            room.addExit(getExitForSide(thisSide, bounds.width, bounds.height), thisSide, dungeonRoom)

            side++
        }

        for(i in 0 until passages) {
            val thisSide = EnumSide forSide side
            val dungeonRoom: DungeonRoom
            if(individualChance != -1 && dice[100] < ((side + 1) * individualChance)) {
                dungeonRoom = DungeonExit(room)
                individualChance = -1
            } else
                dungeonRoom = DungeonResources.getPassage(dice, thisSide, true)

            dungeonRoom.addExit(getExitForSide(thisSide.inverse, dungeonRoom.shape.bounds.width, dungeonRoom.shape.bounds.height), thisSide.inverse, room)
            room.addExit(getExitForSide(thisSide, bounds.width, bounds.height), thisSide, dungeonRoom)

            side++
        }

        return room
    }
}