package net.jerrydev.baputils.gui

import gg.essential.elementa.ElementaVersion
import gg.essential.elementa.WindowScreen
import gg.essential.elementa.components.*
import gg.essential.elementa.components.input.UITextInput
import gg.essential.elementa.constraints.*
import gg.essential.elementa.constraints.animation.Animations
import gg.essential.elementa.dsl.*
import gg.essential.elementa.effects.ScissorEffect
import java.awt.Color


class DemoGui : WindowScreen(ElementaVersion.V2) {
    // Our ExampleGui class will extend from WindowScreen
    // which is a subclass of GuiScreen that will call all mouse/keyboard
    // events for us.
    // In addition, it will construct and provide us with an instance of [Window]
    // that we can use as we need.

    init {
        println("TRY DISPLAY TESTGUI")

        val window = Window(ElementaVersion.V2)

        val box = UIBlock().constrain {
            x = CenterConstraint()
            y = 10f.pixels()
            width = 100f.pixels()
            height = 100f.pixels()
        } effect ScissorEffect() childOf window
        println("TESTGUI L32")
        val newWidth = 200f // Width in pixels
        val newHeight = 150f // Height in pixels

        box.animate {
            setWidthAnimation(Animations.IN_BOUNCE, 2f, PixelConstraint(newWidth))
            setHeightAnimation(Animations.IN_BOUNCE, 2f, PixelConstraint(newHeight))

            onComplete {
                // Trigger new animation or anything.
                println("TESTGUI FINISHED ANIMATE")
            }
        }

        box.onMouseEnter {
            // Animate, set color, etc.
        }
    }
}