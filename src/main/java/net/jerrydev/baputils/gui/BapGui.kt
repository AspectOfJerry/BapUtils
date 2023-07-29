package net.jerrydev.baputils.gui

import gg.essential.elementa.ElementaVersion
import gg.essential.elementa.WindowScreen
import gg.essential.elementa.components.UIText
import gg.essential.elementa.constraints.CenterConstraint
import gg.essential.elementa.constraints.PixelConstraint
import gg.essential.elementa.constraints.SiblingConstraint
import gg.essential.elementa.dsl.childOf
import gg.essential.elementa.dsl.constrain
import net.jerrydev.baputils.BapUtils
import net.jerrydev.baputils.core.BapConfig

class BapGui : WindowScreen(ElementaVersion.V2) {
    init {
        UIText("BapUtils").childOf(window).constrain {
            x = CenterConstraint()
            y = PixelConstraint(45f)
            textScale = PixelConstraint(6.2f)
        }
        UIText("Options menu").childOf(window).constrain {
            x = CenterConstraint()
            y = SiblingConstraint(10f)
            textScale = PixelConstraint(3.2f)
        }

        UIText("> Mod configuration <").childOf(window).constrain {
            x = CenterConstraint()
            y = SiblingConstraint(80f)
            textScale = PixelConstraint(2.2f)
        }.onMouseClick { (event) ->
            BapUtils.setDisplayGui(BapConfig.gui())
        }

        UIText("> Website <").childOf(window).constrain {
            x = CenterConstraint()
            y = SiblingConstraint(20f)
            textScale = PixelConstraint(2.2f)
        }.onMouseClick { (event) ->
            BapUtils.setDisplayGui(BapConfig.gui())
        }

        UIText("> Button e <").childOf(window).constrain {
            x = CenterConstraint()
            y = SiblingConstraint(20f)
            textScale = PixelConstraint(2.2f)
        }.onMouseClick { (event) ->
            BapUtils.setDisplayGui(BapConfig.gui())
        }

        UIText("> Button heh <").childOf(window).constrain {
            x = CenterConstraint()
            y = SiblingConstraint(20f)
            textScale = PixelConstraint(2.2f)
        }.onMouseClick { (event) ->
            BapUtils.setDisplayGui(BapConfig.gui())
        }

        /* UIBlock().childOf(window).constrain {
            x = CenterConstraint()
            y = SiblingConstraint(25f)
            width = PixelConstraint(100f)
            height = PixelConstraint(50f)
        }.onMouseClick { (event) ->
            BapUtils.setDisplayGui(ModConfig().gui());
        } */
    }
}