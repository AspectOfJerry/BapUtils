package net.jerrydev.baputils.guis

import gg.essential.elementa.ElementaVersion
import gg.essential.elementa.WindowScreen
import gg.essential.elementa.components.UIText
import gg.essential.elementa.constraints.*
import gg.essential.elementa.constraints.animation.Animations
import gg.essential.elementa.dsl.*
import net.jerrydev.baputils.BapUtils
import net.jerrydev.baputils.core.BapConfig

class BapGui : WindowScreen(ElementaVersion.V2) {
    init {
        UIText("BapUtils").childOf(super.window).constrain {
            x = CenterConstraint()
            y = PixelConstraint(45f)
            textScale = PixelConstraint(6.2f)
        }
        UIText("Options menu").childOf(super.window).constrain {
            x = CenterConstraint()
            y = SiblingConstraint(10f)
            textScale = PixelConstraint(3.2f)
        }

        UIText("> Mod configuration <").childOf(super.window).constrain {
            x = CenterConstraint()
            y = SiblingConstraint(80f)
            textScale = PixelConstraint(2.2f)
        }.onMouseClick { (event) ->
            BapUtils.setActiveGui(BapConfig.gui())
        }.onMouseEnter {
            animate {
                setTextScaleAnimation(
                    Animations.OUT_EXP,
                    0.3f,
                    PixelConstraint(2.3f),
                )
            }
        }.onMouseLeave {
            animate {
                setTextScaleAnimation(
                    Animations.OUT_EXP,
                    0.3f,
                    PixelConstraint(2.2f),
                )
            }
        }

        UIText("> Website <").childOf(super.window).constrain {
            x = CenterConstraint()
            y = SiblingConstraint(20f)
            textScale = PixelConstraint(2.2f)
        }.onMouseClick { (event) ->
            BapUtils.setActiveGui(BapConfig.gui())
        }.onMouseEnter {
            animate {
                setTextScaleAnimation(
                    Animations.OUT_EXP,
                    0.3f,
                    PixelConstraint(2.3f),
                )
            }
        }.onMouseLeave {
            animate {
                setTextScaleAnimation(
                    Animations.OUT_EXP,
                    0.3f,
                    PixelConstraint(2.2f),
                )
            }
        }

        UIText("> Button e <").childOf(super.window).constrain {
            x = CenterConstraint()
            y = SiblingConstraint(20f)
            textScale = PixelConstraint(2.2f)
        }.onMouseClick { (event) ->
            BapUtils.setActiveGui(BapConfig.gui())
        }.onMouseEnter {
            animate {
                setTextScaleAnimation(
                    Animations.OUT_EXP,
                    0.3f,
                    PixelConstraint(2.3f),
                )
            }
        }.onMouseLeave {
            animate {
                setTextScaleAnimation(
                    Animations.OUT_EXP,
                    0.3f,
                    PixelConstraint(2.2f),
                )
            }
        }

        UIText("> Button heh <").childOf(super.window).constrain {
            x = CenterConstraint()
            y = SiblingConstraint(20f)
            textScale = PixelConstraint(2.2f)
        }.onMouseClick { (event) ->
            BapUtils.setActiveGui(BapConfig.gui())
        }.onMouseEnter {
            animate {
                setTextScaleAnimation(
                    Animations.OUT_EXP,
                    0.3f,
                    PixelConstraint(2.3f),
                )
            }
        }.onMouseLeave {
            animate {
                setTextScaleAnimation(
                    Animations.OUT_EXP,
                    0.3f,
                    PixelConstraint(2.2f),
                )
            }
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
