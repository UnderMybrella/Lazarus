package org.abimon.lazarus.characters.npcs

import org.abimon.lazarus.DiceSet
import org.abimon.lazarus.characters.EnumGoodAlignment
import org.abimon.lazarus.characters.EnumLawfulAlignment
import org.abimon.lazarus.characters.EnumStat
import org.abimon.lazarus.get

object NPCResources {
    val RACE = arrayOf(
        "Dragonborn",
            "Drow",
            "Dwarf",
            "Elf",
            "Gnome",
            "Goblin",
            "Half-elf",
            "Halfling",
            "Hobgoblin",
            "Kobold",
            "Lizardfolk",
            "Merfolk",
            "Tiefling"
    )

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

    val AGENDAS = arrayOf(
            "Believes the PCs are up to something, and isn’t shy about saying so",
            "Desperately seeks the PCs’ forgiveness for a minor error, real or imagined",
            "Fixates on one PC, whom he tries to impress at the rest of the party’s expense",
            "Sees herself as the PCs’ superior and expects their head-nodding respect",
            "Feels he’s learned life’s lessons and is eager to heap advice on any young person who will listen",
            "Seeks advice on an upcoming duel (or other confrontation) with an old enemy",
            "Fixes on an idea or course of action and won’t abandon it",
            "Wants the PCs to know how rich, strong, important, famous, or admired she is",
            "Terrified of making a mistake",
            "Loves to hear himself talk.",
            "An amateur historian seeking information for the book she’s writing",
            "Keeps dropping things or knocking them over and is too flustered to help the PCs",
            "Plotting a crime or rebellion and wonders if the PCs are likely recruits",
            "Eager to show his independence from conventional wisdom",
            "Won’t do anything that might arouse the wrath of an intimidating superior",
            "Has dealt with idiots all day and is sure she’s looking at another bunch of them",
            "Convinced he is a grand hero trapped in an ordinary person’s body, and wants the PCs to help him recover his real identity",
            "Treats outsiders well and locals badly",
            "Completely inebriated but does not want anyone to catch on ",
            "Constantly looking out for insults, which she will imagine if necessary",
            "Wants to cut to the heart of the matter, resolving it as quickly as possible",
            "Wants to be flattered",
            "Recently lost a legal judgment and wants to complain about it at length",
            "A grand hero, trapped in an ordinary person’s body, who wants the PCs to help him recover his real identity",
            "Tired and simply wants to go home",
            "Interested only in who’s got the finest clothing.",
            "Willing to help the PCs because nothing really matters, anyway.  ",
            "Wants to test his or her charm on a PC of the opposite sex with a high Charisma",
            "Takes pleasure in refusing requests",
            "Wants to help the party, but must overcome a faulty memory to do so.",
            "Has just received wonderful news and wants the PCs to celebrate his good fortune with him",
            "Wants the PCs to envy the incredible meal she is about to eat",
            "Loves salacious and embarrassing information and happily shares what he knows in exchange for equally juicy rumors",
            "Hnows adventurers are rich and wonders how she can extract the maximum amount of coin from them",
            "Wants the PCs to think of him as their new best friend",
            "Wants to be left alone to mourn a loved one who has just died",
            "Will do anything to avoid seeming weak or vulnerable",
            "Has committed a sin or crime unrelated to the plot and is sure the PCs are onto him",
            "Late for an important appointment and wants to get rid of the PCs as quickly as possible",
            "Swears by a cousin’s foul-tasting cure-all elixir, urging the PCs to purchase some",
            "Eager to please, perhaps to a fault",
            "Wants to hear all about the biggest, toughest creature the party ever fought",
            "Comes from abroad and wants the PCs to agree that her homeland is much better than this place",
            "Wishes to enlist the PCs in a pet cause",
            "Wants to take action right now",
            "Tries to win a favor from the PCs through blatant begging",
            "Wants to keep secret the fact that she is a disguised rogue, doppelganger, or other entity posing as the real NPC",
            "Believes that people think him stupid and wants to prove otherwise",
            "Wants others to acknowledge how dreadful and imposing she is",
            "Has seen and done it all and wants the PCs to know that their problems mean nothing to him",
            "Wants the group to laugh at her terrible jokes",
            "Gregarious, wants to befriend the PCs and take them home to meet his family",
            "Tries to use as few words as possible",
            "Wants the PCs to be her best friends",
            "Very helpful, but speaks in a disconcertingly loud voice",
            "Powerfully infatuated and hopes to enlist the PCs in a quest for his true love’s heart",
            "Respects and admires the PCs’ current adversary or object of inquiry and would sooner die than commit an act of betrayal",
            "Tries to involve the PCs in a complicated scheme to gain power or harm an enemy. ",
            "Always on the lookout for a suitable spouse for a hapless relative",
            "Saddened by the state of the world and wants the PCs to share her gloom",
            "Feels that he’s an unrecognized genius and wants the PCs to show that they understand him",
            "Believes that all adventurers are murderous scoundrels and wants to get away from the PCs as soon as she can without offending them",
            "Secretly worships a dark deity of decay and destruction who will be pleased if the NPC brings about the party’s ruin",
            "Wants to know everything there is to know about the PCs, because this is her way of showing friendly interest in others",
            "Has a premonition about the PCs and wishes to share it",
            "Fears he’s boring and tries to seem fascinatingly crazy",
            "Aids the PCs only toward peaceful ends",
            "Wants everyone to love her king and country as much as she does",
            "Views the PCs as common street rabble and condescends to them",
            "Corrects any minor mistake or mispronunciation the PCs make. ",
            "Wishes to communicate his great devotion to his deity",
            "Irrationally despises one of the PCs on sight due to race, class, or another superficial trait",
            "Loves the area, wants to improve it, and urges the PCs to do the same",
            "Seeks amusement, preferably at the PCs’ expense",
            "Looking for an argument on any topic whatsoever",
            "Speaks in a barely audible voice and recoils if the PCs don’t do the same",
            "Wants to enlist the PCs in some troublemaking",
            "Wants to complain about a stupid or annoying superior",
            "Wants to help but loses focus unless carefully supervised",
            "Steers any conversation back to his favorite subject: himself",
            "Considers himself the PCs’ inferior and is disquietingly dedicated to aiding them",
            "Has done something terrible, and the more innocent she tries to appear, the more guilty she seems",
            "Suffers from a chronic malady and anxiously shares details of its symptoms",
            "Disbelieves anything remotely questionable the PCs say",
            "Infatuated with a PC of the opposite sex with a high Charisma, and wants to please him or her",
            "Believes he’s clearly the best at what he does",
            "Responds well to serious talk, but badly to jokes and trivialities",
            "Nurses a grudge against the PCs’ current adversary or object of inquiry and will say anything that increases their chances of harming him",
            "Doesn’t want the PCs getting too close to her or her possessions",
            "Wants to help but is too dumb to do it",
            "Has found a treasure map and wants the PCs to help him recover the loot",
            "Wishes to demonstrate her mastery of irrelevant minor facts",
            "Believes anything the PCs tell him",
            "Makes outlandish promises that she has no intention of keeping",
            "Was recently robbed or assaulted and wants the PCs to thrash the perpetrator",
            "Looking for a scrap against safely inferior opponents",
            "Bets on anything",
            "Wants to talk about how badly the kingdom has gone downhill in the past few years",
            "Treats locals well and outsiders badly"
    )

