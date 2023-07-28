package net.jerrydev.baputils.gui;

import gg.essential.elementa.ElementaVersion;
import gg.essential.elementa.WindowScreen;
import gg.essential.elementa.components.UIText;
import gg.essential.elementa.constraints.CenterConstraint;
import gg.essential.elementa.constraints.PixelConstraint;

public class BapTestGui extends WindowScreen {
        /*
        UIComponent text = new UIText()
            .setText("Hello, world from BapUtils!")
            .setX(new CenterConstraint())
            .setY(new PixelConstraint(25f))
            .setTextScale(new PixelConstraint(1f))
            .setChildOf(getWindow());
        */

    public BapTestGui() {
        super(ElementaVersion.V2);
        new UIText()
                .setText("Hello, world from BapUtils!")
                .setX(new CenterConstraint())
                .setY(new PixelConstraint(50f))
                .setTextScale(new PixelConstraint(5f))
                .setChildOf(getWindow());
    }
}
