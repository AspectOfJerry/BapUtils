package net.jerrydev.baputils.core

import gg.essential.vigilance.Vigilant
import gg.essential.vigilance.data.Property
import gg.essential.vigilance.data.PropertyType
import java.io.File

object BapConfig : Vigilant(File("./config/jerrydev/baputils/modconfig.toml")) {
    @Property(
        type = PropertyType.SWITCH,
        name = "Global toggle",
        description = "Enable/disable all features of this mod",
        category = "General",
        subcategory = "Master"
    )
    var modMaster = true

    @Property(
        type = PropertyType.SWITCH,
        name = "Allow Party Takeover",
        description = "Allows other players to take your party.",
        category = "Party",
    )
    var partyTakeoverMaster = true

    @Property(
        type = PropertyType.CHECKBOX,
        name = "Party Takeover trusted only",
        description = "Only allow trusted players to take your party (/trust).",
        category = "Party",
    )
    var partyTakeoverTrustedOnly = true

    @Property(
        type = PropertyType.SWITCH,
        name = "Allow DungeonJoin",
        description = "Allows other players to join a dungeon run on your behalf.",
        category = "Party",
        subcategory = "Dungeons"
    )
    var dungeonJoinMaster = true

    @Property(
        type = PropertyType.CHECKBOX,
        name = "DungeonJoin trusted only",
        description = "Only allow trusted players to join a dungeon run on your behalf (/trust).",
        category = "Party",
        subcategory = "Dungeons"
    )
    var dungeonJoinTrustedOnly = true


    init {
        super.initialize()

        super.setCategoryDescription(
            "General",
            "General settings"
        )
        /* super.setSubcategoryDescription(
            "General",
            "Master",
            "Master switches"
        ); */

        super.setCategoryDescription(
            "Party",
            "Hypixel party utilities"
        )
        /* super.setSubcategoryDescription(
            "Party",
            "Dungeons",
            "Dungeon-related features"
        ); */

        super.setCategoryDescription(
            "Dungeons",
            "Dungeon-related features"
        )

        super.setCategoryDescription(
            "Slayers",
            "Slayer-related features"
        )

        super.setCategoryDescription(
            "Utility",
            "Utility features"
        )

        super.addDependency("partyTakeoverTrustedOnly", "partyTakeoverMaster")
        super.addDependency("dungeonJoinTrustedOnly", "dungeonJoinMaster")
    }
}
