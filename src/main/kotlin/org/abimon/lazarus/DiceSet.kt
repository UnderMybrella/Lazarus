package org.abimon.lazarus

import java.util.*
import kotlin.collections.HashMap

class DiceSet {
    private val dice: MutableMap<Int, Random> = HashMap()

    operator fun get(sides: Int): Int {
        if(!dice.containsKey(sides))
            dice[sides] = Random()

        return dice[sides]!!.nextInt(sides)
    }

    operator fun get(rolls: Int, sides: Int): Int {
        if(!dice.containsKey(sides))
            dice[sides] = Random()

        var result: Int = 0
        for(i in 0 until rolls)
            result += dice[sides]!!.nextInt(sides)
        return result
    }
}