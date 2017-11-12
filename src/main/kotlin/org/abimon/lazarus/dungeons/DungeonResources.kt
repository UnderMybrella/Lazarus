package org.abimon.lazarus.dungeons

import org.abimon.lazarus.DiceSet
import org.abimon.lazarus.get
import java.awt.Point
import java.awt.Rectangle
import java.awt.geom.Ellipse2D

object DungeonResources {
    val STARTING_AREAS = arrayOf(
            StartingArea(Rectangle(20, 20), 0, 4),
            StartingArea(Rectangle(20, 20), 2, 1),
            StartingArea(Rectangle(40, 40), 3, 0),
            D4StartingArea,
            StartingArea(Rectangle(20, 40), 0, 4),
            StartingArea(Ellipse2D.Double(0.0, 0.0, 40.0, 40.0), 0, 4),
            StartingArea(Ellipse2D.Double(0.0, 0.0, 40.0, 40.0), 0, 4),
            StartingArea(Rectangle(20, 20), 2, 1)
    )

    val CHAMBERS = arrayOf(
            DungeonChamber(Rectangle(40, 40), false)
    )

    fun generateDungeon(dice: DiceSet): DungeonRoom {
        return STARTING_AREAS[dice].first().generateDungeonRoom(dice, null, null)
    }

    fun getChamber(dice: DiceSet, exit: DungeonRoom, exitSide: EnumSide): DungeonRoom = CHAMBERS[dice].first().generateDungeonRoom(dice, exit, exitSide)

    fun getDoor(dice: DiceSet, side: EnumSide, width: Int = 4): DungeonRoom {
        val material: String = when(dice[20] + 1) {
            in arrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10) -> "Wooden"
            in arrayOf(11, 12) -> "Wooden, barred or locked"
            13 -> "Stone"
            14 -> "Stone, barred or locked"
            15 -> "Iron"
            16 -> "Iron, barred or locked"
            17 -> "Portcullis"
            18 -> "Portcullis, locked in place"
            19 -> "Secret door"
            20 -> "Secret door, barred or locked"
            else -> "Void; Maths dun broke"
        }

        val door = DungeonDoor(material, width, side)
        when(dice[6] + 1) {
            in arrayOf(1, 2) -> door.addExit(DungeonRoom.getExitForSide(side, door.shape.bounds.width, door.shape.bounds.height), side, getPassage(dice, side, false, width))
            3 -> door.addExit(DungeonRoom.getExitForSide(side, door.shape.bounds.width, door.shape.bounds.height), side, getChamber(dice, door, side))
            else -> door.addExit(DungeonRoom.getExitForSide(side, door.shape.bounds.width, door.shape.bounds.height), side, BasicDungeonRoom(Rectangle(10, 10)))
        }


