package org.abimon.lazarus.dungeons

import java.awt.Color
import java.awt.Rectangle

open class DungeonDoor(val material: String, width: Int, side: EnumSide): BasicDungeonRoom(Rectangle(if(side == EnumSide.NORTH || side == EnumSide.SOUTH) width else 4, if(side == EnumSide.NORTH || side == EnumSide.SOUTH) 4 else width)) {
    override val colour: Color = when(material.split(',')[0]) {
        "Wooden" -> Color(87, 65, 0)
        "Stone" -> Color.DARK_GRAY
        "Iron" -> Color.GRAY
        "Portcullis" -> Color.BLUE
        "Secret door" -> Color.MAGENTA
        else -> Color.PINK
    }
}