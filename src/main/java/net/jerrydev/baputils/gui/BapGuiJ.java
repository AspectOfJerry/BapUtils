package net.jerrydev.baputils.gui;

import gg.essential.elementa.ElementaVersion;
import gg.essential.elementa.UIComponent;
import gg.essential.elementa.WindowScreen;
import gg.essential.elementa.components.UIBlock;
import gg.essential.elementa.components.UIText;
import gg.essential.elementa.constraints.CenterConstraint;
import gg.essential.elementa.constraints.PixelConstraint;
import gg.essential.elementa.constraints.SiblingConstraint;

@Deprecated
public class BapGuiJ extends WindowScreen {
    UIComponent button = new UIBlock()
            .setX(new CenterConstraint())
            .setY(new PixelConstraint(10f))
            .setWidth(new PixelConstraint(10f))
            .setHeight(new PixelConstraint(36f))
            .setChildOf(getWindow());

    @Deprecated
    public BapGuiJ() {
        super(ElementaVersion.V2);
        new UIText()
                .setText("BapUtils!")
                .setX(new CenterConstraint())
                .setY(new PixelConstraint(50f))
                .setTextScale(new PixelConstraint(5f))
                .setChildOf(getWindow());

        new UIText()
                .setText("Hello, World!")
                .setX(new CenterConstraint())
                .setY(new SiblingConstraint(10f))
                .setTextScale(new PixelConstraint(2.5f))
                .setChildOf(getWindow());


    }
}
