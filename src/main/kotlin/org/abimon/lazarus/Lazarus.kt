package org.abimon.lazarus

import org.abimon.lazarus.dungeons.DungeonDoor
import org.abimon.lazarus.dungeons.DungeonExit
import org.abimon.lazarus.dungeons.DungeonResources
import org.abimon.lazarus.dungeons.DungeonRoom
import java.awt.Color
import java.awt.Graphics2D
import java.awt.geom.AffineTransform
import java.awt.geom.Area
import java.awt.image.BufferedImage
import java.io.File
import java.util.*
import javax.imageio.ImageIO
import kotlin.collections.HashMap

fun main(args: Array<String>) {
    val dice = DiceSet()

//    for(i in 0 until 100) {
//        val npc = NPCResources.generateNPC(dice)
//
//        File("npcs/$i.txt").writeText(npc.toString())
//    }

    val room = DungeonResources.generateDungeon(dice)

    var minX = 0
    var minY = 0

    var maxX = 0
    var maxY = 0

    room.traverse(0, 0) { x, y, _, curr, _, _, _, _ ->
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

    val maps: MutableMap<Int, Pair<BufferedImage, Graphics2D>> = HashMap()
    val img = BufferedImage(maxX + xMod + 1, maxY + yMod + 1, BufferedImage.TYPE_INT_ARGB)
    val gr = img.createGraphics().apply { color = Color.WHITE }
    val random = Random()
    val dungeonArea = Area()

    room.traverse(0, 0) { x, y, thisElevation, thisRoom, ox, oy, originElevation, originRoom ->
        val shape = Area(thisRoom.shape)
        val bounds = thisRoom.shape.bounds
        shape.transform(AffineTransform.getTranslateInstance((x + xMod - (bounds.width / 2)).toDouble(), (y + yMod - (bounds.height / 2)).toDouble()))
//        if (thisRoom is DungeonExit || thisRoom is DungeonDoor) {
//        } else {
//        }

        if (thisElevation !in maps) {
            val map = BufferedImage(maxX + xMod + 1, maxY + yMod + 1, BufferedImage.TYPE_INT_ARGB)
            maps[thisElevation] = map to map.createGraphics().apply { this.color = Color.WHITE }
        }

        val (_, g) = maps[thisElevation]!!

        if (thisRoom is DungeonExit || thisRoom is DungeonDoor) {
            g.color = thisRoom.colour
            gr.color = thisRoom.colour

            g.fill(shape)
            gr.fill(shape)

            g.color = thisRoom.colour.brighter()
            gr.color = thisRoom.colour.brighter()

            g.draw(shape)
            gr.draw(shape)
        } else {
            g.color = Color.WHITE
            gr.color = Color.WHITE

            g.draw(shape)
            gr.draw(shape)
        }
    }

    maps.forEach { elevation, (img, g) ->
        g.dispose()
        ImageIO.write(img, "PNG", File("map-B${elevation}F.png"))
    }

    ImageIO.write(img, "PNG", File("map.png"))
}

fun Random.nextColour(): Color = Color(nextInt(256), nextInt(256), nextInt(256))

fun DungeonRoom.traverse(op: (Int, Int, Int, DungeonRoom, Int, Int, Int, DungeonRoom) -> Unit) = this.traverse(0, 0, 0, ArrayList(), Area(), op)
fun DungeonRoom.traverse(x: Int, y: Int, op: (Int, Int, Int, DungeonRoom, Int, Int, Int, DungeonRoom) -> Unit) = this.traverse(x, y, 0, ArrayList(), Area(), op)
fun DungeonRoom.traverse(x: Int, y: Int, elevation: Int, alreadyTravelled: MutableList<String>, map: Area, op: (Int, Int, Int, DungeonRoom, Int, Int, Int, DungeonRoom) -> Unit) {
    exits.forEach { (point, side, exit) ->
        if (exit.uid in alreadyTravelled)
            return@forEach
        alreadyTravelled.add(exit.uid)

        val (px, py) = exit.shape.bounds.shiftToOrigin(x + point.x, y + point.y, side)

        val shape = Area(exit.shape)
        val bounds = exit.shape.bounds
        shape.transform(AffineTransform.getTranslateInstance((x - (bounds.width / 2)).toDouble(), (y - (bounds.height / 2)).toDouble()))

        val thisElevation = if(shape in map) elevation + 1 else elevation
        val thisMap = if(shape in map) Area() else map

        thisMap.add(shape)

        op(px, py, elevation, exit, x, y, thisElevation,this)
        exit.traverse(px, py, thisElevation, alreadyTravelled, thisMap, op)
    }
}