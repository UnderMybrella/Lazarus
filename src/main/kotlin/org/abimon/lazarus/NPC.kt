package org.abimon.lazarus

data class NPC(
        val appearances: Array<String>,
        val talents: Array<String>,
        val mannerisms: Array<String>,
        val interactions: Array<String>,
        val alignment: Pair<EnumLawfulAlignment, EnumGoodAlignment>,
        val ideals: Array<String>,
        val bonds: Array<String>,
        val flawsAndSecrets: Array<String>
) {
    override fun toString(): String {
        return buildString {
            append("Unnamed NPC (${alignment.first.name[0]}${alignment.second.name[0]})\n")
            appearances.forEach { str -> append("\t$str\n") }
            talents.forEach { str -> append("\t$str\n") }
            mannerisms.forEach { str -> append("\t$str\n") }
            interactions.forEach { str -> append("\t$str\n") }
            ideals.forEach { str -> append("\t$str\n") }
            bonds.forEach { str -> append("\t$str\n") }
            flawsAndSecrets.forEach { str -> append("\t$str\n") }
        }
    }
}