        return door
    }

    fun getPassage(dice: DiceSet, side: EnumSide, outOfRoom: Boolean, width: Int = if(outOfRoom) getPassageWidthWide(dice) else getPassageWidth(dice)): DungeonRoom {
        when(dice[20] + 1) {
            in arrayOf(1, 2) -> {
                when(side) {
                    EnumSide.NORTH -> {
                        val passage = BasicDungeonRoom(Rectangle(width, 30))
                        passage.addExit(Point(0, -15), side, getPassage(dice, side, outOfRoom, width))
                        return passage
                    }
                    EnumSide.EAST -> {
                        val passage = BasicDungeonRoom(Rectangle(30, width))
                        passage.addExit(Point(15, 0), side, getPassage(dice, side, outOfRoom, width))
                        return passage
                    }
                    EnumSide.SOUTH -> {
                        val passage = BasicDungeonRoom(Rectangle(width, 30))
                        passage.addExit(Point(0, 15), side, getPassage(dice, side, outOfRoom, width))
                        return passage
                    }
                    EnumSide.WEST -> {
                        val passage = BasicDungeonRoom(Rectangle(30, width))
                        passage.addExit(Point(-15, 0), side, getPassage(dice, side, outOfRoom, width))
                        return passage
                    }
                }
            }
            3 -> {
                when(side) {
                    EnumSide.NORTH -> {
                        val passage = BasicDungeonRoom(Rectangle(width, 30))
                        val door = getDoor(dice, EnumSide.EAST, getPassageWidth(dice))
                        passage.addExit(Point(width / 2, -5 - (door.shape.bounds.width / 2)), EnumSide.EAST, door)
                        passage.addExit(Point(0, -15), side, getPassage(dice, side, outOfRoom, width))
                        return passage
                    }
                    EnumSide.EAST -> {
                        val passage = BasicDungeonRoom(Rectangle(30, width))
                        val door = getDoor(dice, EnumSide.SOUTH, getPassageWidth(dice))
                        passage.addExit(Point(5 + (door.shape.bounds.width / 2), width / 2), EnumSide.SOUTH, door)
                        passage.addExit(Point(15, 0), side, getPassage(dice, side, outOfRoom, width))
                        return passage
                    }
                    EnumSide.SOUTH -> {
                        val passage = BasicDungeonRoom(Rectangle(width, 30))
                        val door = getDoor(dice, EnumSide.WEST, getPassageWidth(dice))
                        passage.addExit(Point(-(width / 2), 5 + (door.shape.bounds.width / 2)), EnumSide.WEST, door)
                        passage.addExit(Point(0, 15), side, getPassage(dice, side, outOfRoom, width))
                        return passage
                    }
                    EnumSide.WEST -> {
                        val passage = BasicDungeonRoom(Rectangle(30, width))
                        val door = getDoor(dice, EnumSide.NORTH, getPassageWidth(dice))
                        passage.addExit(Point(-5 - (door.shape.bounds.width / 2), -(width / 2)), EnumSide.NORTH, door)
                        passage.addExit(Point(-15, 0), side, getPassage(dice, side, outOfRoom, width))
                        return passage
                    }
                }
            }
            4 -> {
                when(side) {
                    EnumSide.NORTH -> {
                        val passage = BasicDungeonRoom(Rectangle(width, 30))
                        val door = getDoor(dice, EnumSide.WEST, getPassageWidth(dice))
                        passage.addExit(Point(-(width / 2), -5 - (door.shape.bounds.width / 2)), EnumSide.WEST, door)
                        passage.addExit(Point(0, -15), side, getPassage(dice, side, outOfRoom, width))
                        return passage
                    }
                    EnumSide.EAST -> {
                        val passage = BasicDungeonRoom(Rectangle(30, width))
                        val door = getDoor(dice, EnumSide.NORTH, getPassageWidth(dice))
                        passage.addExit(Point(5 + (door.shape.bounds.width / 2), -(width / 2)), EnumSide.NORTH, door)
                        passage.addExit(Point(15, 0), side, getPassage(dice, side, outOfRoom, width))
                        return passage
                    }
                    EnumSide.SOUTH -> {
                        val passage = BasicDungeonRoom(Rectangle(width, 30))
                        val door = getDoor(dice, EnumSide.EAST, getPassageWidth(dice))
                        passage.addExit(Point(width / 2, 5 + (door.shape.bounds.width / 2)), EnumSide.EAST, door)
                        passage.addExit(Point(0, 15), side, getPassage(dice, side, outOfRoom, width))
                        return passage
                    }
                    EnumSide.WEST -> {
                        val passage = BasicDungeonRoom(Rectangle(30, width))
                        val door = getDoor(dice, EnumSide.SOUTH, getPassageWidth(dice))
                        passage.addExit(Point(-5 - (door.shape.bounds.width / 2), width / 2), EnumSide.SOUTH, door)
                        passage.addExit(Point(-15, 0), side, getPassage(dice, side, outOfRoom, width))
                        return passage
                    }
                }
            }
            5 -> {}
            in arrayOf(6, 7) -> {}
            in arrayOf(8, 9) -> {}
            10 -> {}
            in arrayOf(11, 12) -> {}
            in arrayOf(13, 14) -> {}
            in arrayOf(15, 16, 17, 18, 19, 20) -> {}
        }

        return getDoor(dice, side, width)
    }

//    fun getPassageWidthWide(dice: DiceSet): Int {
//        when(dice[20] + 1) {
//            in arrayOf(1, 2) -> return 5
//            in arrayOf(3, 4, 5, 6, 7, 8, 9, 10, 11, 12) -> return 10
//            in arrayOf(13, 14) -> return 20
//            in arrayOf(15, 16) -> return 30
//            17 -> return 40
//            18 -> return 40
//            19 -> return 40
//            20 -> return 40
//
//            else -> return 5
//        }
//    }

    fun getPassageWidthWide(dice: DiceSet) = getPassageWidth(dice)

    fun getPassageWidth(dice: DiceSet): Int {
        when(dice[12] + 1) {
            in arrayOf(1, 2) -> return 5
            in arrayOf(3, 4, 5, 6, 7, 8, 9, 10, 11, 12) -> return 10

            else -> return 5
        }
    }
}