package org.abimon.lazarus.dungeons

enum class EnumSide {
    NORTH,
    EAST,
    SOUTH,
    WEST;

    companion object {
        infix fun forSide(side: Int): EnumSide = values()[side % 4]
    }

    val inverse: EnumSide
        get() = when(this) {
            NORTH -> SOUTH
            SOUTH -> NORTH
            EAST -> WEST
            WEST -> EAST
        }

    val opposite: EnumSide
        get() = inverse

    val left: EnumSide
        get() = when(this) {
            NORTH -> WEST
            SOUTH -> EAST
            EAST -> NORTH
            WEST -> SOUTH
        }

    val right: EnumSide
        get() = when(this) {
            NORTH -> EAST
            SOUTH -> WEST
            EAST -> SOUTH
            WEST -> NORTH
        }
}