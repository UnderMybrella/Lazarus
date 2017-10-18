package org.abimon.lazarus

object Resources {
    val APPEARANCE = arrayOf(
            "Distinctive jewelry: earrings, necklace, circlet, bracelets",
            "Piercings",
            "Flamboyant or outlandish clothes",
            "Formal, clean clothes",
            "Ragged, dirty clothes",
            "Pronounced scar",
            "Missing teeth",
            "Missing fingers",
            "Unusual eye colour (or two different colours)",
            "Tattoos",
            "Birthmark",
            "Unusual skin colour",
            "Bald",
            "Braided beard or hair",
            "Unusual hair colour",
            "Nervous eye twitch",
            "Distinctive nose",
            "Distinctive posture (crooked or rigid)",
            "Exceptionally beautiful",
            "Exceptionally ugly"
    )

    val TALENT = arrayOf(
            "Plays a musical instrument",
            "Speaks several languages fluently",
            "Unbelievably lucky",
            "Perfect memory",
            "Great with animals",
            "Great with children",
            "Great at solving puzzles",
            "Great at one game",
            "Great at impersonations",
            "Draws beautifully",
            "Paints beautifully",
            "Sings beautifully",
            "Drinks everyone under the table",
            "Expert carpenter",
            "Expert cook",
            "Expert dart thrower and rock skipper",
            "Expert juggler",
            "Skilled actor and master of disguise",
            "Skilled dancer",
            "Knows thieves' cant"
    )

    val MANNERISMS = arrayOf(
            "Prone to singing, whistling, or humming quietly",
            "Speaks in rhyme or some other peculiar way",
            "Particularly low or high voice",
            "Slurs words, lisps, or stutters",
            "Enunciates overly clearly",
            "Speaks loudly",
            "Whispers",
            "Uses flowery speech or long words",
            "Frequently uses the wrong word",
            "Uses colourful oaths and exclamations",
            "Makes constant jokes or puns",
            "Prone to predictions of doom",
            "Fidgets",
            "Squints",
            "Stares into the distance",
            "Chews something",
            "Paces",
            "Taps fingers",
            "Bites fingernails",
            "Twirls hair or tugs beard"
    )

    val INTERACTION_TRAITS = arrayOf(
            "Argumentative",
            "Arrogant",
            "Blustering",
            "Rude",
            "Curious",
            "Friendly",
            "Honest",
            "Hot tempered",
            "Irritable",
            "Ponderous",
            "Quiet",
            "Suspicious"
    )

    val GOOD_IDEALS = mapOf(
            EnumGoodAlignment.GOOD to arrayOf(
                    "Beauty",
                    "Charity",
                    "Greater good",
                    "Life",
                    "Respect",
                    "Self-sacrifice"
            ),
            EnumGoodAlignment.NEUTRAL to arrayOf(
                    "Balance",
                    "Knowledge",
                    "Live and let live",
                    "Moderation",
                    "Neutrality",
                    "People"
            ),
            EnumGoodAlignment.EVIL to arrayOf(
                    "Domination",
                    "Greed",
                    "Might",
                    "Pain",
                    "Retribution",
                    "Slaughter"
            )
    )

    val LAWFUL_IDEALS = mapOf(
            EnumLawfulAlignment.LAWFUL to arrayOf(
                    "Community",
                    "Fairness",
                    "Honour",
                    "Logic",
                    "Responsibility",
                    "Tradition"
            ),
            EnumLawfulAlignment.NEUTRAL to arrayOf(
                    "Aspiration",
                    "Discovery",
                    "Glory",
                    "Nation",
                    "Redemption",
                    "Self-knowledge"
            ),
            EnumLawfulAlignment.CHAOTIC to arrayOf(
                    "Change",
                    "Creativity",
                    "Freedom",
                    "Independence",
                    "No limits",
                    "Whimsy"
            )
    )

    val BONDS = arrayOf(
            "Dedicated to fulfilling a personal life goal",
            "Protective of close family members",
            "Protective of colleagues or compatriots",
            "Loyal to a benefactor, patron, or employer",
            "Captivated by a romantic interest",
            "Drawn to a special place",
            "Protective of a sentimental keepsake",
            "Protective of a valuable possession",
            "Out for revenge",
            "Self preservation"
    )

    val FLAWS_AND_SECRETS = arrayOf(
            "Forbidden love or susceptibility to romance",
            "Enjoys decadent pleasures",
            "Arrogance",
            "Envies another creature's possessions station",
            "Overpowering greed",
            "Prone to rage",
            "Has a powerful enemy",
            "Specific phobia",
            "Shameful or scandalous history",
            "Secret crime or misdeed",
            "Possession of forbidden lore",
            "Foolhardy bravery"
    )

    fun generateNPC(dice: DiceSet): NPC {
        val alignment = EnumLawfulAlignment.values()[dice[3]] to EnumGoodAlignment.values()[dice[3]]
        return NPC(
                APPEARANCE[dice],
                TALENT[dice],
                MANNERISMS[dice],
                INTERACTION_TRAITS[dice],
                alignment,
                ArrayList<String>().apply {
                    addAll(LAWFUL_IDEALS[alignment.first]!![dice])
                    addAll(GOOD_IDEALS[alignment.second]!![dice])
                }.toTypedArray(),
                BONDS[dice],
                FLAWS_AND_SECRETS[dice]
        )
    }

    operator inline fun <reified T> Array<T>.get(dice: DiceSet): Array<T> {
        val traits: MutableList<T> = ArrayList()

        var reroll: Boolean

        do {
            reroll = dice[20] >= 18 + traits.size

            traits.add(this[dice[size]])
        } while(reroll)

        return traits.toTypedArray()
    }
}