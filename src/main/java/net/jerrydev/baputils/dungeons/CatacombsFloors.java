package net.jerrydev.baputils.dungeons;

import javax.annotation.Nullable;

public enum CatacombsFloors {
    FLOOR0("f0", false, "CATACOMBS_ENTRANCE"),
    FLOOR1("f1", false, "CATACOMBS_FLOOR_ONE"),
    FLOOR2("f2", false, "CATACOMBS_FLOOR_TWO"),
    FLOOR3("f3", false, "CATACOMBS_FLOOR_THREE"),
    FLOOR4("f4", false, "CATACOMBS_FLOOR_FOUR"),
    FLOOR5("f5", false, "CATACOMBS_FLOOR_FIVE"),
    FLOOR6("f6", false, "CATACOMBS_FLOOR_SIX"),
    FLOOR7("f7", false, "CATACOMBS_FLOOR_SEVEN"),
    MASTER0("m0", true, "MASTER_CATACOMBS_ENTRANCE"),
    MASTER1("m1", true, "MASTER_CATACOMBS_FLOOR_ONE"),
    MASTER2("m2", true, "MASTER_CATACOMBS_FLOOR_TWO"),
    MASTER3("m3", true, "MASTER_CATACOMBS_FLOOR_THREE"),
    MASTER4("m4", true, "MASTER_CATACOMBS_FLOOR_FOUR"),
    MASTER5("m5", true, "MASTER_CATACOMBS_FLOOR_FIVE"),
    MASTER6("m6", true, "MASTER_CATACOMBS_FLOOR_SIX"),
    MASTER7("m7", true, "MASTER_CATACOMBS_FLOOR_SEVEN");

    public final String floorCode;
    public final boolean isMaster;
    public final String commandCode;

    CatacombsFloors(String _floorCode, boolean _isMaster, String _commandCode) {
        this.floorCode = _floorCode;
        this.isMaster = _isMaster;
        this.commandCode = _commandCode;
    }

    @Nullable
    public static CatacombsFloors getFloorByName(String name) {
        for(final CatacombsFloors floor : CatacombsFloors.values()) {
            if(floor.floorCode.equals(name.toLowerCase())) {
                return floor;
            }
        }
        return null;
    }
}
