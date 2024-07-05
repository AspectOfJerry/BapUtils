package net.jerrydev.baputils.commands.bap;

import net.jerrydev.baputils.BapUtils;
import net.jerrydev.baputils.Constants;
import net.jerrydev.baputils.commands.BaseCommand;
import net.jerrydev.baputils.core.BapSettingsGui;

import java.util.Arrays;
import java.util.List;

import static net.jerrydev.baputils.utils.Debug.dout;

public final class BapSettings extends BaseCommand {
    @Override
    public String getName() {
        return "settings";
    }

    @Override
    public List<String> getAliases() {
        return Arrays.asList("config", "cfg");
    }

    @Override
    public String getUsage() {
        return super.getUsage();
    }

    @Override
    public byte getRequiredParams() {
        return super.getRequiredParams();
    }

    @Override
    public String getDesc() {
        return "Opens the settings GUI";
    }

    @Override
    public void run(List<String> args) {
        dout("You are on version " + Constants.kModVersion + "!");
        BapUtils.setActiveGui(BapSettingsGui.INSTANCE.gui());
    }
}
