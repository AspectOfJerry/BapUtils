//@file:JvmName("BapConfigGui")

package net.jerrydev.baputils.core

import gg.essential.vigilance.Vigilant
import gg.essential.vigilance.data.Property
import gg.essential.vigilance.data.PropertyType
import java.io.File

object BapSettingsGui : Vigilant(File("./config/jerrydev/baputils/modconfig.toml")) {
    @Property(
        type = PropertyType.SWITCH,
        name = "Global toggle (unavailable)",
        description = "[WIP] Enable all features of this mod",
        category = "General",
        subcategory = "Master"
    )
    var modMaster = true

    @Property(
        type = PropertyType.SWITCH,
        name = "Client chat verbose",
        description = "Let BapUtils show you more messages (client chat).",
        category = "General"
    )
    var clientChatVerbose = true

    @Property(
        type = PropertyType.SWITCH,
        name = "Client chat debug",
        description = "Let BapUtils show you debug messages (client chat).",
        category = "General"
    )
    var clientChatDebug = false

    @Property(
        type = PropertyType.SWITCH,
        name = "Allow Party Takeover",
        description = "Allow other players to take your party.",
        category = "Party",
    )
    var partyTakeoverMaster = true

    @Property(
        type = PropertyType.SWITCH,
        name = "Party Takeover trusted only (unavailable)",
        description = "[WIP] Only allow trusted players to take your party.",
        category = "Party",
    )
    var partyTakeoverTrustedOnly = false

    @Property(
        type = PropertyType.SWITCH,
        name = "Allow Party Warp",
        description = "Allow other players to warp your party.",
        category = "Party",
    )
    var partyWarpMaster = true

    @Property(
        type = PropertyType.SWITCH,
        name = "Party Takeover trusted only (unavailable)",
        description = "[WIP] Only allow trusted players to warp your party.",
        category = "Party",
    )
    var partyWarpTrustedOnly = false

    @Property(
        type = PropertyType.SWITCH,
        name = "Allow JoinDungeon",
        description = "Allow other players to join a dungeon run on your behalf.",
        category = "Party",
        subcategory = "Dungeons"
    )
    var joinDungeonMaster = true

    @Property(
        type = PropertyType.SWITCH,
        name = "JoinDungeon trusted only (unavailable)",
        description = "[WIP] Only allow trusted players to join a dungeon run on your behalf.",
        category = "Party",
        subcategory = "Dungeons"
    )
    var joinDungeonTrustedOnly = false

    @Property(
        type = PropertyType.CHECKBOX,
        name = "Enable AutoJoinIn",
        description = "Listen for 'going/go in 5s' messages and enter a run after the delay (0 to 127s). Optionally specify the floor: 'going in 5s f7'.",
        category = "Party",
        subcategory = "Dungeons"
    )
    var joinDungeonAutoJoinIn = true


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
        super.addDependency("joinDungeonTrustedOnly", "joinDungeonMaster")
    }
}
