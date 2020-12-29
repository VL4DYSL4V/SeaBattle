package enums;

import java.util.HashMap;
import java.util.Map;

public enum MarineRank {

    SEAMAN_APPRENTICE(0),
    SEAMAN(1),
    ENSIGN(2),
    LIEUTENANT_JR_GRADE(3),
    LIEUTENANT(4),
    LIEUTENANT_COMMANDER(5),
    COMMANDER(6),
    CAPTAIN(7),
    REAR_ADMIRAL_LH(8),
    REAR_ADMIRAL(9),
    VICE_ADMIRAL(10),
    ADMIRAL(11),
    FLEET_ADMIRAL(12);

    private final int order;
    private static final Map<Integer, MarineRank> ranks = new HashMap<>();

    static {
        for (MarineRank marineRank : MarineRank.values()){
            ranks.put(marineRank.order, marineRank);
        }
    }

    MarineRank(int order) {
        this.order = order;
    }

    public static MarineRank next(MarineRank marineRank){
        MarineRank out = ranks.get(marineRank.order + 1);
        return (out != null) ? out : marineRank;
    }

    public static MarineRank previous(MarineRank marineRank){
        MarineRank out = ranks.get(marineRank.order - 1);
        return (out != null) ? out : marineRank;
    }

}
