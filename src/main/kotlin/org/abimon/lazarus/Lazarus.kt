package org.abimon.lazarus

import org.abimon.lazarus.dungeons.DungeonDoor
import org.abimon.lazarus.dungeons.DungeonExit
import org.abimon.lazarus.dungeons.DungeonResources
import org.abimon.lazarus.dungeons.DungeonRoom
import java.awt.Color
import java.awt.geom.AffineTransform
import java.awt.geom.Area
import java.awt.image.BufferedImage
import java.io.File
import java.util.*
import javax.imageio.ImageIO

fun main(args: Array<String>) {
    val dice = DiceSet()

//    for(i in 0 until 100) {
//        val npc = NPCResources.generateNPC(dice)
//
//        File("npcs/$i.txt").writeText(npc.toString())
//    }

    val circleImg = BufferedImage(80, 80, BufferedImage.TYPE_INT_ARGB)
    val circleG = circleImg.createGraphics()
    circleG.draw(Circle(80))
    circleG.dispose()

    ImageIO.write(circleImg, "PNG", File("circle.png"))

    val room = DungeonResources.generateDungeon(dice)

    var minX = 0
    var minY = 0

    var maxX = 0
    var maxY = 0

    room.traverse(0, 0) { x, y, curr, _, _, _ ->
        val bounds = curr.shape.bounds

        if (x - bounds.width / 2 < minX)
            minX = x - bounds.width / 2
        if (y - bounds.height / 2 < minY)
            minY = y - bounds.height / 2

        if (x + bounds.width / 2 > maxX)
            maxX = x + bounds.width / 2
        if (y + bounds.height / 2 > maxY)
            maxY = y + bounds.height / 2
    }

    println("$minX,$minY")
    println("$maxX,$maxY")

    val xMod = minX * -1
    val yMod = minY * -1

    val map = BufferedImage(maxX + xMod + 1, maxY + yMod + 1, BufferedImage.TYPE_INT_ARGB)
    val random = Random()
    val g = map.createGraphics()
    val dungeonArea = Area()

    room.traverse(0, 0) { x, y, thisRoom, ox, oy, originRoom ->
        g.color = thisRoom.colour
        val shape = Area(thisRoom.shape)
        val bounds = thisRoom.shape.bounds
        shape.transform(AffineTransform.getTranslateInstance((x + xMod - (bounds.width / 2)).toDouble(), (y + yMod - (bounds.height / 2)).toDouble()))
        if (thisRoom is DungeonExit || thisRoom is DungeonDoor) {
            g.fill(shape)
            g.color = Color.WHITE
            g.draw(shape)
            //g.drawLine(x + xMod, y + yMod, ox + xMod, oy + yMod)
        } else {
            //g.color = g.color.brighter()
            g.color = Color.WHITE
            //g.drawLine(x + xMod, y + yMod, ox + xMod, oy + yMod)
            g.draw(shape)
        }
        //}
    }

    g.color = Color.white
    //g.draw(dungeonArea)

    ImageIO.write(map, "PNG", File("map.png"))

    println(map)
}

fun Random.nextColour(): Color = Color(nextInt(256), nextInt(256), nextInt(256))

fun DungeonRoom.traverse(x: Int, y: Int, op: (Int, Int, DungeonRoom, Int, Int, DungeonRoom) -> Unit) = this.traverse(x, y, ArrayList(), op)
fun DungeonRoom.traverse(x: Int, y: Int, alreadyTravelled: MutableList<String>, op: (Int, Int, DungeonRoom, Int, Int, DungeonRoom) -> Unit) {
    exits.forEach { (point, side, exit) ->
        if (exit.uid in alreadyTravelled)
            return@forEach
        alreadyTravelled.add(exit.uid)

        val (px, py) = exit.shape.bounds.shiftToOrigin(x + point.x, y + point.y, side)

        op(px, py, exit, x, y, this)
        exit.traverse(px, py, alreadyTravelled, op)
    }
}