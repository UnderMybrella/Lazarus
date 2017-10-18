package org.abimon.lazarus

import java.io.File

fun main(args: Array<String>) {
    val dice = DiceSet()

    for(i in 0 until 100) {
        val npc = Resources.generateNPC(dice)

        File("npcs/$i.txt").writeText(npc.toString())
    }
}