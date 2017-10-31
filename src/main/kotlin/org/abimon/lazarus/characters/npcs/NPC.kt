package org.abimon.lazarus.characters.npcs

import org.abimon.lazarus.characters.EnumGoodAlignment
import org.abimon.lazarus.characters.EnumLawfulAlignment
import org.abimon.lazarus.characters.EnumStat
import org.abimon.lazarus.name
import java.util.*

data class NPC(
        val name: String,
        val race: Array<String>,
        val appearances: Array<String>,
        val highStats: Array<EnumStat>,
        val lowStats: Array<EnumStat>,
        val talents: Array<String>,
        val mannerisms: Array<String>,
        val interactions: Array<String>,
        val alignment: Pair<EnumLawfulAlignment, EnumGoodAlignment>,
        val ideals: Array<String>,
        val bonds: Array<String>,
        val flawsAndSecrets: Array<String>,
        val agendas: Array<String>,
        val currentRole: String,
        val pastRoles: Array<String>,
        val gp: Int
) {
    override fun toString(): String {
        return buildString {
            append("$name (${alignment.name})\n")
            append("Appearance:\n")
            appearances.forEach { str -> append("\t$str\n") }
            append("High Stats:\n")
            highStats.forEach { stat -> append("\t${stat.name.toLowerCase().capitalize()}\n") }
            append("Low Stats:\n")
            lowStats.forEach { stat -> append("\t${stat.name.toLowerCase().capitalize()}\n") }
            append("Talents:\n")
            talents.forEach { str -> append("\t$str\n") }
            append("Mannerisms:\n")
            mannerisms.forEach { str -> append("\t$str\n") }
            append("Interactions:\n")
            interactions.forEach { str -> append("\t$str\n") }
            append("Ideals:\n")
            ideals.forEach { str -> append("\t$str\n") }
            append("Bonds:\n")
            bonds.forEach { str -> append("\t$str\n") }
            append("Flaws and Secrets:\n")
            flawsAndSecrets.forEach { str -> append("\t$str\n") }
            append("Agendas:\n")
            agendas.forEach { str -> append("\t$str\n") }
            append("Current Role: $currentRole\n")
            append("Past Roles:\n")
            pastRoles.forEach { str -> append("\t$str\n") }
            append("$gp GP")
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as NPC

        if (!Arrays.equals(race, other.race)) return false
        if (!Arrays.equals(appearances, other.appearances)) return false
        if (!Arrays.equals(highStats, other.highStats)) return false
        if (!Arrays.equals(lowStats, other.lowStats)) return false
        if (!Arrays.equals(talents, other.talents)) return false
        if (!Arrays.equals(mannerisms, other.mannerisms)) return false
        if (!Arrays.equals(interactions, other.interactions)) return false
        if (alignment != other.alignment) return false
        if (!Arrays.equals(ideals, other.ideals)) return false
        if (!Arrays.equals(bonds, other.bonds)) return false
        if (!Arrays.equals(flawsAndSecrets, other.flawsAndSecrets)) return false
        if (currentRole != other.currentRole) return false
        if (!Arrays.equals(pastRoles, other.pastRoles)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = Arrays.hashCode(race)
        result = 31 * result + Arrays.hashCode(appearances)
        result = 31 * result + Arrays.hashCode(highStats)
        result = 31 * result + Arrays.hashCode(lowStats)
        result = 31 * result + Arrays.hashCode(talents)
        result = 31 * result + Arrays.hashCode(mannerisms)
        result = 31 * result + Arrays.hashCode(interactions)
        result = 31 * result + alignment.hashCode()
        result = 31 * result + Arrays.hashCode(ideals)
        result = 31 * result + Arrays.hashCode(bonds)
        result = 31 * result + Arrays.hashCode(flawsAndSecrets)
        result = 31 * result + currentRole.hashCode()
        result = 31 * result + Arrays.hashCode(pastRoles)
        return result
    }
}