package net.jerrydev.baputils.utils;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.List;

public enum CatacombsFloors {
    FLOOR0("0", false, Arrays.asList("f0", "e0")),
    FLOOR1("1", false, Arrays.asList("f1", "bonzo")),
    FLOOR2("2", false, Arrays.asList("f2", "scarf")),
    FLOOR3("3", false, Arrays.asList("f3", "professor", "the_professor")),
    FLOOR4("4", false, Arrays.asList("f4", "thorn", "thorns")),
    FLOOR5("5", false, Arrays.asList("f5", "livid")),
    FLOOR6("6", false, Arrays.asList("f6", "sadan")),
    FLOOR7("7", false, Arrays.asList("f7", "wither", "maxor", "storm", "goldor", "necron")),
    MASTER1("1", true, Arrays.asList("m1", "master_bonzo")),
    MASTER2("2", true, Arrays.asList("m2", "master_scarf")),
    MASTER3("3", true, Arrays.asList("m3", "master_professor")),
    MASTER4("4", true, Arrays.asList("m4", "master_thorn")),
    MASTER5("5", true, Arrays.asList("m5", "master_livid")),
    MASTER6("6", true, Arrays.asList("m6", "master_sadan")),
    MASTER7("7", true, Arrays.asList("m7", "master_necron"));

    public final String floorId;
    public final boolean isMaster;
    public final List<String> floorAliases;

    CatacombsFloors(String _floorId, boolean _isMaster, List<String> _floorAliases) {
        this.floorId = _floorId;
        this.isMaster = _isMaster;
        this.floorAliases = _floorAliases;
    }

    @Nullable
    public static CatacombsFloors getFloorByName(String name) {
        for (CatacombsFloors floor : CatacombsFloors.values()) {
            if (floor.floorAliases.contains(name.toLowerCase())) {
                return floor;
            }
        }
        return null;
    }
}
