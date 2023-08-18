# BapUtils

Welcome to BapUtils, a Hypixel Skyblock mod with random utility features.

To find the latest release, please visit

This is our first Minecraft mod for Hypixel Skyblock, so if there are any bugs or issues with the mod, please open an
issue on
GitHub or contact us on Discord (@tomassy or @jerrydev).

For more information, please visit <https://bap.jerrydev.dev>. <https://jerrydev.net/bap> will be available later.

## Features (TBD)

<details open>
  <summary>Here some features we would like to add</summary>

- Better dragon name in the m7 boss fight (might not happen because there is already a sbe/skytils feature that let you
  do this).
- Display dragon HP.
- Display dungeon secret route (might not happen or will take a really long time to make).

</details>

## Socials

- Our Discord server is currently private, and we do not have an estimated time of when it will be made public for
  BapUtils. You can contact @tomassy or @jerrydev on Discord for the moement.

## Documentation

### Core features

- Party takeover to allow trusted players to take your party (like a remote `/party transfer`).
- Utility features (not only for Hypixel Skyblock).
    - Minecraft color codes
- Trust feature to unlock certain functionalities such as party takeover.

### Commands (/)

| Names                           | Parameters    | Description                                                        |
|---------------------------------|---------------|--------------------------------------------------------------------|
| bap, baputilities, baputils, bp | *?subcommand* | Main command                                                       |
| cache, data                     | *none*        | Displays values data currently stored in the AtomicMemCache        |
| color(s), colour(s), cc         | *none*        | Displays the Minecraft (§/&) color codes. Use as a reference.      |
| crash, exit                     | playerName    | Sends a game crash request to `playerName`.                        |
| debug, bug                      | *variable*    | Enable debug messages in the chat.                                 |
| dev, test                       | *variable*    | Development command with experimental features.                    |
| joindungeon, dung, join, jd     | floorName     | Joins a dungeon run on the party leader's behalf. (\[fm]\[0-7])    |
| help, ?                         | *none*        | Shows info about commands and their aliases.                       |
| hello, hi, world                | *none*        | Sends a hello message. Use to check if mod is loaded.              |
| options, menu, gui              | *none*        | Opens the Options* GUI.                                            |
| settings, config, cfg           | *none*        | Opens the Settings* GUI.                                           |
| takeover, ptake, pto, to        | *none*        | Take the leader's party.                                           |
| trust, friend, allow, add       | playerName    | Trust `playerName` (saved by uuid). This unlocks certain commands. |
| uuid, player, id                | playerName    | Get `playerName`'s Minecraft UUID.                                 |
| warp, pwarp, pw, w              | *none*        | Warps the party on the party leader's behalf.                      |

*In the context of BapUtils, the terms *Options* and *Settings* are employed in the same fashion as in Minecraft: the
*settings* are within the *options* menu.

## Developer reference

If you encounter issues with IntelliJ such as folders not showing, delete the `.idea` folder and reimport the project.

- Forge 1.8.9 11.15.1.2318
- Gradle JVM: Java 17
- Project SDK: Java 1.8

### Versioning

The BapUtils mod version is hardcoded at 3 locations. You must modify all 3 for a version change.

- Constants.java
- build.gradle.kts
- gradle.properties

### Using DevAuth, by DJtheRedstoner

Documentation: <https://github.com/DJtheRedstoner/DevAuth>

Add JVM Property: `"-Ddevauth.enabled=true"`
See the DevAuth repo README for more information