    val ROLES = arrayOf(
            "Innkeeper",
            "Merchant",
            "Shopkeep",
            "Bartender",
            "Stablehand",
            "Soldier",
            "Bodyguard",
            "Guard",
            "Town Watch",
            "Guild Owner",
            "Librarian",
            "Craftsman",
            "None"
    )

    val MONEY = arrayOf<(DiceSet) -> Int>(
            { dice -> dice[2, 4] + dice[10] },
            { dice -> dice[5, 4] + dice[10] },
            { dice -> dice[2, 4] * 10 + dice[10] },
            { dice -> dice[3, 4] * 10 + dice[10] },
            { dice -> dice[4, 4] * 10 + dice[10] },
            { dice -> dice[5, 4] * 10 + dice[10] }
    )

    fun generateNPC(dice: DiceSet): NPC {
        val alignment = EnumLawfulAlignment.values()[dice[3]] to EnumGoodAlignment.values()[dice[3]]
        return NPC(
                "Unnamed NPC",
                RACE[dice],
                APPEARANCE[dice],
                EnumStat.values()[dice],
                EnumStat.values()[dice],
                TALENT[dice],
                MANNERISMS[dice],
                INTERACTION_TRAITS[dice],
                alignment,
                ArrayList<String>().apply {
                    addAll(LAWFUL_IDEALS[alignment.first]!![dice])
                    addAll(GOOD_IDEALS[alignment.second]!![dice])
                }.toTypedArray(),
                BONDS[dice],
                FLAWS_AND_SECRETS[dice],
                AGENDAS[dice],
                ROLES[dice].first(),
                ROLES[dice],
                MONEY[dice].map { it(dice) }.fold(0) { a, b -> a + b }
        )
    }
}